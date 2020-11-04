package ch04.p02;

class Calculator{
    private ArithmaticOperator op; // Has A 관게
    
    public Calculator(ArithmaticOperator op) {
        this.op = op;
    }

    public int run(int x, int y) {
        return op.operate(x,y);
    }
    public long run(long x, long y) {
        return op.operate(x,y);
    }
    public double run(double x, double y) {
        return op.operate(x,y);
    }
}

class Runner{
    public static void run(ArithmaticOperator op, int x, int y){
        System.out.println(op.operate(x,y));
    }
}

public class Main {
    public static void main(String[] args) {
        //다형성 예(1)
        ArithmaticOperator op;
        op = new Multiply();// new add, new Divide, new Subtract 을 넣을 수 있음
        // 이게 다형성임, 하나의 자료형으로 여러 객체의 메서드를 실행할수있는것

        System.out.println(op.operate(10,20));

        //다형성 예(2)
        ArithmaticOperator[] ops;
        ops = new ArithmaticOperator[] {new add(), new Multiply()};
        for(ArithmaticOperator o : ops){
            System.out.println(o.operate(5,2)); // 7,10 
            //다형성 : 여러객체를 한번에 묶어서 사용하는 것
            // 같아보이지만 다르게 구현하는것 오버라이딩과 버츄얼 콜을활용해서 
            
            
            // 이런 것을 하기위해서 있는것 여러 객체를 한번에 묶어서 사용하는 것
            // Array를 쓰는이유는 같은 자료형만 묶을 수 있는데 다형성을 쓰면 여러자료형이담길수잇겟다?
            // 인터페이스를 상속에 개념이다. 그러나 인터페이스는 객체가 아니다.
            // 인터페이스는 객체로 생성되야 할 이유가없음 멤버변수가 없기 때문에
            // virtual method call? => 정리해서말씀해주신데
            // 여러 인터페이스가 같이 임플레멘트됬을대는 어떻게되는지?
            
            // 다형성 예(3)
            Calculator add = new Calculator(new add());
            Calculator sub = new Calculator(new Subtract());
            
            //같은 인터페이스를 사용하기 때문에 같은 arguments로 사용이 될 수 있는것

            System.out.println(add.run(10,20));
            System.out.println(sub.run(10,20));
            
            //다형성 예(4) 
            Runner.run(new add(), 40,50);
            Runner.run(new Divide(), 6,2);
            
            // 여기 다음단계가 람다로 이어짐, 즉 4까지 잘이해해라
        }
    }
}
