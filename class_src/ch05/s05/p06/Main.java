package ch05.s05.p06;

//표준 함수형 인터페이스

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.ObjIntConsumer;

public class Main {

    public static void main(String[] args) {
        Consumer<String> consumer = (s) -> System.out.println(s);//accept()메서드 구현
        //comsumer javadoc에 functional interface라고 적혀있음..   //<String>해주었기때문에 s의 타입 입력하지 않아도됨
        // accept(Object) 입력받은 값이 제네릭 타입에 들어가겠지

        consumer.accept("A String");// A String

        BiConsumer<String,String> biConsumer = (t,u) -> System.out.println(t+","+u);
        biConsumer.accept("StringA","StringB"); //StringA,StringB


        // 오토박싱/언박싱 사용 - 비효율적 => 그냥 int를 사용하고 싶을때 Integer를 사용해야 하니까
        Consumer<Integer> integerConsumer = (x) -> System.out.println(x);
        integerConsumer.accept(5); //5
        //Autoboxing되서 전달되고 하는것

        //그래서 나온게 기본형 Consumer => PConsumer(P:Primitive Type)을 사용 가능하다
        //객체가 아닌 값을 입력받을 수 있다.
        IntConsumer intConsumer = (x) -> System.out.println(x);
        intConsumer.accept(10); //10
        //LongConsumer, DoubleConsumer 도 존재 한다
        // 오버로딩이 아닌 별도의 인터페이스를 가지고 있는 old C Style

        ObjIntConsumer<String> objIntConsumer = (t, x) -> System.out.println(t + ":" + x);
        objIntConsumer.accept("x", 1023); // x:1023
        // T타입과 P타입을 하나씩 입력 받는 ObjType
        //ObjLongConsumer, ObjDoubleConsumer가 있음

        //본질은 입력받고 그게 어떤 기능을 한다. => 그입력을 다양하게 받을 수있는 여러 다른 Variation이 있다 정도..
        //그리고 뒤에서 배울것들과 어떻게 연관되서 사용하는지 이해하면 좋다


    }


}
