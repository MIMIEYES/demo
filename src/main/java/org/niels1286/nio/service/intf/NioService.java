package org.niels1286.nio.service.intf;

import org.niels1286.nio.data.MessageCallback;
import org.niels1286.nio.data.Node;
import org.niels1286.nio.data.NodeFilter;

import java.io.IOException;
import java.util.List;

/**
 * @author Niels
 * @date 2018/2/3
 */
public interface NioService {

    void startServer(String ip, int port) throws IOException;

    void shutdownServer() throws IOException;

    void connect(String ip, int port) throws IOException;

    void disconnect(String nodeId);

    void broadcast(String[] nodes, byte[] msg);

    void send(String node, byte[] msg);

    void onAccept(NodeFilter filter);

    void onRecieve(MessageCallback callback);

    List<Node> getNodeList();
}
