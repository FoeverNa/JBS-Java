package homework.hw06;

/**
 * 싱글톤 패턴 구현하기
 *
 * 단 하나의 인스턴스만 존재할 수 있는 클래스 SingletonPattern을 구현하시오.
 *
 * 생성자를 외부에서 직접 호출할 수 없다.
 * 정적 메소드인 getInstance() 메소드를 이용해 객체를 받아온다.
 * 받아온 객체는 항상 같은 객체를 참조해야 한다.
 */

public class SingletonPattern {
    private final static SingletonPattern instance = new SingletonPattern();// final 까지 쓰면 제일좋음 // 안써도 무관하지만 더좋은방법
                                                                            // private => instance 변수에 접근불가
    private SingletonPattern(){}

    public static SingletonPattern getInstance(){
        return instance;
    }


}

class SingletonPatternTest {
    public static void main(String[] args) {
        // SingletonPattern instance = new SingletonPattern(); // should fail
        SingletonPattern instanceOne = SingletonPattern.getInstance();
        SingletonPattern instanceTwo = SingletonPattern.getInstance();

        System.out.println(instanceOne == instanceTwo);
    }

    //가장 많이 사용되는 창에서 사용됨
    // 게임개발에서 게임의 스테이터스를 가지고있는 다저장해놓는 인스턴스를만들때(다른데서만들면안될때)
    // 굉장히 실용적인 패턴 - 예좀 더찾아보자
}
/**
 * 싱글톤 패턴 인스턴스 생성할때 final 붙여주면 수정불가라는 것 더 명확히 밝혀 줄 수 있음
 */
