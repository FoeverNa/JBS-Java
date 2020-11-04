package homework.hw10.sol;

/**
 * 람다식을 이용하여 알고리즘 정답을 테스트하는 프로그램을 작성하시오.
 *
 * 주어진 interface와 실행 메소드를 이용하여 알고리즘 정답을 테스트하는 프로그램을 작성하시오.
 * 이 테스트 방식을 이용하여 실제 알고리즘 문제를 하나 이상 풀이하시오.
 *
 */

interface Solution<T, R> {
    R solve(T t);
}

//T는 입력 객체, R은 출력 객체...
class Algorithm<T, R> {
    boolean test(T input, R groundtruth, Solution<? super T, ? super R> solution) {
        return solution.solve(input).equals(groundtruth);
    }
}
//solution.solve(input값을 )넣은 메소드의 결과값이 기대값(grountruth)와 같은지 equal로 비교하는 것
//* super T의 의미는 T가 부모타입이면 자식객체를 받을 수 있기 때문에 다형성에 의해 받아줄 수 가 있다
// 그렇기 때문에 super T로 받아준것, 보통 은
// 배열을 제네릭으로 비교하는거여쭤봣는데 답은 Arrays쓰는것 or 제네릭을 사용하지 않고 List를 사용하는것 두가지있었음
// 아니면 배열을 쭉뽑아도됨...

class AlgoInput {
    int x, y, z;

    public AlgoInput(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}

public class LambdaExpressions {
    public static void main(String[] args) {
        Algorithm<AlgoInput, Integer> algo = new Algorithm<>();
        System.out.println(algo.test(new AlgoInput(1, 2, 3),6,
                input -> input.x + input.y + input.z));

    }
}

// 입출력과 동시에 가능하다는것... 좀 천천히봐야겟다 = > 돌려볼수 있으면 돌려보자