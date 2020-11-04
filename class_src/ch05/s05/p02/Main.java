package ch05.s05.p02;


import java.util.Arrays;
import java.util.Comparator;


// 방법 1. Comparator 클래스를 만들고, 객체를 생성하여 전달
class MyComparator implements Comparator<String>{// Comparator는 compare라는 정렬기준을 지정해주는 메서드를 가지고있다

    @Override
    public int compare(String o1, String o2) { //첫번째 글짜는 때고 나머지 글자를 가지고 정렬을 하겟다

        return o1.substring(1).compareTo(o2.substring(1));
    }
}

//방법4

public class Main {
    public static void main(String[] args) {
        // 람다식이 사용되는 대표적인 예
        // 배열의 정렬

        String [] strings = {"fast", "campus", "java", "backend", "choigo","best", "people"};

        System.out.println(Arrays.toString(strings)); // 입력 한 순 정렬

        Arrays.sort(strings);
        System.out.println(Arrays.toString(strings)); //사전 순 정렬

        // 정렬 기준을 바꿀 수 있다
        // sort, 파라미터에 Comparator 객체를 넘겨준다

        //방법 1.
        Arrays.sort(strings, new MyComparator());
        System.out.println(Arrays.toString(strings));

        class MyComparator implements Comparator<String>{// Comparator는 compare라는 정렬기준을 지정해주는 메서드를 가지고있다

            @Override
            public int compare(String o1, String o2) { //첫번째 글짜는 때고 나머지 글자를 가지고 정렬을 하겟다

                return o1.substring(1).compareTo(o2.substring(1));
            }
        }

        // 방법 2. 익명 내부 클래스를 이용할 수 있음.

        Arrays.sort(strings, new Comparator<String>() {// 익명내부클래스, 상속하려는 곳에 인터페이스든
            // 추상클래스든 객체생성하면 익명클래스생성
            @Override
            public int compare(String o1, String o2) {
                return o1.substring(2).compareTo(o2.substring(2));

            }
        });
        System.out.println(Arrays.toString(strings));




        // 방법 3. 람다식을 이용하는 방식

               Arrays.sort(strings, (o1, o2) -> o1.substring(3).compareTo(o2.substring(3)));
        System.out.println(Arrays.toString(strings));


        // 동작을 했으면 하는 부분만 심플하게 작성할 수 있다
        //클래스를 구현하는게 귀찮으니까 익명 내부클래스를 사용할 수있다
        // 익명내부클래스도 귀찬으니가 람다를 사용할 수 있다. => 줄어드는 양상을 보자고

        //방법 4.

        Comparator<String> comp = (o1, o2) -> {
            return o1.substring(3).compareTo(o2.substring(3));
        };
        Arrays.sort(strings, comp);
        System.out.println(Arrays.toString(strings)+"44");



        // 방법 Hanso. Comparable을 이용하는 방법

        // Comparable//
        class Hansol implements Comparable<Hansol> {

            String value;

            public Hansol(String value){
                this.value = value;
            }

            @Override
            public int compareTo(Hansol o) {
                return value.substring(1).compareTo(o.value.substring(1));
            }
            @Override
            public String toString(){
                return value;
            }
        }

        Hansol [] hansols = {new Hansol("coampus"),new Hansol("fast"),new Hansol("java"),new Hansol("choigo")};
        Arrays.sort(hansols);
        System.out.println(Arrays.toString(hansols));


        // 방법 Hansol. IF-Stroy ~String이 상속이 가능했다면~
//        class Fansol extends String{
//            @Override
//            public int compareTo(String o){ 이것만 구현했으면됬다.. 근데 String이 final 이라 안됫다
//
//            }
//        }

    }

}


















