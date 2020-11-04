package ch03.s04.p04;

/**
 * super 키워드
 * this가 자기 자신의 객체를 참조하듯,
 * supe는 부모 객체를 참조한다.
 *
 * super.super라는 식으로 부모의 부모는 참조할 수 없다.(조무보는 할수없다)
 */

class Foo{
    String x = "Foo";


    public Foo(String x) {
        this.x = x;


    }
}

class Bar extends Foo{
  // String x = "Bar"; // 멤버 변수명이 부모와 겹치면 재정의 됨 // 그러나 부모 변수에는 영향을 주지 않음
                       // => 이것도 지우면 Foo Foo Foo 출력, 마지막으로 부모변수로 접근하게됨

    // 부모클래스에 기본 생성자를 사용하는 경우에는 super로 호출 안해줘도됨
    // 하지만 부모클래스에 생성자가 있으면 자식클래스에서 생성자를 호출 해주어야함
    // 부모클래스에 파라미터 생성자가 있으면 호출해 주어야 한다.

    public Bar(String x, String x1) {//파라미터가 겹치니까 x, x1으로 다르게해준것
        super(x); //부모 클래스의 생성자를 얘기함. this와 마찬가지로 첫줄에 써야 합니다. 부모클래스 생성자 호출
                  // 부모 객체를 먼저 생성하고, 그 다음에 자식 객체를 생성할때 이 super에서 이루어지는 것..아하
                  // 그니까 자식 클래스만들때 생성자에 포함시키면 무조건 객체 생성할때마다 부모 클래스를 생성할 수 있겠구나나
                    // 손자 클래스를 만들때는 할아버지 부모 객체가 생성됨
       this.x = x1;
    }

    public void method(){

//       String x = "method"; // 이렇게 지우면 Bar BAR Foo 실행됨 // 로컬 변수 -> 멤버변수> 부모의 멤버변수 순으로 접근하게됨
       System.out.println(x); // 로컬 변수
       System.out.println(this.x); // 자기 자신의 객체에 접근 가능
       System.out.println(super.x); // 부모 객체에 접근 가능
   }
}

//아무것도 상속하지 않은 경우, Object 클래스를 상속하는 것과 같다.
class Jaemi extends Object{
    public void method() {
        // super. // 해보면 여러가지 메소드 속성 사용할 수 잇음. 사실 super안해도 사용할수잇음. 그래서 모든 클래스는 object의 속성포함함
    }
}

public class Main {
    public static void main(String[] args) {
        Bar bar = new Bar("","");
        bar.method(); //method
                      //Bar
                      //Foo

        //자식 객체 생성을 하면,
        //부모 객체를 먼저 생성 하고, 그 다음에 자식 객체를 생성
        //foo 인스턴스 생성후 Bar 인스턴스 생성하게됨
        //자식 여러명이 있으면 따로따로 부모의 객체를 생성하게됨
        //Perosn이라는 타입명으로 만들어도 다 따로따로 인스턴스가 존재할수 있는 이유.

    }

}
