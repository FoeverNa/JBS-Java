package homework.hw06;

/**
 * MyMath 클래스 구현하기
 * 인스턴스를 생성할 수 없는 MyMath 클래스를 구현하시오.
 *
 * MyMath 클래스는 다음 정적 변수를 가진다.
 * PI = 3.1415927;
 * E = 2.718281;
 *
 * MyMath 클래스는 다음 정적 메소드를 가진다.
 * min - 정수 또는 실수를 여러개 입력받아 최소값을 구한다.
 * max - 정수 또는 실수를 여러개 입력받아 최대값을 구한다.
 * abs - 정수 또는 실수를 입력받아 절대값을 구한다.
 * floor - 실수를 입력받아 내림 연산한 정수를 출력한다.
 * ceil - 실수를 입력받아 올림 연산한 정수를 출력한다.
 */

public class MyMath {

    public static double PI = 3.1415927;
    public static double E = 2.718281;

    private MyMath(){}

    //min()
    static public int min(int... params) {
        int minVal=0;

        for(int i : params){
            minVal = i < minVal ? i  : minVal;
        }

        return minVal;
    }
    static public double min(double... params) {
        double minVal=0;

        for(double i : params){
            minVal = i < minVal ? i  : minVal;
        }

        return minVal;
    }
    //max()
    static public int max(int... params) {
        int maxVal=0;

        for(int i : params){
            maxVal = i > maxVal ? i  : maxVal;
        }

        return maxVal;
    }
    static public double max(double... params) {
        double maxVal=0;

        for(double i : params){
            maxVal = i > maxVal ? i  : maxVal;
        }

        return maxVal;
    }
    //abs()
    static public int abs(int i){
        return i >0? i : -i;
    }
    static public double abs(double d){

        return d>0? d: -d;
    }
    //floor
    static public double floor(double d){


        return  d>= 0 ? d-( d % 1) : d -  ( 1 + d % 1);


    }
    //ceil
    static public double ceil(double d){

        return d > 0 ? d  + ( 1 -(d % 1)) : d - (d % 1);

    }
}


class MyMathTest {
    public static void main(String[] args) {

        // MyMath m = new MyMath(); // 인스턴스 생성 불가
        System.out.println(MyMath.PI);
        System.out.println(MyMath.E);
        System.out.println(MyMath.min(2, 3, -4, 6, 3.0, 4.2, 7.3, -1.1, -4.2));
        System.out.println(MyMath.max(7, 0, 6, 16, -4, 3.0, 9.0, 21.0));
        System.out.println(MyMath.abs(4));
        System.out.println(MyMath.abs(-2.3));
        System.out.println(MyMath.floor(0));
        System.out.println(MyMath.ceil(-3.12312245));
    }
}
/**
 * 메서드 안에 불필요한 변수 생성하지 말기
 * 간단한 조건식은 삼항 연산자로 구하기
 * floor 와 ceil구할 때 입력값이 음수일 경우 생각 했어야 했음
 */