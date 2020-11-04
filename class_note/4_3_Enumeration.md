# 열거형 (Enumeration)

 - enum 키워드로 표현한다

 - 내부적인 구현은 enum ==> java.lang.Enum 클래스를 상속하는 자녀 클래스이다.
    - 그렇기 때문에 enum은 다른 클래스를 상속하지 못한다

     - 열거형은 다른 클래스를 상속하지 못하지만, 인터페이스 구현은 가능 하다
    
 - 열거형 타입에는 열거형 상수와 null 값을 할당 가능하다

     - 열거형 타입의 변수에 null값을 넣을 수 있다는 말이었음..

     - ```java
        Color color1 = Color.RED;
        Color color2 = null;
        ```

 - An enum is a special "class" that represents a group of constants
    - Use enums when you have values that you know aren't going to change, like month days, colors, deck of cards, etc.
    



```java

// class 같이 enum으로 선언
enum Job { // 각 상수는 0부터 숫자를 가지지만, 심볼로만 사용하고 숫자는 사용하지 않음
    STUDENT, MARKETING, DEVELOPER, CHIEF_EXECUTIONAL_OFFICER; // 열거형 상수
}


public class Enumeration {
        public static void main(String[] args) {
        Job job = Job.STUDENT; // enum 명은 클래스 일종이기에 자료형으로 사용 가능
                    // 클래스 변수와 유사하게 사용
        
                    if (job == Job.CHIEF_EXECUTIONAL_OFFICER) {
                        System.out.println("사장님 안녕하세요?");
                    }
        
                    char c = 'A';
                    switch (c) {
                        case 'A':
                            break;
                        case 'B':
                            break;
                        default:
                    }

         switch (job) { // switch ~ case 문에는 열거형 자료형을 생략
                    case STUDENT:
                        System.out.println("you will be a great one");
                        break;
                    case MARKETING:
                        System.out.println("you sells");
                        break;
                    case DEVELOPER:
                        System.out.println("you make things");
                        break;
                    case CHIEF_EXECUTIONAL_OFFICER:
                        System.out.println("you choose");
                        break;
                    default:
                        System.out.println("Who are You?");
                } // you will be a great one
        
                    System.out.println(Foo.Symbol.ONE);// ONE
            										// 심볼그자체로 사용된다..
}
}
```
- enum은 클래스와 유사하게 enum enum명으로 선언하다

- 서로 관련 있는 상수들을 모아 심볼릭한 명칭의 집합으로 정의한 것이다(추가)

- 상수를 열거하여 선언할 수 있다. ex) STUDENT, MARKETING, DEVELOPER
    - 상수를 열거하는 것이 객체를 선언하는 것과 같다.(new해서 객체를 생성하지 않는다)
    
      - 클래스를 사용시 해당하는 선언한 모든 변수의 객체를 생성한다
    
      - ex)Job.STUDENT 접근시 enum안에 모든 변수의 객채를 생성한다
    
        ```java
        public class EnumTest {
            public static void main(String[] args) {
                System.out.println(Color.BLUE);
        ///결과
        object created
        object created
        object created
        static created
        BLUE
        ```
    
        
    
    - 생성자를 이용해 멤버변수를 초기화할때 상수옆에 arguments를 입력한다 ex) STUDENT(19)
    
    - 열거형 상수는 정적 변수로 enum에 객체를 담고 있다.
    
      
    
- 변수가 아닌 상수 값들을 모아서 사용할 때 사용할 수 있다.

    

- switch ~case문에서는 열거형 자료형을 생략해야 한다 
  
    - 사용시 에러 발생 한다 ex) Job job(X) , job(O)
    
      


## 클래스 내부에서 열거형 구현

```java
class Foo{ // 클래스 내부에서 열거형 구현 가능하다
    enum Symbol {
        ONE, TWO, THREE;
    }
}
public class Enumeration {
        public static void main(String[] args) {

System.out.println(Foo.Symbol.ONE); //ONE
}
}
```

- 클래스내부에서 열거형을 선언하여 구현할 수 있다.
    - Foo(클래스명).Symbol(enum명).ONE(상수)로 접근이 가능하다
    
- TicTacToe 예제에 Board.WinnerStatus 구현했던것 떠올려봐.

- ```java
    public class Board implements Initializable {
        enum WinnerStatus {
            NOT_FINISHED, TIE, PLAYER_ONE, PLAYER_TWO;
        }
    ```

### 열거형 내부에서 메소드 구현

```Java
enum Symbol {
    ONE, TWO, THREE, FOUR;

    public Symbol nextSymbol() {
        if (this.equals(ONE)) { // 열거형 내부에서는 ONE 바로 사용가능
            // 여기서 this.는 상수들, 상수들이 객체가됨
            // 상수가 ONE이면 뭐하라는 뜻
            return TWO;
        } else if (this.equals(TWO)) {
            return THREE;
        }
        return FOUR;

    }
}
  public class Enumeration {
        public static void main(String[] args) {
            
        Symbol sym = Symbol.ONE; //ONE
        Symbol nextSym = sym.nextSymbol(); // sym 은 객체임 그래서 메소드 사용가능한것
        System.out.println(nextSym); // Two
        nextSym = nextSym.nextSymbol();
        System.out.println(nextSym); // Three
        }
        }
        
```

- 열거형 내부에서도 메서드를 생성 가능하다
  
    - 이때 열거형의 상수는 this를 통해 객체로 지칭될 수 있다. ex) this.equals(ONE)
    
- 열거형의 상수는 객체를 담고 상수로 .을 붙여서 메서드를 사용할 수 있다. exe)Symbol sym = Symbol.ONE; sym.nextSymbol();

    - 객체를 담고 있는 변수를 사용하는 것과 같은데 뭔가 객체 생성의 과정이 없으니 해깔려 보인다..

        


## 열거형 생성자를 이용한 enum 상수 초기화

```java
enum Family {
    FATHER("아버지"), MOTHER("어머니"), SON("아들"), DAUGHTER("딸");
                                                            // 열거형 상수(객체)// new가 써있지않을 뿐이지 new로만들어지는애들
    private String koreanWord; // 멤버 변수(객체 속하는 변수)

    public String getKoreanWord() {
        return koreanWord;
    }

    public void setKoreanWord(String koreanWord) {
        this.koreanWord = koreanWord;

        //KoreanWord변수가 private이기에 getter setter 생성
    }

    // private은 생략 가능, public 불가능, 안써도 private, 안에서만 객체 생성되기 때문..
    private Family(String koreanWord){ // 각각의 객체를 생성하는 생성자
        this.koreanWord = koreanWord; // 이것까지하면 위에 상수에 ','에 빨간불 들어옴 왜? arguments 입력하라고...
    }
}

    public class Enumeration {
        public static void main(String[] args) {

            //열거형 생성자와 멤버 변수 활용
            Family fam = Family.SON;
            System.out.println(fam.getKoreanWord()); //아들
            fam.setKoreanWord("버린 자식");
            System.out.println(fam.getKoreanWord()); //버린자식
            System.out.println(Family.SON.getKoreanWord()); // 버린자식 // 해당객체가 여러개 생성되는게 아닌 하나의 객체만생성되는것

            System.out.println(Family.FATHER.FATHER); // FATHER0
            System.out.println(Family.FATHER.FATHER.MOTHER); // MOTHER
            System.out.println(Family.FATHER.DAUGHTER.getKoreanWord());// 딸

        }
    }
```
- 열거형의 상수는 객체이기 때문에 열거형의 선언된 멤버 변수를 속성으로 갖는다.
  
- 열거형의 멤버 변수는 private이다
  
  ​    
  
- 열거형의 생성자를 통해 멤버 변수를 초기화 할 수 있다. 
    - 열거형의 생성자는 내부에서만 객체를 생성하기 때문에 private이다.
      
    - ex)  private Family(String koreanWord){ this.koreanWord = koreanWord }
      
    - 이때 초기화는 객체가 존재하는 열거형 내에서 한다
      
        - Enum은 상수 선언시 객체를 생성하고 초기화 하는 것이기 때문이다
          
        - FATHER("아버지"), MOTHER("어머니"), SON("아들"), DAUGHTER("딸");
        
            
    
- 클래스와 같이 객체를 통해 멤버 변수에 접근 할 수 있다.
    - 멤버변수가 private인경우도 getter setter를 통해 멤버변수에 접근 한다.
      
        - fam.setKoreanWord("버린 자식");
        
          

## Enum에 상수의 특징

- Enum의 상수는 정적 변수에 객체가 담겨있는 형태이다
  
    - Static Family FATHER = new Family(); 와 같은 형태
    
      
    
- 그래서 System.out.println(Family.FATHER.DAUGHTER.getKoreanWord()); // MOTHER 같은 문장이 가능하다
    - Family.FATHER으로 정적변수에 접근, 정적변수안에 객체로 다시 정적변수인 DAUGHTER에 접근
    - 그리고 정적변수에 객체로 .geKoreaWord()에 접근할수 있었던 것이다



## values(), ordinal() 그리고 valueOf() 메서드 :

 

- 이 메소드들은 **java.lang.Enum** 내부에 존재한다.
- values() 메소드는 enum안에 존재하는 모든 값들은 반환한다.
- enum안에서 순서는 중요하다. ordinal() 메소드를 사용하여 배열 인덱스처럼 각 enum 상수 인덱스를 찾을 수 있다.
- valueOf() 메소드는 존재한다면 특정 enum 상수의 스트링 값을 반환한다.

```java
enum Color { 
    RED, GREEN, BLUE; 
} 
  
public class Test 
{ 
    public static void main(String[] args) 
    {
        for (Color col : Color.values()) { 
            System.out.println(col + " at index " + col.ordinal()); 
        } 
  
        System.out.println(Color.valueOf("RED")); 
    } 
} 
```

```java
RED at index 0
GREEN at index 1
BLUE at index 2
RED
```