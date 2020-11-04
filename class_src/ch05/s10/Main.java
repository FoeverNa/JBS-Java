package ch05.s10;


import java.util.*;

public class Main {
    public static void main(String[] args) {
        // List 인터페이스
        // - Collection 인터페이스 상속
        // - 순서가 있는 데이터의 집합, 데이터 중복 허용
        // - 데이터의 순서(index)가 유일한 데이터 구분자(identifier)로 사용
        List<String> stringList = new ArrayList<>(); // 가장 많이 쓰이는 배열 기반의 리스트(내부적으로 배열형태)
        List<String> stringList2 = new LinkedList<>(); // 노드의 연결로 구성된 리스트
        List<String> stringList3 = new Vector<>(); // ArrayList와 거의 똑같이 구현되어 있는데 멀티스레드 환경에서 안전하게 동작하게 되어있다
                                                   // 하지만 엄청나게 느리다는 단점이 있어서 잘 쓰이지 않는다
        List<String> stringList4 = new Stack<>(); // Stack 자료구조를 구현하고 있다 -> 자료구조 때 봅시다.

        //List 생성
        List<Integer> intList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            intList.add(i); // List의 맨 뒤에 자료를 추가한다. (자연스러운 동작)
        }                   // List는 맨 뒤에 자료 추가(순차처리)가 가장 빠르다
        System.out.println(intList); // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
        System.out.println(intList.size()); // 10

        //List의 값추가
        intList.add(2,678493); // [0, 1, 678493, 2, 3, 4, 5, 6, 7, 8, 9]
        System.out.println(intList.size()); // 11 => size가 하나 늘어난 것을 확인 할 수 있다.
        System.out.println(intList);// 해당 인덱스에 숫자를 한칸 뒤로 밀고 그자리에 값을 끼워 넣는방식 (배열과는 다른 방식) => 이해필요
                                    // 배열의 경우 인덱스를 입력하여 넣는다면 그냥 그자리에 값을 덮어씌우는 방식인데 list는 특별한 방식이다.


        // 입력된 Colletion 내용 전체를 한번에 add하는 메소드
        // index를 입력받아 위치도 지정 가능

        // 새로운 Colletion 생성
        List<Integer> intList2 = new LinkedList<>();
        for (int i = 5; i < 10; i++) {
            intList2.add(i);
        }
        System.out.println(intList2); // [5, 6, 7, 8, 9]

        intList.addAll(1, intList2); // .addAll() // Colletion에 있는 내용을 통째로 넣는 것
        System.out.println(intList); // [0, 5, 6, 7, 8, 9, 1, 678493, 2, 3, 4, 5, 6, 7, 8, 9]

        // get() 요소 가져오기
        System.out.println("ind 3: " + intList.get(3)); // ind 3: 7

        // indexOf() 요소의 인덱스 찾기
        System.out.println(intList.indexOf(9)); // 5 // 객체를 찾아 가장 첫번째 인덱스를 반환
        System.out.println(intList.lastIndexOf(9)); // 15 // 객체를 찾아 가장 마지막 인덱스를 반환

        // remove() 요소 제거와 반환
        // 요소가 제거되면, 그 뒤 요소들은 모두 index가 하나씩 앞으로 당겨짐
            //  List는 순서가 있기 때문에 중간이 절대로 비어있지 않는다
        System.out.println("index 9 was: " + intList.remove(9)); // 9번째 인덱스에 있는 요소를 List 에서 제거하고 return 한다
        System.out.println(intList); // [0, 5, 6, 7, 8, 9, 1, 678493, 2, 4, 5, 6, 7, 8, 9]

        // set(index, value) 배열처럼 index 의 값을 value 로 대체한다
        intList.set(1, 100);
        System.out.println(intList);

        // 인덱스 범위만큼 잘라서 새로운 List반환
                                       // inclusive, exclusive
        List<Integer> list3 = intList.subList(2, 5); // 2~4번째 요소들을 잘라서 새로운 List로 만든다
        System.out.println(list3); // [6, 7, 8]

        // List iteration

        // fori를 이용한 접근 // 간단하고 합리
        for (int i = 0; i < list3.size(); i++) {
            System.out.println(list3.get(i));
        }

        // foreach를 이용한 접근 // 간단하고 불합리
        //listIterator를 이용한 접근을 쉽게 써놓은 것
        for (int value : list3){
            System.out.println(value);
        }

        // listIterator를 이용한 접근 - foreach문은 사실 이것을 짧게 쓴 것 // 복잡하고 합리
        ListIterator<Integer> iter = list3.listIterator();
        while(iter.hasNext()) { // 다음에 가져올게 있는지 검사하는 메소드 // true 일 때만 다음 요소가 있다
            Integer integer = iter.next(); // 다음요소를 반환하는 메소
            System.out.println(integer);
        }
        // 스트림처럼 이터레이터도 값을 담는아이래
        // 이터레이터는 값을 복사는 하지 않고 가져오는 역할만 한다..
        // 스트림은 원본을 보존한다


        // Set 인터페이스
        // - Collection 인터페이스 상속
        // - 순서가 없는 데이터 {집합} (그야말로 집합) 을 다루는 인터페이스
        // - 중복되는 데이터를 효율적으로 제거하는데 사용 가능
        // - 중복을 검사하는 수단 1. hashCode(), 2. equal() // equal이 같다면 hashcode가 같아야된다는 제약조건이 여기서 사용된다
        // - hash 를 빠르게 계산해서 hash 만 비교한 이후 그 다음에 판전이 안나면 equals()로 추가 비교하게 된다

        Set<String> stringSet1 = new HashSet<>(); // 기본적인 집합

        //Set의 메서드는 List의 것 보다는 적다
        stringSet1.add("A");
        stringSet1.add("B");
        stringSet1.add("B");
        stringSet1.add("F");
        System.out.println(stringSet1); // [A, B, F] => 중복값이 알아서 제거된다
                                        // List 의 경우 중복되는 값 제거하기 위해서는 이중 for 문을 이용해서 모두 비교해야된다! O(n^2)
                                        // List -> Set으로 한번씩만 옮기면 중복이 제거됨
                                        // 1중 for문을 이용해서 비교할 필요 없이 알아서 삭제됨  O(n)
                                        // => 많이 사용되는 기능이니 기억해두자

        // NavigableSet - TreeSet
        NavigableSet<String> stringSet2 = new TreeSet<>(); //TreeSet은 set의 자식클래스인 NavigableSet이다.(메서드를 온전히사용가능하다)
                                                           // 이진 탐색 트리 => 집합에 정렬기능이 추가된 것
                                                          // String은 Comparable하기 때문에 따로 구현이 필요 없다

        // 이진 탐색 트리 Binary Search Tree(BST)
        // 처음 데이터를 루트노드로 두고 더작으면 왼쪽 더크면 오른쪽으로 계속해서 입력해 나가는 구조.
        // 그 자리에 노드가 있다면 그자리에 있는 수와 비교 하여 왼쪽 오른쪽으로 구분한다
        // 자료를 빠르게 찾는데 도움이 된다. (기준 그대로 찾아가게된다)
        // 자료자체가 Comparable하던지 아니면 Comparator를 구현해서 넣어주던지 해서 자료들의 정렬기준이 필요하다

        class Foo{
            int x,y;

            public Foo(int x, int y) {
                this.x = x;
                this.y = y;
            }

            @Override
            public String toString() {
                return x + ", " + y;
            }
        }

        NavigableSet<Foo> set = new TreeSet<>((Comparator.comparingInt(o -> o.x))); //구현내용 다시보기 // 포인트는 Comprable 구현해주어야 한다는것
        set.add(new Foo(1, 100));
        set.add(new Foo(4, 50));
        set.add(new Foo(0, 170));
        set.add(new Foo(-2, 3300));

        // 무슨 메서드
        System.out.println(set.first()); // -2, 3300
        System.out.println(set.last()); // 4, 50
        System.out.println(set.lower(new Foo(1, 500) )); // 0, 170 // 작아야되서 더작은게 출력
        System.out.println(set.floor(new Foo(1, 500) )); // 1, 100 // 같아도되서 같은게 출력
        System.out.println(set.higher(new Foo(2, 500) )); // 4, 50 // 더 큰것 이기 때문에 더큰게
        System.out.println(set.ceiling(new Foo(1, 500) )); // 1, 100 // 같아도 되서 같은게 출력? => 여기는 더확인해야 함
        System.out.println();
        //자료를 찾는것을 짧게해주는 훌륭한 친구래

        // poll() -> Set 에서 첫번째 값을 빼면서 리턴 한다
        // Priotity Queue에 기능을 구현하는 것이라는데 잘모르겠다
        System.out.println(set.pollFirst()); // -2, 3300
        System.out.println(set.pollFirst()); // 0, 170
        System.out.println(set.pollFirst()); // 1, 100
        System.out.println(set.pollFirst()); // 4, 50
        System.out.println(set.pollFirst()); // null



        // Map 인터페이스
        // - Collection 인터페이스 상속x
        // - Key, Value 쌍으로 구성된 자료의 집합을 다루는 인터페이스
        //    - Key, Value 쌍은 내부적으로 Map.Entry<K, V> 제네릭 클래스로 구성되어 있다.
        // - Key 는 중복될 수 없으며, Vlaue 는 중복이 가능
        //    - ke y가 identifier 역할을 한다

        // Map 종류
        Map<String, Integer> map = new HashMap<>(); // 가장 대표적으로 사용되는 Map // 구성원리는 hashSet과 거의 비슷하다
//        NavigableMap<String, Integer> map2 = new TreeMap<>();
        //Set 과 Map 은 구현이 거의 비슷하기 때문에 구조도 거의 비슷하다
        ////////////////////////////////// 설명 다시한번 들어보기

        // Map 종류 번외
        Map<String, Integer> map3 = new Hashtable<>(); // Vector처럼 옛날 구현 syrncronize구현 // 사용하지 않는다
        Properties prop = new Properties(); // Hashtable<String, String>을 상속하는 클래스
        prop = System.getProperties(); // System의 property가 이 형식으로 제공된다는 것 참고하자


        // Map 메소드
        System.out.println();

        // put()은 기존에 동일 key가 있었으면 기존 value를 반환, 없었으면 null을 반환
        // 업데이트를 했으면 업데이트한 값을 알 수 있고, 없었으면 없었다고 알려준다 => 유용하다
        System.out.println(map.put("ABCDE",5)); // null
        System.out.println(map.put("CDEF",1023)); // null
        System.out.println(map.put("ABCDE",1023)); // 5

        // get()
        System.out.println(map.get("CDEF")); // 1023 // query => 검색해서 받아오는 과정을 query(질의) 라고 한다
        System.out.println(map.getOrDefault("CDEF", 0)); // 1023

        // getOrDefault()
        System.out.println(map.getOrDefault("ZZZZ", 0)); // 0 // 가져올 key가 없으면 default 값을 반환
        // 이렇게 기존 값이 없을 때 0등으로 기본 값을 설정하고 싶으면 편리하게 사용할 수 있다
        // 나중에 알고리즘 할때 많이 사용할 수 있는 꿀팁이다
        map.put("ABCDE", map.getOrDefault("ABCDE", 0) +1);

        //map의 값 받아 보기
        // keySet 사용하기
        for (String key: map.keySet()) {
            System.out.println(key + ":" + map.get(key));
        }
        //ABCDE:1024
        //CDEF:1023

        // Map.Entry 사용하기
        // 코드는 길지만 이게 더 빠른 방법 => 이것 사용하는 것을 추천
        for (Map.Entry<String, Integer> entry: map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        //ABCDE:1024
        //CDEF:1023s
        System.out.println();

        // TreeMap
        NavigableMap<String, Integer> map2 = new TreeMap<>(); // 애도 Comparable 구현해줘야 하겠지 Tree니까
        map2.put("a", 10);
        map2.put("g", 12);
        map2.put("z", 14);
        map2.put("z", 114);
        map2.put("c", 165);

        System.out.println(map2); // {a=10, c=165, g=12, z=114} // 겹치는 z는 나중에 넣은걸로 update


        System.out.println(map2.ceilingKey("b")); // c // 키로 접근하는 방법
        System.out.println(map2.ceilingEntry("b").getValue()); // 165 // Entry로 접근해서 value에 접근하는 바업ㅂ
        System.out.println(map2.pollFirstEntry().getValue()); // 10
        System.out.println(map2.pollFirstEntry().getValue()); // 165
        System.out.println(map2.pollFirstEntry().getValue()); // 12
        System.out.println(map2.pollFirstEntry().getValue()); // 114

        System.out.println(map2); // {} // 비었다

    }
}
