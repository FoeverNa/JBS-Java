package homework.hw10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 람다식을 이용하여 알고리즘 정답을 테스트하는 프로그램을 작성하시오.
 *
 * 주어진 interface와 실행 메소드를 이용하여 알고리즘 정답을 테스트하는 프로그램을 작성하시오.
 * 이 테스트 방식을 이용하여 실제 알고리즘 문제를 하나 이상 풀이하시오.
 *
 */
@FunctionalInterface
interface Solution<T,  R > {
    R solve(T t1, T t2);
}

class Algorithm<T, R> {
    boolean test(T input, T input2, R groundtruth, Solution<? super T, ? super R> solution) {




        // TODO: solution을 실행하고, 이것이 정답(groundtruth)와 일치하는지 테스트하여 일치 여부를 출력.
        return solution.solve(input, input2).equals(groundtruth);

    }
}

public class LambdaExpressions {
    public static void main(String[] args) {

        // TODO: test를 이용하여 알고리즘 문제를 하나 이상 풀이하고 테스트 결과를 출력하시오.



        Algorithm<String[],  String> algorithm = new Algorithm<>();

        String[] participant1 =  {"leo", "kiki", "eden"};
        String[] completion1 = { "eden", "kiki"	};

        String sol = "leo";



       boolean isOk = algorithm.test(participant1,completion1,sol, (t,g) ->{


            List<String>  listAll = new ArrayList<>(Arrays.asList(participant1));
            List<String> listCom = new ArrayList<>(Arrays.asList(completion1));

            Collections.sort(listAll);
            Collections.sort(listCom);


            listCom.add("");
            String answer="";
            for (int i = 0; i < listAll.size(); i++) {
                if(!listAll.get(i).equals(listCom.get(i))){
                    answer =  listAll.get(i) ;
                    break;
                }

            }
            return answer;

        });

        System.out.println(isOk);




    }
}