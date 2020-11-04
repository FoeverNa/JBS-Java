package ch02.p03;

import java.util.Scanner;

public class Main {

    /**
     * 자료 입출력
     * 출력 - print println printf, 포멧팅 방법
     * 앱력 - 키보드 입력 받기
     */
    public static void main(String[] args) {
        //출력 메소드
        System.out.println("String cㅜa be printed."); //문자열도 출력가능
        System.out.println('c');//문자도 출력가능
        System.out.println(40234); //정수도 출력가능
        System.out.println(1.52f); //float도 출력가능
        System.out.println(15.2434);// double도 출력가능

        //println은 여러가지 자료형을 입력할 수 있다.
        //ln == line에 약자이고 new line을 줄인말

        System.out.print("124124\r\n"); //  \r 캐리지리턴 - 맨첫줄로보내기
                                        // \n newline,, 요즘은 \n만쓰면됨
        System.out.println(""); // = System.out.print(\n);

        System.out.printf("%s is for string%n", "STRING"); //%s는 스트링자리, newline은 수동
        System.out.printf("%s %s %s\n","123","456","15234");
        System.out.printf("%b\n", true); // b=boolean
        System.out.printf("%d 0x%x 0X%X 0%o \n",15,15,15,15); //d=10진수, x= 16진수 , o=8진수
                                    //10진수와 구분위해 16진수에는 앞에0x를 붙이고 8진수에는 0을붙임
        System.out.printf("%f\n",14.23); // f는 float 이 아닌 floating number로 float,double둘다 들어갈 수 있음
        System.out.printf("%f\n",14.23f); // %f로 float형도 입력 가능
        System.out.printf("%e\n",14.423); // 지수표현
        System.out.printf("%c %c\n", 'U','\u0042'); //U B 문자, 유니코드표현가능
        System.out.printf("%n"); //%n == new line

        System.out.printf("%5d.\n", 10); //우측정렬하고 5칸확보 // .은 공간보려고 찍음
        System.out.printf("%-5d.\n", 10); // 좌측정렬하고 5칸빈칸확보
        System.out.printf("%05d\n", 234); //5칸 앞에 0을 붙이면 빈곳은 00으로 표현됨
        System.out.printf("%3d\n", 104294);//3칸은 최소한 확보, 넘처도 출력은 다한다
        System.out.println("");

        //실수 자릿수 표현
        System.out.printf("%5.4f\n", 152523.456228); //5.4에 .4는 네자리까지 표현, 5는 최소 5칸확보하고 표현
        System.out.printf("%5.2f\n", 1.425); //2자리까지 표현하고 5칸 확보해야되니까 1앞에 빈칸하나채움
                                            // 표현된 자리부터 5칸을확보함
                                            //

        System.out.printf("%-5.2f\n", 1.425); //2자리까지 표현하고 뒤에 1칸 만듬

        //이외에도 시간, 날짜 표현하는게 있다 나중에 해보기


        //입력 메소드
        //변수 입력을 리터럴이 아닌 키도드 입력으로 하는 방법
        System.out.println("Input methods.");
        Scanner scanner = new Scanner(System.in); // System.in은 키보드 입력을 뜻함
        //자동 import되네오

//        String s = scanner.next(); // 공백으로 구분된 String을 입력 받는다. String
//        System.out.println(s);

//        // this is string을 입력하면 공백으로 3개의 다른 입력으로 인식함
//        System.out.println(scanner.next()); //"this"
//        System.out.println(scanner.next()); //"is"
//        System.out.println(scanner.next()); //"string"

        // this is 만 입력하면 "this" "is" 출력되고 기다림 그다음 string입력하면 "string"출력됨

        //next() 메소드는 입력이 있을 때까지 기다립니다.
        //Blocking 메소드라고 부른다(<-> non-blocking 메소드 - 기다리지 않고 바로넘어가는애)

        //공백으로 구분되는 정수 입력
//        System.out.println(scanner.nextInt());
//        System.out.println(scanner.nextInt());
//        System.out.println(scanner.nextInt());
//        System.out.println(scanner.nextInt());

        // 정수 4개를 입력 받는다. 다른 자료형 입력 받으면 에러발생

        System.out.println(scanner.nextDouble());
        System.out.println(scanner.nextDouble());
        System.out.println(scanner.nextDouble());


        // \n으로 구분이 되는 String을 입력 받는다.
        // \n은 키보드에서는 엔터를 의미함

//        System.out.println(scanner.nextLine());
//        System.out.println(scanner.nextLine());
//        System.out.println(scanner.nextLine());

    }
}
