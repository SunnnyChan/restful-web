# rw-server
>  RESTful web Server

## Architecture
```md
├── README.md
├── conf
│   ├── rw-server.conf
│   └── sample-rw-server.conf
├── logs
│   └── sample-server.log
├── output
│   ├── conf
│   │   ├── rw-server.conf
│   │   └── sample-rw-server.conf
│   ├── control.sh
│   ├── rw-server-1.0.jar
│   └── sample-control.sh
├── pom.xml
├── sh
│   ├── build.sh
│   ├── control.sh
│   ├── create_server.sh
│   ├── sample-build.sh
│   └── sample-control.sh
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── sunny
│   │   │           └── rw
│   │   │               └── server
│   │   │                   ├── conf
│   │   │                   │   └── Conf.java
│   │   │                   ├── controller
│   │   │                   │   └── SampleResource.java
│   │   │                   ├── domain
│   │   │                   │   ├── biz
│   │   │                   │   ├── def
│   │   │                   │   ├── page
│   │   │                   │   │   ├── PageBase.java
│   │   │                   │   │   ├── PageSampleEcho.java
│   │   │                   │   │   └── PageSampleHelloWord.java
│   │   │                   │   └── service
│   │   │                   │       ├── dao
│   │   │                   │       └── data
│   │   │                   ├── model
│   │   │                   │   └── NullRequest.java
│   │   │                   └── modules
│   │   │                       ├── AbstractModule.java
│   │   │                       ├── ModuleInterface.java
│   │   │                       └── sample
│   │   │                           ├── SampleModule.java
│   │   │                           └── SampleRestfulServer.java
│   │   └── resources
│   │       └── logback.xml
```

### Controller

### Page

### Biz

### Service

#### Data

#### DAO


## Create Web Application or Service

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

