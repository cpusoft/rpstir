#include "logutils.h"
#include "macros.h"

#include "db.h"


#define LOG_PREFIX "[db] "

struct db_request_state {
	struct db_request request;
	struct db_query_progress progress;
};


static bool send_error(const struct db_request * request, error_code_t error_code, db_semaphore_t * more_data_semaphore)
{
	struct db_response * response = malloc(sizeof(struct db_response));
	if (response == NULL)
	{
		log_msg(LOG_ERR, LOG_PREFIX "can't allocate memory for response");
		return false;
	}

	response->PDUs = malloc(sizeof(PDU));
	if (response->PDUs == NULL)
	{
		log_msg(LOG_ERR, LOG_PREFIX "can't allocate memory for response PDU");
		free((void *)response);
		return false;
	}

	response->PDUs[0].protocolVersion = PROTOCOL_VERSION;
	response->PDUs[0].pduType = PDU_ERROR_REPORT;
	response->PDUs[0].errorCode = error_code;
	response->PDUs[0].length = PDU_HEADER_LENGTH + PDU_ERROR_HEADERS_LENGTH;
	response->PDUs[0].errorData.encapsulatedPDULength = 0;
	response->PDUs[0].errorData.encapsulatedPDU = NULL;
	response->PDUs[0].errorData.errorTextLength = 0;
	response->PDUs[0].errorData.errorText = NULL;

	response->num_PDUs = 1;

	response->more_data_semaphore = more_data_semaphore;

	if (!Queue_push(request->response_queue, (void *)response))
	{
		log_msg(LOG_ERR, LOG_PREFIX "can't push response to queue");
		free((void *)response->PDUs);
		free((void *)response);
		return false;
	}

	if (sem_post(request->response_semaphore) != 0)
	{
		log_msg(LOG_ERR, LOG_PREFIX "can't post to the response semaphore");
		return false;
	}

	return true;
}


static void cancel_all(Bag * currently_processing)
{
	if (currently_processing == NULL)
	{
		log_msg(LOG_ERR, LOG_PREFIX "got NULL currently_processing");
		return;
	}

	Bag_iterator it;
	struct db_request_state * request_state;

	Bag_start_iteration(currently_processing);
	for (it = Bag_begin(currently_processing);
		it != Bag_end(currently_processing);
		it = Bag_erase(currently_processing, it))
	{
		request_state = (struct db_request_state *)Bag_get(currently_processing, it);

		if (request_state == NULL)
		{
			log_msg(LOG_ERR, LOG_PREFIX "got NULL request state");
			continue;
		}

		send_error(&request_state->request, ERR_INTERNAL_ERROR, NULL);

		free((void *)request_state);
	}
	Bag_stop_iteration(currently_processing);
}


void * db_main(void * args_voidp)
{
	struct db_main_args * argsp = (struct db_main_args *)args_voidp;

	if (argsp == NULL || argsp->semaphore == NULL || argsp->db_request_queue == NULL)
	{
		log_msg(LOG_ERR, LOG_PREFIX "received NULL argument");
		return NULL;
	}

	Bag * currently_processing = Bag_new(false);

	if (currently_processing == NULL)
	{
		log_msg(LOG_ERR, LOG_PREFIX "can't create currently_processing bag");
		return NULL;
	}

	bool operation_completed;
	Bag_iterator it;
	struct db_request * request;

	while (true)
	{
		if (sem_wait(argsp->semaphore) != 0)
		{
			cancel_all(currently_processing);
			Bag_free(currently_processing);
			return NULL;
		}

		operation_completed = false;

		Bag_start_iteration(currently_processing);
		for (it = Bag_begin(currently_processing);
			it != Bag_end(currently_processing);
			it = Bag_iterator_next(currently_processing, it))
		{
			// TODO
		}
		Bag_stop_iteration(currently_processing);

		if (!operation_completed && Queue_trypop(argsp->db_request_queue, (void**)&request))
		{
			// FIXME/TODO: real implementation instead of this stub
			if (request == NULL)
			{
				log_msg(LOG_ERR, LOG_PREFIX "got NULL db request");
			}
			else
			{
				if (!send_error(request, ERR_INTERNAL_ERROR, NULL))
					log_msg(LOG_ERR, LOG_PREFIX "couldn't send error");

				free((void *)request);
			}
		}
	}
}
