package HellowNetty.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * 服务器端
 */
public class EchoServer {
    public final int port;//端口

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
        int port = 9999;
        EchoServer echoServer = new EchoServer(port);
        System.out.println("服务器即将启动");
        echoServer.start();
        System.out.println("服务器关闭");

    }

    public  void start() throws InterruptedException {
        final EchoServerHandler serverHandler = new EchoServerHandler();
        //线程组
        EventLoopGroup group = new NioEventLoopGroup();
        //work可以传入到group中，服务端可以，客户端不可以
        EventLoopGroup work = new NioEventLoopGroup();
        try{
            //服务端启动必备
            ServerBootstrap b = new ServerBootstrap();
            //切换Epoll通讯模式（1）
//            EpollEventLoopGroup eventExecutors = new EpollEventLoopGroup();
            //把线程组交给启动类
            b.group(group)
                    //金庸了Nagle算法，一旦配置之后，通道打开之后不可更改，childOption用于配置一些信息
                    .childOption(ChannelOption.TCP_NODELAY,true)
                    //指明服务端使用NIO进行网络通讯
                    .channel(NioServerSocketChannel.class)
                    //切换Epoll通讯模式（2）
//                    .channel(EpollServerSocketChannel.class);
                    //指明服务器监听端口
                    .localAddress(new InetSocketAddress(port))
                    //指定处理器,接受到连接请求，新启一个socket通信，也就是所谓的channel。
                    //接受到连接请求，新启一个socket通信，就是channel，每个channel有自己的一个事件处理的Handler
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(serverHandler);
                        }
                    });
            //加上sync()后，绑定到服务器阻塞等待直到连接完成
            ChannelFuture f = b.bind().sync();
            //阻塞，直到channel关闭
            f.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully().sync();
        }
    }
}
