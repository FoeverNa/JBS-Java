# Wrapp 클래스(Wrapper class)

- 기본 자료형 값을 객체로 쓰기 위해 있는 클래스

- 기본형 타입이 허용되지 않는 문법에 기본형 타입을 쓰기 위해서 사용 된다
    -  대표적으로 제너릭에 타입파라미터에는 기본형 타입을 입력할 수 없다
    
- 다음과 같이 기본형 자료형에 대응하는 클래스들을 제공하고 있다
> byte -> Byte
>
> char -> Character
>
> short -> Short
>
> int -> Integer
>
> long -> Long
>
> float -> Float
>
> double -> Double
>
> boolean -> Boolean

## Wrapp 클래스 객체 생성

```java
 public static void main(String[] args) {
        Integer integer = new Integer(10); // 클래스니까 객체생성가능, 객체의 초기화값 입력하는방식
        Integer integer1 = Integer.valueOf(10); // Integer 클래스에 valueof 라는 스태틱메소드를 사용                                     
        Character character = new Character(('d'));
        Character character1 = Character.valueOf('f');

        // Autoboxing
        Integer integer2 =4; //바로 입력가능
```

1. new 키워드를 활용하여 객체 생성

2. valueOf()메서드를 활용하여 객체 생성 가능하다

   2.1 Wrapper클래스에 valueOf 정적 메소드를 활용한다

3. 기본 자료형 값을 바로 입력하여 객체 생성

   3.1 Autoboxing이 적용되서 기본자료형 값을 객체로 생성해서 입력해준다

   3.2 Wrapper클래스의 편의 기능

## Autoboxing  &  Unboxing

- 기본형 값을 Wrapper 클래스의 객체로 자동으로 변환시켜주는 것을 Autoboxing 이라고 한다

- 반대로 Wrapper 클래스의 객체를 자동으로 기본형 값으로 변환 시켜주는 것을 Unboxing 이라고 한다

  ```java
  public class WrapperClass {
      public static Integer add(Integer x, Integer y){ // Autoboxing
          return x+y; // Unboxing => 객체끼리 덧셈이 안되는데 자동으로 기본자료형으로 변형되서 계산
                      // return될때는 다시 출력형인 Integer로 Autoboxing이 이루어짐
      }
       public static void main(String[] args) {
           System.out.println(add(4,2)) // 6
       }
  }
  ```

  1. add의 입력된 int형 자료를 Autoboxing하여 Integer 객체를 생성해서 add의 파라미터 변수에 입력 한다

  2. 입력된 객체를 Unboxing하여 x+y의 연산을 한다

  3. 연산된 값을 다시 Autoboxing 하여 integer 객체로 반환한다

     

## 제너릭 클래스의 활용

###   제너릭 타입 파라미터에 활용

- 제너릭 타입 파라미터에는 참조형 자료만 올 수 있고 기본 자료형은 올 수 없지만 Wrapper 클래스를 활용하여 기본 자료형을 입력할 수 있다
  
    ```java
   public class WrapperClass {
        public static <T> T bypass (T x){
            return x;
        }
        public static void main(String[] args) {
            bypass(5); //5 // autoboxing 
    }
    
   ```
   
    - 메서드의 값을 입력하면 자료형을 자동으로 Autoboxing 해주어 해당 기본형의 객체를 생성해준다
      - 5는 기본자료형의 값으로 원래는 bypass()에 Arguments로 입력할 수 없다
      - 하지만 Wrapper class가  Autoboxing을 해주어 new Integer(5)로 객체를 생성하여  제너릭 메서드에서 사용할 수 있게 된다
   

### Wrapper 타입의 값 비교

- Wrapper 타입은 객체이므로, ==를 이용하여 값을 비교할 수 없다.

  - equals()메서드를 사용해서 비교한다

  ```
  Integer intOne = new Integer(100);
  Integer intTwo = new Integer(100);
  
  System.out.println(intOne == intTwo); // false
  System.out.println(intOne.equals(intTwo)) // true
  System.out.println(intOne == 100) // true (Unboxing)
  ```



### 문자열  <-->  기본자료형

- 문자열로 입력된 값을 기본자료형으로 활용해야 되는 경우 Wapper클래스를 통해 변환할 수 있다
    - Wrapper 클래스의  parse메서드를 활용하는 방법(제일 권장되는 방법)

        ```java
        int x = Integer.parseInt("100"); // pare+자료형 정적 메소드
        ```

    - 기본자료형에 Wrapper 클래스 객체를 넣어서 Unboxing 하는 방법
      
        ```java
        int y = new Integer("100"); 
        ```
        
    - Wrapper 클래스+ valueOf 메서드 활용하기   

        ```java 
         int z = Integer.valueOf("200");
        ```

        