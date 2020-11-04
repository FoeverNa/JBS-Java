# 내부 클래스 (Inner Classes)



## 내부 클래스란

- 클래스 내부에 선언하는 클래스로 중첩 클래스(Nested Class)라고도 부름

- `HAS-A` 관계에 있는 클래스가 해당 클래스에서만 사용될 경우 주로 사용

  - 한 클래스가 특정 클래스에서만 Compositon되서 사용된다면 해당 클래스내에서 클래스를 선언해서 사용하는게 Encapsulation관점에서 유리하다
  - 알고리즘 베스트앨범예제에서 Map.Entry가 내부클래스의 예이다. 

  

## 내부 클래스의 종류

- 클래스 영역에 선언(멤버 내부 클래스?)
  - 인스턴스 내부 클래스: 외부 클래스의 멤버 변수처럼 사용 가능
  - 클래스 내부 클래스: static이 붙은 정적인 내부 클래스
- 로컬 영역에 선언(지역 내부 클래스?)
  - 로컬 내부 클래스: 선언된 영역의 Scope 내에서 사용 가능
  
  - 익명 내부 클래스: 객체를 일회성으로 생성하기 위한 이름이 없는 클래스
  
    

## 다양한 내부 클래스

### 인스턴스 내부 클래스 (Instance Inner Class)

- 클래스 영역에 `static` 키워드 없이 선언된 클래스

- 외부 클래스 객체를 통해서만 생성 가능

  - 특이하게 외부클래스명.new 키워드를 사용해서 객체를 생성한다	

- 외부 클래스의 private을 포함한 모든 멤버에 접근 가능

  - 인스턴스 내부클래스의  경우 객체가 외부클래스의 객체를 통해 생성됨으로 내부의 객체가 생성된 시점에 외부클래스의 객체가 항상 존재함으로 외부클래스의 멤버변수에도 접근이 가능하다

- static 멤버 변수는 사용할 수 없으나, `static final`은 사용 가능

  - 내부클래스의 메서드는 외부 클래스에 객체가 있는 경우에만 호출될 수 있는 메서드이기 때문에 스태틱 변수의 정의가 어려워 진다
    - 외부 클래스의 객체마다 다른 스태틱변수를 가져가야하나 아니면 동일한 클래스의 객체라면 같은 스태틱멤버 가저가야되나하는 문제가 발생 할 수 있다
    - 그래서 애매하니까 그냥 허용하지 않기로하고 문법적으로 막아놓게 된다
  - 그럼 상수는 어떻게 가능것인가 하면 상수는 상수풀에 저장이되는 고정값으로 여러 객체에서 접근을 해서 사용을 해도 위와 같은 문제가 발생 하지 않는다
    - 그래서 상수는 사용할수 있게 허용이됬다

  ```java
  class Outer { // 외부클래스 선언
      class InstacneInner{ // 인스턴스 내부클래스 선언
          int innerMemberVar = 1;
          // static int statiVAr =20;// 스태틱변수가 허용되지 않음
          static final int CONSTANT_VAR = 1000; // 상수는 사용할 수 있음
  
          void innerMethod(){
              System.out.println(innerMemberVar);
              System.out.println(outerMemberVar);// outer private 멤버변수도 접근이가능
                                               // 내부 클래스의 객체가 존재하는 시점에 이미 외부클래스객체가 존재하기때문에 가능
         }
      }
      private int outerMemberVar = 2;
  
      void outerMethod(){// 외부클래스에 속하는 멤버메서드
          InstacneInner obj = new InstacneInner(); // 내부클래스 객체생성가능
          obj.innerMethod(); // 해당 객체로 내부클래스 메서드에 접근도 가능
      }
  
      public static void main(String[] args) {
          // main과 같은 정적 메서드의 경우는 인스턴스 내부클래스의 바로접근할수 없다 => 외부클래스의 객체로만 접근이 가능하기 때문
          // new InstacneInner(); //Error
          
          Outer outer = new Outer();
          InstacneInner inner = outer.new InstacneInner();// 어색하지만 이렇게 쓰는거래// 외워주기
          inner.innerMethod(); // 그다음에 호출할수 있게 된다
      }
  }
  
  ```

  

- 외부 클래스의 멤버 변수와 이름이 겹치는 경우 `this` 활용한다

  - this.는 본인의 객체를 가르킨다
  - Outer(외부클래스명).this는 외부클래스의 객체를 뜻한다
  - 그냥 Outer(외부클래스명)으로는 외부클래스의 정적 변수에 접근할 수 있다
  
  ```java
  class Outer{
  
      class InstanceInner{
          int x = 1;
          void innerMethod(){
              int x =0;
              System.out.println(x); //0  // 로컬 변수
              System.out.println(this.x); //1 //this는 내부 클래스를 가르킴
              System.out.println(Outer.this.x); //2 // 클래스명으로 접근을함 클래스명.this가 외부클래스의 객체를 뜻함
              System.out.println(Outer.y); //Outer.y로 접근하려면 static변수여야함(클래스명으로 접근하는건 정적변수)
          }
      }
      
      private int x = 2;
      private static int y =3;
  
      public static void main(String[] args) {
          Outer outer = new Outer();
          InstanceInner inner = outer.new InstanceInner();
  
          inner.innerMethod();
      }
  }
  ```



### 클래스 내부 클래스 (Class Inner Class)

- 클래스 영역에 `static` 키워드와 함께 선언된 내부 클래스

- 외부 클래스 객체가 없어도 생성 가능하다

  - 스태틱 클래스도 클래스이기 때문에 객체가 생성이 된다
  - 내부 클래스 객체 생성시 외부 클래스는 생성이 되지 않는다.. == 서로 독립적인 클래스이다

- 외부 클래스의 private을 포함한 모든 멤버에 접근 가능

  - 내부클래스의 내부에서 외부클래스의 객체를 생성해서 그 객체를 통해 멤버변수에 접근이가능하다(메인메서드 같네)
    - 이는 내부클래스의 생성시점(외부클래스의 생성시점에 함께생성 된다)에 외부클래스의 객체가 없기 때문에 생긴 특이한 접근방식이다

- static 멤버 변수를 가질 수있다

  - 클래스가 static클래스이기 때문에 가능하다
  
- 클래스에 접근은 바로 내부클래스명으로 할 수 있고 스태틱메서드, 변수는 바로접근이 가능하고, 멤버 변수, 메서드는 객체를 생성해야 접근이 가능하다

- 정리하면 내부클래스이지만 독립된 클래스처럼 접근할 수 있고 외부클래스에도 객체를 생성하면 prviate까지 접근할 수 있는 클래스이다

  ```java
  class Outer{
      static{
          System.out.println("Outer static");
      }
  
      static class ClassInner{
          static {
              System.out.println("Inner static");}
  
          int innerVar = 1;
          static int staticInnerVar =100;
  
          void innerMethod(){
              Outer outer = new Outer();
              System.out.println(outer.outerVar);  // 객체를 생성한 후에 통해 접근가능. 내부클래스 생성시점에 외부클래스의 객체가 없음
                                                 //내부클래스에서 외부클래스의 객체를 생성해야만 멤버변수에 접근할수있다.
              System.out.println(innerVar); //당연가능
          }
  
          static void staticInnerMethod(){
              System.out.println(staticInnerVar);
          }
      }
      private int outerVar = 2;
  
      public static void main(String[] args) {
  
          ClassInner.staticInnerMethod();// 스태틱메서드는 외부클래스와 상관없이 바로 접근이 가능하다
          //Outer static
          //Inner static
          //100
      }
  }
  ```



### 로컬 내부 클래스 (Local Inner Class)

- 클래스 영역이 아닌 로컬 영역에서 선언된 클래스

  - 메소드, 생성자, 초기화 블록의 내부에 선언된 클래스이다
  - 생명주기가 해당 블록내로 한정이 되서 아래의 예와 같이 같은 이름의 클래스를 여러번 선언할 수 있다 
    - 실제로는 같은이름으로 생성되는게 아니라 번호를 매겨서 다른이름의 클래스로 생성된다

- 정적 변수는 가질 수 없으나, static final 변수(상수)는 가질 수 있음

  - 클래스 영역에 선언되지만 생명주기가 로컬 블럭과 함께 가기 때문에 static 변수나 메서드는 가질 수 없다

    

- 로컬 영역의 변수 다음과 같은 사항을 따름

  - Java1.7: final로 선언된 변수에만 접근 가능
  - Java1.8: 모든 변수에 접근 가능하나, final로 간주되어 새로운 값 할당 불가 => 상수가 아닌것처럼 보이지만 사실은 상수같은 역할
  - 로컬 영역에 있는 변수는 생명주기와 로컬블럭과 함께 끝나는데 힙영역에 생성된 로컬내부클래스의 생명주기는 더 길 수 있다
    - 그래서 시점상 이미지 사라진 로컬변수에 접근하는 상황이 발생할 수 있는 가능성이 있기 때문에 로컬 내부 클래스에서 참조하는 로컬변수는 상수나 상수와 같이 변하지 않는 경우에만 가능하다
  
  ```java
  class LocalInnerClass{
  
      int x = 1;
  
      public Object method(){
          final int y1 = 2;
          int y2 = 2;
          class LocalInner{
              // static final int z = 4; // static 일수가 없음 => 클래스영역에 생겨야함
              static final int z = 4; // Possible
              void methodA(){
                  System.out.println(x);
                  System.out.println(y1); //JDK1.7
                  System.out.println(y2); //JDK1.8 //final아니라도 접근할수있게해줌
  //                y2 ++; // 왜안되냐면 fianl로 취급을 함.. 접근만하고 수정은 불가능
              }
          }
  
          LocalInner inner = new LocalInner(); // Heap area
          inner.methodA();
  
          return (Object)inner;
      }
  
      static{
          int y = 2;
          class LocalInner{
               void methodA(){
                  System.out.println("a"); // x에접근 안됨
              }
          }
      }
  
      {
          int y = 2;
          class LocalInner{
             void methodA(){
                  System.out.println(x);
              }
          }
      }
  
      public static void main(String[] args) {
          int y = 2;
          class LocalInner{
              void methodA(){
                  System.out.println("ㅁ");
              }
          }
  
      }
  }
  ```



### 익명의 내부 클래스 (Anonymous Inner Class)

- 로컬 내부 클래스와 동일하나, 이름이 없어 선언하여 즉시 한 번 사용 가능하다

  - 객체를 생성해서 사용하는 곳에서 new키워드를 사용해 객체를 생성하고 바로 중괄호{}를 열어 클래스 이름없이 클래스내용만 구현하여 사용한다.
    - 구현이 끝난 이후 세미콜론;을 이용하여 구현이 끝났음을 알린다
    - 생성되는 객체는 인터페이스나 추상클래스가 사용될 수 있다
  - 예제에서 1 => 2 => 3 순으로 일반적인 클래스와 메서드 구현이 생략되어져 가는 과정을 보여준다
    - Foo => InnerClass = >  익명클래스로 생략되어져 가는 과정을 이해해보자

- 추상 클래스나 인터페이스의 구현에 많이 사용 된다

  - 인터페이스를 구현한 클래스의 객체를 사용한는 것이 아닌 인터페이스를 new해서 사용하면 해당 인터페이스를 구현한 익명의 1회용 객체를 생성해준다
  - 인터페이스나 추상클래스에 추상메서드를 구현해주게 되면 익명의 클래스를 사용한 것이 된다
  
  ```java
  interface IFoo{
      void run();
      void walk();
  }
  class Foo implements IFoo{
      @Override
      public void run() {
          System.out.println("Normally run");
      }
  
      @Override
      public void walk() {
          System.out.println("Normally Walk");
      }
  }
  
  class AnonymousInnerClass{
     static void useIFoo(IFoo foo){ // IFoo를 구현한 객체를 입력받는다
  
          foo.run();
          foo.walk();
      }
  
      public static void main(String[] args) {
         //1. AnnoymousInnerClass를 만들어서 Ifoo을 구현한 Foo의 객체를 넣어서 바로 실행한 경우
         // 기존의 사용하던 방식 그대로 사용한 예
          Foo foo = new Foo();
          useIFoo(foo); // Using Polymorphism
          
          // 2. Ifoo를 구현한 InnerClass를 만들어서 AnnoymouInnerClass에 바로넣어 실행한 경우
          class InnerClass implements IFoo{// 로컬 내부 클래스가 외부의 클래스를 상속받거나 인터페이스를 구현할 수 있다
              						  // 이부분까지는 로컬 내부클래스와 크게 다르지 않음
  
              @Override
              public void run() {
                  System.out.println("Run, Foo Run!");
              }
  
              @Override
              public void walk() {
                  System.out.println("Walk... Foo.. Walk...");
  
              }
          }
  
          InnerClass ic = new InnerClass();
          useIFoo(ic); // 내부클래스지만 IFoo를 구현한 클래스를 넣어줌
  
          
          // 3. 인터페이스를 바로 넣어서 인터페이스를 구현하는 익명의 1회용 클래스를 생성하여 바로 구현해준 경우
          // 이게 익명 클래스를 이용한 경우이다
          useIFoo(new IFoo() {// 인터페이스는 객체생성안되서 new못하는데 갑자기 된다?
                                  // Ifoo를 impelments 한 클래스를 구현하는것임
              @Override
              public void run() {
                  System.out.println("Run!!!");
  
              }
  
              @Override
              public void walk() {
                  System.out.println("walk...");
  
              }
          })  ; //
          // 곧바로 클래스를 작성해서 객체를 생성해서 사용하고 끝나는것..Wow신세계
      }
  }
  ```



### 람다식 맛보기

- 다음 챕터에서 배울 람다식은 익명클래스에서 더 생략된 형태를 보여준다
  - 외부클래스 => 내부클래스 => 익명클래스 => 람다식 순으로 생략되어 가는 형태를 보여준다
- 람다식에서 구현하는 인터페이스의 추상 메서드는 하나만 존재해야 한다
  - 따로 메서드 이름을 구분할 수 가 없기 때문에 해당 인터페이스의 존재하는 단하나의 메서드만 구현한다

```java
interface IBar{
    void run(int x, int y);
}

class Bar implements IBar{
    @Override
    public void run(int x, int y){
        System.out.println("Bar");
    }
}

public class Main{
    static void runIBar(IBar bar){
        bar.run(0,0);
    }

    public static void main(String[] args) {
        runIBar(new Bar()); //외부클래스

        class InnerBar implements IBar{

            @Override
            public void run(int x, int y) {
                System.out.println("innerBar");
            }
        }
        runIBar(new InnerBar()); // 로컬 내부클래스

        runIBar(new IBar() { // 익명 내부 클래스
            @Override
            public void run(int x, int y) {
                System.out.println("Annoymous");
            }
        });

        //Thisi is Lambda!! Exprssion
        runIBar((x,y)-> {
            System.out.println("LAMBDA!!!");
        });// 익명클래스를 한번더 줄여서 쓴게 Lambda expresion
            // 얘가 run인걸 어떻게 알아? 구현하는 인터페이스에 메서드가 하나야됨
    }
}
```

