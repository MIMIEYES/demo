package com.netty.client;

import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.nio.charset.Charset;

/**
 * Created by Pierreluo on 2017/12/6.
 */
public class MyChannelInitializer<T extends ChannelInboundHandlerAdapter> extends ChannelInitializer<SocketChannel> {
    private T t;

    public MyChannelInitializer(T t){
        this.t = t;
    }

    public MyChannelInitializer(){

    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline p = socketChannel.pipeline();
        p.addLast(new LoggingHandler(LogLevel.DEBUG));
        p.addLast("encoder0",new LengthFieldPrepender(8,false));
        p.addLast("encoder1",new StringEncoder(Charset.forName("UTF-8")));
        p.addLast(
                //new ObjectEncoder(),
                //new ObjectDecoder(ClassResolvers.cacheDisabled(null)),
                t);
    }
}
