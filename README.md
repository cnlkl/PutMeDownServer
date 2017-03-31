**运行前请在\src\main\resources下添加配置文件application.yml或者application.properties**

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