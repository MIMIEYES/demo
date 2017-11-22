package com.temp;

import lombok.Getter;
import lombok.Setter;
import org.springside.modules.utils.mapper.XmlMapper;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@XmlRootElement
public class Temp {
    private Base0 head;
    private Base1 body;

    public static void main(String[] args) {
        Base0 b0 = new Base0();
        Base1 b1 = new Base1();
        b0.setId("1");
        b0.setVersion("qqq");
        b1.setAcctName("lihui");
        b1.setAcctNum("88888888");

        Temp temp = new Temp();
        temp.setBody(b1);
        temp.setHead(b0);
        List<Temp> list = new ArrayList<>();
        list.add(temp);

        String str = XmlMapper.toXml(list, "", Temp.class, "gbk");

        System.out.println(str);
        List<Temp> list1 = XmlMapper.fromXml(str, list.getClass());
        System.out.println(list1.get(0).getBody().getAcctNum());

        //String str1=  XmlMapper.toXml(temp, Temp.class, "gbk");
        //System.out.println(str1);
        //
        //Temp temp1 = XmlMapper.fromXml(str1, Temp.class);
        //System.out.println(temp1.getBody().getAcctName());

    }
}
