#!/usr/bin/env bash

## rw-server 模块可以作为一个脚手架
## 执行 该脚本 可以以 rw-server 为模板创建 一个 自己的的 Restful

if [[ $# != 1 ]]
then
 echo "Error usage!"
 exit 1
fi

SCRIPT_DIR="$(cd $(dirname "${BASH_SOURCE[0]}") && pwd )"
SERVER_NAME=$1

if [[ $(uname) == "Darwin" ]]
then
  SED="gsed"
else
  SED="sed"
fi

git clone https://github.com/SunnnyChan/restful-web

find restful-web -name ".git" | xargs rm -Rf

for pom in $(find restful-web -name "pom.xml")
do
  ${SED} -i "s/restful-web/${SERVER_NAME}/g" ${pom}
done

mv restful-web ${SERVER_NAME}

