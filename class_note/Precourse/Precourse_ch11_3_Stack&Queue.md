# Stack과 Queue 구현하기

Stack은 이미 java에서 제공을 하고 Queue는 ArrayList를 통해 많이 사용되는데 둘다 ArrayList를 통해 구현해보도록 하겠습니다



## Stack 구현하기

- Last In First Out(LIFO) : 맨 마지막에 추가된 요소가 가장 먼저 꺼내지는 자료구조
  - 상자가 쌓여있는 구조를 생각하면 된다
- 이미 구현된 클래스가 제공 된다
- ArayList나 LinkedList로 구현할 수 있다
- 게임에서 무르기나, 최근 자료 가져오기 등에 구현된다(오호..맞네)

```java
import java.util.ArrayList;

class MyStack {

    private ArrayList<String> arrayStack = new ArrayList<>();

    public void push(String data){
        arrayStack.add(data);
    }

    public String pop(){
        int len = arrayStack.size();
        if(len == 0){
            System.out.println("스택이 바뀌었습니다");
            return null;
        }
       return arrayStack.remove(len-1);
    }

        

}

public class StackTest{

    public static void main(String[] args) {
        MyStack stack   = new MyStack();
        stack.push("A");
        stack.push("B");
        stack.push("C");

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

}
```

## Queue 구현하기

- First in Fist Out(FIFO) : 먼저 저장된 자료가 먼저 꺼내지는 자료구조

- 선착순, 대기열등을 구현할 때 가장 많이 사용되는 자료 구조

  - 일반적으로도 가장 많이 사용되는 자료구조

- ArrayList나 LinkedList로 구현할 수 있다

- 맨앞(front)에서 빼고 맨 뒤(rear)에 요소를 추가한다

  - 요소를 추가하는 것은 enqueue, 빼는 것은 dequeue 라고 한다

- 큐는 스택 구현한 것에서 pop의 위치를 remove(len-1)에서 remove(0)변경 해주면 된다 구현은 생략

  