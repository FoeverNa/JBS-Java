package ch04.s05;

public class Test02 {

}

abstract class Bar{
    public static String name;
    protected int x;
    int y;
    Foo foo;
    abstract void method(String string);
}

class Qoo{
    Foo foo;
}

class Foo{
    int x;
    void methodA(){};
}