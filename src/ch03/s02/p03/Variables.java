package ch03.s02.p03;

/**
 * 변수 (Variables)
 * 클래스에 쓰이는 다양한 변수
 *   - 클래스 멤버 변수 (static variable, class variable)
 *   - 인스턴스 멤버 변수 (member variable, attribute, ..)
 *   - 로컬 변수 (local variable)
 *   - 로컬 파라미터 변수 (local parameter variable)
 *                      (arguments)
 *
 */

public class Variables {
    static int classVar; // static 자료형 변수명; // 클래스 멤버 변수, 스태틱 변수(정적 변수)
    int instanceVar; // 인스턴스 멤버 변수, 필드, 속성

    public void method(int paramVar) { // paramVar : 로컬 파라미터 변수
        System.out.println(paramVar);
        int localVar; // 로컬 변수 // 메서드 안에 속하는 로컬 변수
        //System.out.println(localVar); // localVar은 0으로 초기화 안되서 바로 출력 불가
        localVar = 10; //메서드 안에서 자유롭게 접근가능
        System.out.println(localVar); // 값 대입 후에는 출력가능
        {
            localVar = 30; // 메소드 안에 {}에서도 접근 가능
            int localVar2 =20;
        }
        System.out.println(localVar); // 블록 내에서 수정한 것도 반영됨
        // locaVar2 =40;// {}밖에서 접근 불가, 생명주기가 끝났다. Life-Cycle 끝났다.
    }
}

class variableTest{
    public static void main(String[] args){
        System.out.println("클래스 변수"); // 클래스에 속하기에 인스턴스 안만들어도 존재// 특이 케이스
        System.out.println(Variables.classVar);//0 으로 초기화됨 => 어떤 변수는 초기화 하지 않아도 자동으로 된다는 것은 특이한케이스
                                                // 클래스 변수는 0으로 초기화 된다!!
        /// 클래스 변수는 클래스 이름으로 바로 접근 가능
        Variables.classVar = 10;
        System.out.println(Variables.classVar);//10
        System.out.println("");

        System.out.println("인스턴스 멤버 변수");
        Variables var = new Variables();
        System.out.println(var.instanceVar);//0 // 인스턴스 변수도 0으로 초기화됨 - 특이 케이스
        //Variables.instanceVar // 접근 불가능, 인스턴스 만들어야 객체(실체)가 생기기 때문
        var.instanceVar = 20;
        System.out.println(var.instanceVar);//20
        System.out.println("");

        Variables var2 = new Variables();
        System.out.println(var2.instanceVar); // 0// 0으로 초기화된 새로운 객체 생성

       // System.out.println(var2.classVar);//10// 인스턴스로도 클래스변수에 접근 가능, but 이러한 접근은 권장되지 않음
                                          // 클래스명으로해서 클래스전체에 속한 변수라는 것을 명확히 하며 사용하라는 뜻
        System.out.println("");






        //Variables.method(); // 잘못된 접근, 객체로 접근해야함

        var.method(9);

        // System.out.println(var.method(9)); //return값이 있어야 가능함


    }
}
