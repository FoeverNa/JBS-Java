package ch03.s04.p01;


/**
 * 상속 (Inheritance)
 * 상속: 어떤 클래스의 모든 멤버 변수 및 메소드를 계승하여,
 * 새로운 클래스를 생성하는 것.
 *
 * 상속 대상 : 조상 클래스, 부모 클래스, 상위 클래스, 슈퍼 클래스(슈퍼 =상위)
 * 상속 결과 : 자손 클래스, 자식 클래스, 하위 클래스, 서브 클래스
 *
 * 상속 관계를 흔히 'IS-A' 관계 라고 부른다.
 */

class Person{
    String name;

    public void work(){
        System.out.println("일하기");  
    }

    public void sleep(){
        System.out.println("잠자기");
    }
 public Person() {}
 public Person(String name) {
        this.name = name;
 }
}

// Person을 상속하는 자식 클래스
               //extends 키워드를 이용하여 상속
class Developer extends Person{

    String mainLang;//코딩하는 Lang

    public void writeCode(){
        System.out.println("돈 받은 만큼 코딩하기");

    }

}

class Student extends Person{
    String major;

    public void writeCode(){
        System.out.println("밤새 코딩을 합니다.");
    }
}

public class Main {
    public static void main(String[] args) {
        // 클래스를 상속하면, 모든 멤버 변수와 모든 메소드를 상속받는다.(not 복사)
        Developer dev = new Developer();
        dev.name = "나개발";
        System.out.println(dev.name); //나개발 //Developer이지만 Person이기도 하다.
                                        // Developer 'Is-A' Person.// 개발자는 사람이다.
                                        // Developer is a Person.
        dev.work(); // 일하기
        dev.sleep(); // 잠자기

        dev.mainLang = "Java";
        dev.writeCode();

        Student stud = new Student(); // Student 'IS-A' Person
        stud.writeCode();

        //부모 클래스로부터 여러 자식 클래스들을 만들 수 있다.
        // 자식 클래스는 자기자신의 변수와 메서드 뿐아니라 부모의 것까지 사용할 수 있음
    }
}
