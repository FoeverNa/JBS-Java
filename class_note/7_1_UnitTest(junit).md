# UnitTest(Junit)

## UnitTest

### UnitTest란?

- 컴퓨터 프로그래밍에서 소스 코드의 특정 모듈이 의도 된 대로 정확히 작동하는지 검증하는 절차다. 즉, 모든 함수와 메소드에 대한 Test case를 작성하는 절차를 말한다
  - 이를 통해서 언제라도 코드 변경으로 인해 문제가 발생할 경우, 단시간 내에 이를 파악하고 바로 잡을 수 있도록 해준다.
  - 왜 메소드 단위인가? 
    - 메소드는 객체의 속성을 변화시키는 '부작용(Side-deffect)'을 가지고 있기 때문에 변화시키는 주체를 최소 단위로 하여 테스트하는 것이 적합하기 때문이다.
- 장점
  - 빠른 문제점 발견 : Unitest는 프로그램을 작은 단위로 쪼개서 각 단위가 정확게 동작하는지 검사하고 이를 통해 문제 발생 시 정확하게 어느 부분이 잘못되었는지를 빠르게 확인 할 수 있게 해준다
  - 변경이 쉽다: 코드를 변경하더라도 Unitest를 통해 해당 모듈이 의도대로 작동하는지 확인할 수 있기 때문에 언제든지 Refactoring을 할 수 있게 해주고  이를통해 더 좋은 코드를 만드는데 도움을 준다
  - 통합이 간단하다 : UnitTest를 통해 프로그램의 각부분을 검증한 후 부분들을 합쳐서 다시 검증하는 통합테스트를 진행하기가 편리해진다.
- 참고자료:
  - https://ko.wikipedia.org/wiki/%EC%9C%A0%EB%8B%9B_%ED%85%8C%EC%8A%A4%ED%8A%B8#:~:text=%EC%9C%A0%EB%8B%9B%20%ED%85%8C%EC%8A%A4%ED%8A%B8(unit%20test)%EB%8A%94,%EC%9E%91%EC%84%B1%ED%95%98%EB%8A%94%20%EC%A0%88%EC%B0%A8%EB%A5%BC%20%EB%A7%90%ED%95%9C%EB%8B%A4.

### 임시테스트 방법의 문제점

- 여러개의 테스트를 작성하기가 어렵다
- 여러가지를 테스트할 때 '부작용'이 발생하기가 쉽다
  - 실행 순서에 따라 결과가 달라진다.
- 테스트 결과가 성공적인지 파악하기가 어렵다.
- Production 코드와 Test 코드가 섞이게 된다.



### Junit 활용한 UnitTest

- 테스트 클래스 작성방법
  - Intellij를 통해 Project Structure - Project Setting - Module - Source를 통해 Test폴더를 만들고 Test폴더로 설정할 수 있다
  - 테스트 하고 싶은 클래스로 돌아와 Generate - Test 를 통해 Test 클래스를 생성하면 Test를 진행할 수 있게 된다
- 테스트 관련 어노테이션
  - @Test : 테스트를 위한 메소드임을 표기해주는 어노테이션이다. assert를 통해 테스트 결과를 출력할 수 있다
  - @Before, @After: 각 테스트 메소드 실행전이나 후에 실행되는 메소드임을 알리는 어노테이션이다
  - @BeforClass, @AfterClass: 테스트 시작과 끝에 한번 실행되는 메소드 이다.
  - @Ignore: 테스트 메서드 해당 어노테이션을 달면 해당 테스트의 결과가 무시된다
    - Ignore 하면 잊어버리기 쉽기 때문에 해당 Test를 진행 안하고 넘어갈 가능성을 주의해야 한다.
      - Test결과에 Igonore된 갯수가 표시 되긴 한다
- 실행 순서 예시 : BeforeClass -> Before -> Test1 -> After -> Before -> Test2 -> After -> AfterClass

#### UnitTest를 위한 AAA 패턴

​	AAA 패턴은 Test를 Arrange, Act, Assert 3가지 단계로 접근하는 패턴이다. 테스트를 명확히 구분하기 위해 주석으로 표시하여 사용하기도 한다

- Arrange(준비): 테스트를 준비하기 위해 시스템이 적절한 상태에 있는지 확인, 객체 생성, 객체와의 소통, API호출 등을한다
- Act(실행): 실제로 테스트 코드를 실행한다
- Assert (단언): 실행한 코드의 결과를 기대하는 갑과 비교한다
- 참고자료 : https://medium.com/@pjbgf/title-testing-code-ocd-and-the-aaa-pattern-df453975ab80

#### asserThat()메서드

assertThat 메서드는 hamcrest 라이브러리에서 제공하는 static 메서드로 가독성이 좋고 자세한 Failure 메시지를 제공한다

- 사용방법
  - assertThat(실제값, is(equaltTo(기대값))) 으로 사용할 수 있다
  - is 대신 not을 넣어  반대의 결과를 Assertion할 수 도 있다
    - 혹은 is(not(equalTo(기대값)))으로 가독성 좋게 표현하기도 한다
- 참고 자료 :  https://jongmin92.github.io/2020/03/31/Java/use-assertthat/

#### Exception Test 3가지 방법

- 어노테이션 활용

  ```java
  @Test(expected=ArithmeticException.class)
  public void checkExceptionByAnnotation() {
      account.throwExcept();
  }
  ```

  - 간단하다는 장점이 있지만 테스트 메소드 내부에 assert가 드러나지 않는다

- try~catch

  ```java
  @Test
  public void checkExceptionByTryCatch() {
      try {
          account.throwExcept();
          fail();
      } catch (ArithmeticException e) {
          assertThat(e.getClass(),
                  equalTo(ArithmeticException.class));
      }
  }
  ```

  - 인지적으로 더 개선되나, 코드가 매우 복잡하다는 단점이 있다

- @Rule활용

  ```java
  @Rule
  public ExpectedException thrown = ExpectedException.none();
  
  @Test
  public void checkExceptionByRule() {
      thrown.expect(ArithmeticException.class);
      account.throwExcept();
  }
  ```
  - Rule을 이용하면 메소드 코드에 expected exception이 드러나므로 인지적으로 개선되고 코드도 간편하다는 장점이 있다
    - 위 예제에 사용된 Rule과 ExpectedException은 나중에 더 살펴보면 좋을 것 같다
  - 참고자료:
    - https://junit.org/junit4/javadoc/4.12/org/junit/rules/ExpectedException.html
    - https://junit.org/junit4/javadoc/4.12/org/junit/Rule.html



### 좋은 테스트의 조건 - FIRST

- Fast(빠른) 

  - 전형적인 자바 시스템의 테스트 케이스는 수천 단위이다.

  - 예를 들면 Unitetst 하나에 평균 200ms가 걸리는 경우, 5000개를 수행하는 데에 16분 소요된다.

    - 우리가 코드를 1번 수정할 때 마다 16분의 테스트를 수행해야 한다는 뜻으로 심각한 비효율성을 초래한다

    - 메소드에 테스트용 우회 코드를 넣거나, Stub 객체를 활용 하여 이런 문제 해결한다

- Isolated(고립된)

  - 테스트는 다른 테스트와 분리되어 있어서 순서와 시간에 영향을 받지 않아야 한다
  - 고립되지 않은 테스트는 실패했을 때 원인을 찾기 어렵다. 이는 unitTest하는 의미를 퇴색 시킨다.
  - 이는 AAA패턴을 활용함으로 도움을 받을 수 있다

- Repeatable(반복 가능한)

  - 테스트를 반복하면 결과가 같아야 한다
  - 테스트 코드 자체만으로 그 내용을 설명할 수 있어야 한다.
    - 외부 환경에 영향을 받지 않아야 한다.
    - (이 부분은 Isolated와 Repetable이 어떻게 다른지 잘이해를 할수 없었다)

- Self-Validating(스스로 검증 가능한)

  - 테스트는 반드시 기대하는 바를 단언해야 한다.(assert를 활용한다)
  - 테스트는 스스로 검증하며, 테스트를 준비하는 것도 스스로 한다.
  - Continous Integration (CI) 도구를 활용하기 위해 이것이 꼭 이루어져야 한다
    - 코드가 통합 될때마다 자동으로 모든 테스트를 수행하고, 상태를 점검하는 프레임워크
    - (CI에 대해 더이해해보고 싶어서 자료를 찾아봤지만 현재 수준에서는 이해하기 어려워서 참고자료만 남겨놓는다)

- Timely(적시의, 제때)

  - 변화하는 코드는 테스트 코드를 향상 꾸준히 작성해야 한다.
    - 큰 결함 없이 기존의 잘 동작하는(변경 예정이 없는) 코드 보다는, 말썽을 일으키고, 계속해서 변하고 있는 동적인 코드에 대한 테스트를 먼저 작성한다
  - 리뷰 시스템을 통해서, 혹은 CI 도구를 이용해서 테스트 코드 작성을 강제하기도 한다 (CI도구에 대한 내용은)
    - 리뷰 시스템은 동료들의 Peer Prssure를 이용하는 방식이다.
    - Build자체를 안되게 하는 방식으로도 사용한다.

- 참고자료

  - https://medium.com/@tasdikrahman/f-i-r-s-t-principles-of-testing-1a497acda8d6
  - CI : http://www.nextree.co.kr/p10799/

### TDD  테스트 주도 개발 (Test-Driven DEvelopment)

- '실패하는' 테스트 케이스를 먼저 작성하고, 코드구현을 통해 해당 테스트를 통과시키는 것을 하나의 짧은 cycle로 구성하여 그것을 여러번 반복하여 프로그래밍을 해나가는 방식
  - 도메인의 요구사항을 구체적인 테스트로 케이스로 작성하고 그것을 통과시켜나감으로 요구사항을 충족시켜나가는 방식이다
  - 기존의 개발 프로세스가 설계 -> 개발 -> 테스트 였다면 설계 -> 테스트 -> 개발로  변화하게 된 것이다.
- TDD 개발 과정 
  - 테스트 케이스 작성(테스트 실패) -> 코드 구현 -> (커밋) -> (리팩토링) -> 테스트 케이스 작성.. 을 빠르게 반복한다
    - 1~2분 간격으로 매우 빠른 호흡으로 진행한다
    - 전체적인 구조는 생각하지 않고 진행하기 때문에 중간중간 커밋과 리팩토링을 진행한다
- TDD장점
  - 객체 지향적인 코드개발 
    - 테스트의 용이성을 위해 복잡한 기능을 한 메서드에 모두 구현하는 것이 아닌 각각의 기능들에 대해서 철저히 구조화 시켜 코드를 작성하게 되기 때문에 객체 지향적인 코드가 된다
  - 설계 수정 시간의 단축
    - 테스트 코드는 최초 설계안에 구조와 기능의 정의를 명확하게 하므로 설계의 구조적 문제를 찾아내는데 도움을 주기 때문에 설계 수정 시간을 단축시키는 효과가 있다
      - 결함은 일찍 찾을 수록 고치는 비용이 적게들어 간다
  - 디버깅 시간의 단축
    - 기본적으로 단위 테스트 기반의 테스트 코드를 작성하기 때문에 문제 발생 시 모듈별로 테스트를 진행해보면 문제의 지점을 쉽게 찾아낼 수 있다.
- 참고자료:
  - https://en.wikipedia.org/wiki/Test-driven_development
  - https://m.blog.naver.com/PostView.nhn?blogId=suresofttech&logNo=221569611618&proxyReferer=https:%2F%2Fwww.google.com%2F