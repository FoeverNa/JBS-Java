package ch05.s05.p04;

@FunctionalInterface
interface Runner2<T>{
    String run(String s);
}

interface Runner2two<T>{
    String run();
}

public class Practice {

    static void useRunner2(String s, Runner2 runner2){
        System.out.println(runner2.run(s));
    }
    static void useRunner2two( Runner2two runner2){
        System.out.println(runner2.run());
    }

    public static void main(String[] args) {
        useRunner2("안녕", (String s) -> {return s;});

        useRunner2("어 안녕", s -> {return s;});

        useRunner2two(() -> "아 아닌데?");

        useRunner2("안녕", s -> {
            String s1 = s+"하세요";
            return s1;
        });
        useRunner2("어맞는데?", x -> x);
        //expression한줄일때 retrun생략하고 ;도 생략하고  Expression한줄만 띡쓰기~





    }
}