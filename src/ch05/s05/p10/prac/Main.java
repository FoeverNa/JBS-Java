package ch05.s05.p10.prac;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class Main {
    public static void main(String[] args) {

        UnaryOperator<String> unaryOperator = s -> s;
        System.out.println(unaryOperator.apply("어뿅"));

        BinaryOperator<String> binaryOperator = (s1,s2) -> s1+s2;
        System.out.println(binaryOperator.apply("어뿅", "하지말라고"));



    }
}
