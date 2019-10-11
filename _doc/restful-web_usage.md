# How to quickly create a web service or web application?

## Create Web Application or Service
* 获取源码
```sh
git clone https://github.com/SunnnyChan/restful-web.git
```
* 编译源码
```sh
mvn clean install
```
* 创建基于 restful-web 框架的 web 项目
```md
执行 restful-web/_dev/sh/rw-server/create_rw-server_project.sh 脚本

如 要在 /Users/sunny/WorkPlace 目录下 创建 名为 sunny-server 的web项目，执行：
cd /Users/sunny/WorkPlace  && sh create_rw-server_project.sh
```
```md
在 sunny-server/src/main/java/com/sunny/rw/server/modules 目录下 创建 mainClass：
SunnyServer.java
```
```java
public class SunnyServer extends AbstractRwServer {

  public static void main(String[] args) throws Exception {
    parserConf(args);
    SunnyServer rwServer = new SunnyServer();
    rwServer.startServer();
    rwServer.startModules();
  }

  private static void parserConf(String[] args) {
    if (args.length != 1) {
      System.out.println("Usage: <config file path>");
      System.exit(1);
    }
    ConfInit.init(args[0]);
  }
}
```
```md
实际上 restful-web 中的 rw-server 模块是一个创建Web应用的脚手架。
```

## Create a server named SampleServer

#### 
```text
server {
  name : sample-server // server name
  modules: ["sample.SampleModule"] // thread modules to start
  port : 8899  // server port
  env : "product" // deploy type

  # running as web service
  service-register-flag : false  // need to register as a service
  zk_register-path : "" // use zk as service discovery infra
}
```
[sample-rw-server.conf](conf/sample-rw-server.conf)
### start
```bash
sh sample-control.sh start
```
### API
#### http://localhost:8899/sample/v1/hello-world
* GET
* Response
```json
{
    "meta": {
        "code": 0,
        "errorType": null,
        "errorMsg": null,
        "traceId": null
    },
    "data": {
        "message": "hello world!"
    }
}
```

#### http://localhost:8899/sample/v1/echo
* POST
* Request
```json
{
    "r1": "test1",
    "r2": "test2"
}
```
* Response
```json
{
    "meta": {
        "code": 0,
        "errorType": null,
        "errorMsg": null,
        "traceId": null
    },
    "data": {
        "echo": {
            "r2": "test2",
            "r1": "test1"
        }
    }
}
```
## Deploy

