package ch04.p02;
                    //== 사칙연산
public interface ArithmaticOperator { // 생성시 클래스말고 인터페이스로 생성가능
    
    int operate(int x, int y);
    
    long operate(long x, long y);
    
    double operate(double x, double y);
}
