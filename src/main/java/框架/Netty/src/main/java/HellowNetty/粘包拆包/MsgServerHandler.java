package HellowNetty.粘包拆包;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: 轩轩
 * @Date: 2019/12/25 18:26
 * @description: 服务端处理消息类
 */
@ChannelHandler.Sharable
public class MsgServerHandler extends ChannelInboundHandlerAdapter {
    private AtomicInteger counter = new AtomicInteger(0);
    /**\
     *
     * @par服务器读取到网络数据后的处理am ctx 通道处理程序上下文
     * @param msg 字节缓冲
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //Netty实现的缓冲区
        ByteBuf in = (ByteBuf)msg;
        String request = in.toString(CharsetUtil.UTF_8);
        System.out.println("Server Accept:"+request+counter.incrementAndGet());

        //应答客户端
        String resp  = "你好！" + request + System.getProperty("line.separator");

        ctx.write(Unpooled.copiedBuffer(resp,CharsetUtil.UTF_8));
    }

    /**
     * 服服务端读取完成网络数据后的处理
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //flush掉所有的数据
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER);
                //当flush完成以后，关闭连接
//                        .addListener(ChannelFutureListener.CLOSE);
    }
    /**
     * 发生异常后的处理
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
