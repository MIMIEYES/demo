package org.niels1286.nio.server;

import org.niels1286.nio.manager.NioManager;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;

/**
 * @author: Niels Wang
 * @date: 2018/2/3
 */
public class NioServer {

    private ServerSocketChannel serverSocketChannel;

    public void start(String ip, int port) throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(ip,port));
        // 将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_ACCEPT事件,注册该事件后，
        // 当该事件到达时，selector.select()会返回，如果该事件没到达selector.select()会一直阻塞。
        serverSocketChannel.register(NioManager.getInstance().getSelector(), SelectionKey.OP_ACCEPT);
    }


    public void shutdown() throws IOException {
        serverSocketChannel.close();
    }
}
