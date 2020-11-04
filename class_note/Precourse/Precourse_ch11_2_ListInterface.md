# 	List 인터페이스

- Colletion의 하위 인터페이스
- 객체를 순서에 따라 저장하고 관리하는데 필요한 메서드가 선언된 인터페이스
- 배열의 기능이 구현하기 위한 메서드가 선언된다
- 종류로 ArrayList, Vector, LinkedList가 있다



## ArrayList와 Vector

- 객체 배열 클래스

- Vector는 자바2부터 제공된 클래스이지만 일반적으로 최적화가 잘되어 있는 ArrayList를 더 많이 사용한다
- Vector는 멀티 쓰레드 프로그램에서 동기화를 지원 한다 
  - 동기화(synchronization) : 두 개의 쓰레드가 동시에 하나의 리소스에 접근 할 때 순서를 맞추어서 데이터의 오류가 방지하지 않도록 한다
- cpacity와 size는 다름 의미임
  - capacity는 배열의 용량(크기)
  - size는 그안에 들어가 있는 데이터의 량(
    - ex) 10개짜리 배열에 3개가 들어가 있으면 capacity는 10 size는 3

## ArrayList와 LinkedList

- 앞에서 배운 Array와 LinkedList의 차이와 같다
- 둘 다 순차적 구조를 구현한 클래스
- ArrayLists는 배열을 구현한 클래스로 논리적 순서와 물리적 순서가 동일 하다
  - 그래서 index를 통해 자료에 접근하는게 빠르다

![링크드2](C:\Users\foevn\Documents\dev\devlog\Images\링크드2.PNG)

- 위의 그림에서 처럼 링크만 제거하거나 열결하면 되기 때문에 LinkedList는 자료의 추가와 삭제가 쉽다

## 실습

- ArrayList

  - 기본 capacity는 10으로 되어 있고 부족할 때마다 capacity를 늘리게 되어 있음
  - 내부적으로는 Obeject array로 구현되어 있다
  - 제너릭 타입으로 타입을 지정해서 사용하게 된다

- Vector

  - 내부 메서드 안에 Synchronized 키워드가 붙어 있음, 나중에 멀티쓰레드 할때 필요한 내용
    - 여러개의 스레드가 한번에 접근할때 순서를 맞춰주는 기능

- LinkedList

  ```java
  import java.util.LinkedList;
  
  public class LinkedListTest {
      public static void main(String[] args) {
  
          LinkedList<String> myList = new LinkedList<String>();
  
          myList.add("A"); //add() 공통적으로 자료를 입력하는 메서드
          myList.add("B");
          myList.add("c");
  
          System.out.println(myList); // toString()메서드, 요소를 보여주는역할
  
          myList.add(1,"D"); //index를 지정하여 입력
          System.out.println(myList);
          myList.removeLast();// 마지막 자료를 지우는 메서드(First도있음)
          System.out.println(myList);
  
          for(int i =0; i<myList.size(); i++){ //List들은 이렇게 index를 통해 접근가능//순서에 따라 저장하기 때문에
              String s = myList.get(i);       //나중에 배울 set은 이런게 어려움
              System.out.println(s);
          }
      }
  }
  ```

- 

  

- add(), toString(), remove(), get()메서드를 사용할 수 있다 ( ArrayList와 비슷한 부분이네)
- get()을 통해 자료에 접근할 수 있는것은 LIst인터페이스만 가능하고 Set인터페이스는 불가능한데 이유는 List인터페이스는 순차적으로 자료를 저장고 Set은 순차적으로 저장하지 않기 때문에 그렇다
  
  - 그 외에도 List는 중복된 자료를 허용하고 Set은 중복된 자료를 허용하지 않는다는 특징이 있다.