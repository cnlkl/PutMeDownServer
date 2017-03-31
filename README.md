# PutMeDownServer

PutMeDown是一款计时记录app，专为低头族打造，在计时期间打开其他应用会自动返回本应用。
每次计时都会有相应备注以供日后回顾，还有图表化的成就系统方便用户查看。

目前已经开发完成第一个版本，实现了基本功能。

[android客户端](https://github.com/cnlkl/put-me-down)

新版本的服务端已开发完毕，客户端尚在开发，主要增加了**好友排行**功能

## 接口文档地址

[接口文档地址](https://www.showdoc.cc/pmd)

## 运行前配置

**运行前请在\src\main\resources下添加配置文件application.yml或者application.properties，配置完成后命令行使用mvn spring-boot:run运行**

```
# 此处以application.yml为例

spring:
#datasource
  datasource:
    username: your account
    password: yout password
    url: your jdbc url
#jpa
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: false

#redis
  redis:
    host: your redis server host
    port: your redis port
    password: your password
    timeout: 15000
    database: 0

#cache
  cache:
    ehcache:
      config: ehcache.xml

#log
logging:
  config: classpath:logback.xml

```

## 环境

开发工具: IntelliJ IDEA 2016.3.5

java: 1.8

maven: 3.3.9

tomcat: 8.5.8

redis: 3.2.8
 
mysql: 5.7.15

## 框架

SpringBoot: 方便快速开发，减少手动配置

Spring: 提供对依赖注入及切面的支持

SpringMVC: Web框架

Hibernate, JPA: 提供持久层的支持

logback, slf4j: 日志管理 


