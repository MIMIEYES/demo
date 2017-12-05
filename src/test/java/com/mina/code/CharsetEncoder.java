package com.mina.code;

import com.mina.MinaCommon;
import com.rsa.RSAUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.omg.IOP.Encoding;

import java.nio.charset.Charset;
import java.security.spec.EncodedKeySpec;

import static com.mina.MinaCommon.ENCODING;
import static com.mina.MinaCommon.MSGLENGTH;

/**
 * Created by Pierreluo on 2017/12/5.
 */
public class CharsetEncoder implements ProtocolEncoder{
    @Override
    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
        System.out.println("encode start.");
        String msg = message.toString();
        if(MinaCommon.isEncrypt) {
            msg = RSAUtil.encrypt(msg, ENCODING);
        }
        int msgLength = msg.getBytes(ENCODING).length + MSGLENGTH;
        msg = StringUtils.leftPad("" + msgLength, MSGLENGTH, "0");

        Charset charset = Charset.forName(ENCODING);
        byte[] bytes = msg.getBytes(charset);
        //IoBuffer buff = IoBuffer.allocate(100).setAutoExpand(true);
        //buff.putString(msg, charset.newEncoder());
        IoBuffer buff = IoBuffer.allocate(bytes.length);
        buff.clear();
        buff.put(bytes);
        buff.flip();
        out.write(buff);
        System.out.println("encode end. buff.length = " + buff.remaining() + "; message = " + msg);
    }

    @Override
    public void dispose(IoSession ioSession) throws Exception {
    }
}
