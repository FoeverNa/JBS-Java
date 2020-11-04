package ch04.s04;

/**
 * 제너릭 (Generics)
 * - 대상 객체의 타입을 입력받아서 사용하는 형식
 * - 미리 사용할 수 있는 타입을 명시해서 컴파일 타임에 체크 가능
 *  - 입력을 Object로 하면 할 수 도 있으나, 런타임에 instanceoff로 객체를 체크해야 함
 *      -내가 원하는 객체가들어왔나 확인화는과정
 *  - 제네릭을 사용할 경우 이러한 과정 없이 간결하게 코드 작성을 할 수 있다.
 *      -제네릭 장점
 */

// 제네릭 클래스

// 일반 클래스
class Foo{

}

//재너릭 클래스
// 클래스를 선언할때에는 설정되지 않은 타입이 있으며, 이것을 타입 파라미터로 표현한다
class GenericFoo<T> { // T: 타입 파라미터
    String name;
    T memberVar;

    public GenericFoo(String name, T memberVar) { // 어떤타입이 들어올지 모름
        this.name = name;
        this.memberVar = memberVar;
    } // 생성자에는 <T> 생략

    //T를 자료형 처럼 멤버변수와 메소드에 사요할 수 있음

}

interface GenericInterface<T>{ //인터페이스도 제네릭이 가능

}

class HashMap<K,V>{ // 여러개의 타입 파라미터도 쓸 수 있다.

}

class GenericBar<T>{

    // 문법적으로 문제가 있는경우
    // static T classVar; // not possible
    // 객체 생성시 자료형을 넣는데 객체를 생성안하고 사용하는 static에 사용 불가

//    static void method(T var){
//        return var;
//    } // not possible too or two

    // 스태틱 메서드도 마찬가지로 클래스에 속해 있기 때문에 당연히 안됨.

    // 문법적으로 문제가 없을 것 같으나, 안정성 문제로 금지


//     T memberVar = new T(); //not possible
    // new키워드를 사용할 수는 없음 => 안정성 문제 때문에 불가능
    // 타입파라미터에 객체를 생성을 하는건 불가능 하다
    // T 가 알려져 있는 상황이다.

//    {
//        Object obj = new Object();
//        if(obj instanceof T){ // not possible
//
//        }
//    }
    //T에속하는 객체인지 확인하는 것도 안정성 문제로 안됨
    // 안정성 문제 - T가 생성자가 어떤것들이 구현되있을지 모르는 상황, 생성자를 콜하는것은 위험해
    // 자바에서는 막혀있음, 그냥막혀있음 이런식에 구현은 하지 말라는 권고
    // 런타임때 T를 입력받으면 구현상 위험한 행위로 봄, 굉장히 위험하다
    // 이건 받아들이는 부분 => 구현되게할수도있지만 java에서는 막아둠

}

// 제네릭 타입의 상속
class GFoo<T> {

}

interface IGFoo<T>{

}

// 문법 상 유의사항
// 타입 파라미터는 부모 제네릭의 타입 파라미터를 모두 채워 주어야 한다.
// 추가적인 타입 파라미터도 사용할 수 있다.
// 2개의 다른 타라미터가 이름이 같아도 T,D로 구분해서 받아주면된다..

class GIGFoo<K, T, D> extends GFoo<T> implements IGFoo<D>{
    //GIGFOO 도 제너릭 클래스이고 제너릭클래스를 상속하는데 뒤에 제너릭 클래스와 제너릭 인터페이스를 상속하는것
    // 그럼 제너릭 클래스가 아니면?

}

// 부모 클래스/ 인터페이스들에 동일한 타입 파라미터를 넘겨줄 수 있다.
class IGIFooTwo<T> extends GFoo<T> implements IGFoo<T>{

}

// 타입 제한을 하지 않으면 extends Object와 동일하다 // 묵시적으로 extends Object
class GenericNoTypeLimit<T extends Object>{}

// extneds를 이용해서 부모 클래스와 인터페이스도 제한할 수 있다.
class GenericTypeLimitation<T extends Number&Cloneable>{//Number는 추상클래스를 상속하고 있는애들만 사용할 수 있음
                                                      //요 추상클래스도 상속하고 있으며 요인터페이스도 구현해야한다
    T memberVar ;
//    GenericTypeLimitation<> genericTypeLimitation = new GenericTypeLimitation<>();
}

//제너릭 메소드
class GenericMethod{
    public static <T> T staticMethod(T t){
        return t;
    }

    // 정적메소드는 사용가능하다
    // 제너릭 클래스에서 타입파라미터와 제네릭메서드의 파라미터는 별개다
    // 제너릭 클래스에서는 안됬자나... 이게 머가다른지 인식해야함
    // 제너릭 클래스에서 사용한 타입파라미터는 스태틱메서드에서 사용이 안됨
    // 하지만 제너릭 메서드에서 사용한 타입파라미터는 제너릭 클래스의 파라미터가 아니고 메서드 자체에 타입파라미터이다.
    // 클래스 생성때 스태틱클래스가 생성되는게아니고(생성만되고) 런타임때 스태틱클래스가 생성되는 것임.\
    // T에 인트나롱 같은 프리미티브타입은 들어갈수가 없음.. = > 메인에서 설명해줌

//    public int method(int x){
//        return x;
//    }

    public <T> T method(T x){
        return x;
    }
    //접근제어자 뒤에 타입파라미터 작성해서 반환과 파라미터 다 활용할 수 있다.

}

//와일드 카드
class WildFoo{

}

class WildBar extends WildFoo{

}

class WildGeneric<T>{


}

// 와일드카드?는 메소드의 입력 타입에 제네릭이 쓰일 경우
// 제네릭의 타입 변수를 결정하지 않거나 제한할 수 있다.

class WildCard{
    public void method1(WildGeneric<?> x){}// WildGeneric<String> x 명시할수도 있음
                                            // ? 을 통해 아무 타입이나 들어올수 있게해줌
    public void method1_eq(WildGeneric<? extends Object> x){} // Object가 상한 -> 모든 클래스 사용가능
    // objext를 조상으로 두고있는애 즉 모든 클래스를 사용할 수 있다.
    public void method2(WildGeneric<? extends WildFoo>  x){} // 그것을 제한하는 방법 // WildFoo, WildBar
    // WildFoo를 조상으로 두고 있는애 , WildFoo를 포함한다
    public void method3(WildGeneric<? super WildBar>  x){} // Object, WildFoo, WildFoo, WildBar
    // Super는 반대 WildBar를 자식으로하는 이상의것들
    public void method4(WildGeneric<String> x){}

}

// 제네릭의 문법은 많지만 이해보다는 제네릭으로 이루어진것을 사용하는 방법을 아는데 중점을 두어야한다


public class Generics {
    public static void main(String[] args) {
        GenericFoo<String> foo = new GenericFoo<String>("name","var");
        // 클래스명에 <>을 붙여서 자료형을 넣어준다. T => String으로 쓰겠다

        GenericFoo<String> foo1 = new GenericFoo<>("name","var");
        // 뒤에 클래스명에 <>는 자료형이 생략가능하다, 위에도 회색으로 뜨자나

        //GenericFoo<int> foo3 = new GenericFoo<int>("name",22);
        // 안되는 예시가 뭐였찌 물어봐야됨 => int는안됨
        // 프미티브 타입파라미터는 재너릭클래스도 안되고 재너릭 메서드도안됨 둘다안됨 걍안됨


        System.out.println(foo.name);// name
        System.out.println(foo.memberVar);// var

        GenericFoo<Integer> foo2 = new GenericFoo<>("name1",4);
                //int외에도 내가 만든클래스 아무거나할 수 있음
        System.out.println(foo2.name);// name1
        System.out.println(foo2.memberVar);// 4

        //이것때문에 주의해야할 문법을 이어서 작성하세요


        //제너릭 메서드
        // 동적 바인딩 - > 컴파일 타임에는 동작이 완전히 정의가 되지 않음
        // 런타임에 결정이 된다.
        // 컴파일을 하면 바이트코드에 동작들이 정의가 되어있어야함 => 정적바인딩
        // 제너릭메서드는 자료형이 안정해져있기때문에 동작이 정의가 되어있지 않음
        // 그래서 런타임때 결정이되어 그때컴파일이 일어나는것이 동적 바인딩.

        GenericMethod.staticMethod(new String("abcdeeeee")); // 오버로딩된것처럼 해당 값들어오면 자료형이 결정됨
        GenericMethod.staticMethod(12341); // new 안해도되고 입력만 하면 자동으로 타입을 설정함
        // 이때는 묵시적이라 int입력이 가능했데..무슨말일까 => 설명해주신데

        GenericMethod genericMethod = new GenericMethod();
        System.out.println(genericMethod.method(23));
        System.out.println(Integer.valueOf(23));


//        WildCard wildCard = new WildCard();
//        WildGeneric<String> wildGeneric = new WildGeneric<String>();
//        wildCard.method1(wildGeneric); //<String>이기에 가능
//        wildCard.method1(wildGeneric); //<?>이기에 가능
//
//








    }
}
