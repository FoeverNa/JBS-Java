package ch05.s05.p04;

// 람다식 다양한 표현 형식

@FunctionalInterface
interface Runner{
    String run(String x); // 단하나의 abstrac mehod를 가지고있음

}

@FunctionalInterface
interface RunnerTWO{
    String run(); // 단하나의 abstrac mehod를 가지고있음

}

public class Main {
    static void useRunner(String x, Runner runner){
        System.out.println(runner.run(x));

    }
    static void useRunnerTwo(RunnerTWO runner){
        System.out.println(runner.run());

    }

    public static void main(String[] args) {

        // 기본형
        useRunner("안녕", (String x) -> {return x;}); // 가장길게쓴폼 // 입력파라미터에 자료형 입력나으
        useRunner("안녕", x -> {return x;}); // 입력 파라미터 1개면 () 생략 가능
        useRunnerTwo(() -> { return "안녕";}); // 입력 파라미터가 없으면 괄호 생략불가능
        useRunner("안녕", (x) -> {
            return x; // 세미콜론이 들어가는 경우 중괄호 필수 => 여러줄 작성 시, 이때, return도 필요함
        }); // 메서드 처럼 사용도 가능ㅎ다ㅏ
        useRunner("안녕", x ->  x); // Expressiono을 바로 쓰면 알아서 retrun 해 준다 (Expression 람다)
        // IDE에서 정리를 해주기 때문에 크게 걱정은 아해도되고 가장단순한 형태로 사용하는게 좋다
    }
    
}