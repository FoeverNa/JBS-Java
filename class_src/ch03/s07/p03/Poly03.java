package ch03.s07.p03;

/**
 * 공변 반환 타입 (Convariant return type)
 *
 */

class Foo {
    public Foo getInstance(){
        return this;
    }
}

class Bar extends Foo{
    @Override
    public Bar getInstance() {
        return this;
    }
    // public Foo getInstance(){ (Foo)getInstance()} 와 비슷하다...라는건가

    // 원래는 오버라이드할때 return타입도 같아야함, 그러나 이런경우는 예외적으로 인정을해줌
    // Foo에서 Foo를 return하나 Bar에서 Bar를 return 같은거라고 인식을 해준 거지지    // 오버로딩 아니고 오버 라이딩, 입력파라미터 같음
    // 공변반환타입 - 같이 변한다 CoVariant'
    // 리턴 타입은 클래스 밖에 들어갈게 없음 //
}

public class Poly03 {
}
