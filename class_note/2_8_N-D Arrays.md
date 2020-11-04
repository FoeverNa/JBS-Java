# 다차원 배열 (N-D Arrays)

- 배열이 배열을 담고 있으면, 다차원 배열이라 한다.(N-D Array)
- 수학에서 말하는 점 - > 선 - > 면 - > 공간 -> 4차원의 개념

## 다차원 배열의 생성

- 다차원 배열은 자료형[][] 변수명; 으로 선언함
- 앞의 []이 상위 차원을 나타내고 뒤에[]이 하위차원을 나타냄
- 선언과 동시에 값을 입력함으로 초기화할 수 있음
- 하위 차원의 길이는 다를 수 있음  
    - ex) int[][] ints3 = new int[5][];
    - 위와 같이 선언하면 5개의 배열이 들어오는것은 정해져있지만 각각의 배열의 길이는 정하지 않는 것이다


```java
 // 다차원 배열의 선언
        int[][] ints; // int array를 담고있는 array// java-like
        int[] halfCStyle[]; // hybrid
        int oldCStyle[][]; // old...

        int [][] ints1 = new int[10][5]; // 길이가 5인 intArray를 담고있는 길이가 10인 intArrary
                                         // 앞에 있는 숫자가 더 큰 차원의 수
        int [][] ints2 = new int[10][];

        for(int i = 0; i< ints2.length; i++){
            ints2[i] = new int[5];
        }

        int[][] ints3 = new int[5][]; // 하위 차원의 길이는 달라질 수 있다.
        ints3[0] = new int[10];
        ints3[1] = new int[8];
        ints3[2] = new int[4];
        ints3[3] = new int[15];
        ints3[4] = new int[9];

        int[][] ints4 = {{1, 2, 3}, {4, 5, 6}}; // int[2][3] 길이가 3인 intArray가 2개가 있다.
        int[][] ints5 = {{1, 3 ,2}, {1,2}, {4,5,3}}; // int[3][]
```

## 다차원 배열과 반복문

- 다차원 배열도 반복문을 통해 접근할 수 있음
- Ehanced for문도 사용할 수 있음

```java
// 다차원 배열과 반복문
        
        for (int i =0; i < ints5.length; i++){ //ints5.length = 3
            System.out.printf("[%d] :", ints5[i].length);
            for(int j = 0; j < ints5[i].length; j++){ //ints5[i].length 3, 2, 3
                System.out.printf("%d ", ints5[i][j]);
            }
            System.out.println("");
        }

        for(int[]array : ints5){   //1차원 배열을 array[]로 가져옴
            for (int val: array){  //1차원 배열의 값을 val에 입력
                System.out.printf("%d ", val);
            }
            System.out.println("");
        }
```