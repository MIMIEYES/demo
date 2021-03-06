package com.netty.server;

import com.netty.common.GatewayService;
import com.netty.common.ThreadPool;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

import java.io.UnsupportedEncodingException;

import static com.netty.common.CommonUtil.closeServer;

/**
 * Created by Pierreluo on 2017/12/6.
 */
public class HelloWorldServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        String uuid = ctx.channel().id().asLongText();
        System.out.println("客户端连接关闭.ID: " + uuid);
        GatewayService.removeGatewayChannel(uuid);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {
        System.out.print("receive client msg:");
        ByteBuf buf = (ByteBuf) msg;
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        String strMsg = new String(bytes, "UTF-8");
        System.out.println(strMsg);
        buf.release();

        // 异步处理客户端消息
        String uuid = ctx.channel().id().asLongText();
        SocketChannel socketChannel = GatewayService.getGatewayChannel(uuid);

        ThreadPool.addTask(socketChannel, strMsg);

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String uuid = ctx.channel().id().asLongText();
        GatewayService.addGatewayChannel(uuid, (SocketChannel)ctx.channel());
        System.out.println("a new connect come in: " + uuid);
    }

}


