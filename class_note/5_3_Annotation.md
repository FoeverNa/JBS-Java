# 애노테이션(Annotaions)

## 애노테이션이란

- 애토테이션의 사전 의미는 주석
- JVM, 컴파일러, 프레임워 등에서 사용 할 수 있도록 전달하는 메타데이터로 사옹된다
  - 메타 데이터 : 데이터에 대한 데이터, 다른 데이터를 설명해 주는 데이터
- @Override, @Deprecated, @SuppressWarnings, @functionalInterface

## 기본 애노테이션

| 애노테이션           | 설명                                            | 비고                            |
| -------------------- | ----------------------------------------------- | ------------------------------- |
| @Override            | 상속하여 오버라이드된 메소드                    |                                 |
| @Deprecated          | 앞으로 사라질 예정임을 표기                     |                                 |
| @SuppressWarnings    | 컴파일러에게 특정 경고 메세지를 무시하도록 한다 | eg. @SuppressWarnings("unused") |
| @FunctionalInterface | 함수형 인터페이스임을 표기(Lamda)               |                                 |

## 에노테이션의 구성요소

- 에노테이션의 작성

  - 추상 메소드와 유사한 형태로 구현 된다

    ```java
    @Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE}) // 대상 설정
    @Retention(RetentionPolicy.SOURCE)//Retetin을 설정하는 부분
    public @interface SuppressWarnings {// 앞에 @를 붙여 선언한다
    	String [] value();
    }
    ```

- 에노테이션 사용

  - key=value 형태로 애노테이션에 속성의 값 전달

    ```java
    @SuppressWarnings(value = {"unsed", "rawtypes"}) // 키 = 값 배열
    @SuppressWarnings(value = "unsed") // 값이 하나인 경우 배열 생략 가능
    @SuppressWarnings({"unsed", "rawtypes"}) // 속성이 value 하나인 경우 키 생략 가능
    @SuppressWarnings(key1 = "value1", key2 = {"value2", "value3"}) // 속성이 여러개인 경우 키를 여러개 사용 가능
    ```

- Target 설정

  - 애노테이션을 사용할 수 있는 대상을 설정한다

  - ElementType 열거형 상수 사용하여 설정한다

    | `ElementType`   | 범위                                                    |
    | --------------- | ------------------------------------------------------- |
    | TYPE            | 클래스, 인터페이스, 애노테이션, 열거형에 사용할 수 있다 |
    | FIELD           | 필드(멤버 변수), 열거형 상수(얘도 사실 변수)            |
    | METHOD          | 메소드                                                  |
    | PARAMETER       | 메소드의 파라미터                                       |
    | CONSTRUCTOR     | 생성자                                                  |
    | LOCA_VARIABLE   | 로컬 변수                                               |
    | ANNOTATION_TYPE | 애노테이션                                              |
    | PACKAGE         | 패키지                                                  |

- Retention 설정

  - 애노테이션 정보를 언제까지 유지할 것인가를 결정하는 Policy

  - RetentionPolicy 열거형 상수 사용 한다

    | `RetentionPolicy` | 범위                                                         |
    | ----------------- | ------------------------------------------------------------ |
    | SOURCE            | 컴파일러가 사용한다( .java->.class로 변경하는 과정에서 사용되지 않는다) |
    | CLASS             | 컴파일러도 사용하고 .class에도 포함이 된다. JVM에는 사용되지 않는다 |
    | RUNTIME           | .class에 포함되고 JVM에도 올라와서 Reflection API에서 사용 가능하다 |

  - Reflection API에서 사용한다는 것은 Runtime에서 사용한다는 것이다

    - Source나 Class는 우리가 사용할 수 없기 때문에 신경쓸 필요가 없다
    - Runtime만이 우리가 커스텀해서 사용하는 것에 영향을 주기에 관심가져야 한다

## 사용자 정의 애노테이션

- 사용자 정의 애노테이션 구현

```java
/**
 * - 멤버변수를 대상으로 하는, Reflection API에서 쓸 수 있는 애노테이션
 * - 애노테이션 속성은 String[]인 value와 "기본값" 기본값을 가지는 valueTwo로 이루어진다
  */

@Target(FIELD)
@Retention(RetentionPolicy.RUNTIME) // 우리가 뭔가해보기 위해선 런타임으로 해야된다
@interface MyAnnotation {// 여기까지 하면 기본구색 완성
    String[] value(); // 애노테이션 속성(기본 생성 이름은 valeu) => 첫번째 속성은 value가 되는게 좋다
    String valueTwo() default "기본값"; // default = 사용하지 않았을 때 기본적으로 들어가는값
}
```

- 사용자 정의 애노테이션 사용

```java
class CustomAnnotationUsage {
    @MyAnnotation("game")// valueTwo는 기본값이 있어서 넣지 않더라도 괜찮지만 기본값이 없으면 하나라도 값을 입력해야 한다
    String gameName = "여러분의 틱택토";
    @MyAnnotation(value = "server", valueTwo = "localhost")
    String ServerIP ;
    @MyAnnotation("game")
    String gameMode = "AI vs AI";
    @MyAnnotation(value = "server", valueTwo = "8888")
    String serverPort;
    @MyAnnotation(value = "db", valueTwo = "localhost")
    String database;

}
```

- Reflection API를 이용하여 애노테이션에 할당된 값을 사용하기

  ```java
   public static void main(String[] args) throws IllegalAccessException {
          AnnotationUsage obj = new AnnotationUsage();
          Map<String, Object> gameProp = new HashMap<>();
          Map<String, Object> serverProp = new HashMap<>();
          Map<String, Object> dbProp = new HashMap<>();
  
          Field[] fields = AnnotationUsage.class.getDeclaredFields(); // 필드 정보를 가져오는 부분(Reflection API 이용)
          for (Field field : fields) {
              MyAnnotation annotation = field.getDeclaredAnnotation(MyAnnotation.class); // 필드에서 Annotation 정보 가져오는 부분(Reflection API)(멤버이름, 값정도)
              if (field.get(obj) == null) {// 필드 값이 비어있는 경우 valueTwo에서 가져온다
                  field.set(obj, annotation.valueTwo());
              }
  
              if (annotation.value()[0].equals("game")) {
                  gameProp.put(field.getName(), field.get(obj));
              } else if (annotation.value()[0].equals("server")) {
                  serverProp.put(field.getName(), field.get(obj));
              } else {
                  dbProp.put(field.getName(), field.get(obj));
              }
  
          }
          System.out.println(gameProp);
          System.out.println(serverProp);
          System.out.println(dbProp);
          
  ```









