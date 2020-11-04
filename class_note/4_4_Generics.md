# 제네릭 (Generic)

- 클래스에서 사용할 타입을 클래스 외부에서 설정하는 것

- 클래스 내부에서 사용하는 데이터의 타입(Type)을 클래스의 인스턴스를 생성할 때 결정하는 것을 의미한다

  

## 왜 제네릭을 쓰는가?

- 여러가지 자료형을 허용하고 싶을 때 Object를 선언 할 수 있지만, 원하지 않는 자료형을 입력 되었을 시 오류를 컴파일 시점에 잡아낼 수 가 없다

- 제네릭은 타입을 입력 받을 때 사용할 수 있는 타입을 명시 할 수 있다.

    - 이를 통해 객체의 타입을 컴파일 시점에서 검사할 수 있어 불필요한 에러를 방지 할 수 있다
    - 타입이 정해져있기 때문에 타입 변환을 할 필요가 없어서 프로그램 성능이 향상되는 효과를 얻을 수 있다
  - Object를 사용하면 형변환이 필요하다?
    

    

## 재네릭 클래스

### 제너릭 클래스의 생성

- 제너릭 클래스는 클래스명 뒤에 <>을 붙여 안에 타입파라미터를 넣어준다 
  
    ```java
    class GenericFoo<T> { // T: 타입 파라미터
    }
    ```

    - 타입 파라미터는 클래스 선언시 아직 결정되지 않은 타입이다
      
        - 타입 파라미터는 참조형 타입만 올 수 있다 
        
          - 기본형(primitive)타입은 사용할 수 없어서 Wrapper클래스를 사용한다
        
            
        
    -  클래스안에서 타입파라미터를 자료형으로 인스턴스 변수와 메서드를 선언할 수 있다.
    
        ```java
        class GenericFoo<T> { 
            String name;
            T memberVar;
        
            public GenericFoo(String name, T memberVar) { // 어떤타입이 들어올지 모름
                this.name = name;
                this.memberVar = memberVar;
            } // 생성자에는 클래스명 뒤에 <T>를 생략
        ```
        - 생성자에는 클래스명 뒤에 입력파라미터 <T>를 생략한다
    
      
    
- 인터페이스도 제너릭으로 생성할 수 있다

    ```java
    interface GenericInterface<T>{ //인터페이스도 제네릭이 가능
    }
    ```

     

- 타입 파라미터의 갯수는 여러개가 될 수 있다

    ```java
    class HashMap<K,V>{ // 여러개의 타입 파라미터도 쓸 수 있다.
    }
    ```

      

- 타입 파라미터의 자료형은 클래스의 객체 생성시 입력해 준다

    ```java
    public class Generics {
        public static void main(String[] args) {
            GenericFoo<String> foo = new GenericFoo<String>("name","var");//<dataType> 
            GenericFoo<String> foo1 = new GenericFoo<>("name","var");// 생성자에 DataType은 생략가능
            System.out.println(foo.name);// name
            System.out.println(foo.memberVar);// var
    }
    }
    ```

    - 객체 생성시 자료형과 생성자 뒤에 <DataType> 형식으로 입력한다 

    - 자료형 뒤에  <DataType>을 써준뒤  생성자에 DataType은  생략이 가능하다

        

### 제너릭 클래스의 사용 제한

- 문법적으로 문제가 있는경우
  - 정적 변수의 자료형으로 타입파라미터를 사용할 수 없다 

    ```java
    class GenericBar<T>{
    static T classVar; // not possible
    }
    ```

     - 정적 변수는 객체 생성 이전에 호출 될 수 있기 때문에 객체 생성시 자료형이 결정되는 제너릭 클래스에서는 사용할 수 없다

       

  - 정적 메서드의 자료형으로 타입파라미터를 사용할 수 없다 ex) static void method(T var){}

      ```java
          static void method(T var){
         return var;
         } // not possible too or two
      ```
      
      - 정적 변수의 경우와 마찬가지로 객체 생성 이전에 호출될 수 있기 때문에 사용이 불가능하다
      - 하지만 타입파라미터를 사용하지 않는 정적메서드는 당연히 사용할 수 있다
      
      

- 문법적으로는 문제가 없지만 안정성 문제로 제한이 되는 경우 
  
- 문법적으로 문제가 없다는 것은 다른 클래스나 자료형으로는 가능한 일이지만 제너릭클래스로만 안되는 경우를 뜻한다?
    
- 안정성 문제 : 타입 파라미터가 어떤 것으로 구현되 있을지 모르는 상황에서 안정성에 문제가 생길 수 있는 상황
  
    - Java에서는 이런식으로 구현하지 말라고 막혀있음
  
      
  
  - 타입파라미터 형으로 객체를 생성할 수는 없다.
  
    ```java
      T memberVar = new T(); // not possible 
    ```
  
    
  
  - instacneof의 비교 대상이 될 수 없다
  
      ```java
    ex) Object obj = new Object();
      if(obj instanceof T) //not possible
    ```
  
    - 됬으면 참 편했을텐데..
  
      
  
      

### 제네릭 타입의 상속

- 제너릭 클래스와 인터페이스 모두 상속과 구현이 가능하다

    ```java
    class GFoo<T> {
    
    }
    
    interface IGFoo<T>{
    
    }
    class GIGFoo<K, T, D> extends GFoo<T> implements IGFoo<D>{
    
    }
    ```

    - 부모 클래스와 인터페이스 명에도 타입 파라미터를 표시해주어야 한다
      
    - 상속받은 클래스는 제너릭 클래스가 되고 각각 상속이나 구현하는 타입 파라미터를 표시해준다

        - 자식 클래스에서 타입 파라미터를 추가할 수도 있다.
        
    - GIGfoo<K>는 자녀 제네릭 클래스에 타입파라미터
              
    
        - 타입 파라미터를 표시할 때는 각각의 파라미터를 독립적인 타입 파라미터로 설정 할수 있다
          
        - 혹은 각각의 파라미터를 하나의 타입 파라미터로 통합 시킬 수 도 있다
          
            ```java
            class IGIFooTwo<T> extends GFoo<T> implements IGFoo<T>{
            }
            ```
    
    - 부모 클래스/ 인터페이스들의 파라미터를 통합하여 동일한 타입 파라미터로 넘겨줄 수 있다
      
      ​    

### 제너릭 타입 제한
```java
// extneds를 이용해서 부모 클래스와 인터페이스도 제한할 수 있다.
{//Number는 추상클래스를 상속하고 있는애들만 사용할 수 있음
                                                          //요 추상클래스도 상속하고 있으며 요인터페이스도 구현해야한다
class GenericTypeLimitation<T extends Number & Cloneable >{
    GenericTypeLimitation<Number> genericTypeLimitation = new GenericTypeLimitation<>();
}
}
```
- 제너릭 클래스의 타입 파라미터 뒤에 extends를 하여 타입 파라미터에 올 수 있는 타입을 제한할 수 있다.
    - 생략시 extedns Object를 하는 것과 동일하다 (Object 는 모든 클래스의 부모 클래스이기 때문)

        ```java
        class GenericNoTypeLimit<T extends Object>{} // == class GenericNoTypeLimit<T>{}
        ```

        

    - extends 뒤에 클래스명 작성시 해당 클래스를 상속하고 있는 클래스만 타입으로 입력할 수 있다(본인포함)

        ```java
        class GenericTypeLimitation<T extends Number >{
             GenericTypeLimitation<Number> genericTypeLimitation = new GenericTypeLimitation<>();
            }
        ```

        - Number 클래스와 Number클래스를 상속하는 자식 클래스들만 자료형으로 입력할 수 있게 된다

        

    - 타입을 제한할 클래스와 인터페이스를 동시에 사용할 수 있다 

        ```java
        class GenericTypeLimitation<T extends Number & Cloneable >{
        }
        ```

        - &를 통해서 앞에 클래스를 쓰고 뒤에 인터페이스를 사용한다
        - 앞에 클래스를 상속하고 뒤에 인터페이스를 구현한 클래스로 제한한다는 뜻이다.
        - 클래스와 인터페이스는 독립적인 요소로 아무 클래스나 해당 인터페이스를 구현하여 사용할 수 있다.
            - 위의 예는 Number을 상속하고 Coneable을 구현한 클래스로 제한한다

              

## 제너릭 메소드
- 제너릭 메서드는 클래스에 타입파라미터를 사용한 것이 아닌 메서드에 타입파라미터를 선언한 것이다
  
    ```java
      public <T> T method(T x){
            return x;
        }
    ```
    
    - 접근제어자 뒤에 타입파라미터 작성해서 반환과 파라미터 다 활용할 수 있다.
    
      - 반환과 입력파라미터 둘 중 하나에 만 활용해도 된다
      
      
    
- 제너릭 메서드의 타입파라미터는 클래스 타입파라미터와 별개이다

    ```java
    class foo<G>{
        public<P> void test(P p){
        }
    }
    ```

    - 제너릭 클래스와는 다르게 제너릭 메서드에서는 타입 파라미터를 자료형으로 선언할 수 있다
    - 이는 제너릭 클래스와는 다르게 타입 파라미터를 메서드 자체에서 입력할 수 있기 때문이다

         

- 제네릭 메소드는 타입파라미터 자료형을 따로 정하지 않고 오버로딩 된 것 처럼 Arguments에 입력하면 자동으로 해당 자료형을 설정한다
  
    ```java
    public class Generics {
        public static void main(String[] args) {
            System.out.println(GenericMethod.staticMethod("abcd"));//abcd
            System.out.println(GenericMethod.staticMethod("1234"));//1234
    }
    }
    ```
    
    - Integer와 String 자료형에 상관없이 값을 입력하면 자동으로 자료형이 결정되어 실행된다
    - 이는 제너릭 메소드에서 동적바인딩이 사용되고 있음을 뜻한다

### 정적바인딩, 동적바인딩에 관한 간략한 설명..

- 제너릭 메서드는 자료형이 정해져있지 않기 때문에 런타임때 정의 되어 지는 동적바인딩을 사용한다
  
    - 자바파일을 바이트코드로 변환하는 컴파일일 과정에 자료형이 정의 되는 것을 정적바인딩이라고 한다
    
    - 컴파일된 파일을 실제 동작하게 하는 런타임 때 메서드가 정의되는 것을 동적 바인디이라고 한다
    
      
    

## 와일드 카드

- 와일드카드란, 제네릭 클래스의 객체를 메소드의 매개변수로 받을 때, 그 객체의 타입 변수를 제한하는 것을 말한다

    ```java
    class WildGeneric<T>{
    
    }
    class WildCard{
        public void method1(WildGeneric<?> x){}
        public void method1_eq(WildGeneric<? extends Object> x){} 
    ```

    - 와일드드카드는 입력받을 클래스명뒤에 타입파라미터 자리에 ? 입력하여 표현할수 있다
      
    - <?>는 <? extends Object>와 같은 뜻이다 = 모든 클래스를 타입파라미터로 할 수 있음

      

- <? extends "class"> 타입파라미터를 class를 조상으로 두고 있는 클래스를 뜻함 (본인 포함)

    ```java
    class WildFoo{
    
    }
    
    class WildBar extends WildFoo{
    
    }  
    class WildGeneric<T>{
    
    }
    public void method2(WildGeneric<? extends WildFoo>  x){} 
    ```

    - extends WildFoo 는 WildFoo를 조상으로 두고있는 클래스로 제한하는 것을 뜻한다 (WildFoo 포함)
      
        - WildFoo, WildBar만 WildGeneric의 자료형이 될 수 있다
        
          

- super 키워드는 해당 class를 조상으로 두고 있는 클래스를 뜻함 (본인포함)

    ```java
    class WildFoo{
    
    }
    
    class WildBar extends WildFoo{
    
    }  
    class WildGeneric<T>{
    
    }
     public void method3(WildGeneric<? super WildBar>  x){} 
    
    ```

    -  super WildBar는 반대로 WildBAR를 자식으로 하는 부모클래스들을 뜻함 (WildBar 포함)
        - WldBar, WildFoo, Object가 WildeGeneric의 자료형이 될 수 있다.

- 와일드 카드 대신 특정 자료형을 사용하는 것도 가능하다

     ```java
     public void method4(WildGeneric<String> x){}
     ```
- 실행부

  ```java
  public class WildCardTest {
      public static void main(String[] args) {
          WildCard wildCard = new WildCard();
          WildGeneric<String> wildGeneric = new WildGeneric<String>();
          wildCard.method1(wildGeneric); //<?>이기에 아무 자료형이나 가능
          wildCard.method4(wildGeneric); //String 이기에 가능
          // wildCard.method2(wildGeneric); // String이기에 불가능
      }
  }
  ```

  

  

## 제네릭 클래스 정리

- 제네릭의 문법은 많지만 하나 하나의 이해보다는 제네릭으로 이루어진 도구들을 사용하는 방법을 아는데 중점을 두고 공부해야 한다


