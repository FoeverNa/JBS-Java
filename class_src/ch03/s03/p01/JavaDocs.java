package ch03.s03.p01;

/**
 * 클래스에 대한 설명. 이 클래스는 자바독 클래스로 자바독을 공부하기 위해...
 * @author Young Na
 */
public class JavaDocs {
    /**
     * 멤버 변수에 대한 설명을 적을 수 있습니다.
     */
    int memberInt;

    /**
     * 멤버 변수 또 다른 것도 설명을 적을 수 있습니다.
     */
    String memberString;

    /**
     * 생성자에 대한 설명입니다.
     * @param memberInt 입력1입니다.
     * @param memberString 입력2입니다.
     */
    public JavaDocs(int memberInt, String memberString) {
        this.memberInt = memberInt;
        this.memberString = memberString;
    }

    /**
     * 메소드에 대한 설명을 여기에 적습니다.
     * @param string 입력 인자 (파라미터)에 대한 설명을 적습니다.
     * @return 리턴값에 대한 설명은 여기에 적습니다.
     */
    public int methodA(String string){
        return string.length();
    }

    public static void main(String[] args) {

        JavaDocs j = new JavaDocs(1,"string");
        //클래스명에 커서 올리면 javadoc적은게 뜸
        // too - generate javadoc 하면 html형태로 만들 수 있음
        // 보통은 interface만 javadoc 함

//        int a = j.methodA();

        //실제로 코딩을 작업하는 시간보다 문서작업 시간이 더 늘 수 있다.
        //어떻게 코딩하면 좋을 지에 대한 디자인.
        //고객과의 미팅.. 을 위한 문서화시간이 더 오래걸림
    }
}
