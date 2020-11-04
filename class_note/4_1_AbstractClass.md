# 추상 클래스(Abstract Class)



 - 일부 메소드가 구현되지 않고, 선언만 되어있는 클래스
    - 선언만 되어있는 메서드 = 추상 메서드
      
      - 추상메서드를 보유하고 있는 클래스 = 추상클래스
      
        
    
 - 추상클래스를 상속하는 자식 클래스에서 추상메서드를 반드시 구현해야 한다

    

 - is a kind of 의 기능을 하며, 펭귄 is kind of a 뽀로로 에서 펭귄에 역할이다

    

 - 효과 : 필요한 모든 클래스(메서드?)가 구현 될 수 있도록 하여 안정성을 높인다

     

    

```java
abstract class AbstractFoo{
//(추상)
    int x, y;

    public void method() {
        System.out.println("method");
    }
                                        // 세미콜론을 잊지 말아야 합니다. => 시험나옴!!!!
    public abstract void abstractMethod(); // 선언만 하고 구현하지 않음
}

class Foo extends AbstractFoo { //만들자마자 화냄 추상메서드 하라고 이때 창띄워서 implements 클릭해주면됨
    @Override
    public void abstractMethod() {
        System.out.println("implemented abstractMethod");
    }

}




public class AbstractClass {
    public static void main(String[] args) {
        //   AbstractFoo afoo = new AbstractFoo(); // 추상클래스는 객체생성이 불가

        Foo foo = new Foo();
        foo.abstractMethod();// "implemneted absgractMethod"

        AbstractFoo afoo = (AbstractFoo) foo; // 부모클래스형으로 자식객체 받음
        afoo.abstractMethod(); // "implemented abstractMethod"
        // virtual method call
        // 아무것도 구현하지 않앗어도 자식메서드를 받을 수 있음

        // 추상 클래스는 객체 생성은 불가하지만 구현된 자식 클래스의 객체는 받을 수 있다.


    }
}
```

- 추상 클래스는 추상메서드를 포함한 클래스로 클래스명 앞에 abstract로 표시해준다(Modifier)

    

- 추상 메서드는 메서드의 실행문이 생략된 형태로 {}까지 생략한다 ex) public abstract void abstractMethod(); ';'이 중요

    

- 추상 클래스는 추상메서드를 구현해줄 자녀 클래스가 필요하다 ex) class Foo extends AbstractFoo{}

    

- 자녀 클래스에서는 추상메서드를 오버라이딩 하여 사용함
    - 자녀 클래스에서도 추상메서드를 오버라이딩 하지 않으면 자녀 클래스도 추상클래스가 됨
    
    - 이때 자녀클래스에서는 추상메서드를 구현해도되고 안해도 된다.
    
      
    
- 추상 클래스는 객체 생성이 불가능 하다
    - 추상 클래스를 부모 클래스형으로 하여 자녀 클래스의 인스턴스를 생성할 수 있음 // 부모 클래스 타입형은 가능하다
      
        - 이때도 virtual method call이 발생 => 보충필요
    - 하지만 추상클래스의 생성자는 작성 가능하다
    
        - 이는 자식 클래스의 생성자에 포함되기 위해서이다
        
          
    
- 추상메서드는 오버라이딩을 전제로 하기 때문에 private으로 선언할 수 없다(publc이 기본이다)

    

- 추상클래스는 정적메소드와 정적변수를 선언할 수 있고 추상클래스명으로 접근할 수 있다

    


## 추상클래스의 활용 예제

```java
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
        System.out.println(stack.peek());// 9

        for(int i =0; i <20; i++){
            System.out.println(stack.pop()); // 9~0 출력
        } // 9~0, 반대로되겟지
    }
}
```
- 추상화 메서드를 통해 Stack 자료구조를 여러가지 방식으로 구현할 수 있다
    - 위에서는 Array 이용해서 구했지만 다른것을 통해서도 구현할수 있데 => 나는잘모름
    



### 참고

- 추상클래스는 추상메서드를 하나라도 가져야 할까?
  - 답은 NO, but 추상메서드를 하나라도 가지는 클래스는 추상 클래스가 되어야한다.


