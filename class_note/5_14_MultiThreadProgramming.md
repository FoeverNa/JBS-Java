# Multi-Thread Programming

- 백엔드 입장에서는 많이 사용하진 않지만 가끔 쓰이지만 그때는 잘 사용하면 좋다..
  - 그래서 배우기가 애매한 것이래

## Process and Thread

- Process: OS로부터 메모리를 할당받아 실행중인 프로그램
  - 어떤 프로그램을 실행하면 특정 램을 차지하면서 돌아가는데 스택과 힙메모리가 차지하게 된다
    - 그 자치하고 있는 범위가 프로세스의 범위이다
    - 다른 프로세스에서는 해당 프로세스에 접근할 수 가없다
    - 메모리를 할당받아 동작할 수 있는 최대 범위가 프로세스이다 => 복습좀해보자
- Thread: 프로세스 동작의 최소 단위로, 하나의 프로세스는 여러 스레드로 이루어질 수 있다.
  - 같은 프로세스 내에서 스레드는 메모리를 서로 공유를 한다
  - 같은 프로세스내에 동작을 여러 흐름으로 하는 것이 각각의 스레드이다
  - 멀티 스레드 작동시 기본적으로 메인스레드가 있고 추가로 보조 스레드를 만들어서 사용하게 된다

## 멀티스레드 프로그래밍의 장단점

- 장점
  - 여러 동작을 병렬적으로 처리하여 CPU의 사용률 향상
    - CPU를 갈군다..라고 한데.. 잘갈굴수록 좋은 프로그램이래
    - 주로 랜더링 작업을 할 때 프리미어나 베가스 같은 프로그램 사용한다고 하면 편집할 때는 램을 많이 사용하지만 실제 랜더링을 할 때는 CPU사용율이 많이 올라간다
    - 라이젠이 스레드를 동시에 많이 돌리게해준데 그래서 멀티 스레드상황에서 유리하다
  - 시간이 걸리는 동작을 분리하여 프로그램의 응답성 향상
- 단점
  - Context Switching 오버헤드 발생
    - 여러 스레드가 동시에 동작할 수 있고 아닐 수 도 있다.(다른스레드가 돌아가는동안 스레드가 대기할 수 있다)
      - 일부만 병렬적으로 동작을 하게 되고 서로 스위칭하면서 오버헤드가 발생할 수가 있다.
        - => 여러 스레드가 있는 것이 좋은것만 아닌 이유가 된다
  - 스레드 제어의 어려움
    - 개발자가 느끼는 어려움. 현업에서도 많이 어려워하는 부분.
      - 난이도가 있다는것은 시간이 오래걸린다는 뜻. 시간이 오래걸리는것은 비싼 프로그래밍 => 이런걸 잘이해할필요가 있데

        

## 스레드 구현

- 스레드 생성

  ```java
  Thread threadOne = new Thread(new Runnable() { // 익명 내부클래스로 스레드를 만들어준다.  Runnable Interface를 구현한다
      public void run() { // runmethod를 한번 실행하는 스레드가 있는 것
          System.out.println("Hello Thread!"); // Main 스레드가 일자로 진행된다면 특정시점에서 스레드가 추가로 실행됬다고 반환되는 형식
      }
  });
  
  Thread threadTwo = new Thread(() -> { // 람다식을 활용하여 바로 생성한다
      System.out.println("Hello Again, Thread!");
  });
  
  class MyThread extends Thread { //  클래스를 만들어서 쓰레드를 상속하여 생성해도 괜찮다
      @Override
      public void run() {
          System.out.println("Hello Again Again, Thread!");
      }
  }
  Thread threadThree = new MyThread();
  ```

- 스레드 실행

  ```java
  Thread threadOne = new Thread(() -> {
      for (int i = 0; i < 10; i++) {
          System.out.print("1");
      }
  });
  
  Thread threadTwo = new Thread(() -> {
      for (int i = 0; i < 10; i++) {
          System.out.print("2");
      }
  });
  
  threadOne.start(); // 스타트 매서드를 사용해줘야 실행이 된다
  threadTwo.start();
  System.out.println("Done!");
  ```

## 스레드의 상태 및 제어

- 스레드를 컨트롤 할 필요가 있기 때문에 상태를 알아야 한다

- 스레드의 상태

  - getState() 메소드로 스레드의 상태를 확인할 수 있다.

    | 열거형 상수     | 설명                                                         |
    | --------------- | ------------------------------------------------------------ |
    | `NEW`           | `start()` 메소드가 아직 호출되지 않음                        |
    | `RUNNABLE`      | JVM에 의해 실행 가능한 상태                                  |
    | `BLOCKED`       | 객체가 블락된 상태                                           |
    | `WAITING`       | `sleep()`, `wait()`, `join()` 등에 의해 무한히 대기 중인 상태 |
    | `TIMED_WAITING` | `sleep()`, `wait()`, `join()` 등에 의해 정해진 시간 동안 대기 중인 상태 |
    | `TERMINATE`     | `run()` 메소드가 종료된 상태                                 |

- 스레드의 우선순위 제어

  ```java
  public final static int MIN_PRIORITY = 1;
  public final static int NORM_PRIORITY = 5; //기본값
  public final static int MAX_PRIORITY = 10;
  ```

  | 메소드                              | 설명                        |
  | ----------------------------------- | --------------------------- |
  | `void setPriority(int newPriority)` | 새로운 우선순위로 설정한다. |
  | `int getPriority()`                 | 우선순위를 반환한다.        |

- `sleep()`을 이용한 제어

  ```java
  Thread.sleep(1000); // ms
  Thread.sleep(100, 200); // ms + ns
  ```

- `join()`을 이용한 스레드 조인

  - 스레드 동작을 동기화하기 위해 사용

  ```java
  Thread t1 = new Thread(() -> System.out.println("A"));
  Thread t2 = new Thread(() -> System.out.println("B"));
  
  t1.start();
  t2.start();
  
  t1.join();
  t2.join();
  
  System.out.println("C");
  ```

- `interrupt()`를 이용한 대기 중지

  ```java
  Thread tSleep = new Thread(() -> {
      try {
          Thread.sleep(1000);
      } catch (InterruptedException e) {
          System.out.println("Interrupted");
      }
  });
  
  tSleep.start();
  
  try {
      Thread.sleep(500);
  } catch (InterruptedException e) {
      e.printStackTrace();
  }
  
  tSleep.interrupt();
  ```

- `yield()`를 이용한 상태 제어

  - `sleep()`과 달리 곧바로 `RUNNABLE` 상태로 변경

  ```java
  new Thread(() -> {
      for (int i = 0; i < 20; i++) {
          if (i % 2 == 0) {
              System.out.print("1");
          } else {
              Thread.yield();
          }
      }
  }).start();
  
  new Thread(() -> {
      for (int i = 0; i < 20; i++) {
          if (i % 2 == 0) {
              System.out.print("2");
          } else {
              Thread.yield();
          }
      }
  }).start();
  ```

- 스레드의 종료

  - run() 메소드의 종료
  - `stop()` 메소드 호출 (deprecated)

## 데몬 스레드

- 다른 스레드가 종료될 경우 함께 종료되는 보조 스레드

- 보통 대기하며 동작하는 무한 루프로 구현

- `setDaemon()` 메소드로 데몬 스레드로 설정

  ```java
  class DaemonThread extends Thread {
      public DaemonThread() {
          this.setDaemon(true);
      }
  
      @Override
      public void run() {
          while (true) {
              try {
                  Thread.sleep(1000);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              System.out.println("Daemon Thread Run");
          }
      }
  }
  ```

## 데이터 공유와 동기화

- 스레드간 데이터 공유 시 신뢰성에 문제가 발생할 수 있음

  ```java
  class Counter {
        int count = 0;
  }
  
  Counter counter = new Counter();
  
  for (int i = 0; i < 1000; i++) {
      new Thread(() -> {
          for (int j = 0; j < 1000; j++) {
              counter.count = counter.count + 1;
          }
      }).start();
  }
  
  try {
      Thread.sleep(1000);
  } catch (InterruptedException e) {
      e.printStackTrace();
  }
  
  System.out.println(counter.count);
  ```

- `synchronized` 키워드 사용

  ```java
  synchronized void method() {
      // 공유 데이터 사용
  }
  ```

  ```java
  void method() {
      synchronized(sharedObj) {
          // 공유 데이터 사용
      }
  }
  ```

- `wait()`, `notify()`, `notifyAll()`

  ```java
  class WorkObject {
        public synchronized void methodA() {
            System.out.println("ThreadA의 methodA() 작업 실행");
            notify(); // 일시정지 상태에 있는 ThreadB를 실행 대기상태로 만듬 
            try {
                wait(); // ThreadA를 일시 정지 상태로 만듬 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    
        public synchronized void methodB() {
            System.out.println("ThreadB의 methodB() 작업 실행");
            notify(); // 일시정지 상태에 있는 ThreadA를 실행 대기상태로 만듬
            try {
                wait(); // ThreadB를 일시 정지 상태로 만듬
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
  
    class ThreadA extends Thread {
        private WorkObject workObject;
  
        ThreadA(WorkObject workObject) {
            this.workObject = workObject;
        }
        
        public void run() {
            for(int i=0; i<10; i++) {
                workObject.methodA(); // 공유객체의 methodA를 반복적으로 호출 
            }
        }
    }
  
    class ThreadB extends Thread{
        private WorkObject workObject;
  
        ThreadB(WorkObject workObject) {
            this.workObject = workObject;
        }
        
        public void run() {
            for(int i=0; i<10; i++) {
                workObject.methodB(); // 공유객체의 methodA를 반복적으로 호출 
            }
        }
    }
  
    class Main {
        public static void main(String[] args) {
            WorkObject sharedObject = new WorkObject();
            
            ThreadA threadA = new ThreadA(sharedObject);
            ThreadB threadB = new ThreadB(sharedObject);
            
            threadA.start();
            threadB.start();
        }
  }
  ```