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
1. lettuce: 확장 가능하고 쓰레드 세이프한 레디스 클라이언트이다. 동기, 비동기, 리액티브 하게 사용될 수 있다. 

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
- https://www.baeldung.com/spring-boot-redis-cache //Redis 매서드 Chashing 에 대한 내용 
- https://ozofweird.tistory.com/entry/Spring-Boot-Redis-Cache-MySQL-%EC%9D%B4%EC%9A%A9%ED%95%9C-%EA%B0%84%EB%8B%A8%ED%95%9C-API-%EC%A0%9C%EC%9E%91 // 캐싱에 대한 내용
- https://github.com/pamesh12/spring-boot/blob/master/spring-cache-redis/src/main/java/com/pamesh/service/impl/UserServiceImpl.java cache 관련 깃 헙
- https://github.com/bclozel/webflux-workshop // webflux workshop
## Test

## redis 
- redis Sentinel 
  - 자동으로 Fail over 를 해주거나, 서비스 Discovery를 해주고 서비스를 모니터링 하는 레지스 기능
  - redis-server /path/to/sentinel.conf --sentinel
  
- redis-cli
  - redis client 이다. 
  - HGETALL: hash type data 를 가져올떄 
  - KEYS *: 모든 키 가져 오기 

## todo
1. 캐싱 
2. 도커 자원 제한 
3. GC 관리 

https://stackoverflow.com/questions/35997541/getting-org-hibernate-lazyinitializationexception-exceptions-after-retrieving