package org.niels1286.nio.service.intf;

import org.niels1286.nio.data.MessageCallback;
import org.niels1286.nio.data.Node;
import org.niels1286.nio.service.impl.NioServiceImpl;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Niels
 * @date 2018/2/5
 */
public class NioServiceTest {
    @Test
    public void test() throws IOException {
        String ip = "127.0.0.1";
        int port = 7777;
        NioService service = new NioServiceImpl();
        service.startServer(ip, port);
        service.connect(ip, port);
        service.onRecieve(new TestCallback());
        while (service.getNodeList().isEmpty()) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (true)
            for (Node node : service.getNodeList()) {
                service.send(node.getNodeId(), "abcdefg".getBytes("UTF-8"));
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }


    class TestCallback implements MessageCallback {
        @Override
        public void recieve(String node, byte[] msg) {
            try {
                System.out.println(node + "===" + new String(msg, "UTF-8"));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
