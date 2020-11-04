package homework.hw04;
/**
 * 행렬의 곱 계산하기
 *
 * 두 행렬의 곱을 구하는 프로그램을 작성하시오.
 * 행렬의 곱을 계산한 후에 행렬 형태로 출력하시오.
 *
 *
 * 인자
 * matA: N x M 행렬
 * matB: M x K 행렬
 */
public class MatMul {

    public static void main(String[] args) {
//        int[][] matA = {{1, 2, 3}, {4, 5, 2}};
//        int[][] matB = {{5, 2}, {6, 2}, {1, 0}};
//        int[][] mulMat = new int[matA.length][matB[0].length];
//
//        // mulMat (0,0) (1,1) 곱셈
//        for(int i = 0; i<matA.length; i++){
//            for(int j = 0; j<matA[0].length; j++){
//                 mulMat[i][i*1] += matA[i][j] * matB[j][i];
//
//            }
//        }
//        // mulMat (1,0)(0,1) 곱셈
//
//        for(int i = 0; i<matA.length; i++){
//            for(int j = 0; j<matA[0].length; j++){
//                mulMat[i][1-(i*1)] += matA[i][j] * matB[j][1-(i*1)];
//            }
//        }
//        // mulMAt 출력
//        for(int[] array : mulMat){
//            for(int val : array){
//                System.out.printf("%d ",val);
//            }
//            System.out.println("");
//        }

        //풀이 예시

        //3X2 2X3, 가운데 2를 하나의 축으로 활용해서 풀면된다는데...
        int [][] matA = {{1, 2, 3} ,{4, 5, 2}};
        int [][] matB = {{5, 2}, {6, 2}, {1, 0}};

        int [][] matC = new int[matA.length][matB[0].length];

        for (int i = 0; i < matC.length; i++) {
            for (int j = 0; j < matC[i].length; j++) {
                for (int k = 0; k < matA[0].length; k++) {
                    matC[i][j] += matA[i][k] * matB[k][j];
                }
            }
        }

        for (int [] row: matC) {
            for (int val: row) {
                System.out.printf("%d\t", val);
            }
            System.out.println("");
        }
        

    }
}
