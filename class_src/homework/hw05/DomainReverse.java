package homework.hw05;

/**
 * 도메인 뒤집기
 *
 * 주어진 홈페이지 주소를 .을 기준으로 각각 뒤집어 출력하시오.
 *
 * ex) www.google.com -> www.elgoog.moc
 *
 * 인자
 * string: 홈페이지 주소
 */

public class DomainReverse {

        public static void main(String[] args) {
//
//            // #1 replace()
//            String string = "www.google.com";
//
//            System.out.println(string.replace("google.com","elgoog.moc"));
//
//            // #2 split()
//
//            String[] strA = string.split("\\.",0);
//
//            for (int i = 0; i <strA.length;i++){
//
//                for(int j = strA[i].length()-1 ; j>=0 ; j-- ) {
//                    System.out.print(strA[i].charAt(j));
//                }
//                if(i != strA.length-1){
//                    System.out.print('.');
//                }
//            }
//            System.out.println("");
//
//            // #3 String Buffer
//
//            for(int i = 0 ; i < strA.length;i++  ){
//                StringBuffer sBuffer = new StringBuffer(strA[i]);
//                System.out.print(sBuffer.reverse());
//                if(i != strA.length-1){
//                    System.out.print('.');
//                }
//            }
//
            //풀이 예시

            String string = "www.google.com";
            int i = string.indexOf('.'); // 처음 점 찾기
            StringBuilder s = new StringBuilder(string.length());// ()안에 길이를 설정.

            while (i > 0) {
                StringBuilder s_ = new StringBuilder(string.substring(0, i));//0 ~  i-1까지 짜름// www
                string = string.substring(i + 1);// gooogle.com
                s.append(s_.reverse());//www을 뒤짚음
                s.append('.');
                i = string.indexOf('.');
            }
            s.append(new StringBuilder(string).reverse());// 마지막꺼 뒤짚기기
            string = s.toString();// string빌더를 string으로 바꿔주기

            System.out.println(string);



        }
}
