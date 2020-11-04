package ch03.s04.p02;

/**
 * 클래스의 포함 관계 Class Composition) // 컴포지션은 구성이란뜻, 클래스를 조립해서 클래스를 만들어냄
 * 상ㅎ속하고 유사하지만, 한 클래스가 다른 클래스의 객체를 포함하는 관계
 * 내부에 포함하고 있어 'HAS-A' 관계로 표현된다.
 */

// MainMachine 'HAS-A' String// String이 클래스기 때문에 우리가 클래스안에 String변수만들면 이미 컴포지션하고있던거임
class MainMachine {

    String model;
    boolean isBroken = false;

    public MainMachine(String model){
        this.model = model;
    }
}

// Developer 'HAS-A' MainMachine
// Developer 클래스는 MainMachine의 객체 하나를 보유한다.
class Developer {
    String name;
    MainMachine mainMachine;

    public Developer(String name, MainMachine machine){
        this.mainMachine = machine;
    }

    public void writeCode() {
        if (mainMachine.isBroken == true){
            System.out.println("코딩을 할 수 없습니다.");
        }
        else {
            System.out.println(mainMachine.model + "(으)로 코딩하기");
        }
           if (Math.random() >0.9){

            breakMachine();
               System.out.println("machine이 고장났습니다.");
        }
    }
    public void breakMachine() {
        mainMachine.isBroken = true;
    }
}

// Developer 'HAS-A' MainMachine
// Devleoper 클래스는 MAinMachine의 객체 하나를 보유한다.(Has)//MainMachine에 속성에도 접근가능
public class Main {
    public static void main(String[] args) {
        MainMachine mac = new MainMachine("MacBook Pro");
        Developer dev = new Developer("너개발", mac);

        for(int i = 0; i<10; i++){
            dev.writeCode();
        }



    }
}
