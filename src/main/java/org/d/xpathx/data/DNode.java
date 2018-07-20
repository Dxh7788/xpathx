package org.d.xpathx.data;

import org.d.xpathx.parse.ZPathParser;
import org.w3c.dom.Node;

import java.util.Properties;

/**
 * Copyright (C) 2017-2018 https://www.htouhui.com - A project by xpathx
 *
 * @author xh.d
 * @since 2018/7/20 11:24
 */
public class DNode {
    private ZPathParser parser;
    private String name;
    private Node node;
    private Properties variables;
    public DNode(ZPathParser parser, Node node) {
        this.parser = parser;
        this.node = node;
        this.name = node.getNodeName();
    }

    public DNode(ZPathParser parser, Node node,Properties variables){
        this(parser,node);
        this.variables = variables;
    }
    public DNode evalNode(String expression) throws Exception {
        return parser.evalNode(node,expression);
    }

    public Node getNode() {
        return node;
    }
}