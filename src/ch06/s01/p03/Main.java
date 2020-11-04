package ch06.s01.p03;

import java.util.ArrayList;
import java.util.List;


/**
 * 옵저버 패턴 (observer pattern)
 * - Observable 객체의 변화를 Observer에서 알 수 있도록 하는 패턴
 *      - Observable 자체는 Deprecated이지만 필요하면 이런식으로 구현해서 사용할 수 있다
 * - GUI와 게임에 많이 쓰이는 패턴
 * - 즉, 보통 Hierarchy가 있는 경우 (계층이 있는 경우) 많이 사용된다
 * - 필요할 때 필요한 것을 랜더링 할 수 있도록 계층적으로 옵저버링을해서 어떤레벨에서 필요할 때 마다 랜더링을 하게된다
 */

class Subject { // 관찰 대상 클래스 // Observable이 Deprecated 이다
    private List<Observer> observers = new ArrayList<>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();

    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for (Observer observer: observers) {
            observer.update();
        }
    }

}

abstract class Observer {
    protected Subject subject;
    public abstract void update();
}

class BinaryObserver extends Observer {
    public BinaryObserver (Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        int state = this.subject.getState();
        System.out.println(Integer.toBinaryString(state));
    }
}

class OctalObserver extends Observer {
    public OctalObserver (Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        int state = this.subject.getState();
        System.out.println(Integer.toOctalString(state));
    }
}

class HexObserver extends Observer {
    public HexObserver (Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        int state = this.subject.getState();
        System.out.println(Integer.toHexString(state));
    }
}

public class Main {
    public static void main(String[] args) {
        Subject subject = new Subject();
        Observer observerOne = new HexObserver(subject); //상호 등록이 된다
        Observer observerTwo = new BinaryObserver(subject);
        Observer observerThree = new OctalObserver(subject);

        subject.setState(10);
        //a
        //1010
        //12
        subject.setState(20);
        //14
        //10100
        //24
        subject.setState(40);
        //28
        //101000
        //50

        //subject의 변화를 Observer가 감지하고 이런것들을 출력한다
        //틱택게임에서 상호래퍼런스를 할 때 옵저버패턴으로 볼 수 있다다
   }

}
