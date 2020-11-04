package ch05.s05.p02;


import java.util.Arrays;
import java.util.Comparator;

public class Practice {
    public static void main(String[] args) {

        String [] strings = {"fast", "campus", "java", "backend", "school"};
        System.out.println(Arrays.toString(strings));
        Arrays.sort(strings);
        System.out.println(Arrays.toString(strings)); //사전순정렬

        String s= "abc";
        String s1= "fed";

        System.out.println(s.compareTo(s1));


    class MyComparator implements Comparator<String>{

        @Override
        public int compare(String o1, String o2) {

            return o1.substring(1).compareTo(o2.substring(1));
        }
    }

        Arrays.sort(strings, new MyComparator());
        // 이거왜 객체를 그냥 넣으면 정렬기준이되지?
        // Compartor타입이 올수 있는데 myCompartor가 Comprator implements하고 있어서 가능
        System.out.println(Arrays.toString(strings));

        Arrays.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.substring(2).compareTo(o2.substring(2));
            }
        });
        System.out.println(Arrays.toString(strings));

        Arrays.sort(strings, ((o1, o2) -> o1.substring(3).compareTo(o2.substring(3))));

        System.out.println(Arrays.toString(strings));

        Comparator<String> com = ((o1, o2) -> -(o1.compareTo(o2)));
        // 바로 return한다면 {}생략하고 코드바로구현할수 있음
        // {}을 쓸거면 return을 생략할 수 없음
        // 전체 괄호는 언제생략하는거지?











    }




}
