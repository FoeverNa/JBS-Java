package ch03.s04.p05.subp02;


import ch03.s04.p05.subp01.ClassA;

class ClassAA extends ClassA {

    public void methodTest(){
        System.out.println(x);
        System.out.println(y); // 자식이라 다른 패키지에서도 사용 가능
        //protected는 private처럼 쓰지만, 상속한 경우 접근이 필요 할 때 사용함(내부적 구현 바꾸어야 할때)
        //System.out.println(z); // 접근불가
       // System.out.println(w);// 내부라 접근가능

        methodA();
        methodB();
//        methodC(); 접근불가
//        methodD(); // 내부이기에 접근 가능

        //private은 특성상 내부 구현을 위해서만 사용됨

        //protect나 prviate은 어떤걸 써도 상관이 없는 경우가 많다.
        //개발철학에 따라서 달리지는 경우라 이때는 이걸써야한다는 정해진것은 없음
        //protected에 변수를 상속받아서 쓰면 private이됨// 자식의 자식은 건들수가 없음
        // 아리송한것은 어쩔수없음 어떤미친넘이 이걸 private으로놧냐하면서 현업자도 싸움..ㅋㅋㅋ
        // 다만 어떤 접근제어자를 어떻게 접근할 수있는지 알아서 다른사람에 코드에 접근할수 있으면 됨

        //클래스에서는 public
        //default는 이미 사용하고 있음
        //protected  private는 전제가 말이안됨 // 이해못햇는데 한번천천히 따져보자
    }
}

public class ClassB {

    public static void main(String[] args) {
        ClassA obj = new ClassA();
        System.out.println(obj.x);
        // System.out.println(obj.y); //protected는 다른 패키지인 경우 자식만 가능
        // System.out.println(obj.z); // default는 다른 패키지면 안됩니다.
        //System.out.println(obj.w); // w는 private이라 접근 불가

        obj.methodA();
        //obj.methodB();
        // obj.methodC();
        // obj.methodD(); // 접근 불가 /IDE 빠른완성에 뜨지도 않음
    }



}
