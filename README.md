# API Server Test

---


클라이언트에서 온 요청을 api server 가 받아서 Dummy server 나 Redis 에서 데이터를 읽은 후 Client 에게 돌려준다. 

Gatling Test Tool 다른 Repository 참조

## 시나리오

---


## 스터디 내용
```
WebFlux
WebClient
RestTemplate
RxJava?
Reactive Programing (Mono, Flux)

Async, Sync, Blocking, Non Blocking


Lettuce
Data-Redis

Netty
Data-Redis-Reactive

Gatling
```


## 환경 
- api server
  ```
  main pc 1 core /2g memory docker
  ```
- gatling - suttle Window (다른 Repository)
- Redis or Dummy
  ```
  suttle ubuntu  4 core /4g memory docker or real 
  main pc docker  
  ```
## docker-compose 
- api server spring mvc + Dummy
- api server spring mvc + Redis
- api server webflux + Dummy
- api server webflux + Redis

## 참고 
- https://www.baeldung.com/spring-log-webclient-calls -- log.. 
- https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html
- https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.webclient.runtime
- https://www.baeldung.com/spring-webclient-json-list
- https://github.com/callicoder/spring-webclient-webtestclient-demo/tree/master/src/main/java/com/example/webclientdemo

## Test

