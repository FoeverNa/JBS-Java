# Map 인터페이스

- kye-value pair의 객체를 관리하는데 필요한 메서드가 정의 됨

- key는 중복 될 수 없음

  - value는 중복되도 괜찮다

- 검색을 위한 자료 구조

- key를 이용하여 값을 저장하거나 검색, 삭제 할때 사용하면 편리함

  - 내부적으로는 hash 방식으로 구현됨

- ```java
  index = hash(key)
  ```

- key가 되는 객체는 객체의 유일성함의 여부를 알기 위해 equals()와 hashCode()메서드를 재정의 함

  - Set인터페이스와 비슷하다

- HashMap 클래스를 가장 많이 사용한다



## HashMap 클래스

- Map 인터페이스를 구현한 클래스 중 가장 일반적으로 사용한는 클래스

- HashTable 클래스는 자바2 부터 제공된 클래스로 Vector 처럼 동기화를 제공 한다

  - 예전에 많이쓴 클래스고 이젠 HashMap 쓰면된다

- pair자료를 쉽고 빠르게 관리할 수 있다

  

### 예제

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
```

- 실습을 위해 기존에 사용하던 Member클래스를 가져옵니다

```java
import java.util.HashMap;
import java.util.Iterator;

public class MemberHashMap {

    private HashMap<Integer, Member> hashMap;

    public MemberHashMap(){
        hashMap = new HashMap<Integer, Member>();
    }

    public void addMember(Member member){
        hashMap.put(member.getMemberId(), member);
    }

    public boolean removeMember(int memberId){
        if (hashMap.containsKey(memberId)){
            hashMap.remove(memberId); // key 값으로 지울수 있음
            return true;
        }
        System.out.println("회원 번호가 없습니다");
        return false;
    }

    public void showAllMember(){

        Iterator<Integer> ir = hashMap.keySet().iterator(); //모든 key를 set으로 반환해줌
//          hashMap.values().iterator(); // 모든 val을 colletion으로 반환해줌
         while(ir.hasNext()){
             int key = ir.next();
             Member member = hashMap.get(key);
             System.out.println(member);
         }
        System.out.println("");
    }
}
```

- addMember()메서드 에서는 hashMap에 add()메서드를 통해 key값과 value를 넣어주어 자료를 추가합니다
- removeMember()메서드에서는 containsKey()메서드를 통해 해당 key가 존재하는지 체크하고 존재한다면 remove(key)메서드를 통해 key로 접근해서 요소를 제거합니다
- showAllMember()메서드 에서는 keySet()메서드나 vlaues()메서드를 통해 요소들을 Iterator 객체에 담을 수 있는데 우리는 keySet()을 통해 key를 담고 그키를 담은 iterator를 순회해서 출력하는 방식으로 전체 요소들을 출력합니다
  - values()를 이용해 바로 값을 출력하는 것도 가능합니다

```java

public class MemberHashMapTest {
    public static void main(String[] args) {

        MemberHashMap manager = new MemberHashMap();

        Member memberLee = new Member(200, "Lee");
        Member memberKim = new Member(300, "Kim");
        Member memberPark = new Member(100, "Park");
        Member memberPark2 = new Member(100, "Park");

        manager.addMember(memberLee);
        manager.addMember(memberKim);
        manager.addMember(memberPark);
        manager.addMember(memberPark2);// 키값이 Integer로 되어있기 때문에 중복값무시

        manager.showAllMember();
        //Park회원님의 아이디는 100입니다.
        //Lee회원님의 아이디는 200입니다.
        //Kim회원님의 아이디는 300입니다.

        manager.removeMember(200);
        manager.showAllMember();

        //Park회원님의 아이디는 100입니다.
        //Kim회원님의 아이디는 300입니다.
    }
}

```

- 키값이 Integer이기 때문에 Hascode와 equals메서드 재정의 안해도 알아서 중복값을 처리해 준다
  - 객체를 사용하는 경우 key값으로 사용하는 클래스에서 재정의해주어야 한다



## TreeMap 클래스

- key 객체를 정렬하여 key-value를 pair로 관리하는 클래스
- key 사용되는 클래스에 Comparable, Comparator 인터페이스를 구현한다
- java에 많은 클래스들은 이미 Comparable이 구현되어 있다
- 구현 된 클래스를 key로 사용하는 경우는 구현할 필요 없음
  - 구현된 클래스로 key를 사용하면 Comparable을 구현할 필요 없다
  - Integer, String같은 클래스는 이미 Comparable구현되어 있다

### 예제

다른 내용은 Hashmap 예제와 동일 하기 때문에 Test클래스만 가져 왔다.

```java
package Colletion.treemap;

public class MemberTreeMapTest {
    public static void main(String[] args) {

        MemberTreeMap manager = new MemberTreeMap();

        Member memberLee = new Member(100, "Lee");
        Member memberKim = new Member(200, "Kim");
        Member memberPark = new Member(300, "Park");
        Member memberPark2 = new Member(300, "Park");

        manager.addMember(memberLee);
        manager.addMember(memberKim);
        manager.addMember(memberPark);
        manager.addMember(memberPark2);
        manager.showAllMember();
        //Lee회원님의 아이디는 100입니다.
        //Kim회원님의 아이디는 200입니다.
        //Park회원님의 아이디는 300입니다.

        manager.removeMember(200);
        manager.showAllMember();
        //Lee회원님의 아이디는 100입니다.
        //Park회원님의 아이디는 300입니다.
  
    }
}
```

- 결가값을 보면 Comparable이 구현되어 있는 Integer클래스를 사용했기 때문에 정렬이 잘되어있는 것을 알 수 있다
  - 다른 객체를 사용하면 해당 객체에 Comparable을 구현해주어야 한다






