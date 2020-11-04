package ch03.s07.p01;

/**
 * 부모 클래스 타입으로 자식 클래스 타입의 객체를 참조하는 특징
 */

class Foo {
    public void methodA() {
    }
}

class Bar extends Foo{
    public void methodB(){}
    @Override
    public void methodA() {
        super.methodA();
    }
}

public class Poly01 {
    public static void main(String[] args) {
        Bar bar = new Bar(); // 자식 객체를 생성한 것
        Foo foo = (Foo)bar; // 부모 클래스 타입으로 자식 객체를 래퍼런스함
        // 여전히 힙 영역에는 자식 객체가 있습니다.

        foo.methodA();
        //foo.methodB();// 이 경우 자식 클래스 메소드는 사용이 불가능
        //문법적으로 사용이 불가. Foo 타입이기 때문에 자식객체이지만 문법적으로 제한이됨

        Foo foo1 = new Foo();
//        Bar bar1 = (Bar)foo1; // 자식 클래스 타입으로 부모 객체를 받음
//        문법오류X지만 실행시 오류가 발생(런타임 오류)
//        bar1.methodA(); // 문법오류 x 애초에 성립이 안됨
//        bar1.methodB(); // 문법오류 x
        // 문법적으로는 변수에 자식객체가 들어있는지 부모객체가 들어있는지는 알수가없음 => 그래서 문법ok
        // 위 아래 bar1은 문법적으로 똑같이 인식함
        // 자식 클래스 자료형으로 객체를 보려 하지만, 부모 객체 부분만 있기 때문에
        // 런타임에서 오류를 발생시킴 = > 실행은 됬지만 오류가 발생하는 상황(실행하기 위한 문법은 성립)
        // 클래스가  Bar > Foo 인 상황에서  Bar에 Foo를 대입을 하려고 하니까 빈부분이 생기고 에러가생김 => 정보부족


        Bar bar1 = (Bar)foo; // 자식 클래스 타입으로 자식 개체를 받음(원래있던자리로도랑옴)
        // 힙 영역에는 계속 자식 객체가 있었던 것.
        bar1.methodA();
        bar1.methodB();

    }
}
