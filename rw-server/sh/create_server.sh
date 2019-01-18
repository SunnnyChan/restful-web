#!/usr/bin/env bash

SERVER_NAME=$1

BASE_DIR="$(cd $(dirname "${BASH_SOURCE[0]}") && pwd )/.."
CONF_DIR="${BASE_DIR}/conf"
SH_DIR="${BASE_DIR}/sh"
SRC_MODULE_DIR="${BASE_DIR}/src/main/java/com/sunny/rw/server/modules"

