# JavaDoc 주석



## JavaDoc 주석이란
- Java 클래스 및 패키지를 문서화 하기 위한 주석

- 클래스의 용도와 사용법 등을 설명하는 규칙

- 자동으로 JavaDoc 문서를 생성할 수 있음

  

## JavaDoc 작성 방법

```java
/**
 * 클래스에 대한 설명. 이 클래스는 자바독 클래스로 자바독을 공부하기 위해...
 * @author Young Na
 */
public class JavaDocs {
    /**
     * 멤버 변수에 대한 설명을 적을 수 있습니다.
     */
    int memberInt;

    /**
     * 멤버 변수 또 다른 것도 설명을 적을 수 있습니다.
     */
    String memberString;

    /**
     * 생성자에 대한 설명입니다.
     * @param memberInt 입력1입니다.
     * @param memberString 입력2입니다.
     */
    public JavaDocs(int memberInt, String memberString) {
        this.memberInt = memberInt;
        this.memberString = memberString;
    }

    /**
     * 메소드에 대한 설명을 여기에 적습니다.
     * @param string 입력 인자 (파라미터)에 대한 설명을 적습니다.
     * @return 리턴값에 대한 설명은 여기에 적습니다.
     */
    public int methodA(String string){
        return string.length();
    }

    public static void main(String[] args) {

        JavaDocs j = new JavaDocs(1,"string");
        //클래스명에 커서 올리면 javadoc적은게 뜸
      
//        int a = j.methodA();

    }
}
```
- 클래스 / 메서드 /변수 바로 위에 /** */을 활용하여 주석을 작성 한다

- 주석을 작성한 클래스와 메서드명 위에 마우스 커서를 올리면 미니박스에 작성된 주석이 뜬다

- Menu - Tool - Generate JavDoc를 선택하면 html형태로 만들 수 있다
