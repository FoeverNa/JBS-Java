package homework.hw04;
/**
 * 배열의 연속합 최대 구하기
 *
 * 정수 배열에서 연속된 값의 합의 최대값을 구하시오.
 *
 * ex1) {1, 45, -2, 5, -6} => 1 + 45 + (-2) + 5 = 49
 * ex2) {-4, 5, 12, -7, 52, -5, 7} => 52
 *
 *
 * 인자
 *  * integers: 정수 배열
 */

public class MaxSum {
    public static void main(String[] args) {
//        int [] integers = {-4, 7, 14, 9, -5, 4, 16, -22, 31};
//        int [] sum = new int[9];
//        int maxValue = 0;
//
//        for(int i= 0; i < integers.length; i++){
//
//            if(i==0){
//                sum[i] = integers[i];
//            } else{
//                sum[i] = integers[i] + sum[i-1];
//                maxValue= maxValue > sum[i] ? maxValue : sum[i];
//                System.out.println(sum[i]);
//            }
//
//        }
//        System.out.println("연속된 값의 합의 최대값 : "+maxValue);

        //풀이 예시
        //2개의 변수를 통해 풀수 있다.
        //나는 무조건 앞에것을 더햇는데 앞에 자리를 자를수 있다느것을 생각못햇네?

        int [] integers = {-4, 7, 14, 9, -5, 4, 16, -22, 31};

        int maxSum = 0;
        int currentSum = 0;
        for (int elem: integers) {
            currentSum += elem;
            currentSum = currentSum > elem ? currentSum : elem; // 이전에 더한값보다 지금 더하는값이 더큰가?
            maxSum = maxSum > currentSum ? maxSum : currentSum;
        }
        System.out.println(maxSum);

    }
}