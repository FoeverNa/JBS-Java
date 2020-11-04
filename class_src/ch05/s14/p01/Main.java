package ch05.s14.p01;


/**
 * Multi-Thread Programming
 * 여러개의 스레드를 사용하는 프로그래밍 기법
 * <p>
 * Thread: 최소의 프로세스 동작 단위 -> 하나의 프로세스는 여러개의 스레드를 가질 수 있다.(메모리를 공유한다)
 * 반대로 말하면 하나의 스레드는 하나의 프로세스에 속한다
 * <p>
 * Process: OS로 부터 메모리를 할당 받아 동작하는 프로그램의 단위
 * 윈도우에 ctrl + alt + delete로 종료해본 그것
 * 프로그램의 실제로 메모리에 상주하면서 동작할 때 프로세스가 된다.(동작의 단위)
 * 클래스가 프로그램이라면 객체는 프로세스가 된다
 * 여러개의 프로그램을 돌리면 여러 프로세스가 돌아가게 된다
 * - 프로그램을 실행할 때, 멀티 프로세스로 동작하는 프로그램도 있다 / 여러개의 프로세스가 동기화되서 동작한다
 * - 최근에는 멀티프로세스로는 많이 구현하지 않는다
 * <p>
 * 멀티스레드의
 * 장점
 * - 여러 동작을 병렬적으로 처리하여 CPU 사용률 향상 (CPU Utilization)
 * - 인코딩, 렌더링, (CPU가 다른것 작업없이 하나에만 집중해야되는 작업들)
 * , 배치작업(DB 정리, 로그 처리 같이 모아 놓은것을 한번에 처리하면되는 것) -> 서비스커질수록 오래걸리기 때문에 최적화 중요
 * -> 촤적화 작업이기 때문에 그냥 구현보다 어렵다
 * - 라이젠이 가성비있게 멀티스레드 돌리기 좋아서 많이쓰인다
 * - 시간이 걸리는 동작을 분리하여 프로그램의 응답성 향상
 * - 주로 GUI 개발에 많이쓰임, 게임, 앱, 웹 API
 * - 요런것들은 멀티스레딩이라고 생각할필요는 업는게 프레임웤이 스레드를 관리해주기 때문이다
 * <p>
 * 단점
 * - 디버깅이 어렵다. 쓰레드가 동시에 동작하기 때문에, 디버거로 확인하기 어려움
 * 디버거를 쓰거나, 디버깅을 하기 위한 코드를 추가하면 동작이 변한다.(코드가 뭐가 먼저실행될지 모른다)
 * - 구현이 어렵다. 쓰레드간의 동기화를 하기 위한 구현이 어렵다, 쉽게 동기화하면 느려진다.(멀티스레드 사용의미 없다)
 * - Context Switching 오버헤드가 있기 때문에 동기화를 잘 못하면 오히려 더 느려진다.
 * 스레드가 몇개가 돌아갈지는 내가 결정하는 것이 아닌 하드웨어 에 따라 OS 에서 결정한다.
 * 그때 잠깐씩 돌아가면 멀티스레드 돌아가는 것 같은 효과나는데 스레드 교체과정에서 오버헤드(Context switching)가 발생하면서 시간소요
 * <p>
 * 결론: 오늘 배울게 어렵다
 */

// 3. Thread를 상속해서 오버라이딩
class MyThread extends Thread {
    @Override
    public void run() {

        for (int i = 0; i <10; i++) {
            System.out.println(i);
        }

    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // 1. 익명 내부 구현
        Thread p1 = new Thread(new Runnable() {// 이름은 thread로 하기도 하고 p라고도 하기도한다// run()메서드를 동작시키는 클래스?

            @Override
            public void run() {
//                try {
//                    Thread.sleep(50); // 얘는 try~catch로 걸어야 된다 이유는 나중에 알려주신다네
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                System.out.println("Hello THread");
            }
        }); //구현하고 그냥 실행하면 아무것도 동작하지 않는다

        // THread 객체는 1회용이며, start()로 실행한다.
//        p.run(); // run으로 실행하면 main 스레드에서 run()메서드를 동작시킨 것, 멀티스레드랑 상관 X
//        p1.start(); // 실제로 OS에 스레드 동작을 요청
//        p.run(); // 그냥 메소드 콜이니까 다시 동작해도 잘 됨// 스레드랑 관계 X
//        p.start(); // Thread 객체는 1회용이므로 start()가 재실행될 수 없음(stream이 떠오르네)
        // 주니어들이 많이 하는 실수, 누구나 하는 실수

        //2. 람다식 구현
        Thread p2 = new Thread(() -> {

//            System.out.println("Thread by lambda");  //어떻게 바로구현하지?
            while (true) {

            }
        });
        p2.start();

//        p2.join(); // sleep 없이 join으로 싱크를 맞출 수 있다. Blcoking 동작( 끝날 때 까지 기다리는 것)
        p2.join(100); //100ms 동안 기다린 후 다음 동작 강제 시작
        p1.start();

//        Thread.sleep(100); // 예외 throw 해야 한다
        p1.join();

        Thread thread = new MyThread();
        thread.run();

        //4. 구현후 즉시 실행
        new Thread(() -> {
            System.out.println("IDEA");
        }).start(); // 객체에 대한 래퍼런스가 없기 때문에 join 등 활용이 어렵다
                    // 언제 동작해도 상관없는거면 간편하게 구현할수있다

        System.out.println("Main thread ended");  // p1 -> main -> p2 순으로 실행되지만 이순서는 고정된것은 아니다

        //p1, p2를 실행하고 main을 실행하고 싶으면 어떻게 해야 할까?
        // 방법 1)Thread.sleep()을 활용하여 쉬고 나서 실행하게 한다, 단점은 sleep한 만큼 시간이 느려진다
        //           main에서 하면 main thread가 sleep하고 해당 스레드에서 sleep하면 해당 스레드가 sleep된다
        //          sleep은 시간 설정을 각각의 시간을 잘설정해주어야 하는 단점도 추가된다.
        // 방법 2) join을 활용, p2가 끝날때까지 기다렸다가 join하고 p1이 실행된다.
        //          p2가 무한루프인경우 끝나지 않기때문에 무한히 기다린다.
        //          이럴 때는 join()에 시간을 지정해준다. 그럼 시간이후에는 다른 스레드들이 실행된다
        //          하지만 이상황에서는 p2스레드는 끝나지 않기 때문에 프로스세스는 끝나지 않는다


    }
}





























