package org.d.xpathx.data;

import org.d.xpathx.parse.ZPathParser;
import org.w3c.dom.Node;

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

    public DNode(ZPathParser parser, Node node) {
        this.parser = parser;
        this.node = node;
        this.name = node.getNodeName();
    }

    public DNode evalNode(String expression) throws Exception {
        return parser.evalNode(node,expression);
    }

    public Node getNode() {
        return node;
    }
}
