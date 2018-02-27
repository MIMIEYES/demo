package org.niels1286.nio.service.impl;

import org.niels1286.nio.manager.NioManager;
import org.niels1286.nio.data.Node;
import org.niels1286.nio.data.MessageCallback;
import org.niels1286.nio.service.intf.NioService;
import org.niels1286.nio.data.NodeFilter;

import java.io.IOException;
import java.util.List;

/**
 * @author Niels
 * @date 2018/2/3
 */
public class NioServiceImpl implements NioService {

    private NioManager manager = NioManager.getInstance();


    @Override
    public void startServer(String ip, int port) throws IOException {
        manager.startServer(ip,port);

    }

    @Override
    public void shutdownServer() throws IOException {
       manager.shutdownServer();
    }

    @Override
    public void connect(String ip, int port) throws IOException {
        manager.connect(ip,port);
    }

    @Override
    public void disconnect(String nodeId) {
        manager.removeNode(nodeId);
    }

    @Override
    public void broadcast( String[] nodes,byte[] msg) {
        manager.broadcast(nodes,msg);
    }

    @Override
    public void send( String node,byte[] msg) {
        manager.send(node,msg);
    }

    @Override
    public void onAccept(NodeFilter filter) {
        manager.addFilter(filter);
    }

    @Override
    public void onRecieve(MessageCallback callback) {
        manager.addCallback(callback);
    }

    @Override
    public List<Node> getNodeList() {
        return manager.getNodeList();
    }
}
