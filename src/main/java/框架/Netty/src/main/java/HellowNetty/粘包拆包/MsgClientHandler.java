package HellowNetty.粘包拆包;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @Author: 轩轩
 * @Date: 2019/12/25 17:56
 * @description: Msg 客户端处理类，用于创造TCP的粘包、连包问题
 */
public class MsgClientHandler extends SimpleChannelInboundHandler<ByteBuf> {


    /**
     * 客户端读取到数据以后，触发的方法
     * @param channelHandlerContext 通道处理程序上下文
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf msg) throws Exception {
        System.out.println("Client accetp :"+msg.toString(CharsetUtil.UTF_8));
    }
    /**
     * 客户端端被通知channer活跃以后，或者说通道建立之后，触发此函数
     * @param ctx
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf msg = null;
        String rendValue = "你好啊 轩轩"+System.getProperty("line.separator");
        for (int i=0 ; i<100 ; i++){
            msg = Unpooled.buffer(rendValue.length());
            msg.writeBytes(rendValue.getBytes());
            ctx.writeAndFlush(Unpooled.copiedBuffer(rendValue,CharsetUtil.UTF_8));
        }

    }

    /**
     * 异常处理
     * @param ctx 通道处理程序上下文
     * @param cause 错误的原因
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
