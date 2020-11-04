package ch03.s04.p03;

/**
 * 메소드 재정의 (Method Overriding)
 * Override -> (기존에 있는것에)덮어 씌운다, 해킹에서 뭔가 달라지게 한다
 * 다형성 (polymorphism)의 근간이 됩니다.
 */

class Person {
    public void writeCode() {
        System.out.println("아무 코드나 일단 적어 보았다.");
    }
    
}

class Student extends Person{
    @Override //이렇게 적어 주는 것이 관례. 필수는 아님(안쓴다고 문법오류는 아님) => 그냥써야한다고 생각해
              // @표현것은 에노테이션인데 나중에 또 배움.
        public void writeCode(){
            System.out.println("능숙하게 코드를 작성해 보았다");
    }
        public void sleep(){
            System.out.println("학생은 잠을잔다");
        }

        public String toString(){

        return "Hello";
        }


}

class Developer extends Person{
    @Override
    public void writeCode() {
        System.out.println("코드 작성이 하기 싫어서 하지 않았다.");
    }
    //@Override 에노테이션만 쓰면 알아서 메소드가 작성된다. 우와
}

public class main {
    public static void main(String[] args) {

        Student stud = new Student();
        stud.writeCode();//능숙하게 코드를 작성해 보았다
        System.out.println(stud);
        Person person = new Person();
        person.writeCode();//아무 코드나 일단 적어 보았다.

        // 여러 하위 클래스에서 오버라이딩 할 수 있음//상송과 같이
        Developer dev = new Developer();
        dev.writeCode();//코드 작성이 하기 싫어서 하지 않았다.

        Person p = (Person)dev; //부모 클래스의 자료형으로 자식클래스 인스턴스를 부모클래스로 캐스팅하여 대입함
                                //Person이 아닌 Developer의 method()사용됨
        p.writeCode();// 코드 작성이 하기 싫어서 하지 않았다.
        p = (Person)stud;
        p.writeCode(); // 능숙하게 코드를 작성해 보았다
        //부모 클래스 자료형이지만 오버라이드된 메소드가 동작한다.
        // 이런 모습이 다형성(Polhymorphism)의 구현 중 하나이다.
        // 다양한 자식들을 사람으로 받아서 모두 코드를 작성하게 하면 알아서 자식들이 자신들의 방식으로 코드를 작성함
        // Person이란 배열에
        System.out.println("");
        Person[] people = new Person[]{new Developer(), new Student()};
        //Person이란 자료형으로 안에 자식클래스의 인스턴스를 가질 수 있음

        for(Person person1: people){
            person1.writeCode();
        }
        //코드 작성이 하기 싫어서 하지 않았다.
        //능숙하게 코드를 작성해 보았다
        // => 한번 자식클래스의 메서드들을 사용해버렸다... 이게 다형성의 구현

        stud.sleep();
        p=(Person)stud;
        //p.sleep(); // => 부모 클래스로 캐스팅 되면, 자식 클래스에만 있는 메소드는 실행이 안된다.

        // 왜이렇게 사용하는지 이해하게 되면객체 지향 객체지향 할 수있음
    }
}
