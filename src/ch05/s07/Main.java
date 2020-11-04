package ch05.s07;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 정규표현식 (Regular Expression)
 * - 문자열을 다루는 패턴화된 작업을 정의하는 수식
 * - 문자열 매칭, 템플릿(이메일, 전화번호) 일치 여부 확인, 템플릿 매칭 검색
 *      - 문서를 분석해서 특정값을 가져오는 곳에 사용할 수 있다.
 * - 정규표현식은 느리기 때문에 만용하면 안된다.
 *      - 부분적으로 파싱해서 결과물을 가지고 사용
 *      - 알고리즘문제 풀때 정규표현식쓰는 것은 부적절
 */

public class Main {
    public static void main(String[] args) {
//        String regex = "abcdef";
//        String str = "aaaaa";

//        String regex = "a+"; // +, ? , * 실습
//        String str = "";

        String regex = "(xyz)+"; // 그룹이 반복된다
        String str = "xyzxyzx";

        System.out.println(Pattern.matches(regex, str));
        //문자열 그대로 넣어서 그대로 매칭시켜보는 것이 가장 기본적인 형태

        // boundary는 문자열의 시작, 끝, 공백, 문장기호로 구분되는지 여부 확인 (대소문자로 의미 반전)
        Pattern pattern = Pattern.compile("\\bjava\\b", Pattern.MULTILINE); // 정규표현식을 pattern에 저장할 수 있음
        // \\역슬레시 쓰려면 두개씩 사용해야 한다
        // java에서는 falg를 지원안하고  Pattern.에 모아논것을 사용할 수 있다
        Matcher matcher = pattern.matcher("Java is Java and Java will be Java");

        while (matcher.find()) { //fid()의 메소드의 경우 찾으면 true, 못찾으면 false 반환
            System.out.println(matcher.start()+","+matcher.end()); //찾은 것에 시작과 끝에 인덱스를 알려준다);
        }

        Pattern pattern1 = Pattern.compile("ID:\\s?");
        Matcher matcher1 = pattern1.matcher("ID: Samzang");

        System.out.println(matcher1.lookingAt()); // 패턴을 시작하는지 확인
        String string = matcher1.replaceFirst("");
        System.out.println(string); //Samzang

        //그룹 캡처
//        Pattern pattern2 = Pattern.compile("(\\w+):(\\w+)\\.(\\w+)"); //
        Pattern pattern2 = Pattern.compile( //이름을 붙여줄수 있다
                "^(?<field>\\w+):(?<name>\\w+)\\.(?<ext>\\w+)$");
        Matcher matcher2 = pattern2.matcher("filename:temp.png");

        matcher2.find(); //find가 패턴을 찾게해줌
        System.out.println(matcher2.group()); // filename:temp.png 매칭된 전체가 출력
        System.out.println(matcher2.group(0)); // filename:temp.png 매칭된 전체가 출력
        System.out.println(matcher2.group(1)); // filename 첫번째 그룹 출력

        System.out.println(matcher2.group("field")); // group명으로 접근할수있다
        System.out.println(matcher2.group("name")); //
        System.out.println(matcher2.group("ext")); //


    }
}
