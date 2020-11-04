package homework.hw03;

public class Fibonacci {
    /**
     * 피보나치 수열
     *
     * 피보나치 수열을 출력하는 프로그램을 작성하시오.
     *
     * 피보나치 수열의 점화식 : f(n) = f(n-1) + f(n-2)
     *                        f(1) = 0, f(2) = 1
     *
     * 0, 1, 1, 2, 3, 5, 8, ...
     *
     * 인자
     * seqLength: 출력하고자 하는 피보나치 수열의 길이
     */

    public static void main(String[] args) {

        int seqLength = 100;

        double previousNum = 0;
        double nextNum = 1;


        for(int i = 0 ; i < seqLength; i++) {

            System.out.printf("1.%.0f\n",previousNum);

            double sum = previousNum + nextNum;
            previousNum = nextNum;
            nextNum = sum;

        }
    }
}
