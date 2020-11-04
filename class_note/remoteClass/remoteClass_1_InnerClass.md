# 내부 클래스(Inner Calss)

- 클래스안에 클래스가 있으면 내부 클래스 - 중첩 클래스라고도 부름(Nested Class)

- Has-A 관계에 있는 클래스가 해당 클래스에서만 사용될 경우 주로 사용된다

  ```java
  class battery{
  
  class Cell{
  }
      
  }
  ```

> 베터리를 열어보면 안에 cell이 여러개 있다면 has-a관계라고 한다
>
> 근데 cell이 우리프로젝트에서 배터리안에서만 사용될 때  battery내부안에 셀클래스를 만든다
> => Has -A로 깔끔하게 구현할 수 있고 Cell이 다론곳에서 안쓰인다는것은 확실히 알 수 있는 효과 있다
> ==> 물론 Cell에 접근이 불가능한건아니지만 일반적으로는 그런의미로 사용이 된다

- 내부클래스 중에 가장 많이쓰이는 것이 익명의 내부클래스이고 그것을 활용한 것이 람다식이다



## 내부클래스의 종류

### 클래스 영역에 선언

#### 1. 인스턴스 내부클래스 :

- 외부 클래스의 멤버 변수처럼 사용가능

- static이 붙지 않은 내부 클래스

#### 2.클래스 내부 클래스 :

- static이 붙은 정적인 내부 클래스

  

### 로컬 영역에 선언

- 메소드 내부나 초기화 블록내부 또는 컨스트럭터 내부에 생성된 클래스

#### 3. 로컬 내부 클래스

#### 4. 익명 내부 클래스 

- 이름이 없음, 클래스에서 인스턴스 만들려면 클래스이름을 써줘야되는데 이름이없는 클래스

- 클래스에서 객체를 한번만 생성하려고 하면 클래스를 익명으로 만드는것도 가능하다 

  

## 다양한 내부클래스



### 1. 인스턴스 내부클래스

- 클래스 영역에 static 없이 만들어지면 인스턴스 내부클래스

- 내부 클래스에서 외부 클래스의 private 까지 접근이 가능하다 = > 엄청가깝네

- 외부 클래스의 객체를 통해서만 내부클래스의 객체를 생성가능(외부클래스의 이름만으로는 생성 불가능함)

  - Outer InstanceInner 를 자료형으로해서 outer.new InstanceInner();로 생성한다

    

- static 멤버 변수 사용할수 없으나, static final는 사용 가능하다
  -  클래스가 없어서..사용불가, 클래스가 있고 스태틱멤버변수가 존재하는데, 클래스가 없기때문에)
  - static final은 상수 취급이 되기 때문에 사용이 가능하다  => 질문 상수풀은 클래스 메모리영역이 아닌가?

```java
class Outer {
    class InstanceInner {
        int innerMemberVar = 1;
        //static int staticMemberVar = 1; // 존재 불가능
        static final int CONSTANT_VAR = 10; // 존재 가능 // 이너클래스명으로 접근간능

        void innerMethod() {
            System.out.println(innerMemberVar);// 접근가능
            System.out.println(outerMemberVar);// private 멤버변수에도 접근가능
        }
    }

  private int outerMemberVar = 2;

  void outerMethod() { // 멤버 메소드, 이 메서드 콜하기 위해선 아우터 인스턴스가 있다는뜻//그래서 그객체 통해 이너객체 생성가능
      IstanceInner obj = new InstanceInner();
      obj.innerMethod();
  }
    
    public static void main(String[] args) { // 
      Outer outer = new Outer();
      InstanceInner inner = outer.new InstanceInner();// 아우터 객체로 이너 클래스 객체생성,
        										 //outer.  new InstanceInner(); 두 부분으로 나누어보면 된다
      inner.innerMethod();
  }

}


```

- 외부 클래스의 멤버 변수와 이름이 겹치는 경우 this 키워드를 활용 할 수 있다.

```java
class Outer {
    class InstanceInner {
        int var = 1;

       void innerMethod() {
            System.out.println(var);//1
            System.out.println(this.var);//1 //innerclass에 this
            System.out.println(Outer.this.var);//2 //Outer.this이기때문에 Outer의 객체 뜻함
        }
    }

  private int var = 2;

  public static void main(String[] args) {
      Outer outer = new Outer();
      InstanceInner inner = outer.new InstanceInner();
      inner.innerMethod();
  }

}
```



2) 클래스 내부 클래스 (Class Inner Class)

- 클래스 내부에 Static 클래스를 생성하는 경우 클래스 내부 클래스라고 한다

- 외부 클래스 객체가 없어도 내부 클래스의 객체를 생성 가능하다

- 외부 클래스의 private을 포함한 모든 멤버에 접근 가능하다
  
- 하지만 접근시 외부 클래스의 객체를 생성해서 접근해야 한다
  
- static 클래스이기 때문에 static 멤버 변수와 메서드를 가질 수 있다.

  

- 내부 클래스에서 외부 클래스 변수 접근시 외부 클래스의 객체를 생성해야 한다

  - 하지만 객체를 생성해서 접근시 private까지 접근할 수 있다.wow

  

```java
class Outer {
    static class ClassInner {
        int innerVar = 1;
        static int staticInnerVar = 100;

        void innerMethod() {
            Outer outer = new Outer(); // 외부클래스 객체를생성해야 접근이 가능하다.. 내부에 있지만 외부에 접근을바로못해//객체에 속한게 아니기때문에
    			// 어디에도 속한게 아니다..?? Math.Adder 처럼 adder를 쓰고싶을때쓴다
            System.out.println(outer.outerVar);
            System.out.println(innerVar);
        }
    }
    
    private int outerVar = 2;
    
    void outerMethod() {
        ClassInner inner = new ClassInner();
        inner.innerMethod();
    }
    
    public static void main(String[] args) {
        ClassInner inner = new ClassInner();
        inner.innerMethod();
        System.out.println(ClassInner.staticInnerVar); //Ineer static 변수에 Inner클래스명으로 접근가능
    }

}
```

