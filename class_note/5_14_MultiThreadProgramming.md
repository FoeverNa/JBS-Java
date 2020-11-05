# Multi-Thread Programming

## 프로세스와 스레드

- 프로그램: 실행하면 OS로 부터 메모리가 할당되서 해당 메모리영역에 상주하면서 동작하게 되는데 그것을 프로세스라고 한다
- 스레드:  프로세스의 동작단위로 하나의 프로세스는 여러개의 스레드를 가질 수 있다.(최소 하나의 스레드를 가진다)
  - 여러개의 스레드를 사용하는 프로그래밍 기법을 프로그래밍이라고한다.

## 멀티스레드의 장단점

- 장점: 여러 동작을 병렬적으로 처리하여 CPU 사용률을 향상 시켜 처리속도를 향상 시킬 수 있다
  - 주로 인코딩, 렌더링, 배치 작업등 cpu가 하나의 작업에만 집중하면서 오래 걸리는 작업들에 사용된다
- 단점: 프로그램을 최적화 하는 것이기에 구현이 어렵고 여러개의 스레드가 동시에 동작하기에 디버깅 하기가 어렵다
  - 잘못 구현하면 오히려 스레드를 switching하는 과정에서 오버헤드가 발생하여 더 느려질 수 있다

## 멀티스레드의 실행 방식

- 동시성(Concurrency) : 멀티 작업을 위해 **하나의 코어**에서 멀티 스레드가 번갈아가며 실행하는 것
- 병렬성(Paralleism) : 멀티 작업을 위해 **멀티 코어**에서 개별 스레드를 동시에 실행하는 것
  - Stream API에 병렬처리하는게 대표적인 예이다.
- 참고자료: https://blog.naver.com/PostView.nhn?blogId=qbxlvnf11&logNo=220837131449&parentCategoryNo=&categoryNo=12&viewDate=&isShowPopularPosts=false&from=postView

## 스레드의 특징

- Thread의 객체는 1회용으로 .start()메소드를 통해 실행할 수 있고 해당 객체를 한번 사용하면 다시 사용할 수 없다
- Thread에는 1부터 10에 Priority가 존재하고 기본값은 5이다.
  - 높아질 수록 스레드의 우선순위가 높아진다.

## 스레드의 상태

- 스레드 스케줄링 : 스레드의 개수가 코어(CPU)의 수보다 많을 경우, 스레드를 어떤 순서에 의해 동시성으로 실행할 것인가를 정하는 작업
- 실행 대기상태 : 아직 스케줄링이 되지 않아서 실행을 기다리고 있는 상태
- 실행 상태: 실행 대기 상태에 있는 스레드 중에서 스레드 스케줄링으로 선택된 스레드가 CPU를 점유하고 run()메소드를 실행하는 상태
  - 스레드는 start()메소드를 호출하면 곧바로 스레드가 실행되는 것이 아닌 실행 대기 상태가 된다. 실행 대기 상태에 있는 스레드 중에서 스레드 스케줄링으로 선택된 스레드만이 실행 상태가 된다. 실행 상태의 스레드는 run()메소드를 모두 실행하기 전에 스레드 스케줄링에 의해 다시 실행 대기 상태로 돌아갈 수 있다. 그리고 실행 대기 상태에 있는 스레드가 선택되어 실행 상태가 된다
- 종료 상태: run() 메소드가 종료되어 더 이상 실행할 코드가 없어져 스레드의 실행을 멈추는 것
  - 조금씩 나누어서 실행하다가 실행할 코드가 없으면 스레드가 종료된다
- 일시 정지 상태: 스레드가 실행할 수 없는 상태로 WAITING, TIMED_WAITING, BLOCKED의 세 가지 상태가 존재한다
- 참고자료 : https://blog.naver.com/qbxlvnf11/220921178603

## 스레드의 제어

- sleep() 스레드를 해당 시간동안 일시정지 한뒤에 다시 대기상태가 된다
  - 대기시간도 있기 때문에 오버헤드가 yield()보다 크다
- join() 해당 스레드의 동작이 종료될 때까지 기다렸다가 다른 작업을 수행한다 -> 스레드 동기화에 사용 될 수 있다
  - 언제까지 기다리게 할 것인지 시간을 지정할 수 있다
  - https://www.javatpoint.com/join()-method 참고자료
- setPriority를 통해 스레드의 우선순위를 조정한다
  - OS에서도 자동으로 스레드들의 priority를 조정하기 때문에 조정해도 큰차이를 느낄 수 없다
- yield() 다른 스레드에 양보하고 바로 실행대기(sleep(0)과 같이)
  - 대기 상태에서 바로 다음에 동작할 수도 있다 
  - 자주 스레드가 변한다는 것은 오버헤드가 자주 발생해서 수행속도가 느려진다
- interrupt()는 sleep하고 있는 스레드를 interrupt 예외를 발생시켜 대기상태로 만들수 있다
  - 잘못 설정되어  오랫 동안 sleep하는 스레드들을 깨우는 역할이다.

## 데몬 스레드

- 다른 스레드가 종료될 경우 함께 종료되는 보조 스레드 보통 대기하며 동작하는 무한 루프로 구현 한다
  - 생성자에 setDaemon() 메소드로 데몬 스레드로 설정할 수 있다.
  - 예제에서는 시간마다 자동 저장되는 곳에 사용되었다.

## 데이터 공유와 동기화

- 스레드간 데이터 공유 시 신뢰성에 문제가 발생할 수 있다
  - 왜그런지는 조금더 보충해야 될 것 같다
- Intrinsic lcok
  - 객체가 intrinsic lock(고유 락)을 가진 스레드만 동작할 수 있도록 동기화하여 이런 문제 해결할 수 있다
    - 동기화 하는 방법 = synchronized 키워드 사용
      - Object 객체를 생성해서 sychonized에 매개변수에 입력하여 사용한다
      - 따로 객체생성하지 않고 메서드가 구현된 객체를 매개변수에 입력하여 사용한다
      - 사용 메서드에 synchronized 키워드를 사용한다 (메서드 구현된 객체를 lock으로 사용한다)
        - 사용하는 부분에만 sychonized를 하는게 최적화를 위해 가장 좋은 방식이라고 하였다.
      - 메서드를 사용하는 곳에 synchronized를 사용 한다
    - wait, notify로 동기화된 객체들을
      - notify =>: 일시 정지 상태에 있는 다른 스레드를 실행 대기 상태로 만든다
        - nitifyAll은 일시 정지 상태에 있는 다른 모든 스레드를 실행 대기 상태로 만든다
      - wait() => lock을 소유하고 있는 스레드가 lock을 반납하고 일시 정지 상태가 된다
      - 참고1: https://cornswrold.tistory.com/189
      - 

- 세마포
  - 세마포(Semaphore)는 동기화를 하는 또다른 방법이다.
    - 세마포는 가상의 Permit을 만들어 생성되고 외부의 스레드는 Permit을 요청해 확보(acquire)하거나 이전에 확보한 Permit을 반납(release)할 수 있다
      - 세마포 객체를 만들 때 argument에 permit의 갯수를 입력할 수 있다.(permit이 여러개 일 수 있다)
      - 해당 세마포 객체가 permit이 0개 일지라도 release()를 하면 permit이 하나 늘어난다(창조경제 인가)
    - 스레드가 aquire를 시도하는 방식은 다양하다
      - acuire(): permit을 acuire할 때까지 기다린다. interrupt로 방해할 수 있다
      - tryAcuire(): aquire을 시도하고 blocking하지 않고  결과에 따라 boolean을 반환한다
      - tryAcuire(timeout,TimeUnit): timeout만큼 기다리는데 단위가 TimeUnit이다. 결과에 따라 boolean을 반환한다
      - acquireUninterrupibly: acquire할 때까지 기다리지만 interrupt할 수 없다.
    - 참고자료:  https://aroundck.tistory.com/5873
  - 세마포를 활용해 '식사하는 철학자 라는 문제를 풀는 법을 배웠다'
    - 문제: https://namu.wiki/w/%EC%8B%9D%EC%82%AC%ED%95%98%EB%8A%94%20%EC%B2%A0%ED%95%99%EC%9E%90%20%EB%AC%B8%EC%A0%9C
    - 철학자들 스레드이고 집어야할 포크를 세마포로 보고 스레드들이 left right 포크에 permit을 모두획득했을 때 식사를 하도록 구현을 하는 방식이 었다.
      - 나중에 혼자 풀어보는 것을 당부하셨으니 꼭 풀어봐야 겠다
- JFC와 Sychronized
  - JFC에 일부 구현체는 Sychronized 키워드를 사용하여 구현되어 있는데 List에 Vector가 그대표적인 예이다
    - ArrayLIst같이 동기화 되지 않은 구현체를 동기화해서 사용하고 싶다면 Colletions.synchronizedList를 사용하여 동기화된 LIst로 변환하여 사용할 수 있다
      - 자료를 더 찾아보니 완전히 동기화 문제가 문제가 해결되는 것은 아니고 따로 동기화를 해주어야 한다고 한다
      - 참고자료 :https://okky.kr/article/279692

## 스레드풀

- 스레드는 할당된 작업을 마치면 종료되기 때문에 작업이 있을 때마다 생성해서 사용해야 한다. 하지만 스레드를 계속 생성할경우 생성과 종료시에 생기는 오버헤드 때문에 메모리에 부담을 준다

  - 스레드 풀은 미리 스레드를 생성해서 풀에 대기시켜 놓은 다음 작업이 요청될 때 해당 스레드를 사용하여 스레드에 오버헤드를 줄여 최적화하는 방식이다. 
  - 스데드풀에 스레드는 작업을 마치더라도 종료되지 않으며 다음 작업을 실행하게 된다
  - 작업들이 몰릴 경우 스레드가 모두 사용되고 있을 경우가 있기 때문에 작업들은 queue에 담겨서 스레드에게 분배된다.

- 스레드풀의 생성은 java.util.concurrent.Executors에 factory 메서드로 생성할 수 있다.

  - newFixedThreadPool 고정된 스레드풀을 간편히 생성할 때 사용할 수 있으며 생성할 때 스레드 갯수를 입력받는다.
  - newCachedThreadpool은 확장 가능한 스레드 풀로써 작업이 있을 때 스레드를 생성하고 스레드의 대기상태가 길어지면 스레드를 종료시켜서 최적화를 시키는 기능이 있다.
  - new ThreadPoolExecutor를 통해 여러가지 설정을 직접해서 생성할 수도 있는데 이때 코어 스레드 개수, 최대 스레드 개수, 대기시간(지나면 종료), 대기시간 단위, queue 객체 까지 입력받는다.
  - 참고자료: https://docs.oracle.com/javase/tutorial/essential/concurrency/pools.html

- 예제에 사용된 Interface 정리

  - Interface Callable
    - Runnable과 비슷한 인터페이스로 스레드에 사용되는 인터페이스지만 return값이 있고 check exception을 throws할 수 있다는 점이 다르다
      - 아직은 예외를 thows할 수 있는게 어떤 의미를 가지는지는 잘모르겠다..
    - https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/Callable.html
  - Interface Future
    - Future는 동기화된 결과가 완료되서 전달될 때까지 blocking 하여 값을 변수에 담는다
      - 스레드 풀에 실행 결과가 future 변수에 담기게 된다
    - 결과 값은 get()메서드를 사용해서 출력할 수 있고 작업이 완료될 때까지  blocking 되기 때문에 timout을 설정하거나 Interrupte할 수 있다.
    - cancel을 통해 작업을 취소할 수 있지만 작업이 완료되거나 이미 취소된 작업은 취소할 수 없다
      - 취소한 시점에 작업이 시작되지 않았다면 정상적으로 취소되고 작업은 시작되지 않는다.
      - 취소한 시점에 작업이 이미 시작되었다면 arguments에 입력하는 myInterruptIfRunning을 통해 해당 Thread에 Interrupt를 걸지 여부를 결졍할 수 있다.
    - https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/Future.html

- 주요 메서드

  - execute():Runnable 객체를 실행하는 메서드, 비동기적으로실행한다(?)
  - submit(): Runnable, Callable 객체를 모두 실행시킬 수 있으며 Future객체를 return한다.
    - Runnable의 Future객체는 작업이 완료됬는지 확인하는데 사용될 수 있다
  - shutdown(): 할당된 작업들이 모두 완료되면 스레드풀을 종료시키는 메서드. shutdown이후에 새로운 작업을 할당해도
  - 참고자료
    - http://tutorials.jenkov.com/java-util-concurrent/executorservice.html
    - https://docs.oracle.com/en/java/javase/15/docs/api/java.base/java/util/concurrent/Executor.html
    - https://docs.oracle.com/en/java/javase/15/docs/api/java.base/java/util/concurrent/ExecutorService.html

  

  - 백엔드 개발자는 멀티스레드를 사용한다면 배치작 업에서 스레드풀을 사용하는 경우가 많다고 하셨기 때문에 기억해두었다가 나중에 활용하면 좋을 것 같다

## 배치프레세스(Batch Processing 일괄처리)

- 거래를 개별적으로 처리하는 것이 아닌 한번에 모아서 일괄적으로 처리하는 기법
  - 주로 거래를 한번에 모아 정산하는 프로그램에 사용된다
  - 대량의 거래를 한번에 처리하기 때문에 시스템과부화가 생길 수 있어 이용자가 적은 시간대에 실행된다
- 일괄처리적으로 대량의 데이터를효율적으로 처리 하기 위해 멀티스레드 프로그래밍을 활용한다.
- 참고자료 : https://limkydev.tistory.com/140

