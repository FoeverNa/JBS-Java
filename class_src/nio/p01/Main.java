package nio.p01;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * nio(NEw Input/Output)
 * nonblocking-io (x)
 *
 * io stream : 입력 노드/출력 노드가 구분
 * nio channel : 입/출력 모두 처리 가능(통합 인터페이스)
 *
 * - 채널을 이용해서 입/출력을 모두 처리 (동시에 입출력이 되는 것은 아니다)
 * - 기본적으로 버퍼를 사용한다
 * - nio는 non-blocking을 지원 한다 (비동기식)
 *    - 읽기/쓰기를 시켜놓고 다른 작업 진행 가능 (멀티스레드)
 *    - non-blocking IO는 잘못된 용어사용이다
 *
 */

public class Main {
    public static void main(String[] args) throws URISyntaxException {
        // path를 표현하는 방식
        Path path = Paths.get("1.txt");
        Path path1 = Paths.get("User","ieyoung","1.txt"); //dir를 쭉 이어서 써나가는 방법
        Path path2 = Paths.get(new URI("file:///users/ieyoung/1.txt"));

        // getName, getRoot 등 다양하게 path에 내용을 받아올 수 있다
        //to ~ 에도 몇가지 기능이 더있다

        // Paths -> get 정적 메소드
        // Files -> createFile, createDirectory, delete, copy, move
        // 직접 해보기

        // Buffer: Direct buffer, non-direct buffer
        // Direct buffer: OS에 메모리를 직접 요청
        //          -> 사용가능한 크기가 크다(JVM제한x), 생성 속도는 느리다(OS와 통신 필요),
        //              입출력 성능이 좋음(입출력속도가 OS의 스택메모리를 받아오기 때문에그렇다)
        //              한번 크게 잡아놓고 계속해서 쓸때는 Direct가 좋다
        //              ByteBuffer만 생성 가능, nio는 ByteBuffer를 사용하기에 nio와 잘맞는다
        // Buffer는 메모리 덩어리, 그 그획을 Byte로만 처리하는 것이 기본 Byte Buffer
        //
        // Non-direct buffer: JVM에 힙 메모리 그대로사용
        //          -> 사용 가능한 메모리 작은편(JVM에 의해 제한),
        //           생성 속도가 빠름(JVM상에서 곧바로 사용), (잠깐쓰는애는 그냥얘를쓰는게 낫다)
        //           입출력 성능은 Direct buffer에 비해 떨어짐
        //          여러가지 Buffer를 다 사용할 수가 있다

        ByteBuffer buff = ByteBuffer.allocateDirect(1024); // Direct buffer
        ByteBuffer buff1 = ByteBuffer.allocate(1024); // Non-Direct buffer
        // 메소드 이름만 바궈서 호출하는게 허무할수 있지만 그냥 좋다

        CharBuffer cBuff = CharBuffer.allocate(1024); // Non-Direct buffer만 있다.
        IntBuffer iBuff = IntBuffer.allocate(1024);
        // ByteBuffer 외 다른 버퍼는 allocateDirect 없음

        //하지만 bytebuffer를 쉽게 변환할 수 있다
        DoubleBuffer dBuff = buff.asDoubleBuffer();// OS에 처리하는것 byte기준으로 되기때문에 수동으로 바꾸써라

        Path src = Paths.get("1.txt");
        Path dst = Paths.get("2.txt");


        try(FileChannel channel1 = FileChannel.open(src,
                StandardOpenOption.READ);
            FileChannel channel2 = FileChannel.open(dst,
                    StandardOpenOption.WRITE,
                    StandardOpenOption.CREATE)
        ) {
            int read = -1;
            ByteBuffer readBuffer =
                    ByteBuffer.allocate((int) channel1.size()); // channel1 을 한번에 읽을수 있는 buffer만듬

            read = channel1.read(readBuffer); // 이러면 읽어준 것
            if (read == -1) { // -1이면 읽기에 실패한것, 읽은 갯수가 없는것인가
                throw new IOException();
            }

            readBuffer.flip();
            channel2.write(readBuffer); // 이러면 써준 것
            readBuffer.clear();

            // buffer 사용법
            // capacity, limit, position, mark
            // 0 <= mark <= position <= limit <= capacity
            // capacity는 Buffer 전체를 뜻한다
            // Read에 의해서 bytes5개 써졌을 때 6번째에posiotion이
            // limit으로 어디까지 읽었는지(사용이 되었는지) 표기하는것이 표시해두는곳
            //      특정 위치 이후에는 쓰레기 값이 있을 수 있는데 그걸방지하기 위해 LIMIT이 있는 것
            // flip= limit을 하고 posiont 이 맨앞으로간 것
            // buffer에 크기 상관없이 읽힌 만큼만 옮기게된다
            // clear하면 posiont은 앞으로 오고 limit은 capcity로 간다
            //  그럼 해당 buffer를 쓴적이 없는 상태가 된다


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
