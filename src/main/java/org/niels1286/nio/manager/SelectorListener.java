package org.niels1286.nio.manager;

import org.niels1286.nio.constant.NodeStatusEnum;
import org.niels1286.nio.data.Node;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author: Niels Wang
 * @date: 2018/2/3
 */
public class SelectorListener implements Runnable {
    
    private NioManager manager = NioManager.getInstance();

    private static Thread thread = null;

    public static synchronized final void start() {
        if (thread != null) {
            return;
        }
        thread = new Thread(new SelectorListener());
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                // It likes queue.take();
                manager.getSelector().select();
                Iterator<SelectionKey> ite = manager.getSelector().selectedKeys().iterator();
                while (ite.hasNext()) {
                    SelectionKey key = ite.next();
                    ite.remove();
                    if (key.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel channel = server.accept();
                        InetSocketAddress remoteAddress = (InetSocketAddress) channel.getRemoteAddress();
                        String nodeId = remoteAddress.getAddress().getHostAddress() +"-"+ remoteAddress.getPort();
                        channel.configureBlocking(false);
                        channel.register(manager.getSelector(), SelectionKey.OP_READ);
                        Node node = new Node(channel,0);
                        node.setCurrentId(nodeId);
                        node.setStatus(NodeStatusEnum.CONNECTED);
                        boolean result = manager.verifyNode(node);
                        if(!result){
                            //todo 断开连接
                        }
                        manager.putNode(nodeId, node);

                    } else if (key.isReadable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        InetSocketAddress remoteAddress = (InetSocketAddress) channel.getRemoteAddress();
                        String nodeId = remoteAddress.getAddress().getHostAddress()  +"-"+  remoteAddress.getPort();
                        manager.changeStatus(nodeId,NodeStatusEnum.CONNECTED);
                        read(nodeId,channel);
                    } else if (key.isConnectable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        if (channel.isConnectionPending()) {
                            channel.finishConnect();
                        }
                        InetSocketAddress remoteAddress = (InetSocketAddress) channel.getRemoteAddress();
                        String nodeId = remoteAddress.getAddress().getHostAddress()  +"-"+  remoteAddress.getPort();
                        Node node = new Node(channel,1);
                        node.setCurrentId(nodeId);
                        node.setStatus(NodeStatusEnum.CONNECTED);
                        channel.configureBlocking(false);
                        channel.register(manager.getSelector(), SelectionKey.OP_READ);
                        manager.putNode(nodeId, node);
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * 处理读取客户端发来的信息 的事件
     */
    public void read(String nodeId,SocketChannel channel) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(512);
        channel.read(buffer);
        byte[] data = buffer.array();
        manager.recieve(nodeId,data);
    }
}
