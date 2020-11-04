package ch03.s04.p05.subp01;

public class ClassA {
    public int x;
    protected  int y;
    int z; // default(= package)
    private int w;

    public void methodA() {}
    protected  void methodB() {}
    void methodC() {} // default(=package)
    private void methodD(){}

    public void methodTest(){
        System.out.println(x);
        System.out.println(y);
        System.out.println(z);
        System.out.println(w);// 내부라 접근가능

        methodA();
        methodB();
        methodC();
        methodD(); // 내부이기에 접근 가능

        //private은 특성상 내부 구현을 위해서만 사용됨
    }


}


class ClassTest { // 같은 패키지인 경우우
    public static void main(String[] args) {
        ClassA obj = new ClassA();
        System.out.println(obj.x);
        System.out.println(obj.y);
        System.out.println(obj.z);
        //System.out.println(obj.w); // w는 private이라 접근 불가

        obj.methodA();
        obj.methodB();
        obj.methodC();
        // obj.methodD(); // 접근 불가 /IDE 빠른완성에 뜨지도 않음
    }

}