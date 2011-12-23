========== db_rtr_serial_query_init() ==========

if no session_id in rtr_session  // handled elsewhere
    cache-reset

if no sn in rtr_update
    cache-reset

if sn in prev_serial_num in rtr_update
    feed sn_next from rtr_incremental

if sn in serial_num in rtr_update
    no new data avail
else
    cache reset


========== db_rtr_reset_query_init() ==========

if no session_id in rtr_session  // handled elsewhere
    cache-reset

if no sn in rtr_update
    not-ready

if no rtr_full for sn
    error
else
    feed sn from rtr_full


========== db_rtr_serial_query_get_next() - abstract ==========

result = query db

while (result.hasNext)
    append row

if (num_pdus == max_pdus)
    update state
    return

// if we get here, both num_pdus < max_pdus  and  !result.hasNext

if (db does not contain sn in serial_num)
    return Error-Report

if (db contains this sn in prev_serial_num)
    update state
    return

append End-of-Data
return


========== db_rtr_serial_query_get_next() - detailed ==========

*is_done = 1

if (query_state.not-ready)
    return Error-Report:No-Data-Avail

if (query_state.bad-sn)
    return Cache-Reset

if (query_state.no-new-data)
    return Cache-Response and End-of-Data

if (max_rows < ???)
    complain

if (!query_state.data_sent)
    append Cache-Response
    set query_state.data_sent
    ++num_pdus

query = select asn, ip_addr, is_announce
        from rtr_incremental
        where serial_num = state->ser_num
        order by asn, ip_addr
        limit max_pdus - num_pdus
        offset ???current_row???
// the offset of the initial row is 0, not 1
// select * from tbl limit 5, 10;  # retrieves rows 6-15
// "limit row_count" is equiv to "limit 0, row_count"

result = query...
num_rows = mysql_num_rows(result)

while (num_rows > 0)
    append PDU
    --num_rows
    ++state->first_row

if (num_rows == max_pdus)
    *is_done = 0
    return num_pdus

if (db does not contain sn in serial_num)
    return Error-Report

if (db contains this sn in prev_serial_num)
    update state
    return

append End-of-Data
return