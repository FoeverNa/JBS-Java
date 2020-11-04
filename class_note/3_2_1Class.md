# 클래스



## 클래스와 객체

- 클래스 - 객체를 생성하기 위한 설계도 (Class)

- 객체(인스턴스) - 클래스를 구체화하여 값으로 생성된 것(Object, instance)

- 클래스를 객체로 만드는 과정 - Instanciation 라고 함

  

## 클래스의 구성

```java
class Car{ // 클래스 이름은 보통 PascalCase로 적는다.
    int speed = 0; // 속성 = 멤버 변수
    // 속성 : attribute, filed
    // 멤버 변수 : member variable
    // 같은 개념이지만 컨텍스트에 따라 다르게 부름, 그래서 용어를 익숙하게 알아야함

    void move() { // 메소드 (method), (가끔 멤버 함수나 함수 라고 부르는 사람 but 메서드가 더 정확한 표현)
         speed = 10; // 행위를 구현, 주로 속성을 변경하는 역할
    }
}
```
- 클래스는 class 클래스명으로 생성할 수 있음 ex) class Car {}

- 클래스는 속성(=멤버 변수), 메서드로 구성됨

  


## 객체의 생성

```java
public class Main {
    public static void main(String[] args) {
        Car carOne = new Car(); // new 키워드로 클래스에서 객체 생성 (Instanciation)
                                // 생성방법 : 자료형은 클래스명 변수명은 camelCase = new 클래스명();
        System.out.println(carOne.speed);//0// .으로 속성 접근 가능
        carOne.move();
        System.out.println(carOne.speed);//10 // move()메서드가 속성을 변경함

        Car carTwo = new Car();
        System.out.println(carTwo.speed); // 0 // carOne과는 독립적인 객체로 존재함

        Car carThree = carOne; // 참조형 객체 (가르키는 객체)
        System.out.println(carThree.speed); //10//carThree는 new하지 않았으니 새로운 객체가 생긴 것이 아님
        carThree.speed = 5;
        System.out.println(carThree.speed);//5 
        System.out.println(carOne.speed);//5 carThree가 가르키는 carOne의 값이 바뀐 것이 었음!
        //carThree와 carOne은 다른 변수이지만 같은 값을 가르키고 있다.
        // String은 immutable이기에 참조형 객체이어도 이런 문제가 없다.(수정이 불가하기 때문에)
        // 그러나 보통 class는 mutabl이기 떄문에 신경써서 사용해야 함;
}
```
- new 키워드를 통해 인스턴스를 생성할 수 있음 ex)Car carOne = new Car();
  
    - Car클래스를 데이터타입으로 하는 carOne 변수에 인스턴스를 생성해서 대입하겠다.
    
- 인스턴스를 대입한 변수명 뒤에 '.'을 붙여 해당 인스턴스에 속성과 메서드에 접근할 수 있다.
  
    - ex) carOne.speed, carOne 인스턴스에 speed 변수에 접근
    
- 같은 클래스라도 new 클래스로 생성한 만큼의 독립된 인스턴스(객체)가 생성됨
  
- 위의 예시에서 carOne과 carTwo는 같은 클래스(정의)를 공유하는 독립된 객체
  
- 변수에 새로운 객체를 생성하지 않고 기존에 생성된 객체를 대입한다면 같은 객체를 참조하게 된다.
  
    - 위의 예시에서 carThree와 carOne이 같은 객체를 가르키고 어느한쪽을 수정하면 다른 한쪽도 수정되는 모습을 확인 가능
    
      - 그렇기 때문에 사용할 때 주의를 기울여야 한다
    
        
    
    - 하지만 String은 mutable하기 때문에 값이 수정되지 않아 이런 문제가 발생하지 않는다.
    
      

## 클래스와 객체의 메모리 구조

- 메모리 구조를 이해하여 한정된 메모리를 효율적으로 사용하여 최고의 성능을 낼 수 있도록 프로그램을 설계해야 한다

  

### 자바프로그램의 실행 구조

- 일반 프로그램 : 프로그램 -> 운영체제 -> 하드웨어
  - 일반 프로그램은 운영체제의 제어 아래에서 메모리를 제어하여 실행된다
- 자바 프로그램 : 프로그램 -> JVM -> 운영체제 -> 하드웨어
  - 자바프로그램은 JVM(Java Virtual Machine)이 운영체제로부터 메모리를 할당 받아 실행하게 되는 구조이다
- 자세한 내용은 링크 참조  (링크) [java메모리 구조](https://re-build.tistory.com/2)



### 클래스와 객체에서 고려해야할 3가지 메모리 영역

 1. 클래스 영역 ( Class area, method area, code area, static area) = Static Area = Method Area
   - field 정보, method 정보, type 정보, constant pool, Constructor(생성자)
        - field 정보 : 멤버변수 이름, data type, 접근제어자 등의 정보
        - 메서드 정보 : 메서드 이름, 리턴타입, 매개변수, 접근제어자 등의 정보
        - 상수 풀 ()
        - static 변수 
        - 프로그램 종료까지 메모리에 상주 한다

 2. 스택 영역(Stack area)
   - method 호출 시  Stack 영역에 각각의 메서드를 위한 메모리가 할당 된다.  즉, 각 메서드는  하나씩의 Stack을 가지게 된다.	

           - main메서드가 먼저호출 되고 그위로 각각 독립적인 메서드영억이 호출되는 형태

        -  선언된 로컬 변수(파라미터 로컬 변수 포함) , 리턴값, 연산값들을 임시로 저장하는 공간이다

        - method 사용이 끝나면 Stack 영역에서 해체 된다
        
             

 3. 힙 영역 (Heap area)
   - new 키워드로 생성된 객체(객체의 속성(멤버 변수))
   - 프로그램 실행 중 생성되는 모든 객체는 Heap영역에 동적으로 할당 된다
           - garbage collection(GC)이 동작하는 영역 , 이를 통해 메모리를 반환 한다
                - 더이상 사용하지 않는 메모리를 알아서 제거하는 JVM의 기능
- 참조형(Reference Type)의 데이터 타입을 갖는 객체(인스턴스), 배열 등은 Heap 영역에 데이터가 저장된다.

```java
        // 메모리 구조 예시
        
    public class MemoryStructure {// 클래스 영역
        int x, y;  // 힙 영역(new 키워드로 생선된 객체의 변수로 생성됨)
        // int x,y의 값은 값이 실제로 힙 영역에 메모리가 생성되서 그 값을 담게됨
        
        String string = "String!!!"; // 힙 영역(변수자체는 힙영역에 생성), 상수풀("String!!!"은 상수풀에 생성)
        // string은 클래스이기 때문에 값을 생성하는게 아닌 상수풀에 잡힌 값을 참조하는 참조값이 담기게 됨
        
        public void method(int value) {// 클래스 영역'
            // int value => 스택 영역 // 
            char c = 'w'; // 스택 영역
        } // 메서드 사용시에 임시로 스택영역에 메모리가 할당되고 메서드 사용 후에 사라지는 영역(지역 변수)
        // 참고 : 주소값을 몇bit로 쓰느냐에 따라 32-bit, 64-bit으로 나뉨, 근데 요즘은 대부분 64-bit os를 쓰기 때문에 대부분 64bit
        // 메모리 주소를 몇 bit를 쓰느냐에 따라 운영체제가 32-bit 64-bit나뉘고 ram을 4gb이상쓰기위해 64bit os가 주를 이룸
```
- 클래스 변수는 클래스 영역에 생성됨

- 로컬 변수와 같이 파라미터 로컬 변수(입력인자) 또한 스택영역에 생성되었다가 메서드 사용 후 메모리에서 사라짐
  
- 로컬변수와 파라미터 로컬 변수는 같은것이라고 인식해도된다. 같은 위치에서 생성되고 소멸됨
  
- 메서드에서 생성된 객체의 경우 힙영역에 메모리가 생성되지만 메서드 사용후 G.C에 의해 정리됨

    


## 변수 (Variables)

### 변수의 종류

- 클래스 멤버 변수 (static variable, class variable)

- 인스턴스 멤버 변수 (member variable, attribute, ..)

- 로컬 변수 (local variable)

- 로컬 파라미터 변수 (local parameter variable) (arguments)
               
### 변수의 생성     

```java
// 클래스에서 변수 생성

public class Variables {
    static int classVar; // static 자료형 변수명; // 클래스 멤버 변수, 스태틱 변수(정적 변수)
    int instanceVar; // 인스턴스 멤버 변수, 필드, 속성

    public void method(int paramVar) { // paramVar : 로컬 파라미터 변수
        System.out.println(paramVar);
        int localVar; // 로컬 변수 // 메서드 안에 속하는 로컬 변수
        //System.out.println(localVar); // 로컬 변수는 자동으로 0으로 초기화가 안되서 바로 출력 불가
        localVar = 10; //메서드 안에서 자유롭게 접근가능
        System.out.println(localVar); // 값 대입 후에는 출력가능
        {
            localVar = 30; // 메소드 안에 {}에서도 접근 가능
            int localVar2 =20;
        }
        System.out.println(localVar); // 블록 내에서 수정한 것도 반영됨
        // locaVar2 =40;// {}밖에서 접근 불가, 생명주기가 끝났다. Life-Cycle 끝났다.
    }
}
```
- 클래스 멤버변수는 static 자료형 변수명으로 선언할수 있음 exe) static int classVar;

- 클래스 변수, 객체 변수는 초기화 하지 않을 경우 0으로 초기화 되고 로컬변수는 초기화 되지 않음

- 로컬 변수는 메서드 안에서 생성되지만 메서드 내에서도 {}(블럭)으로 사용 지역 정해짐
   - {}안에서 생성된 변수는 {}안에서 생애가 끝남..
   - {}밖에서 생성된 변수는 {}안밖을 오가도 상관 없음.
   - for문 if문에서 {}를 사용하고 있다.

### 변수에 대한 접근

```java
// 각 변수에 대한 접근
class variableTest{
    public static void main(String[] args){
        System.out.println("클래스 변수"); // 클래스에 속하기에 인스턴스 만들지 않아도 존재// 특이 케이스
        System.out.println(Variables.classVar);//0 으로 초기화됨 => 어떤 변수는 초기화 하지 않아도 자동으로 된다는 것은 특이한케이스
                                                // 클래스 변수는 0으로 초기화 된다!!
        /// 클래스 변수는 클래스 이름으로 바로 접근 가능
        Variables.classVar = 10;
        System.out.println(Variables.classVar);//10
        System.out.println("");

        System.out.println("인스턴스 멤버 변수");
        Variables var = new Variables();
        System.out.println(var.instanceVar);//0 // 인스턴스 변수도 0으로 초기화됨 - 특이 케이스
        //Variables.instanceVar // 접근 불가능, 인스턴스 만들어야 객체(실체)가 생기기 때문
        var.instanceVar = 20;
        System.out.println(var.instanceVar);//20
        System.out.println("");

        Variables var2 = new Variables();
        System.out.println(var2.instanceVar); // 0// 0으로 초기화된 새로운 객체 생성

       // System.out.println(var2.classVar);//10// 인스턴스로도 클래스변수에 접근 가능, but 이러한 접근은 권장되지 않음
                                          // 클래스명으로해서 클래스전체에 속한 변수라는 것을 명확히 하며 사용하라는 뜻
        System.out.println("");

        //Variables.method(); // 잘못된 접근, 객체로 접근해야함

        var.method(9);
        // System.out.println(var.method(9)); //return값이 있어야 가능함


    }
}
```

- 클래스 변수는 인스턴스 생성하지 않아도 접근 가능함
    - 클래스명에 '.'을 붙여 접근할 수 있음 ex)Variables.classVar

- 객체 변수와 지역 변수에 접근하기 위해서는 인스턴스 생성해야 함 ex)Variables var = new Variables();
    - 객채명 뒤에 '.'을 붙여 접근할 수 있음 ex)System.out.println(var.instanceVar);

- 객체로도 클래스 변수에 접근 가능하지만 클래스명으로 접근하는걸 권장(클래스 변수라는걸 구분하기 위함)
   - ex) var2.classVar; => 접근가능 but 권장 X
