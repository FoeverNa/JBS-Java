package ch03.s02.p04;

/**
 * 메소드 (Methods)
 * 객체가 하는 동작(행위)을 정의하는 작업을 수행하는 코드의 집합, 나열
 * 코드의 중복을 방지, 유지보수성을 향상, 코드의 가독성 개선
 * 메서드가 없는 프로그래밍은 상상두 할수가 없다
 */

class Foo{
    int value;
        }

class Bar{
    // 인스턴스 메소드( 보통 그냥 메소드라고 부름)
         // Return type(출력의 자료형)
    public int add(int x, int y){ // 입력 파라미터 (메서드 호출시 같이 입력)
        return x + y ; //반환 값(Return value)
    } // return 값이 있기 때문에 정의가 되있는것이다. 변수는 어떻다고했는데

    // int를 반환하는 add라는 메서드인데 int x,y를 입력받는다 => 선언!
    //선언(Declaration) - ~한 것이 있다. 실제 구현은 X
    //정의(Definition) - 선언 + 구현(초기화)

     //정적 메소드(static method), 클래스 메소드
    // vioid return type은 return이 없음.(return이라고 적어도 되지만 안적어도됨)
    public static void classMethod(){
        System.out.println("클래스메소드 호출");
    }

    public static void swapPrimitive(int x, int y){ // bar에 속성과는 상관없어서 static으로만듬
        // int x, int y의 사본이 넘어온다.
        // 기본형 타입(Primitive type)인 경우에 해당
        // call by value : 메소드 호출을 할 때, 값을 복사해서 넘긴다.
        // 값을 복사해온 이후에는 외부의 x,y변수와는 상관없는 애가됨. 그래서 밖에 변수가 안변함

        //신발을 바꿔신는 원리로
        int temp = x;
        x = y;
        y = temp;
    }
    public static void swapReference(Foo x, Foo y){
        // Foo -> class, class는 참조형 변수// 사본이 아닌 같은 것을 가르킴
        int temp = x.value;
        x.value = y.value;
        y.value = temp;
    }
                              // 여러개의 int를 입력 받는다.
                              // 입력 받은 결과는 배열로 주어진다.
                              // 가변 인자(Variable arguments) 인자가 변한다.
    public static int sumAll(int... params){
        int sum = 0;
        for (int val : params){
            sum+= val;
        }
        return sum;
    }
    // 메소드 오버로딩 Overloading
    // 함수명은 같고 입력인자가 다른 메서드
    // 입력 인자의 갯수도 다를 수 있다.
    public static float sumAll(float... params){
        float sum = 0;
        for (float val : params){
            sum+= val;
        }
        return sum;
    }
}

class Person {
    static String korWord = "사람";
    boolean isHungry = true; // 인스턴스 멤버 변수

    // 클래스 메소드
    public static void describe(){
        System.out.println(korWord+"입니다.");
        // 클래스 메소드는 클래스 변수를 사용할 수 있다.
        // 단, 객체에 속하는 속성은 사용할 수 없다.
    }

    // 인스턴스 메소드
    public void eat(){
        isHungry = false;
    }


}

public class Method {
    public static void classMethod() {
        System.out.println("클래스 메소드 호출");
    }

    public void instanceMethod() {
        System.out.println("인스턴스 메소드 호출");
    }

    // main 메소드에서 실행이 되는 것은 약속. main메서드도 static 메서드
    public static void main(String[] args) {
        Bar.classMethod(); //클래스메소드 호출 // 클래스메서드는 바로 콜이됨
        //Bar.add(1,2); // 콜이 되지 않음
        Bar bar = new Bar(); //Bar 인스턴스를 생성
        System.out.println(bar.add(1,2));//3 // bar.add(1,2)가 반환값으로 대치가됨
        bar.classMethod(); // 클래스메소드호출// 인스턴스에서 클래스메서드호출 가능 but 권장X

        Person p1 = new Person(); // p1 변수명 자료형 Person// Person이라는 인스턴스가 할당되어있음
        Person p2 = new Person();
        // 인스터느 메소드는 객체의 속성을 변화시키는 역할을 한다.
        p1.eat(); //메서드 콜
        System.out.println(p1.isHungry);//false
        System.out.println(p2.isHungry);//true  // p1만 먹었기에 p2는 아직 배고픔

        Person.describe();//사람입니다. // 특정한 객체의 속성을 바꾸는 역할을 하지 않는다
                                      // static 변수를 변화 시킬수는 있다.


        // Call by
        int x = 10;
        int y = 20;

        Bar.swapPrimitive(x, y);
        System.out.println(x+","+y); //10,20 // 변화없음 왜? 위에 메서드에 설명

        Foo f1 = new Foo();
        Foo f2 = new Foo();

        f1.value = 10;
        f2.value = 20;
        Bar.swapReference(f1, f2);
        System.out.println(f1.value + "," +f2.value);//20, 10 => 값의 변화에 영향을줌

        //params
        System.out.println(Bar.sumAll(2,3,4,1,3,24,346,1,2,34,-123,-12,2));// 287
        System.out.println(Bar.sumAll(2.2f,0.2f,3.2f,56.23f));// 287
        // println이 자료형에 상관없이 동장하는 이유 = 오버로딩


        //main 메서드에서는 어떻게 클래스 /인스턴스 메서드를 호출 하나 - 퀴즈

        classMethod();// 동일 클래스에 속한 클레스 메소드 호출(클래스 명 필요 X)
                     // 클래스메서드에서는 같은 클래스에 다른 클래스메서드를 바로 호출이 가능함
        Method.classMethod();// 생략 안할수도 있음
        Method m = new Method(); // 인스턴스 생성 안되어있으니 생성해야함.
        m.instanceMethod(); // 인스턴스 메소드 호출

    }
}
