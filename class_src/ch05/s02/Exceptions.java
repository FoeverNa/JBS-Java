package ch05.s02;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 오류 / 예외
 * 오류 - 메모리 부족 또는 프로그램 실행이 꼬이는경우
 *      더이상 어떻게 프로그램을 복구해야 할 지 알 수 없다.
 *      프로그램의 문제를 해결하여 해결 - 프로그램 종료후 버그픽스를 해야함
 *
 *      오류가나면 프로그램이 종료되는게 맞고 그걸 계속 무시하고 실행하게 하면 해킹의위험발생
 *
 * 예외 (Exception)
 * - 오류에 비해서 심각도가 낮고, 프로그램의 정상적인 흐름만 방해
 *    -> 파일을 읽으려 했으나, 해당 파일이 없음
 *      - 파일만 찾아주면됨
 *    -> 네트워크 연결이 유실
 *       네트워크 리커넥션하면됨
 *
 * - 문제 상황을 처리하는 로직을 구현하여, 런타임에서 자연스럽게 해결 가능
 *      -이게 얼마나 유용할까? 라고생각들면 카톡을하다가 와아피아 잠깐 끊겼다고 카톡이 튕기면?화가나지
 *      이런걸 넘어서 백엔드 단에서는 서버가 잠깐 비가많이온다고 패킷유실이 생겼다고 프로그램이 멈추었다?
 *      비가와서 비를맞은것도 아닌데 서버가멈추면 사용자들이 그걸 용납할까?
 *     - 그래서 예외처리가 중요하다,, 프로그램 유지에 중요한 역할이겟따
 *
 * 예외 처리
 * - 예외가 발생헀을 경우에, 이 상황을 '감지'하고 '처리'하는 코드
 * - try ~ catch, throws, thorw, finally 키워드들을 이용
 *  - 다 새로 배워야 하는 애들
 *
 *
 *  Throwable 클래스를 상속하는 자식 클래스들로 이러한 문제를 해결
 *      - 대장클래스
 */

public class Exceptions {
    public static void main(String[] args) {
        // try ~ catch
        try {
            System.out.println("되나?");
            // 예외가 발생할 수 있는 코드 작성
            // 예외가 발생할 경우 예외 객체를 던짐(throw) 어디로?
        } catch (Exception e) { // 던져진 예외를 받음 (catch를 이용해서)
            // Exception 클래스 및 그 자식 클래스를 사용해서 받음(클래스였구나)
            // 에외상황을 처리하는 코드드
        }

//        int[] integers = new int[10]; // 0~9까지 되야되는데 20을넣으면 에러발생
//            integers[20] = 5; // Exception 발생

        try {
            int[] integers = new int[10]; // 0~9까지 되야되는데 20을넣으면 에러발생
            integers[20] = 5;
        } catch (ArrayIndexOutOfBoundsException e) {// 위에서 에러명을 출력해봐서 입력할수 있음
            System.out.println(e.getMessage()); //Index 20 out of bounds for length 10
            // 객체 e에 메세지를 심어준것임. 그걸 message로보여줌
            //에러를 thorw해서 밑에서 catch를 해서 메세지로 출력해준상황
//            e.printStackTrace();// 시뻘건 메세지도 출력해주는애
            // 근데 순서는 맨마지막에 출력됨 // IDE => 버그래, 순서가꼬임
            // 콘솔에서 출려하면 이런에러 안뜬데, 에러자연스럽게 받아들이면됨
        }
//        System.out.println("Programm ended normaly");
        // 에러가 있었음에도 프로그램이 에러가 나지 않고 끝까지 실행되서 출력문까지 출력되고 종료


        // 다중 예외 처리
        try{
            System.out.println(10/0);
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println(e);
        }catch(NumberFormatException e){
            System.out.println(e);
        }catch(ArithmeticException e){
            System.out.println(e);
        } catch(Exception e){ // 나머지 모든 Exception을 모두 catch
                                // 모든 Exception 객체의 조상
                                // 스윗치케이스의 default와 비슷하게 동작
                                // 이것을 쓰는 것은 권장하지 않음
                                // 어떤 에러가 발생하는지 처리할 수있을때 처리하는거야
                                // 그렇기때문에 예상하지 않은 에러는 확인하고 처리를해야함
        }

        // 다중 예외 switch~ case처럼 첫 번째 에러확인 하고 안걸리면 그 다음 확인하는 방식
        // 특정 catch 구문에 선택되는 조건은 다형성에 의해서 결저된다
        // 즉, catch하고 있는 클래스의 자식 객체면  chatch 가능(본인도포함이겠지?)
        // 오류 끼리 서로 부모자식하고잇나부다
        // 순서도 겹치게끔 구현하면 중요해진다. 안겹치게 구현하겠찌?


        try {
            int[] integers = new int[20];
//            integers[21] = 10;
            System.out.println("던졌다?"); //
        } catch (Exception e) {
            System.out.println("받았다!"); // 받았다
        } finally {
            System.out.println("마침내!"); // 마침내!
            // try에서 생성한 리소스를 회수하기 위해

        }
        //firnally는 cat발생 여부와 무관하게 실행
        //던졌다? 마침내! // 받았다 마침내!! 두 상황모두 finally를 실행

        FileInputStream file = null;
//        file = new FileInputStream(("a.txt"));
//        file.read(); // 두번오류 발생할 수 있음
        /**
         * 예외의 종류 2가지
         * - Checked Exception
         *  Exception 클래스를 상속하고 있으면 Checked Exception
         *  컴파일러에서 에러 발생 - > try ~ catch를 작성하지 않으면
         *  아예 빌드조차 할 수 없다.(실행도 못함)
         *  무조건 try & catch를 사용해야되서 예외처리를 배우고나서 사용할 수 있다.
         *
         * - Unchecked Exception
         *  RuntimeException 클래스를 상속하고 있으면 Unchecked Exception
         *  try ~ catch를 작성하지 않더라도 빌드와 실행이 가능하다
         *  대표적으로 ArrayIndexOutOfBounds, ArithmaticException
         */
        try {
            file = new FileInputStream(("a.txt"));
            file.read(); // 두번오류 발생할 수 있음
        } catch (IOException e) {
            System.out.println("파일 처리 실패");
        } finally {
            if (file != null) {
                try {
                    file.close(); // 이것도 꼭 해줘야하는 이유가 checked exception 있기 때문
                } catch (IOException e) {
                    System.out.println("앗!.. 아아..");
                }

            }

        //file.read()한줄할려고 이모든걸해줘야함 그래서 ㅏㄴ온게
        // try ~ with ~ resources 구문
        // Java 1.7에서 추가된 기능
        // AutoClosable 인터페이스를 구현하는 리소스만 사용 가능 (close 메소드를 가지고있음)
        // 뒤에 나오는 파일을 핸들하는 Stream을 배우면 리소스에 대한 이해를 더 할 수 있을거야
        // 굉장히 편리하다

        try(FileInputStream file1 = new FileInputStream("a.txt")){
            file1.read();
        } catch (IOException e){
            System.out.println("파일처리실패");
        }


            System.out.println("Programm ended normaly");
        }
    }
}


// throws 키워드를 이용하여 예외 처리 위임
// methodA의 예외를 methodB로 넘겨서처리함
class checkedExceptionThrow {
    void methodA() throws IOException { // methodA가 처리하는게 아니고 method가 처리해줘라
                                        // methodA는 IOException을 보내는애야라고 알려주는애
                                        //throw's' methodA가 던진다라는 동사로 사용함
        FileInputStream file1 = new FileInputStream("a.txt");
        file1.read();
        file1.close();
    }

    void methodB() {//얘도 throws할수도 있고 직접 try catch할수도 있음
        try {
            methodA();
        } catch (IOException e){
            System.out.println("메소드에이실패"); // 처리를 methodB로 위임함
                                                // close()에대한 처리 없음으로 지금 방식은 권장X
        }
    }
}

//Unchecked Exception의 경우에는 thorws 키워드를 사용하지 않아도 됨
class UncheckedExceptionThrow{
    void methodA(){ // 언첵트익셉션은 throws안해도 알아서 위임됨
        int x= 10/0; //아리스매틱익셉션 // 런타임익셉션
                        //빨간줄이 안뜨는 에러기때문에 어디서 해도상관이없으니 까 안뜨는거래
                        
    }

    void methodB() { // 여기서해도 아래에서 해도 되서 에러가안뜸
        methodA();
    }

    public static void main(String[] args) {
        try{
            new UncheckedExceptionThrow().methodB();
        } catch (ArithmeticException e){
            System.out.println(e.getMessage()); /// by zero
            
        }
    }
}

class Foo{
    void methodA() throws IOException{}// Checked Exception
}

class BarOne extends Foo{
    void methodA() throws IOException {} // possible
}

class BarTwo extends Foo{
    void methodA() throws FileNotFoundException{}// 더 자식 Exception은 possible
    // FileNOtFoundException은 IOException의 자식 클래스이기 때문에 가능함
}

class BarThree extends Foo{
//    void methodA() throws Exception {}
//  부모 클래스의 메소드를 오버라이드 할때는
    // 부모 클래스의 메소드의 예외보다 더 조상인 예외는 던질 수 없다.
    // 오버라이딩할 때 구현하는 내용을 어느정도 제한하고 있는 부분
    // 파일 처리 익섹셥 하다가 갑자기 다른에러가 나온다는것은 다른 기능을 한다는 뜻이자나.
    // 그렇기에 비슷한 에러가 나오라고 제한하는 것
    // 오버라이딩 했으면 부모의 가업을 따라가라 - 엄격한집안
}

//오버라이딩 해도 다른 예외 발생할 수 있는데 구지 예외까지 상속해야되는이유는?
// 고슬링씨의 선택.. 오버라이딩할때 구현하는 내용을 어느정도 제한하는 언어라는 뜻

