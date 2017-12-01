package com.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created on 2017/11/22.
 */
public class MapToXml {
    /**
     * map转xml map中没有根节点的键
     */
    public static Document map2xml(Map<String, Object> map, String rootName) throws DocumentException, IOException {
        Document doc = DocumentHelper.createDocument();
        Element root = DocumentHelper.createElement(rootName);
        doc.add(root);
        map2xml(map, root);
        return doc;
    }

    /**
     * map转xml map中含有根节点的键
     */
    public static Document map2xml(Map<String, Object> map) throws DocumentException, IOException {
        Iterator<Map.Entry<String, Object>> entries = map.entrySet().iterator();
        if (entries.hasNext()) {
            Map.Entry<String, Object> entry = entries.next();
            Document doc = DocumentHelper.createDocument();
            Element root = DocumentHelper.createElement(entry.getKey());
            doc.add(root);
            map2xml((Map) entry.getValue(), root);
            return doc;
        }
        return null;
    }

    /**
     * map转xml
     */
    private static Element map2xml(Map<String, Object> map, Element body) {
        Iterator<Map.Entry<String, Object>> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Object> entry = entries.next();
            String key = entry.getKey();
            Object value = entry.getValue();
            if (key.startsWith("@")) {
                body.addAttribute(key.substring(1, key.length()), value.toString());
            } else if (key.equals("#text")) {
                body.setText(value.toString());
            } else {
                if (value instanceof List) {
                    List list = (List) value;
                    Object obj;
                    for (int i = 0; i < list.size(); i++) {
                        obj = list.get(i);
                        if (obj instanceof Map) {
                            Element subElement = body.addElement(key);
                            map2xml((Map) list.get(i), subElement);
                        } else {
                            body.addElement(key).setText((String) list.get(i));
                        }
                    }
                } else if (value instanceof Map) {
                    Element subElement = body.addElement(key);
                    map2xml((Map) value, subElement);
                } else if (null != value) {
                    body.addElement(key).setText(value.toString().trim());
                } else {
                    body.addElement(key).setText("");
                }
            }
        }
        return body;
    }

    /**
     * 格式化输出xml
     */
    public static String formatXml(String xmlStr, String encoding) throws DocumentException, IOException {
        Document document = DocumentHelper.parseText(xmlStr);
        return formatXml(document, encoding);
    }

    /**
     * 格式化输出xml
     */
    public static String formatXml(Document document, String encoding) throws DocumentException, IOException {
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding(encoding);
        StringWriter writer = new StringWriter();
        XMLWriter xmlWriter = new XMLWriter(writer, format);
        xmlWriter.write(document);
        xmlWriter.close();
        return writer.toString();
    }
}
