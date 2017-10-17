# Web Flux

이번장에서는 Reactive Stack, Netty, Undertow 및 Servlet 3.1+ 컨테이너와 같은 non-blocking Server에서 실행하기 위해 Reactive Streams API를 기반으로 개발할 웹 어플리케이션에 대해서 정리를 해보았습니다.

각각의 챕터는 Spring WebFlux와 functional programming model(함수형 프로그래밍 모델)을 다룰것이며 웹 어플리케이션의 경우 Servlet으로 시작해서 Web application으로 정리를 했습니다.

# 1. Spring WebFlux

## 1.1 소개

Spring Framework와 Spring Web Mvc에 포함된 오리지널 웹 프레임워크는 원래 Servlet API 및 Servlet Container용으로 개발이 되었습니다.

그리고 이번에 반응형 웹 프레임워크로서 Sporing WebFlux가 이번 5.0에 추가가 되었죠. 이것은 fully non-blocking이며 Reactive Stream back pressure를 지원하고 Netty나 Undertow 그리고 Servlet 3.1+ container와 같은 서버에서 실행이 됩니다.

Spring Web Mvc, Spring WebFlux 두개의 웹 프레임워크는 spring-webmvc와 spring-webflux라는 이름으로 Spring framework안에 나란히 공존합니다. 각모듈은 선택사항이며 당신이 만들 웹 어플리케이션 프로그램인 이 중에 하나 또는 다른 모듈을 사용할 수 있으며 어떤 경우는 둘다 사용이 가능합니다. (예: Reactive WebClient를 가진 Spring MVC Controller)

게다가 웹 프레임워크 외에도 Spring WebFlux는 HTTP 요청을 수행하는 WebClient, Web Endpoint를 테스트 하는 WebTestClient 및 클라이언트 및 서버 반응형 WebSocket을 지원합니다.

### 1.1.1 왜 새로운 웹 프레임웍을 만들었나?

첫번째 이유는 적은 수의 스레드로 동시성을 처리하고 적은 하드웨어 자원으로 확장 할 수 있는 Non-blocking Web Stack이 필요한 이유 였습니다.

Servlet 3.1 +은 non-blocking I/O를 위한 API를 제공합니다. 하지만 이것을 사용하게 되면 Synchronous(Filter, Servlet) 또는 Blocking(getparameter, getPart)하는 나머지 Servlet Api들과 멀어지게 됩니다.

이것은 새로운 Common API가 모든 Non-blocking Runtime에 대한 기반 역할을 하도록 하는데에 동기를 부여 했습니다. 이것은 Non-blocking space에 잘 설정된 Netty와 같은 서버 때문에 중요합니다.

두번째 이유로는 함수형 프로그래밍입니다. Java5부터 어노테이션을 추가하는 것과 마찬가지로 어노테이션이 달린 Rest Controllers나 Unit Test에서 Java 8 람다식을 사용하므로써 함수형 API에 대한 기능을 만들 수 있게 되었습니다.

그리고 Java의 CompletableFuture와 ReactiveX에 의해 비동기 로직을 선언하고 구성이 가능합니다. 

프로그래밍 모델 레벨에서 Java8은 Spring WebFlux가 Annotation이 달린 컨트롤러와 함께 함수형 웹 엔드포인트를 제공할 수 있게 했습니다.

 