#!/usr/bin/env bash

BASE_DIR="$(cd $(dirname "${BASH_SOURCE[0]}") && pwd )/.."
OUTPUT_DIR=${BASE_DIR}/output

WORK_MODULE="rw-server"

MODULES="
common-utils
common-db
common-web

infra-config
service-discovery
rw-server
"

function build() {
    local module=$1
    cd "${BASE_DIR}/../${module}" && mvn clean install -Dmaven.test.skip=true
    ret=$?
    if [ ${ret} -ne 0 ]
    then
        echo "maven install "${module}" failed"
        return ${ret}
    else
        echo "maven install "${module}" OK"
        return 0
    fi
}

# build modules
echo "== start build module"
echo "== Java version : "
echo $(java -version)

for line in ${MODULES}
do
    if ! build ${line}
    then
        exit 1
    fi
done

# build deploy file
rm -rf ${OUTPUT_DIR}
mkdir ${OUTPUT_DIR}
cp "${BASE_DIR}/sh/control.sh" ${OUTPUT_DIR}

cp -r ${BASE_DIR}/conf ${OUTPUT_DIR}
mv ${BASE_DIR}/target/rw-server-1.0.jar ${OUTPUT_DIR}