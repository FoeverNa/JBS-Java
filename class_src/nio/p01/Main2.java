package nio.p01;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;



public class Main2 {
    public static void main(String[] args) throws URISyntaxException {



        ByteBuffer buff = ByteBuffer.allocateDirect(1024); // Direct buffer
        ByteBuffer buff1 = ByteBuffer.allocate(1024); // Non-Direct buffer
        // 메소드 이름만 바궈서 호출하는게 허무할수 있지만 그냥 좋다

        CharBuffer cBuff = CharBuffer.allocate(1024); // Non-Direct buffer만 있다.
        IntBuffer iBuff = IntBuffer.allocate(1024);
        // ByteBuffer 외 다른 버퍼는 allocateDirect 없음

        //하지만 bytebuffer를 쉽게 변환할 수 있다
        DoubleBuffer dBuff = buff.asDoubleBuffer();// OS에 처리하는것 byte기준으로 되기때문에 수동으로 바꾸써라


        //버퍼가 작은 경우
        Path src = Paths.get("1.txt");
        Path dst = Paths.get("3.txt");


        try(FileChannel channel1 = FileChannel.open(src,
                StandardOpenOption.READ);
            FileChannel channel2 = FileChannel.open(dst,
                    StandardOpenOption.WRITE,
                    StandardOpenOption.CREATE)
        ) {
            int read = -1;
            ByteBuffer readBuffer =
                    ByteBuffer.allocate(8); // channel1 을 한번에 읽을수 있는 buffer만듬
                                                              // channel이 기억하고 어떻게
            while ((read = channel1.read(readBuffer)) != -1){ // 채널을 이어서 다시이어서 읽을수있게된다//
                readBuffer.flip(); // 채워진 곳에 limit거릴고 posion은 0으로가겟지
                channel2.write(readBuffer); // 현재 읽은 곳을 write를 한다
                readBuffer.clear(); // limit은 다시 capacity로가고 position은 0으로가서 새버퍼되면

            }
            // allocate -> allocateDiret // 보통은 allocateDiret를 사용한다(allocate로 차이나는정도는 너무작은경우..)
            // 버퍼 크기를 변경, 너무 크면 또 오버헤드생기고 너무작으면 또 너무마니왓다갓다해야되, 적절해야된다

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
