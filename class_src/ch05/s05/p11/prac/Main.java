package ch05.s05.p11.prac;

import java.util.function.*;

public class Main {

    public static void main(String[] args) {

        Consumer<String> consumer1 = s -> System.out.println("consumer1 :"+ s);
        Consumer<String> consumer2 = s -> System.out.println("consumer2 :"+ s);
        Consumer<String> consumer3 = consumer1.andThen(consumer2);
        consumer3.accept("yaho");

        Function<String, Integer> function1 = s -> s.length();
        Function<Integer, Long> function2 = value -> (long)value;
        Function<String, Long> function3 = function1.andThen(function2);
        System.out.println(function3.apply("egemoya"));
        Function<String, Long> function4 = function2.compose(function1);
        System.out.println(function4.apply("loveyoumuch"));


        BinaryOperator<String> op0 = (s1, s2) -> s1+ s2;
        UnaryOperator<String> op1 = (s2) -> s2;
        BiFunction opo2 = op0.andThen(op1);

        System.out.println(opo2.apply("oh", "yes"));








    }

}
