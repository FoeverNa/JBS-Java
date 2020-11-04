# java.lang 패키지

## java.lang

- Java에서 가장 기본적이며 자주 사용되는 클래스를 모은 패키지
- 별도로 import하지 않아도 사용이 가능한, Java의 기본 중의 기본
  - 우리가 이미 알게 모르게  많이 사용하고 있는 패키지

## Object Class

- Java.lang 패키지에 대표적인 클래스

- 모든 클래스의 조상 클래스로, 클래스가 갖춰야할 기본 기능을 제공한다

- 필요한 경우 Object 클래스에 정의된 메소드를 Override하여 사용한다

  - 대표적으로 equals()메서드

  | 메소드                                                      | 설명                                                       |
  | ----------------------------------------------------------- | ---------------------------------------------------------- |
  | `public final native Class<?> getClass()`                   | 현재 객체의 원본 클래스를 반환한다.                        |
  | `public native int hashCode()`                              | 현재 객체의 해시코드 값을 반환한다.                        |
  | `public boolean equals()`                                   | 현재 객체와 대상이 같은 객체를 참조하는지 여부를 반환한다. |
  | `public String toString()`                                  | 객체를 문자열로 변환하여 반환한다.                         |
  | `proteted native clone() throws CloneNotSupportedException` | 객체를 복사하여 새로운 객체로 반환한다.                    |

### equals()

- 동일한 객체를 참조하는지 여부를 boolean으로 반환하는 메서드

- Object 클래스의 equals() 메소드 구현

  - 객체의 참조만을 비교

  ```java
  public boolean equals(Object obj) {
      return (this == obj);
  }
  ```

- String 클래스의 equals() 메소드 구현 (Overriding)

  - 효율적으로 객체의 내용이 동일한지 비교

  ```java
  public boolean equals(Object anObject) {
      if (this == anObject) {
          return true;
      }
      if (anObject instanceof String) {
          String aString = (String)anObject;
          if (!COMPACT_STRINGS || this.coder == aString.coder) {
              return StringLatin1.equals(value, aString.value);
          }
      }
      return false;
  }
  ```
  
- ```java
  Object obj = new Object();
  Object obj1 = obj;
  Object obj2 = new Object();
  
  System.out.println(obj.getClass());//class java.lang.Object
  //Class 클래스 객체를 반환 (Class라는 클래스가 있는거야)
  
  System.out.println(obj.equals(obj1));//true
  System.out.println(obj.equals(obj2));//false
  //구현을보려면 해당 메서드명위에 오른쪽 클릭한 뒤 Go Too - Ddclaration or Usage 으로가면 된다
  System.out.println("");
  
  String str1 = "abc";
  String str2 = str1;
  String str3 = "abc";
  String str4 = new String("abc");
  
  //동일 객체를 비교
  System.out.println(str1 == str2);//true
  System.out.println(str1 == str3);//true //String풀에서 이미 존재하는 String을 참조하기때문에
  System.out.println(str1 == str4);//false //New String할 경우 명시적으로 새로운 객체 생성
  System.out.println("");
  
  //String의 eqauls는 내용만 같아도 true를 반환되도록 오버라이딩되어있다
      //String클래스에서 Object를 상속하니까 equals()메서드를 오버라이딩할 수 있지
      //String의 경우 다른 객체여도 같은 내용일 수 있기 때문에 (효율적으로)비교하게끔 오버라이딩
  System.out.println(str1.equals(str2));//true
  System.out.println(str1.equals(str3));//true
  System.out.println(str1.equals(str4));//true
  System.out.println("");
  
  //우리도 새로운 클래스를 만든다면 eqauls()를 잘 오버라이딩해야된다, 어떤 것들이 같으면 같다고할지
  //이전에 클래스에 멤버 변수가 같으면 같은 클래스라고 출력하던것
  ```



### hashCode() 

- 객체를 구분하기 위해 사용하는 정수값(해시코드)을 반환
  - 프로그램 내에서 객체마다 유일하므로 주소값처럼 사용 가능 => 실제 주소값은 아니다
- `native` 메소드이므로 구현 내용을 볼 수 없음
  - native : C 또는  C++ 등 외부언어로 작성된 코드 => 굉장히 효율적으로 작성해야하는 알고리즘의 경우 다른언어로 작성하기도 한다
- hashCode()의 제한 사항
  - 한 객체의 hashCode()를 여러번 호출할 경우, equals()에 사용하는 값이 변하지 않으면 동일한 값을 반환해야 한다.(equals()에서 사용하지 않는 값은 변해도 상관이 없다)
    - 객체가 변하지 않는다면 hashCode도 동일하게 유지가 되어야 한다는 뜻
  - equals() 메소드가 같다고 판단한 두 객체의 hashCode() 반환값은 같아야 한다.
    - equals()가 같다고 판단했을때는 같아야 하지만 다르다고 판단했을때는 다를 필요는 없다 => 햇깔리게 하는 부분이긴하네
  - 권고사항 : equals() 메소드가 다르다고 판단한 두 객체의 hashCode()가 반드시 다를 필요는 없으나, 다른 값이 나오면 Hash기반 자료구조의 성능이 향상된다.
    - equals()가 다른데 hashCode()값이 같은 경우가 있는데 가능하면 다르게 나오게 해주는 것이 HashTable성능에 도움이 된다는 뜻
      - 이런 경우를 해쉬 충돌이라고 한다 => HashTable & HashMap 자료구조에서 더자세히 배우는 것 같다
    - 즉 eqauls()가 같으면 hashCode()는 반드시 같고, equals()가 다를때 hashCode()가 반드시 다른 것은 아니다.

### clone() 

- 자신을 복제하여 새로운 객체를 생성하는 메소드

- 배열을 복제할 경우 새로운 객체를 생성하므로 원본을 훼손하지 않을 수 있음

- clone() override 시 `Cloneable` 인터페이스를 구현해야 함

  ```java
  int [] ints = {1, 2, 3, 4, 5};
  int [] ints2 = ints.clone();
  ```

### getClass()

- Class 에 대한 정보를 담고 있는 Class 객체를 반환

- 객체의 getClass() 메소드 또는 해당 클래스의 정적 변수 class를 이용 가능

  ```java
          // getClass() : 클래스 저옵를 담고 있는 Class 객체를 반환
          class Bar{
              public void methodA(){
                  System.out.println("methodA is called.");
              }
          }
  
          //Class 클래스, Method 클래스의 활용
          // Reflect API를 이용한다
  		Bar bar = new Bar();
          Class barClass = bar.getClass();
  
          System.out.println(barClass.getName()); // s06.Main$1Bar
                                                  // 패키지. Main클래스안에 로컬내부클래스는 숫자로구분된 클래스명이존재
          System.out.println(barClass.getDeclaredMethods().length); //1 // 선언되어 있는 메소드의 수를 반환한다
          barClass.getDeclaredMethod("methodA").invoke(bar);// methodA is called.
  													//해당 객체의 메소드에 접근할 수 있음
          System.out.println("");
  
          // API를 만들때 사용하기 때문에 이런것들이 있단는 것 기억하고 자세한건 그때 찾아서 사용하면 된다
  
          
  ```

## System Class

- 실행 중인 OS와 interact하기 위한 클래스 

  - 객체화 될수 없는 클래스이다

- System 클래스의 주요 정적 변수 => 객체를 만들지 않고 사용한다

  | 속성                                  | 설명                                  |
  | ------------------------------------- | ------------------------------------- |
  | `public static final PrintStream err` | 오류를 출력하기 위한 표준 출력 스트림 |
  | `public static final InputStream in`  | 표준 입력을 처리하기 위한 입력 스트림 |
  | `public static final PrintStream out` | 표준 출력을 처리하기 위한 출력 스트림 |

- System 클래스의 주요 정적 메소드

  | 메소드                | 설명                                                         |
  | --------------------- | ------------------------------------------------------------ |
  | `arraycopy()`         | src 배열의 내용을 dst 배열로 복사한다.                       |
  | `currentTimeMillis()` | 현재 시스템 시간을 ms로 반환한다.                            |
  | `exit()`              | 프로그램을 종료한다                                          |
  | `gc()`                | GC를 요청한다.                                               |
  | `getenv()`            | 환경 변수의 값을 반환한다.                                   |
  | `getProperties()`     | 시스템 속성을 Property로 반환한다.                           |
  | `getProperty()`       | 시스템 속성 값을 문자열로 반환한다. 없을 경우 null 또는 def를 반환 |
  | `identityHashCode()`  | 객체의 해시코드 값을 반환한다.                               |
  | `lineSeparator()`     | 시스템의 줄넘김 문자열을 반환한다. UNIX: `\n`, WINDOWS: `\r\n` |
  | `nanoTime()`          | 시스템 시간을 ns로 반환한다.                                 |
  | `setProperties()`     | 시스템 속성을 한번에 설정한다.                               |
  | `setProperty()`       | 시스템 속성을 하나씩 설정한다.                               |

  - 주요 Property

    | 속성 | 설명 | | `user.country` | OS 로케일 정보 | | `java.io.tmpdir` | 임시 경로 | | `line.separator` | 줄넘김 문자열 | | `user.home` | 유저 홈 경로 | | `file.separator` | 파일 경로 구분 |

```java
System.out.println(System.out); // out은 정적변수이고 PrinStream의 객체이다(객체를 담고있다)
System.out.println(System.err); // err도 PritStream 객체를 담고 있다
System.out.println(System.in); // in은 InputStream 객체
//위의 나온 Stream은 Stream API와는 전혀 다른, I/O Stream이다.

System.out.println("f");//f // 표준 출력을 처리하기 위한 객체
System.err.println("w");//w(빨간색 글씨) //  오류를(표준 출력 장치에) 출력하기 위한 객체-> 그래서 빨갛게출력
// err에도 out과 동일한 메서드 존재하는 이유는 동일한 클래스의 객체 담고 있기 때문에
Scanner scanner = new Scanner(System.in); // 표준 입력장치를 사용하기 위한 객체
System.out.println("");

int[] ints = {1,2,3,4};// 이제 배열 만들 때 IntStream 안쓰나 이런생각도 해볼수 있겠지
int[] ints1 = new int[10];
System.arraycopy(ints,0,ints1, 0, ints.length);
// native로 더 효율적으로 구현되어있음, Arrays에 있지 않고 System에 구현되어 있는 이유
System.out.println(ints1);
System.out.println("");

 //시간에 관련된 메서드
//currentTimeMillis(), nanoTime()
System.out.println(System.currentTimeMillis()); //1601282787547
System.out.println(System.currentTimeMillis()); //1601282787547 //밀리초는 큰차이가 없네
System.out.println(System.nanoTime()); //594530045231600 -> 끝이 00인 이유는 1나노단으로 측정이 안되는것
System.out.println(System.nanoTime()); //594530045261300 -> 나노타임으로 하니까 차이가나네
// 일반적인 머신은 RTOS(Realtime machine)가 아니기 때문에 정확하지 않을 수 있음

    // 종료에 관한 메서드
//System.exit(0); // 프로그램 강제 종료 할수 있음
// Process finished with exit code 0, 원래 프로그램 종료시 나오는 메세지.
//관습적으로 status =0: 정상 종료. status != 0: 비정상 종료 (1)

    //가비지컬렉션
System.gc(); //Garbage Collection
            //꼭 실행할 필요는 없으나, 좀더 빨리 해달라고 용처하는 정도
                //요청했다고 바로 gc가 작동하는 것도 아니다

// 환경변수
System.out.println(System.getenv()); //모든 환경변수를 출력해준다 => os랑 통신하는애라 가능
System.out.println("");
System.out.println(System.getProperties());//시스템 속성을 Property로 반환한다
System.out.println(System.getProperty("user.country")); // KR //사용자 국적을 알수 있게됨
System.out.println(System.getProperty("java.io.tmpdir")); // 기본 Temp 위치를 알 수 있음
System.out.println(System.getProperty("line.separator")); // windows - \r\n, UNIX \n, 줄바꿀때 필요한 것 OS별로 체크해줌
System.out.println(System.getProperty("user.home")); // windows보단 mac에서 중요한의미를 가진다
System.out.println(System.getProperty("file.separator")); 
// 사용자의 oS환경 정보에 접근해서 그것을 통해 프로그래밍의 활용할 수 잇겠다
// 직접 사용하는 것보다 이렇게 화용할수있게 해주면좋다
```



## Math Class

- 수학 계산에 필요한 메소드를 가진 final 클래스

  - 상속이 불가능 하다 => 상속해봐야 구현해봐야 기존 구현보다 나을리 없다라는 의미
  
- 모든 메소드가 static 메소드로 구현되어 있다

  - 객체를 생성하지 않고 그대로 사용하게 되어 있다
  
  | 메소드            | 설명                                                        |
  | ----------------- | ----------------------------------------------------------- |
  | `abs()`           | 절대값을 반환한다.                                          |
  | `ceil()`          | 올림 값을 double로 반환한다.                                |
  | `floor()`         | 내림 값을 double로 반환한다.                                |
  | `max()`           | 두 값 중 더 큰 값을 반환한다.                               |
  | `min()`           | 두 값 중 더 작은 값을 반환한다.                             |
  | `random()`        | 0 이상 1.0 미만의 임의의 값을 반환한다.                     |
  | `round()`         | 소수점 첫째자리에서 반올림한 정수 값을 반환한다.            |
  | `addExact()`      | 덧셈 연산으로, Overflow 발생 시 `ArithmaticException` 발생. |
  | `subtractExact()` | 뺄셈 연산으로, Overflow 발생 시 `ArithmaticException` 발생. |
  | `multiplyExact()` | 곱셈 연산으로, Overflow 발생 시 `ArithmaticException` 발생. |

```java
// Math 클래스

System.out.println(Math.abs(-4)); // 4 // 절대값 반환 // int long float double 오버로딩이 되어 있다

System.out.println(Math.ceil(1.2)); // 2.0 // 올림값 반환 // double 형 입력, 출력도 double 형 -> 값의 범위(Overflow 가 발생할수있음)
                                    // overFlow 같은 경우는 실무나, 코딩테스트에서 고려해야 될 대상이 될 수 있으니 알아두어야 한다
                                    // workAround 한 방식을 왜 하는지 그리고 어떻게 하는지를 이해해야 한다.

System.out.println(Math.floor(1524.4)); // 1524.0 // 내림 값 반환 // double 입력 double 출력

System.out.println(Math.max(4, 2)); // 4 // 두 값 중 더 큰 값 반환
                                    // 2개의 값만을 비교하게 되어 있음 -> Reduce 방식을 이용해서 처리하기 때문에(Accumulation?)
                                 // 다른 언어에서는 max({1,2,3,4,5,}) 이런식으로 여러수를 비교할 수 있게 하게 되어 있음 => 두 방식다 장단점이 있음

System.out.println(Math.min(4, 2)); // 2 // 두 값 중 더 작은 값 반환

System.out.println(Math.random()); // 0.0 이상, 1.0 미만의 값을 임의로 출력
System.out.println(Math.random() < 0.3); // true, false 로 확률을 출력해준다 -> 이경우는 30& 확률로 true

// random을 통한 확률 표현
int count =0;
for (int i = 0; i < 10000; i++) {
     if(Math.random() < 0.3){ // 30% 확률
        count += 1;
    }
}
System.out.println(count); // 2928 // 3073 // 3033 // 2990 거의 확률과 비슷하게 출력되게 설정되어 있다
// 몬테카를로 방법? 과 연관되어 있다고 한다

// 랜덤한 정수
int minVal = 2;
int maxVal = 10;
int randInt = (int)(Math.random() * (maxVal - minVal + 1) // 0~8이 나오려면 random값에 9를 곱해줘야한다
+ minVal); // 0~8으로 나온 값에 +2를 해주면 2~10값이 나온다
           // 알아두면 도움이 될것 같은 알고리즘이다
System.out.println(randInt); // 2 // 9 // 4

System.out.println(Math.round(1.4f)); // 소숫점 첫째자리에서 반올림한 정수값을 반환한다
                                      // 입력 float -> 출력 int, double -> long

// addExact, subtractExact, multiplyExact
System.out.println(Integer.MAX_VALUE + 10); //-2147483639 // OverFlow 발생

try {
    System.out.println(Math.addExact(Integer.MAX_VALUE, 10)); // OverFlow 발생시 ArithmaticException 예외를 발생시켜줌
    System.out.println(Math.subtractExact(Integer.MAX_VALUE, -10)); //위에서 예외발생되면 아래예외는 고려 하지 않는다
    System.out.println(Math.multiplyExact(Integer.MAX_VALUE, 4));
} catch(ArithmeticException e) {
    e.printStackTrace();
}
System.out.println("end");

// 일반적으로 OverFlow가 나는것은 버그가 아니고 특징이다, 그 특징을 다루기 위해 예외처리하는 것이다 
```