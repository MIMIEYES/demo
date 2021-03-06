package com.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

import static com.netty.common.CommonUtil.*;

/**
 * Created by Pierreluo on 2017/5/18.
 */
public class NettyClientTest {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("启动客户端.");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                NettyClientTest client = new NettyClientTest();
                client.startUp();
            }
        };
        Thread thread = new Thread(runnable, "client-pierre");
        thread.start();
        waitStartUp.await();

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
                        socketChannel.writeAndFlush(taskStr);
                    }

                }
            }
        };
        Thread sendThread = new Thread(listener, "client_listen_and_send_msg");
        sendThread.start();

        System.out.println("生产消息.");
        Scanner scanner = new Scanner(System.in);
        String input = null;
        while (scanner.hasNextLine()) {
            input = scanner.nextLine();
            if(!"exit".equals(input)) {
                addMsg(input);
            } else {
                System.out.println("准备关闭客户端.");
                closeClient();
                break;
            }

        }

    }

    public void startUp(){
        EventLoopGroup group = null;
        try {
            Bootstrap boot = new Bootstrap();
            group = new NioEventLoopGroup();
            boot.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    //  保持长连接
                    .option(ChannelOption.SO_KEEPALIVE, true);
            //boot.handler(new LoggingHandler(LogLevel.DEBUG));
            boot.handler(new MyChannelInitializer<>(new RequestMsgClientHandler()));
            // Start the client
            ChannelFuture f = boot.connect("localhost",11111).sync();

            if(f.isSuccess()) {
                socketChannel = (SocketChannel) f.channel();
                System.out.println("clientId: " + socketChannel.id().asLongText() + " - 连接成功.");
                waitStartUp.countDown();
            }

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
            System.out.println("***************** client close. ***************** ");
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            // Shut down the event loop to terminate all threads.
            group.shutdownGracefully();
        }
    }
}


