# 상속 (Inheritance)

 - 상속 : 어떤 클래스의 모든 멤버 변수 및 메소드를 계승하여, 새로운 클래스를 생성하는 것

 - 상속 대상 : 조상 클래스, 부모 클래스, 상위 클래스, 슈퍼 클래스(슈퍼 =상위)

 - 상속 결과 : 자손 클래스, 자식 클래스, 하위 클래스, 서브 클래스

 - 상속 관계를 흔히 'IS-A' 관계 라고 부른다.

    - 부모를 P, 자녀를 C라하면 C is P 라고 표현한다

   

## 클래스의 상속

 ```java
class Person{
    String name;

    public void work(){
        System.out.println("일하기");
    }

    public void sleep(){
        System.out.println("잠자기");
    }

}

// Person을 상속하는 자식 클래스
               //extends 키워드를 이용하여 상속
class Developer extends Person{

    String mainLang;//코딩하는 Language

    public void writeCode(){
        System.out.println("돈 받은 만큼 코딩하기");
    }

}

class Student extends Person{
    String major;

    public void writeCode(){
        System.out.println("밤새 코딩을 합니다.");
    }
}

public class Main {
    public static void main(String[] args) {
        // 클래스를 상속하면, 모든 멤버 변수와 모든 메소드를 상속받는다.(not 복사)
        Developer dev = new Developer();
        dev.name = "나개발";
        System.out.println(dev.name); //나개발 //Developer이지만 Person이기도 하다.
                                        // Developer 'Is-A' Person.// 개발자는 사람이다.
                                        // Developer is a Person.
        dev.work(); // 일하기
        dev.sleep(); // 잠자기

        dev.mainLang = "Java";
        dev.writeCode();

        Student stud = new Student(); // Student 'IS-A' Person
        stud.writeCode();

        //부모 클래스로부터 여러 자식 클래스들을 만들 수 있다.
        // 자식 클래스는 자기자신의 변수와 메서드 뿐아니라 부모의 것까지 사용할 수 있음
    }
}
 ```
- "class Developer extends Person"와 같이 클래스명 뒤에 extends "상속받을 클래스명"으로 상속받을 수 있다

  

- 클래스를 상속하면 모든 멤버 변수와 메소드를 상속받는다.

  - 부모 클래스의 멤버변수와 메소드를 사용할 수 있다
    - 이는 자식클래스의 객체를 생성시 부모 클래스의 객체를 같이 생성하기에 가능한 일이다
  - 따로 자식클래스에 변수와 메소드를 작성해주지 않아도 자식클래스 객체를 통해 접근할 수 있다.
    - 자식객체를 만드는 순간 상속을 위한 부모객체도 생성되기 때문에 해당 변수와 메서드를 사용할 수 있게 되는 것이다

  ![djk](C:\Users\foevn\Documents\dev\devlog\Images\djk.png)

- 이 때 두 클래스의 관계를 Devleoper 'Is -A' Person이라고 부를수 있고 이를 IS-A관계라고 함

  

- 하나의 부모 클래스로 부터 여러 자식 클래스를 만들 수 있고 자식클래스는 자신의 것 뿐아니라 부모의 변수와 메서드도 사용 가능하다

  - 자식 클래스가 부모의 클래스보다 범위가 넓다(부모클래스의 범위 + 자신의 클래스의 범위)

  


## 클래스의 포함 관계  (Class Composition)
(컴포지션은 구성이라는 뜻, 클래스를 조립해서 클래스를 만들어낸다는 의미)

- 상속하고 유사하지만, 한 클래스가 다른 클래스의 객체를 포함하는 관계이다

  

- 한 클래스가 다른 클래스를 포함하고 있기에 'HAS-A' 관계로 표현된다.

```java
// MainMachine 'HAS-A' String// String이 클래스기 때문에 우리가 클래스안에 String 변수 만들면 이미 컴포지션하고 있던거임
class MainMachine {

    String model;
    boolean isBroken = false;

    public MainMachine(String model){
        this.model = model;
    }
}

// Developer 'HAS-A' MainMachine
// Developer 클래스는 MainMachine의 객체 하나를 보유한다.

class Developer {
    String name;
    MainMachine mainMachine;

    public Developer(String name, MainMachine machine){
        this.mainMachine = machine;
    }

    public void writeCode() {
        if (mainMachine.isBroken == true){
            System.out.println("코딩을 할 수 없습니다.");
        }
        else {
            System.out.println(mainMachine.model + "(으)로 코딩하기");
        }
           if (Math.random() >0.9){

            breakMachine();
               System.out.println("machine이 고장났습니다.");
        }
    }
    public void breakMachine() {
        mainMachine.isBroken = true;
    }
}

// Developer 'HAS-A' MainMachine
// Devleoper 클래스는 MAinMachine의 객체 하나를 보유한다.(Has)//MainMachine에 속성에도 접근가능

public class Main {
    public static void main(String[] args) {
        MainMachine mac = new MainMachine("MacBook Pro");
        Developer dev = new Developer("너개발", mac);

        for(int i = 0; i<10; i++){
            dev.writeCode();
        }
    }
}
```

- 포함관계에서는 한 클래스에서 다른 클래스의 객체를 보유하여 그 객체를 활용하여 해당 클래스의 변수와 메서드의 접근한다.
- Devleoper 클래스는 MainMachine의 객체 하나를 보유한다. 
  - 이는 Developer클래스에서 MainMachine에 속성에도 접근가능다는 뜻이다
  - 위와 같은 관계를 Developer 'HAS-A' MainMachine라고 한다.



## super 키워드

- this가 자기 자신의 객체를 참조하듯, super는 부모 객체를 참조한다.

  - 자식 클래스(객체)에서 부모 클래스(객체)를 참조할 때 활용하는 키워드 이다

    

- super.super라는 식으로 부모의 부모는 참조할 수 없다.(조부모는 할수없다)

```java
 class Foo{
     String x = "Foo";
 
     public Foo(String x) {
         this.x = x;
     }
 }
 
 class Bar extends Foo{
   // String x = "Bar"; // 멤버 변수명이 부모와 겹치면 재정의 됨 // 그러나 부모 변수에는 영향을 주지 않음
                        // => 이것도 지우면 Foo Foo Foo 출력, 마지막으로 부모변수로 접근하게됨
 
     // 부모클래스에 기본 생성자를 사용하는 경우에는 super로 호출 안해줘도됨
     // 하지만 부모클래스에 생성자가 있으면 자식클래스에서 생성자를 호출 해주어야함
     // 부모클래스에 파라미터 생성자가 있으면 호출해 주어야 한다.
 
     public Bar(String x, String x1) {//파라미터가 겹치니까 x, x1으로 다르게해준것
         super(x); //부모 클래스의 생성자를 얘기함. this와 마찬가지로 첫줄에 써야 합니다. 부모클래스 생성자 호출
                   // 부모 객체를 먼저 생성하고, 그 다음에 자식 객체를 생성할때 이 super에서 이루어지는 것..아하
                   // 그니까 자식 클래스만들때 생성자에 포함시키면 무조건 객체 생성할때마다 부모 클래스를 생성할 수 있겠구나
                     // 손자 클래스를 만들때는 할아버지 + 부모 객체가 생성됨
        this.x = x1;
     }
 
     public void method(){
 
 //       String x = "method"; // 이렇게 지우면 Bar BAR Foo 실행됨 // 로컬 변수 -> 멤버변수> 부모의 멤버변수 순으로 접근하게됨
        System.out.println(x); // 로컬 변수
        System.out.println(this.x); // 자기 자신의 객체에 접근 가능
        System.out.println(super.x); // 부모 객체에 접근 가능
    }
 }
 //아무것도 상속하지 않은 경우, Object 클래스를 상속하는 것과 같다.
 class Jaemi extends Object{
     public void method() {
         // super. // 해보면 여러가지 메소드 속성 사용할 수 잇음. 사실 super안해도 사용할수잇음. 그래서 모든 클래스는 object의 속성포함함
     }
 }
 public class Main {
     public static void main(String[] args) {
         Bar bar = new Bar("","");
         bar.method(); //method
                       //Bar
                       //Foo
 
         //자식 객체 생성을 하면,
         //부모 객체를 먼저 생성 하고, 그 다음에 자식 객체를 생성
         //foo 인스턴스 생성후 Bar 인스턴스 생성하게됨
         //자식 여러명이 있으면 따로따로 부모의 객체를 생성하게됨
         //Perosn이라는 타입명으로 만들어도 다 따로따로 인스턴스가 존재할수 있는 이유.
 
     }
}
```

- 부모 클래스의 멤버 변수명과 자식 클래스의 변수명이 같을 경우 자식클래스에서 자동으로 재정의 된다
  
-  이 때 부모멤버 변수에는 영향이 없고 자식 멤버 변수만 재정의 된다
   - 위와 같은 상황에서 부자 클래스의 변수명을 구분하기 위해 super.과 this.을 앞에 참조하게 된다
   
        
   
- 자식 클래스를 생성할 때 기본 생성자에 super();가 포함되 부모 클래스의 생성자를 먼저 호출하고 자신의 객체를 생성하게 된다
    - 그렇기 때문에 자식클래스에서 부모클래스의 멤버변수와 메서드를 사용할수 있게 되는 것이다
    
    - 부모 클래스에서 파라미터 생성자를 만들 경우 자식클래스에서도 (같은  파라미터?)생성자를 만들어서 super()로 부모클래스의 파라미터 생성자를 참조 해주어야 한다
    
  - 이는 부모 클래스에서 파라미터 생성자를 만들 경우 기본생성자 없어지기 때문에 자식 클래스에서 부모클래스의 객체를 생성하기 위해서 부모 클래스의 파라미터 생성자를 참조해야할 필요성이 생기기 때문이다
    
     
    
  
 - 하나의 부모클래스의 여러 자식클래스가 있을 경우 자식들의 인스턴스를 생성 시 각각 부모클래스의 인스턴스를 따로 생성한다
   
    - 만약 5개의 다른 자식클래스의 인스턴스를 생성시 5개의 부모클래스의 인스턴스도 생성되는 것이다
    
      
    
- 이 것 때문에 Perosn이라는 타입명으로 만들어도 각각 따로 인스턴스가 존재할 수 있음(Person하나의 인스턴스를 공유하지 않기 때문)
  
  - 이를 통해 다형성을 구현할 수 있다
  
  
  ​      
  
 - 모든 클래스의 부모가 되는 클래스는 Objcet 클래스 이다
    - 평소에는 생략되었지만 모든 부모클래스는 Object클래스를 extends한다
    
    - 그렇기 때문에 Object클래스의 변수와 메서드를 어떤 클래스에서도  사용할 수 있는 것이다.
    
      
    
      

 ## 참고사항

 - main 메서드는 부모클래스에서만 생성할 수 있고 자식클래스에서는 생성하지 못한다(이게 의미있는 멘션일가?)
