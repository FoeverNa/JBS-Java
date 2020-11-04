package ch02.p09;

/**
 * 문자열 (String)
 *
 * 문자열은 내부적으로 '클래스'로 구성되어 있다.
 * 내부에는 문자 배열로 된 데이터가 있다. char[]
 * 한번 만든 문자열은 변하지 않는다. (Immutable)
 *
 * 문자열 편집은 String을 쓰지 않고 Builder나 Buffer 등을 사용한다.
 */
public class Main {

    public static void main(String[] args) {

        //문자열 생성
        String s1 = "문자열 생성 방법";
        String s2 = new String("문자열 생성 방법2"); // 클래스 생성자. 권장하지 않음

        String s3 = "abcde"; // 처음 상수풀에 생성됨
        String s4 = "abcde"; // 상수풀에 동일한 문자열이 있으면 그것을 참조하고 없으면 생성함
        String s5 = new String("abcde"); // 흙빛이 되어버림.. 구지 생성한다고?

        System.out.println(s3 == s4); //true // 문자열을 곧바로 생성할 경우 상수 풀에서 찾아 사용
                                            // String은 immuntable하기에 같은값을 참조해도 문제가 발생하지 않음
        System.out.println(s3 == s5); //false // 문자열을 클래스로 생성할 경우 새로운 값을 생성

        System.out.println(s3.equals(s4));//true // equals는 무슨 메서드였지?
        System.out.println(s3.equals(s5));//true


        // String 메서드

        String s = "This is a string.";
        System.out.println(s.length()); //16 //length 같이 이름만들어도 기능이 예상되는게 잘지은 이름이다.
                                        //이름을 짓기 어려운 기능은 메서드로 만들지 않는 것이 좋다.
        System.out.println(s.charAt(2));//i
        System.out.println(s.indexOf('a'));//8 // a가 몇번 인덱스에 위치하는가?
        System.out.println(s.equalsIgnoreCase("this Is A STRiNg."));//true//문자열비교해서 같은면true
                                                                                // 대소문자 구분x
        System.out.println(s.replace('i','t'));//Thts ts a strtng.//기존의 String을 바꾸는것은 아님
                                                            //변경된 String을 생성해서 출력하는 것
        System.out.println(s);//This is a string.// 기존 내용 유지
        System.out.println(s.substring(3, 9));//s is a // index 3~8번까지 출력해줌(마지막 인덱스 제외)
        System.out.println(" wwefw ".trim());//wwefw// 양옆의 공백 제거해주는 메소드
        System.out.println("*".repeat(10));//********** //문자열을 반복해줌
        // 추가적인것은 더 찾아서 사용하면 된다.

        char[] characters = s.toCharArray();
        for(char value : characters){
            System.out.println(value);
        } // String의 배열을 불러온것이 아닌 새로운 배열을 만든것임// so 여기 배열 내용 변경시켜도 원문에 변화없음

    }
}
