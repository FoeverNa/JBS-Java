package ch03.s02.p06;

/**
 * Getter, Setter
 * 멤버 변수를 간접적으로 다룰 수 있게 해 주는 메소드
 * 멤버 변수의 캡슐화(Encapsulation)에 도움이 됨
 *           -> 정보의 은닉/보호
 * 멤버 변수의 값을 제한 해야 할 때 유용
 */

public class GetterSetter {
    int x,y;
    String z;
    float w; // code - generate - GetterSetter

    //constructor도 Generate를 통해 만들 수 있다.
    //constructor 만들면 기본 생성자가 사라짐.
    //만약 둘다 필요하다면 기본생성자를 작성해주면됨
    public GetterSetter(){};
    public GetterSetter(int x, int y, String z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if(x>0 && x <= 1000){
            this.x=x;
        }else{
            System.out.println("x should be 1<= x <=1000");
            System.out.println("however, you put in x =" +x);
        }

    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getZ() {
        return z;
    }

    public void setZ(String z) {
        this.z = z;
    }

    public float getW() {
        return w;
    }

    public void setW(float w) {
        this.w = w;
    }
}

class GetterSetterTest {
    public static void main(String[] args) {
        GetterSetter g = new GetterSetter();

       g. x= 10;
        System.out.println(g.x);

        g.setX(20);
        System.out.println(g.getX());  // 왜이렇게 귀찮게 해?
                                   // 속성에 대한 접근을 개발자가 설정할 수 있음


        g.x=20; // 권장되지 않은 접근

        g.setX(1111111111);
        System.out.println(g.getX());
    }
}