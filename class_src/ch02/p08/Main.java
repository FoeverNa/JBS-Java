package ch02.p08;

/**
 * N-D 배열 (N-D Arrays)
 * 배열이 배열을 담고 있으면, 다차원 배열이라 한다.(N-D Array)
 * 수학에서 말하는 점 - > 선 - > 면 - > 공간 -> 4차원
 */
public class Main {

    public static void main(String[] args) {
        // 다차원 배열의 선언
        int[][] ints; // int array를 담고있는 array// java-like
        int[] halfCStyle[]; // hybrid
        int oldCStyle[][]; // old...

        int [][] ints1 = new int[10][5]; // 길이가 5인 intArray를 담고있는 길이가 10인 intArrary
                                         // 앞에 있는 숫자가 더 큰 차원의 수
        int [][] ints2 = new int[10][];

        for(int i = 0; i< ints2.length; i++){
            ints2[i] = new int[5];
        }

        int[][] ints3 = new int[5][]; // 하위 차원의 길이는 달라질 수 있다.
        ints3[0] = new int[10];
        ints3[1] = new int[8];
        ints3[2] = new int[4];
        ints3[3] = new int[15];
        ints3[4] = new int[9];

        int[][] ints4 = {{1, 2, 3}, {4, 5, 6}}; // int[2][3] 길이가 3인 intArray가 2개가 있다.
        int[][] ints5 = {{1, 3 ,2}, {1,2}, {4,5,3}}; // int[3][]

        // 다차원 배열과 반복문

        for (int i =0; i < ints5.length; i++){ //ints5.length = 3
            System.out.printf("[%d] :", ints5[i].length);
            for(int j = 0; j < ints5[i].length; j++){ //ints5[i].length 3, 2, 3
                System.out.printf("%d ", ints5[i][j]);
            }
            System.out.println("");
        }

        for(int[]array : ints5){   //1차원 배열을 array[]로 가져옴
            for (int val: array){  //1차원 배열의 값을 val에 입력
                System.out.printf("%d ", val);
            }
            System.out.println("");
        }
    }
}
