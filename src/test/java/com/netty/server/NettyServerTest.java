package com.netty.server;

import com.netty.client.NettyClientTest;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.concurrent.Executors;

import static com.netty.common.CommonUtil.*;

/**
 * Created by Pierreluo on 2017/5/18.
 */
public class NettyServerTest {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("启动服务端.");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                NettyServerTest server = new NettyServerTest();
                server.startUp();
            }
        };
        Thread thread = new Thread(runnable, "server-pierre");
        thread.start();

        System.out.println("监听消息发送队列并发送消息.");
        Runnable listener = new Runnable() {
            @Override
            public void run() {
                while (RUNNING) {
                    String taskStr = null;
                    String name = Thread.currentThread().getName();
                    System.out.println(name + "-线程[消息发送队列]准备读取消息.");
                    synchronized (SEND_QUEUE) {
                        while(RUNNING && SEND_QUEUE.isEmpty()) {
                            try {
                                System.out.println( name + "-线程[消息发送队列]等待消息.");
                                SEND_QUEUE.wait();
                            } catch (Exception e) {
                                Thread.currentThread().interrupt();
                                return;
                            }
                        }
                        taskStr = SEND_QUEUE.poll();
                    }
                    if(StringUtils.isNotBlank(taskStr)) {
                        System.out.println(name + "-线程[消息发送队列]发送数据.");
                        sendServerMsg(taskStr);
                    }

                }
                System.out.println("服务端停止监听发送消息.");
            }
        };
        Thread sendThread = new Thread(listener, "server_listen_and_send_msg");
        sendThread.start();

        System.out.println("生产消息.");
        Scanner scanner = new Scanner(System.in);
        String input = null;
        while (scanner.hasNextLine()) {
            input = scanner.nextLine();
            if(!"exit".equals(input)) {
                addMsg(input);
            } else {
                System.out.println("准备关闭服务端.");
                closeServer();
                break;
            }

        }

    }

    public void startUp() {
        //EventLoopGroup boss = null;
        //EventLoopGroup worker = null;
        try {
            ServerBootstrap boot = new ServerBootstrap();
            boss = new NioEventLoopGroup();
            worker = new NioEventLoopGroup();
            boot.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    // 保持长连接
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            //p.addLast(new LoggingHandler(LogLevel.DEBUG));
                            p.addLast("encoder0",new LengthFieldPrepender(8, false));
                            p.addLast("encoder1",new StringEncoder(Charset.forName("UTF-8")));
                            p.addLast("decoder",new LengthFieldBasedFrameDecoder(1024 * 1024, 0, 8, 0, 8));
                            p.addLast( new HelloWorldServerHandler());
                        }
                    });
            // Start the server.
            ChannelFuture f = boot.bind(11111).sync();


            // Wait until the server socket is closed.
            f.channel().closeFuture().sync();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            // Shut down all event loops to terminate all threads.
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }

    }
}
