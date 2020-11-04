package ch05.s05.p05;

// 람다식과 익명클래스 객체가 동일한 것은 아니다 라는 것의 증명// 그외에는 완전히 똑같이 동작함

@FunctionalInterface
interface IFoo {

    String method();
}

public class Main {

    static void functionalMethod(IFoo foo){
        System.out.println(foo.method());
    }

    void methodA(){
        functionalMethod(() -> { // 람다식은
            System.out.println("this: " + this);// 이 this가 왜 Main의 this?
                                                // => 람다식은 익명 클래스와 달리 내부 클래스가 만들어지지 않음
                                                // 실제로 함수처럼 구현이 된다
            System.out.println("OuterClass.this: " + Main.this);// 위와 같은객체
            return "Lambda expression used.";
        });

        functionalMethod(new IFoo() {// 익명 클래슥 만들어지고, 그 객체가 활욛된다는게 실제로 이루어짐
            @Override
            public String method() {
                System.out.println("this: " + this);// 애는 익명클래스의 객체가 this가됨
                System.out.println("OuterClass.this: " + Main.this);//외부 클래스인 Main객체의 this가됨
                return "Anonymous local inner class used.";
            }
        });

    }

    public static void main(String[] args) {

        new Main().methodA();

    }
}
