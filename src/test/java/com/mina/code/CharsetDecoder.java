package com.mina.code;

import com.mina.MinaCommon;
import com.rsa.RSAUtil;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import static com.mina.MinaCommon.ENCODING;
import static com.mina.MinaCommon.MSGLENGTH;

/**
 * Created by Pierreluo on 2017/12/5.
 */
public class CharsetDecoder extends CumulativeProtocolDecoder {
    @Override
    protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
        System.out.println("doDecode start in =" + in.hashCode() + "; in.length=" + in.remaining() + "; this=" + this.hashCode());
        boolean result = false;
        try {
            int msgLength = MinaCommon.getDecodeMsgLength();
            String msg = null;
            if(msgLength == 0 && in.remaining() >= MSGLENGTH) {
                byte[] bytes = new byte[MSGLENGTH];
                in.get(bytes);
                String length = new String(bytes, ENCODING);
                System.out.println("doDecode msgLength string = " + length);
                try {
                    msgLength = Integer.parseInt(length);
                    if(msgLength < MSGLENGTH) {
                        System.err.println("Invalid Msg length [" + msgLength + "]");
                        out.write("");
                        return result;
                    }
                    MinaCommon.setDecoderMsgLength(msgLength);
                } catch (Exception e) {
                    out.write("");
                    return result;
                }
            }
            System.out.println("current msglength = " + msgLength);
            if(msgLength > 0 && in.remaining() + MSGLENGTH >= msgLength) {
                MinaCommon.removeDecodeMsgLength();
                byte[] bytes = new byte[msgLength - MSGLENGTH];
                in.get(bytes);
                String encryptedMsg = new String(bytes, ENCODING);
                msg = encryptedMsg;
                if(MinaCommon.isEncrypt) {
                    msg = RSAUtil.decrypt(msg, ENCODING);
                }

                out.write(msg);
                result = true;
            }
            System.out.println("doDecode end. result=" + result + "; msgLength=" + msgLength + "; msg=" + msg);
        } catch (Exception e)  {
            System.err.println(e.getMessage());
            throw e;
        }

        return result;
    }
}
