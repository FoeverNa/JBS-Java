package ch03.s07.p02;

/**
 * 멤버 변수의 재정의는 선언된 객체의 타입을 따른다. (문법적으로 본다)
 * 메소드 오버라이딩은 메모리상의 객체 타입을 따른다. (실제 객체로 본다)
 *   (가상 메소드 호출; Virtual method call)
 *
 */

class Foo {
    static public String y = "Super Class";
    public String x = "Super";
    public void methodA(){
        System.out.println("Super Method");
    }
}

class Bar extends Foo {
    static String y = "Sub Class";
    public String x = "Sub";


    @Override
    public void methodA() {
        System.out.println("Sub Method");
    }
}
// 변수 x와 methodA둘다 오버라이드함

public class Ply02 {

    public static void main(String[] args) {
        Bar bar = new Bar();
        Foo foo = (Foo)bar;

        

        System.out.println(bar.x); //Sub
        bar.methodA(); // Sub Method

        System.out.println(foo.x); //Super
        // 멤버 변수의 재정의는 선언된 객체의 타입을 따른다(문법적으로 본다)

        foo.methodA(); //Sub Method
        // 메소드 오버라이딩은 메모리상의 객체 타입을 따른다.(실제 객채로 본다)
        // Foo에 있는 메소드는?
        // Virtual method call: 가상메소드 호출; 실제로 사용되지 않지만 호출되지는않음
        // 문법을 위해 호출이되어야되지(재정의된거니까)그러나 쓰이지는 않음

        System.out.println(Foo.y); // Super Class
        System.out.println(Bar.y); //Sub Class

        System.out.println(bar.y); // Sub Class
        System.out.println(foo.y); // Super Class
        // 스태틱 변수도 문법적으로 따라간다...
    }
}
