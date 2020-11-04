# Desing Patterns

## 디자인 패턴이란

- 디자인 패턴이란
  - 자주 발생하는 문제를 해결하기 이ㅜ해 설계된 재사용 가능한 해결책
    - Don't reinvent the wheel(마차를 만들기 위해서 바퀴부터 만들 필요는 없다)
  - 소프트웨어 설계 문제를 쉽게 해결할 수 있도록 패턴화된 설계 방식
  - 팀원들과 소통을 위해 디자인 패턴 학습이 필요하다
    - 설계에 대한 설명을 줄일 수 있다

## 디자인 패턴의 구조

- 문맥(Context)
  - 패턴이 적용될 수 있는 문제 상황을 기술
- 문제(Problem)
  - 패턴이 적용되어 해결되어야 하는 여러 설계 이슈를 기술
- 해결(Solution)
  - 문제를 해결하는 설계 구성 요소와 구성 요소 사이의 관계를 기술
- 문맥 -> 문제 -> 해결 순으로 서술이 된다

## 대표적인 디자인 패턴

- 싱글톤 패턴

  - 단 하나의 객체만 존재할 수 있는 클래스를 구현하는 패턴
    - 어디에서 접근 해도 같은 객체에만 접근하게 된다
  - 프로그램 전체의 '상태'를 정의하는 데 만힝 사용된다
    - 예를 들면 게임의 진행 상태, GUI 전체를 통솔하기 위해

  ```java
  lass Singleton {
      private static Singleton instance;// private으로 선언
      
      private Singleton() {}
      
      public static Singleton getInstance() {
          if(instance == null) { // Lazy instanciation 객체화
                                 // 객체를 만드는 것 자체가 메모리를 사용하고 로딩속도에 영향을 주기 때문에 실제 사용할 때 객체를 만드는 것
              instance = new Singleton();
          }
          // 로딩속도 개선, 메모리 낭비 방지
          return instance;
      }
  }
  ```

- 팩토리 패턴

  - 구상 클래스 객체(추상 클래스를 구현한 것; Concrete class)를 전담하여 생성하는 클래스를 구현하는 패턴

    - 추상 클래의 반대에 의미로 구상클래스라고 부른다

    

```java
interface Shape {
    void draw();
}

class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Rectangle drawn");
    }
}

class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Square drawn");
    }
}

class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Circle drawn");
    }
}


class RoundedRectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Rectangle drawn");
    }
}

class RoundedSquare implements Shape {
    @Override
    public void draw() {
        System.out.println("Square drawn");
    }
}

// 팩토리 메소드 패턴
// 팩토리 클래스가 다른 클래스에서 가지게 될 의존성을 모두 가져옴
class ShapeFactory {
    Shape getShape(String shapeType) {
        Shape shape;
        if (shapeType.equals("Circle")) {
            shape = new Circle();
        } else if (shapeType.equals("Rectangle")) {
            shape = new Rectangle();
        } else if (shapeType.equals("Square")) {
            shape = new Square();
        } else {
            shape = null;
        }

        return shape;
    }
}

// 추상 팩토리 패턴 (Abstract factory pattern)
// 팩토리 자체도 추상화를 해버린다
abstract class AbstractFactory { // 추상팩토리 생성
    abstract Shape getShape(String shapeType);
}

class RoundedShapeFactory extends AbstractFactory {
    @Override
    Shape getShape(String shapeType) {
        Shape shape = null;
        if (shapeType.equals("Circle")) {
            shape = new Circle();
        } else if (shapeType.equals("Rectangle")) {
            shape = new RoundedRectangle();
        } else if (shapeType.equals("Square")) {
            shape = new RoundedSquare();
        }

        return shape;
    }
}

class NormalShapeFactory extends AbstractFactory {
    @Override
    Shape getShape(String shapeType) {
        Shape shape = null;
        if (shapeType.equals("Circle")) {
            shape = new Circle();
        } else if (shapeType.equals("Rectangle")) {
            shape = new Rectangle();
        } else if (shapeType.equals("Square")) {
            shape = new Square();
        }

        return shape;
    }
}

class FactoryProducer {
    public static AbstractFactory getFactory(boolean rounded) {
        if (rounded) {
            return new RoundedShapeFactory();
        } else {
            return new NormalShapeFactory();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        String shapeType = "Circle"; // 외부에서 어떤식으로 그릴지 주워지는 상황 // 실제로 많이 있는 상황이래
        Shape shape;

        // 우리가 원래하던 폴리몰피즘 이용한 방법
        // 매번 직접 객체를 생성하면
        // 클래스가 구상 클래스에 의존하게 됨 => 구상클래스는 내용이 자주 바뀔 수 있기 때문에 의존하지 않는게 좋다
        // 클래스는 추상 클래스(인터페이스)에 의존한느 것이 더 바람직 하다
        // 그래서 등장한 것이 팩토리메소드 패턴
        if (shapeType.equals("Circle")) {
            shape = new Circle();
        } else if (shapeType.equals("Rectangle")) {
            shape = new Rectangle();
        } else if (shapeType.equals("Square")) {
            shape = new Square();
        } else {
            shape = null;
        }

        if (shape != null) {
            shape.draw();
        }

        ShapeFactory factory = new ShapeFactory();
        Shape shape1 = factory.getShape("circle");

        //의존성은
        //상속, has-A관계,
        // main 에서 생성자가 호출이 되기 때문에 의존성이 생기게 된다
        // 의존성이 생기면 Circle()에 이름이 바뀌면 모든 래퍼런스도 고쳐주어야 한다
        // 어쨋든 구상클래스의 의존성은 무조건 팩토리가 먹어주는게 좋다! 병목으로 먹어준다!


        //추상팩토리 구현하는것 들어가야된다
        Shape shape2 = FactoryProducer
                .getFactory(true)
                .getShape("Rectangle");
        shape2.draw(); // Rounded Rentangle

        //구상 클래스를 직접 래퍼런스하지 않고 의존성을 줄여 편리하게 사용할 수 있다
        // 이미 프레임워크에 이렇게 구현이 되어있기 때문에 이런 코드들을 읽고 사용할 수 있다다
        // 스프링/ GUI는 특히 렇게 구현되어있다
    }
}
```

- 옵저버 패턴
  - Observable 객체의 변화를 Observer에서 알 수 있도록 하는 패턴
    - Observable 자체는 Deprecated라 구현할수 없지만 필요하면 이런식으로 구현해서 사용할 수 있다
  - GUI와 게임에 많이 쓰이는 패턴
    - 즉, 보통 Hierarcy가 있는 경우 (계층이 있는 경우) 많이 사용된다
    - 필요할 때 필요한 것을 렌더링 할 수 있도록 계층적으로 옵저버링을 해서 어떤 레벨에서 필요할 때 마다 렌더링을 하게된다

```java
class Subject { // 관찰 대상 클래스 // Observable이 Deprecated 이다
    private List<Observer> observers = new ArrayList<>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();

    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for (Observer observer: observers) {
            observer.update();
        }
    }

}

abstract class Observer {
    protected Subject subject;
    public abstract void update();
}

class BinaryObserver extends Observer {
    public BinaryObserver (Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        int state = this.subject.getState();
        System.out.println(Integer.toBinaryString(state));
    }
}

class OctalObserver extends Observer {
    public OctalObserver (Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        int state = this.subject.getState();
        System.out.println(Integer.toOctalString(state));
    }
}

class HexObserver extends Observer {
    public HexObserver (Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        int state = this.subject.getState();
        System.out.println(Integer.toHexString(state));
    }
}

public class Main {
    public static void main(String[] args) {
        Subject subject = new Subject();
        Observer observerOne = new HexObserver(subject); //상호 등록이 된다
        Observer observerTwo = new BinaryObserver(subject);
        Observer observerThree = new OctalObserver(subject);

        subject.setState(10);
        //a
        //1010
        //12
        subject.setState(20);
        //14
        //10100
        //24
        subject.setState(40);
        //28
        //101000
        //50

        //subject의 변화를 Observer가 감지하고 이런것들을 출력한다
        //틱택게임에서 상호래퍼런스를 할 때 옵저버패턴으로 볼 수 있다다

   }

}
```