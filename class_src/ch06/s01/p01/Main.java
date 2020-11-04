package ch06.s01.p01;

/**
 * 디자인 패턴 (Design Patterns)
 * - 자주 발생하는 문제를 쉽게 해결하기 위해 제시된 재사용 가능한 해결책
 *      - Don't reinvent the wheel(마차를 만들기 위해서 바퀴부터 만들 필요는 없다)
 * - 소프트웨어 설계 문제를 쉽게 해결할 수 있도록 패턴화된 설계 방식
 * - 팀원들과의 소통을 위해 디자인 패턴 학습이 필요
 *      - 이거 싱글톤으로 구현하면되겠네 이거 싱글톤으로 구현해주세요 라고 하면 그것으로 구현하면 된다
 *      - 설명이 불필요하게 된다
 *
 * - 문맥 -> 문제(어떤 문제가 발생할수 있는지) -> 해결(어떤걸 적용해서 해결하게됬는지)  순으로 서술이 된다
 * - 다양한 디자인패턴들이 강의안에 정리가 되어있으니 읽어보기
 */

/**
 * Singleton
 * - 단 하나의 객체만 존재하도록 하는 클래스
 *      - 어디에서 접근 해도 같은 객체에만 적근하게 된다
 * - 프로그램 전체의 '상태'를 정의 하는 데 많이 사용
 *      - 예를 들면 게임의 진행 상태, GUI 전체를 통솔하기 위해
 */

//class Singleton {
//    private static Singleton instance; // private으로 선언
//
//    private Singleton() {}
//
//    public static Singleton getInstance() {
//        if (instance == null) { // Lazy instanciation객체화 /
//                                //  객체를 만드는 것 자체가 메모리를 사용하고 로딩속도에 영향을 주기 때문에 실제 사용할 때 객체를 만드는 것
//            instance = new Singleton();
//        }
//        // 로딩속도 개선, 메모리 낭비 방지
//        return instance;
//    }
//}

    interface Shape {
        void draw();
}

class RoundedRectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Rounded Rectangle::draw() method.");
    }
}

class RoundedSquare implements Shape {

    @Override
    public void draw() {
        System.out.println("Rounded Rectangle::draw() method.");
    }
}

class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}

class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}

class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}

abstract class AbstractFactory {
        abstract Shape getShape(String ShapeType) ;
}


class ShapeFactory extends AbstractFactory {

        Shape getShape(String shapeType) {
            if(shapeType.equalsIgnoreCase("CIRCLE")) {
                return new Circle();
            } else if(shapeType.equalsIgnoreCase("RECTANGLE")) {
                return new Rectangle();
            } else if(shapeType.equalsIgnoreCase("SQUARE")) {
                return new Square();
            } else {
                return null;
            }
        }

}

class RoundedShapeFactory extends AbstractFactory {

    Shape getShape(String shapeType) {

        if(shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new RoundedRectangle();
        } else if(shapeType.equalsIgnoreCase("SQUARE")) {
            return new RoundedSquare();
        } else {
            return null;
        }
    }

}

class FactoryProducer {
        public static AbstractFactory getFactory(boolean rounded) {
            if(rounded) {
                return new RoundedShapeFactory();
            } else {
                return new ShapeFactory();
            }
        }
}

public class Main {
    public static void main(String[] args) {

//        ShapeFactory shapeFactory = new ShapeFactory();
//
//        Shape shape1 = shapeFactory.getShape("CIRCLE");
//        shape1.draw();
//        Shape shape2 = shapeFactory.getShape("RECTANGLE");
//        shape2.draw();
//        Shape shape3 = shapeFactory.getShape("SQUARE");
//        shape3.draw();

        FactoryProducer.getFactory(true).getShape("rectangle").draw();
    }

}


















