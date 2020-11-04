package ch05.s11;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;

public class Main {

    // 버퍼 사용할대 필요한 아이
    static void copyStream(InputStream input, OutputStream output) throws IOException {
        byte[] buff = new byte[1024];
        int read = 0;
        while ((read = input.read(buff)) > 0) {
            output.write(buff, 0, read);
        }
    }

    public static void main(String[] args) throws IOException, URISyntaxException {

        // 입력스트림 / Reader

        // Stream/Reader 등을 사용하는 이유는
        // 노드(입출력 장치/파일//메모리 등)으로부터 데이터를 읽고 쓰는 기본적인 방식 => 이해하고 있어야 한다
        // 스캐너 쓰면 좋지만 이해하기 어렵고 제어하기도 어렵다
        // 보통은 컴퓨터를 사용하지만, Embedded machine 의 경우 Stream 을 사용하는 경우가 많다
        // 그래서 Scanner가 편리하기는 하지만, Stream/Reader 동작을 이해할 필요가 있다

//         Stream - byte 단위로 읽고 쓰는 특징
//         byte 단위로 읽어서 int 로 출력함
//         영어 알파벳은 byte 단위로 끊어도 되지만
//         한글 글자는 byte단위로 끊으면 읽고 쓸 수 없음

//        try (InputStream input = System.in;){ // close를 자동으로 해주는 방식
//            int read = -1;
//            while((read = input.read()) != -1 ){
//                System.out.printf("Int: %d Char: %c\n", read, read);
//            }
//
//        } catch(IOException e) {
//            e.printStackTrace();
//        }

        // abcd 입력시
        //Int: 97 Char: a
        //Int: 98 Char: b
        //Int: 99 Char: c
        //Int: 100 Char: d
        //Int: 10 Char:

        // 한글은 1byt e로 표현이 안되서 한글을 입력하면 깨져서 나오게 됨

//        try (InputStream input = System.in;){ // close를 자동으로 해주는 방식
//            int read = 0;
//            byte[] bytes = new byte[512]; // 버퍼, 선언해 놓고 사용할 수 있게 해놓은 메모리 공간
//            while((read = input.read(bytes)) != 0 ){
//                System.out.println(Arrays.toString(bytes)); // [97, 98, 99, 100, 10, 0, 0, 0, 0, 0, 0, 0....
//                System.out.println(read); // 5 // 5개를 읽었다는 뜻
//            }
//
//        } catch(IOException e) {
//            e.printStackTrace();
//        }


//        try (InputStream input = System.in;){
//            int read = 0;
//            byte[] bytes = new byte[512];
//            while((read = input.read(bytes)) != 1 ){ // 엔터를 입력하면 구동이 끝나게 된다
//                if((char)bytes[0] =='q'){
//                    break;
//                }
//                System.out.println(Arrays.toString(bytes));
//                System.out.println(read);
//            }
//
//        } catch(IOException e) {
//            e.printStackTrace();
//        }


        // reader 활용
        // Stream과 유사하나, Reader는 char 단위로 읽어서int로 반환
        // Stream을 이용해서 Reader를 초기화 할 수 있다
//        // 보충해야된다 => 한글도 입력할 수 있다는 점이 좋은방식

//        try(InputStreamReader reader = new InputStreamReader(System.in)) {
//            int read = -1;
//            while ((read = reader.read()) != 1) {
//                System.out.printf("Int: %d Char: %c\n", read, read);
//            }
//        } catch (IOException e){
//            e.printStackTrace();
//        }

//         try(InputStreamReader reader = new InputStreamReader(System.in)) {
//            int read = -1;
//            char [] cbuf = new char[100];
//            while ((read = reader.read(cbuf)) != 1) {
//                System.out.println(Arrays.toString(cbuf)); // [a, b, c, d, ,  , ==> char으로 바로 출력된다
//                System.out.println(read);
//            }
//        } catch (IOException e){
//            e.printStackTrace();
//        }


        // Stream의 mark/reset 기능
        // 많이쓰이진 않겠지만 Stream 자체에도 이런 기능이 있다 정도로 참고해 두면 된다
//
//        System.out.println(System.in.markSupported());// true // mark/reset 가능한지 체크해준다
//
//        // 입력받은 값에 mark 를 해둔곳에서 부터 기억해두었다가 reset 을 하면 다시 mark 한 곳에서 부터 출력 해준다
//
//        try (InputStream input = System.in;){ // close를 자동으로 해주는 방식
//            int read = -1;
//            while((read = input.read()) != 'q' ){
//                System.out.printf("Int: %d Char: %c\n", read, read);
//                if(read == 'm'){ // m을 입력하면 mark 시작
//                    input.mark(32); // 입력 파라미터 : 몇개까지 기억하게 할 것인가
//                                               // 참고 : 버퍼 숫자는 2의 제곱승 중으로 사용하는 경우가 많다
//                }
//                if ((char)read == 'r'){ // m 이후에 입력한 것들이 r까지 반복 된다 => 정확히는 r + 엔터까지 들어온다
//                    input.reset();
//                }
//            }
//
//        } catch(IOException e) {
//            e.printStackTrace();
//        }

        // 출력 스트림/Writer
        // 메모리를 Node로 하는 입출력


//        char [] memory = "메모리 입출력 테스트 입력".toCharArray();
//        char [] cbuf = new char[4]; // memory보다 작은 크기로 만듬
//        int read = 0; // 얘가 뭐하는지 잘모르겠네
//        try (CharArrayReader reader = new CharArrayReader(memory); // 안에 ;을 사용하면 resoruce 를 2개 사용할 수 있다
//             CharArrayWriter writer = new CharArrayWriter();) { // 두번째 것도 ;을 붙여줘어야 하네
//            while ((read = reader.read(cbuf)) > 0 ){
//                writer.write(cbuf, 0, read); // 0은 offset 을 0으로 해서 skip 없이 간다는 것 => 이걸 왜 하는 지 알기
//                                    // 안쓰면 버퍼에 이전 값이 남아 있을 수 있다. // 마지막 출력이 입력이 아니고 입력트가 된다
//                                    // char[]이 아닌 String으로 하면 적절하지 않다고 설명하셨는데 다시 이해해보기
//            }
//            System.out.println(Arrays.toString(writer.toCharArray())); // 왜 charArray로 바꾸는지 이해하기
//        } catch (IOException e){
//            e.printStackTrace();
//        } // 전체 과정 이해하기 버퍼를 타고가서 charArray에 들어간다는데


        //File class

//        System.out.println(File.separator); // Windows는 \로 표시된다. 시스템에 따라 달라질 수 있다
//
////        String filePath = "D:\\Temp\\MyTemp"; // \를 2개씩 써야지 \역할을 한다  // 이렇게는 잘 사용하지 않는다
//        String filePath = "C:" + File.separator + "Temp"+ File.separator +"MyTemp";
//        System.out.println(filePath); // D:\Temp\MyTemp
//
//        File fileOne = new File(filePath); // 해당 객체는 폴더까지만 가르키고 있음 -> path 만 표현 하고 있다
//        System.out.println(fileOne.mkdir()); // 생성에 성공하면 true / 실패하면 false // mkdir 은 마지막 폴더만 생성 // 상위 폴더가 없다면 false 함
//        System.out.println(fileOne.mkdirs()); // mkdirs 은 경로의 폴더도 생성 // 상위폴더 없어도 생성해서 마지막 폴더까지 생성 함
//
//        File fileTwo = new File(filePath, "file2.txt");
//        fileTwo.createNewFile(); // 해당 폴더에 파일이 생성됨 // 파일 생성 방법 1
//        // IOexception 을 main 에서 throw 하면 어떻게 되는거지?
//
//        File fileThree = new File(fileOne, "file3.txt");
//        fileThree.createNewFile(); // 위랑 무슨차이지?
//
//        File fileFour = new File(new URI("file:///c:/Temp/MyTemp/file4.txt")); // 예도 예외처리해줘야함
//        fileFour.createNewFile();
//        fileFour.deleteOnExit(); // 그냥 delete도 가능하다 // 프로그램이 종료되면 파일이 삭제 된다 -> 정말 Temp 파일에 사용하면 유용하겠다
//
//        //path 가 file 객체로 되어있다는 것이 구분을 어렵게하는 단점이 있다는 것 이해해보자
//
//        System.out.println(fileTwo.getName()); // file2.txt 파일명 출력
//        System.out.println(fileTwo.getParent()); // C:\Temp\MyTemp
//
//        System.out.println(fileTwo.isAbsolute()); // 절대 경로를 사용하는 가? // 상대 경로도 존재하다는거지, 찾아
//                                                // 상대경로를 하면 실행한 위치에 만든다던지하는 장점이있고
//                                                // 절대겨올를 사용하면 어디에서 실행하면 같은 곳에 만들어진다는 장점이 있다
//        System.out.println(fileTwo.getCanonicalPath()); // .. 등을 모두 배제한 표준 표현법 사용 // 절대 경로라고 해도 깔끔한 경로는 아니기 때문에 정리가 필요할때 사용한다
//
//        System.out.println(fileOne.isDirectory()); // true // directory 를 가르키는가?
//        System.out.println(fileTwo.isFile()); // ture  // file 을 가르키는가?
//
//        System.out.println(Arrays.toString(fileOne.list())); // [file2.txt, file3.txt] // String Array으로 출력된다
//        // list를 이용해 directory안에 file들을 다 출력해 볼 수 잇따
//        System.out.println(Arrays.toString(fileOne.listFiles())); // [C:\Temp\MyTemp\file2.txt, C:\Temp\MyTemp\file3.txt] // File객체 Array로 출력이 된다
//        // 용도에 따라 사용하면 될 것이다
//
//        // 강사님이 src파일을 미리 만들었다. src
//
//        File srcFile = new File(fileOne, "src.txt");
//        File dstFile = new File(fileOne, "dst.txt");
//        dstFile.createNewFile(); // 파일 생성

//        // Stream을 이용한 파일의 복사 (byte단위)
//        try(FileInputStream src = new FileInputStream(srcFile);
//            FileOutputStream dst = new FileOutputStream(dstFile);) {
//            int read = -1; // 더 읽을게 없으면 -1을 출력한다
//            while((read = src.read()) != -1){
//                dst.write(read);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } // 실행결과 src 파일의 내용이 dst 파일로 복사가 되었다


        // 이것도 가능하다, 폴리몰피즘에 의해
//
//        try(InputStream src = new FileInputStream(srcFile);
//            OutputStream dst = new FileOutputStream(dstFile);) {
//            int read = -1; // 더 읽을게 없으면 -1을 출력한다
//            while((read = src.read()) != -1){
//                dst.write(read);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // Reader를 이용한 파일의 복사 ( byte단위) // 체크
//        try(FileReader src = new FileReader(srcFile);
//            FileWriter dst = new FileWriter(dstFile);) {
//            int read = -1; // 더 읽을게 없으면 -1을 출력한다
//            while((read = src.read()) != -1){
//                dst.write(read);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } // 실행결과 src 파일의 내용이 dst 파일로 복사가 되었다
//

//        try(InputStream src = new FileInputStream(srcFile);
//            OutputStream dst = new FileOutputStream(dstFile);) {
//            int read = -1;
//            byte [] buff = new byte[4];
//            while((read = src.read(buff)) > 0){
//                dst.write(buff, 0, read);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } // 버퍼를 이용해서 파일을 복사하는것

        // append = true 로 FileWriter 를 생성하면 이어서 작성한다 (txt, ini(설정값담는파일), properties..파일에만 사용한다)
        // Log 를 작성할 때 활용할 수 있다
        // binary 파일 - 문자열로 작성된 것이 아닌, decoding 이 된 상태의 파일
        // 그림파일, 동영상파일, exe파일 .. (텍스트가 아닌 모든 파일)
//        try(FileReader src = new FileReader(srcFile);
//            FileWriter dst = new FileWriter(dstFile, true);) {
//            int read = 0;
//            char[] buff = new char[4];
//            while((read = src.read(buff)) > 0) {
//                dst.write(buff, 0, read);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } // 버퍼를 이용해서 파일을 복사하는것
//


        //여기까지가 메인스트림

        // 보조스트림

        // InputStreamReader ┬  byte 스트림 -> char 스트림으로 변환하는 보조 스트림 // 위에 InputStream 사용한 예 있다
        // OutputStreamWriter┘

        // 반응성이 중요하지 않은 경우에 사용 한다 (파일입출력, 네트워크 일부 경우(다운로드, 업로드...)
        // BufferedReader       ┬ 스트림에 버퍼링 적용하여 스트림 throughput 향상
        // BufferedWriter       ┤ throughput: 평균 전송 속도(로 이해하면 될 것 같다)
        // BufferedInputStream  ┤ delay: 버퍼링을 쓸 경우 오히려 안좋아짐 -> delay 시간 길어짐(네트워크에서 ping으로 생각하는 것)
        // BufferedOutputStream ┘ 위 두 속성은 서로 trade-off 관계 // 평균 전송 속도 높이는게 이득인 상황이 생긴다 -> 그대 Buffer를 사용한다

//        File src = new File("C:/Windows/explorer.exe"); // Windows 에 가장 큰 파일
//        File dst = new File("C:/Temp/MyTemp/explorer.exe"); // 복사할 위치
//
//        try(FileInputStream in = new FileInputStream(src);
//            FileOutputStream out = new FileOutputStream(dst);
//            BufferedInputStream buffIn = new BufferedInputStream(in);
//            BufferedOutputStream buffOut = new BufferedOutputStream(out);) {
//            long start = System.nanoTime();
//            copyStream(in, out);
//            System.out.println(System.nanoTime() - start); // 79874600
//
//            start = System.nanoTime();
//            copyStream(buffIn, buffOut);
//            System.out.println(System.nanoTime() - start); // 18100 // 10000배 이상 차이가 난다 -> 반드시 사용해야하는 정도의 차이
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // 반응성이 중요한 경우에만 Buffer 사용하고 그외에는 그냥 사용하는게 좋을 정도로 큰 차이라고 이해하면 좋겠다


        // 기본형 데이터
        // DataInputStream  ┬  기본형 데이터 (Primitive Type)을 전송하기 위한 스트림
        // DataOutputStream ┘  Stream, Read(Writer)는 byte/ char 단위로 전송한다
        // readBoolean, readByte, readShort ... readUTF (String type)
        // writeBoolean, writeByte, writeShort ...readUTF (String type)

//        File src = new File("C:/Temp/MyTemp/data.dat"); // 윈도우는 \가 맞지만 /을 사용해도 괜찮다
//        DataOutputStream out = new DataOutputStream(new FileOutputStream(src)); // 보조 스트림이기때문에 메인 스트림을 생성해서 입력할 필요하다
//        out.writeUTF("자바왕");
//        out.writeInt(128);
//        out.writeFloat(523.411f);
//
//        DataInputStream in = new DataInputStream(new FileInputStream(src));
//        String str = in.readUTF();
//        int integer = in.readInt();
//        float floatVal = in.readFloat();
//
//        System.out.println(str + " " + integer + " " + floatVal);


        // 객체 입출력

        // 객체 직렬화를 위한 인터페이스 - Serializable
        // 객체는 힙영역에 내용이 연속적으로 써있는것이 객체의 실체
        // 우리는 이 class를 알고 있기 때문에 힙영역에 적혀있는것이 무엇인지 아는 것이다
        // 이 객체의 내용중에서 우리가 관심있는 내용 중에 저장하고 싶은 내용만 골라서 일자로 펴주는것(컴팩트하게)
        // 필요한 내용만 딱딱 모아주는 것,
        // 직렬화 된 객체를 입력 받았을 시 다시 힙내용에 풀어서 쓰는것을 deSerialization이라고 한다
        // Serialization <=> DeSerialization
//
//        class Foo implements Serializable {// Serialize를 할 수 있는 객체를 만드는 클래스이다.
//                                           // has-a관계에 있는 클래스들 모두 Serialzable해야 한다( 다같이 직렬화해야되니까)
//                                           // foo 안에 bar 객체가 있다면 bar도 Seraiizable해야 한다는 뜻
//
//            static final long serialVersionUID = 1L; // 클래스 버전 관리 // 클래스 수정할때 버전이 바뀌어서 버전을 관리한다 (UID = UniqueID)
//            // 객체를 저장할때와 불러올 때 같은지 체크하여
//            // serialVersionUID가 일치하지 않으면 실패(하도록 되어 있다)
//
//            String userName;
//            int userID;
//            transient String passWord;
//            // transient (접근자?제어자?)의 경우 Serialize에 포함하지 않음 ( 저장/불러오기 대상에서 제외
//
//            public Foo() {}
//
//            public Foo(String userName, int userID, String passWord) {
//                this.userName = userName;
//                this.userID = userID;
//                this.passWord = passWord;
//            }
//
//            @Override
//            public String toString() {
//                return userName + " " + userID + " " + passWord;
//            }
//        }
//
//        Foo foo = new Foo("HanSol-The=Outsider",
//                 1423, "negazeilzalnaga");
//        System.out.println(foo); // HanSol-The=Outsider 1423 negazeilzalnaga
//
//        File dst = new File("C:/Temp/MyTemp/obj.data"); //확장자는 상관이 없다
//
//        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(dst));
//            ObjectInputStream in = new ObjectInputStream(new FileInputStream(dst))) {
//            out.writeObject(foo);
//            Object read = in.readObject();
//            if (read != null && read instanceof Foo) {
//                Foo readFoo = (Foo)read;
//                System.out.println(readFoo); // HanSol-The=Outsider 1423 null
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//       // 구현실패해서 설명만봣음
//        //결과가 password가 null로 나온것은 transient로 만들었기 때문이다
//        //용도는 다양하다고 설명해주셨는데 역시 내가 이해못하는 부분들이 있었나보다
//
//        // 부모클래스는 Serializable하지 않을 때,
//        // 자식클래스를 Serializable하게 구현하기
//
//        class ParentFoo{
//            int memVarOne;
//            double memVarTwo;
//
//        }
//
//        class ChildFoo extends ParentFoo implements Serializable {
//
//
//            int childMember; // 얘는 childMember이기 때문에 알아서 직렬화된다
//
//
//            private void writeObject(ObjectOutputStream out) throws IOException {
//                out.writeInt(memVarOne); // 직접 부모객체의 멤버 변수를 직접 out에 써주기어야 한다
//                out.writeDouble(memVarTwo); // 원하지 않는 변수가 있다면 trsient대신 그냥 생략해주어도 된다
//                out.defaultWriteObject(); //child foo에 있는애들을 직렬화해주는 애
//            }
//
//            private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
//                   memVarOne = in.read(); // write를 한 순서 그대로 읽어야만 읽는게 가능하다
//                   memVarTwo = in.readDouble();
//                   in.defaultReadObject();
//
//            }
//        }



    }
}
