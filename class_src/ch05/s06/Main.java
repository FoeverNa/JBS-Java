package ch05.s06;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{

        // Object 클래스
        // 상속을 배울때 모든 클래스가 Object상속하고 있다고말한 그 클래스
        // 모든 클래스의 조상 클래스 - 클래스의 기본 기능을 제공한다
        // 필요한 경우 Object 클래스에 구현디

        Object obj = new Object();
        Object obj1 = obj;
        Object obj2 = new Object();

        System.out.println(obj.getClass());//class java.lang.Object
        //Class 클래스 객체를 반환 (Class라는 클래스가 있는거야)


            //equals() 동일한 객체를 가르키는지 여부를 boolean으로 반환
        System.out.println(obj.equals(obj1));//true
        System.out.println(obj.equals(obj2));//false
        //구현을보려면 해당 메서드명위에 Goto - Ddclare로가면됨
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

        //우리도 새로운 클래스를 만든다면 eqauls()를 잘 오버라이딩해야된다, 어떤것들이 같으면 같다고할지

            //hashCode() 메소드
        // 객체를 구분하기 위해서 사용하느 정수값(int)을 반환해준다
        // 각 객체마다 프로그램 내에서 유일한 값을 가지기 때문에 주소값처럼 사용 가능하다 -> 실제 주소값은 아니다
        // hashCode()는 native이기 때문에 정의를 볼 수 없다
            //native : C 또는 C++ 등 외부언어로 작성된 메소드 => 굉장히 효율적으로 작성해야하는 알고리즘의 경우 다른언어로작성하기도함
            // 왜 native를 하는거는 좀어렵긴한 문젠거 같다
        // hashCode()를 Override할 때 제약사항
            //조건1 한 객체의 hashCode()를 여러번 호출할 경우,
                // 해당 객체의 equals()에서 사용하는 값이 변하지 않았다면
                // hashCode()의 반환값은 동일해야 한다.
                // 즉, 객체가 변하지 않으면 hashCode는 그대로 유지되어야 한다는 뜻?
            // 조건2. equals() 메소드가 같다고 판단한 두 객체의 hashCode() 값은 항상 같아야 한다
                // 두번째거를 실현하는 방법이 첫번째가되는 것 - > 이해해보기
            // 조건3. equals() 메소드에서 다르다고 판단하는 두 객체는 항상 hashCode()값이 다를 필요는 없으나
            // 다르면 Hash기반 자료구조의 성능이 향상된다. => 권고사항
                // eqauls()가 다른데 hashCode()값이 다른 경우가 있다네
                //-> 즉, equals()가 같으면 hashcod()는 반드시 같고, 다르다고 hashCode() 반드시 다른것은 아니다
            // 직접 구현시 위의 조건을 만족시키기가 어렵기 때문에 가능하면 오버라이드 하지 않고 그대로 사용한다

            // getClass() 메소드
        class Foo{
        }
        Foo foo = new Foo(); //
        System.out.println(foo.hashCode());
        //일반적으로 Object에 구현된 hashCode를 그대로 쓰는 것이 좋다
        System.out.println("");

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
        System.out.println(barClass.getDeclaredMethods().length); //1 // Method클래스도 존재한다
        barClass.getDeclaredMethod("methodA").invoke(bar);// methodA is called.
        System.out.println("");


        // API를 만들때 사용하기 때문에 그렇게 기억만 해두면 될 것 같다

        // System 클래스
        // OS와 interact하기 위한 클래스
        // 정적 변수와 메소드만으로 구선된 클래스 => 객체를 만들지 않음

        System.out.println(System.out); // System.out은 정적변수이고 PrinStream의 객체이다
        System.out.println(System.err); // PritStream 객체
        System.out.println(System.in); // InputStream
        //Stream API와는 다른, I/O Stream이다.
        System.out.println("f");// 표준 출력을 처리하기 위한 객체
        System.err.println("w");//  오류를(표준 출력 장치에) 출력하기 위한 객체-> 그래서 빨갛게출력
        // err에도 동일한 메서드 존재하는 이유는 같은 클래스의 객체 담고 있기 때문에
        Scanner scanner = new Scanner(System.in); // 표준 입력장치를 사용하기 위한
        System.out.println("");

        int[] ints = {1,2,3,4};// 인트스트림안쓰나 이런생각도 해볼수 있겠지
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
        // 일반적인 머신은 RTOS(Retime machine)가 아니기 때문에 정확하지 않을 수 있음

            // 종료에 관한 메서드
        //System.exit(0); // 프로그램 강제 종료
        // Process finished with exit code 0, 원래 프로그램 종료시 나오는아이네
        //관습적으로 status =0: 정상 종료. status != 0: 비정상 종료 (1)

            //가비지컬렉션
        System.gc(); //Garbage Collection
                    //꼭 실행할 필요는 없으나, 좀더 빨리 해달라고 용처하는 정도
                        //요청했다고 바로 gc가 작동하는 것도 아니다
            // 환경변수
        System.out.println(System.getenv()); //모든 환경변수를 출력해준다 => os랑 통신하는애라 가능
        System.out.println("");
        System.out.println(System.getProperties());
        System.out.println(System.getProperty("user.country")); // KR //사용자 국적을 알수 있게됨
        System.out.println(System.getProperty("java.io.tmpdir")); // 기본 Temp 위치를 알 수 있음
        System.out.println(System.getProperty("line.separator")); // windows -
        System.out.println(System.getProperty("user.home")); // windows보단 mac에서 중요한의미를 가진다
        System.out.println(System.getProperty("file.separator")); // 직접 사용하는 것보다 이렇게 화룡ㅇ할수있게 해주면좋다

        // 유용한것들이 있다고 알아두고 나중에 비슷한 것 나오면 이거랑 연관되어 있구나 라고 알아들으면 좋다


        // Math 클래스

        System.out.println(Math.abs(-4)); // 4 // int long float double 오버로딩

        System.out.println(Math.ceil(1.2)); // 2.0 // double 형 입력, 출력도 double 형 -> 값의 범위(Overflow 가 발생할수있음)
                                            // overFlow 같은 경우는 실무나, 코딩테스트에서 고려해야 될 대상이 될 수 있으니 알아두어야 한다
                                            // workAround 한 방식을 왜 하는지 그리고 어떻게 하는지를 이해해야 한다.

        System.out.println(Math.floor(1524.4)); // 1524.0 // 내림 연산 // 더블 입력 더블 출력

        System.out.println(Math.max(4, 2)); // 4 // 2개의 값만을 비교하게 되어 있음 -> Reduce 방식을 이용해서 처리하기 때문에(Accumulation?)
        // 다른 언어에서는 max({1,2,3,4,5,}) 이런식으로 여러수를 비교할 수 있게 하게 되어 있음 => 두 방식다 장단점이 있음

        System.out.println(Math.min(4, 2)); // 2

        System.out.println(Math.random()); // 0.0 이상, 1.0 미만의 값을 임의로 출력
        System.out.println(Math.random() > 0.7); // true, false 로 확률을 출력해준다 -> 이경우는 30& 확률로 true

        // random을 통한 확률 표현
        int count =0;
        for (int i = 0; i < 10000; i++) {
             if(Math.random() < 0.3){ // 30% 확률
                count += 1;
            }
        }
        System.out.println(count); // 2928 // 3073 // 3033 // 2990 거의 확률과 비슷하게 출력되게 설정되어 있다
        // 몬테카를로?

        // 랜덤한 정수
        int minVal = 2;
        int maxVal = 10;
        int randInt = (int)(Math.random() * (maxVal - minVal + 1) // 0~8이 나오려면 random값에 9를 곱해줘야한다
        + minVal); // 0~8에 +2를 해주면 2~10값이 나온다
        System.out.println(randInt); // 2 // 9 // 4

        System.out.println(Math.round(1.4f)); // 입력 float -> 출력 int, double -> long

        System.out.println(Integer.MAX_VALUE + 10); // OverFlow 발생
        try {
            System.out.println(Math.addExact(Integer.MAX_VALUE, 10)); // OverFlow 발생시 ArithmaticException 예외를 발생시켜줌
            System.out.println(Math.subtractExact(Integer.MAX_VALUE, -10));
            System.out.println(Math.multiplyExact(Integer.MAX_VALUE, 4));
        } catch(ArithmeticException e) {
            e.printStackTrace();
        }
        System.out.println("end");

        // 일반적으로 OverFlow가 나는것은 버그가 아니고 특징이다, 그 특징을 어떻게 다룰 것인가











    }
}
