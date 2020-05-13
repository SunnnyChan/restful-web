#!/usr/bin/env bash

# usage : sh control.sh DefaultRwServer start

SERVER=$1
OPERATION=$2

WORK_HOME="$(cd $(dirname $0) && pwd -P)"
cd ${WORK_HOME}

CONFIG_FILE="conf/rw-server.conf"

logfile="var/${SERVER}.log"
PID_FILE="var/${SERVER}.pid"

mkdir -p var &>/dev/null

function get_pid() {
    if [ -f ${PID_FILE} ];then
        cat ${PID_FILE}
    fi
}
function check_pid() {
    pid=$(get_pid)
    if [ "x_" != "x_$pid" ]; then
        running=$(ps -p ${pid} | grep -v "PID TTY" |wc -l)
        return ${running}
    fi
    return 0
}

function start() {
    nohup java -Xms2g -Xmx8g -cp rw-server-1.0.jar com.sunny.rw.server.application.${SERVER} ${CONFIG_FILE} > start.log 2>&1 &
    echo $! > ${PID_FILE}
}

function stop() {
    for (( i = 0; i < 60; i++ )); do
        kill `get_pid` &>/dev/null
        check_pid
        if [ $? -eq 0 ];then
            echo "stopped"
            return 0
        fi
        sleep 1
    done
    echo "stop timeout(60s)"
    return 1
}

case ${OPERATION} in
    "start" )
        start
        ;;
    "stop" )
        stop
        ;;
    "restart" )
        stop && start
        ;;
    * )
        echo "unknown command option"
        exit 1
        ;;
esac
