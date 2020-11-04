package ch05.s04.p03;

class Outer{
    static{
        System.out.println("Outer static");
    }

    static class ClassInner{
        static {
            System.out.println("Inner static");}

        int innerVar = 1;
        static int staticInnerVar =100;

        void innerMethod(){
            Outer outer = new Outer();
            System.out.println(outer.outerVar);  // 객체를 생성한 후에 통해 접근가능. 내부클래스 생성시점에 외부클래스의 객체가 없음
                                               //내부클래스에서 외부클래스의 객체를 생성해야만 멤버변수에 접근할수있다.
            System.out.println(innerVar); //당연가능
        }

        static void staticInnerMethod(){
            System.out.println(staticInnerVar);
        }
    }
    private int outerVar = 2;

    public static void main(String[] args) {

        ClassInner.staticInnerMethod();
        //Outer static
        //Inner static
        //100
    }
}


public class Main {
}
