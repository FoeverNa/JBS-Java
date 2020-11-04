package ch04.s02;

/**
 * 인터페이스 (Interface)
 * - 클래스가 사용되는 방식 / 접점만을 선언하는 클래스와 유사한 틀
 * - 클래스 사용의 권고사항을 제시하는 역할을 하는 특수형태 클래스
 * - 아무런 구현이 되어 있지 않으며, 모든 메소드가 추상 메소드
 *
 * 인터페이스의 특징
 * - class 가 아니고 interace 키워드를 사용
 * - 멤버 변수는 항상 "public static final"이다.(객체멤버변수 없음)(생략 가능)
 * - 멤버 메소드는 항상 "public abstract"이다.(생략가능)(private은 의미가없지)
 * - 인터페이스의 상속은 implements 키워드 사용
 * - 클래스는 하나만 상속할 수 있으나, 인터페이스는 여러개 가능
 * extends하고 하나의 클래스만 적을 수 있지만, 인터페이스는 여러개 클래스를 적을 수 있음
 *
 */
// public, default 가능함

interface IFoo{
    public static final int MEMBER_VAR = 10; // (상수는 대문자와 '_'조합)
                                            // public static final 생략 가능

    public abstract void methodA(int param); // public abstract 생략가능
    void methodB(int param); // public abstract


}

class Foo implements IFoo{

    @Override
    public void methodA(int param) {
        System.out.println(param);
    }

    @Override
    public void methodB(int param) {
        System.out.println(param);
    }


    }


// 인터페이스 이름 convention
// interface IFoo <- class Foo // 상속하는 자식에서 부모쪽으로 화살표
// interface Printable <- class Bar
// 인터페이스에 대문자 I를 붙여주기도하고 형용사로 표현하기 좋은애들은 그냥 제목으로 표현함


/**
 * 인터페이스 간의 상속
 * 인터페이스로 인터페이스를 상속할 경우, extends(구현을 할때만 implements)
 * 클래스 <- 클래스 상속과 달리 다중 상속 가능
 */

interface Walkable{
    void walk();
}

interface Runnable{
    void run();
}

interface Jumpable extends Walkable, Runnable{
    void jump();// walk(), run()도 포함되어 있을 것
}

class Jumper implements Jumpable{

    @Override
    public void walk() {
        System.out.println("walk");
    }

    @Override
    public void run() {
        System.out.println("run");
    }

    @Override
    public void jump() {
        System.out.println("jump");
    }
}

/**
 * JDK 1.8 이후의 인터페이스
 *
 * - 인터페이스에는 default 메소드를 구현할 수 있음
 * - default 메소드는 항상 public이다.
 * - 인터페이스의 철학과 맞지 않으나,
 *    기존에 사용하던 인터페이스가 개선되어, 모든 자식 메소드에 동일하게
 *    구현되어야 하는 메소드가 생긴 경우에 쉽게 기능을 추가하기 위해 만들어짐
 *
 *    구현안된게 상속되면 자식클래스에서 중복으로 여러개의 메서드를 작성해야하는 불편을 줄이려고함
 */

interface IFooTwo {
    void abstractMethod();
    default void defaultMethod(){ // 디폴트 메소드 //오버라이드해도되고 안해도됨
        System.out.println("Default method"); // 구현을 해주어야함
    }
}

class FooTwo implements IFooTwo{

    @Override
    public void abstractMethod() {

    }
    @Override // Overriding not necessary
    public void defaultMethod(){
        System.out.println("Overriden default method");
    }
}


class SuperFoo{
   public void defaultMethod(){
        System.out.println("Default method in Super class");
    }
}

class FooThree extends SuperFoo implements IFooTwo{
    @Override
    public void abstractMethod() {
        System.out.println("abstract method implemnets");
    }
}


// 인터페이스의 static 메소드
interface IBar {
    static int x = 10;
    static void staticMethod(){
        System.out.println("static method"); //바로 구현 가능
    }
    public abstract void method();


}

class Bar implements IBar{

//    int x = 20;

    public void method(){
        System.out.println(x);
    }


}


public class Interface {
    public static void main(String[] args) {
        FooTwo footwo = new FooTwo();
        footwo.abstractMethod();
        footwo.defaultMethod();// "Overriden default method"

        FooThree foothree = new FooThree();
        foothree.abstractMethod();
        foothree.defaultMethod(); //Default method in Super class
        //부모클래스의 Default method가 실행됨(인터페이스의 default는 안됨)
        // 외우는것 이렇게 만들었데
        // 부모와 인터페이스에 모두 메서드가 있는 경우
        // 부모클래에 있는 메소드를 실행한다.
        // 클래스는 다중상속이 안되는데 인터페이스는 다중상속이 가능함
        // 인터페이스에서 다중상속이 가능한 이유는 여러 곳에서 메소드를 가져와서 겹쳐도상관이없음
        // 인터페이스는 구현부가 없으니 겹쳐도 상관이 없음
        // 그런데 default메서드가 생기면서 다중상속에문제가 발생해버림
        // 그래서 원래는 인터페이스는 구현안되있는게 기본이니까 클래스 메서드에 우선순위가 생김
        // 다중상속시 문제를 방지하기 위해, 인터페이스보다 일반 상속이 우선시
        // 일반상속은 다중상속이 안되니까 우선시해도 문제발생x
        
        IBar.staticMethod(); // 인터페이스명으로 클래스 메서드 호출 가능
//        Bar.staticMethod(); // 구현체인 자식 클래스로는 클래스 메소드 호출 불가능 //암기필요
        System.out.println(IBar.x); // 인터페이스명으로 인터페이스 정적변수 접근가능
        System.out.println(Bar.x); // 구현체 클래스로 인터페이스 클래스 정적변수 접근 가능

        Bar bar = new Bar();
//        System.out.println(bar.x); // 접근가능
        // bar.staticMethod(); // 접근불가

        System.out.println(bar.x);
        bar.method();

        IBar iBar= new Bar();

//        System.out.println(iBar.x); // 안되는게 맞고 인터페이스의 스태틱 변수에 접근함
        iBar.method();



    }
}


