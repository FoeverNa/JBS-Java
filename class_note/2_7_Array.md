# 배열 (Arrays)

## 배열의 특성

- 하나의 변수로 여러 개의 값을 다룰 수 있음
- 동일 자료 형만 다룰 수 있음
- 한번 생성한 배열의 크기는 변하지 않음.
- 배열에 속한 값은 메모리에 연속으로 위치함(중요특성!)
  - 빠르게 연산할수있는 근거가된다
  - 한번 배열을 가져올때 일정한 덩어리를 가져올수있게 한다 = > 여러번재사용해서 빠르게 동작함
    - 이런걸 덩어리를 가져온느것은 캐쉬라고하고 캐쉬에 내가원하는것이있어서 값을 가져올수있는걸 캐쉬히트라고한다

## 배열의 선언

- 일반적으로 자료형[] 변수명; 으로 선언함
- 초기화 하지 않고 선언만하면 메모리는 잡히지 않음

```java
        int intval;

        int[] integers;
//        int cStyleInteger[]; // []를 베얄명 뒤에 입력하여 배열선언가능하나 가독성때문에 안쓰임//쓰지마

        long[] longs;
        char[] chars;

        String[] strings;

```

## 배열의 초기화

- new 자료형[배열의크기] 로 초기화 함
  - new해서 하기 때문에 힙영역에 생성이됨
    - 힙영역은 소프트웨어적으로는 크기가 제한이 없기에 배열의 크기도 크게 제약이없다
- 혹은 바로 {}안에 값을 입력하여 초기화 하기도한다
  - new 자료형[]이 생략되어있는 형태이다
```java
// 배열의 생성과 초기화
        integers = new int[10]; // 선언된 배열을 초기화
        int[] integers2 = new int[10]; // 선언과 동시에도 가능
        integers2[0] = 5;
        integers2[1] = 10;
        integers2[3] = 9;
        // 값을 넣을 때 순차적으로 하지 않아도 된다.

        System.out.println(integers2[0]);
        System.out.println(integers2[1]);
        System.out.println(integers2[2]); // 0으로 자동으로 초기화// 입력없으면=0
        System.out.println(integers2[3]);
        System.out.println("");

        int[] integers3 = new int[]{5, 2, 3, 6, 12, 4}; // 바로 입력할 때는 길이 입력 안해도 된다.
        System.out.println(integers3[0]);
        System.out.println(integers3[1]);
        System.out.println(integers3[2]);
        System.out.println(integers3[3]);
        System.out.println(integers3[4]);
        System.out.println(integers3[5]);
//      System.out.println(integers3[6]);// ArrayIndexOutOfBoundsException 오류 발생
        //배열 사용할 때에는 선언해 준 길이까지만 접근해야 한다.

        int[] integers5 = {1, 4, 5, 23, 0}; // new int[] 안 붙여도 된다.
```

## 반복문을 통한 배열에 접근

- 반복문을 통하여 배열을 초기화 할 수 있음
  - 또한 반복문을 통해 배열을접근할 수 있음
  - for(int i )같은 배열을 fori문이라고도 부기도 한다
    - for i문은 배열값을 수정할 수 있다
- 이를 편하게 하기 위한 Enhaced for문을 통해서도 배열에 접근할 수 있음
  - foreach라고도 부른다
    - 값을 소비할때만 foreach쓴다


```java
   // 배열을 반복문으로 접근

        float[] floats = new float[5];
        for (int i = 0; i < floats.length; i++) { // for문을 이용한 초기화, new할때도 초기화지만 처음값넣을때도 초기화//혼용 사용
            floats[i] = (float) (i * 0.25);
        }
        
        // for문을 통한 배열 출력
        for (int i = 0; i < floats.length; i++) {
            System.out.println(floats[i]);
        }

        // Enhanced for, for each문이라고도 함
        // 배열에 접근하는 다른 방법
        // for(자료형 변수명 : 배열명){ 배열0부터 끝까지 값을 floatVal에  대입해줌)

        for(float floatVal: floats){
            System.out.println(floatVal);
        }
        // 밑에 for문과 같은 역할을 함
        
        for(int i = 0 ; i < floats.length; i++){
            float floatVal = floats[i];
            System.out.println(floatVal);
        }
```

## 배열의 크기 변화
- 배열은 메모리를 연속적으로 사용하기에 배열의 크기를 수정할 수 없음
  - 배열의 장점이자 단점이 된다
- 그래서 배열의 크기를 변경하기 위해서는 새로운 배열을 생성해서 대입해주어야함 함
- 반복문을 통해서 하기도하고  arraycopy 메서드를 통해서 수행하기도 함

```java

        int [] src = {1, 2, 3, 4, 5};
        int [] dst = new int[10];
        for(int i = 0; i< src.length; i++){
            dst[i] = src[i];
        }
        for(int integer : dst){
            System.out.println(integer);
        }

        // 배열 크기변경은 빈번하기에 크기변경 함수 존재

        int[] src2 = {1, 2, 3, 4, 5};
        int[] dst2 = new int[10];
        System.arraycopy(src2,0,dst2,0,src2.length);//arraycopy(어디에서,몇번째부터, 어디로, 몇번째부터,어디까지)
        for(int integer: dst2){
            System.out.println(integer);
        }
```