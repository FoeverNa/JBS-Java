package homework.hw02;

import java.util.Scanner;

public class InputOutput {
/**
 * 입력받아 처리하기
 *
 * 3개의 정수를 입력받아, 그 중 최대값을 출력하고자 한다.
 * 이를 수행하는 프로그램을 작성하시오.
 *
 */

public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    System.out.println("최대값을 구할 숫자 3개를 입력하시오");
    int a = scanner.nextInt();
    int b = scanner.nextInt();
    int c = scanner.nextInt();

    int x = a>b ? a :b;
    int y = x > c ? x : c;

    System.out.println(y);


}

}
