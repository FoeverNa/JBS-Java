package ch02.p04;

/**
 * 연산자(Operator)
 * 연산자(operator), 피연산자(Operand) ->으로이루어진 연산식(Expression) 익스프레션은 기억해두어야함
 * 사칙 연산자 : + - * /
 * 비교 연산자 : > < <= >=
 * 논리 연산자
 * 증감 연산자
 * 삼항 연산자
 * 비트 연산자
 */
public class Operator {

    public static void main(String[] args) {
        //사친연산

        int x = 10, y = 20, z;

        z = x + y; // +: 연산자(동사), x:피연산자, y:피연산자, x+y:연산식(expression)
                    // 항이 2개있는 연산자 = 이항 연산자, 항=피연산자
                    // =:연산자, z:피연산자, x+y : 피연산자(연산식이 피연산자가됨)
                    //연산자들간에 우선순위가 있기 때문에 =이 앞에 있지만 가장 늦게 연산된다
        System.out.println(z); //30

        System.out.println("정수형 사칙 연산");
        System.out.println(20 -5); // 15
        System.out.println(5-20); // -15
        System.out.println(10 * 662); //6620
        System.out.println(150 / 8); //18, 정수의 나눗셈은 몫만 나옴, 사라지는 수는 &로 구함
        System.out.println(150 & 8); // modulus,(모듈러스연산) 나머지 구하는 연산자

        System.out.println("실수형 사칙 연산");
        System.out.println(10.0 + 52.3);
        System.out.println(10.5f + 12.3); //float이 double로 자동으로 변환되서 계산됨
        System.out.println(10.4 - 50);//정수와 실수의 계산에서는 실수형으로 변환후 연산
        System.out.println(10.2 * 4.2);// 42.839999
        System.out.println(150 / 8.0); //18.75, 정수/실수이기에 실수로 변환 되서 몫이 아닌 실수값이 나옴
        System.out.println(5.2 / 1.2); // 몫이 아닌 실수값
        System.out.println(5.2 % 1.2);// 실수도 modulus 연산으로 나머지 계산 가능
                                        //0.4가 나와야되는데 소숫점 조금더나오는데 그건 부동소수점 오차
        System.out.println("");


        System.out.println("사칙연산 주의사항");
        //overflow가 있음
        System.out.println(Integer.MAX_VALUE /2 *3); //-1073741827,값이 overflow되버림
        System.out.println(Integer.MAX_VALUE); //최대값
        System.out.println(Integer.MAX_VALUE+1); // 최대값+1 = 최소값
        System.out.println(Integer.MIN_VALUE); // 최소값
        System.out.println(Integer.MIN_VALUE-1); //최소값 -1 = 최대값

        //참고 : 최대값 +1이 최소값이 되는 이유

        int maxVal = 0b01111111111111111111111111111111; // 최대값에서 +1을 하면 맨끝짜리부터 값이 올라가서 00000하다가 맨끝에서1이됨
        int maxVal1= 0b10000000000000000000000000000000; //에 +1을 해주면 최소값이됨 (최대 범위보다 +1많잖아 최소값이)

        System.out.println(maxVal);
        System.out.println(maxVal1);
        int minVal = 0b10000000000000000000000000000001;// +1을 해준 결과
        //맨앞자리가 0이면 양수 1이면 음수
        // 원래는 1의 보수구할때는 역수를하고 +1을하면 음수가나옴//근데 maxval minval에서는 0을 표현해야되서 min이 1더많잖아 그래서 -1더해줘야함
        
        System.out.println(minVal);
        //1's C 1의 보수 , 2진수의 음수를 만들기 위한 값
        // 0과 1을 다 뒤짚어 주고 1을 더해주면 1's C이 된다.

        //실수의 오차발생

        System.out.println((6 - 5.9) *10); //0.99999999999999964// 1이어야 하는데 정밀도 부족
        System.out.println(Math.floor((6-5.9)*10)); //0.0 // floor는 내림연산 1미만이기에 0
                                                    // 큰 문제 발생가능// 특히 실수 6.0이 정밀도 떨어져서 더 오차 발생

        System.out.println(40/0.0); // infinity // 0으로 나누면 -0을 무한대로 하는 개념, so그 전에 막아버림
        System.out.println(40%0.0); // NaN// Not a Number 숫자가 아니다. 인피티티가 뜬다 난이뜬다 라고 얘기함
                                    // 수식에는 문제가 없지만 값에 문제가 있는 경우이기에 살펴봐야함
        System.out.println("");

        //실수형 오차는 딥러닝 같이 여러번 연산을 반복하는 경우 큰 문제가 발생하지만, 그외에 경우에는 미미해서 크게 영향은없음
        // 하지만 실수의 연산 값을 정수형으로 활용하려고 할때는 데이터 소실의 문제가 발생할 수 있다.


        // 대입 연산자
        z = x + y;
        z += 10; // z = z+10;의 줄인 형태, +자리에 4칙연산자 + %까지 가능함, 비트연산자도 가능
        z %= 2;

        // 비교 연산자
        System.out.println("비교 연산자");// 출력이 boolean
        System.out.println(10>20); // false
        System.out.println(10<20); // true
        System.out.println(10>=10); // true // 노란색 불빛은 자명해서 나옴.

        x= 10;
        y= 10;
        System.out.println(x == y); //true// 같다는 "=="
        System.out.println(x != y); //false// 다르다는 "!="

        //논리연산자 - 입출력이 모두 boolean
        // a AND b : a, b 모두 참일때 만 참
        // a OR b : a 또는 b 둘 중 하나만 참이어 도 참
        // a XOR b : a 또는 b 둥 중 하나만 참이어 야 참.(둘다 참이면 거짓)// exclusive or,베타적 or 라고함
        // NOT a : a가 참이면 거짓, 거짓이면 참

        System.out.println(10 <20 & 40 >= 2);// true // 양쪽이 모두 ture이기에 true
        System.out.println(40 < 2 | 1> 0); //true // 1>0이 참이기에 참
        System.out.println(!(10>20)); //true //false의 not은 true// 단항연산자!!
        System.out.println(10>2 ^ 5 >2); //false //^ = XOR
        System.out.println("");

        //short - circuit // 앞에만 비교해서 조건맞으면 뒤에는 검사 안한다

        System.out.println(10<20 && 4<2); // 비트단위로 비교하는 short -circuit. 연산이 더 빠르다 정도만 알자
        System.out.println(10<20 || 2<5);


        int aa = 0;
        int bb = 0;

        System.out.println(aa > 0 & bb > 0); //false
        System.out.println(aa++ > 0 & bb++ >0);
        System.out.println(aa + ","+bb); // 1,1

        aa = 0;
        bb = 0;

        System.out.println(aa++ > 0 && bb++ >0); // &&은 하나만 조건 만족하면 뒤에 것은 평가하지 않음
        System.out.println(aa + ","+bb); // 1,0 // bb++의 연산은 평가 되지 않음

        //&& -> 하나만 거짓이어도 뒤에 항은 계산 하지 않음
        //|| -? 하나만 참이어도 뒤에 항은 계산 하지 않음
        // 일관성을 위해서는 연산자 하나만쓰고 성능을 위해서는 두개를 쓰면 된다.


//        // 증감연산자 - 단항 연산자
//
//        int val = 0;
//
//        System.out.println(val++); //0 // value값을 그대로 사용한 후에 다음에 value에 +1을 해줌
//                                    // val = 0으로 먼저 Expression 평가 후에 val +=1 적용
//        // sout(val);
//        // val +=1;
//        System.out.println(++val); //2 // val +=을 먼저 계산 후에 Expression 평가
//        // val +=1;
//        //sout(val)
//        System.out.println(val--);   //2
//        // sout(val);
//        // val -=1;
//        System.out.println(--val); //1
//        // val -=1;
//        //sout(val)
//
//        val = 5;
//        int new_val = val++ * 10 + --val;
//        // 5*10
//        // val++ 로 val 이 6이됨
//        // --val 로 val 이 5가됨
//        // 5*10 +5=5
//        System.out.println(new_val); //55
//
//        //삼항 연산자
//        // (cond)?(true expression):(false expression)
//        //  boolean  값                 값
//
//        System.out.println(true?1:0);//1
//        System.out.println(false?1:0);//0
//
//        x = 10;
//        y = 13;
//
//        System.out.println(x > y ? x : y); // max function, 더 큰게 출력 되는 구조
//        System.out.println(x < y ? x : y); // min funciton, if문을 간단하게 쓰는 연산자로 자주쓰임
//
//
//        //비트 연산자
//        //정수형 연산에 사용
//
//        //shift operator
//        System.out.printf("b%32s\n", Integer.toBinaryString(8)); //1000
//        //Integer.toBinaryString(int)
//        System.out.printf("b%32s\n", Integer.toBinaryString(8>>1));//100 // >>은shift 연산자
//        //>>1 은 오른쪽으로 한칸씩 옮긴것임 1000 => 100, 2진수에서 한칸씩 내려간다는 것은 절반으로 줄어든다는 뜻
//        System.out.printf("b%32s\n", Integer.toBinaryString(7));//111
//        System.out.printf("b%32s\n", Integer.toBinaryString(7>>1)); //11 3,5가 아닌 소숫점 자른 3이 나온다
//        System.out.printf("b%32s\n", Integer.toBinaryString(1423));//10110001111
//        System.out.printf("b%32s\n", Integer.toBinaryString(1423<<2)); //1011000111100
//        System.out.printf("b%32s\n", Integer.toBinaryString(1423>>4)); //1011000
//        System.out.printf("b%32s\n", Integer.toBinaryString(1423<<2)); //1011000111100
//        System.out.printf("b%32s\n", Integer.toBinaryString(1423<<4)); //101100011110000
//        //새로 추가 되는 비트는0으로 추가됨
//
//        System.out.printf("b%32s\n", Integer.toBinaryString(-1));  //b11111111111111111111111111111111
//        System.out.printf("b%32s\n", Integer.toBinaryString(-1>>1)); //b11111111111111111111111111111111
//                                                                        // 사인비트의 영향으로 1으로채워짐//변화없음
//        System.out.printf("b%32s\n", Integer.toBinaryString(-1>>>1)); // b 1111111111111111111111111111111
//                                                                        // 사인비트보다 한칸 더채워서 0으로채워짐
//                                                                       // <<에는 <<<없지만 >>에는 >>>이 있다.
        //-1에서 오른쪽으로 >>1을 하는것은 -0.5가되는 것(비트의 왼쪽이동은 *2/ 오른쪽이동은 /2)근데 정수로 표현되야됨
        //그래서 -0.5 = -1이라는 규칙을 만듬(정수표현위해) 그래서 -1같이 사인비트까지 꽉찬상황에서는 무조건 >>>을사용
        //그러나 사인비트가 안차있는상황에서는 보통의 경우처럼 >>을 사용함
//
//        //Bitwise 논리 비트 연산자
//        System.out.printf("b%32s\n", Integer.toBinaryString(1252));
//        System.out.printf("b%32s\n", Integer.toBinaryString(15234));
//        System.out.printf("b%32s\n", Integer.toBinaryString(1252 & 15234)); //하나라도 0이면 0, 둘다 1이면 1
//        System.out.printf("b%32s\n", Integer.toBinaryString(1252 | 15234)); //하나라도 1이면 1 둘다0이면 0
//        System.out.printf("b%32s\n", Integer.toBinaryString(1252 ^ 15234)); //XOR
//        System.out.printf("b%32s\n", Integer.toBinaryString(~1252)); //not은 !아닌 ~
//
//        // &, |, ^, ~ 산술연산자가 아니라 비트연산자다.
//
//        // 비트연산자와 대입연산자
//        int intVal = 4123;
//        intVal >>= 2; // intVal = intVAll>>2; Shift 연산자도 대입연산자 가능
//        intVal |= 412; // intVal = intVal | 412; Bitwise 연산자도 대입연산자 가능
//
//        //우선순위표  우선순위가 있지만 확시한건 ()가 제일 우선순위 => 해깔리면 무조건 ()해라
//
//
//

    }
}
