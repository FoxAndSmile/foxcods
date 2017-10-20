# Web Flux

이번장에서는 Reactive Stack, Netty, Undertow 및 Servlet 3.1+ 컨테이너와 같은 non-blocking Server에서 실행할 Reactive Streams API를 기반의 웹 어플리케이션에 대해서 정리를 해보았습니다.

각각의 챕터는 Spring WebFlux와 functional programming model(함수형 프로그래밍 모델)을 다룰것이며 웹 어플리케이션의 경우 Servlet으로 시작해서 Web application으로 정리를 했습니다.

# 1. Spring WebFlux

## 1.1 소개

Spring Framework와 Spring Web Mvc에 포함된 오리지널 웹 프레임워크는 원래 Servlet API 및 Servlet Container용으로 개발이 되었습니다.

그리고 이번에 반응형 웹 프레임워크로서 Sporing WebFlux가 이번 5.0에 추가가 되었습니다. 이것은 fully non-blocking이며 back pressure을 조절하는 Reactive Stream있습니다. 그리고 WebFlux는 Netty나 Undertow 그리고 Servlet 3.1+ container와 같은 서버에서 실행이 됩니다.

Spring Web Mvc, Spring WebFlux 두개의 웹 프레임워크는 spring-webmvc와 spring-webflux라는 이름으로 Spring framework안에 나란히 공존합니다. 각 모듈은 선택사항이며 당신이 만들 웹 어플리케이션 프로그램인 이 중에 하나 또는 다른 모듈을 사용할 수 있으며 어떤 경우는 둘다 사용이 가능합니다. (예: Reactive WebClient를 가진 Spring MVC Controller)

게다가 웹 프레임워크 외에도 Spring WebFlux는 HTTP 요청을 수행하는 WebClient, Web Endpoint를 테스트 하는 WebTestClient 및 반응형 WebSocket을 지원합니다.

### 1.1.1 왜 새로운 웹 프레임웍을 만들었나요?

첫번째 이유는 적은 수의 스레드로 동시성을 처리하고 적은 하드웨어 자원으로 확장 할 수 있는 Non-blocking Web Stack이 필요한 이유였습니다.

Servlet 3.1 +은 non-blocking I/O를 위한 API를 제공합니다. 하지만 이것을 사용하게 되면 Synchronous(Filter, Servlet) 또는 Blocking(getparameter, getPart)하는 나머지 Servlet Api들과 멀어지게 됩니다.

이것은 새로운 Common API가 모든 Non-blocking Runtime에 대한 기반 역할을 하도록 동기를 부여 했습니다. 이것은 Non-blocking space에 잘 설정된 Netty와 같은 서버 때문에 중요합니다.

두번째 이유로는 함수형 프로그래밍입니다. Java5부터 어노테이션을 추가하는 것과 마찬가지로 어노테이션이 달린 Rest Controllers나 Unit Test에서 Java 8 람다식을 사용하므로써 함수형 API에 대한 기능을 만들 수 있게 되었습니다.

그리고 Java의 CompletableFuture와 ReactiveX에 의해 비동기 로직을 선언하고 구성이 가능합니다. 

프로그래밍 모델 레벨에서 Java8은 Spring WebFlux가 Annotation이 달린 컨트롤러와 함께 함수형 웹 엔드포인트를 제공할 수 있게 했습니다.

### 1.1.2 Reactive?는 무엇인가요?
 
우리는 non-blocking과 functional을 다뤘습니다. 그러나 Reactive는 무엇인지 이것은 무슨의미를 지니는지 알아보겠습니다.

reactive라는 용여는 변화에 대응하기 위해 만들어진 프로그래밍 모델을 의미합니다. 예를 들면 I/O이벤트에 반응하는 네트워크의 구성요소나, 마우스 이벤트에 반응하는 UI 컨트롤러 등등 이러한 의미에서 non-blocking은 reactive적이다라고 할 수 있습니다.

왜냐면 작업이 완료되거나 데이터가 사용이 가능할 때 알람을 보내는 기능등이 있기 때문입니다.

또한 Spring 팀에서 Reactive라고 부르는 또다른 매커니즘이 있습니다. 이것은 non-blocking back pressure 입니다.

동기식, 즉 imperative(명령형) 코드에서의 blocking 형태의 호출은 caller가 결과를 기다리는 자연스러운 형태입니다. 하지만 non-blocking에서는 수신자가 무리하지 않는 범위에서 이벤트 속도를 조절하는것이 중요합니다.

Reactive Stream은 Java9에서도 채택된 자그만한 스팩으로 비동기 컴포넌트간의 back pressure를 정의합니다. 예를 들어 pub/sub 모델에서 pub은 sub역할을 하는 서버가 응답으로 사용할 데이터를 생성할 수 있습니다. Reactive Stream의 주요 목적은 sub로 보내는 데이터를 얼마나 빨리 만들거나 느리게 만드거나를 제어하는 것이 주목적입니다.

#### 만약 publisher가 속도를 늦출수 없으면 어떻게 하죠?

Reactive Stream은 오직 메커니즘과 바운더리를 설정하는 것입니다. 만약 publisher가 속도를 늦출수 없다면 버퍼를 두던가 삭제를 하던가 실패 처리를 해야합니다.

### 1.1.3 Reactive API

Reactive Stream은 상호 운영성에 중요한 역할을 합니다. 라이브러리나 인프라 구성 요소로서는 관심 포인트지만 응용 프로그램의 API는 너무 낮은 수준이므로 응용 프로그램 API로는 유용하지 않습니다.

Java 8 Stream API와 유사하지만 콜렉션뿐 아니라 비동기 로직을 구성하는 더 높은 수준의 풍부하고 기능적인 API를 Reactive 라이브러리들이 지원해줍니다.

자 Reactor는 Spring WebFlux에서 선택한 Reactive 라이브러리 입니다.

It provides the Mono and Flux API types to work on data sequences of 0..1 and 0..N through a rich set of operators aligned with the ReactiveX vocabulary of operators.

Reactor는 Reactive Stream 라이브러리이므로 non-blocking back pressure를 지원합니다. Reactor는 서버 사이드로 자바에 중점을 두고 잇습니다. 또한 Spring과 긴밀히 협력하여 개발이 되었습니다.

WebFlux는 Reactor를 필수 요소로 사용하지만 Reactive Stream을 통해서 다른 라이브러리와 상호 운용됩니다. 일반적으로 WebFlux API는 일반 publisher를 입력으로 받고 이를 내부적으로 Reactor Type으로 적용한 후 이것을 사용하고 Flux나 Mono로 출력합니다.

즉 Publisher에서 입력하고 출력에서 여러 연산을 적용 할 수 있지만 여러 다른 Reactive library에서 사용하기 위해선 output을 수정 할 필요가 있습니다.

Whenever feasible — e.g. annotated controllers, WebFlux adapts transparently to the use of RxJava or other reactive library. See Reactive Libraries for more details.

### 1.1.4 Programming models

Spring-web 모듈에는 Web Flux의 근간이 되는 Reactive 토대가 담겨져 있습니다. 예를 들면 Http 추상화, Reactive Streams server adapter, reactive codecs, Servlet Api와 비슷하지만 Non-blocking의 의미를 갖는 core API.

Spring WebFlux사용할 경우 2가지의 프로그래밍 모델중에서 하나를 선택 할 수 있습니다.

1. Annotated Controllers : Spring MVC와 비슷하며 spring-web 모듈과 동일한 Annotation을 기반으로 합니다. Spring MVC나 WebFlux Controller 둘다 모두 Reactive(Reactor, RxJava) Return Type을 지원하므로 결과를 쉽게 구분 할 수 가 없습니다. 주목할 만한 차이점은 WebFlux가 reactive @RequestBody 인자를 지원한다는 점입니다.

2. Funtional Endpoint : 람다 기반의 가벼운 함수형 프로그래밍 모델로써 이것은 Application이 요청을 라우팅하고 처리하는데 사용하는 작은 라이브러리나 유틸 셋으로 생각하면 됩니다. Annotated Controller와의 큰 차이점은 Application이 Annotation을 통해 intent를 선언하고 콜백되는 것보다 첨부터 끝까지 요청 처리를 담당한다는 점입니다.

### 1.1.5 Choosing a web framework

Spring MVC 또는 WebFlux중 무엇을 사용해야할까요? 몇가지 특징을 살펴 보기로 합시다.

만약 당신이 잘 작동하는 Spring MVC기반의 어플리케이션이 있으면 딱히 변경할 필요가 없습니다. 사실 Imperative(명령형) 프로그래밍은 코드를 작성하고 이해하고 디버깅하는 가장 쉬운 방법이기 때문입니다. 현재까지 가장 많은 라이브러리들이 존재하고 있습니다.

만약 non-blocking web stack을 이미 구매했다면 Spring WebFlux는 다른 실행 모델과 동일한 실행 모델 혜택을 제공하며 Netty, Tomcat, Jetty, Undertow, Servlet 3.1+ 컨테이너등 다양한 서버를 선택 할 수 있습니다. 

annotated Controllers나 functional web endpoints나 여러 Reactive library(Reactor, RxJava  등등)을 프로그래밍 모델로 선택 할 수 있습니다.

만약 당신이 Java8 람다나 Kotlin과 함께 가볍고 사용하기 쉬운 WebFramework에 관심이 있다면 Spring WebFlux Functional Web Endpoints를 사용하길 바랍니다. 이것은 높은 투명성과 컨트롤하기 쉬우며 덜 복잡한 요구사항 기능을 가진 소규모 응용 프로그래밍이나 마이크로서비스에도 적합합니다.

마이크로서비스 아키텍처에서는 Spring MVC 또는 Spring WebFlux Controller 또는 Spring WebFlux Functional endpoints를 함께 사용 할 수 있습니다.

두 프레임워크 모두 Annotaion 기반 프로그래밍 모델을 지원하며 올바른 작업을 위한 도구를 선택하는 동시에 쉽게 배우고 쓸수 있습니다.

어플리케이션을 판단하는 간당한 방법은 어플리케이션의 종속성을 확인하는 것입니다. Persistence API또는 사용할 Networking API를 블로킹해서 사용할 경우 Spring MVC는 적합하다고 할 수 있습니다. Reactor와 RxJava 모두 별도의 스레드에서 블로킹 호출을 수행하는 것이 기술적으로 가능하지만 non-blocking web stack을 최대한 활용하지는 않습니다.

Remote Service를 호출하는 Spring MVC 어플리케이션이 있으면 한번 Reactive WebClient를 시도해보세요. Reactive(Reactor, RxJava 등등)을 Spring MVC Controller 메소드에서 직접 반환 할 수 잇습니다. 호출당 대기 시간이나 호출간 상호의존성이 클 수록 더 많은 이점이 있습니다. Spring MVC Controller는 다른 Reactive component들도 호출을 할 수 있습니다.

만약 규모가 큰 팀의 경우 non-bloking, functional, declarative programming에 대해서 높은 학습 곡선을 염두하시길 바랍니다. 우선 reactive Webclient를 사용하면서 연습을 하길 바랍니다.  Beyond that start small and measure the benefits. We expect that for a wide range of applications the shift is unnecessary.

만약 이걸 써야 할지 말아야 할지 잘모르겟으면 non-blocking I/O가 작동하는 방식 및 그 효과를 학습하시길 바랍니다. 좋은 글로는 아래 블로그를 읽어보세요.

https://medium.com/netflix-techblog/zuul-2-the-netflix-journey-to-asynchronous-non-blocking-systems-45947377fb5c

### 1.1.6 Choosing a server

Spring WebFlux는 Netty, Undertow, Tomcat, Jetty 및 Servlet 3.1+ 컨테이너에서 지원합니다. 각 서버는 공통 Reactive Stream Api에 맞게 조정됩니다. Spring WebFlux 프로그래밍 모델은 Common API를 기반으로 합니다.

#### Common question: how can Tomcat and Jetty be used in both stacks?

Tomcat and Jetty are non-blocking at their core. It’s the Servlet API that adds a blocking facade. Starting in version 3.1 the Servlet API adds a choice for non-blocking I/O. However its use requires care to avoid other synchronous and blocking parts. For this reason Spring’s reactive web stack has a low-level Servlet adapter to bridge to Reactive Streams but the Servlet API is otherwise not exposed for direct use.

Spring Boot2는 기본적으로 WebFlux와 함께 Netty를 사용합니다. Netty가 Non-blocking 관련해서 널리 사용되며 자원을 공유할 수 있는 클라이언트 서버를 모두 제공을 하기 때문입니다. Spring boot2에서의 기본 서버 선택은 기본적으로만 제공될 뿐 개발할 어플리케이션에서 다른 서버에 최적화된 Reactive Stream을 가진 여러 서버를 선택 할 수 있습니다. 

### 1.1.7 Performance vs scale

성능으로 보면 많은 특징과 의미가 있습니다. Reactive 및 non-blocking은 일반적으로 어플리케이션을 더욱 빠르게 동작시키진 않습니다. 경우에 따라서는 원격 호출을 병렬로 실행 할 수 잇습니다. 아무튼 전체적으로 non-blocking  방식을 수행하기 위해서 더 많은 작업이나 처리시간이 필요 할 수도 있습니다.

reactive 및 non-blocking의 이점은 고정된 스레드 수와 적은 메모리로 확장 할 수 있다는 점입니다. 즉 응용프로그래밍이 예측이 가능한 방식으로 확장이 되므로 보다 탄력적이라고 할 수 있습니다. 그러나 이러한 이점을 관찰해보려면 느리거나 예측 할 수 없는 네트워크 I/O를 포함한 대기 시간이 필요합니다. That’s where the reactive stack begins to show its strengths and the differences can be dramatic. 

## 1.2 Reactive Spring Web

Spring-web 모듈은 반응이 적은 웹 어플리케이션을 구현하기위해 클라이언트 및 서버와 같은 낮은 수준의 인프라와 HTTP 추상화를 제공합니다. 모든 public API는 Reactor가 지원되는 reactive Streams을 기반으로 합니다.

서버의 지원은 2가지 계층으로 구성이 되어 있습니다.

1. HttpHandler 및 Server Adapters : Reactive Stream back pressure로 Http 요청처리를 위한 가장 기본적으로 공통 적인 API들입니다.

2. WebHandler API : 약간 높은 Level 이지만 filter chain 스타일 처리의 범용적인 Web API들 입니다.

### 1.2.1 HttpHandler

모든 HTTP 서버에는 HTTP 요청 처리를 위한 API가 존재합니다. HttpHandler는 요청 및 응답을 처리하는 한가지 메소드를 사용하는 쉬운 방법입니다. 주목적으로는 서로 다른 서버에서 Http 요청 처리를 위한 공통 Reactive Stream을 제공하는 것입니다.

Spring-web 모듈에는 지원되는 모든 서버에 대한 어댑터가 있습니다. 아래는 서버 API 사용되고 Reactive Stream 지원이 되는 곳을 보여줍니다.