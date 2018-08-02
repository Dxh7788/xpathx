package org.diro.mybatis.data;

import org.diro.mybatis.parsing.XPathParser;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Copyright (C) 2017-2018 https://www.htouhui.com - A project by xpathx
 *
 * @author xh.d
 * @since 2018/7/20 11:24
 */
public class XNode {
    private XPathParser parser;
    private String name;
    private Node node;
    private Properties attributes;//属性property
    private String body;//标签的文本值<a>123</a>,body=123
    private Properties variables;
    public XNode(XPathParser parser, Node node) {
        this.parser = parser;
        this.node = node;
        this.name = node.getNodeName();
        this.attributes = parseAttributes();//解析属性
        this.body = parseBody(node);//解析body
    }

    public XNode(XPathParser parser, Node node, Properties variables){
        this(parser,node);
        this.variables = variables;
    }
    public XNode evalNode(String expression) throws Exception {
        return parser.evalNode(node,expression);
    }

    public Node getNode() {
        return node;
    }

    private Properties parseAttributes() {
        attributes = new Properties();
        NamedNodeMap attributesMap = node.getAttributes();
        for (int i =0;i<attributesMap.getLength();i++){
            Node attrNode = attributesMap.item(i);
            attributes.setProperty(attrNode.getNodeName(), attrNode.getNodeValue());

        }
        return attributes;
    }

    private String parseBody(Node child) {
        if (child.getNodeType() == Node.ELEMENT_NODE) {
            String data = child.getTextContent();
            return data;
        }
        return null;
    }
    public String getStringAttribute(String name){
        if (attributes!=null){
            return attributes.getProperty(name);
        }
        return null;
    }
    public Boolean getBoolenAttribute(String name){
        if (attributes!=null){
            String value = attributes.getProperty(name);
            if (null != value){
                return Boolean.valueOf(value);
            }
        }
        return Boolean.valueOf(false);
    }
    public Integer getIntegerAttribute(String name){
        if (attributes!=null){
            String value = attributes.getProperty(name);
            if (null !=value){
                return Integer.valueOf(value);
            }
        }
        return null;
    }
    public Double getDoubleAttribute(String name){
        if (attributes!=null){
            String value = attributes.getProperty(name);
            if (null !=value){
                return Double.valueOf(value);
            }
        }
        return null;
    }

    public String getStringBody(String def){
        if (body !=null){
            return body;
        }
        if (def !=null){
            return def;
        }
        return null;
    }

    public List<XNode> evalNodes(String expression) {
        return parser.evalNodes(node, expression);
    }

    public List<XNode> getChildren() {
        List<XNode> xNodes = new ArrayList<XNode>(0);
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                xNodes.add(new XNode(parser, node));
            }
        }
        return xNodes;
    }

    public Properties getChildrenAsProperties() {
        Properties properties = new Properties();
        for (XNode xNode : getChildren()) {
            String name = xNode.getStringAttribute("name");
            String value = xNode.getStringAttribute("value");
            properties.setProperty(name, value);
        }
        return properties;
    }
}
