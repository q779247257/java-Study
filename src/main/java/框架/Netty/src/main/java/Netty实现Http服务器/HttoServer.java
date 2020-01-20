package Netty实现Http服务器;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Author: 轩轩
 * @Date: 2020/1/10 10:56
 * @description:
 */
public class HttoServer {
    public static final int port = 6789;//服务器端口
    private static EventLoopGroup group = new NioEventLoopGroup();
    private static ServerBootstrap b = new ServerBootstrap();
    private static final boolean SSL = true;

    public static void main(String[] args) throws Exception {
        try {
            b.group(group);
            b.channel(NioServerSocketChannel.class);
            b.childHandler(new ServerHandlerInit());

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
