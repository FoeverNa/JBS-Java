# 예외 처리(Handling Exception)



## 	오류와 예외

### 오류(Errors)

- 메모리 부족 또는 프로그램 실행이 꼬이는 경우
    - 재귀함수잘못써서 스택오버플로우가 발생한다든지..
- 프로그램이 동작 중에 비정상 종료가 발생하는 상황
- 더 이상 어떻게 프로그램을 복구해야 할 지 알 수 없는 경우
- 프로그램의 문제를 해결하여 해결 한다 ( 프로그램 종료 후 버그픽스를 해야 한다 )
    -  코드를 고치라는 말과 같다
    - 오류가 나면 프로그램이 종료되는게 맞고 그걸 계속 무시하고 실행하게 하면 해킹의 위험 발생한다(해커들이 이런 취약성을 공략함)

### 예외 (Exception)

- 오류에 비해서 심각도가 낮고, 프로그램의 정상적인 흐름만 방해하는 현상
    - 파일을 읽으려 했으나, 해당 파일이 없음 -> 파일만 찾아주면됨
    - 네트워크 연결이 유실 -> 네트워크 리커넥션하면됨
    
- 문제 상황을 처리하는 로직을 구현하여, 런타임에서 자연스럽게 해결 가능 하다

 - 사소한 문제들을 유연하게 처리하여 사용자들이 불편함을 느끼지 않고 서비스를 이용할 수 있도로 해주는 기능
   
    - 특히 서버를 유지하는데 중요한 역할을 한다 
      - 외부 요청에 반응할 수 없는 Down Time을 최소화하는게 백엔드의 목표라면 예외처리는 그초석이라 할 수 있다
    
- 예외의 클래스는 Exception클래스이다
  
    - https://docs.oracle.com/javase/7/docs/api/java/lang/Exception.html
      
    - 그 하위 클래스들로 우리가 접할 수 있는 모든 예외 클래스들이 존재한다
    
        - 객체 지향프로그래밍은 예외마저도 객체로 만들어서어떻게하나부다...
    
        

#### 예외의 종류 2가지

- Checked Exception
  - Exception 클래스를 상속하고 있으면 Checked Exception 이다
  - 예외가 발생할 수 있으면 컴파일러에서 에러 발생 시킨다 - > try ~ catch 를 작성하지 않으면 아예 빌드조차 할 수 없다.(실행도 못함)
    - 무조건 try & catch 를 사용해야 되서 예외 처리를 배우고 나서 사용할 수 있다.
  - 체크가 되었다는게 아니라 우리가 체크를 해야한다는 뜻이다
  
 - Unchecked Exception  
   - RuntimeException 클래스를 상속하고 있으면 Unchecked Exception 이다
     - RuntimeException은 Exception클래스를 상속한다
   - try ~ catch 를 작성하지 않더라도 빌드와 실행이 가능하다
     - 그렇기 때문에 개발자의 경험과 역량을 통해 처리해야 되는 예외 영역으로 볼 수 있다
     - 예외처리가 잘될 수록 안전한 프로그래밍이 된다
   - 대표적으로 ArrayIndexOutOfBounds, ArithmaticException이 있다

## 예외 처리

- 예외가 발생헀을 경우에, 이 상황을 '감지'하고 '처리'하는 코드
    - 정상적으로 프로그램의 작동 유지되며 에러에 대한 log(기록)을 남기는게 주요 목적이다

    - try ~ catch, throws, thorw, finally 키워드들을 이용한다
      
        - Throwable 클래스를 상속하는 자식 클래스들로 이러한 문제를 해결한다
        
          - Throwable클래스를 Exception과 Error클래스가 상속하고 우리는 Exception에만 관심을 갖는다
        
        - Throwable 클래스의 주요 메소드
        
          | 메소드                                |                             설명                             |
          | ------------------------------------- | :----------------------------------------------------------: |
          | `public String getMessage()`          | 발생한 예외에 대한 메세지를 반환 => 예외가발생하면 콘솔창에 뜨는 메세지를 가져오는 메서드 |
          | `public String getLocalizedMessage()` | 오버라이드하여 추가 메세지 제공 (오버라이드하지 않으면 getMessage()와 동일) => 아래의 예가있다 |
          | `public Throwable getCause()`         |          예외의 원인이 되는 Throwable 객체 반환한다          |
          | `public void printStackTrace()`       | 예외가 발생된 메소드가 호출될 때의 Method call stack을 출력 => 어떤메서드에 몇번째줄에서 에러가발생했는지 상세히 표시됨 (우리가 맨날 보는 에러메세지의 긴줄) |
          
          - 우리가보는 예외 메세지는(에외처리를 하지않은) getMessage와 printStackTrace가 출력되고 우리가 주로 예외처리로 사용하게 될 메서드도 그 2가지 이다

## 예외 처리 기법

### try ~ catch 구문

```java
public class Exceptions {
    public static void main(String[] args) {
        // try ~ catch
        try {
            // 예외가 발생할 수 있는 코드 작성
            // 예외가 발생할 경우 예외 객체를 던짐(throw) 어디로?
        } catch (Exception e) { // 던져진 예외를 받음 (catch를 이용해서)
            // Exception 클래스 및 그 자식 클래스를 사용해서 받음(클래스였구나)
            // 에외상황을 처리하는 코드드
        }

//        int[] integers = new int[10]; // 0~9까지 되야되는데 20을넣으면 에러발생

        try {
            int[] integers = new int[10]; // 0~9까지 되야되는데 20을넣으면 에러발생
            integers[20] = 5;
        } catch (ArrayIndexOutOfBoundsException e) {// 위에서 에러명을 출력해봐서 입력할수 있음
            System.out.println(e.getMessage()); //Index 20 out of bounds for length 10
            // 객체 e에 메세지를 심어준것임. 그걸 message로보여줌
            //에러를 thorw해서 밑에서 catch를 해서 메세지로 출력해준상황
            e.printStackTrace();// 시뻘건 메세지도 출력해주는애
            // 근데 순서는 맨마지막에 출력됨 // IDE => 버그래, 순서가꼬임
            // 콘솔에서 출려하면 이런에러 안뜬데, 에러자연스럽게 받아들이면됨
        }
       System.out.println("Programm ended normaly");
        // 에러가 있었음에도 프로그램이 에러가 나지 않고 끝까지 실행되서 출력문까지 출력되고 종료
}
}
```

- try {}안에 예외가 발생할 수 있는 코드를 작성 한다
  
    ```java
           try {
            }
    ```
    
    - 예외가 발생할 경우 예외 객체를 던진다(throw)
        - 예외가 발생할 경우 해당 코드는 실행되지 않고 Throwable 클래스를 상속하는 자식객체(혹은 Throwable자체)를 만들어서 객체를 throw한다(던진다)
            - 여러개의 에러가 발생할 구문이어도 처음 예외가 발생한 시점에서 예외처리가 된다  
    - 예외가 발생하지 않을 경우 정상적으로 try코드가 실행되고 catch 구문은 실행되지 않는다
    
- catch()안에 Throwable의 자식클래스들을 나열하고 {}안에 발생시 어떻게 처리해줄지 적는다

    ```java
     catch (Exception e) {
            }
    ```

    - try에서 발생한 예외가 (Exception e)에 예외와 동일한 클래스(혹은 자식클래스)라면 변수 e에 담고 {}안에 수행문을 수행한다 

        - 작성된 예외명과 다른 예외가 발생할 시 예외처리가 되지 않고 그냥 예외가 발생한다(프로그램이 종료된다)

        - {]안에 수행될 수행문에는 Throwalbe의 주요 메서드를 사용할 수 있다

            - getMessage()는 에러에 대한 자세한 메세지를 반환 한다
            - toString()는 에러에 대한 짧은 설명을 반환 한다
            - printStackTrace()는 오리지널 에러 메세지를 출력해 준다(빨간색 에러메세지)
                - printStackTrace()는 어디에서 실행되던 맨마지막에 출력되는 버그가 있다(IDEA에러 라고 한다)

            

- try~ catch 구문을 통해 예외 처리시 예외가 발생해도 프로그램이 종료되지 않고 끝까지 수행된다

    - 이렇게 끝까지 프로그램이 수행되게 하는 것이 예외처리이다.

    - try ~ catch 구문 바깥에 출력문을 작성하고 예외가 발생해도 출력문은 실행이 된다 

        

### 다중 예외 처리

```java

public class Exceptions {
public static void main(String[] args) {        
//        try {
//            // 아주아주 예민한 내용이 실행되는 부분
//        } catch(ArithmeticException e){// 에러명은 랜덤으로 입력하는 중
//                                        // 첫번째 캐치
//
//        } catch(FileAlreadyExistsException e){ // 첫번째 에러가 아니라면 두번째 케치
//
//        } catch(EOFException e){
//
//        } catch(IOException e){
//
//        } catch(Exception e){ // 나머지 모든 Exception을 모두 catch
//                                // 모든 Exception 객체의 조상
//     
//        }
}
}
```
- 여러개의 catch 구문을 사용하면 다중으로 예외처리를 할 수 있다
    - switch~case 문 처럼 첫번째 에러 확인하고 아니라면  그 다음 예외 확인하는 방식이다
    - 예외처리가 되면 아래 나열된 에러는 고려하지 않는다(break;로 조건문 빠져나가듯이)

- 특정 catch 구문에 선택되는 조건은 다형성에 의해 결정 된다
    - catch 하고 있는 예외의 자식 객체면 예외를 catch 할 수 있다
        - 부모클래스가 먼저 예외처리 될시 자녀 클래스의 예외는 처리될 수 없다
            - 부모클래에 예외에서 자녀 클래스의 예외까지 처리해버리기 때문에
                - 그렇기 때문에 부모클래스의 예외를 자식클래스의 예외보다 먼저 처리하면 컴파일 에러가 발생한다

- Exception 클래스는 모든 예외 클래스에 부모클래스로 사용하면 모든 예외를 모두 catch 할 수 있다
  
    - switch case의 default나 if else의 else과 비슷한 역할 이다
      
    - 하지만 어떤 예외가 발생하는지 모르고 예외처리를 하는것은 위험한 방식으로 사용하길 권장하지 않는다 
        - 항상 어떤 예외가 발생하는지 밝혀진 코드들만 예외처리를 해야 한다
          
          

### try ~ catch ~ finally 구문

```java
public class Exceptions {
public static void main(String[] args) {     
        try {
            int[] integers = new int[20];
//            integers[21] = 10;
            System.out.println("던졌다?"); //
        } catch (Exception e) {
            System.out.println("받았다!"); // 받았다
        } finally {
            System.out.println("마침내!"); // 마침내!
            // try에서 생성한 리소스를 회수하기 위해

        }

        FileInputStream file = null;
//        file = new FileInputStream(("a.txt"));
//        file.read(); // 두번오류 발생할 수 있음
}
}
```
- try~catch 구문에 finally 구문을 연결한 형태로 어떤 조건하에서도 반드시 실행되어야 하는 구문을 작성하는데 사용한다
    - 예외가 발생하였을 때도 실행되고 예외가 발생하지 않은 상황에서도 실행되는 구문이다
    - 보통 try 구문에서 접근한 System 자원을 안전하게 복구하기 위해서 사용된다 => 아래에 자세히 설명
        - FileInputStream 같이 파일을 읽어오는 과정에서 에러가 발생할시 close 를 해주어야 할 필요가 있을때 사용한다

#### close() 하는 이유

입출력 스트림은 대표적으로 외부의 파일을 읽고 쓰기 위해서 사용하는데 한번에 그내용을 모두 메모리에 올리기 부담스러우니 스트림이라는 **연결통로**를 연결하여 언제든지 원하는 부분의 원하는 만큼 내용을 읽어들일수 있도록 한다 그런데 이 연결이라는 **스트림은 시스템 자원에서 제공한 것**으로 **시스템 자원의 낭비를 막기 위해선** 사용 후 close()를 통해 닫아주어야 한다. 추가로 스트림이 열려있으면 메모리가 낭비되는 부분도 있지만 열려있는 file에 **리소스를 점유해버리는 문제**도 발생할 수 있다. 어떤 프로그램이 열려있을 때 이름을 수정하거나 삭제하는 일이 안되는 경험처럼 해당 파일에 접근이 안되서 또 다른 문제가 발생할 수 도 있기 때문에 close()를 해주어야 한다



### try ~ with ~ resources 구문

```java
public class Exceptions {
    public static void main(String[] args) {
       //예시1
        try {
            file = new FileInputStream(("a.txt"));
            file.read(); // 두번오류 발생할 수 있음 => 어디서 두번 오류발생한다는거지?
        } catch (IOException e) {
            System.out.println("파일 처리 실패");
        } finally {
            if (file != null) {
                try {
                    file.close(); // 이것도 꼭 해줘야하는 이유가 checked exception 있기 때문
                } catch (IOException e) {
                    System.out.println("앗!.. 아아..");
                }

            }

        //예시2            
        try(FileInputStream file1 = new FileInputStream("a.txt")){
            file1.read();
        } catch (IOException e){
            System.out.println("파일처리실패");
        }
            System.out.println("Programm ended normaly");
        }
    }
}
```
- resource 를 회수하기 위해 finally 구문을 사용하는 경우 예시 1 처럼 중첩해서 try ~catch 구문을 사용 해야 되서  코드가 길어 진다
    - 이런 경우 예시2 처럼 try ~ with ~ resource 구문을 사용하여 구문을 짧게 줄일 수 있다

        - Java 1.7에서 추가된 기능으로 AutoClosable 인터페이스를 구현하는 리소스에서 close()를 자동으로 해주는 기능이다 

        - try(괄호안에 객체를 생성하고){다른 괄호 안에 예외가 발생할 수 있는 코드를 입력하여 사용할 수 있다}

          - close를 사용하지 않아도 자동으로 close를 해주고 그에 따라서 try ~ catch도 하나 줄어든다

            

    

## 예외 위임 처리

### Checked Exception의 위임 처리

```java
// throws 키워드를 이용하여 예외 처리 위임
// methodA의 예외를 methodB로 넘겨서처리함
class checkedExceptionThrow {
    void methodA() throws IOException { 
        FileInputStream file1 = new FileInputStream("a.txt");
        file1.read();
        file1.close();
    }

    void methodB() {//얘도 throws할수도 있고 직접 try catch할수도 있음
        try {
            methodA();
        } catch (IOException e){
            System.out.println("메소드에이실패"); // 처리를 methodB로 위임함
                                                // close()에대한 처리 없음으로 지금 방식은 권장X
        }
    }
}
```
- throws 키워드를 이용하여 직접 예외 처리하는 것이 아닌 메소드를 호출하는 곳으로 위임해서 예외처리를 할 수 있다
    - 메소드의 ()입력 파라미터 옆에 throws 키워드를 사용하고 예외명을 명시하여 사용한다
        - 메소드가 throw's' 한다는 동사로 사용된 키워드이다
        - 이 메소드는 해당 에러를 발생시킬 수 있다는 것을 명시해주는 것이다
    - 호출하는 메소드에서 try 구문안에 던진 메소드를 사용하고 catch 구문에서 예외를 핸들링 해준다.
- 어디서 예외처리를 할지는 예외처리에 목적에 맞게 사용하면 된다 (객체 지향적인 구현)
    - 하나의 메서드에서 처리할 수도 있지만 목적에 맞게 예외가 발생할것을 알려주는 것, 예외를 처리하는 곳 등을 목적에 따라 분리한다
### Unchecked Exception 의 위임 처리

```java
class UncheckedExceptionThrow{
    void methodA(){ // 언첵트익셉션은 throws안해도 알아서 위임됨
        int x= 10/0; //아리스매틱익셉션 // 런타임익셉션
                        //빨간줄이 안뜨는 에러기때문에 어디서 해도상관이없으니 까 안뜨는거래
                        
    }

    void methodB() { // 여기서해도 아래에서 해도 되서 에러가안뜸
        methodA();
    }

    public static void main(String[] args) {
        try{
            new UncheckedExceptionThrow().methodB();
        } catch (ArithmeticException e){
            System.out.println(e.getMessage()); /// by zero
            
        }
    }
}
```

- Unchecked Exception 의 경우에는 throws 키워드를 사용하지 않아도 위임이 된다
  
    - checked Exception 과 예외가 있더라도 컴파일과 실행까지 되기 때문에 예외처리를 어디에서 하던 상관이 없다
    
- 예외 처리할 곳에서 try ~catch구문 통해 에외 클래스와 처리할 방식을 입력해주면 된다

    

### 메소드를 오버라이드한 경우의 예외 처리


```java
class Foo{
    void methodA() throws IOException{}// Checked Exception
}

class BarOne extends Foo{
    void methodA() throws IOException {} // possible
}

class BarTwo extends Foo{
    void methodA() throws FileNotFoundException{}// 더 자식 Exception은 possible
    // FileNOtFoundException은 IOException의 자식 클래스이기 때문에 가능함
}

class BarThree extends Foo{
//    void methodA() throws Exception {}

}

```

- 부모 클래스의 메소드를 오버라이드 할 때, 부모 클래스의 메소드의 예외보다 더 조상인 예외는 thorws 할수 없다
    - 부모 클래스의 예외가 IOException 이라면 그보다 자식클래스인 FileNotFoundException 은 오버라이드한 자식클래스에서 사용 가능 하다
    - 하지만 IOException 보다 상위클래스 예외인 Exception은 자식클래스의 오버라이드된 메서드에서는 사용 할 수 없다
    
- 이는 오버라이드된 메서드에 일관성을 부여하는 것으로 예외 클래스의 상속관계가 이루어진다면 메서드들이 어느정도 비슷한 기능을 하게 되기 때문이다
  
    - 이러한 방식은 Java 의 선택적인 구현으로 Java 에 성격을 보여준다
    
      

#### 예외 위임 처리를 하는 이유(추가조사)

- 메서드의 선언된 thorws문을 통해 해당 API를 사용했을 때 어떤 예외가 발생할 수 있는지를 예측할 수 있다
- 현재 메서드내에서 예외를 처리할 필요가 없다고 판단하였을 때
-  API를 사용하는 다른 개발자에게 예외처리의 자율성을 주기 위하여
  - 같은 예외라도 개발자에 따라 다르게 처리할 수 있기 때문에
- 그외에 팀의 정책, 플랫폼 환경 등 다양한 이유로 예외를 위임 처리 한다



### throw

- 예외를 발생시키는 키워드

  - 기존의 예외 상황이 아니지만 예외가 발생해야 된다고 판단했을 때 예외를 발생시킬 수 있는 키워드

  - 기존의 예외 클래스를 이용할 수 도 있고 새로운 사용자정의 예외클래스를 만들어서 사용할 수도 있다
    - 예1 : 숫자를 0으로 나누어서 발생하는 ArthmeticException을 숫자를 5를 나누었을 때도 발생시킬 수 있음
    - 예2 : 로그인 상황에서 비밀번호가 틀렸다면 로직으로 처리할 수 있지만 그상황 자체를 예외로 취급하고 사용자 정의 예외를 만들어서 예외를 발생시키고  try~ catch 문을 통해 예외를 처리할 수 있다 
  - throw를 하지 않고 해당 예외상황을 조건문을 통해서 처리를 할 수 도있지만 예외를 발생시키는 이유는 위에 thows에서 처럼 기능을 분리하는 하는 이유와 같다

- new 키워드로 새 Exception 객체를 생성하여 예외 내용을 작성

  ```java
  void exceptMethod() throws Exception {
      ...
      if (Err) {
          throw new Exception("Some Error"); // 예외 발생 및 Message 전달
      }
      ...
  }
  ```



## 사용자 정의 예외 (Custom Exceptions)

- Exception 또는 RuntimeException 클래스를 상속하여 작성

  - RuntimeException은 Exception의 자녀클래스로 RuntimeException을 구현할때 사용되는 클래스이다
  - Exception을 상속한경우 Checked Exception이 되어 반드시 예외를 처리해야 한다.

  ```java
  class MyException extends RuntimeException {//Runtime 상속했으니 Unchecked Exception이 된다
      enum ErrorCode { // Enum이용해서 예외내의 에러코드들을 작성해준다
          ERROR_A, ERROR_B;
      }
  
      private ErrorCode errorCode;// Errocode 저장할 수 있게 변수만들어주기
  
      public MyException(ErrorCode errorCode, String message) {// 원래는 message만 초기화하는게 기본// 예제에서는+@로 errocode도 표시되어잇음
          super(message);
          this.errorCode = errorCode;
      }
  
      @Override
      public String getLocalizedMessage() {// 위에서 봤던 geLocalizedMessage를 재정의한 것
          String message = getMessage();// 그냥 getter메서드 
          ...
          return localizedMessage;// 재정의한 에러메세지를 담아줌 // 기본은 그냥 getMessage해주는것
      }
  }
  ```

