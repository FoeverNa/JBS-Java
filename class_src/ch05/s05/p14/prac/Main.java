package ch05.s05.p14.prac;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class Main {
    public static void main(String[] args) {
        String[] strings = {"son", "ali", "kevin","dbra","endom","cane"};

        Arrays.sort(strings,String::compareTo);
        System.out.println(Arrays.toString(strings));

        Function<String, Integer> func1 = Integer::parseInt;
        System.out.println(func1.apply("3124"));

        String s = "yaho";
        Predicate<String> pred1 = s::equals;
        System.out.println(pred1.test("yaho"));

        UnaryOperator<String> fnc = String::new;

        IntFunction<String[]> fnc2 = String[]::new;

        String[] stirngs = fnc2.apply(100);

        class Foo{
            int x = 10;
            public int compareTo(int x, int y){
                return -(x-y);
            }
        }

        Integer[] ints = {0, 5, 10, 15, 20};
        Foo foo = new Foo();
        Arrays.sort(ints,foo::compareTo);
        for(int value: ints){
            System.out.printf("%d ", value);
        }






    }
}
