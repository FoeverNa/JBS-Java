package ch05.s04.p02;

class Outer{

    class InstanceInner{
        int x = 1;

        void innerMethod(){
            int x =0;
            System.out.println(x); //0  // 로컬 변수
            System.out.println(this.x); //1 //this는 내부 클래스를 가르킴
            System.out.println(Outer.this.x); //2 // 클래스명으로 접근을함 클래스명.this가 외부클래스의 객체를 뜻함
            System.out.println(Outer.y); //Outer.y로 접근하려면 static변수여야함(클래스명으로 접근하는건 정적변수)
        }
    }

    private int x = 2;
    private static int y =3;

    public static void main(String[] args) {
        Outer outer = new Outer();
        InstanceInner inner = outer.new InstanceInner();

        inner.innerMethod();
    }
}


public class Main {
}
