package ch05.s05.p11;

import java.util.function.*;

public class Main {
    public static void main(String[] args) {
        //Consumersms andThen만 있고 compse 없음

        Consumer<String> c0 = s -> System.out.println("c0" + s);
        Consumer<String> c1 = s -> System.out.println("c1" + s);
        Consumer<String> c2 = c0.andThen(c1); // 새로운 Consumer를 만든다
        //c0의 객체에 andThen이 default()메소드로 구현되어 있다
        c2.accept("String");
        //c0String
        //c1String

        // Function 계열은 입력 => 출력 => 입력 => 출력 타입이 연쇄가 되어야 한다.

        Function<String, Integer> func1 = s -> s.length();
        Function<Integer, Long> func2 = value -> (long) value;
        Function<String, Long> func3 = func1.andThen(func2);
        //입력이 Inter인 아이가 와야됨.. 왜나면 fun1의 출력이 Inter이기때문에// 출력은 상관이 없음
        //func3는 String입력받고 Long을 출력하는 애가 되야함... 와웅...
        System.out.println(func3.apply("Four"));//4

        //andThen과 거꾸로 실행되는 것..천천히봐보자
        Function<String, Long> func4 = func2.compose(func1);
        System.out.println(func4.apply("Four"));//4


        BinaryOperator<String> op0 = (s1, s2) -> s1 + s2;
        UnaryOperator<String> fnc0 = (s2) -> s2;
        UnaryOperator<String> op1 = s -> s + "";
        //2개의 값이 하나의 출력으로 나온걸 받아야하니까 Unary로 전환
        BiFunction<String, String, String> op2 = op0.andThen(op1);
        // 중간의 Funtion 계열이 있을 수도 있기 때문에
        // 최종 조합 결과는 Function 계열로 받아 주어야 함함

        //String만 사용하는 것이지만 andThen 입장에서는 어떤게 들어가있는지 모름
        //andThen 입장에서는 중간에 Operator가 아니라 Function이 들어갈 수 도있으니까
        // 전체가 Operator계열이라고 확실할 수 없음.. 그래서 BiFunction이라고 해야한다..
        //= > 이번 과제하면서 이해를 해보길 바란데...이런걸 사용해야하나보다

        Function<String, String> op3 = op1.compose(fnc0);
        // 이부분확인해야함함

        // 그
        //입력이랑 출력
        //개수가달라서
        //안된거였더거같아요
        //두개다 입력은 2개인데
        //출력은 하나라서
        //그냥 UnaryOperator쓰셔도
        //될거 같아요


    }
}
