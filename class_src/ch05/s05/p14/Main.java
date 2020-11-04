package ch05.s05.p14;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class Main {

    public static void main(String[] args) {

        // 클래스명 :: 인스턴스_메소드
        String [] strings = {"fast", "campus", "best", "academy"};
        Arrays.sort(strings, String::compareTo); // 기존에 있는 메서드를 참조
        // 클래스명 String :: compareTo 인스턴스 메서드
        System.out.println(Arrays.toString(strings)); //comparTo없어도 정렬이 도ㅣ긴한다..
        //Comparator 인터페이스는 2개의 입력 o1, o2를 받음
        //compare(o1, o2) -> o1.compareTo(o2) 가 되는 것을 설명해주셨다
        // o1.인스턴스_메소드(o2) 로 호출되는 메소드가 사용됨
        System.out.println("");

        // 클래스::클래스_메서드
        Function<String, Integer> func = Integer::parseInt;
        //parseInt가 String 입력 받아서 Interger 출력하는애니까 Funtion으로 사용
        // 람다식 구현안받고 바로 메서드를 입력받을 수 있음

        //인스턴스::인스턴스_메소드
        String s = "String";
        Predicate<String> pred = s::equals;
        System.out.println(pred.test("String" )); //true
        System.out.println(pred.test("String2" )); //flase

        //o1, o2가 입력받는 것과 o 만 입력받는것에 차이 인식해야함
        // 클래스 입력하는경우 인스턴스가 필요하니 첫번개 객체와 두번재 객체 둘다 입력받음
        // 근데 객체사용할 경우 해당객체 + 1개만더입력ㄱ받으면됨

        // 생성자를 andThen, compose 등과 함게 사용 가능 (체인에 들어갈수 있음을 생각)

        // 클래스::new
        UnaryOperator<String> fnc = String::new;
        //String입력을 받아서 새로운 String 객체를 만들어줌 => 사용할수 있다정도만 이해해줘
        //생성자를 FuntionalInterface로 교체해서 사용할 수 있따

        //클래스[]::new -> 배열 생성
        //Int입력받아서 String[]을 출력하는애
        IntFunction<String[]> fnc2 = String[]::new;
        //new String[10];을 그대로 사용하는 것

        // 길이가 100짜리 String[] 생성하기
        String[] strings1 = fnc2.apply(100);


    }
}
