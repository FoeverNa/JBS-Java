# 알고리즘 연습 문제 풀이

프로그래머스 코딩테스트 연습문제 중 강사님이 선별한 4개의 문제를 각자 풀어보고 강의를 통해 풀이해주신 정리한 내용입니다

## 문제 1

```java
/**
 * 스파이들은 매일 다른 옷을 조합하여 입어 자신을 위장합니다.
 *
 * 예를 들어 스파이가 가진 옷이 아래와 같고 오늘 스파이가 동그란 안경, 긴 코트, 파란색 티셔츠를 입었다면 다음날은 청바지를 추가로 입거나
 * 동그란 안경 대신 검정 선글라스를 착용하거나 해야 합니다.
 *
 * 종류	이름
 * 얼굴	동그란 안경, 검정 선글라스
 * 상의	파란색 티셔츠
 * 하의	청바지
 * 겉옷	긴 코트
 *
 * 스파이가 가진 의상들이 담긴 2차원 배열 clothes가 주어질 때 서로 다른 옷의 조합의 수를 return 하도록 solution 함수를 작성해주세요.
 *
 * 제한사항
 * clothes의 각 행은 [의상의 이름, 의상의 종류]로 이루어져 있습니다.
 * 스파이가 가진 의상의 수는 1개 이상 30개 이하입니다.
 * 같은 이름을 가진 의상은 존재하지 않습니다.
 * clothes의 모든 원소는 문자열로 이루어져 있습니다.
 * 모든 문자열의 길이는 1 이상 20 이하인 자연수이고 알파벳 소문자 또는 '_' 로만 이루어져 있습니다.
 * 스파이는 하루에 최소 한 개의 의상은 입습니다.
 *
 */
import java.util.Hashtable;
import java.util.Map;
class Solution {
    public int solution(String[][] clothes) {
        Map<String, Integer> map = new Hashtable<>();//1번
        for (String [] cloth: clothes) { //2번
            String key = cloth[1];
            int value = map.getOrDefault(key, 1);
            map.put(key, ++value);
        }
        int answer = 1;//3번
        for (int value: map.values()) {//4번
            answer *= value;
        }
        answer--;//5번
        return answer;
    }
}
```

### 문제풀이

 경우의수를 생각해 한 부위별 옷의 가짓수 +1(해당 부위의 옷을 안입었을 경우) 를 부위별로 모두 곱해주고 옷을 하나도 안입었을 경우 한가지를 빼준 값을 return 하면 되는 문제입니다

- 1번 : 옷 부위 별로 경우의 수를 계산하기 위해 String을 key값으로 하고 Integer를 value로 하는 Hashtable을 생성합니다
- 2번 : 향상된 for문을 통해 입력받은 배열을 출력하여 부위가 저장되어 있는 cloth[1]의 값을 key에 담아준다
  - map.getOrDefault()메서드를 활용하여 옷부위에 해당하는 key 값에 value가 있으면 해당 value를 출력하고 없으면 1을 출력하여 value 변수에 담는다
    - 1을 출력하는 것은 처음 vlaue를 입력할 때 해당 부위를 입지 않는 경우를 +1해주기 위함이다
  - map.put()메서드를 활용하여 key값에 ++value값을 입력해준다. value에 +1을 하는 이유는 해당 key값을 가진 배열이 나왔을 경우 옷의 가짓수가 1개 증가함을 의미한다
    - hashTable.put(key,value)메서드는 key값에 이미 value가 존재한다면 새로 입력한 value로 value를 대체하여 준다.
- 3번: 경우의 수를 곱해주기 위해 answer을 0이 아닌 1로 초기화 시켜준다
- 4번: 향상된 for문을 통하여 map.values() 메서드를 순회하며 vlue값에 담아 answer의 모두 곱해준다
  - value는 부위별 경우의 수이고 이모든 것을 곱한값이 모든 부위를 입는 경우의 수가 된다
- 5번: 모든 옷을 입지 않는 경우의 수 한 가지는 있을 수 없기 때문에 answer에서 빼주고 출력해 주면 된다



### 느낀점

- Hashtable은 처음 써보는데 배열보다 편한 기능이 많은 것 같아서 더 잘쓸수 있도록 연습해야 될것 같다

- getOrDefault() 메서드와 put 메서드를 연결해서 사용하면 기존value에 값을 추가해 나가는 패턴으로 많이 사용할 수 있을 것같다

- 풀이과정은 쉬운 문제였는데 구현하기가 어려웠다. 그보다도 베스트앨범 문제를 너무 오래풀어서 이문제는 오래 못본게 아쉽다. 이문제는 어떻게든 풀었을 것 같은데. 앞으로도 문제 어려우면 다음 문제 찾아보는 것 해봐야겠다

  

## 문제 2

```java
/**
 * 문제 설명
 * 스트리밍 사이트에서 장르 별로 가장 많이 재생된 노래를 두 개씩 모아 베스트 앨범을 출시하려 합니다. 노래는 고유 번호로 구분하며,
 * 노래를 수록하는 기준은 다음과 같습니다.
 *
 * 속한 노래가 많이 재생된 장르를 먼저 수록합니다.
 * 장르 내에서 많이 재생된 노래를 먼저 수록합니다.
 * 장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.
 * 노래의 장르를 나타내는 문자열 배열 genres와 노래별 재생 횟수를 나타내는 정수 배열 plays가 주어질 때,
 * 베스트 앨범에 들어갈 노래의 고유 번호를 순서대로 return 하도록 solution 함수를 완성하세요.
 *
 * 제한사항
 * genres[i]는 고유번호가 i인 노래의 장르입니다.
 * plays[i]는 고유번호가 i인 노래가 재생된 횟수입니다.
 * genres와 plays의 길이는 같으며, 이는 1 이상 10,000 이하입니다.
 * 장르 종류는 100개 미만입니다.
 * 장르에 속한 곡이 하나라면, 하나의 곡만 선택합니다.
 * 모든 장르는 재생된 횟수가 다릅니다.
 */

import java.util.*;

class Node implements Comparable<Node>{//3번
    String key;
    int value;
    int index;

    public Node(String key, int value, int index) {
        this.key = key;
        this.value = value;
        this.index = index;
    }

    public Node(String key, int value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public int compareTo(Node node) {
        return -Integer.compare(this.value,node.value); //- 붙이면 내림차순, +면 오름차순
    }
    public String toString() {
        return "(" + key + ", " + value + ", " + index + ")";
    }
}


class Solution {
    public int[] solution(String[] genres, int[] plays) {

        Map<String, Integer> sumMap = new Hashtable<>(); // 1번
        List<Node> list = new LinkedList<>();//3번 => 위의 Node Class부터 생성

        for (int i = 0; i < genres.length; i ++) { // 2번
            String key = genres[i];
            int value = plays[i];

            int currentValue = sumMap.getOrDefault(key, 0);
            sumMap.put(key, currentValue + value);
            list.add(new Node(key, value, i));//4번
        } //장르별로 plays수를 합치는 방법

        List<Node> sumList = new LinkedList<>();// 5번
        									//ArrayList써도됨
       
        for(Map.Entry<String, Integer> entry: sumMap.entrySet()){//6번 //key끼리 value끼리 묵여있는것을 key-value단위이 엔트리로 묵어줌,
            sumList.add(new Node(entry.getKey(), entry.getValue()));
        }

        Collections.sort(sumList);//7번
        Collections.sort(list); //8번

//        System.out.println(list); // 확인용 출력 //toString 재정의해야함

        List<Integer> bests = new ArrayList<>();//9번

        while(!sumList.isEmpty()){//10번
            String key = sumList.remove(0).key;// getKey로 하는게 더엄밀함
            int num =0;
            for(Node node: list){
                if(node.key.equals(key)){
                    bests.add(node.index);
                    num++;
                }
                if(num ==2){
                    break;
                }
            }
        }

        int[] answer = new int[bests.size()];//11번
            for (int i = 0; i < answer.length; i++) {
                answer[i] = bests.get(i);
            }
        return answer;
    }
}
```

### 문제풀이

- 1번 : 먼저 장르별로 plays를 합쳐서 어떤장르부터 입력해야되는지 알기 위해 key를 String으로 Value를 Integer로 받는 Hashtable을 생성합니다
- 2번 : for문을 통하여 같은 index에 있는 genre와 plays를 각각 key 와 value변수에 담아줍니다
  - getOrDefault()메서드를 통해 현재 key값에 담겨있는 value를 currentValue 변수에 담아줍니다
  - put()메서드를 통해 다시 key값을 통해 currentValue에 value를 더해서 value값으로 입력해 줍니다
    - 위의 과정을 통해서 장르별로 play가 합쳐지게 됩니다
- 3번: 그 후 곡들을 장르, 재생수, 고유번호를 정보를 가진 하나의 객체로 저장하기 위해 Nod클래스를 생성합니다
  - 장르를 key, 재생수를 vlaue, 고유번호롤 index 변수로 넣기위해 변수를 선언하고 각각 생성자를 생성합니다
  - Node 클래스는 값을 정렬하기 위해 Comparable 인터페이스를 구현합니다
    - compareTo 메서드를 재정의해야 하는데 value(재생수)를 비교하기 위해 Integer.compare()메서드를 사용하고 현재 객체의 value와 입력받는 객체의 vlaue를 파라미터변수로 입력합니다.
    - 비교값 앞에 -(음수)를 붙이는 것은 내림차순으로 정렬하라는 뜻이고 반대로 +(양수)가 되면 오름차순으로 정렬이 됩니다
    - 가장 아래의 toString합수는 아래의 정렬이후에 테스트를 위해 정의해 놓은것으로 지워도 상관이 없습니다.
  - 모든 클래스 작성을 마치고 LinkedList를 생성해 Nod객체를 넣어줄 것이라고 생성해줍니다
    - List를 활용한 이유는 key변수가 있지만 장르가 겹치게 되면 key값으로 곡들을 분류할 수 없기 때문에 list를 활용하여 객체를 넣는 방법을 선택했습니다
- 4번: 2번의 for문에 list.add()메서드를 nod객체를 초기화하여 LikedList에 입력합니다
- 5번: sumMap을 정렬하기 위해 먼저 sumList변수로 LikedList를 만듭니다(ArrayList를 사용해도 됩니다)
- 6번 : sumMap.entrySet()을 통해 key값과 value가 따로따로 묵여있는 것들을 entry라는 key-value쌍의 형태로 되게만들어주는 메서드를 사용하여 다시 Map.Entry entry변수에 답는다
  - Entry는 Map의 내부클래스로 key와 value형태로 저장을 해주는역할을 하나부다정도로 이해하고 넘어갔습니다
  - nod객체를 생성하여 entry에 key와 value를 입력해주고 add()메서드를 통해 sumList에 담아 주었습니다
- 7번: sumList에 담긴 장르별 palys의 합계를 Colletion.sort 메서드를 통해  node에서 재정의된 comparTo메서드에 정의에 따라 정렬해 줍니다.
  - 5~6번 항목은 7번의 정렬을 위해 한 작업들이었습니다
- 8번: list의 담긴 곡들도 7번과 같이 정렬해 줍니다
- 9번: best앨범을 채우기 위할 best ArrayList를 생성합니다
- 10번: sumList가 값이 없을 때까지  값을 하나씩 꺼내서 key 값으로 사용하기 위해 while문을 작성합니다
  - sumList.remove(0).key를 통해 0번째 idex에 있는 객체를 꺼내서 key값을 key변수에 넣습니다
    - remove메서드는 array에서 해당 index에 값을 제거함과 동시에 출력해주는 정리하면 꺼내서 쓰는 개념의 메서드라 가능한 구현입니다
  - 향상된 for문을 통해 list의 nod객체들을 하나씩 꺼내서 현재 꺼낸 key와 같으면(장르가 같으면) best에 index(고유번호)를 add합니다.
    - for문앞에 선언된 num은 한장르당 최대 2곡까지 수록할 수 있기에 한곡 수록할때마다 num++을 해주고 num이 2가 되면 for문을 빠져나와 해당 key(장르)가 더이상 best에 추가되지 못하게 하는 역할을 합니다
- 11번: best는 ArrayList<Integer>로 선언되어 있는데 return은 int[] 형태임으로 int[]배열을 선언해주고  ArrayList의 값을 int[]에 넣어주고 그 배열을 return함으로 풀이를 마칩니다



### 느낌점

- 장르, 재생수, 고유값 3가지를 하나의 배열에 넣어야되기 때문에 객체를 생성해야 된다는것은 알았지만 어떻게 다루어야 할지 몰랐는데 이 예제를 통해 배웠다.
  - Copaable을 구현해 Colletion.sort메서드를 사용하여 자료구조의 요소를 정렬할 수 있다는 것을 배웠다
- 2곡만 수록하는것도 되게 어렵게 느껴졋는데 flag세우듯 반복문에 int 변수 하나로 설정할수 있다는것도 새로웠다



## 문제 3

```java
/**
 * 문제 설명
 *  0 또는 양의 정수가 주어졌을 때, 정수를 이어 붙여 만들 수 있는 가장 큰 수를 알아내 주세요.
 *
 *  예를 들어, 주어진 정수가 [6, 10, 2]라면 [6102, 6210, 1062, 1026, 2610, 2106]를 만들 수 있고, 이중 가장 큰 수는 6210입니다.
 *
 *  0 또는 양의 정수가 담긴 배열 numbers가 매개변수로 주어질 때,
 *  순서를 재배치하여 만들 수 있는 가장 큰 수를 문자열로 바꾸어 return 하도록 solution 함수를 작성해주세요.
 *
 *  제한 사항
 *  numbers의 길이는 1 이상 100,000 이하입니다.
 *  numbers의 원소는 0 이상 1,000 이하입니다.
 *  정답이 너무 클 수 있으니 문자열로 바꾸어 return 합니다.
 */


import java.util.PriorityQueue;

//class Node implements Comparable<Node>{
//    String number;
//
//    public Node(String number) {
//        this.number = number;
//    }
//
//    @Override
//    public int compareTo(Node o) {
//        //여기다 비교하는 방법 하면됨
//        return 0;
//    }
//}
class Solution {
    public String solution(int[] numbers) {
        //1번
        PriorityQueue<String> pq = new PriorityQueue<>((o1, o2) -> { 
            //2번
            if (o1.equals(o2)) {
                return 0;
            } else {
                if (Integer.parseInt(o1 + o2) < Integer.parseInt(o2 + o1)) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });

        //3번
        for (int number : numbers){
            pq.offer(String.valueOf(number));
        }
		
        //5번
        assert pq.peek() != null; // assert는 null인지 확인해줌
        if (pq.peek().equals("0")){
            return "0";
        } //입력값이 모두 0인경우를 처리해주는 부분..

        
        //4번
        StringBuilder builder = new StringBuilder();
        while(!pq.isEmpty()){
            builder.append(pq.poll());
        }
        // 원래 STring 구현하는 방법이 있는데 그것을 무시하고 우리가 지정한 방식으로 해라
		
        //6번
        return builder.toString();

    }
}
```



### 문제 풀이

- 1번 :  값을 비교해서 우선순위에 따라 출력하기 위해 PriorityQueue를 사용하기로하고 편의를 위해 Integer가아닌 String타입으로 생성해줍니다
  - PritoiryQueue는 기존 Queue와 다르게 선입선출이 아닌 우선순위가 있는 값이 먼저 출력됩니다
  - 생성자에 o1, o2를 입력하면 Comparator 메서드를 바로 람다식형태로 입력할 수 있게 ->{}형식으로 자동입력됩니다
    - 이는 {}안에 람다식으로 Comparator인터페이스의 compare 메서드의 구현부를 구현하는 것입니다
- 2번 : 람다식을 통해 o1과 o2를 붙였을 때와 o2와 o1을 붙였을 때의 값을 비교해서 더 큰것이 앞으로 가게 해줍니다.
  - compare()메서드에서는 o1과 o2를 비교하여 같으면 0, o2가크면 1, o1이 크면 -1을 return 하여 내림차순으로 정렬하는 메서드입니다.
    - pq 배열을 정렬하는 것이아닌 poll()메서드를 사용할때 출력될 우선순위를 설정하는 것입니다.
- 3번 : 입력 받은 numbers배열의 값을 하나씩 가져와 pq.에 offer(=add)합니다.
- 4번: String 값을 이어 붙이기 위해서 StringBuilder builder를 생성하고 while문을 통해 pq.poll을 하여 builder에 appdend해줍니다
  - 여기서 poll되는 순서는 Comparator에 의해 우선순위가 큰값 즉 값을 앞으로 붙여넣었을때 가장 큰값이 먼저 poll되게 됩니다
- 5번: numbers의 첫 값이 0이어서 pq.peek이 0인 경우 0 을 return해줍니다
  - 첫 값이 0이라는 것은 모든 입력값이 0인 한가지 특별한 케이스를 위해 작성한 코드입니다
- 6번: builder를 출력값인 string으로 변환하기 위해 toString 메서드를 사용해주고 마무리합니다



### 느낀점

- Priority Queue, 람다식등 사용해보지 않은 것들이 등장해서 어렵긴 했지만 예제를 통해서 배우는 기회가 되서 좋았다
- Comparator를 람다식으로 구현한 부분에서 return값이 왜 0, 1, -1이 되는지 잘모르겠어서 한참 찾았는데 compare 메서드를 구현하고 있는 것이고 compare메서드의 작동원리나는 것을 배웠다
- 번호를 코드옆이아니라 위에 적어주는게 가독성이 조금이라도 더 좋아 진다는 것을 알고 이제부터 그렇게 하려고 합니다



## 문제4

```java
/**
 *문제 설명
 * n개의 노드가 있는 그래프가 있습니다. 각 노드는 1부터 n까지 번호가 적혀있습니다. 1번 노드에서 가장 멀리 떨어진 노드의 갯수를 구하려고 합니다.
 * 가장 멀리 떨어진 노드란 최단경로로 이동했을 때 간선의 개수가 가장 많은 노드들을 의미합니다.
 *
 * 노드의 개수 n, 간선에 대한 정보가 담긴 2차원 배열 vertex가 매개변수로 주어질 때,
 * 1번 노드로부터 가장 멀리 떨어진 노드가 몇 개인지를 return 하도록 solution 함수를 작성해주세요.
 *
 * 제한사항
 * 노드의 개수 n은 2 이상 20,000 이하입니다.
 * 간선은 양방향이며 총 1개 이상 50,000개 이하의 간선이 있습니다.
 * vertex 배열 각 행 [a, b]는 a번 노드와 b번 노드 사이에 간선이 있다는 의미입니다.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
class Node implements Comparable<Node> {
    int index;
    int dist;
    public Node(int index, int dist) {
        this.index = index;
        this.dist = dist;
    }
    @Override
    public int compareTo(Node o) {
        return Integer.compare(dist, o.dist);
    }
}
class Solution {
    public int solution(int n, int[][] edge) {
        int [] dists = new int[n + 1];
        Arrays.fill(dists, Integer.MAX_VALUE);
        List<List<Integer>> adj_lists = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            List<Integer> adj_list = new ArrayList<>();
            for (int[] e: edge) {
                if (e[0] == i) {
                    adj_list.add(e[1]);
                } else if (e[1] == i) {
                    adj_list.add(e[0]);
                }
            }
            adj_lists.add(adj_list);
        }
        PriorityQueue<Node> heap = new PriorityQueue<>();
        dists[1] = 0;
        heap.add(new Node(1, dists[1]));
        while (!heap.isEmpty()) {
            Node node = heap.remove();
            for (int indVisit: adj_lists.get(node.index)) {
                if (node.dist + 1 < dists[indVisit]) {
                    dists[indVisit] = node.dist + 1;
                    heap.add(new Node(indVisit, dists[indVisit]));
                }
            }
        }
        int max = 0;
        int answer = 0;
        dists[0] = 0;
        for (int el: dists) {
            if (el == max) {
                answer++;
            }
            if (el > max) {
                max = el;
                answer = 1;
            }
        }
        return answer;
    }
}
```

문제4는 현재 저에게는 난이도가 높아서 문제와 풀이만 남겨놓고 후일을 기약하겠습니다







