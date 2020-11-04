package ch05.s05.p13.prac;

import java.util.function.BinaryOperator;

public class Main {

    public static void main(String[] args) {
        BinaryOperator<String> minBy = BinaryOperator.minBy((o1,o2)-> o1.length()-o2.length());

        BinaryOperator<String> maxBy = BinaryOperator.minBy((o1,o2)-> -(o1.length()-o2.length()));

        System.out.println(minBy.apply("wow", "chingudle"));
        System.out.println(maxBy.apply("wow", "chingudle"));
    }
}
