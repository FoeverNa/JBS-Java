package ch02.p06;

public class Main {
    /**
     * 반복문 (Loops)
     * for 문, while 문
     * 초기화 - 반복문을 실행하기 위해서 증감할 변수를 초기화
     * 조건식 - 반복문을 실행할 조건(또는 종료할 조건)
     * 실행문 - 조건식이 참(또는 거짓)일 경우 실행할 코드
     * 증감식 - 실행문이 실행된 후에 변수에 증감을 주는 것
     */
    public static void main(String[] args) {
        // for 문
        //for(초기화;조건식;증감식){실행문}
        //초기화는 처음 한번만 , 다음 반복에서는 바로 증감식으로 감.
        // i=0으로 시작하는 이유는 유구한 전통, 배열과 연관

        for (int i = 0; i < 5; i++) {
            System.out.println(i);
        }
        System.out.println("");

        // 증감식에도 대입연산자가 들어갈 수 있음
        for (int i = 0; i < 5; i += 2) {
            System.out.println(i);
        }
        System.out.println("");

        // 4~0 을 출력
        for (int i = 4; i >= 0; i--) {
            System.out.println(i);
        }
        System.out.println("");

        // nested for (중첩된 for문)
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.printf("(%d, %d)\n", i, j);
            }
        } // 5*5 = 25

        //중괄호를 안쓰면 for문 코드 한줄만 실행됨, 그것을 확장하기 위해 {}을 사용해서 {}안에 코드를 다 실행하게 됨
        for (int i = 0; i < 5; i++)
            System.out.print(i);    // iterated -용어기억
        System.out.println(""); //not iterated(반복되다)

        //while 문
        //초기호ㅘ
        //while (조건문){
        // 실행문
        // 증감식
        // }
//
//        int i = 0;
//        while (i<5) {
//            System.out.println(i);
//            i++;
//        }

        //while문에서는 변수 i를 외부에서 사용되었기 때문에 코드전체에서 i를 사용못함,
        //for문은 for문안에서 초기화했기 때문에 변수i를 계속 사용할 수 있다.

//        i = 0;
//
//        do{
//            System.out.println(i);
//            i++;
//        } while(false); // 조건문이 false여도 반드시 한번은 실행된다.
//    }

            //제어문 - break continure
            for (int i = 0; i < 10; i++) {
                if (i == 3) {
                    continue;
                }
                System.out.println(i);
            }

            for (int i = 0; i < 10; i++) {
                if (i == 3) {
                    break;
                }
                System.out.println(i); // if문과 함께 사용됨.
            }

            outer:
            for(int i = 0; i<5 ; i ++){
                for (int j = 0; j < 5; j ++){
                    if(i ==3 ){
                        System.out.println("continue called");
                        continue outer; // label outer;을 적어주면 가까운 for문이 아니라 ㅣabel이 적혀있는 for문으로 빠져나감
                                        // break도 마찬가지로 label을 사용 가능하다.
                                        // continue called가 4번 실행되냐 1번실행 되냐 차이
                                        // label 은 outer왜에도 다른 단어도 사용가능exe loop1:
                                        // 글자색은 키워드는 주황색(바뀔수 없음), 회색은 바뀔 수 있음
                    }
                    System.out.printf("%d * %d = %d\n", i, j, i*j);
                }
            }

    }
}
