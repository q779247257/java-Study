package 基础.buffer;

import org.junit.Test;
import  java.nio.ByteBuffer;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/1
 * @描述：
 */
public class ByteBufferTest {
    @Test
    public void byteBuffTest(){
        //创建一个 capacity 为6 的ByteBuffer
        ByteBuffer allocate = ByteBuffer.allocate(6);
        allocate.put((byte)1);
        allocate.put((byte)2);
        allocate.put((byte)3);
        System.out.println(allocate);
        //设置limit为1，那么表示buffter的有效数据长度是1
        allocate.limit(3);
        //设置position到0位置，这样都诗句的时候就从这个位置开始读
        allocate.position(0);
        System.out.println(allocate);
        //get获取一个字节，position 的位置也会随着读取而改变
        byte b = allocate.get();
        byte c = allocate.get();
        byte d = allocate.get();
        System.out.println(allocate);
    }
}
