package Netty实现Http服务器;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import static io.netty.util.CharsetUtil.UTF_8;

/**
 * @Author: 轩轩
 * @Date: 2020/1/10 11:45
 * @description:
 */
public class BusiHandler extends ChannelInboundHandlerAdapter {

    private String result = "";


    /**
     * 发送回应
     * @param content 发送的内容
     * @param ctx  上下文
     * @param status  状态码
     */
    private void send (String content, ChannelHandlerContext ctx , HttpResponseStatus status){
        FullHttpResponse response =
                new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,status,
                Unpooled.copiedBuffer(content, CharsetUtil.UTF_8));

        //设置请求头
        response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain;charset=UTF-8");

        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    /**
     * 收到消息时，返回信息
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //收到的消息
        String result ="";
        FullHttpRequest httpRequest = (FullHttpRequest)msg;
        try{
            //todo
            String path = httpRequest.uri();//路径
            String body = httpRequest.content().toString(UTF_8);//拿body
            HttpMethod method = httpRequest.method();//请求的类型

            if (!"/test" .equalsIgnoreCase(path)){
                result = "非法请求:"+path;
                send(result,ctx,HttpResponseStatus.BAD_REQUEST);
                return;
            }
            //todo 如果是GET 请求
            if (HttpMethod.GET.equals(method)){
                System.out.println("body:"+body);
                result = "Get请求接受，回应:                           这是我回应";
                send(result,ctx,HttpResponseStatus.OK);

            }
            //todo 如果是 POST 请求
            if (HttpMethod.POST.equals(method)){

            }
            if (HttpMethod.CONNECT.equals(method)){

            }
            if (HttpMethod.DELETE.equals(method)){

            }
            if (HttpMethod.OPTIONS.equals(method)){

            }
            if (HttpMethod.PUT.equals(method)){

            }
            if (HttpMethod.TRACE.equals(method)){

            }

        }catch (Exception e){
            System.out.println("请求处理失败");
        }finally {
            httpRequest.release();
        }
    }

    /**
     * 建立连接时，返回消息
     * @param ctx 通道处理程序上下文
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接的客户端地址："+
                ctx.channel().remoteAddress()
                );

    }
}
