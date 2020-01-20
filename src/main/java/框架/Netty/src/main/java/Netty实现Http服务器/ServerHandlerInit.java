package Netty实现Http服务器;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.*;

/**
 * @Author: 轩轩
 * @Date: 2020/1/10 11:06
 * @description:
 */
public class ServerHandlerInit extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline ph = ch.pipeline();
        //todo 增加SSL支持

        //编码
        ph.addLast("encded", new HttpResponseEncoder());
        //解码
        ph.addLast("decode", new HttpRequestDecoder());
        //聚合器
        ph.addLast("aggre",
                new HttpObjectAggregator(10*1024*1024));

        //开启压缩
        ph.addLast("comperessor", new HttpContentCompressor());


        //业务处理
        ph.addLast("busi",new BusiHandler());

    }
}
