package ch05.s14.p07;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 세마포 (Semaphore)
 * 사전적 의미는 횟대 (깃발)
 * n개의 깃발을 놓고, 여러 스레드가 경쟁하도록 하는 sync 기법
 * n = 1이면, BinarySemaphore라고 하며, Lock과 유사하게 동작(미세하게 다름)
 *
 */

public class Main {
    public static void main(String[] args) {
        Semaphore sem = new Semaphore(1);

        sem.release(); //세마포를 가지고 있지 않아도 반환할 수 있다
                        // 실제로 세마포 숫자가 늘어난다..??머집

        System.out.println(sem.availablePermits()); //1
//        try { // Blocking으로 동작
//            sem.acquire(); //세마포 획득하는 과정 // 존재하는 세마포보다 더크게가져가면 Blocking이 걸린다
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
        sem.acquireUninterruptibly(); // try ~catch 없이 실행// interrupt에 반응하지 않음 // 뚝심있음

        System.out.println(sem.tryAcquire()); // Blocking 하지 않고, 실패하면(세마포없어도) false 리턴하고 다음으로 넘어감 /
        try {
            System.out.println(sem.tryAcquire(2000,TimeUnit.MILLISECONDS)); // Blocking을 하도록 시간 지정할 수 도 있따
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(sem.availablePermits()); //0
        sem.release(); // 세마포 반환
        System.out.println(sem.availablePermits());//1
    }
}
