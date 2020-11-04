package ch05.s14.p08;


import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 식사하는 철학자들 문제
 * 위키백과 참조
 *
 */

//직접 구현해보기
//성공조건은

class Philosopher extends Thread {
    private  final int id;
    private final Fork left;
    private final Fork right;

    public Philosopher(int id, Fork left, Fork right) {
        this.left = left;
        this.right = right;
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            try {
                left.acquire(); //있으면 가져오고 아니면 기다린다
//                System.out.println(id + ": left fork taken.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                if (!right.tryAcquire(1000, TimeUnit.MILLISECONDS)) {
                    left.release();
                    Thread.yield();
                    continue;
                }
                ; // 둘다가져오면 식사를 할 수 있다
//                System.out.println(id + ": right fork taken.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println(id + ": is eating.");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            left.release();
            right.release();
            Thread.yield();
        }
    }
}

class Fork extends Semaphore {

    public Fork() {
        super(1); // 1로 고정할거라서 그냥 입력
    }
}

public class Main {

    public static void main(String[] args) {
        Philosopher[] philosophers = new Philosopher[5];
        Fork[] forks = new Fork[5];

        for (int i = 0; i < 5; i++) {
            forks[i] = new Fork();
        }

        for (int i = 0; i < 5-1; i++) {
            philosophers[i] = new Philosopher(i, forks[i], forks[(i + 1) % 5]);

        }

        philosophers[4] = new Philosopher(4, forks[0], forks[4]); // 마직막 사람은 반대로 포크를 쥐게 하기

        for (int i = 0; i < 5; i++) {
            philosophers[i].start();;
        }

        // 첫실행에서 모두 왼쪽 포크를 드는 문제가 있다. => 데드락 상태
        // 이걸 어떻게 해결할까?
        // 1) accquie를 tryaccurie로 바꾸어줘서 안오면 left 포크 내려놓게하기
        // 2) 포크내려논사람에 Thread.yeild를 추가한다 //내가 내려놓고 조금 있다가 포크를 줍도록 양보를 한다.(바로 안줍는다)
        // 3) 마지막 사람은 반대로 포크 집게하기
        // 4) 먹고난 사람에 Thread.yeild 추가한다
    }

}