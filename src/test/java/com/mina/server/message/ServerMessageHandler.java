package com.mina.server.message;

import org.apache.mina.core.future.CloseFuture;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * Created by Pierreluo on 2017/12/5.
 */
public class ServerMessageHandler extends IoHandlerAdapter {

    @Override
    public void sessionClosed(IoSession session) {
        System.out.println("sessionClosed: clear session.");
        CloseFuture closeFuture = session.closeOnFlush();
        closeFuture.addListener(new IoFutureListener<IoFuture>() {
            @Override
            public void operationComplete(IoFuture ioFuture) {
                if(ioFuture instanceof CloseFuture) {
                    ((CloseFuture) ioFuture).setClosed();
                }
            }
        });
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) {
        System.out.println("this connection is idle:"+ session.getRemoteAddress() + status +", close now.");
        session.closeNow();
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        System.out.println("create new connection: " + session.getRemoteAddress());
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        System.out.println("session opened:" + session.getId() + session.getBothIdleCount());
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        sessionClosed(session);
        System.err.println("Server Exception:" + cause.getMessage());
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        String content = "";
        if(message != null) {
            content = (String ) message;
            System.out.println("Server receive message: " + content);
        }

        try {
            // handle msg
        } catch (Exception e) {
            System.err.println("Error when handling msg [" + content + "]");
            e.printStackTrace();
        } finally {
            sessionClosed(session);
        }

    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        System.out.println("Server send message:" + message);
    }
}
