package 基础.序列化;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @Author: 轩轩
 * @Date: 2020/2/19 11:07
 * @description: 测试序列化后字节大小
 */
public class TestUserInfo {
    public static void main(String[] args) throws IOException {
        UserInfo info = new UserInfo("欢迎来到Netty", 100);

        //todo 使用JDK的序列化
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bos);
        os.writeObject(info);
        os.flush();
        os.close();

        byte[] b = bos.toByteArray();
        System.out.println("jdk可序列化长度为:"+b.length);

        //todo 使用自行的序列化
        System.out.println("自行的序列化长度为："+info.codeC().length);


    }
}
