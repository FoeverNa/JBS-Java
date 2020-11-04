# 스트림 API (Stream API)

## 스트림 API란

- Java 8에서 추가된 java.util.stream 패키지

- 컬렉션의 요소를 람다식으로 처리할 수 있도록 하는 함수형 프로그래밍 도구

  - 간결한 코드로 작성할 수 있다

  - 데이터 소스에 대한 공통된 접근 방식 제공 한다.

    - Stream으로 변경해주고 나면, List, Set, Map 모두 동일한 방식으로 데이터를 처리할 수 있다

      

## 스트림 API의 특징

- 간결한 코드 작성

```java
       // JAVA7에서의 코드
        List<String> list =  Arrays.asList("fast", "campus", "rocks");
        List<String> newList = new ArrayList<>();

        for(String s : list){
            newList.add(s.toUpperCase());//대문자로 변환해서 add
        }

        for(String s: newList){
            System.out.println(s);
        }
        //FAST
        //CAMPUS
        //ROCKS
        System.out.println("");


        // JAVA8 - STREAM API -> 훨씬 더 간결한 코드로 작성할 수 있다
        List<String> list1 = Arrays.asList("fast", "campus", "rocks");
        Stream<String> stream = list1.stream(); // stram으로 변환해주는것
        stream.map(String::toUpperCase)
                .forEach(System.out::println);
        //FAST
        //CAMPUS
        //ROCKS
        // 스트림으로 변환만 해주면 Upercase로 바꾸고 출력한다라는 기능을 아주 쉽게 구현할 수 있다
        // 길게쓰는 코드는 쭉이어서 작성할수 있지만 람다식은 시처럼 고민해서 쓰는것이래..오

```

- 데이터 소스에 대한 공통의 접근 방식 제공

  - List, Set, Array 등 다양한 경우에 대해 표준화된 방식으로 데이터 접근 가능
  - 특히 Stream::sorted() 메소드는 공통된 정렬 방식을 제공

- 중간 처리와 최종 처리를 지원

  - Mapping, Filtering, Sorting 등 중간 처리 지원 (여러개 사용 가능)
  - Iteration, Count, Average, Summation, Reduce 등 최종 처리 지원 (마지막에 하나 사용 가능)

- 데이터 소스와 처리 결과를 분리

  - 원본 데이터를 유지하여 처리에 의한 부작용 방지

  - 처리 결과를 새로운 컬렉션으로 저장 가능

    

## 스트림 API를 이용한 자료 처리

### 스트림 API의 종류

| 종류           | 처리 대상                   |
| -------------- | --------------------------- |
| `Stream<T>`    | 일반적인 객체를 처리        |
| `IntStream`    | 기본 자료형 `int`를 처리    |
| `LongStream`   | 기본 자료형 `long`을 처리   |
| `DoubleStream` | 기본 자료형 `double`을 처리 |

### 스트림 객체 생성 메소드

- 데이터 소스로부터 스트림 생성

  | 데이터 소스  | 메소드                                              |
  | ------------ | --------------------------------------------------- |
  | `Collection` | `default Stream<E> stream()`                        |
  | `Collection` | `default Stream<E> parallelStream()`                |
  | `Arrays`     | `public static <T> Stream<T> stream(T[] array)`     |
  | `Arrays`     | `public static <T> Stream<T> of(T ... values)`      |
  | `Arrays`     | `public static IntStream stream(int[] array)`       |
  | `Arrays`     | `public static IntStream of(int ... values)`        |
  | `Arrays`     | `public static LongStream stream(long[] array)`     |
  | `Arrays`     | `public static LongStream of(long ... values)`      |
  | `Arrays`     | `public static DoubleStream stream(double[] array)` |
  | `Arrays`     | `public static DoubleStream of(double ... values)`  |

- 정수 범위와 java.util.Random으로부터 생성

  | 구분            | 메소드                                                       |
  | --------------- | ------------------------------------------------------------ |
  | `int`형 범위    | `public static IntStream range(int startInclusive, int endExclusive)` |
  | `int`형 범위    | `public static IntStream rangeClosed(int startInclusive, int endInclusive)` |
  | `long`형 범위   | `public static LongStream range(long startInclusive, long endExclusive)` |
  | `long`형 범위   | `public static LongStream rangeClosed(long startInclusive, long endInclusive)` |
  | Random *p*형 값 | `public PStream ps()`                                        |
  | Random *p*형 값 | `public PStream ps(long streamSize)`                         |
  | Random *p*형 값 | `public PStream ps(long streamSize, p origin, p bound)`      |

  ```java
   	    //위의 정리된 표와 코드를 같이 보면 좋을 것 같다
  
  		// 스트림 생성 방식1.
          Stream<String> stream1 = list1.stream();// 제네릭 T를 가진 기본형 stream// 객체를 대입
  
          // 스트링 생성 방식2.
          int[] ints = {4, 6, 2, 19, 2, 58, 4, 6, 5};
          // Arrays 클래스의 클래스 메소드 stream을 이용한것 // array는 객체가 아니기 때문에...?
          IntStream insStream = Arrays.stream(ints);
          // LongStream, DoubleStream 도 있다. // P type같네
          // Wrapper클래스 사용안하고 PType쓰는건 오토박싱 언박싱 없이 효율적으로 사용가능
  
          // 스트림 생성 방식3.
          // Stream 클래스의 메서드 of // 가장 직접적인 생성 방식이래
          DoubleStream doubleStream = DoubleStream.of(0.4, 0.6, 0.2, 1.2, 0.94);
  
          // range를 이용한 스트림
          // 정수를 시작부터 끝까지 쭉 뱉어주는 친구 => fori문을 대체하는 스트림 // 보충
          IntStream intStream1 = IntStream.range(0,10);// => 10은 포함되지 않는다
          intStream1.forEach(System.out::println);
          // 0 1 2 3 4 5 6 7 8 9
          IntStream intStream2 = IntStream.rangeClosed(0,10);// => 10은 포함되지 않는다
          intStream2.forEach(System.out::println); // 출력을 한번하면 재활용이안됨 Stream은 그래서 새로만들어줘야함
          // 0 1 2 3 4 5 6 7 8 9
          //LongStream도 range, rageClosed가 있다
  
          // Random 객체를 이용한 스트림
          Random random = new Random();
          //Random에 메서드목록보면 유용한게많다
          LongStream longStream = random.longs(); // 개수 제한 없이 무한히 출력한다
         longStream.forEach(System.out::println);
  
          // 개수 제한 가능
          LongStream longStream1 = random.longs(100); // 개수를 정할 수 있다
          longStream1.forEach(System.out::println);
  
          // 개수 제한 + 범위 제한 가능 => 가장 많이 사용하는 기능 
          LongStream longStream2 = random.longs(100,0,1000 ); // 100개의 수를 0 ~ 1000까지 범위로 랜덤 생성
          longStream2.forEach(System.out::println);
  
    
  
  ```

### 중간 처리 메소드

- 기존의 스트림을 처리하여 새로운 스트림을 반환한다. 
- 여러개의 중간 처리 메소드를 동시에 사용할 수 있다

| 동작   | 메소드                                                       |
| ------ | ------------------------------------------------------------ |
| 필터링 | dinstict(), filter()                                         |
| 자르기 | skip(), limit()                                              |
| 정렬   | sorted()                                                     |
| 매핑   | flatMap(), flatMapToP(), map(), mapToP(), asDoubleStream(), asLongStream() |
| 조회   | peek()                                                       |



#### 필터링

필터링은 스트림의 일부 요소를 제거하는 역할을 한다.

- `Stream<T> distict()` : 스트림 요소의 중복을 제거한 스트림을 반환

- `Stream<T> filter(Predicate<? super T> predicate)` : Predicate에서 true에 해당하는 요소만으로 구성된 **스트림을 반환**

  ```java
  System.out.println("");
  Stream<String> stringStream = Stream.of("Java","Is","Fun","Isn't","It","?", "Java");
  
      //필터링 메소드
  //distinct() 스트림에 같은 요소가 있을 경우 하나만 남기고 삭제하는 메소드
  stringStream.distinct().forEach(System.out::println);
  //stringStream.distinct() -> distinct()의 결과도 스트림이된다
  System.out.println("");
  
  //filter(): Predicate 계열을 입력으로 받아서, true인 요소만 남긴다 (조건문의 true인요소() -> true만 필터한다
  stringStream = Stream.of("Java","Is","Fun","Isn't","It","?");
  stringStream.filter(s -> s.length() >= 3).forEach(System.out::println); // length()가 3이상인 요소만 남겨라
  //바로 파라미터로 String받는게 신기하네 // 파라미터는 1개만 입력이 가능하다
  System.out.println("");
  
  ```

#### 자르기

자르기는 스트림의 일부 요소를 한번에 생략한다.

- `Stream<T> limit(long maxSize)` : 최대 maxSize까지 구성된 스트림을 반환

  - maxSize에 미치지 못하면 그대로 가지고 가고 넘으면 순서대로 뒤에가 짤림

- `Stream<T> skip(long n)` : 스트림의 상위 n개 요소를 생략한 스트림을 반환

  

#### 정렬

스트림 요소의 `compareTo()` 또는 입력받은 `Comparator`를 이용해 정렬

- `Stream<T> sorted()` : Comparable 객체를 정렬한 스트림 반환

- ```java
  Stream<T> sorted(Comparator<? super T> comparator)
      
  //Comparable 인터페이스의 compareTo 메소드 정렬(기본 정렬 방식)
  stringStream = Stream.of("abc", "fwf", "twtie", "dnmov", "work");
  stringStream.sorted().forEach(System.out::println);
  System.out.println("");
  
  //Comparator 인터페이스를 람다식으로 구현하여 정렬
  stringStream = Stream.of("abc", "fwf", "twtie", "dnmov", "work");
  stringStream.sorted((o1, o2) -> o1.length() - o2.length()).forEach(System.out::println);
  System.out.println("");
  
  //intSTream은 람다식으로 구현불가능하다?
  ```

   

  

  : Comparator를 이용하여 정렬된 스트림 반환 => 어떻게 사용하는지 알아보기

  - Comparator의 정적 메소드와 기본 메소드

    - 정적 메소드: naturalOrder(), reverseOrder(), comparing(), comparingP(), nullsFirst(), nullsLast()
    - 기본 메소드: reversed(), thenComparing(), thenComparingP()

    

#### 매핑

Function 인터페이스를 이용해 요소의 값을 변환한다.

- map 계열

  - `<R> Stream<R> map(Function<? super T, ? extends R> mapper)` : 기존 스트림의 T 타입 요소를 R 타입으로 변환하여 새로운 스트림 반환
  - `PStream mapToP(ToPFunction<? super T> mapper)` : R이 기본형 타입으로 제한된 map() -> Function이 아닌 Operator계열만 사용이 가능하다

- flatMap 계열

  - `<R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)` : 스트림의 T 타입 요소가 n개의 R 타입 요소로 매핑된 새로운 스트림을 반환
    - 2차원 배열의 하나하나 요소들들이나 String배열의 하나하나 char을 하나의 스트림으로 만들어서 새로운 스트림을 반환한다
    - String 값 하나를 char[]로 본다면 String의 배열 또한 2차원 배열이 된다
  - `PStream flatMapToP(Function<? super T, ? extends PStream> mapper)` : R이 기본형 타입으로 제한된 flatMap()
  
  ```java
         // 매핑(Mapping) -> 입력요소 1 : 1 출력요소
          // Function 계열의 인터페이스를 사용하여 스트림의 각 요소를 매핑 => 안에있는애들을 다 매핑해버림
          stringStream = Stream.of("abc", "fwf", "twtie", "dnmov", "work");
          // Function 계열로 String -> Integer로 변환하는 매핑
              // -> 문자열로 이루어진 stream을 다른 stream으로 변환
          Stream<Integer> stream2 = stringStream.map(s -> s.length()); // Integer stream으로 받아줘야함
          //Function<String, Integer> 느낌으로 된 것
          stream2.forEach(System.out::println);// 정수로 반환
          System.out.println("");
  
          //PStream (기본형 타입의 스트림)은 Operator 계열로 처리한다(자료형 변환 x) //왜 funtion안하는지 찾기
          IntStream intStream3 = IntStream.of(5,2,30,8,0,2,-34);
          IntStream intStream4 = intStream3.map(value -> value * 10);
          intStream4.forEach(System.out::println);
          System.out.println("");
  
            // flatMap 계열 매핑 -> 입력 1 : n 출력 (하나의 입력을 받고 여러개의 결과값을 스트림 형태로 출력 한다)
          List<String> list2 = Arrays.asList("java", "backend", "best", "course");
          list2.stream().flatMap(s -> {
              return Arrays.stream(s.split("")); // 하나의 String받고 한글자씩 쪼개서 String Stream으로 출력
              // s,split : "java" -> {"j", "a", "v", "a"}
          }).forEach(System.out::println);
          System.out.println("");
  ```



#### 조회

스트림 처리 과정에 영향을 미치지 않으며, 중간 결과를 확인할 수 있으며 입력받은 스트림과 **동일한 스트림을 반환**한다. (Stream에 영향을 주지 않는다)

- `Stream<T> peek(Consumer<? super T> action)`

  - stream의 요소를 파라미터로 입력받아 소비할 수 있는 메서드를 구현할 수 있지만 원본  Stream에는 영향을 주지 않는다
  
  ```java
          //조회(Peek) - 중간 결과를 출력해볼 수 있음 (디버깅 가능)
          // peek() -> Consumer 계열을 람다식 입력으로 받아 입력 요소를 소비
          list2.stream().flatMap(s -> {
              return Arrays.stream(s.split(""));
              // s,split : "java" -> {"j", "a", "v", "a"}
          }).peek(s -> System.out.println("flatMap(): " +s))
                  .distinct().peek(s -> System.out.println(("distinct(): "+ s)))
          .count();
          // 요소가 하나씩 계산이된다
          // => 하나씩 쭉되는게아니고 flatNam() 다음에 distinct() 하나씩 출력해준다다
              //최종 처리 메소드가 있어야만 중간 메소드가 작동을 한다
          System.out.println("");
  
  
  ```



### 최종 처리 메소드

- 스트림의 끝으로 스트림을 반환하지 않는다
  - 결과 값은 void 일 수도 있고 다른 무언가를 리턴할 수 도 있다

| 동작 | 메소드                                            |
| ---- | ------------------------------------------------- |
| 매칭 | allMatch(), anyMatch(), noneMatch()               |
| 수집 | collect()                                         |
| 루핑 | forEach()                                         |
| 집계 | count(), max(), min(), average(), sum(), reduce() |
| 조사 | findFirst(), findAny()                            |



#### 매칭

Predicate 계열을 이용해 스트림 요소들이 특정 조건에 만족하는지 조사하는 메소드

- `boolean allMatch(Predicate<? super T> predicate)` : 스트림의 모든 요소가 Predicate를 만족하면 true를 반환 

- `boolean anyMatch(Predicate<? super T> predicate)` : 스트림의 요소 중 하나라도 Predicate를 만족하면 true를 반환

- `boolean noneMatch(Predicate<? super T> predicate)` : 스트림의 요소가 모두  Predicate를 만족하지 않으면 true를 반환(하나라도 만족하면 false)

  ```java
  Stream<String> st0 = Stream.of("abc", "cde","efg");
  System.out.println(st0.allMatch(s -> s.equals("abc")));// false
  
  st0 = Stream.of("abc", "cde","efg");
  System.out.println(st0.anyMatch(s -> s.equals("abc")));// true
  
  st0 = Stream.of("abc", "cde","efg");
  System.out.println(st0.noneMatch(s -> s.equals("abc")));//false
  ```



#### 집계(통계)

- 기본 집계 메소드

  - 기본형 스트림의 통계(Int, Long, Double) : count(), sum(), average(), min(), max()

  - T 타입 스트림의 통계 : count(), min(), max() (min, max의 경우 `Comparator` 필요 => 객체를 비교하는 것이기 때문에)

    

- reduce() 메소드 -> 사용자 정의 집게 메소드 // optional은 수업에서 다루지 않음

  - `Optional<T> reduce(BinaryOperator<T> accumulator)` : accumulator를 수행하고 `Optional<T>` 타입 반환

  - `T reduce(T identity, BinaryOperator<T> accumulator)` : identity를 초기값으로 하여, accumulator를 이용해 집계 연산

  - `<U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner)` : combiner를 이용해 병렬 스트림 결합

    ```java
    System.out.println(IntStream.range(0, 10).reduce(0,(value1, value2) -> value1 + value2)); //45 
    // 처음부터 누적해서 연산을 해나가는 것. accumulator
    // identity -> 맨처음 누적값과 첫번째값을 연산하기 위함
    // 0+0, 0+1, 1+2, 3+3 ....
    // sum() 이 reduce를 이용해서 구현이 되어 있다다
    
    System.out.println(IntStream.range(0, 10).
                       reduce(Integer.MAX_VALUE, (value1,value2) -> value1 < value2 ? value1 : value2)); // 10
    
    
    ```

- `java.util.Optional<T>`

  - T 타입 객체의 null 여부에 따라 다르게 동작하는 Wrapper 클래스

  - Optional 클래스의 정적 메소드를 이용해 Optional 객체 생성

    - `public static <T> Optional<T> of(T value)` : value가 null인 경우 NullPointerException을 발생시키는 Wrapping 메소드

    - `public static <T> Optional<T> ofNullable(T value)` : value가 null인 경우 empty()의 결과를 리턴하는 Wrapping 메소드

    - `public static <T> Optional<T> empty()` : 값이 비어있는 Optional 객체를 리턴

      

  - Optional 객체를 처리하는 메소드

    - `public T get()` : Optional의 값을 리턴하며, null일 경우 NullPointerException 발생
    - `public void ifPresent(Consumer<? super T> consumer)` : Optional 값이 null이 아닐 경우 consumer를 이용해 소비한다.
    - `public T orElse(T other)` : Optional의 값이 null일 경우 other를 반환한다.
    - `public T orElseGet(Supplier<? extends T> other)` : Optional의 값이 null일 경우 Supplier를 통해 공급받은 값을 반환한다.
    - `public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X` : Optional의 값이 null일 경우 exceptionSupplier에서 공급받은 예외를 throw

#### 반복

forEach() 메소드로 스트림 요소를 순차적으로 `Consumer<T>`를 이용해 소비

- `void forEach(Comsumer<? super T> action)` : 스트림의 각 요소를 action으로 소비
  
  - Consumer 이기 때문에 void를 출력한다.
  
    

#### 조사

첫번째 요소 또는 아무 요소를 조사하는 최종 처리 메소드. 필터링 등으로 인해 스트림에 요소가 남아있는지 확인할 수 있다.

- `Optional<T> findFirst()` : 스트림의 첫 요소 또는 empty Optional 객체를 반환

- `Optional<T> findAny()` : 스트림의 아무 요소나 가지는 Optional 객체를 반환

  

#### 수집

- 필요한 요소를 수집하여 새로운 Collection으로 구성하여 반환하는 메소드
  
- Stream API는 JCF -> Stream -> 중간 처리 -> 결과(출력, 값, Colletion) 해준다
  
  - 출력, 값, Colletion 중 하나는 선택을 해서 결과를 반환해야 한다
  
- collect() 메소드

  - `<R, A> R collect(Collector<? super T, A, R> collector)` : collector를 이용해 새로운 Collection R에 담아 반환

    - Colletion -> 스트림 -> 처리 -> Colletion

    - Collectors의 정적 메소드: `toList()`, `toSet()`, `toCollection()`, `toMap()`, `toConcurrentMap()`
      - 해당 메소드 통해서 간단하게 변환이 가능하다

  - `<R, A> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner)` : supplier를 통해 공급된 컨테이너 R에 accumulator를 이용해 T값을 저장. 병렬처리 스트림에 사용될 경우 combiner를 이용해 스레드별 컨테이너 R을 통합

    ```java
       //toList() 메소드를 쓸 경우, ArrayList로 Collect하는 Collector 반환
            String[] array = {"Collection", "Framework", "is", "so", "cool"};
            Stream<String> stream3 = Arrays.stream(array);
            List<String> collected = stream3.filter(s -> s.length() >= 3)
                    .collect(Collectors.toList());
            System.out.println(collected); //[Colletion, Framework, cool]
            //filter된 결과가List로 collet된 모습
    
             //toSet() 메소드를 쓸 경우, HashSet으로 Collect하는 Colletor를 반환
            Stream<String> stream4 = Arrays.stream(array);
            Set<String> collected2 = stream4.filter(s -> s.length() >= 3)
                    .collect(Collectors.toSet());
            System.out.println(collected2); //[Colletion, cool, Framework]
            // set이라 정렬되지 않은 결과값
    
            Stream<String> stream5 = Arrays.stream(array);
            List<String> collected3 = stream5.filter(s -> s.length() >= 3)
                    .collect(Collectors.toCollection(LinkedList::new));
            System.out.println(collected3);
    		// 이렇게 Collet 하고싶은 것을 지정해서 생성할 수 있음
    
            Stream<String> stream6 = Arrays.stream(array);
            Set<String> collected5 = stream6.filter(s -> s.length() >= 3)
                    .collect(Collectors.toCollection(HashSet::new));
            System.out.println(collected5); // Set으로 생성하면 HashSet이지만 이렇게 지정할수도있다
    
            //Map<K, V> Map.Entry<K, V>  // Map은 key value쌍이여야 됨
            Stream<String> stream7 = Arrays.stream(array);
            Map<String, Integer> collected6 = stream7.filter(s -> s.length() >= 3)
                    .collect(Collectors.toMap(s -> s, String::length));// toMap()안에 key value어떻게 할지 람다식으로 작성해야한다
    															//key, value 별도의 람다식으루 작성해야 한다
            System.out.println(collected6);
            // String이 key이고 Integer가 벨류인 상황
    ```

  

- Collectors의 정적 메소드를 이용한 그룹화와 분리 

  - 스트림을 그룹화하거나 분리하여 Colletion으로 반환하는 방식
    - 분리 = partitioningBy(Predicate)
      - partitioningBy는 Map<boolean , List<T> >가 되어 boolean을 key로 입력갑이 List로 분류된다
    - 그룹화 = goupingBy(Fucntion)
      - groupinBy는 Map<R , List<T> >가 되어 R타입별로 입력갑이 List로 분류된다
          - Function식에 따라 그룹별로 분리하여 Colletion으로 반환한다

  ```java
       String [] array2 = {"Python", "is", "awful", "lame", "not", "good"};
          Map<Integer, List<String>> map = Arrays.stream(array2)
                .collect(Collectors.groupingBy(String::length)); //claasfier가 구분자
                  //기본은 List고 List말고 다른걸로 담을수도 있음
          System.out.println(map);//{2=[is], 3=[not], 4=[lame, good], 5=[awful], 6=[Python]}
          //기준은 여러개 해보는것 해보기
  
          Map<Boolean, List<String>> map2 = Arrays.stream(array2)
                .collect(Collectors.partitioningBy(s -> s.length() <4 ));
          System.out.println(map2); //{false=[Python, awful, lame, good], true=[is, not]}
  ```

  

  - 그룹화 + DownStream collector 
  - 그룹화해서 List에 담길 요소들을 DownStream Collector를 통해 처리하는 방법
    - `counting()`, `summingP()`, `averagingP()`, `maxBy()`, `minBy()`, `reducing()` 을 사용할 수 있다
```java
          // 그룹화 + DownStream collector
          // 최종 처리 메소드에서 있던 count, min()... 등과 유사한
          // Collector중에도 counting(), minBy(), maxBy() ... 등이 있다.
  
          Map<Integer, Long> map3 = Arrays.stream(array2)// 출력값이 Long
                  .collect(Collectors.groupingBy(String::length,Collectors.counting()));
                                  //List로 저장하는게 아니라 다른방법으로 처리하겠다
          System.out.println(map3); //{2=1, 3=1, 4=2, 5=1, 6=1} // 각 length별로 counting한 값
          //Collecotr에오 counting이 있는데 일반적으로는 count();를 사용한다
          System.out.println("");
```



### 병렬 스트림 => 내용보충

- 보통의 프로그램의 코드가 순서에 따라 한줄씩 실행된다면 다중 스레드 상황에서는 동시에 여러줄이 독립적으로 실행할 수 있는데 이것을 병렬처리라고 한다
  
- 순수함수의 경우 입력값이 동일한 경우 출력값이 동일한 특성을 가졌기 때문에 동시에 여러줄을 처리하더라도 서로의 값의 영향을 미치지 않는다
  - 이러한 수수함수를 활용하여 동시에 여러스레드에서 코드를 처리하여 수행 속도를 높이는 것이 병렬처리이다.
  - 병렬처리로 수행한 결과는 순서대로 실행 되지 않기 때문에  뒤죽박죽섞이게 될 수 있다.
  
- 병렬 스트림의 생성
  
  - 스트림의 경우 이러한 병렬처리를 하기위한 병렬스트림을 간단하게 생성할 수 있다
    - stream() 대신 parallelStream()으로 변경
    - stream 생성 후 parallel()으로 병렬화
  
  ```java
  Stream<String> parStream = Arrays.stream(array2).parallel();// stream  생성 후 parallet()으로 병렬화
  System.out.println(parStream.map(String::length)
          .count());//6
  
  List<String> list4 = List.of("atwe","bff","cqqqw","dtwer");
  // parallelStream을 사용하면 연산 순서가 달라질 수 있다.
  Stream<String> stream8 = list4.parallelStream(); //stream()대신 prallelStream()으로 변경
  // System.out.println(stream8.isParallel());//parallel인지 검사도 가능하다
  
  stream8.map(String::length)
      .peek(s -> System.out.println("A:"+s))
      .filter(value -> value > 3)
      .peek(s -> System.out.println("B:"+s))
      .forEach(System.out::println);
  //List의 0번재부터 peek A -> peek B -> foreach순으로 실행되야하지만 위의 예제의 경우 순서가 뒤죽박죽으로 실행이됨
  //=> 병렬처리가 됬다는 증거
  ```
  
  
  
- combiner를 이용해 병렬 스트림으로 생성된 컬렉션을 결합
  
  - `BiConsumer<T, K> combiner` : T 객체에 K 객체를 결합

```java
Integer reducedParallel = Arrays.asList(1,2,3)
        .parallelStream()
        .reduce(10,(a,b)->a*b,(a,b)-> a+b);

System.out.println(reducedParallel); //60
// 10*1 + 10 *2 + 10 *3 = 60;
// 병렬처리한 값을 마지막에 결합한다
```