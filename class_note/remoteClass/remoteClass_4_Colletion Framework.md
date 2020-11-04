# 자바 컬렉션 프레임워크 (Java Collections Framework; JCF)

## 컬렉션 프레임워크란

- java.utils에 속한 일련의 클래스로, 자료구조를 담당
- 잘 짜여진 인터페이스를 기반으로 다양한 자료구조를 구현
- 제네릭 클래스로 되어 있어, 다양한 객체를 요소로 담을 수 있다.
  - 자료의 자료형은 정해져있지 않고 사용자가 선택할 수 있게 해주었다

## JCF 인터페이스

컬렉션에서 프레임워크에서 가장 중요한 3대 인터페이스

- List 인터페이스(ADT)
  - Collection 인터페이스를 상속
  - 순서가 있는 데이터의 집합으로, 데이터의 중복을 허용
    - 순서로 정의가 되기 때문에 순서가 다르면 다른데이터로 취급 가능
  - 구현체: ArrayList, LinkedList(, Vector, Stack)
    - 배열을 이용한 ArryaList
    - 링크로 연결된 LinkedList
    - Vector,Stack 이런게 있기는 하다고 알고만있으면됨 잘사용 안되서
- Set 인터페이스(집합으로 이해하면됨)
  - Collection 인터페이스를 상속
  - 순서가 없는 데이터의 집합으로, 데이터의 중복을 허용하지 않음
    - 중복 입력시 문제가 되는 건 아니고 이미 있는 데이터기 때문에 무시된다
    - 그런것을 오히려 활용해서 중복되는 객체만 남길 수 있게 하는 방식으로 사용하기도 한다
  - 구현체: HashSet, TreeSet
- Map 인터페이스(Colletion 인터페이스와 별도) 
  - Key-Value 쌍으로 이루어진 데이터의 집합
    - key는 쿼리(검색)를 할수있는대상이된다. 그래서 key를 통해 value를 검색할 수 있게 된다
  - 구현체: HashMap, TreeMap, HashTable, Properties
    - HashMap 을 많이 사용한다



### Collection 인터페이스

- Collection 인터페이스의 주요 메소드

  | 메소드                                      | 설명                                                         |
  | ------------------------------------------- | ------------------------------------------------------------ |
  | `boolean add(E e)`                          | 요소 e를 컬렉션에 추가한다.(상속받는 클래스에 따라 add하는 방법이 구체적이됨) |
  | `boolean addAll(Collection<? extends E> c)` | 다른 컬렉션 c의 모든 요소를 컬렉션에 추가한다.(와일드카드는 E e랑 동일한 기능한다.. ) |
  | `void clear()`                              | 컬렉션의 모든 요소를 제거한다.                               |
  | `boolean contains(Object o)`                | 컬렉션에 요소 o가 존재하는지 확인한다.                       |
  | `boolean containsAll(Collection<?> c)`      | 컬렉션 c의 모든 요소가 컬렉션에 포함되는지 확인한다.         |
  | `boolean equals(Colletion(?)일거래)`        | 컬렉션 o와 같은 내용을 포함하는지 비교한다.                  |
  | `boolean isEmpty()`                         | 컬렉션이 비어있는지 확인한다.                                |
  | `Iterator<E> iterator()`                    | 컬렉션의 Iterator를 반환한다.(자료 하나하나에 접근하게 해주는 메서드) |
  | `boolean remove(Object o)`                  | 컬렉션에서 요소 o를 제거한다.(요소 = Element = 콜렉션안에 하나하에 데이터를 부르는말) |
  | `boolean removeAll(Collection<?> c)`        | 컬렉션 c에 속한 모든 요소를 컬렉션에서 제거한다.             |
  | `boolean retainAll(Collection<?> c)`        | 컬렉션 c에 포함된 객체만 남기고 나머지 요소를 제거한다.(기능만알아두면 이름은 찾아서쓰면 됨) |
  | `int size()`                                | 컬렉션에 속한 요소의 개수를 반환한다.                        |
  | `T[] toArray(T[] a)`                        | 컬렉션의 요소들을 `T[]` 배열로 반환한다.(iterator로 받는게 더 표준적인 방법이긴 하다) |

- Iterator

  - `iterator()`로 반환되는 객체로, Collection에 저장된 요소에 접근하기 위해 사용

    | 메소드              | 설명                       |
    | ------------------- | -------------------------- |
    | `boolean hasNext()` | 다음 요소가 있는지 확인    |
    | `E next()`          | 다음 요소가 있을 경우 반환 |
    | `void remove()`     | 현재 위치의 요소를 삭제    |

  - Iterator의 활용

    ```java
    Iterator<String> iter = collection.iterator(); //String 이 담겨있다고 가정
    while (iter.hasNext()) {//hasNext가 true => 가져올 값이 있다
        String string = iter.next();// 다음값을 가져온다
        System.out.println(string);
    }
    ```

    ```java
    for (String string: collection) {// 위와 동일한 형식, 위의 원리로 이렇게 for문사용하며됨
        System.out.println(string);
    }
    ```

### List 인터페이스

- 순서가 있는 데이터의 집합을 다루는 인터페이스

  - 인덱스가 0부터 끝까지 다있어야함
    - 그래서 중간에 하나를 제거하면 나머지를 당겨와서 순서에 맞춰줌(List에 내용생각해봐)

- List는 인덱스(데이터가 몇번째있는지) 를 이용해 요소를 구분할 수 있어, 데이터의 중복을 허용

  - 객체의 경우 동일한 객체를 가르켜도 문제가 없다라는 뜻

- Collection 인터페이스를 상속받았으며, 추가 메소드가 구현되어 있다.

  - 위의 메소드 모두 사용할 수 있고 추가적인 메서드가 있다

  | 메소드                                                 | 설명                                                         |
  | ------------------------------------------------------ | ------------------------------------------------------------ |
  | `void add(int index, E element)`                       | index 위치에 요소 element를 삽입한다.                        |
  | `boolean addAll(int index, Collection<? extends E> c)` | index위치부터 컬렉션 c의 모든 요소를 추가한다.(그뒤에 있던것은 그만큼 뒤로 밀려나겠지) |
  | `E get(int index)`                                     | index 위치에 저장된 요소를 반환한다.(Colletion에는 쿼리할 수 있는 방법이 없음) |
  | `int indexOf(Object o)`                                | 객체 o가 저장된 첫번째 인덱스를 반환한다. 없을 경우 -1을 반환한다.(중복이 가능하기때문) |
  | `int lastIndexOf(Object o)`                            | 객체 o가 저장된 마지막 인덱스를 반환한다. 없을 경우 -1을 반환한다. |
  | `ListIterator<E> listIterator()`                       | ListIterator 객체를 반환한다.(Iterator를 상속하는 ListItrator) |
  | `E remove(int index)`                                  | index에 위치한 객체를 제거하고 반환한다.(꺼내서 쓴다는 느낌,그냥 제거할때 사용하면된다) |
  | `E set(int index, E element)`                          | index에 위치한 요소를 element로 대체한다.(index를 유지하고 data만 바꾸면됨) |
  | `List<E> subList(int fromIndex, int toIndex)`          | fromIndex에 위치한 요소부터 toIndex의 직전에 위치한 요소까지 포함한 List를 반환한다.(from은 포함하고 to는 포함하지 않는다) => 포함하는지안하는지는 항상 생각해보는게 좋음 |

  오버헤드 = 중간에 넣거나 뺄때 자료를 앞뒤로 움직여주는것?

  오버헤드 ; 특정 동작을 위해 같이 부수적으로 발생하는 추가적 계산, 추가적 메모리사용을 통칭 , 이경우에는 위에 설명이 맞겟다

  

- List 인터페이스의 구현체

  ```java
  List<String> list = new ArrayList<>();
  list = new LinkedList<>(); // 동일 인터페이스로 다른 구현체를 사용 가능
  ```

  List로 선언한 이유는 잘이해 못했네

  

  - `ArrayList<E>`

    - 제네릭 클래스로 구현된 자료구조

      - 타입파라미터로 입력을 받음

    - 배열을 기반으로 구현된 클래스로, 가장 자주 활용되며 활용도가 높다.(Array보다 자주 사용된다)

      - 우리과제로 해봤던것 떠올리면됨

    - ArrayList의 생성자는 세 종류가 주어진다.

      ```java
      public ArrayList()//0으로 시작된다? 10이 아니고?
      public Arraylist(int initialCapacity)// 크기입력
      public ArrayList(Collection<? extends E>)// 이부분은 계속 이해가 안되네 와일드카드다시바야겟다
      ```

      

  - `LinkedList<E>`

    - 제네릭 클래스로 구현된 자료구조

    - 연결리스트를 기반으로 구현된 클래스로, 배열의 단점을 극복하기 위한 구현체

      - 데이터가 노드라는 형태로 구현되어 있고 그 노드를 전후로 서로 참조하면서 연결되어 있는 형태
      - 배열의 단점은 데이터넣거내 뺄때 주변에 데이터를 댕기고 밀어줘야되는 것
        - 순차 추가/삭제은 바로 가능하지만 비순차 추가/삭제는 느리다는 단점이 있다
      - 링크드는 이걸 빠르게 할 수 있는 장점이 있다

    - ArrayList vs. LinkedList

      | 구현체       | 순차 추가/수정/삭제                            | 비순차 추가/수정/삭제 | 조회                              |
      | ------------ | :--------------------------------------------- | --------------------- | --------------------------------- |
      | `ArrayList`  | 빠름                                           | 느림                  | 빠름                              |
      | `LinkedList` | 느림(순차는 오히려 느리네, 그렇게 느리진 않데) | 빠름                  | 느림(기본적으로 처음과 끝만 조회) |

  - `ArrayList`, `LinkedList`

    - Object를 요소로 가지는 List 구현체 (Java5 이전)=> 안쓰니까 그냥있다는것만 알아두샘

- List의 정렬

  - Collections 클래스의 sort()메소드 사용
    - public static <T extends Comparable<? super T> void sort(List<T> list)
      - 객체에 선언된 `public int compareTo(T t)` 메소드로 비교하여 정렬
    - public static <T> void sort(List<T> list, Comparator<? super T> c)
      - Comparator 객체 c에 정의된 `public int compare(T o1, T o2)` 메소드로 비교하여 정렬

- ListIterator

  - `listIterator()`로 반환되는 객체로, 양방향 사용이 가능 => List는 순서가 있기 때문에 그렇다.
  - Iterator를 상속받은 클래스
    - Iterator 의 메서드 사용가능

  | 메소드                  | 설명                                                         |
  | ----------------------- | ------------------------------------------------------------ |
  | `boolean hasPrevious()` | 이전 요소가 있는지 확인(순서가 있기 때문에 가능하다, 이것을 양바향으로 사용한다고 얘기하는거네) |
  | `E previous()`          | 이전 요소가 있을 경우 반환                                   |

### Set 인터페이스

- 순서가 없는 데이터의 집합을 다루는 인터페이스

- 중복되는 데이터를 효율적으로 제거하는 데에 쓸 수 있음

  - 중복 데이터를 인정안하기 때문에 set으로 묶었다가 풀어만 줘도 중복 데이터가 사라진다

- Collection 인터페이스를 상속받아 메소드를 구현하고 있음

- Set의 구현체

  - `HashSet<E>`

    - Set의 대표적인 구현체로, 기본적인 기능이 구현되어 있다.
    - Hash값을 이용하여 구분하여 빠르게 동작하는 자료구조

  - `TreeSet<E>`

    - `NavigableSet<E>` 인터페이스를 구현하며, 이진 탐색 트리 자료구조이다.

    - 조금 다른아이, 나중에더 잘알아보자 

    - 객체를 삽입하는 동시에 정렬되는 특성상 비교가 가능해야 한다.

      ```
      public TreeSet() // Comparable 구현체의 경우
      public TreeSet(Comparator<? super E> comparator) // 별도로 비교 객체 삽입
      ```

    - TreeSet의 메소드 => 나중에

    | 메소드                                                       | 설명                                                     |
    | ------------------------------------------------------------ | -------------------------------------------------------- |
    | `public E first()`                                           | 정렬된 첫 요소를 반환                                    |
    | `public E last()`                                            | 정렬된 마지막 요소를 반환                                |
    | `public E lower(E e)`                                        | e 객체의 바로 이전 객체를 반환                           |
    | `public E higher(E e)`                                       | e 객체의 바로 다음 객체를 반환                           |
    | `public E floor(E e)`                                        | e 또는 e 보다 이전 객체를 반환                           |
    | `public E ceiling(E e)`                                      | e 또는 e 보다 이후 객체를 반환                           |
    | `public E pollFirst()`                                       | 정렬된 첫 요소를 제거하고 반환                           |
    | `public E pollLast()`                                        | 정렬된 마지막 요소를 제거하고 반환                       |
    | `public NavigableSet<E> descendingSet()`                     | 내림차순으로 정렬하는 객체를 반환                        |
    | `public NavigableSet<E> headSet(E toElement, boolean inclusive)` | toElement 이전 요소로 구성된 객체 반환                   |
    | `public NavigableSet<E> tailSet(E fromElement, boolean inclusive)` | fromElement 이후 요소로 구성된 객체 반환                 |
    | `public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive)` | fromElement 이후, toElement 이전 요소로 구성된 객체 반환 |