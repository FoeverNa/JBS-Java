package ch05.s05.p12.prac;

import java.util.function.DoublePredicate;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {
        DoublePredicate dp0 = x -> x>0.5;
        DoublePredicate dp1 = x -> x<0.7;
        DoublePredicate dp2 = dp0.and(dp1);

        System.out.println(dp2.test(0.6));

        DoublePredicate dp3 =dp0.negate();

        System.out.println(dp3.test(0.5));

        Predicate<String> eq = Predicate.isEqual("안뇽");

        System.out.println(eq.test("안넝"));
        System.out.println(eq.test("안뇽"));


    }
}
