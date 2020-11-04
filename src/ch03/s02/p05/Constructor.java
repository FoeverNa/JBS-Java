package s02.p05;

/**
 * 생성자 (Constructor)
 * 클래스에서 인스턴스를 생성할 때 사용하는 메서드
 * new 키워드를 사용할 때 호출되는 메서드
 *
 * 기본 생성자 (Default constructor)
 * 파라미터 생성자(Parameter constructors)
 * --> 여러개의 생성자를 오버로딩 할 수 있음
 *
 */

public class Constructor {
    int x;
    int y;
    String z; //  3개다 인스턴스 변수

//    public Constructor() {
//        this(0,0,"");
//    }// 기본 생성자, 구현하지 않아도 알아서 생긴다.(생략가능)

    public Constructor() {
        this(1, 2, "");
    }

    //생성자 중에 외부에서 사용할 수 없는 경우가 있다 정도만 알아두셈 // 외부에서 호출이 불가능한 것도있다.
//    private Constructor(){    }

    //Constructor를 오버로딩함
    //파라미터 생성자
    public Constructor(int x, int y, String z) {
        this.x = x; // this는 멤버 변수를 표기하기 위해 사용될 수 있다.
        this.y = y;
        this.z = z;
    }

    //생성자는 클래스이름, return이없기에 return type적지 않음
    public Constructor(int a, int b) {
        this(a, b, "");  // 이전 생성자를 불러오는 this.
        //this.는 무조건 첫줄에 사용할 수 있다.
        //가장 긴 파라미터를 기준으로 짧은것을 this로 불러올 수 있다.
    }
    //this는 객체를 참조하는것
    // 순서에 상관없이 코드전체를 보고 실행되기에 아래의 생성자를 위의 생성자가 참조하는것것
    // 코드 중복을 최소화 하기위해서 this를 통해 생성자를 불러온다.
    // 코드 중복을 최소화하는 것은 실수를 방지 하게 해주는 기능을 함 . 그래서 중복최고하고 연결할 수록 좋은 코드가 됨

}

class ConstructorTest {
    public static void main(String[] args) {
        Constructor c = new Constructor(); // 기본 생성자 호출
        //System.out.println(c.x+","+c.y+","+c.z);//0,0,null //기본생성자
        // z의 경우, 클래스이기 때문에 null로 초기화 됨
        // null -> 아무것도 참조하고 있지 않다. // 참조형 변수의 경우 null로 초기화
        System.out.println(c.x + "," + c.y + "," + c.z);//1,2,초기화//생성자 수정 이후

        Constructor c1 = new Constructor(10, 20, "파라미터생성자");
        System.out.println(c1.x + "," + c1.y + "," + c1.z); // 10,20,파라미터생성자

        Constructor c2 = new Constructor(10, 20);
        System.out.println(c2.x + "," + c2.y + "," + c2.z); //10,20,


    }
}

