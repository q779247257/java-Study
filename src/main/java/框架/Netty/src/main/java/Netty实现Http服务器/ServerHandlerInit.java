package Netty实现Http服务器;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.ssl.SslContext;

/**
 * @Author: 轩轩
 * @Date: 2020/1/10 11:06
 * @description:
 */
public class ServerHandlerInit extends ChannelInitializer<SocketChannel> {

    private final SslContext sslCtx;

    public ServerHandlerInit(SslContext sslCtx) {
        this.sslCtx = sslCtx;
    }


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline ph = ch.pipeline();
        //todo 增加SSL支持
        if (sslCtx != null){
            ph.addLast(sslCtx.newHandler(ch.alloc()));
        }

        //增加编码器
        ph.addLast("encded", new HttpResponseEncoder());
        //添加解码器
        ph.addLast("decode", new HttpRequestDecoder());
        //聚合器 作用： 把多个Http请求聚合为一个Http请求
        ph.addLast("aggre",
                new HttpObjectAggregator(10*1024*1024));

        //开启压缩
        ph.addLast("comperessor", new HttpContentCompressor());


        //业务处理
        ph.addLast("busi",new BusiHandler());

    }
}
