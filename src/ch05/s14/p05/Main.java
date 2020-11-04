package ch05.s14.p05;

/**
 * Inte
 * - 자바의 모든 객체 (Object)는 고유 락을 가지고 있음
 *      - 객체의 소유권을 한정하는 내부적 구현 -> 소유권은 독점적이다.
 * - synchronized를 이용하면 객체의 고유 락의 소우권을 가져올 수 있다.
 *      - 소유권이 이미 점유된 경우에는 Blocking으로 동작(기다렸다가 반환되면 내가 소유권이어 받아서 동작한다)
 *
 */

// 1. 멀티스레드 동작에 취약한 구현
class Counter {
    private int count = 0;
    public int increaseCount() {
        return ++count; // 읽고, 수정하고, 쓰는 작업
                        // 경쟁적으로 동작하다 보면, 읽고 수정하고 쓰기 전에 다른 쓰레드가 읽는 경우가 발생
                        // 원하는대로 프로그램 동작하지 않는데 괜찮다고 생각하면 큰일난다..
    }

    public int getCount() {
        return count;
    }
}

// 2. Object 객체에 Intrinsic Lock을 이용한 구현 // 거의 사용하지 않는 방법
class Counter2 {
    private Object lock = new Object();
    private int count = 0;
    public int increaseCount() {
        synchronized (lock) { // 설명 못들음
            return ++count;
        }
    }

    public int getCount() {
        return count;
    }
}

// 3. this 객체의 Intrinsic Lock을 이용한 구현
class Counter3 {

    private int count = 0;
    public int increaseCount() {
        synchronized (this) { //lock 생성없이 자기자신 객체를 lock 으로 걸어도 된다.
            return ++count;
        }
    }

    public int getCount() {
        return count;
    }
}

// 3. 메소드에 synchronized 키워드 사용
// synchronized 키워드가 사용된 메소드를 호출하기 위해서는
// 해당 객체를 소유해야만 호출이 가능. 소유하지 못하면 Blocking
// 가장 좁은 영역에서 intrinsic Lock을 사용했기 때문에 가장 좋은 구현이 된다. => 이게 멀티스레드가 어려워지는 이유
class Counter4 {

    private int count = 0;
    public synchronized int increaseCount() {
            return ++count;
        }

    public int getCount() {
        return count;
    }
}

// 4. 동작하는 곳에서 sychronize 해주기
class Counter5 {

    private int count = 0;
    public int increaseCount() {
        return ++count;
    }

    public int getCount() {
        return count;
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Counter5 c = new Counter5();

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
//                synchronized (c){ // 이렇게 싱크를 하면 어떻게 될까? // 잘 동작해 보인다
                    // 이렇게 싱크를 하면, 병렬 동작이 전혀 이루어지지 않는다. 하나의 스레드가 100번동작하고 다른스레드가 100번 동작한 것
                    // 이러면 가장 안전하지만 가장 효율이 떨어지는 코드가 된다.
                for (int j = 0; j < 100; j++) {
                    // c라는 shared object (공유 객체)가 있을 때
                    // 멀티스레드로 부터 안전한 영역을 생성하는 방법
                   synchronized (c){
                       c.increaseCount();
                   }

                }
            }).start();
        }

        Thread.sleep(1000);
        System.out.println(c.getCount()); // 실행할때마다 값이 달라짐


    }
}

//Lock을 가졌다가 반환하는 것들이 잘이해가 안가네..음
// 멀티스레드로부터 안전한 구현을 만드는 것이라는데
// 100개의 스레드가 경쟁을 하는데 한번에 하나씩만 동작을 하게만드는것. 그 기준이 Lock을 가진 스레드가 된다