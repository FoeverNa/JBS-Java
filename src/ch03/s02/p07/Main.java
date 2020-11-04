package ch03.s02.p07;

/**
 * 초기화 블록 (Initializer)
 */
public class Main {
    static int classVar;
    static int instancecount;
    int instanceVar;


    // static initializer
    static {
        System.out.println("static block1");
        classVar = 20; // class variable에만 접근 가능, 객체생성 이전
    }

    // object initializer
    {
        System.out.println("block1");
        instanceVar = 30;// 객체 생성이후이기 때문에 instanceVar에 접근가능
        classVar = 50;// 추천되지 않음, 객체생성이 스태틱에 영양주기에
        instancecount++; //예외적으로 전체적으로 관리해주어야 할항목은 넣을 수 있음
    }

    static{ //2번째 staticinitialze는 첫번째 이후에 바로실행됨
        System.out.println("static block2");

        classVar = 5;
    }

    { // 2번째 object도 마찬가지.. 결국은 동일한 중괄호 안에 있는것과 다르지 않다.
        instanceVar =5;
        System.out.println("block2");

    }
        // 초기화 블록을 어디다쓴다고 했지?
}

class MainTest{
    public static void main(String[] args) {
        //static block 실행(만들어지면서 실행바로됨)
        System.out.println((Main.classVar));//20// static block에서 초기화됨
        //instance 생성시 initializer 실행
        Main main = new Main();
        System.out.println(Main.instancecount);
        System.out.println(main.instanceVar);//30 // object initialize에서 초기화됨
        System.out.println(Main.classVar);//20

        Main main2 = new Main();
        System.out.println(Main.instancecount); //block1
//                                                block2
//                                                 2

        Main main3 = new Main();
        System.out.println(Main.instancecount);//block1
//                                               block2
//                                                 3
    }
}
