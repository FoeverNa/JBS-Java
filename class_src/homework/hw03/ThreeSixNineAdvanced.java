package homework.hw03;

public class ThreeSixNineAdvanced {
    /**
     * 369게임+
     * <p>
     * 369게임에 참여했을 때, 내가 외쳐야 하는 숫자나 동작을 순서대로 출력하시오.
     * <p>
     * 규칙
     * 1. 3, 6, 9, 5가 포함 되지 않은 숫자는 숫자를 외친다. (2!)
     * 2. 숫자에 포함된 3, 6, 9의 개수 만큼 박수를 친다. (짝!)
     * 3. 숫자에 포함된 5의 개수 만큼 발을 구른다. (쿵!)
     * 4. 박수와 발구르기의 순서는 관계 없으나, 반드시 번갈아 수행한다.
     * <p>
     * 예시
     * 2   -> 2!
     * 33  -> 짝!짝!
     * 553 -> 쿵!짝!쿵! (짝!쿵!쿵!x)
     * 47  -> 47!
     * <p>
     * 인자
     * gameLength: 게임이 종료되는 데 걸리는 턴 수
     * numPeople: 게임에 참여하는 인원 수
     * myTurn: 내가 숫자를 외치는 순번
     */

    public static void main(String[] args) {

        int gameLength = 1000;
        int numPeople = 12;
        int myTurn = 3;

        for (int i = 3; i <= 1000; i++) {
            int num1 = i % 10;
            int num2 = (i % 100) / 10;
            int num3 = i / 100;
            int numClap = 0;
            int numKung = 0;


            if((i-3)% 12 !=0){
                continue;
            }


            if (num1 == 3 || num1 == 6 || num1 == 9) {
                numClap++;
            }
            if (num2 == 3 || num2 == 6 || num2 == 9) {
                numClap++;
            }
            if (num3 == 3 || num3 == 6 || num3 == 9) {
                numClap++;
            }
            if (num1 == 5) {
                numKung++;
            }
            if (num2 == 5) {
                numKung++;
            }
            if (num3 == 5) {
                numKung++;
            }


            if (numClap == 0 && numKung == 0) {
                System.out.println(i);
            } else if(numKung ==3 ) {
                System.out.println("쿵!쿵!쿵!");
            } else if(numClap ==3 ) {
                System.out.println("짝!짝!짝!");
            } else if(numKung ==2 && numClap ==1 ) {
                System.out.println("쿵!짝!쿵!");
            } else if(numKung ==2 && numClap ==0 ) {
                 System.out.println("쿵!쿵!");
            } else if(numKung ==1 && numClap ==2 ) {
                System.out.println("짝!쿵!짝!");
            } else if(numKung ==0 && numClap ==2 ) {
                System.out.println("짝!짝!");
            } else if(numKung ==1 && numClap ==1 ) {
                System.out.println("쿵!짝!");
            } else if(numKung ==1 && numClap ==0 ) {
                System.out.println("쿵!");
            } else if(numKung ==0 && numClap ==1 ) {
                System.out.println("짝!");
            }

        }
        
    }
}











