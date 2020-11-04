# 메소드(Method)

## 메소드란

 - 객체가 하는 동작(행위)을 정의하는 작업을 수행하는 코드의 집합, 나열

 - 코드의 중복을 방지, 유지보수성을 향상, 코드의 가독성 개선 기능을 함

    - 극단적으로 main안에 모든 코드를 구현할 수 있지만 그렇게 하지 않는 이유 => OOP적인 구현을 잘하기 위해서

 - 메서드가 없는 프로그래밍은 상상두 할수가 없다

   

## 메소드의 구현

```java
    class Bar{
    // 인스턴스 메소드( 보통 그냥 메소드라고 부름)
         // Return type(출력의 자료형)
    public int add(int x, int y){ // 입력 파라미터 (메서드 호출시 같이 입력)
        return x + y ; //반환 값(Return value)
    } // return 값이 있기 때문에 정의가 되있는것이다. 변수는 어떻다고했는데

    // int를 반환하는 add라는 메서드인데 int x,y를 입력받는다 => 선언!
    //선언(Declaration) - ~한 것이 있다. 실제 구현은 X
    //정의(Definition) - 선언 + 구현(초기화)

     //정적 메소드(static method), 클래스 메소드
    // void return type은 return이 없음.(return이라고 적어도 되지만 안적어도됨)
    public static void classMethod(){
        System.out.println("클래스메소드 호출");
    }

  // 실행부
    public class Method {
    // main 메소드에서 실행이 되는 것은 약속. main 메서드도 static 메서드
    public static void main(String[] args) {
        Bar.classMethod(); //클래스메소드 호출 // 클래스 메서드는 바로 콜이됨
        //Bar.add(1,2); // 콜이 되지 않음
        Bar bar = new Bar(); //Bar 인스턴스를 생성
        System.out.println(bar.add(1,2));//3 // bar.add(1,2)가 반환값으로 대치가 됨
        bar.classMethod(); // 클래스 메소드 호출// 인스턴스에서 클래스메서드호출 가능 but 권장X
}
}
}
```
- 메소드는 Return type, 실행문, 입력파라미터, Return으로 구성
    - 출력타입 메소드명 (입력파라미터){Return} ex) public int add(int x, int y){ return x + y ;}
    
- 변수에서 처럼 메서드도 인스턴스메서드와 클래스 메서드로 구분됨

    - 클래스 매서드는 인스턴스 없이 클래스명으로 바로 호출 가능(객체 생성)
    - 인스턴스 메서드는 인스턴스 생성 후 호출 가능
    - 객체로 클래스 메서드 호출 가능하지만 권장X ex) bar.classMethod();

- 변수나 메서드의 선언은 ~한 것 이 있다고 얘기하는 것이고 정의는 선언+구현(초기화)한 것을 말함

    - 클래스 변수는 클래스의 생성과 동시에 초기화 되고 인스턴스 변수는 인스턴스 생성시 초기화됨
    - 메서드는 실행문 작성시 초기화 되고 실행문은 빈{}도 포함됨 ex) public void method(){}
      - 선언부만 있는것이 선언한 상태(추상메서드) ex) public void method()
    
- 입력 파라티터는 Parameter라고하고 메소드의입력값은 Arguments라고 한다.
      
  


### 클래스 메서드와 인스턴스 메서드

```java
class Person {
    static String korWord = "사람";
    boolean isHungry = true; // 인스턴스 멤버 변수
    // 클래스 메소드
    public static void describe(){
        System.out.println(korWord+"입니다.");
        // 클래스 메소드는 클래스 변수를 사용할 수 있다.
        // 단, 객체에 속하는 속성은 사용할 수 없다.
    }
    // 인스턴스 메소드
    public void eat(){
        isHungry = false;
    }
}

public class Method {
    public static void main(String[] args) {

        Person p1 = new Person(); // p1 변수명 자료형 Person// Person이라는 인스턴스가 할당되어있음
        Person p2 = new Person();
        // 인스터느 메소드는 객체의 속성을 변화시키는 역할을 한다.
        p1.eat(); //메서드 콜
        System.out.println(p1.isHungry);//false
        System.out.println(p2.isHungry);//true  // p1만 먹었기에 p2는 아직 배고픔

        Person.describe();//사람입니다. // 특정한 객체의 속성을 바꾸는 역할을 하지 않는다
                                      // static 변수를 변화 시킬수는 있다.
}
}
```

- 클래스 메서드는 클래스 변수의 속성을 변화시킬 수 있지만 인스턴스 변수를 변화시키지 않는다
  
  - 클래스 메서드에서 인스턴스 변수에 접근이 불가능하다
  
- 클래스 메서드는 오버라이딩 할 수 없다
  
  - 자녀 클래스에서 동일 명의 메서드 작성시 자녀의 클래스 메서드가 된다
  
- 클래스 메서드는 오버로딩 할 수 있다.

    

- 인스턴스 메서드는 객체의 속성을 변화시키는데 사용된다.
    - 각 객체는 독립적이기 때문에 같은 클래스에 여러개의 객체가 존재하여도 인스턴스 메서더는 해당 객체의 변수에만 영향을 준다
      
        - ex) 위 예제에 p1.eat()을 하여도 p2.isHungry는 변하지 않는다.
        
          


### 기본형 변수 vs 참조형 변수

```java

class Foo{
    int value;
        }

class Bar{
    //기본형 변수를 파라미터로 사용한 메서드
    public static void swapPrimitive(int x, int y){ // bar에 속성과는 상관없어서 static으로만듬
        int temp = x;
        x = y;
        y = temp;
    }
        // int x, int y의 사본이 넘어온다.
        // 기본형 타입(Primitive type)인 경우에 해당
        // call by value : 메소드 호출을 할 때, 값을 복사해서 넘긴다.
        // 값을 복사해온 이후에는 외부의 x,y변수와는 상관없는 애가됨. 그래서 밖에 변수가 안변함

    // 참조형 변수를 파라미터로 사용한 메서드
    public static void swapReference(Foo x, Foo y){
        // Foo -> class, class는 참조형 변수// 사본이 아닌 해당 변수를 가르킴
        int temp = x.value;
        x.value = y.value;
        y.value = temp;
    }

public class Method {
    public static void main(String[] args) {
       // Call by
        int x = 10;
        int y = 20;

        Bar.swapPrimitive(x, y);
        System.out.println(x+","+y); //10,20 // 변화없음 왜? 위에 메서드에 설명

        Foo f1 = new Foo();
        Foo f2 = new Foo();

        f1.value = 10;
        f2.value = 20;
        Bar.swapReference(f1, f2);
        System.out.println(f1.value + "," +f2.value);//20, 10 => 값의 변화에 영향을줌
}
}
}
```

- 기본형 변수를 파라미터로 사용할 경우 외부변수의 사본을 가져와서 실행문에 대입됨 => CallbyValue
  
- 메서드의 결과 값이 외부 변수에 영향을 주지 않음 
  
- 참조형 변수를 파라미터로 사용할 경우 해당 변수를 참조 하기 때문에 메서드의 결과값이 해당 변수에 영향을 줌 => Call by reference
  
    - 실제로 참조한 변수의 값이 변화하게 됨으로 주의해서 사용해야 함
    
      

### 가변 인자(Variable Arguments)


```java
class Bar{
                               // 여러개의 int를 입력 받는다.
                               // 입력 받은 결과는 배열로 주어진다.
                               // 가변 인자(Variable arguments) 인자가 변한다.
    public static int sumAll(int... params){
        int sum = 0;
        for (int val : params){
            sum+= val;
        }
        return sum;
    }
    // 메소드 오버로딩 Overloading
    // 함수명은 같고 입력인자가 다른 메서드
    // 입력 인자의 갯수도 다를 수 있다.
    public static float sumAll(float... params){
        float sum = 0;
        for (float val : params){
            sum+= val;
        }
        return sum;
}
}

//실행부
public class Method {
    public static void main(String[] args) {
        System.out.println(Bar.sumAll(2,3,4,1,3,24,346,1,2,34,-123,-12,2));// 287
        System.out.println(Bar.sumAll(2.2f,0.2f,3.2f,56.23f));// 287
        // println이 자료형에 상관없이 동장하는 이유 = 오버로딩
}
}
```
  - 같은 data type에 여러개의 파라미터를 입력할 때 가변 인자를 사용함
    - 자료형... params을 사용 ex)(int... params)
    
  - 입력 받은 결과는 배열로 주어짐 // data type[] params;
    - (int... params)의 params가 해당 배열의 이름
    

### 메서드 오버로딩

  - 같은 함수명을 갖고 입력인자가 달라서 여러가지 data type을 입력 받을 수 있게 하는 것을 메서드를 오버로딩이라고함
    - 같은 기능을 하며 여러가지 입력값을 받을 수 있는 메서드를 추가로 작성하는 것
    
    - 자료형이 다르거나 입력 인자의 갯수가 다른경우에 해당 함ex) void add(int x,int y){} void add(int x,int y,int z){}
    
      - 같은 자료형이더라도 입력 인자의 갯수가 다른 경우도 오버로딩이 가능하다
      - 리턴 타입만 다르면 안된다
      - 입력인자가 사이에 업캐스팅이 되는 경우는 가능한 범위내에서 자동으로 된다 => 퀴즈 문제 떠올려바
    
    - 오버로딩의 대표적인 예로 자료형에 상관없이 출력해주는 prinln()이 있음
    
      


 ### 클래스 안에 main메서드가 포함된 경우

 ```java
  public class Method {
      public static void classMethod() {
          System.out.println("클래스 메소드 호출");
      }
  
      public void instanceMethod() {
          System.out.println("인스턴스 메소드 호출");
      }
      
      // main 메소드에서 실행이 되는 것은 약속. main메서드도 static 메서드
      public static void main(String[] args) {
  
  //main 메서드에서는 어떻게 클래스 /인스턴스 메서드를 호출 하나 - 퀴즈
  
          classMethod();// 동일 클래스에 속한 클레스 메소드 호출(클래스 명 필요 X)
                       // 클래스메서드에서는 같은 클래스에 다른 클래스메서드를 바로 호출이 가능함
          Method.classMethod();// 생략 안할수도 있음
          Method m = new Method(); // 인스턴스 생성 안되어있으니 생성해야함.
          m.instanceMethod(); // 인스턴스 메소드 호출
}
}
 ```

  - main 메서드는 프로그램의 시작부라고 약속된 클래스 메서드로 실행기능을 담당
    
  - main 메서드가 선언된 클래스안에서 선언된 클래스 매서드는 main메서드에서 호출시 클래스명 없이 메서드 명만으로 호출 가능
    -ex) classMethod();
    - 같은 상황에서도 인스턴스 메서드는 인스턴스를 생성해야 호출 가능함
  
  - 메인 메서드안에서 선언된 변수들도 로컬 변수로 인식되어 메인메서드 사용 할때만 스택 메모리에 생성됨
  
  - 일반적으로 메인 메서드는 public Class에 사용됨

  

  ## 생성자 (Constructor)

 - 클래스에서 인스턴스를 생성할 때 사용하는 메서드 => 정확히 메서드는 아니래

      - new 키워드를 사용할 때 호출되는 메서드
 - 기본 생성자 (Default constructor)
      - 구현하지 않아도 자동으로 생성되는 생성자이다
      - 아무런 동작도 하지 않고, 객체만을 생성한다
        - 파라미터 없이 내용을 구현하였으면 그것도 기본생성자라고 부르긴한다
 - 파라미터 생성자(Parameter constructors)
     - 입력 파라미터를 받는 생성자
     - 여러개의 생성자를 오버로딩 할 수 있다(여러 입력파라미터를 가질 수 있다)
     - 보통 멤버변수를 초기화하는 동작을 수행 한다


  ### 기본 생성자

  ```java
public class Constructor {
    int x;
    int y;
    String z; //  3개다 인스턴스 변수

//    public Constructor() {} 기본 생성자, 구현하지 않아도 알아서 생긴다.(생략가능)

  public Constructor() {
        this(1, 2, "");
    }

public class ConstructorTest {
    public static void main(String[] args) {
            Constructor c = new Constructor(); // 기본 생성자 호출
            //System.out.println(c.x+","+c.y+","+c.z);//0,0,null //기본생성자
            // z의 경우, 클래스이기 때문에 null로 초기화 됨
            // null -> 아무것도 참조하고 있지 않다. // 참조형 변수의 경우 null로 초기화
            System.out.println(c.x + "," + c.y + "," + c.z);//1,2,초기화//생성자 수정 이후
}
}
}
  ```

- 기본 생성자는 입력 인자와 실행문이 비어있는 생성자로 생략시 자동구현된다(직접 구현하지 않아도 된다)
  
    - 기본생성자를 따로 작성하지 않아도 바로 인스턴스를 생성할 수 있었던 이유
    
- 기본 생성자로 인스턴스를 생성하면 멤버 변수의 값은 int=0, Stirng=null로 초기화 됨(null은 아무것도 참조하지 않고 있다는 뜻)

- 기본 생성자에 각 변수별 초기화 값을 실행문에 넣어주면 인스턴스 생성시 해당값으로 각 변수가 초기화 됨
  
    - 위와 같은 생성자는 기본생성자라고 부르지 않는다
    
      

### 파라미터 생성자

```java
public class Constructor {
    public Constructor(int x, int y, String z) {
        this.x = x; // this는 멤버 변수를 표기하기 위해 사용될 수 있다.
        this.y = y;
        this.z = z;
    }

    //생성자는 클래스이름, return이없기에 return type적지 않음
  // 이전 생성자를 불러오는 this.
        //this.는 무조건 첫줄에 사용할 수 있다.
        //가장 긴 파라미터를 기준으로 짧은것을 this로 불러올 수 있다.
    }

public class ConstructorTest {
    public static void main(String[] args) {
         Constructor c1 = new Constructor(10, 20, "파라미터생성자");
         System.out.println(c1.x + "," + c1.y + "," + c1.z); // 10,20,파라미터생성자
    
         Constructor c2 = new Constructor(10, 20);
         System.out.println(c2.x + "," + c2.y + "," + c2.z); //10,20,
}
}
```
- 파라미터 생성자는 입력 인자를 입력 받는 메서드로 기본 생성자를 오버로딩 하여 만든 메서드
  
- 필요에 따라 다양한 파라미터를 조합하여 생성자를 여러개 만들어서 사용할 수 있음
  
- 생성자를 여러개 오버로딩 할때는 this키워드를 사용해서 이미 만들어둔 생성자를 참조하여서 코드 중복사용을 줄임

- 일반적으로 입력받은 Arguments로 멤버변수를 초기화하는데 사용한다

  ​    

### this 키워드

- this는 해당 클래스의 이미 작성된 생성자를 호출할 때 사용 된다
    - 일반적으로 가장 입력 인자가 많은 생성자를 this로 호출해 필요한 부분만 따로 초기화 값을 지정해 준다

    - 생성자를 호출하는 경우로 this()를 사용하는 경우 실행문에 첫 줄에만 사용 가능함

      - 다른코드 아래의 사용하게 될 경우 에러가 발생한다

    - this();는 기본생성자 호출하는 것이고  파라미터 생성자 호출시 ()안에 입력변수 입력해야한다

      

- 또한 this는 하나의 클래스에 멤버 변수명과 파라미터 변수명이 같을 경우에 멤버 변수를 가르키는 용도로도 사용됨.

    - this. 변수명 으로 접근 한다

        

- this를 사용하는 이유는 코드 중복을 최소화하여 실수를 방지 하기 위하여 사용함 
  
    - 코드의 중복을 최소로 할 수록 좋은 코드가 됨
    
      
    
- this는 그자체로 자기자신의 객체를 가르키는 참조변수로도 사용될 수 있다



## Getter,  Setter

 - 멤버 변수를 간접적으로 다룰 수 있게 해 주는 메소드

       - 게터에 조건을 달아 특정 범위만 접근할쉬있도록한다든지 셋에 조건을 걸어 어떤 범위만 입력할 수 있게 한다던지 할 수 있따

         

 - 멤버 변수의 캡슐화(Encapsulation)에 도움이 됨
         
         - 정보의 은닉/보호
      
         
      
 - 멤버 변수의 값에 대한 접근을 제한 해야 할 때 유용 하다

```java
public class GetterSetter {
    int x,y;
    String z;
    float w; // code - generate - GetterSetter

    //constructor도 Generate를 통해 만들 수 있다.
    //constructor 만들면 기본 생성자가 사라짐.
    //만약 둘다 필요하다면 기본생성자를 작성해주면됨
    public GetterSetter(){};
    public GetterSetter(int x, int y, String z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if(x>0 && x <= 1000){
            this.x=x;
        }else{
            System.out.println("x should be 1<= x <=1000");
            System.out.println("however, you put in x =" +x);
        }

    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getZ() {
        return z;
    }

    public void setZ(String z) {
        this.z = z;
    }

    public float getW() {
        return w;
    }

    public void setW(float w) {
        this.w = w;
    }
}

class GetterSetterTest {
    public static void main(String[] args) {
        GetterSetter g = new GetterSetter();
        g. x= 10;
        System.out.println(g.x);
        g.setX(20);
        System.out.println(g.getX());  // 왜이렇게 귀찮게 해?
                                   // 속성에 대한 접근을 개발자가 설정할 수 있음
        g.x=20; // 권장되지 않은 접근
        g.setX(1111111111);
        System.out.println(g.getX());
    }
}
```

- 클래스에 필요한 변수를 선언한 후 Menu -Code - Generate - Getter, Setter를 선택하여 자동으로 생성할 수 있음
  
    - 참고로 Constructor도 같은 방법으로 사용 가능하다
      - 이 때 기본 생성자도 같이 사용하고 싶다면 기본 생성자를 오버로딩 한다
    
- get()메서드는 해당 변수를 불러 오는 메서드이고 set메서드는 해당 변수의 값을 수정할 때 사용하는 메서드
  
    - set()메서드는 조건을 설정해서 조건하에서만 변수의 값을 수정할 수 있게 작성 가능하다
        - 날짜 같이 입력값에 형식이 존재하는 경우에 활용이 가능 하다
    
- 사용자에게 멤버 변수에 접근을 제한적으로 설정 할 때 사용할 수 있다
  
    - 변수에 접근 범위를 설정할 수 있다
      
    - 접근제어자 private과 함께 사용된다
    
        


## 초기화 블록 (Initializer)

- 초기화 블록은 클래스 또는 인스턴스를 생성할 때 단 한번 실행되는 코드 블록


```java
public class Main {
    static int classVar;
    static int instancecount;
    int instanceVar;

    // static initializer
    static {
        System.out.println("static block1");
        classVar = 20; // class variable에만 접근 가능, 객체생성 이전
    }

    // object initializer
    {
        System.out.println("block1");
        instanceVar = 30;// 객체 생성이후이기 때문에 instanceVar에 접근가능
        classVar = 50;// 추천되지 않음, 객체생성이 스태틱에 영양주기에
        instancecount++; //예외적으로 전체적으로 관리해주어야 할항목은 넣을 수 있음
    }

    static{ //2번째 staticinitialze는 첫번째 이후에 바로실행됨
        System.out.println("static block2");

        classVar = 5;
    }

    { // 2번째 object도 마찬가지.. 결국은 동일한 중괄호 안에 있는것과 다르지 않다.
        instanceVar =5;
        System.out.println("block2");

    }
        // 초기화 블록을 어디다쓴다고 했지?
}

class MainTest{
    public static void main(String[] args) {
        //static block 실행(만들어지면서 실행바로됨)
        System.out.println((Main.classVar));//20// static block에서 초기화됨
        //instance 생성시 initializer 실행
        Main main = new Main();
        System.out.println(Main.instancecount);
        System.out.println(main.instanceVar);//30 // object initialize에서 초기화됨
        System.out.println(Main.classVar);//20

       Main main2 = new Main();
        System.out.println(Main.instancecount); //block1
//                                                block2
//                                                 2
                                                   
        Main main3 = new Main();                
        System.out.println(Main.instancecount);//block1
//                                               block2
//                                                 3
    }
}

```

- static initializer는 클래스가 생성 될 때 단 한번 실행 됨
  
    - 클래스가 생성되는 시점은 main에서 클래스가 사용되는 시점에 생성된다
      - 보통은 main메서드 에서 해당 클래스에 정적 변수나 메서드나에 접근하거나 객체를 생성한 경우를 나타낸다.
        - 위 예제에서는 MainTest에서 Main.classVar을 실행했을때 해당 클래스가 메모리에 생성이되고 그후 해당클래스의 클래스 변수에 접근하게 된다
        - 만약 클래스 변수에 접근하기전에 먼저 Main에 객체를 생성했다면 먼저 해당 클래스가 생성되고 그 후 클래스의 객체를 생성하게 되는것이다
    
- 객체 생성 이전에 생성 되기 때문에 클래스 변수에만 접근 가능하다 (인스턴스 변수 사용 불가)
  
  
  
- object initializer는 인스턴스가 생성 될 때 마다 한번 실행되는 코드이다(객체는 여러번 생성 가능하다)
  
    - 보통은 객체 변수에만 접근 하지만 클래스 변수에도 접근할 수는 있다 (특별한 경우 빼고 권장하지 않음) 
    
      
    
- 두 initalizer 모두 여러번 나누어 작성해도 각각 하나의 코드를 길게 늘어뜨린형태로 한번에 실행된다

    - 위치와 상관 없이 통합되서 순서대로 실행 된다

