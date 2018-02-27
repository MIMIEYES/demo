package org.niels1286.nio.data;

/**
 * @author Niels
 * @date 2018/2/3
 */
public interface MessageCallback {

    void recieve(String node, byte[] msg);
}
