package ch05.s14.p06;

class WorkObject {
    public synchronized void methodA() {
        System.out.println("methodA() called");
        notify(); // wait()중인 다른 스레드를 하나 동작 상태로 만든다.(첫실행에는 효과가 없다)
//        notifyAll(); // 잉? 설명못들엇네
        try {
            wait(); // Lock을 반환하고 대기 상태로 들어감 // 서로 notify해주고 waity해주는것이 교차하게 된다.
                    // notify와 wait에 순서가 바뀌면 데드락 상태가 될 수 있다.(계속 서로 기다리는 상태)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public synchronized void methodB() {
        System.out.println("methodB() called");
        notifyAll();
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // A wait() -> B notify() wait() -> A notify() A wait()

}

class MyThread extends Thread {
    private WorkObject workObject;
    private boolean isA;

    public MyThread(WorkObject workObject, Boolean isA) { // Dependency Injection //스프링에서 많이 나온데
        this.workObject = workObject;
        this.isA = isA;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (isA) {
                workObject.methodA();
            } else {
                workObject.methodB();
            }
        }
    }
}

public class Main {

    public static void main(String[] args) {
        WorkObject sharedObj = new WorkObject();

        Thread p1 = new MyThread(sharedObj, true);
        Thread p2 = new MyThread(sharedObj, false);

        p1.start();
        p2.start();

        //그냥 했을 때는 A 10번 B 10번됬지만 우연히 그런 것이다
        //A랑 B를 엄밀하게 순차적으로 실행시키고 싶다.
        //=> 가능함

    }
}
