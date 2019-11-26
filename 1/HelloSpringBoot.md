# 搭建Spring Boot开发环境  
1. 安装JDK  
2. 安装Spring Tool开发工具   

### 安装JDK   
下载地址：[JDK下载](https://www.oracle.com/technetwork/java/javase/downloads/index.html);  
选择同意后进行下载  
![image](https://github.com/xuanu/Hello-Spring-Boot/raw/master/1/img/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202019-11-26%2010.42.18.png);   
安装过程则是一直下一步即可。  
安装完成后，将：JDK安装目录/bin/ 添加到环境变量Path中。    
可参考[jdk的下载与安装详细图解](https://blog.csdn.net/qq_42003566/article/details/82629570)进行安装  
### 安装Spring Tool开发工具     
> 下载地址：[Spring Tool ](https://spring.io/tools/);  
基于Eclipse的修改版本，更适合Spring Boot框架   
直接解压即可。   
### 创建一个项目  
new -> Spring Starter Project     
![image](https://github.com/xuanu/Hello-Spring-Boot/blob/master/1/img/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202019-11-26%2010.56.11.png)   
![image](https://github.com/xuanu/Hello-Spring-Boot/blob/master/1/img/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202019-11-26%2010.59.34.png)   
![image](https://github.com/xuanu/Hello-Spring-Boot/blob/master/1/img/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202019-11-26%2011.00.56.png)      
项目结构如图：   
![image](https://github.com/xuanu/Hello-Spring-Boot/blob/master/1/img/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202019-11-26%2011.30.17.png)   
右键Application 以SpringBoot的方式运行   
```

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.2.1.RELEASE)

2019-11-26 11:32:33.311  INFO 2891 --- [           main] cn.zeffect.gzgcxy.Gzgcxy2Application     : Starting Gzgcxy2Application on zeffects-MacBook-Pro.local with PID 2891 (/Users/zeffect/Desktop/soft/spring/work/GZGCXY-2/target/classes started by zeffect in /Users/zeffect/Desktop/soft/spring/work/GZGCXY-2)
2019-11-26 11:32:33.313  INFO 2891 --- [           main] cn.zeffect.gzgcxy.Gzgcxy2Application     : No active profile set, falling back to default profiles: default
2019-11-26 11:32:33.790  INFO 2891 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2019-11-26 11:32:33.796  INFO 2891 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2019-11-26 11:32:33.797  INFO 2891 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.27]
2019-11-26 11:32:33.845  INFO 2891 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2019-11-26 11:32:33.845  INFO 2891 --- [           main] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 505 ms
2019-11-26 11:32:33.953  INFO 2891 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
2019-11-26 11:32:34.083  INFO 2891 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2019-11-26 11:32:34.086  INFO 2891 --- [           main] cn.zeffect.gzgcxy.Gzgcxy2Application     : Started Gzgcxy2Application in 0.953 seconds (JVM running for 6.436)
```     
成功运行后，控制台会进行输出   

