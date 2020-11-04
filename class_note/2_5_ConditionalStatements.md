# 조건문 (Conditional Statements)

- 조건문은 조건에 따라서 코드의 실행 흐름을 결정함

## 조건문의 구성

- 조건식(Conditional Expression) , 실행문 (Execution Statement)

## 다양한 조건문

### if ~ else

- 조건식이 참일 때 수행문을 실행하고 아닐 때 else 구문을 실행하는 조건문
- if else하고 한줄만 실행할경우 중괄호를 생략할수있으나 중괄호를 하는게 좋은 습관 => 나중에 수정하거나 다른사람이 수정할경우 실수할 가능성이 생김

```java
String snum = "510225-2055132";
        char c = snum.charAt(7); //2
        System.out.println(c);


        // if (bollean) {true일때 실행문}
        if(c=='1' ) {
            System.out.println("남자 입니다.");
        }

        if(c=='1' || c == '3') {
            System.out.println("남자 입니다.");
        } else {
            System.out.println("여자 입니다.");
        }

        int x = 6; // 1 2 3 6
        if (x % 2 == 0) {
            System.out.println("짝수 입니다.");
        } else {
            System.out.println("홀수 입니다.");
        }

        System.out.println(x % 2 == 0 ? "짝수 입니다." : "홀수 입니다");

        // 둘 중에 어떤 코드가 나은 코드냐 하면 상황에 따라 다르다 라고 답할수 있음
        // 근데 나중에 코드컨벤션에 의해 코드를 줄일 때 유용할 수 있음
```

### if ~ else if

- if문이 true면 수행문을 실행하고 false면 else if 문의 조건을 따지는 여러 if문이 연결된 형태의 조건문
- 조건문에 따라 실행아 안될 수 도 있기 때문에 조건문의 순서도 중요하다


```java
int score = 2;
        char grade;

        if (score>= 9) {
            grade = 'A';
        } else if ( score >= 7) {
            grade = 'B';
        } else if ( score >= 5) {
            grade = 'C';
        } else if ( score >= 3) {
            grade = 'd';
        } else {
            grade = 'F';
        }
        System.out.println(grade);
```

### Nested if 문

- if문안에 if문이 포함되어 있는 형태 
- if문의 true일 때 수행문안에 포함된 if문의 조건식을 평가하여 수행함
- nestes가 깊어질수록 복잡도가 증가한다고 표현하고 복잡도를 낮춰주는쪽이 좋은 코딩 방향이다(필요하다면 당연히 써야함)

```java
score = 90;
        boolean isLate = true;
        // late인 경우에는 하나 낮은 그레이드를 주기로 하였다.
        if(score >= 90){
            if (isLate){
                grade = 'B';
            } else {
                grade = 'A';
            }
        } else if (score >= 80) {
            if (isLate){
                grade = 'C';
            } else {
                grade = 'B';
            }
        } else if (score >= 70) {
            if (isLate){
                grade = 'D';
            } else {
                grade = 'C';
            }
        } else if (score >= 60) {
            if (isLate){
                grade = 'F';
            } else {
                grade = 'D';
            }
        } else{
             grade = 'F';
        }
        System.out.println(grade);
```

###  switch ~ case

- 다른 조건문과는 다르게 조건식이 아닌 조건 값을 평가함.
  - boolean값이 아니다
- case (값) 하고 (값)과 조건 값과 같으면 : 이후에 수행문이 실행됨
- 수행문 다음에 break;를 사용해 조건문을 빠져나오지 않으면 아래의 case의 수행문이 모두 수행되니 주의
- 위와 같은 상황을 fall-through라고하고 일부러 case를 붙여서 사용해  fall- through하기도 한다
- 실제로 사용하는것은 Enumeration과 함께 사용된다
  - case에는 열거형상수가 온다
  - 조건값에는 enum객체가 온다


```java
        switch(grade) {
            case 'A':
                System.out.println("휼륭합니다!"); // fall - through//라고 주석해주어야함

            case 'B':
                System.out.println("멋집니다!");
                break;
            case 'C':
            case 'D':
                System.out.println("많이 노력하세요!");
                break;
            default :
                System.out.println("다음엔 잘하세요.");
        }
```