package ch04.s01;

/**
 * 추상 클래스 (Abstract Class)
 * - 일부 메소드가 구현되지 않고, 선언만 되어있는 클래스
 *    - 자식 클래스에서 이것을 반드시 구현하게 끔 강제하는 것
 *    - 효과 : 필요한 모든 클래스가 구현 될 수 있도록 하여 안정성을 높임
 */

abstract class AbstractFoo{
//(추상)
    int x, y;

    public void method() {
        System.out.println("method");
    }
                                        // 세미콜론을 잊지 말아야 합니다. => 시험나옴!!!!
    public abstract void abstractMethod(); // 선언만 하고 구현하지 않음
}

class Foo extends AbstractFoo { //만들자마자 화냄 추상메서드 하라고 이때 창띄워서 implements 클릭해주면됨
    @Override
    public void abstractMethod() {
        System.out.println("implemented abstractMethod");
    }

}


public class AbstractClass {
    public static void main(String[] args) {
        //   AbstractFoo afoo = new AbstractFoo(); // 추상클래스는 객체생성이 불가

        Foo foo = new Foo();
        foo.abstractMethod();// "implemneted absgractMethod"

        AbstractFoo afoo = (AbstractFoo) foo; // 부모클래스형으로 자식객체 받음
        afoo.abstractMethod(); // "implemented abstractMethod"
        // virtual method call
        
        // 아무것도 구현하지 않앗어도 자식메서드를 받을 수 있음

        // 추상 클래스는 객체 생성은 불가하지만 구현된 자식 클래스의 객체는 받을 수 있다.


    }
}
// 추상클래스를 어디에 쓸수잇을까? 뒤에 예제로 이어짐