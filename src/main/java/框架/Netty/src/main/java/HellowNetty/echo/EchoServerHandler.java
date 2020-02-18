package HellowNetty.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;


/**
 * netty 提供的一个注解，加上此注解后，多个线程可以共享Handler
 * 这名handler可以再多个channle之间共享，一位了这个实现必须是线程安全的
 */
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    /**\
     * 服务器读取到网络数据后的处理
     * @param ctx 通道处理程序上下文
     * @param msg 字节缓冲
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //Netty实现的缓冲区
        ByteBuf in = (ByteBuf)msg;
        System.out.println("Server Accept:"+in.toString(CharsetUtil.UTF_8));
        ctx.write(in);
/**
 * //这两个也可以写入数据，但是不常用，这么写的话数据路径相对于上面的写入方法会更长一些
 ctx.channel().writeAndFlush(in);
 ctx.pipeline().write(in);
 */

//        释放消息
//        ReferenceCountUtil.release(msg);

    }

    /**
     * 服服务端读取完成网络数据后的处理
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //flush掉所有的数据
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).
                //当flush完成以后，关闭连接
                addListener(ChannelFutureListener.CLOSE);
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
