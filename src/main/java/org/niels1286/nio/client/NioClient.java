package org.niels1286.nio.client;

import org.niels1286.nio.manager.NioManager;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/**
 * @author: Niels Wang
 * @date: 2018/2/3
 */
public class NioClient {

    private SocketChannel channel;

    /**
     * @param ip
     * @param port
     * @throws IOException
     */
    public void initClient(String ip, int port) throws IOException {
        // 获得一个Socket通道
        channel = SocketChannel.open();
        // 设置通道为非阻塞
        channel.configureBlocking(false);

        // 客户端连接服务器,其实方法执行并没有实现连接，需要在listen（）方法中调
        //用channel.finishConnect();才能完成连接
        channel.connect(new InetSocketAddress(ip, port));
        //将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_CONNECT事件。
        channel.register(NioManager.getInstance().getSelector(), SelectionKey.OP_CONNECT);
    }

}
