package ch04.p01;

/**
 * AbstactStack
 * -> Stack (스택이라는 자료구조? => 후입선출 방식)
 *
 * is_empty()
 * push()
 * pop()
 * peek()
 *
 * 데이터가 들어갈때는 push()로 들어감, pop()은 데이터뺌
 * peek()를 하면 데이터를 빼지않고 보기만함,
 *
 * 1 - 3 - 4 순으로 push를 했으면 pop을 하면 4가 먼저나오고 3이나오고 그다음 1이 나옴(후입선출)
 * is_empty()는 비어있는지 확인하는것
 * peek()은 쳐다만보고 빼지는 않는 것임
 */

// 일부는 구현을 해놨음, 변하지 않고 사용될 애들 => 느낌강하게 final을 입력할수 잇음
    // 그외에 추상메서드는 자유도가 있는애들, 구현해야햐지만 자유도있게 구현해라
abstract class AbstractStack {
    protected int capacity;
    protected int top; // 마지막으로 들어온데이터위치, 즉 length와 비슷한 느낌의 스택용

    public AbstractStack(int capacity){
        this.capacity = capacity;
        this.top = 0;
    }

    public boolean isEmpty(){
        return this.top ==0; // top이 0이면 비어있으니 true
    }

    public abstract void push(int value);
    public abstract int pop();
    public abstract int peek();
}

// 추상메서드를 통해 ArrayStack뿐아니라 다른 방식의 Stack을 만들 수 있게됨.
class ArrayStack extends AbstractStack{
    private int[] array;

    public ArrayStack(int capacity){
        super(capacity);
        array = new int[capacity];
    }

    @Override
    public void push(int value) {
        if(top == capacity){
            int[] new_array = new int[capacity *2];
            for (int i = 0; i <array.length; i++){ // for문을 arraycopoy로 대체할수있다는 매세지뜸
                new_array[i] = array[i];
            }
            array = new_array;
            capacity *= 2;
        }
        array[top++] = value;
    }

    @Override
    public int pop() {
        if(isEmpty()){
            return -1;
        }
        return array[--top]; // top에서 -1을 해준값을 뽑아줘야지 배열은 0에서시작하니까~
                             // 빼주기 위해서 실제값을 줄여줌
    }

    @Override
    public int peek() {
        if(isEmpty()){
            return -1;
        }

        return array[top-1]; // 보기만 하면되니까 그냥 -1하면됨, 오호옿오호
    }


}
// 기본 기능 + 2가지에러
// 에러1 : add하는데 배열에 capacity 부족할때
// 에러2 : pop하는데 빼는데 배열을 초과했을때 에러발생

public class AbstractClassPractice {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(10); // 데이터 10개 입력할 수 있음
        // ArrayStack => AbstracStack 으로 대체가능


        for (int i =0; i<15; i++){
            stack.push(i);
        } // 0~9
        System.out.println(stack.peek());// 9
//        System.out.println(stack.peek());// 9

//        for(int i =0; i <20; i++){
//            System.out.println(stack.pop()); // 9~0 출력
//        } // 9~0, 반대로되겟지
//
//        for(int i =0; i<15; i++){
//            System.out.println(stack.peek(i));
//        }
    }
}


