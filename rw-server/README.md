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


## Deploy

