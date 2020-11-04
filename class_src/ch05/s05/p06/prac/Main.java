package ch05.s05.p06.prac;

import java.util.function.*;

public class Main {
    public static void main(String[] args) {
        Consumer<String> consumer = s -> System.out.println(s);
        consumer.accept("String");

        BiConsumer<String, Integer> biConsumer = (s,i) -> System.out.println(s + i);
        biConsumer.accept("String", 58);

        Consumer<Byte> byteConsumer = b -> System.out.println(b);
        byteConsumer.accept((byte) 1);

        IntConsumer intConsumer = x -> System.out.println(x);;
        intConsumer.accept(313);

        ObjDoubleConsumer<String> objDoubleConsumer = (s,x) -> System.out.println(s+" :"+x);
        objDoubleConsumer.accept("PI",3.14);


        Supplier<String> supplier = () -> "졸려";

        System.out.println(supplier.get());

        // biSuppier는 없다

        BooleanSupplier booleanSupplier = () -> true;
        System.out.println(booleanSupplier.getAsBoolean());

        IntSupplier rollDice = () -> ((int)(Math.random()*6)+1);

        for (int i = 0; i < 10; i++) {
            System.out.println(rollDice.getAsInt());
        }

        int x = 40;

        IntSupplier intSupp = () -> x;

        System.out.println(intSupp.getAsInt());


    }
}
