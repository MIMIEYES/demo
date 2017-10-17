package com.json.jsonlib;

import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import java.util.Map;

/**
 * Created by Pierreluo on 2017/7/7.
 */
public class XmlTest {
    public static void main(String[] args) {
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
}
