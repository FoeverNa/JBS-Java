# 인터페이스 (Interface)

- 클래스가 사용되는 방식 / 접점만을 선언하는 클래스와 유사한 틀
  
    - 클래스 사용의 권고사항을 제시하는 역할을 하는 특수형태의 클래스
    
- 아무런 구현이 되어 있지 않으며, 모든 메소드가 추상 메소드 이다

- be able to ~ , ~할수있는에 해당하는 기능을 부여하며  ~able로 네이밍하는경우가 많다. 

    

## 인터페이스의 특징

- class 가 아니고 interace 키워드를 사용한다
    - 파일도 class가 아닌 interface로 생성 가능 하다
    - public과 default 접근제어자 사용가능하다
      
        - 클래스와 비슷한 속성이네
        
          
    
- 멤버 변수는 항상 "public static final"이다.
    - 인터페이에는 클래스 멤버 변수밖에 생성하지 못한다

    - 인터페이스는 객체를 생성하지 않기 때문에 객체 멤버 변수가 없다
      
        - 객체를 생성하지 않기 때문에 생성자도 없다
        
    - "public static final" 키워드는 생략 가능 하다

        

- 멤버 메소드는 항상 "public abstract"이다.
    - 정의상으로 모든 메서드가 추상메서드인 클래스이기 때문에 어찌며보면 당연한 이야기
    
    - 오버라이딩을 위한 메서드이기 때문에 private 메서드는 의미가 없다
    
    - "public abstract"는 생략 가능하다
    
      
    
- 정적 메소드도 선언이 가능하다
    - 정적 메소드는 추상메서드가 될 수 없다 (static abstract X)
      
        - 정적 메소드는 구현부가 있어야 한다    
        
    - 정적 메소드는 오버라이딩 할 수 없다
    
        
    
- 인터페이스의 상속은 implements 키워드 사용한다

    

- 클래스는 하나만 상속할 수 있으나, 인터페이스는 여러개를 구현 가능하다
    - extends 하고 하나의 클래스만 적을 수 있지만, 인터페이스는 implements 하고 여러개 클래스를 적을 수 있음
      
        - 이때 구현한 인터페이스들의 메서드들을 구현 받은 클래스에서 모두 구현하여야 한다.
    - 이를 통해 다중 상속을 한 것 처럼 사용할 수 있다.
      
        - TVCR예제 - 이렇게만 적어놓으니까 기억하나도 안난다 ^^
        
          
    
- 인터페이스명을 자료형으로 하여 구현체의 객체를 참조할 수 있다
    - 상속관계의 경우와는 다르게 인터페이스의 객체는 생성되지 않는다
    
    - 그러나 메서드는 오버라이딩 하기 때문에 가상 메서드 호출은 일어난다.
    
    - 이를 통하여 다형성을 구현할 수 있다?
    
      
    
- 추상메서드가 없다면 구현체에서 메서드 구현을 안해도 된다.

    - 당연한 말이니까 지울까?

    

- 인터페이스는 객체를 생성할 수 없다.


```java

interface IFoo{
    public static final int MEMBER_VAR = 10; // (상수는 대문자와 '_'조합)
                                            // public static final 생략 가능

    public abstract void methodA(int param); // public abstract 생략가능
    void methodB(int param); // public abstract
}

class Foo implements IFoo{

    @Override
    public void methodA(int param) {
        System.out.println(param);
    }

    @Override
    public void methodB(int param) {
        System.out.println(param);
    }
}

```
- 인터페이스의 이름의 예시

    - (1)interface Ifoo < class Foo
      
        - 상속하는 자식에서 부모쪽으로 화살표 한다
        
    - (2)interface Printable <- class Bar
    
    - (1)과 같이 인터페이스명에 대문자 I를 붙여서 구분한다
        - 혹은 (2)와 같이 형용사로 표현하기 좋은 이름은 그냥 제목으로 표현한다
          
          

## 인터페이스 간의 상속

- 인터페이스로 인터페이스를 상속할 경우, extends를 사용한다
    - 인터페이스를 구현 할때만 implements를 사용한다
    - 클래스 <- 클래스 상속과 달리 다중 상속 가능하다
      
        - 인터페이스를 extends 키워드로 여러개 상속 가능하다
          - 메서드의 구현이 안되어 있기 때문에 어떤 메서드를 사용할지 문제될게 없다
        
    
- 인터페이스의 상속으로 다형성을 구현할 수 있다.
  
    - 테란 Repable 예제
    
- 인터페이스를 상속한 인터페이스를 구현할 때는 상속받은 모든 부모 인터페이스의 메서드를 구현해야한다
  
```java
  
interface Walkable{
    void walk();
}

interface Runnable{
    void run();
}

interface Jumpable extends Walkable, Runnable{
    void jump();// walk(), run()도 포함되어 있을 것
}

class Jumper implements Jumpable{

    @Override
    public void walk() {
        System.out.println("walk");
    }

    @Override
    public void run() {
        System.out.println("run");
    }

    @Override
    public void jump() {
        System.out.println("jump");
    }
}
```

- Jumpable()에는 자신의 메서드인 jump(); 뿐아니라 상속받은 walk()와 run()도 포함되어 있다
  
    - 그래서 jupable을 구현한 Jumper 클래스에서는 모든 메서드를 다 구현해야 한다.
    - 이때 서로 상속받은 인터페이스안에 같은 메서드명의 다른 메서드가 존재할수있다. 이때도 같은 이름의 메서드를 모두 구현해야한다
      - ex) Colletion의 add와 Colletion을 상속받은 List에 add를 모두 구현한 ArrayList(같은이름의 다른 add메서드 2개가 존재한다)
- 이기능으로 인터페이스의 다형성 구현할 수 있을 것 같다
    - 만약 구현체에서 3개의 인터페이스를 따로 implements했다면 각각의 인터페이스로 자녀객체 생성해봤자 각자의 영역만 사용가능하다
    
    - 하지만 이렇게 하나의 인터페이스로 모두 상속받아 대표 인터페이스를 구현한다면 대표 인터페이스로 다형성을 구현할 수 있겠다!!유레카!
    
      

## JDK 1.8 이후의 인터페이스

- JDK 1.8 이후 인터페이스에서는 default 메소드를 구현할 수 있다

- default 메서드는 인터페이스를 구현하는 모든 하위 클래스에서 필수로 구현하는 메서드로 메서드 중복 구현을 방지하는 역할을 한다.
    - 인터페이스의 철학(정의)와는 맞지 않으나, 모든 자식 메서드가 동일하게 구현되어야 하는 메소드가 생긴 경우 쉽게 기능을 추가하기위해 만들어졌다

- default 메서드 특징
    - default 메소드는 항상 public이다.
        - default는 접근제어자의 default가 아니고 인터페이스의 default 메서드를 나타낸다
        - 인터페이스를 구현하는 모든 클래스에서 사용 될 메서드이기 때문에 private은 의미가 없다
    - default 메소드는 구현체에서는 반드시 구현할 필요는 없다.
        - abstract 메서드의 경우 구현체에서 반드시 구현하여야 하지만 default 메서드의 경우 구현하지 않아도 된다
        - 자녀 클래스에서 구현하지 않은 default 메서드에 접근시 부모클래스의 default 메서드에 접근된다



```java
interface IFooTwo {
    void abstractMethod();
    default void defaultMethod(){ // 디폴트 메소드 //오버라이드해도되고 안해도됨
        System.out.println("Default method"); // 구현을 해주어야함
    }
}

class FooTwo implements IFooTwo{

    @Override
    public void abstractMethod() {

    }
    @Override // Overriding not necessary
    public void defaultMethod(){
        System.out.println("Overriden default method");
    }
}


class SuperFoo{
   public void defaultMethod(){
        System.out.println("Default method in Super class");
    }
}

class FooThree extends SuperFoo implements IFooTwo{
    @Override
    public void abstractMethod() {
        System.out.println("abstract method implemnets");
    }
}


// 인터페이스의 static 메소드
interface IBar {
    static void staticMethod(){
        System.out.println("static method"); //바로 구현 가능
    }
}

class Bar implements IBar{

}


public class Interface {
    public static void main(String[] args) {
        FooTwo footwo = new FooTwo();
        footwo.abstractMethod();
        footwo.defaultMethod();// "Overriden default method"

        FooThree foothree = new FooThree();
        foothree.abstractMethod();
        foothree.defaultMethod(); //Default method in Super class
       
       IBar.staticMethod(); // 인터페이스명으로 클래스 메서드 호출 가능
    // Bar.staticMethod(); // 구현체인 자식 클래스로는 클래스 메소드 호출 불가능 //암기필요
       System.out.println(IBar.x); // 인터페이스명으로 인터페이스 정적변수 접근가능
       System.out.println(Bar.x); // 구현체 클래스로 인터페이스 클래스 정적변수 접근 가능
      
       Bar bar = new Bar();
       System.out.println(bar.x); // 접근가능 
    // bar.staticMethod(); // 접근불가

    }
}
```

- 상속받은 클래스와 구현한 인터페이스의 모두 default method가 있다면 상속받은 부모클래스의 default method가 실행된다
 - 이때 인터페이스의 default method는 실행되지 않느다
 -  = 부모와 인터페이스에 모두 메서드가 있는 경우 부모클래스에 있는 메소드를 실행한다.
- 이유
  - 클래스는 다중상속이 안되는데 인터페이스는 다중상속이 가능하다. 
  - 인터페이스에서 다중상속이 가능한 이유는 여러 곳에서 메소드를 가져와서 겹쳐도 구현이 안되어 있기 때문에 문제 발생하지 않는다
  - 그런데 default 메서드가 생기면서 다중 상속에 문제가 발생해버렸다.
  - default 메서드의 구현부가 상속받은 다른 메서드와 충돌할 수 있게 되어버린기 때문이다.
  - 이러한 다중상속시 문제를 방지하기 위해 인터페이스 보다 일반 상속이 우선시 한다
  - 일반 상속은 다중 상속이 안되니까 일반 상속을 우선시해도 후에 문제발생할 것이 없다.
  



## 참고

 - 인터페이스의 정적 메서드가 있는경우
    - 인터페이스 명으로 호출 가능 ex ) IBar.staticMethod();
    - 구현한 클래스명으로 호출 불가능 ex) // Bar.staticMethod();
        - 일반 상속에서는 가능하지만 인터페이스에서는 불가능 하다 => 이런건 그냥 외우는거래 원리가 아닌
    - 구현체의 객체로 접근 불가능 
        - 일반 상속에서는 가능하지만 인터페이스에서는 불가능 하다
    
 - 인터페이스 정적 변수가 있는경우
    - 인터페이스 명으로 호출가능
    - 구현체의 클래스명으로 호출 가능 // 이게 정상인데 왜 정적메서드 호출은안될까?
    - 구현체의 객체로 접근 가능 // 이게 정상이지
    
  - 인터페이스 정적메서드와 구현체의 정적메서드가 같은 경우 각각 클래스명으로 호출할 수 있다
    
    

### 인터페이스로 구현체의 객체를 생성했을 경우

 - 인터페이스의 메서드와 멤버변수에만 접근할 수 있는데, 인터페이스에는 멤버 변수가 없으니 메서드에만 접근할 수 있겠다

    - 근데 인터페이스의 메서드는 모두 추상메서드니까 무조건 구현체의 오버라이드된 메서드만 사용이가능하겠다

    - 그렇기 때문에 무조건 가상 메서드 호출이 일어나는 경우 일수도 있겟네?

      

### 인터페이스와 추상메서드의 차이점

- 추상메서드는 멤버 변수가 있기 때문에 자녀 클래스들에 공통 속성을 설정할 수 있다. 또한 일반메서드와 추상메서드로 공통의 기능도 구현할 수 있다. 그래서 추상메서드는 어떤 개념에 상위 개념으로 세분화 되는 하위 클래스들을 대표하는 클래스로 사용할 수 있다
  - 대표적인 예로 Animal 이란 큰 대분류안에 많은 종류의 Animal 을 상속하는 동물클래스들을 작성할 수 있으며 그들의 공통 속성인 name, lifespan, color 것을 공통적으로 상속해 줄 수 있고 또한 공통 기능인 walk(),eat()등의 기능들도 상속해 줄 수 있다.
  - 그래서 추상메서드의 역할을 is kind of라고 하나보다..
- 반면에 인터페이스는 추상메서드 밖에 없지만 다중 상속이 가능하기 때문에 세분화된 기능을 나타내는데 활용될 수 있다.
  - 예를 들면 Animal의 하위클래스들 중에 세분화 기능이라고 볼 수 있는 flyable, ridable 등을 표시해줄 수 있으며 동물별로 여러개의 세분화 된 기능들을 추가하는데 사용할 수 있다
  - 그래서 인터페이스의 역할을 able to라고 하나보다..

- 정리해보면 공통적인 것을 모아 추상 클래스를 만들고 차이점을 모아 인터페이스를 만든다