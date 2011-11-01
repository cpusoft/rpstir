#!/bin/sh

# ***** BEGIN LICENSE BLOCK *****
# 
#  BBN Address and AS Number PKI Database/repository software
#  Version 3.0-beta
# 
#  US government users are permitted unrestricted rights as
#  defined in the FAR.
# 
#  This software is distributed on an "AS IS" basis, WITHOUT
#  WARRANTY OF ANY KIND, either express or implied.
# 
#  Copyright (C) Raytheon BBN Technologies Corp. 2010.  All Rights Reserved.
# 
#  Contributor(s): Mark Reynolds
# 
#  ***** END LICENSE BLOCK ***** */

# set environment variables if not set
THIS_SCRIPT_DIR=$(dirname $0)
. $THIS_SCRIPT_DIR/../envir.setup

${RPKI_ROOT}/rsync_aur/rsync_aur -t ${RPKI_PORT} -d ${RPKI_ROOT}/performance/REPOSITORY/apnic.mirin.apnic.net/mock -f ${RPKI_ROOT}/performance/LOGS/apnic.mirin.apnic.net/mock.log