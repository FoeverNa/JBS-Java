package ch05.s05.p09;

import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;
import java.util.function.UnaryOperator;

public class Main {

    public static void main(String[] args) {

        // Operator는 그냥 Operator Interface는 없음
        // 입력이 1개인 것을 Unary를 붙여서 표현
        UnaryOperator<String> operator =  s -> s+ ".";
        System.out.println(operator.apply("왔다"));//왔다.

        //입력이 1개건 2대건 입력 출력이 모두 같은 자료형을 사용한다
        BinaryOperator<String> operatorTwo = (s1, s2) -> s1 + s2;
        System.out.println(operatorTwo.apply("나", "왔다"));//나왔다



        IntUnaryOperator op = value -> value * 10;
        System.out.println(op.applyAsInt(5)); // 50
        //P type은 AsInt 붙는다
        // LongUnaryOperator, DoubleUnaryOpertor

        IntBinaryOperator intBinaryOperator = (v1, v2) -> v1*v2;
        System.out.println(intBinaryOperator.applyAsInt(3, 5));
        //LongBinaryOperator, DoubleBinaryOperator



    }
}
