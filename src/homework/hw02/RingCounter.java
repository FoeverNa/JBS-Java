package homework.hw02;

import java.util.Scanner;

public class RingCounter {

    /**
     * 링카운터 출력기
     *
     * 크기가 4인 링카운터는 아래와 같이 동작한다.
     *
     * 0b0001 -> 0b0010 -> 0b0100 -> 0b1000 -> 0b0001 ...
     *
     * 즉, 링카운터는 하나의 비트만 1의 값을 가지며 1의 위치가 N번마다 반복되는 형태로 동작한다.
     *
     * 링카운터의 크기 numBits 와 카운트된 횟수 numCount를 이용하여
     * 현재 링카운터의 값을 10진수로 출력하는 프로그램을 작성하시오.
     */

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        System.out.println("링카운터의 크기를 입력하세요:");
        int numBits = scanner.nextInt();
        System.out.println("카운트된 횟수를 입력하세요:");
        int numCount = scanner.nextInt();
        int shift = numCount  % numBits;


        int result = 1 << shift;


        System.out.println(result);


    }
}
