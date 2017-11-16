# Angular 4

한번 Angular4에 대해서 알아보자.

# 1. NPM(Node Package Manager)?

웹 어플리케이션의 개발 규모가 커지면서 난독화, 압축, 번들링, 테스트등을 해야하는데 이러한 서비스를 패키지로 제공하고 관리를 해주는 역할을 한다.

Window 기준에서 Node.js를 설치하면 바로 설치가 된다.

## 1.1 NPM 명령어

* npm init
* npm install
* npm uninstall
* npm list
* npm prune
* npm link
* npm run

## 1.2 package.json

프로잭트의 정보를 JSON으로 담고 있는 파일. 직접 입력할 수 있지만 npm init으로 생성을 할 수 있다. 

NPM에서 라이브러리 버전은 x.x.x형태를 띄는데 각각 Magor, Minor, Patch를 의미한다. 그리고 각각은 아래와 같은 의미를 가진다.

* 기존 버전과 호환되지 않게 API가 변하면 Magor를 변경
* 기존 버전과 호환되면서 새로운 기능 추가시 Minor를 변경
* 기존 버전과 호환되면서 버그 수정시 Patch를 변경.

라이브러리 버전관리를 위해서 ~,^,* 등의 기호를 제공한다 그중에 ~와 ^가 이해가 안갈 수 있는데 이것은 아래와 같다.

* ^ : 0을 제외한 처음나타나는 값을 기준으로 하위버전 모두를 지칭한다. 즉 ^1.2.0이면 1.2.0 <= ver < 2.0.0을 의미한다 또한 ^0.3.0이면 0.3.0 <= ver < 0.4를 의미한다. 
* ~ : ~는 지정된 버전부터 상위 버전전까지의 모든 버전을 지칭한다. 즉 ~1.3.1이라면 1.3.1 <= version < 1.4을 의미하고 ~1.8로 지정하면 1.8 <= ver < 1.9를 의미한다.

테스트를 하고 싶으면 https://semver.npmjs.com를 참고해라 

자세히 알고 싶으면 https://docs.npmjs.com/misc/semver를 참고 해라.

# 2. TypeScript

MS에서 만든것으로 Javascript에 타입과 컴파일 기능을 넣었다고 생각하면 된다.

Node로 인해서 터진 Javascript 붐으로 ECMA에서는 ES6를 내놓고 MS에는 TypeScript를 내놓게 된다. ES6는 직접 공부하길 바라고 아무튼 MS에서 Ts의 정의를 보면 아래와 같이 적혀있다.

* TypeScript is a typed superset of javascript that compiles to plain Javascript.

TypeScript는 Javascript를 컴파일 할 수 있는 Superset 이다.

## 2.1 Why Type?

타입이 들어감으로 인해서 Javascript의 자동 형변환에 따른 오류등을 쉽게 찾고 수정을 할 수 있다. 간단한건 모르겟지만 대규모 시스템에서 이런 자동 타입 형변환으로 문제가 생길 가능성이 있다.

## 2.2 Compile

ts는 결국 Javascript 컴파일된다. 이 컴파일 시점에 내가 원하는 버전의 ES로 변환이 가능하다. Default로 ES3로 변환한다.

## 2.3 Use

타입스크립트 설치

npm install -g typescript

tsc 타입스크립트.ts --target es6

# 3. AngularJs

## 3.1 CLI

Angular는 CLI로 템플릿코드 자동 생성, 개발서버, 배포, 테스트 등 앵귤러 프로젝트에 도움이 되는 여러 기능을 제공한다. 설치는 아래 명령어를 통해서 한다. 시간이 좀 걸린다. 설치가 끝나면 ng명령어를 사용을 할 수 있다.

npm i @angular/cli -g

### 3.1.1 ng new

angular 4 프로젝트 생성 명령어

* e2e : protractor tool을 사용해서 블라우저를 띄워서 실제 어플리케이션을 통합테스트할 코드가 있다.
* src : 어플리케이션 소스
* .angular-cli.json : cli에서 사용할 설정 정보
* karma.conf.js : karma를 사용해서 단위테스트를 하는데 그 설정파일
* protractor.conf.js : e2e 폴더안의 통합테스트를 실행하기 위한 protractor 도구의 설정파일
* tslint.json : ts구문 체크용 설정 파일
* tsconfig.json : ts 컴파일 설정 파일
* src/typing.d.ts : ts에서 사용할 타입 선언 정보 파일

--prefix xxx 같은 옵션을 통해서 생성된 프로젝트안에서 컴포넌트를 생성시 selector에 자동으로 xxx접두어를 붙여주는 옵션, 왜 prefix를 넣냐면 다른 프로젝트의 컴포넌트나 지시자들과 이름이 충돌 날수 있기 때문에다.

### 3.1.2 ng serve

ng serve는 소스를 Webpack을 통해서 번들링하고 실행 시키는 명령어

IE9~11에서 Angular를 동작시키려면 src/polyfills.ts에서 아래 항목들을 주석을 해제해야 한다.

```javascript
/** IE9, IE10 and IE11 requires all of the following polyfills. **/
// import 'core-js/es6/symbol';
// import 'core-js/es6/object';
// ...
// import 'core-js/es6/set';
```

### 3.1.3 ng test

ng test는 karma와 jasmine을 사용해서 테스트를 한다. 명령어 입력시 자동으로 브라우저를 띄워서 확인 하게 해준다.

테스트 코드는 src/app.component.spec.ts에 작성 되어 있다.

### 3.2 타입 선언 정보

Javascript의 스팩에서 메소드들은 당연히 Type에 관한 정보를 가지고 있지 않다. 그래서 TypeScript는 TypeScript declaration file이라는 타입에 대한 정보를 갖는 파일을 내포하고 있다. 그래서 컴파일시점에 이 파일에서 메소드의 기능이 반환하는 타입이 무엇인지 추론을 하게 된다.

기본적인 Typescript declaration file은 아래 주소에 공개되어 있으니 확인해보길 바란다.

https://github.com/Microsoft/TypeScript/tree/master/lib

자 jquery나 lodash같은 외부 라이브러리같은 곳에서 사용하는 함수같은 경우 어떻게 해야할까? 만약 $로 시작하는 jquery selector를 사용할 경우 문제가 발생을 할 것이다. 실제 이런것에 대해서는 jquery가 별도 d.ts를 제공하거나 하는데 이런것을 받아야 한다. 이런것을 검색하고 받게 해주는 사이트는 다음과 같다.

http://microsoft.github.io/TypeSearch/

위에서 검색후 해당 패키지의 타입 정보가 있을 경우 npm install --save-dev @types/패키지명 으로 설치가 가능하다.

# 4. Angular4 Info

Angular4는 Front Framework이다. 즉 Angular4를 사용한다는 것은 Angular4에서 제공하는 방식으로 사용자와 상호작용할 뷰와 뷰와 함께 사용될 로직을 개발하는 것을 의미한다. 그러니 Angular4가 어떻게 어떤 방식들을 제공하는지 알아야 한다.

## 4.1 Component

앵귤러는 Component를 만들고 Component를 만들다가 끝난다고 이야기를 하곤 한다. 이정도로 Angular4에서는 Component가 중요하다.

Component의 선언은 @Component를 Class위에 선언 하므로써 결정이 된다.

```javascript
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
``` 
@Component안에는 selector, templateUrl, styleUrls같이 Component의 정보가 들어가는데 이것을 메타데이터라 한다. 이런 메타데이터는 컴포넌트와 연결된 View 템플릿, 스타일정보등등이 들어간다.

### 4.1.1 Component Life Cycle

Angular에서 컴포넌트들은 각자의 생명주기를 가지고 있다. 앵귤러는 이런 컴포넌트의 생명주기때마다 이벤트를 정의하여 인터페이스로 제공한다.

### 4.1.2 Component Tree

Angular에선 컴포넌트들은 트리형태로 부모 자식간의 트리형태의 구조를 지닌다.

CLI를 사용해서 새로운 컴포넌트를 생성하는 방법은 아래와 같다.

ng g component welcome-app (/src/app/welcome-app폴더를 생성하고 그안에 탬플릿, css, ts등을 포함하며 spec.ts로 끝나는 테스트 코드 까지 작성이 된다.)

### 4.1.3 데이터 바인딩

데이터 바인딩은 단방향, 양방향 2가지가 있다. 단방향 바인딩은 삽입식, 프로퍼티, 이벤트 바인딩 3가지로 구분된다.

양방향 바인딩은 FormsModule를 Import해야 사용이 가능하다.

```javascript
import { FormsModule } from '@angular/forms';
...
imports: [
    BrowserModule,
    FormsModule
  ],
```

한글 같은 조합형 문자는 글자 완성후 스페이스나 엔터를 통해서 문자를 입력완료한 시점에 compositionend라는 이벤트가 발생하고 양방향은 이 이벤트를 통해서 처리를 한다. 그렇기 때문에 글자 완성시까지 표시가 되지 않는다.

만약 글자를 입력하는 순간 바인딩 하고 싶으면 COMPOSITION_BUFFER_MODE설정을 아래와 같이 변경하면된다.

```javascript
import { FormsModule, COMPOSITION_BUFFER_MODE } from '@angular/forms';
...
providers: [{provide: COMPOSITION_BUFFER_MODE, useValue: false}],

```

## 4.2 Angualar를 아름답게 사용하기 위해 알아야 할것

서비스는 비지니스 로직을 분리하기 위해 존재한다. 그리고 컴포넌트는 뷰와 서비스의 비지니스 로직을 관리하는 컨트롤 타워 같은 역할로 사용해야 한다.

# 5. 뷰 기초

## 5.1 바인딩

### 5.1.1 단방향 바인딩

```javascript
{{}} <-- 템플릿 표현식
```

템플릿 표현식의 유효 컨텍스트는 컴포넌트이다 그리고 템플릿 표현식에는 복잡한 로직이나 선언문은 넣지 않는다.

### 5.1.2 프로퍼티 바인딩

```javascript
[Dom 프로퍼티] = "템플릿 표현식"
```

### 5.1.3 이벤트 바인딩

```javascript
(이벤트 대상) = "템플릿 문장"
``` 
 
이벤트 대상은 click, keyup같은 dom Event 이름을 의미한다.

### 5.1.4 양뱡향 바인딩

```javascript
[(ngModel)] = "data"
```

## 5.2 지시자

### 5.2.1 구조 지시자

NgIf, NgFor, NgSwitch등등의 Dom요소를 추가하거나 삭제하는 등 Dom트리를 동적으로 조작하는 지시자.

사용하다보면 더미태그들을 넣는데 실제 브라우저에서 더미 데이터를 사용하기 시르면 ng-container를 사용 할 수 있다.

```javascript
...
<ng-container *ngSwitchCase="'kkk'">kkk</ng-container>
...
```

### 5.2.2 속성 지시자

다수의 Class를 동적으로 추가하거나 할때 유용하다. ngClass, ngStyle이 존재한다.

```javascript
<div [ngClass]="myCls">테스트</div>
``` 

## 5.3 파이프(|)

파이프는 뷰에 노출할 데이터를 변환하거나 가공할때 사용한다.

### 5.3.1 파이프 커스텀

파이프는 내맘대로 만들 수 있다.

```javascript
@Pipe({ name: 'geekMark' })
export class GeekMarkerPipe implements PipeTransform {
  transform(value: string, type: string): string {
    switch (value) {
      case 'A':
        return `Good A : ${value} `;
      case 'B':
        return `Normal B : ${value} `;
      case 'C':
        return `Bad C : ${value} `;
    }
  }
}


// 사용예 파이프에 추가 인자를 넘겨주는 것은 :를 사용한다.
// {{name | geekMark:'A'}}
```

## 5.4 정리

* 뷰를 컴포넌트 단위로 설계하고 구현한다.
* 컴포넌트는 반드시 뷰를 지칭하는 template를 가지고 있다.
* @Component 데코레이터에 메타데이터를 선언하면 컴포넌트를 만들수 있다.
* 컴포넌트는 반드시 모듈에 등록해야 사용이 가능하다.
* 컴포넌트들이 모여서 컴포넌트 트리가 만들어진다.
* 템플릿은 표준 HTML과 앵귤러 고유의 문법으로 작성하고 렌더링된다.
* 여러 지시자들을 통해서 뷰와 컴포넌트 간의 관계를 만든다.
* 파이프를 통해서 원하는 데이터 형태로 데이터를 보여줄수 있다.
* 파이프나 지시자는 커스텀이 가능하다.

# 6. 멋지게 사용하기 위해서 알아야 할 것

좀더 복잡한 로직들을 이쁘게 처리하기 위해서 몇몇가지를 더 알아보자.

## 6.1 서비스

순수한 비지니스 로직이나 값을 다루는 클래스.

서비스는 필수로 붙이는 데코레이터가 없지만 Injectable이라는 데코레이터를 붙이는 것을 권장하고 있다.

## 6.2 의존성 주입

NgModule의 providers에 서비스를 선언하고 해당 서비스를 사용하는 컴포넌트의 생성자자의 파라메터에 선언을 해주면 앵귤러 의존성 주입기가 컴포넌트를 생성시 생성자의 인자로 provider에서 선언한 값을 주입해준다.

```javascript
@NgModule({
  ...
  providers: [MyDefaultLoggerService],
  ...
})

...
export class MouseTrakingAreaComponent implements OnInit {
  logLevel: LogLevel = LogLevel.INFO;

  constructor(private logger: MyDefaultLoggerService) { // 주입
  }
  ...
}
```

### 6.2.1 @Injectable, @Inject

의존성 주입기는 @injectable 여부로 인스턴스를 생성시 생성자로 인해 의존성을 주입해 줄 필요가 잇는지 체크한다. 

의존성 주입시 관련 파라메터들은 Class형태는 자동 추론하지만 열거형이나 기본형은 추론하지 못하니 @Inject를 설정한다.
 
### 6.2.2 providers

NgModule에 선언한 서비스가 어떤 인자를 받는지 설정할 수 있다. provide는 @Inject('logLevel')은 컴포넌트등에서 생성자로 선언한 파라메터 인자부분이다 이 파라메터의 값과 provide값이 매칭이 되면 해당 인자를 useValue값으로 넣는다.


```javascript
 providers: [MyDefaultLoggerService, {provide: 'logLevel', useValue: LogLevel.INFO}]
```

그러나 문자열로 키를 잡는것은 중복이 날수 있으니 위험하며 만약 중복시 마지막으로 설정한 값을 넣어준다. 그래서 보통 이런 문제를 해결하기 위해서 InjectionToken을 사용한다. 이것은 임의의 값을 생성한다.

자식 컴포넌트는 최상위 Root모듈인 app.module까지 providers에 선언된 서비스를 찾는다. 만약 상위 컴포넌트의 의존성 주입 정보를 사용하지 않을 경우 @Host와 @Optional을 사용할 수 있다.

@Host는 현재 컴포넌트의 의존성 주입 정보를 찾아서 넣으라는 것이고 @Optional은 현재 컴포넌트의 의존성 정보가 없을경우 의존성 주입을 받지 않아도 된다는 의미이다.

```javascript
...
constructor(
    @Host() myserviceX: MyServiceX,
    @Host() Optional() myserviceY: MyServiceY,
)
...
```

# 7. 컴포넌트 고급

Angular4에서 컴포넌트는 독립된 Style이나 스코프를 갖는다. 잘 생각해보자 결국 컴포넌트의 조합된 HTLM이 통으로 보여주는건데 어덯게 그렇게 되는것일까? 렌더링시에 Angular가 HTML 태그에 임의의 값을 넣고 그 값을 CSS 컴파일 시점에 넣어서 컴포넌트별로 각각의 CSS를 먹게 할 수 있는 것이다.

HTML표준에도 Web Compoent라는 표준이 잇는데 표준의 Shadow Dom을 통해서 비슷한 기능을 만들 수 있다. 하지만 이것은 이것을 지원해주는 브라우저 박에 사용을 못한다.

## 7.1 Web Component를 Angular컴포넌트에서 쓰기

Web Component를 사용하고 싶으면 @Component에 encapsulation으로 ViewEncapsulation.Native옵션을 주면 된다.

```javascript
@component({
...
encapsulation: ViewEncapsulation.Native
})
```

## 7.2 컴포넌트 공유와 이벤트 전파

### 7.2.1 @Input와 프로퍼티 바인딩

부모에서 자식에게 데이터를 줌, 자식에서 값을 변경해도 부모에 반영 안됨

```javascript
<child-area [cflag]="myflag" [dflag]="goodFlag"></child-area>
...
export Class ChildAreaComponent() {
    @Input() cflag;
    @Input() dflag;
    ...
}
```

### 7.2.2 @Output 사용한 이벤트 바인딩








