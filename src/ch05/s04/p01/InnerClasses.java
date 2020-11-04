package ch05.s04.p01;

class Outer {
    class InstacneInner{ // 인스턴스 내부클래스
        int innerMemberVar = 1;
        // static int statiVAr =20;// 스태틱변수가 있을수 없음
        static final int CONSTANT_VAR = 1000;

        void innerMethod(){
            System.out.println(innerMemberVar);
            System.out.println(outerMemberVar);// outer private 멤버변수도 접근이가능
                                              // 이미 외부클래스객체가 존재하기때문에가능

        }

    }

    private int outerMemberVar = 2;

    void outerMethod(){// 인스턴스에 속하는 멤버메서드
        InstacneInner obj = new InstacneInner(); // 내부클래스 객체생성가능
        obj.innerMethod(); // 해당객체로 메서드에 접근도가능
    }

    public static void main(String[] args) {
        // main과 같은 정적 메서드의 경우는 인스턴스 내부클래스의 바로접근할수없음

        // new InstacneInner(); //Error

        Outer outer = new Outer();
        InstacneInner inner = outer.new InstacneInner();// 어색하지만 이렇게 쓰는거래// 외워주기
        inner.innerMethod(); // 그다음에 호출할수 있게 된다
    }
}

public class InnerClasses {
}
