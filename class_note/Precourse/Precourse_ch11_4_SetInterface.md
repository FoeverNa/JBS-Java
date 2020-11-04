# Set 인터페이스



## Iterator 로 순회하기

- Colletion 의 개체를 순회하는 인터페이스

- iterator()메서드 호출

  ```java
  Iterator ir = memberArrayList.iterator();
  ```

- Iterator에 선언된 메서드

  - hasnext() 이후에 요소가 더 있는지 확인하는 메서드, 요소가 있다면 true 반환
  - next() 다음에 있는 요소를 반환

- 예제



## Set 인터페이스

- Colletion 하위의 인터페이스

- 중복을 허용하지 않음

- List는 순서기반의 인터페이스지만, Set은 순서가 없음

- get(i)는 메서드가 제공되지 않음(Itrator로 순회)

- 저장된 순서와 출력순서는 다를 수 있음

- 아이디, 주민번호, 사번 등 유일한 값이나 객체를 관리할 때 사용

- HashSet, TreeSet 클래스

  ​	

### HashSet 클래스

- Set 클래스를 구현한 클래스

- 중복을 허용하지 않으므로 저장되는 객체의 동일함 여부를 알기 위해 equals()와 hashCode()메서드를 재정의 해야 한다

  

#### HashSet과 Iterator 예제

```java
import java.util.HashSet;
import java.util.Iterator;

public class HashSetTest {
    public static void main(String[] args) {
        HashSet<String> set = new HashSet<>();// set인터페이스를 구현한 hashSet클래스
        set.add("이순신");
        set.add("김유신");
        set.add("유관순");
        set.add("김홍도");
        set.add("강감찬");
        set.add("이순신"); // 중복값은 입력이 안됨

        System.out.println(set); // toString();
                                //[김유신, 강감찬, 이순신, 유관순, 김홍도]// 순서는 순차적이 아님

        Iterator<String> ir = set.iterator(); // Colletino 인터페이스 구현한 클래스는 다숑ㅇ가능


        while(ir.hasNext()){ // hsNext는 다음 자료가 있는지 체크
           String str = ir.next();// next는 다음자료를 담는역할
            System.out.println(str);
        }

    }
}
```

- HashSet은 순차적으로 입력이 되는 것이 아닌 hash코드를 통해 정렬이 되기 때문에 입력 순서와는 상관업싱 결과가 출력된다
- 중복된 입력 값  "이순신"은 중복값을 허용하지 않는 Set인터페이스의 따라 무시되고 하나의 값만 출력되게 된다
  - String과 같이 이미 존재하는 자료형은 따로 hashCdoe()와 equals()메서드를 재정의 하지 않아도 중복값을 찾아낼 수 있다.
- 요소 하나하나에 접근하기 위해 Iterator 메서드를 통하여 객체를 생성하여 변수 ir에 답고 해당 ir을 while문을 통해 hasNext가 true일때까지 next를해서 출력하면 요소 하나하나 값을 출력할 수 있다.

#### HashSet 예제2

```java
public class Member {

    private int memberId;
    private String memberName;

    public Member() {}
    public Member(int memberId, String memberName){
        this.memberId = memberId;
        this.memberName = memberName;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String toString(){
        return memberName + "회원님의 아이디는 " + memberId + "입니다.";
    }
}
    @Override
    public int hashCode() {
        return memberId;// intger값하면 반환하면되는데 memberId로 구분할거니까 바로 리턴
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Member){
            Member member = (Member)obj;
            return this.memberId == member.memberId;
        }

        return false;
    }
```

- 먼저 자료구조에 입력될 객체를 만들기 위해 member 클래스를 작성한다

- toStirng() 메서드를 재정의하여 Member name과 Id를 출력하게 한다

- hashCode() 메서드를 재정의하여 구분하는 값을 memberId를 사용한다

- equals() 메서드를 재정의하여 입력된 객체가 Member 객체가 맞는지 확인하여 맞으면 memberId가 서로 같으면 같은 객체라고 취급한다

  - hasCode()와 equals()메서드는 객체를 자료구조에 넣을때 중복을 입력을 방지하기 위해 재정의 해주어야 하는 메서드이다.

  

```java
import java.util.HashSet;
import java.util.Iterator;

public class MemberHashSet {

       private HashSet<Member> hashSet;

        public MemberHashSet(){
            hashSet = new HashSet<Member>();
        }

        public void addMember(Member member){
            hashSet.add(member);
        }

        public boolean removeMember(int memberId){

            Iterator<Member> ir = hashSet.iterator();
            while(ir.hasNext()){
                Member member = ir.next();
                if(member.getMemberId() == memberId){
                    hashSet.remove(member);
                    return true;
                }
            }

            System.out.println(memberId + "번호가 존재하지 않습니다");
            return false;
        }


        public void showAllmember(){
            for(Member member : hashSet){
                System.out.println(member);
            }
            System.out.println("");
        }
}
```

- HashSet을 선언하고 타입을 <Member>로 하여 Member 객체를 활용할 수 있도록 한다

- 요소를 제거할 수 있는 removeMember()메서드를 작성하고 Iterator를 활용하여 요소 하나 하나에 접근하여 입력한 memberId와 동일하다면 remove한다.

  - 만약 입력한 memberId와 동일한 memberId가 없다면 번호가 존재하지 않는다고 출력해준다

- showAllmember() 메서드를 작성하고 향상된 for문을 통해 hashSet에 접근하면 iterator를 통해 접근한것과 같이 하나하나의 요소에 접근할 수 있다

  

```java
public class MemberHashSetTest {
    public static void main(String[] args) {

        MemberHashSet manager = new MemberHashSet();

        Member memberLee = new Member(100, "Lee");
        Member memberKim = new Member(200, "Kim");
        Member memberPark = new Member(300, "Park");
        Member memberPark2 = new Member(300, "Park");

        manager.addMember(memberLee);
        manager.addMember(memberKim);
        manager.addMember(memberPark);
        manager.addMember(memberPark2);// equals와 hashcode 오버라이딩 하니 자동으로 안들어감..wow

        manager.showAllmember();
       // Lee회원님의 아이디는 100입니다.
       // Kim회원님의 아이디는 200입니다.
       // Park회원님의 아이디는 300입니다.

    }
}
```

- member 객체 4개를 생성하고 각각 addMember를 하고 showAllmember를 하면 아래의 결과 값처럼 중복값은 무시되고 나머지 객체가 잘 입력됬음을 확인할 수 있다.
  -  Member 클래스에서 hashCode()메서드와 equals()메서드를 재정의 하지 않으면 객체의 중복값은 구분하지 못하고 함께 입력됫을 것이다



### TreeSet 클래스

- 객체의 정렬에 사용되는 클래스
  - tree가 붙으면 정렬을 위해서 사용됨
- 중복을 허용하지 않으면서 오름차순이나 내림차순으로 객체를 정렬 함
- 내부적으로 이진 검색 트리(binary search tree)로 구현되어 있음
  - 객체 비교를 위해 Comparable이나 Comparator 인터페이스를 구현 해야 함
- 이진 검색 트리에 자료가 저장 될 때 비교하여 저장될 위치를 정함

#### TrreSet 예제 1

```java
import java.util.TreeSet;

public class TreeSetTest {
    public static void main(String[] args) {

        TreeSet<String> treeSet = new TreeSet<String>();

        treeSet.add("홍길동");
        treeSet.add("강감찬");
        treeSet.add("이순신");

        for(String str : treeSet){
            System.out.println(str);  // 강감찬 이순신 홍길동// 오름찬순되어있음
                                    //String에 이미 Comparable 구현되어잇음
        }
    }

}
```

- String 자료를 TreeSet 자료구조에 넣고 출력을 하니 오름차순으로 자동으로 정렬이 된다

  - 이미 String 내부에서 Comparable인터페이스를 implements했기 때문에 그렇다

- 하지만 보통 참조자료형의 경우 Comparable이 구현되어 있지 않기 때문에 따로 구현해주어야 할 필요성이 있는데 예제 2를 통해 구현에 예제2를 살펴보겠다

  

#### TrreSet 예제 2

```java
package Colletion.treeset;

public class Member implements Comparable<Member> {

    private int memberId;
    private String memberName;

    public Member() {}
    public Member(int memberId, String memberName){
        this.memberId = memberId;
        this.memberName = memberName;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String toString(){
        return memberName + "회원님의 아이디는 " + memberId + "입니다.";
    }

    @Override
    public int hashCode() {
        return memberId;// intger값하면 반환하면되는데 memberId로 구분할거니까 바로 리턴
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Member){
            Member member = (Member)obj;
            return this.memberId == member.memberId;
        }

        return false;
    }

    @Override
    public int compareTo(Member member) {

        return this.memberId - member.memberId ;
    }

//    @Override
//    public int compareTo(Member member) {
//
//        return this.memberName.compareTo(member.getMemberName());
//    }
}
```

- 자료의 정렬을 위해 Member클래스에 Comparable <Member>를 구현합니다.

  - compareTo메서드를 재정의해야 하는데 memberId를 기준으로 삼고 싶다면 this.memberId에서 파라미터의 memberId를 빼준값을 출력합니다.
    - 만약 이값이 양수라면 오름차순으로 정렬을 하고 음수라면 내림차순을 합니다.
  - memberName으로 구분하고 싶다면 String은 이미 구현이 되어있기 때문에 바로 compareTo를 사용해주면 된다

- 또한 Comparator를 구현해서 사용하는 방법도 있다

  ```java
  package Colletion.treeset;
  
  import java.util.Comparator;
  
  public class Member implements Comparator<Member> {
  
      private int memberId;
      private String memberName;
  
      public Member() {}
      public Member(int memberId, String memberName){
          this.memberId = memberId;
          this.memberName = memberName;
      }
  
      public int getMemberId() {
          return memberId;
      }
  
      public void setMemberId(int memberId) {
          this.memberId = memberId;
      }
  
      public String getMemberName() {
          return memberName;
      }
  
      public void setMemberName(String memberName) {
          this.memberName = memberName;
      }
  
      public String toString(){
          return memberName + "회원님의 아이디는 " + memberId + "입니다.";
      }
  
      @Override
      public int hashCode() {
          return memberId;// intger값하면 반환하면되는데 memberId로 구분할거니까 바로 리턴
      }
  
      @Override
      public boolean equals(Object obj) {
          if(obj instanceof Member){
              Member member = (Member)obj;
              return this.memberId == member.memberId;
          }
  
          return false;
      }
  
      @Override
      public int compare(Member member1, Member member2) {
          return member1.memberId - member2.memberId;
      }
  
  
  }
  ```
  - 이때는 copare 메서드를 제정의하게 되는데 member1은 this를 뜻하고 member 2는 입력되는 객체를 뜻한다
    - 이 또한 copareTo와 같이 두 객체의 변수를 비교하여 사용하면 된다
  - 또한 compareTo를 사용할 때는 treeSet의 객체를 생성할 때 입력파라미터에 해당 객체를 생성해주어야 한다( new Member())

```java
       public MemberTreeSet(){
          TreeSet<Member>  treeSet = new TreeSet<Member>(new Member());
        }
```

```java
package Colletion.treeset;

public class MemberTreeSetTest {
    public static void main(String[] args) {

        MemberTreeSet manager = new MemberTreeSet();

        Member memberLee = new Member(300, "Lee");
        Member memberKim = new Member(100, "Kim");
        Member memberPark = new Member(200, "Park");

        manager.addMember(memberLee);
        manager.addMember(memberKim);
        manager.addMember(memberPark);

        manager.showAllmember();
         //Kim회원님의 아이디는 100입니다.
        //Park회원님의 아이디는 200입니다.
        //Lee회원님의 아이디는 300입니다.
    }
}
```

- 위와 같이 MemberId를 기준으로 정렬된 것을 확인할 수 있다

#### Compare 인터페이스와 Comparator 인터페이스

- 정렬 대상( 자료역할 하는 클래스)이 되는 클래스가 구현해야 하는 인터페이스

- Comparable은 compareTo()메서드를 구현

  - 매개변수와 자신(this)를 비교한다

- Comparator는 compare()메서드를 구현

  - 두 개의 매개 변수를 비교한다

  - TreeSet 생성자에 Comparator가 구현된 객체를 매개변수로 전달해야 한다

  - ```java
              TreeSet<Member>  treeSet = new TreeSet<Member>(new Member());
    ```

- 일반적으로는 Comparable을 많이 사용한다

  - 이미 Comparable을 구현한 경우 Comparator를 이용하여 다른 정렬 방식을 정의 할 수 있다

    

  - ```java
    import java.util.Comparator;
    import java.util.TreeSet;
    
    class Mycompare implements Comparator<String>{
    
        @Override
        public int compare(String s1, String s2) {
            return s1.compareTo(s2)*(-1);
        }
    }
    
    public class ComparatorTest {
        public static void main(String[] args) {
    
            TreeSet<String> treeSet = new TreeSet<String>(new Mycompare());
    
            treeSet.add("홍길동");
            treeSet.add("강감찬");
            treeSet.add("이순신");
    
            for (String str : treeSet) {
                System.out.println(str); 
            }
        }
    }
    ```

    - 이미 Comparable이 구현되어있을때 Mycompare라는 클래스를 만들어 Comparator를 구현하여 해당 클래스의 객체를 TreeSet 생성자에 매개변수로 입력하여 새로운 정렬기준을 구현할 수 있다

