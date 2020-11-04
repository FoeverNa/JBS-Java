package ch05.s04.p05;

interface IFoo{
    void run();
    void walk();
}

class Foo implements IFoo{
    @Override
    public void run() {
        System.out.println("Normally run");
    }

    @Override
    public void walk() {
        System.out.println("Normally Walk");
    }
}

class AnonymousInnerClass{
   static void useIFoo(IFoo foo){ // IFoo를 구현한 객체를 입력받는다

        foo.run();
        foo.walk();
    }

    public static void main(String[] args) {
       //1.
        Foo foo = new Foo();
        useIFoo(foo); // Using Polymorphism

        // 2.
        class InnerClass implements IFoo{

            @Override
            public void run() {
                System.out.println("Run, Foo Run!");
            }

            @Override
            public void walk() {
                System.out.println("Walk... Foo.. Walk...");

            }
        }

        InnerClass ic = new InnerClass();
        useIFoo(ic); // 내부클래스지만 IFoo를 구현한 클래스를 넣어줌

        //이제 익명의내부클래스 이용할거야

        // 3.
        useIFoo(new IFoo() {// 인터페이스는 객체생성안되서 new못하는데 갑자기 된다?
                                // Ifoo를 impelments 한 클래스를 구현하는것임
            @Override
            public void run() {
                System.out.println("Run!!!");

            }

            @Override
            public void walk() {
                System.out.println("walk...");

            }
        })  ; //
        // 곧바로 클래스를 작성해서 객체를 생성해서 사용하고 끝나는것..Woo신세계
    }
}

interface IBar{
    void run(int x, int y);
}

class Bar implements IBar{
    @Override
    public void run(int x, int y){
        System.out.println("Bar");
    }
}

public class Main{
    static void runIBar(IBar bar){
        bar.run(0,0);
    }

    public static void main(String[] args) {
        runIBar(new Bar());

        class InnerBar implements IBar{

            @Override
            public void run(int x, int y) {
                System.out.println("innerBar");
            }
        }
        runIBar(new InnerBar());

        runIBar(new IBar() {
            @Override
            public void run(int x, int y) {
                System.out.println("Annoymous");
            }
        });

        //Thisi is Lambda!! Exprsion
        runIBar((x,y)-> {
            System.out.println("LAMBDA!!!");
        });// 익명클래스를 한번더 줄여서 쓴게 Lambda expresion
            // 얘가 run인걸 어떻게 알아? 구현하는 인터페이스에 메서드가 하나야됨
    }
}

