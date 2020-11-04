package ch05.s05.p13;

import java.util.function.BinaryOperator;

public class Main {
    public static void main(String[] args) {
        BinaryOperator<String> minBy = BinaryOperator.minBy((o1,o2) -> o1.length() > o2.length() ? 1 : -1);

        //위 예는삼항연산자로는 같은경우 0을 출력 하는경우가 있어야되는데 없기때문에 엄밀한 Comparator는 아니다
        // 파라미터 o1, o2에 String이라고 써주지 않는한 앞에 BianrayOperator<STring>이라고 언급해줘야 타입을 infer할수 있다

        BinaryOperator<String> maxBy = BinaryOperator.maxBy((o1, o2) -> o1.length() > o2.length() ? 1: -1);

        System.out.println(minBy.apply("abc", "cd")); // cd
        System.out.println(maxBy.apply("abc", "cd")); // abc

        System.out.println(minBy.apply("abc", "cde")); // cde
        //0일 경우에는  왜 난 cde가 출력되는지 알아보기...



    }
}
