package ch05.s05.p03;

// 람다식 사용 조건

@FunctionalInterface// 필수는 아닌데 적어줄 수 있다 => FuntionalInterface인지 미리 검사해주어서 구현전에 알수있게해준다
interface Runner<T>{
    T run(); // 단하나의 abstrac mehod를 가지고있음

//    T runTwo(); //abstrac method를 2개 이상 작성하면 람다식에 에러가 생김

    default void mehtod(){} // default method 구현되어있더라도 lambda와 상관없다
}

public class Main {


    static void useRunner(Runner<?> runner){// 와일드 카드에 특정한 자료형 넣을 수 있지만 ?적으면 뭐든 올 수 있다
                                            // 기본적으로는 지금 상황에서는 String으로 했어야 했음
                                            // 제네릭사용하면 기본적으로 어떤파라미터 사용할지 적어야 한다
        System.out.println(runner.run());

    } // Array.sort 메서드와 비슷 , 인터페이스를 입력받으니까


    public static void main(String[] args) {
        useRunner(() -> "This is how to use runner.");

    }

}
