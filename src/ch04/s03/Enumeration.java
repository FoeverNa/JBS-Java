package ch04.s03;

/**
 * 열거형 (Enumeration)
 * enum 키워드로 표현
 * 내부적인 구현은 enum ==> java.lang.Enum 클래스를 상속(하는 애)
 * enum은 다른 클래스를 상속하지 못함(윗줄을 상속하기 때문에)
 *
 * 열거형은 다른 클래스를 상속하지 못하지만, 인터페이스 구현은 가능(상속과 별개로 가능)
 * 열거형 타입에는 열거형 상수와 null 값 할당 가능
 */

// class 같이 enum으로 선언
enum Job { // 각 상수는 0부터 숫자를 가지지만, 심볼로만 사용하고 숫자는 사용하지 않음
    STUDENT, MARKETING, DEVELOPER, CHIEF_EXECUTIONAL_OFFICER; // 열거형 상수
}

class Foo{ // 클래스 내부에서 열거형 구현 가능하다
    enum Symbol {
        ONE, TWO, THREE;
    }
}

// 열거형 내부에서 메소드 구현
enum Symbol {
    ONE, TWO, THREE, FOUR;

    public Symbol nextSymbol() {
        if (this.equals(ONE)) { // 열거형 내부에서는 ONE바로 사용가능
            // 여기서 this.는 상수들, 상수들이 객체가됨
            // 상수가 ONE이면 뭐하라는 뜻
            return TWO;
        } else if (this.equals(TWO)) {
            return THREE;
        }
        return FOUR;

    }
}

interface IFamily{
    String FATHER = "아빠";
}

// 열거형 생성자를 이용한 enum 상수 초기화
enum Family implements IFamily {
    FATHER("아버지"), MOTHER("어머니"), SON("아들"), DAUGHTER("딸");
                                                            // 열거형 상수(객체)// new가 써있지않을 뿐이지 new로만들어지는애들
    private String koreanWord; // 멤버 변수(객체 속하는 변수)

    public String getKoreanWord() {
        return koreanWord;
    }

    public void setKoreanWord(String koreanWord) {
        this.koreanWord = koreanWord;

        //KoreanWord변수가 private이기에 getter setter 생성
    }

    // private은 생략 가능, pbulic 불가능, 안써도 private, 안에서만 객체 생성되기 때문..
    private Family(String koreanWord){ // 각가의 객체를 생성하는 생성자
        this.koreanWord = koreanWord; // 이것까지하면 위에 상수에 ','에 빨간불 들어옴 왜? arguments 입력하라고...

    }
}


public class Enumeration {
    public static void main(String[] args) {
        Job job = Job.STUDENT; // enum 명은 클래스 일종이기에 자료형으로 사용 가능
        // 클래스 변수와 유사하게 사용

        if (job == Job.CHIEF_EXECUTIONAL_OFFICER) {
            System.out.println("사장님 안녕하세요?");
        }

        char c = 'A';
        switch (c) {
            case 'A':
                break;
            case 'B':
                break;
            default:
        }

    switch(job) { // switch ~ case 문에는 열거형 자료형을 생략
        case STUDENT:
            System.out.println("you will be a great one");
            break;
        case MARKETING:
            System.out.println("you sells");
            break;
        case DEVELOPER:
            System.out.println("you make things");
            break;
        case CHIEF_EXECUTIONAL_OFFICER:
            System.out.println("you choose");
            break;
        default:
            System.out.println("Who are You?");
    }


        System.out.println(Foo.Symbol.ONE);// 심볼그자체로 사용된다..

        // 열거형 메소드

        Symbol sym = Symbol.ONE;
        Symbol nextSym = sym.nextSymbol(); // sym 은 객체임 그래서 메소드 사용가능한것
        System.out.println(nextSym);
        nextSym = nextSym.nextSymbol();
        System.out.println(nextSym);


        //열거형 생성자와 멤버 변수 활용
        Family fam = Family.SON;
        System.out.println(fam.getKoreanWord()); //아들
        fam.setKoreanWord("버린 자식");
        System.out.println(fam.getKoreanWord()); //버린자식
        System.out.println(Family.SON.getKoreanWord()); // 버린자식 // 해당객체가 여러개 생성되는게 아닌 하나의 객체만생성되는것

        System.out.println(Family.FATHER.FATHER); // FATHER0
        System.out.println(Family.FATHER.FATHER.MOTHER); // MOTHER
        System.out.println(Family.FATHER.DAUGHTER.getKoreanWord());// 딸

    }
}

