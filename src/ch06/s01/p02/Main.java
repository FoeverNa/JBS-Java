package ch06.s01.p02;

/**
 * 팩토리 페턴
 * - 구상 클래스 객체(추상 클래스를 구현한 것; Concrete class)
 * 를 전담하여 생성하는 클래스를 구현하는 패턴 (추상클래스의 반대에 의미로 구상클래스라고 부른다)
 */

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


























