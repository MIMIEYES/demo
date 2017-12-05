package com.mina.client;

import com.mina.code.CharsetDecoder;
import com.mina.code.CharsetEncoder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ReadFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.*;
import org.apache.mina.transport.socket.SocketConnector;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import javax.print.attribute.standard.PrinterURI;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * Created by Pierreluo on 2017/12/5.
 */
public class MinaClient {
    private SocketConnector connector;
    private IoSession session;
    private CumulativeProtocolDecoder decoder;
    private ProtocolEncoder encoder;

    private String remoteServer;
    private int port;
    private int retryTimes;
    private int retryInterval;
    private int timeout;

    public MinaClient() {
        this.decoder = new CharsetDecoder();
        this.encoder = new CharsetEncoder();
    }

    public MinaClient(CumulativeProtocolDecoder decoder, ProtocolEncoder encoder, String host, int port) {
        this.decoder = decoder;
        this.encoder = encoder;
        this.remoteServer = host;
        this.port = port;
    }

    public String sendAndReceiveImp(String msg) throws Exception {
        String retMsg = null;
        connector = new NioSocketConnector();
        connector.setConnectTimeoutMillis(timeout * 1000);
        DefaultIoFilterChainBuilder filterChain = connector.getFilterChain();
        filterChain.addLast("codec", new ProtocolCodecFilter(
                new ProtocolCodecFactory() {
                    @Override
                    public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
                        return encoder;
                    }

                    @Override
                    public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
                        return decoder;
                    }
                }
        ));
        SocketSessionConfig cfg = connector.getSessionConfig();
        cfg.setUseReadOperation(true);

        try {
            session = connector.connect(new InetSocketAddress(remoteServer, port)).awaitUninterruptibly().getSession();
            session.write(msg).awaitUninterruptibly();

            System.out.println("mina client send msg: " + msg);
            ReadFuture readFuture;
            readFuture = session.read();
            if(readFuture.awaitUninterruptibly(timeout , TimeUnit.SECONDS)) {
                retMsg = (String ) readFuture.getMessage();
                System.out.println("mina client receive msg:" + retMsg);
            } else {
                System.out.println("mina client receive msg: Timeout");
                throw new Exception("timeout");
            }
        } finally {
            this.close();
        }
        return retMsg;
    }

    private void close() {
        if(session != null) {
            session.closeOnFlush();
            session.getService().dispose();
        }
    }

    public String sendAndReceive(String msg) throws Exception {
        int failedcnt = 1;
        while (failedcnt <= retryTimes) {
            try {
                String retMsg = sendAndReceiveImp(msg);
                if(verifyAckMsg(msg, retMsg)) {
                    return retMsg;
                }
                failedcnt++;
            } catch (Exception e) {
                System.out.println("failed to communicate with remoteServer");

                try {
                    TimeUnit.MILLISECONDS.sleep(retryInterval);
                } catch (InterruptedException e1) {

                }
                failedcnt++;
            }
        }
        return null;
    }

    private boolean verifyAckMsg(String msg, String retMsg) {
        return true;
    }
}
