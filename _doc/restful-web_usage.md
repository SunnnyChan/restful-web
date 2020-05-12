# How to quickly create a web service or web application?  

## Create Web Project  

* 获取源码  

```bash
git clone https://github.com/SunnnyChan/restful-web.git
```  

* 创建基于 restful-web 框架的 web 项目  

```bash
# 例如 要在 /Users/sunny/WorkPlace 目录下 创建 名为 sunny-web 的web项目，执行：  
cd  /Users/sunny/WorkPlace，
# 拷贝源代码中脚本 restful-web/_dev/sh/rw-server/create_rw-server_project.sh 至当前目录，
sh create_rw-server_project.sh sunny-web
```

# 在 sunny-server/src/main/java/com/sunny/rw/server/modules 目录下 创建 mainClass：
SunnyServer.java


## Create a Web server named SampleServer  

```java
public class SampleServer extends AbstractRwServer {

    public static void main(String[] args) throws Exception {
        parserConf(args);
        SampleServer rwServer = new SampleServer();
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

* 对应的配置文件  

sample-rw-server.conf

```conf
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

### build & start  

```bash
sh sample-control.sh build && sh sample-control.sh start
```

***TODO***  

rw-server 实例的生成 需要修改脚本来自动生成。  

### 测试

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


