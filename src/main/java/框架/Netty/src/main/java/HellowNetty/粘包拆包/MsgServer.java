package HellowNetty.粘包拆包;

import HellowNetty.echo.EchoServer;
import HellowNetty.echo.EchoServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;

import java.net.InetSocketAddress;

/**
 * @Author: 轩轩
 * @Date: 2019/12/25 18:26
 * @description:
 */
public class MsgServer {
    public final int port;//端口

    public MsgServer(int port) {
        this.port = port;
    }
    public  void start() throws InterruptedException {
        final MsgServerHandler serverHandler = new MsgServerHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    .childOption(ChannelOption.TCP_NODELAY,true)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //限制传输大小
                            ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
                            ch.pipeline().addLast(serverHandler);
                        }
                    });
            ChannelFuture f = b.bind().sync();
            f.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int port = 9999;
        MsgServer echoServer = new MsgServer(port);
        System.out.println("服务器启动!");
        echoServer.start();
        System.out.println("服务器关闭！");
    }

}
