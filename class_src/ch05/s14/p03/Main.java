package ch05.s14.p03;

public class Main {
    public static void main(String[] args) {
        Thread p1 = new Thread(()-> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) { // interrupt를 만나면 sleep하지 않고 catch 문을 실행한다.
                System.out.println("Interrupted");
            }
            System.out.println("P1!");
        });

        Thread p2 = new Thread(()-> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("P2!");
            p1.interrupt(); // 기존 동작을 방해하고 interrupt에 반응을 강제하는 메소드 // 잘못 sleep하는애들 깨워줄 수 있다
                            // 주로 임베디드에서 많이 사용 (의도적으로는 많이 사용하지 않는다)
        });

        p1.start();
        p2.start();

        //P2!
        //Interrupted
        //P1!


    }
}
