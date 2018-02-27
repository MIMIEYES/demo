package org.niels1286.nio.data;

import org.niels1286.nio.constant.NodeStatusEnum;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author: Niels Wang
 * @date: 2018/2/3
 */
public class Node {

    private final SocketChannel channel;
    private final int type;
    private String nodeId;
    private String currentId;

    private NodeStatusEnum status = NodeStatusEnum.Unconnected;

    public Node(SocketChannel channel ,int type) {
        this.channel = channel;
        this.type = type;
    }

    public boolean send(byte[] msg) throws IOException {
        if (channel == null) {
          return false;
        }

            channel.write(ByteBuffer.wrap(msg));
        return true;
    }

    public void destroy() throws IOException {
        if(null==channel){
            return;
        }
        this.channel.shutdownInput();
        this.channel.shutdownOutput();
    }

    public String getNodeId() {
        if(null==nodeId){
            return currentId;
        }
        return nodeId;
    }

    public NodeStatusEnum getStatus() {
        return status;
    }

    public void setStatus(NodeStatusEnum status) {
        this.status = status;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getCurrentId() {
        return currentId;
    }

    public void setCurrentId(String currentId) {
        this.currentId = currentId;
    }

    public int getType() {
        return type;
    }
}
