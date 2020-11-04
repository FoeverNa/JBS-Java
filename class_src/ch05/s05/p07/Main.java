package ch05.s05.p07;

import java.util.function.BooleanSupplier;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {
        Supplier<String> supplier = () -> "A String";
        System.out.println(supplier.get());//A String

        //BiSupplier는 불가능,, return은 하나만 가능하기 때문에

        // Supplier는 P Type 계열에서 getAsP 메소드로 정의// 그냥 get()이 아님
        BooleanSupplier boolSup = () -> true; // true,false
        System.out.println(boolSup.getAsBoolean());//true// get이 아닌 getAsBoolean();

        //IntSupplier, LongSupplier, DoubleSupplier

        //주사위 예제
        IntSupplier rollDice = () -> (int)(Math.random() *6);

        for (int i = 0; i < 10; i++) {
            System.out.println(rollDice.getAsInt());

        }

        //예제2, fix되있는 동작이 아니더라도 동적으로 동작할 수 있다.. = > 그래서 의미가 있다
        // 이부분은 좀더 생각해봐야겟다
        int x = 4;
        IntSupplier intSupp = () -> x;
        System.out.println(intSupp.getAsInt());
        // 로컬변수와 지금 컨텍스트에서 접촉할수 있는 모든 변수를 활용 가능함

    }
}
