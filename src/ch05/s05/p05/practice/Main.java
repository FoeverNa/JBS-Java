package ch05.s05.p05.practice;

@FunctionalInterface
interface IFoo{

    String method();
}

public class Main {

    static void functionalMethod(IFoo foo){
        System.out.println(foo.method());
    }

    void methodA(){
        functionalMethod(() -> {
            System.out.println("this "+ this);
            System.out.println("OuterClass.this: " + Main.this);
            return "Lambda expression used.";

        });

        functionalMethod(new IFoo() {
            @Override
            public String method() {
                System.out.println("this: " + this);
                System.out.println("OuterClass.this: " + Main.this);
                return "Anonymous local inner class used.";
            }
        });

    }

    public static void main(String[] args) {

        new Main().methodA();

    }
}

