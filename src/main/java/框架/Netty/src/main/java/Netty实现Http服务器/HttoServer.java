package Netty实现Http服务器;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;

/**
 * @Author: 轩轩
 * @Date: 2020/1/10 10:56
 * @description:
 */
public class HttoServer {
    public static final int port = 6789;//服务器端口
    private static EventLoopGroup group = new NioEventLoopGroup();
    private static ServerBootstrap b = new ServerBootstrap();
    private static final boolean SSL = true;//是否启动SSL

    public static void main(String[] args) throws Exception {
        final SslContext sslCtx;//增加SSL支持是为了支持HTTPS请求
        if (SSL){
            //Ne
            SelfSignedCertificate ssc = new SelfSignedCertificate();
            sslCtx = SslContextBuilder.forServer(
                    ssc.certificate(),
                    ssc.privateKey()
            ).build();
        }
        try {
            b.group(group);
            b.channel(NioServerSocketChannel.class);
            b.childHandler(new ServerHandlerInit(sslCtx));

            //服务器绑定端口监听
            ChannelFuture f = b.bind(port).sync();
            System.out.println("服务器端启动成功，端口是："+port);

            //监听服务器关闭监听
            f.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully();
        }


    }

}
