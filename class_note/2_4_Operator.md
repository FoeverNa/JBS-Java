# 연산자 (Operators)



## 연산의 구성

- 연산은 연산자(Operator), 피연산자(Operand), 연산식(Expression)으로 구성됨
  
  - 연산자는 일항, 이항, 삼항 연산자로 구분됨
- 피연산자의 수에 따라 단한 연산자 이항연산자 삼항연산자로 구분할 수 있다
- 연산자사이에는 우선순위가 있어서 연산자의 위치에 상관없이 우선순위에 따라 연산된다
  - 괄호가 가장 높기 때문에 확실치 않을때는 그냥 괄호를 사용해주면 된다 = > 다른사람이 해깔릴거같을때도 괄호써라
    
    - 무슨 뜻인지 잘알아볼수있는 코드가 이쁜코드다 
    
      

```java
        int x = 10, y = 20, z;

        z = x + y; // +: 연산자(동사), x:피연산자, y:피연산자, x+y:연산식(expression)
                    // 항이 2개있는 연산자 = 이항 연산자, 항=피연산자
                    // =:연산자, z:피연산자, x+y : 피연산자(연산식이 피연산자가됨)
                    //연산자들간에 우선순위가 있기 때문에 =이 앞에 있지만 가장 늦게 연산된다
        System.out.println(z); //30
```



## 정수와 실수의 사칙연산

- 사칙연산자는 + - * / 와 나머지를 구하는 %(modulus) 연산자로 구성된다
    
    - The modulus operator returns the remainder of the two numbers after division
- 정수의 나눗셈은 몫만 출력되고 실수는 나눗셈 값이 출력됨
    
    - 나머지를 나누어서 나오는 값은 실수 이기 때문에 실수를 표현할 수 없는 정수는 몫만 출력한다(로 이래했음)
- %연산자로 정수 뿐만 아니라 '실수'의 나머지도 구할 수 있다
- 정수는 Int로(long만 long으로 ) 변환해서 연산하고 실수는 double로 변환해서 연산한다
- 정수와 실수의 사칙연산에서는 정수가 실수로 변환되어서 연산하게 된다
    
    - double 로 변환되어서 연산하게 된다
    
      

```java
        System.out.println("정수형 사칙 연산");
        System.out.println(20 -5); // 15
        System.out.println(5-20); // -15
        System.out.println(10 * 662); //6620
        System.out.println(150 / 8); //18, 정수의 나눗셈은 몫만 나옴, 사라지는 수는 &로 구함
        System.out.println(150 % 8); //6 // modulus,(모듈러스연산) 나머지 구하는 연산자

        System.out.println("실수형 사칙 연산");// 
        System.out.println(10.0 + 52.3); // 62.3
        System.out.println(10.5f + 12.3);//22.8 //float이 double로 자동으로 변환되서 계산됨
        System.out.println(10.4 - 50);//-39.6 // 정수와 실수의 계산에서는 실수형으로 변환후 연산
        System.out.println(10.2 * 4.2);//42.839999
        System.out.println(150 / 8.0); //18.75, 정수/실수이기에 실수로 변환 되서 몫이 아닌 실수값이 나옴
        System.out.println(5.2 / 1.2); //4.333333333333334// 몫이 아닌 나머지 까지 나눈 실수값
        System.out.println(5.2 % 1.2);// 0.40000000000000036 // 실수도 modulus 연산으로 나머지 계산 가능
                                        //0.4가 나와야되는데 소숫점 조금 더 나오는데 그건 부동소수점 오차
        System.out.println("");
```



### 사칙연산시 주의 사항

- 사칙 연산의 결과가 overflow되서 전혀 다른 값이 출력 될 수 있다
- 실수의 사칙연산의 결과는 미세한 오차가 발생 할 수 있다
    - 실수형 오차는 딥러닝 같이 여러번 연산을 반복하는 경우 큰 문제가 발생하지만, 그외에 경우에는 미미해서 크게 영향은 없다
    - 하지만 실수의 연산 값을 정수형으로 활용하려고 형변환 할때는 데이터 소실의 문제가 발생할 수 있기 때문에 유의해야 한다
      - 항상 좋지 않은 결과가 나오기때문에 정수를 원한다면 정수로만 연산해야 한다
- 계산식은 오류가 안나지만 값에 따라 Infinity, NAN이 뜨기도 한다
    
    - 0으로 나누면 Infinity가 나온다
    
      


```java
        System.out.println("사칙연산 주의사항");
        //overflow가 있음
        System.out.println(Integer.MAX_VALUE /2 *3); //-1073741827,값이 overflow되버림
        System.out.println(Integer.MAX_VALUE); //2147483647 //최대값
        System.out.println(Integer.MAX_VALUE+1); //-2147483648 // 최대값+1 = 최소값
        System.out.println(Integer.MIN_VALUE); //-2147483648 // 최소값
        System.out.println(Integer.MIN_VALUE-1); //2147483647 // 최소값 -1 = 최대값

        //참고 : 최대값 +1이 최소값이 되는 이유

        int maxVal = 0b01111111111111111111111111111111; // 최대값에서 +1을 하면 맨끝짜리부터 값이 올라가서 00000하다가 맨끝에서1이됨
        int maxVal1= 0b10000000000000000000000000000000; // = Integer.MIN_VALUE
        // Integer.MAX_VALUE에 2의 보수를 구하면 0b10000000000000000000000000000001
        // 근데 Integer에 표현범위는 양수가 하나 작음, 그래서 min Val이 하나 더 많아야되는데 음수니까 +1이 아니라 -1을 해줘야됨
        // 그래서 -1을 하면 int minVal = 0b10000000000000000000000000000000;이 나오게 된다


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
```



## 대입 연산자 (Assignment Operators)

- 변수의 값을 대입 하는 연산자로 '='으로 표현하고 수학의 같다 와는 다른 의미

- 대입연산자 앞에 다른 연산자를 붙여 연산식을 줄여서 표현할 수 있음

  

```java
        // 대입 연산자
        z = x + y;
        z += 10; // z = z+10;의 줄인 형태, +자리에 4칙연산자 + %까지 가능함, 비트연산자도 가능
        z %= 2;
```



## 비교 연산자 (Comparison Operators)

- 비교 연산자는 두개의 항을 비교할때 사용하는 연산자로 그 출력값은 boolean 타입(true / false)이다

- '=='는 같다, '!=' 같지 않다(not) 외에 부등호가 사용된다

  
```java
        System.out.println("비교 연산자");// 출력이 boolean
        System.out.println(10>20); // false
        System.out.println(10<20); // true
        System.out.println(10>=10); // true // 노란색 불빛은 자명해서 나옴.

        x= 10;
        y= 10;
        System.out.println(x == y); //true// 같다는 "=="
        System.out.println(x != y); //false// 다르다는 "!="
```



## 논리 연산자 (Logical Operators)

- 논리연산자는 입력과 출력이 모두 boolean 인 연산자
- a AND b : a, b 모두 참일때 만 참 &
- a OR b : a 또는 b 둘 중 하나만 참이어 도 참 |
- a XOR b : a 또는 b 둘 중 하나만 참이어 야 참.(둘다 참이면 거짓)// exclusive or,베타적 or 라고한다 ^
    
    - 하나가 false고 하나가 true여야한다
- NOT a : a가 참이면 거짓, 거짓이면 참 ! (단항 연산자)
- 논리 연산자를 2개 사용하는 것을 short-circuit 이라 하고 더 빠르게 연산하게 된다. ex) &&, ||
    
    - 앞에 수식이 조건을 만족하면 뒤에 수식은 평가 하지 않는다
- Shor - circuit은 한쪽만 만족하면 다른쪽의 연산식을 연산하지 않고 넘기기 때문에 연산속도를 줄일  수 있다.
    
    - 비교식이 연산이 오래걸리는 경우 그 차이가 더 클 수 있다
    
      

```java
        System.out.println(10 <20 & 40 >= 2);// true // 양쪽이 모두 ture이기에 true
        System.out.println(40 < 2 | 1> 0); //true // 1>0이 참이기에 참
        System.out.println(!(10>20)); //true //false의 not은 true// 단항연산자!!
        System.out.println(10>2 ^ 5 >2); //false //^ = XOR
        System.out.println("");

        //short - circuit // 앞에만 비교해서 조건맞으면 뒤에는 검사 안한다

        System.out.println(10<20 && 4<2); // 비트단위로 비교하는 short -circuit. 연산이 더 빠르다정도만 알자
        System.out.println(10<20 || 2<5);
```



## 증감 연산자 (Increment and Decrement Operators)

- 증감 연산자는 단항 연산자로 값을 증가시키거나 감소시킨다

- 증감 연산자가 값의 앞에 붙으면 증감 먼저 반영하고 Expression 평가함

- 반대로 증감 연산자가 값의 뒤에 붙으면 Expression 평가하고 증갑을 반영함

```java
       int val = 0;

        System.out.println(val++); //0 // value값을 그대로 사용한 후에 다음에 value에 +1을 해줌
                                    // val = 0으로 먼저 Expression 평가 후에 val +=1 적용
        // sout(val);
        // val +=1; 
        System.out.println(++val); //2 // val +=을 먼저 계산 후에 Expression 평가
        // val +=1;
        //sout(val)
        System.out.println(val--);   //2
        // sout(val);
        // val -=1;
        System.out.println(--val); //1
        // val -=1;
        //sout(val)

        val = 5;
        int new_val = val++ * 10 + --val; 	
        // 5*10
        // val++ 로 val 이 6이됨
        // --val 로 val 이 5가됨
        // 5*10 +5=5
        System.out.println(new_val); //55
```



## 삼항 연산자 (Ternary Operator)

- 삼항 연산자는 앞의 조건이 ture이면 A를 출력하고 false면 B를 출력하는 간단한 조건문
- (cond)?(true expression):(false expression)의 형태로 구성됨
  - condition은 boolean값이어야 한다

```java
        System.out.println(true?1:0);//1
        System.out.println(false?1:0);//0

        x = 10;
        y = 13;

        System.out.println(x > y ? x : y); // max function, 더 큰게 출력 되는 구조
        System.out.println(x < y ? x : y); // min funciton, if문을 간단하게 쓰는 연산자로 자주쓰임
```



## 비트 연산자 (Bitwise Operator)

### 논리 비트 연산자

- &, |, ^, ~ 가 비트 연산자로 사용됨
- 2개의 정수의 비트를 비교해서 연삼함
- & 2개의 비트를 비교해 하나라도 0이면 0을 도출
- | 2개의 비트를 비교해 하나라도 1이면 1을 도출
- ~ 2개의 비트를 비교해 둘중 하나만 1이면 1을 도출, 둘다 1이면 0 도출
- ^ 2개의 비트를 비교해 하나라도 0이면 0을 도출

```java
        System.out.printf("b%32s\n", Integer.toBinaryString(1252)); //b                     10011100100
        System.out.printf("b%32s\n", Integer.toBinaryString(15234));//b                  11101110000010
        System.out.printf("b%32s\n", Integer.toBinaryString(1252 & 15234));//b                 10000000
                                                                            //AND, 하나라도 0이면 0, 둘다 1이면 1
        System.out.printf("b%32s\n", Integer.toBinaryString(1252 | 15234));//b           11111111100110
                                                                            //OR, 하나라도 1이면 1 둘다0이면 0
        System.out.printf("b%32s\n", Integer.toBinaryString(1252 ^ 15234));//b           11111101100110
                                                                           //XOR, 둘중 하나만 1일때 1, 나머지 0
        System.out.printf("b%32s\n", Integer.toBinaryString(~1252)); //b11111111111111111111101100011011
                                                                   //not은 !아닌 ~, 단항연산자, 1=>0, 0=>1 도출
```



### 이동 비트 연산자 (Shift Operator)

- 정수형 2진수 연산에 사용
- <<, >>, >>>, 으로 좌측 피연산자의 각 비트를 연산자 방향대로 우측 피연사자만큼 이동시킴
    - <<을 1번하면 2를 곱하는 효과, >>(>>>)을 1번하면 2를 나누는 효과가 있다
    - << 2 하면 2칸을 이동 시킨다(2의 제곱을 곱하거나 나눈효과)
    - 이동시킬 때 생기는 빈자리는 0으로 채우게 된다
- '-1'의 경우 >>을 하면 2를 나누는데 -0.5가된다. 그런데 2진수는 정수이기 때문에 -0.5를 반올림해서 다시 -1이 된다.
    - 그래서 >>을 하면 값이 변화가 없게 되기 때문에 >>>란 기호를 사용해야 원하는 결과를 출력할 수 있다.
        - -0.5를 내림 하지 않고 반올림 하는것은 자바의 규칙.. 
        - -1외에 다른 수는 >>을 사용하면 된다

```java
        System.out.printf("b%32s\n", Integer.toBinaryString(8)); //1000
        //Integer.toBinaryString(int)
        System.out.printf("b%32s\n", Integer.toBinaryString(8>>1));//100 // >>은shift 연산자
        //>>1 은 오른쪽으로 한칸씩 옮긴것임 1000 => 100, 2진수에서 한칸씩 내려간다는 것은 절반으로 줄어든다는 뜻
        System.out.printf("b%32s\n", Integer.toBinaryString(7));//111
        System.out.printf("b%32s\n", Integer.toBinaryString(7>>1)); //11 3,5가 아닌 소숫점 자른 3이 나온다
        System.out.printf("b%32s\n", Integer.toBinaryString(1423));//10110001111
        System.out.printf("b%32s\n", Integer.toBinaryString(1423<<2)); //1011000111100
        System.out.printf("b%32s\n", Integer.toBinaryString(1423>>4)); //1011000
        System.out.printf("b%32s\n", Integer.toBinaryString(1423<<2)); //1011000111100
        System.out.printf("b%32s\n", Integer.toBinaryString(1423<<4)); //101100011110000
        //새로 추가 되는 비트는0으로 추가됨

        System.out.printf("b%32s\n", Integer.toBinaryString(-1));  //b11111111111111111111111111111111
        System.out.printf("b%32s\n", Integer.toBinaryString(-1>>1)); //b11111111111111111111111111111111 
                                                                        // 사인비트의 영향으로 1으로채워짐//변화없음
        System.out.printf("b%32s\n", Integer.toBinaryString(-1>>>1)); // b 1111111111111111111111111111111
                                                                        // 사인비트보다 한칸 더채워서 0으로채워짐
                                                                       // <<에는 <<<없지만 >>에는 >>>이 있다.
```

#### 참고 비트연산자와 대입연산자

- 논리비트연산자와 이동 비트 연산자도 대입연산자와 함께 사용이 가능하다

```java
        int intVal = 4123;
        intVal >>= 2; // intVal = intVAll>>2; Shift 연산자도 대입연산자 가능
        intVal |= 412; // intVal = intVal | 412; Bitwise 연산자도 대입연산자 가능
```




