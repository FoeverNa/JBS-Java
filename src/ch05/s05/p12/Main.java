package ch05.s05.p12;

import java.util.function.DoublePredicate;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {

        DoublePredicate p0 = x -> x  > 0.5;
        DoublePredicate p1 = x -> x  < 0.7;
        DoublePredicate p2 = p0.and(p1);
        //두개의 true/false를 엮어서 논리연산을 함
        System.out.println(p0.test(0.9)); //true
        System.out.println(p1.test(0.9)); //false
        System.out.println(p2.test(0.9)); //false => true and false = false
        System.out.println("");

        System.out.println(p0.test(0.6)); //true
        System.out.println(p1.test(0.6)); //true
        System.out.println(p2.test(0.6)); //true => true and true = true
        System.out.println("");

        DoublePredicate p3 = p0.or(p1);

        System.out.println(p0.test(0.9)); //true
        System.out.println(p1.test(0.9)); //false
        System.out.println(p3.test(0.9)); //true => true or false = true
        System.out.println("");

        // negate = not
        DoublePredicate p4 = p0.negate();

        System.out.println(p4.test(0.9)); // not true = false;
        System.out.println("");

        // isEqual

        Predicate<String> eq = Predicate.isEqual("String");
        // eq와 입력된 값이 같은지 확인해주는 메서드
        System.out.println(eq.test("String")); // true
        System.out.println(eq.test("String!")); // false

        //클래스메서드라 위의 경우와 다르게 객체로 메서드로 접근한게 아니라 클래스 메서드로 접근한다는것이 다름
    }
}
