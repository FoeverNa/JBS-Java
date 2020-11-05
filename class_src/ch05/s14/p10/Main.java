package ch05.s14.p10;

import java.util.concurrent.*;

/**
 * Thread Pool
 * - 스레를 직접 만들어 사용할 경우, 작업을 마친 스레드는 종료됨(1회용)
 *      -> 멀티스레드 작업을 계속 할 경우, 스레드를 생성/삭제하는 오버헤드가 있다.(상당한 오버헤드라고 하심)
 * - 스레드 풀
 *      - 미리 스레드를 생성해 두고, 작업만 스레드에 할당하여 동작
 *      - 미리 생성해 둔 스레드의 집합을 스레드 풀이라고 한다(무엇인가 레디되있는 것을 모아놓은것을 풀이라고한다)
 *      - 배치 작업( 모아두고 한번에 처리한 작업)에 많이 사용 한다
 *
 */

public class Main {
    public static void main(String[] args) throws InterruptedException {
        //1.스레드 풀 생성
        //1)
        ExecutorService pool1 = Executors.newCachedThreadPool();
        /**
         * newCachedThreadPool
         * - 초기 스레드가 0개
         * - 코어 스레드가 0개 - 일하지 않아도 살려두는 스레드
         * - 요청 작업보다 스레드가 부족하면 새 스레드를 생성한다
         * - 60초 동안 일하지 않은 스레드는 제거한다(죽여 버린다)
         * 어떻게 최적화 할지 모르는 상황에서 무난한 선택지가 될 수 있다
         * 코어스레드가 0개이기 때문에 작업안하는 스레드는 모두 죽는다
         */
        //2)
        ExecutorService pool2 = Executors.newFixedThreadPool(10);
        /**
         * newFixerdThreadPool
         * - 최대 스레드 nThread개
         * - 코어 스레드 nThread개 (파라미터)
         * - 요청 작업보다 스레드가 부족하면 새 스레드 생성 한다
         * - 작업하지 않는 스레드도 제거하지 않고 동작 한다
         */
        //3)
        ExecutorService es = new ThreadPoolExecutor( // 스레드풀 생성하는애3인데 뭐가다른거지
                10, // 코어 스레드 개수
                100, // 최대 스레드 개수
                120, // 대기시간(일하지 않으면 제거하는 시간)
                TimeUnit.SECONDS, //대기시간의 단위
                new SynchronousQueue<Runnable>() //작업을 담아둘 Queue
                //요청 -> 작업을 쌓아둘 큐 -> 스레드풀
                // main에게 온요청을 큐에던저주고 main은 갈길가는거임. 아니면 main이 요청처리해주어야되서
        );

        // 3번이 가장 자유도가 높고 1,2번은 그걸 쉽게 쓰기위해 만들어놓은 것이다 오케이
        // 1~3번은 같은 인터페이스 상속함으로 사용하는 것은 똑같다. 필요에 따라 골라 사용하면 된다

        //2. 스레드에 할당할 작업 생성
        class Work implements Runnable {
            @Override
            public void run() {
                for (int i = 0; i < 100 ; i++) {
                    System.out.println(i);
                }
            }
        }

        class CallableWork implements Callable<String> {
            @Override
            public String call() throws InterruptedException {
                Thread.sleep(10000);
                return "작업종료";
            }
        }
        // 3. 스레드 작업요청
        Future<String> future; // 미래에 값을 담아주는 클래스, 멀티스레드가 돌아가고 있어서 답이 아직 안왔어도 이런애가올거라는친구
                                // 멀티스레드와 main 스레드를 동기화 시켜주는 클래스 (멀티스레드 작업이 끝날때까지 기다려주는것이 동기화)
        future = es.submit(new CallableWork());
        for (int i = 0; i < 100 ; i++) {
           es.submit(new Work()); // Runnable 객체를 만들어서 넣어준것
            //submit과 execute 차이
        }
        // 실행결과 프로그램이 끝나지 않는이유?
        // 스레드 풀은 자동 종료가 안되기 때문에(이게 존재의의), 직접 스레드풀을 직접 종료해 주어야 한다.

        //4. 스레드 종료(동기화: 끝나는걸 기다렸다가 끝내니까 동기화라고 볼수있다다)
       es.shutdown(); // Thread.join()과 마찬가지로 작업이 끝나기를 기다려서 종료한다.
        //shutdown now는 기다리않고 바로 종료하게 된다
        // 복잡해보이지만 정형화되어 있는 작업
        for (int i = 100; i < 200 ; i++) {
            es.submit(new Work());
        }

        Thread.sleep(1000); //메인 스레드가 먼저도착할수있으니 기다려주는애


//        future.cancel(true);     // cancel을 호출하고 나면 get을 호출할 수 없다(CancellationException) 발생
                                                    // mayInterruptIfRunning이 true이면, 스레드에 interrupt도 발생시킨다.


        try {                                   //Future 객체가 채워질때까지 기다렸다가 채워지면 .get을 하고 넘어간다
            System.out.println(future.get()); //get()은 Blocking method //멀티스레드가 future의 객체를 채워줄때까지 main이 여기서 기다림
            // get()이 영원히 기다릴 수 있으므로 timeout 설정 가능
            System.out.println(future.isCancelled());
            System.out.println(future.isDone());
            //이런 메서드들로 외부에서 얘를 컨트롷할 수 있게 지원해 주는 것
        } catch (InterruptedException e) { //Interrupte 걸수가 있음

            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // 백엔드 개발자는 멀티스레드 사용하면 우리는 스레드풀을 사용하게 된다
        // 우리가 하는 작업들이 병렬적으로 빵하는 작업들이 많기 때문
        // 여러 옵션들이 있는데 그건 상황에 따라 다르기 때문이라 상황에 맞춰 설정해주어야한다.
        // 따로 설정해서 쓰는것은 GUI나 다른 멀티스레드 필요환경에서 사용하게 된다



    }
}
