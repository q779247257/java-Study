package 基础.序列化;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

/**
 * @Author: 轩轩
 * @Date: 2020/2/19 11:25
 * @description: 测试序列化性能差距
 *
 */
public class PerformTestUserInfo {
    public static void main(String[] args) throws Exception {
        UserInfo info = new UserInfo("欢迎来到Netty", 100);
        int loop = 1000000;


        //todo 使用jdk的序列化
        ByteArrayOutputStream bos = null;
        ObjectOutputStream os = null;

        //启动时间
        long startTime = System.currentTimeMillis();
        for (int i = 0 ; i<loop ; i++){
            bos = new ByteArrayOutputStream();
            os = new ObjectOutputStream(bos);
            os.writeObject(info);
            os.flush();
            os.close();
            byte[] b = bos.toByteArray();
            bos.close();
        }
        //结束时间
        long endTime = System.currentTimeMillis();
        System.out.println("jdk序列化的开销时间是"+ (endTime-startTime) + "ms");

        //todo 使用自行的序列化
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        startTime = System.currentTimeMillis();
        for (int i=0 ; i<loop ; i++){
            byte[] bytes = info.codeC();
        }
        endTime = System.currentTimeMillis();
        System.out.println("自行序列化的开销时间是 :" + (endTime-startTime)+"ms");


    }
}
