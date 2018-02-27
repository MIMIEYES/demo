package org.niels1286.nio.constant;

/**
 * @author: Niels Wang
 * @date: 2018/2/3
 */
public enum NodeStatusEnum {

    Unconnected(-1),CONNECTING(1), CONNECTED(2), FAILED(0);

    private final int status;

    NodeStatusEnum(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
