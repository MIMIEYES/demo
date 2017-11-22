package com.temp;

import lombok.Getter;
import lombok.Setter;
import net.sf.json.JSONArray;
import net.sf.json.xml.XMLSerializer;
import org.springside.modules.utils.mapper.XmlMapper;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Setter
@Getter
public class In {
    private BaseRes head;
    private Object body;


    public static void main(String[] args) {
        In in = new In();
        BaseRes b = new BaseRes();
        F635Res f = new F635Res();
        in.setHead(b);
        in.setBody(f);
        b.setInstID("123");
        f.setBusiNum("8888888");

        String s = XmlMapper.toXml(in, in.getClass(), "gbk");
        System.out.println(s);

        XMLSerializer xml = new XMLSerializer();
        String r = xml.write(JSONArray.fromObject(in), "gb2312");
        System.out.println(r);
        //In<BaseRes, F635Res> in1 = XmlMapper.fromXml(s, in.getClass());
        //
        //System.out.println(in1.getBody().getBusiNum()+" - "+in1.getHead().getInstID());


        //HashMap<String, BaseRes> in = new HashMap<>();
        //
        //b.setInstID("123");
        //f.setBusiNum("8888888");
        //in.put("head", b);
        //in.put("body", f);
        //String s = XmlMapper.toXml(in,"in", in.getClass(), "gbk");
        //System.out.println(s);
        //
        //In in1 = XmlMapper.fromXml(s, In.class);
        //System.out.println(in1.getBody().getBusiNum()+" - "+in1.getHead().getInstID());

    }

}
