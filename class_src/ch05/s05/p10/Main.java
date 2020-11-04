package ch05.s05.p10;

import java.util.function.BiPredicate;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {

        Predicate<String> pred = (s) -> s.length() ==4;
        System.out.println(pred.test("ABCD"));//true
        System.out.println(pred.test("ABCDE"));//false
        //Predicate 는 test()를 사용한다

        BiPredicate<String, Integer> pred2 =(s, v) -> s.length() == v;
        System.out.println(pred2.test("abcd",23 ));//false
        System.out.println(pred2.test("abcde",5 ));//true

        // P Type 입력가능하다
        IntPredicate pred3 = x -> x > 0;
        // LongPredicate, DoublePredicate

    }

}
