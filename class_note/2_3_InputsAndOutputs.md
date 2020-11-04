# 자료의 입출력 (Inputs and Outputs)

자료의 입력과 출력을 위한 메서드에 대해 배움

## 출력메서드

- 포멧 문자열을 이용한 문자열/기본형 출력

- 객체를 직접 출력 = > toString 메서드를 재정의하여 출력방법 선택가능하다

### println()
- println은 여러가지 자료형을 입력할 수 있다.
- ln == line에 약자이고 new line을 뜻함 

```
  System.out.println("String can be printed."); //String can be printed. // 문자열도 출력가능
        System.out.println('c');//c //문자도 출력가능 
        System.out.println(40234); //40234 //정수도 출력가능
        System.out.println(1.52f); //1.52 //float도 출력가능
        System.out.println(15.2434);//15.2434 double도 출력가능
```

### printf()

- 포멧String을 먼저 입력하고 포멧 문자열에 대항하는 변수를 입력해준다
- printf는 포맷문자를 사용하여 출력함
- 자료형에 맞는 포맷문자를 사용하고 ','를 사용하여 포맷문자에 해당하는 내용을 입력함
- %s = string / %b = boolean / %d =10진수 %x=16진수, %o=8진수 / %f = floating number = double, float
 %e = 지수 / \u0 = 유니코드 / %n = new line / 등이 포맷문자가 있음
- %(숫자)d를 통해 숫자만큼의 빈칸을 확보할 수 있음
    - (숫자) 앞에 0을 붙여 남는 칸을 0으로 채울 수 있다
- 확보한 빈칸의 숫자만큼을 초과하더라도 내용은 모두 출력됨
- 실수에 자릿수 표현은 %0.0f로 표현되고 뒤에 수는 자릿수를 뜻하고 앞에수는 표현할 칸수를 뜻함
- 이외에도 시간과 날짜를 출력하는 포맷문자도 존재한다
- 포멧문자가 안들어오고 객체가들어오면 객체의 toString통해서 출력하겠다

```$xslt
System.out.printf("%s is for string\n", "STRING"); //STRING is for string //%s는 스트링자리, newline은 수동
        System.out.printf("%s %s %s\n","123","456","15234"); // 123 456 15234
        System.out.printf("%b\n", true);//true // b=boolean
        System.out.printf("%d 0x%x 0X%X 0%o \n",15,15,15,15);//15 0xf 0XF 017  //d=10진수, x= 16진수 , o=8진수
                                    //10진수와 구분 위해서 16진수에는 앞에 0x를 붙이고 8진수에는 0을붙임
        System.out.printf("%f\n",14.23);//14.230000 // f는 float 이 아닌 floating number로 float,double둘다 들어갈 수 있음
        System.out.printf("%f\n",14.23f);//14.230000// 1.442300e+01  // %f로 float형도 입력 가능
        System.out.printf("%e\n",14.423); // 지수표현
        System.out.printf("%c %c\n", 'U','\u0042');//U B //%c = char // U B 문자, 유니코드표현가능
        System.out.printf("%n"); //%n == new line

        System.out.printf("%5d.\n", 10);//   10.    //우측정렬하고 5칸확보 // .은 공간보려고 찍음
        System.out.printf("%-5d.\n", 10);//10   .   // 좌측정렬하고 5칸빈칸확보
        System.out.printf("%05d\n", 234);//00234    //5칸 앞에 0을 붙이면 빈곳은 00으로 표현됨
        System.out.printf("%3d\n", 104294);//104294 //3칸은 최소한 확보, 넘처도 출력은 다한다
        System.out.println("");


        //실수 자릿수 표현
        System.out.printf("%5.4f\n", 152523.456228);//152523.4562 // 5.4에 .4는 네자리까지 표현, 5는 최소 5칸확보하고 표현
                                                     //칸은 소숫점 끝짜리에서 시작
        System.out.printf("%5.2f\n", 1.425); // 1.43//2자리까지 표현하고 5칸 확보해야되니까 1앞에 빈칸하나채움
                                            // 표현된 자리부터 5칸을확보함
                                           
        System.out.printf("%-5.2f\n", 1.425);//1.43 //2자리까지 표현하고 뒤에 1칸 만듬

```

## 입력메서드
입력 메서드는 변수의 입력을 리터럴이 아닌 키보드로 입력을 뜻함

### Scanner 클래스
- Scanner 클래스를 생성함(import)함으로 입력메서드를 사용할 수 있음

    

- System.in 은 시스템 기본 입력의 의미로 컴퓨터는 키보드가 기본 입력장치라 키보드 입력을 받음

    - 휴대폰이면 가상키보드가 될것이고, 인형뽑기 기계면 조이스틱이될것이다 = > 시스템에 따라 다르다

- next() 메서드는 공백으로 구분된 String 을 입력 받는다 ex("String is good" => "String" "is" "Good")

    

- 3개의 입력값 중 2개만 입력하게 되면 입력을 대기하게 되는데 이것을 Blocking 메서드라고 함
  <-> non - blocking 메서드// 2개만 입력값을 받는 것이 겠지?
  //Blocking methods in Java are those methods which block the executing thread until their operation finished.
  
  
  
- nextInt() Integer 타입의 값을 입력받는 메서드
    
    - nextInt 외에도 nextDouble 과 같이 다른 기본자료형을 입력 받는 메서드도 활용 가능 하다  
    
      
    
- nextLint() \n으로 구분되는 String 을 입력받음, \n은 키보드에서는 엔터를 의미함

    

- close()는 가빕지 컬렉터 작동하기전에 메모리 낭비 방지하기 위해 해줄수 있을때 해주면좋다


```java
        System.out.println("Input methods.");
        Scanner scanner = new Scanner(System.in); // System.in은 키보드 입력을 뜻함
        //자동 import 되네요 굿

//        String s = scanner.next(); // 공백으로 구분된 String을 입력 받는다. String
//        System.out.println(s);

//        // this is string을 입력하면 공백으로 3개의 다른 입력으로 인식함
//        System.out.println(scanner.next()); //"this"
//        System.out.println(scanner.next()); //"is"
//        System.out.println(scanner.next()); //"string"

        // this is 만 입력하면 "this" "is" 출력되고 기다림 그다음 string입력하면 "string"출력됨

        //next() 메소드는 입력이 있을 때까지 기다립니다.
        //Blocking 메소드라고 부른다(<-> non-blocking 메소드 - 기다리지 않고 바로넘어가는애)

        //공백으로 구분되는 정수 입력
//        System.out.println(scanner.nextInt());
//        System.out.println(scanner.nextInt());
//        System.out.println(scanner.nextInt());
//        System.out.println(scanner.nextInt());

        // 정수 4개를 입력 받는다. 다른 자료형 입력 받으면 에러발생

        System.out.println(scanner.nextDouble());
        System.out.println(scanner.nextDouble());
        System.out.println(scanner.nextDouble());


        // \n으로 구분이 되는 String을 입력 받는다.
        // \n은 키보드에서는 엔터를 의미함

//        System.out.println(scanner.nextLine());
//        System.out.println(scanner.nextLine());
//        System.out.println(scanner.nextLine());

```



