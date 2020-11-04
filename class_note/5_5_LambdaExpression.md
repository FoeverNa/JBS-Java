# 람다식 (Lambda Expression)

## 람다식이란

- Java 1.8에서 추가된 함수형 프로그래밍 기법

- 객체지향 프로그래밍과 다르게, 비즈니스 로직만을 빠르게 구현하는 특징

  - 비지니스 로직 - Mission Critical한 부분 (둘이 비슷한 표현) <=  돈이 되는 부분을 얘기함()
    - 프로그래밍을 해서 돈이 안벌린다면 구지 프로그래밍을 할 이유가 없는것 (미사여구 빼고)
      - 프로그램을 작성하고 의미가 있으려면 비즈니스 로직을 갖추어야 한다

- 객체지향 언어인 Java에서 메소드를 함수처럼 사용하는 형식

  - Java에는 원래 함수라는 것이 없다..

  - 근데 함수형 프로그래밍을 하려면 '1급 함수'라는 개념이 필요하다

    - 1급 함수 :  클래스 없이 기능만을 구현한 **함수**가 Java에 **객체처럼** 파라미터, 리턴 값, 변수에 할당 될수 있으며 런타임에 생성될 수 있는 특징을 가진 함수를 1급 함수라고 한다.

    - 이것을 가능하게 하는 것이 람다식이다

  - 어떻게 가능하게 하나?

    - 클래스 with 메서드(한개) = 함수로 가정을 한다

    

## 람다식의 예

### 배열의 정렬

- 기본 정렬 방식을 이용한 정렬0

  ```java
  String [] strings = {"fast", "campus", "java", "backend", "school"};
  System.out.println(Arrays.toString(strings));// 입력 한 순 정렬
  Arrays.sort(strings);
  System.out.println(Arrays.toString(strings)); //사전 순 정렬
    // 정렬 기준을 바꿀 수 있다
  
  ```

- 정렬을 위한 Compartor를 implement한 클래스 생성해서 정렬하기

  ```java
  class MyComparator implements Comparator<String>{// Comparator는 compare라는 정렬기준을 지정해주는 메서드를 가지고있다
  
      @Override
      public int compare(String o1, String o2) { //첫번째 글짜는 때고 나머지 글자를 가지고 정렬을 하겟다
  
          return o1.substring(1).compareTo(o2.substring(1)); //comparTo는 String에 메서드
      }
  }
  
  public class Main {
      public static void main(String[] args) {
                 Arrays.sort(strings, new MyComparator());
          System.out.println(Arrays.toString(strings));
      }
      
  
  ```

- 익명 내부 클래스를 이용한 정렬 방식 변경

  ```java
         Arrays.sort(strings, new Comparator<String>() {// 익명내부클래스, 상속하려는 곳에 인터페이스든
                                                        // 추상클래스든 객체생성하면 익명클래스생성
              @Override
              public int compare(String o1, String o2) {
                  return o1.substring(2).compareTo(o2.substring(2));
  
              }
          });
          System.out.println(Arrays.toString(strings));
  ```

- 람다식을 이용한 정렬 방식의 변경

  ```java
                 Arrays.sort(strings, (o1, o2) -> o1.substring(3).compareTo(o2.substring(3)));
                System.out.println(Arrays.toString(strings));  
  		// 동작을 했으면 하는 부분만 심플하게 작성할 수 있다
          //클래스를 구현하는게 귀찮으니까 익명 내부클래스를 사용할 수있다
          // 익명내부클래스도 번거로우니 람다를 사용할 수 있다. => 줄어드는 양상을 보자고
  ```

  ```java
  Comparator<String> comp = (o1, o2) -> {
      return  o1.substring(3).compareTo(o2.substring(3))
  };
  Arrays.sort(strings, comp);
  ```

- Comparator 대신 Comaprable을 이용하는 방법

- ```java
  // Comparable//
  class Hansol implements Comparable<Hansol> {
  
      String value;
  
      public Hansol(String value){
          this.value = value;
      }
  
      @Override
      public int compareTo(Hansol o) {
          return this.value.substring(1).compareTo(o.value.substring(1));
      }
      @Override
      public String toString(){
          return value;
      }
  }
  
  Hansol [] hansols = {new Hansol("coampus"),new Hansol("fast"),new Hansol("java"),new Hansol("choigo")};
  Arrays.sort(hansols);
  System.out.println(Arrays.toString(hansols));
  
  // 객체 지향적으로 Comparable을 구현하는 것보다 Comparatro를 람다식으로 활용하는게 훨씬 편하다라는 결론...
  // -> 이렇게 람다식을 사용하는게 편리한 상황들이 존재한다
  ```



### 함수형 인터페이스 (Functional Interface)

- 오버라이드와 같은 어노테이션으로 추상 메소드가 단 하나 존재하는지 검사(람다식을 사용할 수 있는지 검사)

  - 메서드가 2개가 되면 어노테이션에  빨갛게 에러가 뜬다
  - 람다식은 해당 인터페이스를 구현한 클래스의 객체를 만들어서 해당 클래스의 유일한 메서드를 바로 구현하는 것이라고 정리해보자
  
- default 메서드는 FunctionalInterface에서도 사용가능하다

  - 추상메서드 1개 + default메서드 까지 가능하다

  ```java
  
  @FunctionalInterface// 필수는 아닌데 적어줄 수 있다 => FuntionalInterface인지 미리 검사해주어서 구현전에 알수있게해준다
  interface Runner<T>{
      T run(); // 단하나의 abstrac mehod를 가지고있음
  
  //    T runTwo(); //abstrac method를 2개 이상 작성하면 어노테이션에 빨간불들어옴 -> 문법에러는 아닌데 해당 어노테이션에 맞지 않다고
  
      default void mehtod(){} // default method 구현되어있더라도 lambda와 상관없다
  }
  
  public class Main {
  
      static void useRunner(Runner<?> runner){// 와일드 카드에 특정한 자료형 넣을 수 있지만 ?적으면 뭐든 올 수 있다
                                              // 기본적으로는 지금 상황에서는 String으로 했어야 했음
                                              // 제네릭사용하면 기본적으로 어떤파라미터 사용할지 적어야 한다
          System.out.println(runner.run());
  
      } // Array.sort 메서드와 비슷 , 인터페이스를 입력받으니까
  
  
      public static void main(String[] args) {
          useRunner(() -> "This is how to use runner.");
  
      }
  
  }
  ```

## 람다식의 형식

- 람다식의 다양한 표현 형식

```java

@FunctionalInterface
interface Runner{
    String run(String x); // 단하나의 abstrac mehod를 가지고있음

}
@FunctionalInterface
interface RunnerTWO{
    String run(); // 단하나의 abstrac mehod를 가지고있음

}

public class Main {
    static void useRunner(String x, Runner runner){
        System.out.println(runner.run(x));

    }
    static void useRunnerTwo(RunnerTWO runner){
        System.out.println(runner.run());

    }

    public static void main(String[] args) {

        // 기본형
        useRunner("안녕", (String x) -> {return x;}); // 가장길게쓴폼 // 입력파라미터에 자료형 입력가능
        
        useRunner("안녕", x -> {return x;}); // 입력 파라미터 1개면 () 생략 가능
        
        useRunnerTwo(() -> { return "안녕";}); // 입력 파라미터가 없으면 괄호 생략불가능
        
        useRunner("안녕", (x) -> {
            return x; // 중괄호 안에 세미콜론이 들어가는 경우 중괄호 생략불가 == 코드 여러 줄 작성 시 생략불가 이때, return도 생략 할 수 없음	
        }); // 일반 메서드 처럼 여러줄 기능 구현가능 하다는 뜻
        
        useRunner("안녕", x ->  x); // Expressiono을 바로 쓰면 알아서 retrun 해 준다 (Expression 람다)
        
        //여러가지 표현 방식은 IDE에서 정리를 해주기 때문에 크게 걱정은 하지 않아도 되고 가장 단순한 형태로 사용하는게 좋다
    }
    
}
```

- 퀴즈내용 추가
  - 람다식의 입력 파라미터 변수와 구현부의 로컬변수명을 겹칠 수 없다(둘다 같은 스코프에 로컬파라미터니까 겹칠수가 없지)
  - () -> 다음에 ()을 열었다면 무엇이든 구현해야 오류가 생기지 않는다.. 구현하지 않을 거라면 ()을 생략해야 한다
    - () -> 다음에 중괄호 {}는 구현을 하지 않아도 된다 -> 메서드 내용 비워놔도 괜찮은 것과 같다
  - (() -> )도 구현부가 비어있기대문에 안됨.. (() ->{})와 같이 중괄호라도 해주어야 한다..



## 람다식의 변수 참조

- 익명 내부 클래스와 동일한 규칙으로 변수 참조 한다 

- 문법적으로 this의 사용법만 다르다

  - this만 다르기 때문에 this만 알면 된다
- 익명의 내부 클래스와 달리 외부 클래스를 참조 한다
    - 람다식은 내부클래스를 따로 생성하지 않기 때문이다
    
  ```java
  // 람다식과 익명클래스 객체가 동일한 것은 아니다 라는 것의 증명 // 그 외에는 완전히 똑같이 동작함
  
  @FunctionalInterface
  interface IFoo {
  
      String method();
  }
  
  public class Main {
  
      static void functionalMethod(IFoo foo){
          System.out.println(foo.method());
      }
  
      void methodA(){
          functionalMethod(() -> { 
              System.out.println("this: " + this);// => Main의 this
                                                  // => 람다식은 익명 클래	스와 달리 내부 클래스가 만들어지지 않음
                                                  // 실제로 함수처럼 구현이 된다 // JVM에 작동방식이 다르다
              System.out.println("OuterClass.this: " + Main.this);// => Mian의 this
              return "Lambda expression used.";
          });
  
          functionalMethod(new IFoo() {// 익명 내부 클래슥 만들어지고, 그 객체가 활욛된다는게 실제로 이루어짐
              @Override
              public String method() {
                  System.out.println("this: " + this);// => 익명 내부 클래스의 this
                  System.out.println("OuterClass.this: " + Main.this);//=> Main의 객체(외부클래스의 객체)
                  return "Anonymous local inner class used.";
              }
          });
      }
  
      public static void main(String[] args) {
  
          new Main().methodA();
  
      }
  }
  // 람다식과 익명내무클래스는 구현은 다르지만 똑같은 기능을한다
  // 이렇게 구현이 달라도 똑같이 구현할 수 있는 이유는 자바에서 람다식이 함수형프로그래밍으로 동작하기 때문이다.
  
  ```





## 표준 함수형 인터페이스

- 자주 사용되는 함수형 인터페이스를 정의해 둔 API
  - 람다식은 정형화 될 수 밖에 없고 그것을 표준형으로 만들어 놓음 
    - 사용자정의 함수형 인터페이스 보다는 표준 함수형 인터페이스를 이용하는 것이 편리하다
      - 일단 인터페이스를 만들 필요가 없다..
- 람다식을 이용한 함수형 인터페이스를 어떻게 사용할 것인가에 관한 내용
  - 하나의 추상메서드와 디폴드메서드를 어떻게 연계해서 사용할 것인가를 이해해야 한다
  
  - 여러 표준 함수형 인터페이스를 연계해서 사용하는 경우를 이해해야한다
  
- Consumer, Supplier, Function, Operation, Predicate 계열이 있다.

| 계열      | 입력 | 출력      | 메소드                          | 설명                                       |
| --------- | ---- | --------- | ------------------------------- | ------------------------------------------ |
| Consumer  | O    | X         | `void accept(T)`                | 입력을 소비 // 입력만 받고 출력은 하지않음 |
| Supplier  | X    | O         | `T get()`                       | 출력을 공급  // 입력받지 않고 출력만 한다  |
| Function  | O    | O         | `R apply(T)`//입력 출력타입변환 | 입력 -> 출력 함수 매핑                     |
| Operation | O    | O         | `T apply(T)`                    | 입력을 연산하여 동일 타입의 출력으로 리턴  |
| Predicate | O    | `boolean` | `boolean test(T)`               | 입력을 판단//참인지 거짓인지 판단          |



### 표준 함수형 인터페이스의 종류

인터페이스이기 때문에 입력파라미터, 출력값만 달라질수 있다.



#### Consumer 계열

- 파라미터 입력을 받아서 그것을 소비하는 Functional Interface이다
  - 소비한다는 것은 새로운 출력을 내놓지 않고 함수내에서 처리하고 끝나기때문에 소비라고 하는 것이지 입력 파라미터만 있고 출력값이 void인 메서드와 다를게 없다
- accept 메소드를 사용하는데 리턴이 void인 것이 특징

| 인터페이스             | 메소드                                                       |
| ---------------------- | ------------------------------------------------------------ |
| `Consumer<T>`          | `void accept(T t)`//입력값으로 출력이아닌 다른방식으로 영향을 주는 역할 |
| `BiConsumer<T, U>`     | `void accept(T t, U u)`                                      |
| `IntConsumer`          | `void accept(int value)`                                     |
| `LongConsumer`         | `void accept(long value)`                                    |
| `DoubleConsumer`       | `void accept(double value)`                                  |
| `ObjIntConsumer<T>`    | `void accept(T t, int value)`                                |
| `ObjLongConsumer<T>`   | `void accept(T t, long value)`                               |
| `ObjDoubleConsumer<T>` | `void accept(T t, double value)`                             |

```java
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.ObjIntConsumer;

public class Main {

    public static void main(String[] args) {
        Consumer<String> consumer = (s) -> System.out.println(s);//accept()메서드 구현
        //comsumer javadoc에 functional interface라고 적혀있음..   //<String>해주었기때문에 s의 타입 입력하지 않아도됨
        // accept(Object) 입력받은 값이 제네릭 타입에 들어가겠지

        consumer.accept("A String");// A String

        BiConsumer<String,String> biConsumer = (t,u) -> System.out.println(t+","+u);
        biConsumer.accept("StringA","StringB"); //StringA,StringB


        // 오토박싱/언박싱 사용 - 비효율적 => 그냥 int를 사용하고 싶을때 Integer를 사용해야 하니까
        Consumer<Integer> integerConsumer = (x) -> System.out.println(x);
        integerConsumer.accept(5); //5
        //Autoboxing되서 전달되고 하는것

        //그래서 나온게 기본형 Consumer => PConsumer(P:Primitive Type)을 사용 가능하다
        //객체가 아닌 값을 입력받을 수 있다.
        IntConsumer intConsumer = (x) -> System.out.println(x);
        intConsumer.accept(10); //10
        //LongConsumer, DoubleConsumer 도 존재 한다
        // 오버로딩이 아닌 별도의 인터페이스를 가지고 있는 old C Style

        ObjIntConsumer<String> objIntConsumer = (t, x) -> System.out.println(t + ":" + x);
        objIntConsumer.accept("x", 1023); // x:1023
        // T타입과 P타입을 하나씩 입력 받는 ObjType
        //ObjLongConsumer, ObjDoubleConsumer가 있음
        
        //본질은 입력받고 그게 어떤 기능을 한다. => 그입력을 다양하게 받을 수있는 여러 다른 Variation이 있다 정도..
        //그리고 뒤에서 배울것들과 어떻게 연관되서 사용하는지 이해하면 좋다

    }


}

```



#### Supplier 계열

- 아무런 입력을 받지 않고, 값을 하나 반환하는 함수형 인터페이스 이다

- 자료를 '공급'하는 역할을 한다(공급자)

  

| 인터페이스        | 메소드                   |
| ----------------- | ------------------------ |
| `Supplier<T>`     | `T get()`                |
| `BooleanSupplier` | `boolean getAsBoolean()` |
| `IntSupplier`     | `int getAsInt()`         |
| `LongSupplier`    | `long getAsLong()`       |
| `DoubleSupplier`  | `double getAsDouble()`   |

```java
import java.util.function.BooleanSupplier;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {
        Supplier<String> supplier = () -> "A String";
        System.out.println(supplier.get());//A String

        //BiSupplier는 불가능,, return은 하나만 가능하기 때문에

        // Supplier는 P Type 계열에서 getAsP 메소드로 정의// 그냥 get()이 아님
        BooleanSupplier boolSup = () -> true; // true,false
        System.out.println(boolSup.getAsBoolean());//true// get이 아닌 getAsBoolean();

        //IntSupplier, LongSupplier, DoubleSupplier 사용 가능하다

        //주사위 예제
        IntSupplier rollDice = () -> (int)(Math.random() *6);

        for (int i = 0; i < 10; i++) {
            System.out.println(rollDice.getAsInt());

        } 
        // 3 4 4 5 2 1 3  4 4 5 2 3

        //예제2,
        int x = 4;
        IntSupplier intSupp = () -> x;
        System.out.println(intSupp.getAsInt());
        // 로컬변수와 지금 컨텍스트에서 접촉할수 있는 모든 변수를 활용 가능함
	    // fix되있는 동작이 아니더라도 동적으로 동작할 수 있다.. = > 그래서 의미가 있다
    }
}
```



#### Function 계열

- Mapping: 입력 -> 출력의 Mapping을 하는 함수형 인터페이스
- 입력 타입과 출력 타입은 다를 수 있다

| 인터페이스            | 메소드                                                       |
| --------------------- | ------------------------------------------------------------ |
| `Function<T, R>`      | `R apply(T t)` T라는 입력 타입 받아서 R이라는 입력타입으로 매핑해주는 것 |
| `BiConsumer<T, U, R>` | `R apply(T t, U u)`                                          |
| `PFunction<R>`        | `R apply(p value)`                                           |
| `PtoQFunction`        | `q applyAsQ(p value)`                                        |
| `ToPFunction<T>`      | `p applyAsP(T t)`                                            |
| `ToPBiFunction<T, U>` | `p applyAsP(T t, U u)`                                       |

- 매핑이란 하나의 값을 다른 값으로 대응시키는 것을 뜻한다. 위의 경우에는 하나의 자료형을 다른 자료형으로 대응시켜 주는 것.
- http://wiki.hash.kr/index.php/%EB%A7%A4%ED%95%91#:~:text=%EB%A7%A4%ED%95%91(mapping)%EC%9D%B4%EB%9E%80%20%ED%95%98%EB%82%98%EC%9D%98,(map)%EC%97%90%EC%84%9C%20%EB%82%98%EC%98%A8%20%EB%A7%90%EC%9D%B4%EB%8B%A4.

```java
P, Q : Integer, Long, Double
p, q : int, long, double
```

```java
import java.util.function.*;

public class Main {

    public static void main(String[] args) {
        // Function은  T, R 두개의 자료형을 입력받게 되어있다
        Function<String, Integer> func = (s) -> s.length();
        System.out.println(func.apply("four"));//4 = > 4글자이기 때문에
        // Function에 메서드는 apply

        // Bi가 붙으면 '입력'을 2가지를 받을 수 있다는 의미// 출력 2개는 원래 불가능
        //T,U 입력받아서 R 출력
        BiFunction<String, String, Integer> funcTwo = (s,u) -> s.length()+u.length();
        System.out.println(funcTwo.apply("A", "BDEFGHIJK"));//10

        //P Type Function은 입력을 P 타입을 받는다.
        //R만 입력을 받음 -> 입력 타입을 P로 정해져잇음
        IntFunction<String> funThree = value -> String.valueOf(value);//Integer.parsInt()는 반대
        //IntFunction<String> funThree = value -> ""+value; // 이것도 같은 기능
        System.out.println(512);//512

        // ToP Type Function은 출력을 P타입으로 한다
        ToIntFunction<String> funcFour = (s) -> s.length();
        //출력이 P타입인 경우에는 AsP가 들어간다
        System.out.println(funcFour.applyAsInt("ABCDE"));//5

        //ToIntBiFunction<String,String> // Int출력을 하는 두개의 입력을 받는것
        // P Type은 Int, Long, Double 이 있다

        // PToQFunction : P -> Q로 매핑하는 함수 //기본형에서 기본형으로 매핑해주는 Function
        IntToDoubleFunction funcFive;
        
        // IntToIntFunction은 없다 = > Function은 형태를 변환시키는 것이기 때문에 동일한 자료형은없다
        //기본형 자료만 없는 것이고 그냥 Function은 같은형으로 매핑이 가능하다
    }
}

```



#### Operator 계열

- 입력받은 타입과 동일한 타입의 출력을 하는 함수형 인터페이스
- Function 계열과 달리 입출력 타입이 다를 수 없다.

| 인터페이스             | 메소드                                              |
| ---------------------- | --------------------------------------------------- |
| `UnaryOperator<T>`     | `T apply(T t)`                                      |
| `BinaryOperator<T>`    | `T apply(T t1, T t2)`                               |
| `IntUnaryOperator`     | `int applyAsInt(int value)`                         |
| `LongUnaryOperator`    | `long applyAsLong(long value)`                      |
| `DoubleUnaryOperator`  | `double applyAsDouble(double value)`                |
| `IntBinaryOperator`    | `int applyAsInt(int value1, int value2)`            |
| `LongBinaryOperator`   | `long applyAsLong(long value, long value2)`         |
| `DoubleBinaryOperator` | `double applyAsDouble(double value, double value2)` |

```java
import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;
import java.util.function.UnaryOperator;

public class Main {

    public static void main(String[] args) {

        // Operator는 그냥 Operator Interface는 없음
        // 입력이 1개인 것을 Unary를 붙여서 표현
        UnaryOperator<String> operator =  s -> s+ ".";
        System.out.println(operator.apply("왔다"));//왔다.

        //입력이 1개건 2대건 입력 출력이 모두 같은 자료형을 사용한다
        BinaryOperator<String> operatorTwo = (s1, s2) -> s1 + s2;
        System.out.println(operatorTwo.apply("나", "왔다"));//나왔다

        IntUnaryOperator op = value -> value * 10;
        System.out.println(op.applyAsInt(5)); // 50
        //P type은 applyAsInt 붙는다
        // LongUnaryOperator, DoubleUnaryOpertor

        IntBinaryOperator intBinaryOperator = (v1, v2) -> v1*v2;
        System.out.println(intBinaryOperator.applyAsInt(3, 5));
        //LongBinaryOperator, DoubleBinaryOperator

    }
}

```



#### Predicate 계열

- 논리 판단을 해 주는 함수형 인터페이스이다
- 입력을 받아서 boolean 타입 출력으로 변환 한다

| 인터페이스          | 메소드                       |
| ------------------- | ---------------------------- |
| `Predicate<T>`      | `boolean test(T t)`          |
| `BiPredicate<T, U>` | `boolean test(T t, U u)`     |
| `IntPredicate`      | `boolean test(int value)`    |
| `LongPredicate`     | `boolean test(long value)`   |
| `DoublePredicate`   | `boolean test(double value)` |

```java
import java.util.function.BiPredicate;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {

        Predicate<String> pred = (s) -> s.length() ==4;
        System.out.println(pred.test("ABCD"));//true
        System.out.println(pred.test("ABCDE"));//false
        //Predicate 는 test()를 사용한다

        BiPredicate<String, Integer> pred2 =(s, v) -> s.length() == v;
        System.out.println(pred2.test("abcd",23 ));//false
        System.out.println(pred2.test("abcde",5 ));//true

        // P Type 입력가능하다
        IntPredicate pred3 = x -> x > 0;
        // LongPredicate, DoublePredicate
    }
)
```



### 표준 함수형 인터페이스의 메소드

#### andThen(), compose()

- 두 개 이상의 함수형 인터페이스를 연결하기 위해 사용 (인터페이스 엮어서 사용하기)

  - `A.andThen(B)`: A를 먼저 실행하고 B를 실행 Consumer, Function, Operator 계열 지원
    - Consumer, Function, Operator 계열에 default 메서드로 구현되어 있음

  - `A.compose(B)`: B를 먼저 실행하고 A를 실행. Function, Operator 계열 지원

  ```java
  import java.util.function.*;
  
  public class Main {
      public static void main(String[] args) {
          //Consumersms andThen만 있고 compse 없음
  
          Consumer<String> c0 = s -> System.out.println("c0" + s);
          Consumer<String> c1 = s -> System.out.println("c1" + s);
          Consumer<String> c2 = c0.andThen(c1); // 새로운 Consumer를 만든다
          //c0의 객체에 andThen이 default()메소드로 구현되어 있다
          c2.accept("String");
          //c0String
          //c1String
          //하나의 매개변수를 공유한다
  
          // Function 계열은 입력 => 출력 => 입력 => 출력 타입이 연쇄가 되어야 한다.
  
          Function<String, Integer> func1 = s -> s.length();
          Function<Integer, Long> func2 = value -> (long) value;
          Function<String, Long> func3 = func1.andThen(func2);
          //입력이 Inter인 아이가 와야됨.. 왜나면 fun1의 출력이 Inter이기때문에// 출력은 상관이 없음
          //func3는 String입력받고 Long을 출력하는 애가 되야함... 와웅...
          System.out.println(func3.apply("Four"));//4
         	// 입력값을 공유하지않고 메서드가 맞물리는 형태
  
          //andThen과 거꾸로 실행되는 것..천천히봐보자
          Function<String, Long> func4 = func2.compose(func1);
          System.out.println(func4.apply("Four"));//4
          //매개변수에 있는게 먼저실행된다.
          // 예제에서는 순서를 위해 func1과 func2의 자리르 바꿔주었다
  
  
          BinaryOperator<String> op0 = (s1, s2) -> s1 + s2;
          UnaryOperator<String> fnc0 = (s2) -> s2;
          UnaryOperator<String> op1 = s -> s + "";
          //2개의 값이 하나의 출력으로 나온걸 받아야하니까 Unary로 전환
          BiFunction<String, String, String> op2 = op0.andThen(op1);
          // 중간의 Funtion 계열이 있을 수도 있기 때문에
          // 최종 조합 결과는 Function 계열로 받아 주어야 함함
  
          //String만 사용하는 것이지만 andThen 입장에서는 어떤게 들어가있는지 모름
          //andThen 입장에서는 중간에 Operator가 아니라 Function이 들어갈 수 도있으니까
          // 전체가 Operator계열이라고 확실할 수 없음.. 그래서 BiFunction이라고 해야한다..
          //= > 이번 과제하면서 이해를 해보길 바란데...이런걸 사용해야하나보다
  
          Function<String, String> op3 = op1.compose(fnc0);
          //예제에서는 BinaryOperator를 거꾸로하는건 실패해서 그냥 operator를 function으로 compose함
          // 포인트는 Operator가 andThen이나 compose사용할 수 있지만 funtion으로 치환해야한다는 것
      }
  }
  ```



#### and(), or(), negate(), isEqual()

- Predicate 계열의 기본 메소드(boolean 계열) (인터페이스의 추상메서드)

  - and(), or(), negate() (not)   
  - 각각 &&, ||, !의 동작을 한다.

- Predicate 계열의 인터페이스의 클래스 메소드(인터페이스의 정적메소드)

  - isEqual()

  ```java
  import java.util.function.DoublePredicate;
  import java.util.function.Predicate;
  
  public class Main {
  
      public static void main(String[] args) {
  
          DoublePredicate p0 = x -> x  > 0.5;
          DoublePredicate p1 = x -> x  < 0.7;
          DoublePredicate p2 = p0.and(p1);
          
          //두개의 true/false를 엮어서 논리연산을 함
          System.out.println(p0.test(0.9)); //true
          System.out.println(p1.test(0.9)); //false
          System.out.println(p2.test(0.9)); //false => true and false = false
          System.out.println("");
          //하나의 매개변수를 공유한다
  
          System.out.println(p0.test(0.6)); //true
          System.out.println(p1.test(0.6)); //true
          System.out.println(p2.test(0.6)); //true => true and true = true
          System.out.println("");
  
          DoublePredicate p3 = p0.or(p1);
  
          System.out.println(p0.test(0.9)); //true
          System.out.println(p1.test(0.9)); //false
          System.out.println(p3.test(0.9)); //true => true or false = true
          System.out.println("");
  
          // negate = not
          DoublePredicate p4 = p0.negate();
  
          System.out.println(p4.test(0.9)); // not true = false;
          System.out.println("");
  
          // isEqual
  
          Predicate<String> eq = Predicate.isEqual("String");// isEqaul를 하는 객체를 생성
          // eq와 입력된 값이 같은지 확인해주는 메서드
          System.out.println(eq.test("String")); // true
          System.out.println(eq.test("String!")); // false
  
          //클래스메서드라 위의 경우와 다르게 객체로 메서드로 접근한게 아니라 클래스 메서드로 접근한다는것이 다름
          //인터페이스명.메서드명 , 람다식 구현이 없음..
      }
  }
  ```



#### minBy(), maxBy()

- BinaryOperator 인터페이스의 클래스 메소드(정적 메서드)

  - `Comparator<T>`를 파라미터로 받아 최소값/최대값을 구하는 `BinaryOperator<T>`를 리턴
  - Arrays.sort에서 사용하던 그 Compartor가 맞다
  
  ```java
  import java.util.function.BinaryOperator;
  
  public class Main {
      public static void main(String[] args) {
          BinaryOperator<String> minBy = BinaryOperator.minBy((o1,o2) -> o1.length() > o2.length() ? 1 : -1 );
          //위 예는삼항연산자로는 같은경우 0을 출력 하는경우가 있어야되는데 없기때문에 엄밀한 Comparator는 아니다
          // 파라미터 o1, o2에 String이라고 써주지 않는한 앞에 BianrayOperator<STring>이라고 언급해줘야 타입을 infer할수 있다
  
          BinaryOperator<String> maxBy = BinaryOperator.maxBy((o1, o2) -> o1.length() > o2.length() ? 1: -1);
  
          System.out.println(minBy.apply("abc", "cd")); // cd
          System.out.println(maxBy.apply("abc", "cd")); // abc
  
          System.out.println(minBy.apply("abc", "cde")); // abc
  
      }
  }
  ```



### 람다식에 메소드/생성자 사용 /

- 이미 구현된 메소드가 있는 경우, 다양한 방식으로 람다식을 대체 가능하다
  - 구현된 메서드를 람다식으로 구현하지 않고 재사용 하고자 할 때 사용하는 방법
  - 중요하다!

#### ClassName::instanceMethod

- 첫번째 파라미터를 객체로, 두번째 파라미터를 메소드 입력으로 사용

  ```java
  		// 클래스명 :: 인스턴스_메소드
          String [] strings = {"fast", "campus", "best", "academy"};
          Arrays.sort(strings, String::compareTo); // 기존에 있는 메서드를 참조
          // 클래스명 String :: compareTo 인스턴스 메서드
          System.out.println(Arrays.toString(strings)); //comparTo없어도 정렬이 도ㅣ긴한다..
          //Comparator 인터페이스는 2개의 입력 o1, o2를 받음
          //compare(o1, o2) -> o1.compareTo(o2) 가 되는 것을 설명해주셨다
          // o1.인스턴스_메소드(o2) 로 호출되는 메소드가 사용됨
          System.out.println("");
  ```

#### ClassName::classMethod

- 클래스 메소드의 입력으로 모든 파라미터가 사용됨

  ```java
          // 클래스::클래스_메서드
          Function<String, Integer> func = Integer::parseInt;
          //parseInt가 String 입력 받아서 Interger 출력하는애니까 Funtion으로 사용
          // 람다식 구현안받고 바로 메서드를 입력받을 수 있음
  ```

#### instance::instanceMethod

- 주어진 객체의 메소드를 호출

  ```java
          //인스턴스::인스턴스_메소드
          String s = "String";
          Predicate<String> pred = s::equals;
          System.out.println(pred.test("String" )); //true
          System.out.println(pred.test("String2" )); //flase
  
          //o1, o2가 입력받는 것과 o 만 입력받는것에 차이 인식해야함
          // 클래스 입력하는경우 인스턴스가 필요하니 첫번개 객체와 두번재 객체 둘다 입력받음
          // 근데 객체사용할 경우 해당객체 + 1개만더입력ㄱ받으면됨
  ```

#### ClassName::new

- 생성자를 이용하여 객체를 생성하는 람다식

  ```java
          // 생성자를 andThen, compose 등과 함게 사용 가능 (체인에 들어갈수 있음을 생각)
  
          // 클래스::new
          UnaryOperator<String> fnc = String::new;
          //String입력을 받아서 새로운 String 객체를 만들어줌 => 사용할수 있다정도만 이해해줘
          //생성자를 FuntionalInterface로 교체해서 사용할 수 있따
  ```

#### ClassName[]::new

- 배열을 생성하는 람다식

  ```java
          //클래스[]::new -> 배열 생성
          //Int입력받아서 String[]을 출력하는애
          IntFunction<String[]> fnc2 = String[]::new;
          //new String[10];을 그대로 사용하는 것
  
          // 길이가 100짜리 String[] 생성하기
          String[] strings1 = fnc2.apply(100);
  ```



## 추가공부

### 함수형 프로그래밍과 순수함수

함수형 프로그래밍은 부수 효과를 없애고 순수 함수를 만들어 모듈화 수준을 높이는 프로그래밍 패러다임이다

- 부수효과 : 외부의 상태를 변경한느 것 또는 함수로 들어온 인자의 상태를 직접 변경하는 것
- 순수 함수 : 부수 효과가 없는 함수 즉, 어떤 함수에 동일한 인자를 주었을 때 항상 같은 값을 리턴하는 함수 + 외부의 상태를 변경하지 않는 함수

### 순수함수와순수함수가 아닌것

```java
        IntBinaryOperator add = (a,b) -> a+b; // add는 순수함수

        int c = 10;

        IntBinaryOperator add2 = (a,b) -> a+b+c; // add2는 순수함수가 아님
                                                  // c가 상수라면 add2로 순수함수

        IntBinaryOperator add3 = (a,b) -> {
             c = b; // 람다식에서 실제 가능한 식은 아니다// c가 상수이거나 이펙티브 상수일때만 가능하다
             return a +b;
        }; // 외부 변수를 변화시키기 때문에 add3는 순수함수가 아님 

       class Bar{
           int val ;
       }
       Bar bar = new Bar();

        IntUnaryOperator add4 = b -> bar.val +b; // 외부객체를 변화시키기 때문에 순수함수가 아니다


        IntUnaryOperator add5 = b -> {
            int val = bar.val;
            return val + b;
        }; // 외부객체를 변화시키지 않고 값만 참조하기 때문에 순수함수가 맞다
```



### 요약 정리

순수함수는 외부의 상태를 변경하지 않으면서 동일한 인자에 대해 항상 똑같은 값을 리턴하는 함수이다. 그렇기 때문에 모듈화에 도움이되고 병렬화와 재사용에 도움이 된다