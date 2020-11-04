package ch05.s04.p04;

class LocalInnerClass{

    int x = 1;

    public Object method(){
        final int y1 = 2;
        int y2 = 2;
        class LocalInner{

            // static final int z = 4; // static 일수가 없음 => 클래스영역에 생겨야함
            static final int z = 4; // Possible

            void methodA(){
                System.out.println(x);
                System.out.println(y1); //JDK1.7
                System.out.println(y2); //JDK1.8 //final아니라도 접근할수있게해줌
//                y2 ++; // 왜안되냐면 fianl로 취급을 함.. 접근만하고 수정은 불가능

            }
        }

        LocalInner inner = new LocalInner(); // Heap 영역에 객체가 생성이됨
        inner.methodA();

        return (Object)inner;
    }

    static{
        int y = 2;
        class LocalInner{
             void methodA(){
                System.out.println("a"); // x에접근 안됨
            }
        }
    }

    {
        int y = 2;
        class LocalInner{
           void methodA(){
                System.out.println(x);
            }
        }
    }

    public static void main(String[] args) {
        int y = 2;
        class LocalInner{
            void methodA(){
                System.out.println("a");
            }
        }

    }
}

public class Main {
}
