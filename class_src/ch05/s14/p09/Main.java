package ch05.s14.p09;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> list1 = new Vector<>(); // 옛날 구현인데 속도가느리다 Synchronize 되서 느리다 했던것 떠올려보자
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = Collections.synchronizedList(new ArrayList<>()); // Synchronized ArrayList로 바꿔는 애가 있다

        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    list1.add(1); // Vector의 add 는 Synchronized가 되어있다
                }
            }).start();
        }

        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    synchronized (list2){
                        list2.add(1); // ArrayList의 add는 그냥이다
                    }
                }
            }).start();
        }

        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                        list3.add(1); // ArrayList의 add는 그냥이다
                }
            }).start();
        }

        Thread.sleep(1000); // 다른 스레드들이 다돌기전에 main이 끝날수도 있기 때문에 main이 기달려주라는 의미로 sleep

        System.out.println(list1.size()); //100000
        System.out.println(list2.size()); //98440 // list2에도 synchronized 걸면 똑같이나온다
        // 동작속도는 list1 < list2
        System.out.println(list3.size()); //10000

    }
}
