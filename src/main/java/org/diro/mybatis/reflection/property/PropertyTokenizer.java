package org.diro.mybatis.reflection.property;

import java.util.Iterator;

/**
 * Copyright (C) 2017-2018 https://www.htouhui.com - A project by dmybatis
 *
 * @author xh.d
 * @since 2018/7/30 17:22
 */
public class PropertyTokenizer implements Iterator<PropertyTokenizer> {

    private String index;
    private String name;
    private String indexName;
    private String children;

    public PropertyTokenizer(String fullname) {
        int delim = fullname.indexOf(".");
        if (delim > -1) {
            name = fullname.substring(0, delim);
            children = fullname.substring(delim + 1);
        } else {
            name = fullname;
            children = null;
        }
        indexName = name;
        delim = name.indexOf("[");
        if (delim > -1) {
            index = name.substring(delim + 1, name.length() - 1);
            name = name.substring(0, delim);
        }
    }

    @Override
    public boolean hasNext() {
        return children != null;
    }

    @Override
    public PropertyTokenizer next() {
        return new PropertyTokenizer(children);
    }

    @Override
    public void remove() {
    }

    public String getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public String getIndexName() {
        return indexName;
    }

    public String getChildren() {
        return children;
    }
}
