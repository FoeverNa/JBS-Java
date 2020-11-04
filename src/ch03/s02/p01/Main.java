package ch03.s02.p01;

/**
 * 클래스 - 객체를 생성하기 위한 설계도 (Class)
 * 객체 - 클래스를 구체화하여 값으로 생성된 것(Object, instance)
 * 클래스를 객체로 만드는 과정 - Instaciation
 *
 */

class Car{ // 클래스 이름은 보통 PascalCase로 적는다.
    int speed = 0; // 속성 = 멤버 변수
    // 속성 : attribute, filed
    // 멤버 변수 : member variable
    // 같은 개념이지만 컨텍스트에 따라 다르게 부르기도 한다. 그래서 용어를 익숙하게 알아야한다.상사랑대화가 통화려면

    void move() { // 메소드 (method), (가끔 멤버 함수라고 부르는 사람도잇음), (가끔 함수, 메서드가 더정확한 표현)
         speed = 10; // 행위를 구현, 주로 속성을 변경하는 역할
    }
}


public class Main {
    public static void main(String[] args) {
        Car carOne = new Car(); // new 키우드로 클래스에서 객체 생성(Instanciation)
                                // 자료형은 클래스명 변수명은 camelCase = new 클래스명();
        System.out.println(carOne.speed);//0//.으로 속성 접근 가능
        carOne.move();// move()메소드 사용
        System.out.println(carOne.speed);//10 // move()메서드가 속성을 변경함

        Car carTwo = new Car();
        System.out.println(carTwo.speed); // 0 // carOne과는 독립적인 객체로 존재함

        Car carThree = carOne; // 참조형 객체 (가르키는 객체)
        System.out.println(carThree.speed); //10//carThree는 new하지 않았으니 새로운 객체가 생긴 것이 아님
        carThree.speed = 5;
        System.out.println(carThree.speed);//5 
        System.out.println(carOne.speed);//5 carThree가 가르키는 carOne의 값이 바뀐 것이 었음!
        //carThee와 carOne은 다른 변수이지만 같은 값을 가르키고 있다.
        // String은 immutable이기에 참조형 객체이어도 이런 문제가 없다.(수정이 불가하기 때문에)
        // 그러나 보통 class는 mutabl이기 떄문에 신경써서 사용해야 함

    }
}
