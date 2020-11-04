package ch05.s05.p08.prac;

import java.util.function.*;

public class Main {
    public static void main(String[] args) {

        Function<String, String> function = x -> x;

        System.out.println(function.apply("wow"));

        BiFunction<String,String,Integer> biFunction = (s1,s2) -> s1.length()+s2.length();
        System.out.println(biFunction.apply("hello", "java"));

        LongFunction<String> longFunction = x -> String.valueOf(x);
        System.out.println(longFunction.apply(567875132131L));

        ToIntBiFunction<String,String> toIntBiFunction = (s1,s2)-> s1.length()+s2.length();
        System.out.println(toIntBiFunction.applyAsInt("hahahgo", "eldkzk"));

        // PToQFuntion : P - Q로 매핑하는함수...//기본형에서 기본형으로
        IntToDoubleFunction intToDoubleFunction = x -> (double)x;
        Function<Integer, Integer> integerFunction = x -> x;



    }

}
