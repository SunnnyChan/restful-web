#!/usr/bin/env bash

# usage : sh control.sh start

SERVER="DefaultRwServer"
CONFIG_FILE="conf/rw-server.conf"
OPERATION=$1

sh control.sh ${SERVER} ${CONFIG_FILE} ${OPERATION}



