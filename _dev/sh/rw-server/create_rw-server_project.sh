#!/usr/bin/env bash

## rw-server 模块可以作为一个脚手架
## 执行 该脚本 可以以 rw-server 为模板创建 一个 自己的的 Restful


SCRIPT_DIR="$(cd $(dirname "${BASH_SOURCE[0]}") && pwd )"
FILE_POM="${SCRIPT_DIR}/pom.xml"
SERVER_NAME=$1

if [[ $(uname) == "Darwin" ]]
then
  SED="gsed"
else
  SED="sed"
fi

git clone https://github.com/SunnnyChan/restful-web

find restful-web/rw-server -name ".git" | xargs rm -Rf

cp "${FILE_POM}" restful-web/rw-server
${SED} -i "s/taktin-server/${SERVER_NAME}/g" restful-web/rw-server/pom.xml

cp -r restful-web/rw-server ${SERVER_NAME}

rm -rf restful-web
