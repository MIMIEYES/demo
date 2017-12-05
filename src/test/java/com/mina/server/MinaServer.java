package com.mina.server;

import com.mina.code.CharsetDecoder;
import com.mina.code.CharsetEncoder;
import com.mina.server.message.ServerMessageHandler;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.*;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by Pierreluo on 2017/12/5.
 */
public class MinaServer {
    private String host;
    private Integer port;
    private SocketAcceptor acceptor;
    private CumulativeProtocolDecoder decoder;
    private ProtocolEncoder encoder;
    private Integer timeout;

    public MinaServer() {
        acceptor = new NioSocketAcceptor();
        // 即使端口被占用，也可以绑定
        acceptor.setReuseAddress(true);
        this.encoder = new CharsetEncoder();
        this.decoder = new CharsetDecoder();
    }

    public boolean start() {
        DefaultIoFilterChainBuilder filterChain = acceptor.getFilterChain();
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
        acceptor.setHandler(new ServerMessageHandler());
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, timeout);

        try {
            acceptor.bind(new InetSocketAddress(host, port));
            System.out.println("mina server started.");
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean stop() {
        if(acceptor != null) {
            acceptor.unbind();
            acceptor.dispose(true);
            acceptor = null;
        }
        System.out.println("mina socket stopped.");
        return true;
    }
}
