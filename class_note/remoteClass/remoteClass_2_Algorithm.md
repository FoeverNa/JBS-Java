# 알고리즘 강의 정리

 프로그래머스 코딩테스트 연습문제 중 강사님이 선별한 4개의 문제를 각자 풀어보고 강의를 통해 풀이해주신 정리한 내용입니다



## 문제1 (초급)

```java
/**1번
 * 스킬은 알파벳 대문자로 표기하며, 모든 문자열은 알파벳 대문자로만 이루어져 있습니다.
 * 스킬 순서와 스킬트리는 문자열로 표기합니다.
 * 예를 들어, C → B → D 라면 CBD로 표기합니다
 * 선행 스킬 순서 skill의 길이는 1 이상 26 이하이며, 스킬은 중복해 주어지지 않습니다.
 * skill_trees는 길이 1 이상 20 이하인 배열입니다.
 * skill_trees의 원소는 스킬을 나타내는 문자열입니다.
 * skill_trees의 원소는 길이가 2 이상 26 이하인 문자열이며, 스킬이 중복해 주어지지 않습니다
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {


    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        loop:
        for (String tree : skill_trees) {//3번
            List<Integer> list = new ArrayList<>();//4번
            for (char s : tree.toCharArray()) {//5번
                int index = skill.indexOf(s);//6번
                if (index >= 0) {
                    list.add(index);
                }
            }
            if (list.size() > 0 && list.get(0) != 0) {//7번
                continue;
            }
            for (int i = 1; i < list.size(); i++) {//8번
                if (list.get(i - 1) + 1 != list.get(i)) {
                    continue loop;
                }
            }
            answer++;//9번
        }
        return answer;
    }
}

class skilltreeTest{

    public static void main(String[] args) {//2번
        String arr[] = {"BACDE", "CBADF", "AECB", "BDA"};
        System.out.println(new Solution().solution("CBD", arr));
    }
}
```

### 문제풀이

1. 알고리즘 문제의 제한조건은 어떠한 알고리즘을 사용할 수 있는지에 대한 조건이기 때문에 유심히 살펴봐야한다
2. 먼저 main을 작성하여 테스트 코드를 작성하는게 편리하기에 먼저 작성한다.
3. skill_trees에 있는 것들을 살펴봐야하니 향상된 for문을 작성해 준다
4. ArrayList를 사용하기 위해 List를 선언한다.(그냥 ArrayList로 선언해도 된다.) => 콜렉션즈프레임워크에서 배울내용
5. String을 char배열로 만들어주는 toCharArray() 사용해 for문으로 활용해서 skilltree들을 하나씩 비교한다
6. s가 skill에 몇번째 인덱스에 있는지 index변수에 담은 후 index>=0보다 크다면 해당 index를 list에 add해준다
   1. tree에 skill에 있는 글자가 하나라도 있다면 해당 index를 list에 넣는작업
7. list의 size가 0보다 크면서 맨처음 나온 index가 0 이 아니라면 다음 tree로 넘어가라는 조건문
   1. skill에 해당하는 char은 들어있지만 맨 처음 skill인 C로 시작하지 않는 경우를 걸러주는 조건문
8. list의 index가 차례대로 증가하고 있는지 확인하는 반복문
   1. index가 차례대로 증가해야 연속적인 스킬트리가 되기때문에
9. 위에 조건을 다 통과하면 조건에 부합함으로 answer++해주고 answer을 return함



### 느낀점

- skill이 들어있는지를 확인하는 문제 이기 때문에 skill에 indexOf를 썻어야됬는데 반대로 skilltree에 indexOf를 쓴게 문제 풀이를 어렵게 만들었다
- 배열을 쉽게 쓰고 싶을 때는 ArrayList를 사용하는구나



## 문제2 (중급)

```java
/**
 * 문제 설명
 * 가로 길이가 Wcm, 세로 길이가 Hcm인 직사각형 종이가 있습니다.
 * 종이에는 가로, 세로 방향과 평행하게 격자 형태로 선이 그어져 있으며,
 * 모든 격자칸은 1cm x 1cm 크기입니다. 이 종이를 격자 선을 따라 1cm × 1cm의 정사각형으로 잘라 사용할 예정이었는데,
 * 누군가가 이 종이를 대각선 꼭지점 2개를 잇는 방향으로 잘라 놓았습니다.
 * 그러므로 현재 직사각형 종이는 크기가 같은 직각삼각형 2개로 나누어진 상태입니다.
 * 새로운 종이를 구할 수 없는 상태이기 때문에,
 * 이 종이에서 원래 종이의 가로, 세로 방향과 평행하게 1cm × 1cm로 잘라 사용할 수 있는 만큼만 사용하기로 하였습니다.
 * 가로의 길이 W와 세로의 길이 H가 주어질 때, 사용할 수 있는 정사각형의 개수를 구하는 solution 함수를 완성해 주세요.
 *
 * 제한사항
 * W, H : 1억 이하의 자연수
 */

class Solution {
    long gcd(int x, int y) {//2번
        if (y == 0) {
            return x;
        } else {
            return gcd(y, x % y);
        }
    }
    public long solution(int w, int h) {
        long answer = 0;
        answer = (long)w * (long)h - ((long)w + (long)h - gcd(w, h));//3번
        return answer;
    }
}

class SquaresTest{

    public static void main(String[] args) {//1번
        System.out.println(new Solution().solution(8, 12));
    }

}
```

### 문제풀이

1. 먼저 test코드를 작성한다
2. 문제에서 활용될 최대공약수()를 구할 알고리즘으로 유클리드 호제법을 사용하여 메서드를 작성한다
   1. 유클리드 호제법 : 2개의 자연수(또는 정식) a, b에 대해서 a를 b로 나눈 나머지 r이라 하면(단, a>b), a와 b의 최대공약수는 b와 r의 최대공약수와 같다. 이 성질에 따라, b를 r로 나눈 나머지 r'를 구하고, 다시 r을 r'로 나눈 나머지를 구하는 과정을 반복하여 나머지가 0이 되었을 때 나누는 수가 a와 b의 최대공약수이다.
   2. gcd 메서드는 자기 함수를 다시 return하는 재귀 함수로 구현되어 있다 .재귀함수  하나의 [함수](https://namu.wiki/w/함수)에서 자신을 다시 호출하여 작업을 수행하는 방식으로 주어진 문제를 푸는 방법이다
   3. gcd는 **greatest common divisor**의 약자이다
3. 먼저 int값을 입력받아 long으로 출력하는 메서드이기 때문에 모든 값을 long으로 형변환 해주는 것을 먼저해준다.  그후 전체 상자의 갯수인 w*h 에 - 찢어진 상자의 갯수인 (w+h-gcd)를 해준다
   1. 찢어진 상자의 갯수가 w+h-gcd인 이유는 찢어질때마다 가로세로 한칸씩 찢어지지만 꼭지점이 만나는 지점에서는 찢어지지 않아 한칸이 찢어지지 않는데 칸의 갯수가 양변의 최대공약수와 같기 때문이다.

### 느낀점

- 찢어진 부분에 공통점 찾기가 어려웠는데 설명을 들어보니 이렇게 쉬운게 없다.. 그림에서 패턴이 반복되는 부분있었는데 작은 패턴에서 큰패턴으로 규칙발견해갔으면 가능햇었을것같다
- 유클리드 호제법을 배웠는데 앞으로 최대공약수를 구해야하는 상황에서 종종 쓰일것같아 기억하면 좋을 것 같다
- 재귀함수도 처음봤는데 먼가 메서드의 반복문 같은 느낌이었다



## 문제3 중급



```java
/**
 * 문제 설명
 * 가로 길이가 2이고 세로의 길이가 1인 직사각형모양의 타일이 있습니다.
 * 이 직사각형 타일을 이용하여 세로의 길이가 2이고 가로의 길이가 n인 바닥을 가득 채우려고 합니다.
 * 타일을 채울 때는 다음과 같이 2가지 방법이 있습니다.
 * 타일을 가로로 배치 하는 경우
 * 타일을 세로로 배치 하는 경우
 *
 * 직사각형의 가로의 길이 n이 매개변수로 주어질 때, 이 직사각형을 채우는 방법의 수를 return 하는 solution 함수를 완성해주세요.
 *
 * 제한사항
 * 가로의 길이 n은 60,000이하의 자연수 입니다.
 * 경우의 수가 많아 질 수 있으므로, 경우의 수를 1,000,000,007으로 나눈 나머지를 return해주세요.
 */

    class Solution {
    public int solution(int n) {
            int [] integers = new int[n + 1];//3번
            integers[1] = 1;//4번
            integers[2] = 2;
            for (int i = 3; i < n + 1; i++) {
                integers[i] = (integers[i - 1] + integers[i - 2]) % 1000000007;
            }
            return integers[n];//5번
        }
        
//            if (n == 1) {   //1번
//                return 1;
//            }
//            if (n == 2) {
//                return 2;
//            }
//            return solution(n - 1) + solution(n - 2);
//
//
//    }
}

class TileTest {

    public static void main(String[] args) {
        System.out.println(new Solution().solution(60000));
    }
}
```



### 문제풀이

 문제를 분석해보면 n=1일때 1가지, n=2 일때 2가지, n=3일때 3가지, n=4일때 5가지, n=5일대 8가지로 피보나치 수열에 규칙성을 가지고 있다.

1. 피보나치 수열을 **재귀함수**로 표현하면 한것이다. n 부터 차례로 더 해가는게 아니라 재귀함수를 통해 점화식을 만들어 간다
   1. 점화식은 어떤 수열의 각각의 항들의 관계를 나타낸 식이다. 보통 점화식은 n번째의 항을 이전에 나온 항들로 나타내는 공식으로 나타나고, 이 점화식을 만족하는 수열은 초기값에 따라 유일하게 결정된다. 이렇게 수열을 정의하는 것을 수열의 귀납적 정의(recursive definition)라 한다. 
2. 하지만 재귀함수는 메서드를 반복적으로 호출하기 때문에 제한사항에서 나온것 처럼 자연수 60,000 입력했을때 오류가 발생한다. 이렇게 반복된 계산을 줄이는데 사용하는 것이 **다이나믹 프로그래밍** 이다
   1. 다이나믹 프로그래밍은 특정 범위까지의 값을 구하기 위해서 그것과 다른 범위까지의 값을 이용하여 효율적으로 값을 구하는 알고리즘 설계 기법이다. 
   2. 위와 같이 여러개의 값이 반복되서 호출되는 경우 값을 기억해 두었다가 활용하는 기법이라고 간단히 정리해보았다..(확실하지는 않음)
3. 값을 기억하기 위해 배열을 만든다. 배열의 크기가 n+1인 이유는 이번 케이스에서 n이 1부터 시작하기 때문에 배열의 인덱스를 맞춰주기 위해 0을 비우고 1부터 시작하기 위해서다
4. n과 n+1의 값을 먼저 배열에 대입하고 i=3부터 시작하는 반복문을 통해 피보나치 수열의 값을 배열에 대입한다
   1. %1000007을 해주는것은 문제의 제한사항에 제시된 부분이다
   2. 출력값에만 나눠주는게 아닌 모든값에 나눠주도 괜찮은 이유는 더한값을 나눠주나 나눠준값을 더하거나 값이 같기 때문이다.
5. 그 후 입력된 값만 출력하면 되기 때문에 재귀 함수보다 훨씬 적은 연산으로도 같은 결과를 낼 수 있고 이러한 방식을 다이나믹 프로그래밍이라고 한다



### 느낀점

1. 일단 재귀함수로 점화식을 표현하는 방식 자체는 수열과 관계된 부분인데 수열이 고등학교때 배웠다지만 낯설어서 보충학습이 필요한 것 같다
2. 다이나믹 프로그래밍을 처음 배웠는데 배열에 값을 담아서 풀었던 방식은 원래 선호하던 방식이라 조금더 빨리 친해지지 않을까 싶지만 세상일이 그렇게 쉽게 되지 않더라



## 문제4 고급

```java
/**
 * 문제 설명
 * 아래와 같이 5와 사칙연산만으로 12를 표현할 수 있습니다.
 *
 * 12 = 5 + 5 + (5 / 5) + (5 / 5)
 * 12 = 55 / 5 + 5 / 5
 * 12 = (55 + 5) / 5
 *
 * 5를 사용한 횟수는 각각 6,5,4 입니다. 그리고 이중 가장 작은 경우는 4입니다.
 * 이처럼 숫자 N과 number가 주어질 때, N과 사칙연산만 사용해서 표현 할 수 있는 방법 중 
 * N 사용횟수의 최솟값을 return 하도록 solution 함수를 작성하세요.
 *
 * 제한사항
 * N은 1 이상 9 이하입니다.
 * number는 1 이상 32,000 이하입니다.
 * 수식에는 괄호와 사칙연산만 가능하며 나누기 연산에서 나머지는 무시합니다.
 * 최솟값이 8보다 크면 -1을 return 합니다.
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public int solution(int N, int number) {
        List<Set<Integer>> sets = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        sets.add(set);
        set = new HashSet<>();
        set.add(N);
        sets.add(set);
        for (int i = 1; i <= 8; i++) {
            set = new HashSet<>();
            int num = N;
            for (int j = 1; j < i; j++) {
                num *= 10;
                num += N;
            }
            set.add(num);
            for (int j = 1; j < i / 2 + 1; j++) {
                for (int x: sets.get(j)) {
                    for (int y: sets.get(i - j)) {
                        set.add(x + y);
                        set.add(x * y);
                        set.add(x - y);
                        set.add(y - x);
                        if (y != 0) {
                            set.add(x / y);
                        }
                        if (x != 0) {
                            set.add(y / x);
                        }
                        if(set.contains(number)) {
                            return i;
                        }
                    }
                }
                sets.add(set);
            }
        }
        return -1;
    }
}

class NsetTest{
    public static void main(String[] args) {
        System.out.println(new Solution().solution(5, 12));
        System.out.println(new Solution().solution(2, 11));
    }
}
```

 

고급 문제는 강사님께서 기본 전략과 풀이까지 설명해주셨지만 기본 전략 부분부터 이해하기가 어려워 다음을 기약하고 문제와 풀이만 남겨 놓습니다