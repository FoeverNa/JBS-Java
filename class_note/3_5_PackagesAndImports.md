# 패키지와 임포트 (Packages and Imports)



## 패키지 (Packages)

- Java에서 소스코드를 관리하는 방식

- 물리적으로 디렉토리로 구분된 파일을 .으로 계층적으로 구분

- 패키지이름 규칙 package 소속.프로젝트.용도 (상위계층 하위계층 최하위계층) 
  
    - ex) package com.google.dev.utils
    
- 관례적으로 회사소속이면 도메인을 거꾸로적음. come.google.dev(개발용).utils(유틸모음)    
  - ex2) package com.fastcampus.catcare.service (catcare 프로젝트에 sevice단을 구현하는중)
    
        

## 임포트(importrs)

- 다른 패키지에 선언된 클래스를 사용하기 위한 키워드 
- 지금까지 공부과정중에서도 클래스를 계속 임포트 해왔지만 우리가 그 존재를 잘 모른 이유가 있음
    - 이유 1 : java.lang.* (자바랭귀지에서 제공하는 가장 기본적 패키지, import불필요, 자동으로 임포트됨 ex)String
    - 이유 2 : auto import - IDE세팅할때 우리는 오토임포트에 체크를 했음, 그래서 클래스를 쓰면 자동으로 IMPORT가 된다



### 임포트 하는 4가지 방법

1. 패키지에 속한 모든 클래스를 임포트 : import come.exampleprojext.utils.* 

- src/com/example/projext/utils/ 아래에 있는 .java라고 되있는 모든 파일들을 임포트하겠다
       - utils/하위 폴더에 .java는 가져오지는 않음 utils폴더 안에 있는 java파일만 가져옴
- 패키지에 속한 모든 클래스를 사용하는게 아니면 권장하지 않는 방법. 메모리 효율성 떨어짐
- 참고 : *은 별표가 아닌 애스터리스크라고 부름.

2. 패키지에 속한 특정 클래스를 임포트

- import com.fastcampus.dogcare.service.WebAPI; 해당 클래스만 가져옴
- import java.io.inputStream;
- 필요한 클래스만 불러오기 때문에 메모리 효율성 높아 추천되는 방식



3. 클래스의 이름이 겹치는 경우, 패키지명을 포함하여 사용 한다

- import java.util.List;
```java
public class Foo{
main{
List list = new Litst();
java.awt.List list2 = new java.awt.List(); => 겹치는 List클래스를 쓸때마다 매번 가져와서 사용해야함
```

- 겹치는 클래스의 클래스명을 바꿀 수는 방법이 있지만 좋은 코딩은 아님... 원래이름을 모르면 다른사람이 읽을수가없음
- 그렇기 때문에 클래스 이름을 지을 때는 겹치지 않는 유니크한 이름이 좋다 




4. static 멤버는 static import하여 클래스를 생략하고 사용 가능

- 해당 클래스에 포함된 static 변수나 메서드는 클래스를 호출하지 않고 불러오기 가능하다

- import static java.lang.Math.random;(스태틱메서드를 가져오기)
- import static java.lang.System.out; (스태틱 메서드를 가져오기)

```java
public class StaticImport {
    public static void main(String args[]) {
        out.println(random());
    }
}
```
- static 메서드만 사용할 수 있지만 사실은 클래스를 다 호출하기 때문에 구지 권장하지 않는 방법임



### 정리

- 임포트 할때 권장하는 방법은 2번 3번이 권장됨, 그러나 다른 방법은 다른 사람이 사용시 읽을수 있어야되니까 기억은 해놔야한다
