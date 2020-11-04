



## 제어자란?

- 클래스, 변수, 메소드에 부가기능을 부여하는 키워드
- 접근제어자(Access modifiers)
  - 접근할 수 있는 범위를 정하는 키워드
  - public, protected, (default = package), private 
    - default키워드는 default 메서드에 사용됨
- 그외 제어자(other modifiers)
   - 특별한 기능을 부여하는 제어자
   - static, final, abstract, synchronized



## 제어자의 기능



### 접근제어자

 - 접근 가능한 범위를 정해, 데이터 은닉/보호(Encpsulation)기능을 추가한다.
   
   - 캡슐화가 필요 없으면 모두  public하면 된다. 하지만 편의성보다는 은닉하는게 더 좋은점이 많기에 사용된다.
   - 기본적으로 변수는 proteced를 통해 상속된 곳에서 사용하거나 private으로 하여 게터세터로 접근하게 한다
     - 만약 이렇게 사용하지 않고 그냥 직접 접근하여 사용하는게 자연스러운 변수가 있다면 클래스의 설계가 잘못된 것이다
   
 - 접근제어자별 접근 범위(public > protected > default > private)

   

| 접근제어자 | 같은 클래스 | 같은 패키지 | 다른패키지에 속한 자식클래스 | 전체 |
| :--------: | :---------: | :---------: | :--------------------------: | :--: |
|   public   |      O      |      O      |              O               |  O   |
| protected  |      O      |      O      |              O               |  X   |
|  default   |      O      |      O      |              X               |  X   |
|  private   |      O      |      X      |              X               |  X   |

- 코딩 실습1

 ```java
 
 package s04.s02.p05.subp01;
 
 public class ClassA {
     public int x;
     protected  int y;
     int z; // default(= package)
     private int w;
 
     public void methodA() {}
     protected  void methodB() {}
     void methodC() {} // default(=package)
     private void methodD(){}
 
     public void methodTest(){
         System.out.println(x);
         System.out.println(y);
         System.out.println(z);
         System.out.println(w);// 내부라 접근가능
 
         methodA();
         methodB();
         methodC();
         methodD(); // 내부이기에 접근 가능
 
         //private은 특성상 내부 구현을 위해서만 사용됨
     }
 }
 class ClassTest { // 같은 패키지인 경우우
     public static void main(String[] args) {
         ClassA obj = new ClassA();
         System.out.println(obj.x);
         System.out.println(obj.y);
         System.out.println(obj.z);
         //System.out.println(obj.w); // w는 private이라 접근 불가
 
         obj.methodA();
         obj.methodB();
         obj.methodC();
         // obj.methodD(); // 접근 불가 /IDE 빠른완성에 뜨지도 않음
     }
 
 }
 ```

- 같은 패키지의 같은 클래스의 경우 모든 접근제어자에 접근이 가능함

- 같은 패키지의 다른 클래스의 경우 private 접근제어외에 다른 접근자에는 접근이 가능함

  

- 코딩 실습2
```java
package s04.s02.p05.subp02;
import s04.s02.p05.subp01.ClassA;


class ClassAA extends ClassA {

    public void methodTest(){
        System.out.println(x);
        System.out.println(y); // 자식이라 다른 패키지에서도 사용 가능
        //protected는 private처럼 쓰지만, 상속한 경우 접근이 필요 할 때 사용함(내부적 구현 바꾸어야 할때)
        //System.out.println(z); // 접근불가
       // System.out.println(w);// 내부라 접근가능

        methodA();
        methodB();
//        methodC(); 접근불가
//        methodD(); // 내부이기에 접근 가능
    }
}

public class ClassB {

    public static void main(String[] args) {
        ClassA obj = new ClassA();
        System.out.println(obj.x);
        // System.out.println(obj.y); //protected는 다른 패키지인 경우 자식만 가능
        // System.out.println(obj.z); // default는 다른 패키지면 안됩니다.
        //System.out.println(obj.w); // w는 private이라 접근 불가

        obj.methodA();
        //obj.methodB();
        // obj.methodC();
        // obj.methodD(); // 접근 불가 /IDE 빠른완성에 뜨지도 않음
    }
}
```
- 다른 패키지에 해당클래스를 상속받은 클래스는 public과 protect만 접근이 가능함

- 다른 패키지에 상속받지 않는 보통의 클래스의 경우 public으로만 접근이 가능함

- 위의 예제에서 처럼 접근제어자 키워드 별로 접근이 가능한 범위를 지정해 줄 수 있음

  

### 클래스의 접근제어자

- 클래스에는 Public과 Default 접근제어자만 사용 가능하다

  - public과 deafault 접근제어자를 통해 다른 패키지에서 클래스에 접근 할 수 있는지 없는지만 제어한다

  - private의 경우 클래스로 접근이 다른 클래스의 접근이 불가능하기때문에 객체지향에 의미에 맞지않는다

  - protected의 경우 다른패키지의 자식클래스가 있다는 것이 전제되어야 되기때문에 public과 다른점이 없다(존재 의미가 없음)

    

#### 참고사항  : 클래스명이 public class의 클래스명과 같은 이유

- java 파일에는 무조건 하나의 클래스가 있기로 약속을 했고 하나의 클래스명은 파일이름과 같아야 한다는 규칙이있음

- 해당 public 클래스는 전체 클래스를 대표해서 다른 클래스들에서 불러서 사용할 수 있게 됨

- 그 외에 전체에 존재하는 다른 클래스는는 내부적으로 대표 클래스를 구성하는 클래스로 인식을하게 됨

  

#### 싱글톤 패턴(Singletone)

- private 키워드를 활용해 객체가 단 하나만 존재할 수 있는 클래스를 만드는 패턴
    - 게임의 스테이터스 등 하나의 클래스에서 하나의 객체만을 사용해야하는 경우 사용하는 패턴
    - 레지스트리 같은 설정 파일
    - 하나의 프린터를 공유하는 사무실
    - 공통된 오브젝트를 사용해야하는 프로젝 트 등의 사용 
```java
class SingletonClass {
  private final static SingletoneClass instance = new SingletonClass();
  private SingletonClass() {}

  public static SingletonClass getInstance() {
      return instance;
  }
}
```



### 그 외의 제어자

### final

- 더 이상 바뀔수 없음을 의미한다
   - 클래스 : 더이상 상속이 불가능해진다.  ex)Math 클래스는 final 클래스
   - 메소드 : 자식클래스에서 더이상 오버라이드할수 없음(재정의할수없음)
   - 변수 : 초기화한 뒤에 값이 변하지 않음.
      - 클래스에서 초기화 한 경우 fianal variable이고
      - 생성자에서 초기화 한 경우는 blank fianl vriable이라고 한다.
         - 인스턴스 생성시 black final variable은 값을 입력할 수 있다.

```java
public class Foo {
    final int x = 0; // final variable
    final int y; // blank finial variable
    public Foo(int y) {
      this.y = y; // blank final variable initialization
  }
}
```