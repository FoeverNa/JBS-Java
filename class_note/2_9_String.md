# 문자열 (String)



- 문자열은 내부적으로 '클래스'로 구성되어 있다.

  - 특정한 문자열은 객체가 된다

- 내부에는 문자 배열로 된 데이터가 있다. char[]

- 한번 만든 문자열은 변하지 않는다. (Immutable)// 이뮤터블

  - 재사용이 가능한 장점때문에 이뮤터블하다
  - 같은 문자열이면 객체를 생성하지 않고 해당 객체를 참조 한다

- 문자열 편집은 String을 쓰지 않고 StringBuilder나 StringBuffer 등을 사용한다.

  - 둘다 String을 편집하게 할 수 있는 클래스
  - 기본적으로 Builder를 사용하고 멀티 스레드일때 Buffer을 사용한다
  
  

## String의 immutable 특성


```java
public class StringTest {
    public static void main(String[] args) {
        String s = "안녕하세요";
        String s1 = s;
        s += " 슈가에 아유미에요";
        
        System.out.println(s); // 안녕하세요 슈가에 아유미에요
        System.out.println(s1); // 안녕하세요 // 원래 String s는 변하지 않는다//immutable
    }
}
```
- String 변수에 + String을 더 해주면 새로운 String 값이 출력되지만, 원래 있던 String 변수의 값이 변한 것이 아닌 새로운 String 리터럴이 생성되서 입력되는 것이다.
    - String s에 + String 값을 더해주면 s는 s+String값의 결과를 출력하지만 original s를 담은 s1의 값은 변하지 않는다.
    - 보통의 참조 변수는 s의 값을 변하면 s를 참조하는 s1의 값도 변하지만 String은 변하지 않는 특성을 가졌다.




## 문자열 생성

- String 변수명 = "값";을 통해 생성
- 클래스이기에 new 생성자 사용해서 생성할 수 있지만 권장하지 않음(힙메모리중 young메모리에 생성됨 )
- String은 String 풀 에 생성되고 중복된 문자를 다른 변수로 생성할 때 해당 값을 참조해서 사용

- 힙메모리에는 Young memory(nursery?)와 Old memonry가 있음
    - 비교적 최신에 들어온것은 young memory에 생성되고 그후 오래 생성되어있으면 old memory로 이동됨
    - String은 값을 생성시 old memory에 string풀에 생성됨// 
    - 그 후 다른 값 입력시 string 풀에서 찾아서 사용하고 없면 새로 생성함
    - new 로 객체생성시 youngmemory에 생성됨 => 권장하지 않는이유..의미가없음
      - 같은 문자열 객체가 있더라도 새객체가 생성된다 
    
    

```java
        String s1 = "문자열 생성 방법"; // 보통의 생성 방법, String 변수명 = "값";
        String s2 = new String("문자열 생성 방법2"); // 클래스 생성자. 권장하지 않음

        String s3 = "abcde"; // String 생성하면 String풀에 생성됨
        String s4 = "abcde"; // String풀에 동일한 문자열이 있으면 그것을 참조하고 없으면 String풀에 새로 생성함
        String s5 = new String("abcde"); // 흙빛이 되어버림.. 구지 생성한다고?// 힙메모리에 생성됨

        System.out.println(s3 == s4); //true // 문자열을 곧바로 생성할 경우 String풀에서 찾아 사용
                                            // String은 immuntable하기에 같은값을 참조해도 문제가 발생하지 않음
        System.out.println(s3 == s5); //false // 문자열을 클래스로 생성할 경우 새로운 인스턴스를 생성하기에 참조값이 다름

        System.out.println(s3.equals(s4));//true // equals는 무슨 메서드였지? -> String 값비교 메서드.
        System.out.println(s3.equals(s5));//true // 참조 값이 아닌 실제 값을 비교해주는 메서드
```



## String 메서드

- length() 스트링의 길이 출력

- charAt(index) index에 위치한 char 출력

- indexOf('char') 'char'이 몇번 index에 위치하는가 출력

- equalsIgnoreCase("string") string과의 값비교 같으면 true(대소문자구분 x)

- replace('i','t') char 'i'를 't'로 바꾸어서 출력(기존 값에는 영향x)

- substring(a, b) index a~(b-1)번째까지 출력해줌 (b는 포함안됨)

- trim() 양옆의 공백 제거해주는 메소드

- repeat(i) string을 i만큼 반복시켜주는 메서드

- toCharArray() String을 char단위로 새로운 배열에 넣어줌. 
    - String의 원본배열을 불러오는것이 아닌 새로운 배열을 만드는 것.
    - 원본 배열이 아니니 수정해도 String 값의 영향 X
    
- equals(anObject) anObject와 비교한 결과 출력(boolean) 

  - ==을 하면 같은 객체가 맞는지 비교하는 방법
  - 같은 내용이 다른객체에 있더라도 비교하고 싶다면 equals를 사용
  
  
  
- | 메소드             | 메소드 선언                                             | 설명                                               |
| ------------------ | ------------------------------------------------------- | -------------------------------------------------- |
  | length()           | `public int length()`                                   | 문자열의 길이를 출력                               |
  | charAt()           | `public char charAt(int index)`                         | index번째에 위치한 문자 출력                       |
  | indexOf()          | `public int indexOf(char ch)`                           | ch가 위치한 index 출력. 없을 시 -1                 |
  | equals()           | `public boolean equals(Object anObject`                 | anObject와 비교한 결과 출력                        |
  | equalsIgnoreCase() | `public boolean equalsIgnoreCase(String anotherString)` | 대소문자 구분없이 anotherString과 비교 결과 출력   |
  | replace()          | `public String replace(char oldChar, char newChar)`     | oldChar를 찾아 newChar로 변경된 문자열 출력        |
  | substring()        | `public String substring(int beginIndex, int endIndex)` | 문자열을 beginIndex부터 endIndex-1까지 잘라서 출력 |
  | trim()             | `public String trim()`                                  | 문자열 좌우 공백을 없앤 결과를 출력                |
  | matches()          | `public boolean matches(String regex)`                  | 문자열을 정규표현식 regex 확인 결과 출력           |
  | split()            | `public String[] split(String regex)`                   | 문자열을 정규표현식 형태로 나눈 후 배열로 출력     |
  
  
  
    

```java
        String s = "This is a string.";
        System.out.println(s.length()); //16 //length 같이 이름만들어도 기능이 예상되는게 잘지은 이름이다.
                                        //이름을 짓기 어려운 기능은 메서드로 만들지 않는 것이 좋다.
        System.out.println(s.charAt(2));//i
        System.out.println(s.indexOf('a'));//8 // a가 몇번 인덱스에 위치하는가?
        System.out.println(s.equalsIgnoreCase("this Is A STRiNg."));//true//문자열비교해서 같은면true
                                                                                // 대소문자 구분x
        System.out.println(s.replace('i','t'));//Thts ts a strtng.//기존의 String을 바꾸는것은 아님
                                                            //변경된 String을 생성해서 출력하는 것
        System.out.println(s);//This is a string.// 기존 내용 유지
        System.out.println(s.substring(3, 9));//s is a // index 3~8번까지 출력해줌(마지막 인덱스 제외)
        System.out.println(" wwefw ".trim());//wwefw// 양옆의 공백 제거해주는 메소드
        System.out.println("*".repeat(10));//********** //문자열을 반복해줌
        // 추가적인것은 더 찾아서 사용하면 된다.

        char[] characters = s.toCharArray();
        for(char value : characters){
            System.out.println(value);
        } // String의 배열을 불러온것이 아닌 새로운 배열을 만든것임// so 여기 배열 내용 변경시켜도 원문에 변화없음
```
