package ch05.s14.p02;


public class Main {
    public static void main(String[] args) {
       Thread p1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.print("~");
//                Thread.yield(); // 다른 스레드로 양보하고 바로 실행 대기(Sleep(0)과 같은 속도, 바로 실행대기)
                                // 대기상태에서 바로 다음에 다시 동작할 수 있다-> 그래서 다시동작하는 구간이 있다
                                // 자주 변한다는 건 오버헤드가 늘어난다는 단점이 있다
                try {
                    Thread.sleep(1); // Running 상태에서 Timed_Waiting 상태 (실행에서 빠지고 일정시간 기다렸다가 실행대기)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread p2 =new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.print("*");
//                Thread.yield();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Priority 기능은 있으나, 보장되지 않는다.
        // 이유는 Straving하는 Thread가 없게 하기 위해서 OS가 조절하기 때문 (하나만 동작하지 못하도록 os가 알아서 우선순위조정)
        System.out.println(p1.getPriority()); // 우선순위 - 값이 높을 수록 우선순위가 높다. //default = 5

        p1.setPriority(Thread.MAX_PRIORITY);
        p2.setPriority(Thread.MIN_PRIORITY);

        p1.start();
        p2.start();
        //~ 와 *이 막 교차하진 않고 어느정도 이어지다가 교체되는모습
        //스레드가 서로 점유하면서 출력되는 모습

        //어떻게하면 번갈아가며 시킬까?
        //Thread.yield(); -> 오버헤드가 발생해서 수행속도가 느려진다
        //Thread.sleep(); -> 오버헤드가 yield보다 크다
        //Priotity 정하기
        // 어느정도 컨트롤은 가능하다.
    }
}
