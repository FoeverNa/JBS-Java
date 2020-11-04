package ch02.p01;


/**
 * 변수(Variable)
 * 변하는 수, 수학의 변수와 유사하다.
 * 변수의 타입, 변수명, 값을 할당
 * 변수의 선언
 * 변수에 값 할당(리터럴)
 * 변수명 규칙
 */
public class Variable {
    //상수의 변수명은 모두대문자로 사용하고 띄어쓰기는 _로함
    static int STATIC_VRIABlE = 10;

    public static void main(String[] args) {
	    int x;
	    x = 10; // 10 -> 리터럴, 순수한 값을 리터럴이라고 부름, 있는 그대로의 값
        System.out.println(x);

        int y, z, value; //여러개를 동시에 선언가능

        int valueOne = 10; //자료형 선언과 함께 값 대입가능
        int valueTwo = 20;

        int valueThree = 100, valueFour = 1000; // 같은 자료형의 변수를 옆으로 늘여서 선언

        //int int
        int intOne; //int는 변수명으로 사용불가 but int+@는 가능
        //int 4thword; // 숫자가 가장먼저 나올 수는 없음
        int val2ue1; //숫자가 중간에는 가능
        int 한글_됩니다; // 한글은 쓸수있지만 사용하지말아라 강사님이 욕먹는데

        //int Three&Four; // &과 같은 특수문자 불가능
        int $power; // 그중 $는 가능하지만 특수한경우에만 사용하기에 쓰지마세요

        // Code Convention

        int valueOfComputer; // 처음엔 소문자로시작하고 단어가바뀔떼마다 대문자사용 = camelCase= camelNotation
        // int PascalCase;//참고로 시작도 대문자 단어도 대문자하는것을 파스칼케이스
        int lowerCamelCase; // 카멜케이스
        int UpperCamelCase; // 파스칼케이스

        int _8thWord; //숫자먼저쓸땐 _(언더바)먼저 사용, 언더바는 특수문자 X




    }
}
