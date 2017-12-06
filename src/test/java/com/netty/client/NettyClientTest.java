package com.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * Created by Pierreluo on 2017/5/18.
 */
public class NettyClientTest {
    EventLoopGroup group = new NioEventLoopGroup();
    Bootstrap boot = null;

    public static void main(String[] args) {
        NettyClientTest client = new NettyClientTest();
        client.startUp();
    }

    void startUp(){
        try {
            boot = new Bootstrap();
            boot.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true);
            boot.handler(new LoggingHandler(LogLevel.DEBUG));
            boot.handler(new MyChannelInitializer<>(new RequestMsgClientHandler("qqqqqqq")));
            // Start the client
            ChannelFuture f = boot.connect("localhost",11111).sync();

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


