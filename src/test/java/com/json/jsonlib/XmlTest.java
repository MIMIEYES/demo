package com.json.jsonlib;

import com.temp.BaseRes;
import com.temp.BaseRes1;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;
import org.apache.commons.beanutils.BeanUtils;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Created by Pierreluo on 2017/7/7.
 */
public class XmlTest {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        run1();

    }
    public static void run0() {
        XMLSerializer xml = new XMLSerializer();
        String msg = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><project>" +
                "<modelVersion>4.0.0</modelVersion>" +
                "<groupId>com.mimieye</groupId>" +
                "<artifactId>demo</artifactId>" +
                "<version>0.0.1-SNAPSHOT</version>" +
                "<packaging>jar</packaging></project>";
        Map map = (Map)JSONObject.toBean((JSONObject) xml.read(msg),Map.class);
        System.out.println(map);
    }

    private static void run1() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        String xmlall = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\"?>\n" +
                "<in>\n" +
                "    <head>\n" +
                "        <version>1.0.1</version>\n" +
                "        <instID>088025896696</instID>\n" +
                "        <servName>F635</servName>\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <busiNum>string</busiNum>\n" +
                "        <memType>string</memType>\n" +
                "        <opeType>string</opeType>\n" +
                "        <memName>string</memName>\n" +
                "        <regisName>string</regisName>\n" +
                "        <identiType>1</identiType>\n" +
                "        <identiNum>string</identiNum>\n" +
                "        <busiLicense>string</busiLicense>\n" +
                "        <taxRegisNum>string</taxRegisNum>\n" +
                "        <bindOpenOrgNum>string</bindOpenOrgNum>\n" +
                "        <bindOpenBankName>string</bindOpenBankName>\n" +
                "        <cardType>string</cardType>\n" +
                "        <bindAccNum>string</bindAccNum>\n" +
                "        <bindAccName>string</bindAccName>\n" +
                "        <isCrossLine>string</isCrossLine>\n" +
                "        <phoneNum>string</phoneNum>\n" +
                "    </body>\n" +
                "</in>\n";
        XMLSerializer xml = new XMLSerializer();
        Map<String, Object> map = (Map) JSONObject.toBean((JSONObject) xml.read(xmlall), Map.class);
        Object head = map.get("head");
        Object body = map.get("body");
        //Map<String, Object> head = BeanUtils.objToMapObject(map.get("head"));
        //Map<String, Object> body = BeanUtils.objToMapObject(map.get("body"));
        //Map<String, Object> dynaClass = BeanUtils.objToMapObject(head.get("dynaClass"));
        //System.out.println("dynaClass=============" + org.apache.commons.beanutils.BeanUtils.getProperty(head, "dynaClass"));
        System.out.println("head=============" + head);
        System.out.println("body=============" + body);
        System.out.println("all=============" + map);

        System.out.println(BeanUtils.getProperty(head, "instID"));
        //System.out.println(BeanUtils.getProperty(body, "memName"));
        //BaseRes bq = new BaseRes();
        //BeanUtils.copyProperties(bq, head);
        //System.out.println(bq.getInstID());
        //System.out.println(bq.getVersion()+ " - " + bq.getServName());

        BaseRes1 bq1 = new BaseRes1();
        //mapper.map(bq, bq1);
        mapper.map(head, bq1);
        System.out.println(bq1.getInstID());
        System.out.println(bq1.getVersion()+ " - " + bq1.getServName());

    }
    private static Mapper mapper = new DozerBeanMapper();
}
