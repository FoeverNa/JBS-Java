package ch05.s01;

/**
 * Wrapp 클래스(Wrapper class)
 * - 기본형 타입을 객체로 쓰기 위해 있는 클래스
 * - 기본형 타입이 허용되지 않는 문법에 기본형 타입을 쓰기 위해서 제공
 *
 * byte -> Byte
 * char -> Character
 * short -> Short
 * int -> Integer
 * long -> Long
 * float -> Float
 * double -> Double
 * boolean -> Boolean
 */

public class WrapperClass {
    public static Integer add(Integer x, Integer y){ // Autoboxing
        return x+y; // Unboxing => 객체끼리 덧셈이 안되는데 자동으로 기본자료형으로 변형되서 계산
                    // return될때는 다시 출력형인 Integer로 Autoboxing이 이루어짐
    }

    public static <T> T bypass (T x){
        return x;
    } // T에는 원내 primitive 올수없음
//    public static <T> T add (T x, T y) {
//        return x + y;
//    } // 요거는 안됨 요거는 불편함 // 사칙연산에 대한 구현은 직접 해야함

    public static void main(String[] args) {
        Integer integer = new Integer(10); // 클래스니까 객체생성가능, 객체의 초기화값 입력하는방식
        Integer integer1 = Integer.valueOf(10); // Integer 클래스에 valueof라는 스태틱메소드를 사용
                                                // 위에랑 똑같음

        Character character = new Character(('d'));
        Character character1 = Character.valueOf('f');

        // Autoboxing
        Integer integer2 =4; //바로 입력가능
        System.out.println(add(4,2)); // 6
                                // Integer를 바로 pirmitive타입으로 입력가능하도록 Autoboxing

        //왜 이딴걸 쓰나? => 이걸 사용하면 제네릭을 사용할 수 있다.
        //제너릭
        bypass(5); // autoboxing
        // T: Wrapper class인 Integer로 결정됨
        // 5 -> new Integer(5) (Autoboxing)로 제너릭에 입력이될수있게됨
        // java 1.5 부터 지원하는 기능으로 primitive 타입 간단하게 입력할수있게 도와 줌
        // 객체생성하는게 생략이 되있는게 Autoboxing

        // 문자열 <-> 기본자료형
        int x = Integer.parseInt("100"); // pare+자료형 정적 메소드
                                            // String에서 primitive 타입으로 변환해주는 메서드
        int y = new Integer("100"); // 오버로딩이 되어있어서 String을 바로 입력받을 수 있다.
                                        // deprecated : 언젠가는 안쓰일문법이니 알아두라우
        int z = Integer.valueOf("200");
        System.out.println(z);
        // x가 가장 권장되는 방식

        //언박싱
        int m = new Integer(10);// 기본 자료형이 필요한 자리에 Wrapper class 객체가 있을 경우 자동으로 unboxing이 이루어짐
        // 객체로사용될대는 auto로사용 객체가 프리미티브되야될땐 unboxing




    }
}
