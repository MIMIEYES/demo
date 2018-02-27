package org.niels1286.nio.manager;

import org.niels1286.nio.client.NioClient;
import org.niels1286.nio.constant.NodeStatusEnum;
import org.niels1286.nio.data.Node;
import org.niels1286.nio.server.NioServer;
import org.niels1286.nio.data.MessageCallback;
import org.niels1286.nio.data.NodeFilter;

import java.io.IOException;
import java.nio.channels.Selector;
import java.util.*;

/**
 * @author: Niels Wang
 * @date: 2018/2/3
 */
public class NioManager {

    private static final NioManager INSTANCE = new NioManager();
    private NioServer server;

    private NioManager() {
        try {
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static NioManager getInstance() {
        return INSTANCE;
    }

    private Selector selector;

    private final Map<String, Node> nodeMap = new HashMap<>();

    private List<MessageCallback> callbackList = new ArrayList<>();

    private List<NodeFilter> filterList = new ArrayList<>();


    public Selector getSelector() {
        return selector;
    }

    public boolean putNode(String id, Node node) {
        if (id == null || id.trim().length() == 0 || node == null) {
            throw new RuntimeException("parameter error");
        }
        if (nodeMap.containsKey(id)) {
            return false;
        }
        nodeMap.put(id, node);
        return true;
    }

    public Node getNode(String id) {
        return nodeMap.get(id);
    }

    public void removeNode(String id) {
        Node node = nodeMap.get(id);
        if (null == node) {
            return;
        }
        try {
            node.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        }
        nodeMap.remove(id);
    }

    public void changeStatus(String nodeId, NodeStatusEnum status) {
        Node node = nodeMap.get(nodeId);
        if (null == node) {
            return;
        }
        node.setStatus(status);
    }

    public void recieve(String nodeId, byte[] data) {
        for (MessageCallback callback : callbackList) {
            callback.recieve(nodeId, data);
        }
    }

    public void addCallback(MessageCallback callback) {
        if (null == callback) {
            return;
        }
        callbackList.add(callback);
    }

    /**
     * @param node
     * @return
     */
    public boolean verifyNode(Node node) {
        boolean result = true;
        for (NodeFilter filter : filterList) {
            if (!result) {
                break;
            }
            result = filter.filter(node);
        }
        return result;
    }

    public void addFilter(NodeFilter filter) {
        if (null == filter) {
            return;
        }
        filterList.add(filter);
    }

    public void broadcast(String[] nodes, byte[] msg) {
        for (String nodeId : nodes) {
            send(nodeId, msg);
        }
    }

    public void send(String nodeId, byte[] msg) {
        Node node = nodeMap.get(nodeId);
        if (null != node) {
            try {
                node.send(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Node> getNodeList() {
        return new ArrayList<>(this.nodeMap.values());
    }

    public void startServer(String ip, int port) throws IOException {
        server = new NioServer();
        server.start(ip, port);
        SelectorListener.start();

    }

    public void shutdownServer() throws IOException {
        if (null == server) {
            return;
        }
        server.shutdown();
        server = null;
        Set<String> nodeIdSet = new HashSet<>(nodeMap.keySet());
        for (String nodeId : nodeIdSet) {
            Node node = nodeMap.get(nodeId);
            if (node.getType() == 0) {
                nodeMap.remove(nodeId);
            }
        }
    }

    public void connect(String ip, int port) throws IOException {
        NioClient client = new NioClient();
        client.initClient(ip, port);
        SelectorListener.start();

    }
}
