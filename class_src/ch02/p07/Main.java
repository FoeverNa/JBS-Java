package ch02.p07;


/**
 * 배열 (Array)
 * 배열의 튻성
 * - 하나의 변수로 여러 개의 값을 다룰 수 있다.
 * - 동일 자료 형만 다룰 수 있다.
 * - 한번 생성한 배열의 크기는 변하지 않는다.
 * - 배열에 속한 값은 메모리에 연속으로 위치한다.(중요특성!)
 */
public class Main {

    public static void main(String[] args) {
        int intval;

        // 배열의 선언 // 선언만하면 메모리는 잡히지 않음
        int[] integers;
//        int cStyleInteger[]; // 배열선언가능하나 가독성때문에 안쓰임//쓰지마

        long[] longs;
        char[] chars;

        String[] strings;

        // 배열의 생성과 초기화
        integers = new int[10]; // 선언된 배열을 초기화
        int[] integers2 = new int[10]; // 선언과 동시에도 가능
        integers2[0] = 5;
        integers2[1] = 10;
        integers2[3] = 9;
        // 값을 넣을 때 순차적으로 하지 않아도 된다.

        System.out.println(integers2[0]);
        System.out.println(integers2[1]);
        System.out.println(integers2[2]); // 0으로 자동으로 초기화// 입력없으면=0
        System.out.println(integers2[3]);
        System.out.println("");

        int[] integers3 = new int[]{5, 2, 3, 6, 12, 4}; // 바로 입력할 때는 길이 입력 안해도 된다.
        System.out.println(integers3[0]);
        System.out.println(integers3[1]);
        System.out.println(integers3[2]);
        System.out.println(integers3[3]);
        System.out.println(integers3[4]);
        System.out.println(integers3[5]);
//      System.out.println(integers3[6]);// ArrayIndexOutOfBoundsException 오류 발생
        //배열 사용할 때에는 선언해 준 길이까지만 접근해야 한다.

        int[] integers5 = {1, 4, 5, 23, 0}; // new int[] 안 붙여도 된다.

        // 배열을 반복문으로 접근

        float[] floats = new float[5];
        for (int i = 0; i < floats.length; i++) { // for문을 이용한 초기화, new할때도 초기화지만 처음값넣을때도 초기화//혼용 사용
            floats[i] = (float) (i * 0.25);
        }

        for (int i = 0; i < floats.length; i++) {
            System.out.println(floats[i]);
        }

        // Enhanced for, for each문이라고도 함
        // 배열에 접근하는 다른 방법
        // for(자료형 변수명 : 배열명){ 배열0부터 끝까지 값을 floatVal에  대입해줌)

        for(float floatVal: floats){
            System.out.println(floatVal);
        }
        // 밑에 for문과 같은 역할을 함

        for(int i = 0 ; i < floats.length; i++){
            float floatVal = floats[i];
            System.out.println(floatVal);
        }

        //배열의 크기를 못바꾸는 이유는 배열이 연속적으로메모리 사용하기에 뒤에 연속적으로 붙여서 수정을 못함
        // 그래서 배열의 크기를 변경하기 위해서는 새로 배열을 생성해줘야 함

        int [] src = {1, 2, 3, 4, 5};
        int [] dst = new int[10];
        for(int i = 0; i< src.length; i++){
            dst[i] = src[i];
        }
        for(int integer : dst){
            System.out.println(integer);
        }

        // 배열 크기변경은 빈번하기에 크기변경 함수 존재

        int[] src2 = {1, 2, 3, 4, 5};
        int[] dst2 = new int[10];
        System.arraycopy(src2,0,dst2,0,src2.length);//arraycopy(어디에서,몇번째부터, 어디로, 몇번째부터,어디까지)
        for(int integer: dst2){
            System.out.println(integer);
        }

    }
}
