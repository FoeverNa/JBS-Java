package ch05.s14.p04;

/**
 * 데몬 스레드 (Daemon thread)
 * - 다른 스레드가 모두 종료될 경우 스스로 종료되는 스레드 <- 정의
 * - 무한 루프로 대기하면서 동작하는 구현이 많다 <- 활용
 *      - 일정 시간마다 동작, interrupt등에 의해서 동작 한다 (외부에서 요청)
 */
class AutoSaver extends Thread{
    public AutoSaver() {
        this.setDaemon(true); // Daemon 스레드로 설정된다
                              // 메인 스레드가 종료되면 스스로 죵료가 된다? ->문장 확인
                                // 많이 사용한다. 주기적으로 돌거나, 뒤에서돌면서 메인스레드 도와줘야되는 상황에서
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Save Something..
            System.out.println("Auto Save Done!"); // 5초마다 자동 세이브
        }
    }

}

public class Main {
    public static void main(String[] args) throws InterruptedException {

        new AutoSaver().start();

        for (int i = 0; i < 15; i++) {
            Thread.sleep(1000);
            System.out.println("Working");

        }
        //Working
        //Working
        //Working
        //Working
        //Auto Save Done! //5초마다 자동저장
        //Working
        //Working
        //Working
        //Working
        //Working
        //Auto Save Done!
        //Working
        //Working
        //Working
        //Working
        //Working
        //Auto Save Done!
        //Working // 다른 Thread 종 료이후 Daemon Thread도 종료
    }
}
