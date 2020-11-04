package ch02.p05;

public class Main {

    /**
     * 조건문 - 조건에 따라서 코드의 실행 흐름을 결정
     * if 계열
     * switch 계열
     *
     * [branch 문] - 기계어 수준에서 부르는 조건 실행문 (branch라고 하면 조건문으로 알아들으면됨)
     * 초보때는 용어만 잘 알고 있어도 반은 간다. 용어 아는데 시간할애하라 - 용어알면 검색해서 활용가능
     */

    public static void main(String[] args) {

        String snum = "510225-2055132";
        char c = snum.charAt(7);
        System.out.println(c);


        // if (bollean) {true일때 실행문}
        if(c=='1' ) {
            System.out.println("남자 입니다.");
        }

        if(c=='1' || c == '3') {
            System.out.println("남자 입니다.");
        } else {
            System.out.println("여자 입니다.");
        }

        int x = 6; // 1 2 3 6
        if (x % 2 == 0) {
            System.out.println("짝수 입니다.");
        } else {
            System.out.println("홀수 입니다.");
        }

        System.out.println(x % 2 == 0 ? "짝수 입니다." : "홀수 입니다");

        // 둘 중에 어떤 코드가 나은 코드냐 하면 상황에 따라 다르다 라고 답할수 있음
        // 근데 나중에 코드컨벤션에 의해 코드를 줄일 때 유용할 수 있음

        int score = 2;
        char grade;

        if (score>= 9) {
            grade = 'A';
        } else if ( score >= 7) {
            grade = 'B';
        } else if ( score >= 5) {
            grade = 'C';
        } else if ( score >= 3) {
            grade = 'd';
        } else {
            grade = 'F';
        }
        System.out.println(grade);

        // 네스티드 if , 복합적인 if

        score = 90;
        boolean isLate = true;
        // late인 경우에는 하나 낮은 그레이드를 주기로 하였다.
        if(score >= 90){
            if (isLate){
                grade = 'B';
            } else {
                grade = 'A';
            }
        } else if (score >= 80) {
            if (isLate){
                grade = 'C';
            } else {
                grade = 'B';
            }
        } else if (score >= 70) {
            if (isLate){
                grade = 'D';
            } else {
                grade = 'C';
            }
        } else if (score >= 60) {
            if (isLate){
                grade = 'F';
            } else {
                grade = 'D';
            }
        } else{
             grade = 'F';
        }
        System.out.println(grade);


        // switch ~ case
        // 조건문에 '값'이들어오게 된다. (boolean에 한정되지 않습니다)
        // case가 범위가 될 수 없고, case도 값이어야 합니다.
        // (변수)가 들어가고 case 다음에 조건 값이 들어가서 변수의 값이 조건값과 같으면 아래의 실행문을 실행.

        switch(grade) {
            case 'A':
                System.out.println("휼륭합니다!"); // fall - through//라고 주석해주어야함

            case 'B':
                System.out.println("멋집니다!");
                break;
            case 'C':
            case 'D':
                System.out.println("많이 노력하세요!");
                break;
            default :
                System.out.println("다음엔 잘하세요.");
        }
        // break가 없으면 다음 케이스문으로 그대로 넘어가서 결국은 해당 case아래의 모든 실행문 실행됨
        // -> fall - through가 발생한다고 함 // 일부러 case를 합쳐서 fall - through하게 하기도 함

    }
    }
