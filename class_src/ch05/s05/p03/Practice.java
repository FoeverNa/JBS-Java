package ch05.s05.p03;

@FunctionalInterface
interface Runner2<T>{
    T run();
//    T runTwo();
    default void method(){ }
}

public class Practice {

   static void useRunner(Runner2<String> runner2){
       System.out.println(runner2.run());
    }

    public static void main(String[] args) {
        Runner2<String> runner2 = () -> "thisisyo";
        Runner2<String> runner3 = () -> "himynmae";
       System.out.println(runner2.run());
       System.out.println(runner3.run());

       useRunner(() -> "return Strings");
       useRunner(runner2);
       // 하 알거같으면서도 모르겠다
        //  interface를 구현한 객체하나를 만들어서 해당 객체의
        // 유일한 메서드내용을 구현하는것 이라고 정리해보자




    }
}
