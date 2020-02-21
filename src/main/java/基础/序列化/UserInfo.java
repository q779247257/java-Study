package 基础.序列化;

import java.io.Serializable;
import java.nio.ByteBuffer;

import static javax.crypto.SecretKey.serialVersionUID;

/**
 * @Author: 轩轩
 * @Date: 2020/2/19 10:52
 * @description:
 */
public class UserInfo implements Serializable {

    private static final long serialVersionUID = -1102660655096769935L;
    private String name;
    private int userId;

    public UserInfo(String name, int userId) {
        this.name = name;
        this.userId = userId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    //自行序列化
    public byte[] codeC(){
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //name转换为字节数组value
        byte[] value = this.name.getBytes();
        //写入字节数组的长度
        buffer.putInt(value.length);
        //写入字节数组value的值
        buffer.put(value);
        //写入userID的值
        buffer.putInt(this.userId);
        //准备读取buffer中的数据
        buffer.flip();
        value = null;
        byte[] result = new byte[buffer.remaining()];
        //buffer中的数据写入字节数组并作为结果返回
        buffer.get(result);
        return result;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", userId=" + userId +
                '}';
    }
}
