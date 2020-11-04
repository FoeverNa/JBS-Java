package ch05.s05.p08;

import java.util.function.*;

public class Main {

    public static void main(String[] args) {
        // Function은  T, R 두개의 자료형을 입력받게 되어있다
        Function<String, Integer> func = (s) -> s.length();
        System.out.println(func.apply("four"));//4 = > 4글자이기 때문에
        // Function에 메서드는 apply

        // Bi가 붙으면 '입력'을 2가지를 받을 수 있다는 의미// 출력 2개는 원래 불가능
        //T,U 입력받아서 R 출력
        BiFunction<String, String, Integer> funcTwo = (s,u) -> s.length()+u.length();
        System.out.println(funcTwo.apply("A", "BDEFGHIJK"));//10

        //P Type Function은 입력을 P 타입을 받는다.
        //R만 입력을 받음 -> 입력 타입을 P로 정해져잇음
        IntFunction<String> funThree = value -> String.valueOf(value);//Integer.parsInt()는 반대
        //IntFunction<String> funThree = value -> ""+value; // 이것도 같은 기능
        System.out.println(512);//512

        // ToP Type Function은 출력을 P타입으로 한다
        ToIntFunction<String> funcFour = (s) -> s.length();
        //출력이 P타입인 경우에는 AsP가 들어간다
        System.out.println(funcFour.applyAsInt("ABCDE"));//5

        //ToIntBiFunction<String,String> // Int출력을 하는 두개의 입력을 받는것
        // P Type은 Int, Long, Double 이 있다

        // PToQFunction : P -> Q로 매핑하는 함수
        IntToDoubleFunction funcFive;


        // IntToIntFunction은 없다 = > Function은 형태를 변환시키는 것이기 때문에 동일한 자료형은없다

    }
}
