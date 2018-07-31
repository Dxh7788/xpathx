package org.diro.mybatis.reflection.wrapper;

import org.diro.mybatis.reflection.MetaObject;

/**
 * Copyright (C) 2017-2018 https://www.htouhui.com - A project by dmybatis
 *
 * @author xh.d
 * @since 2018/7/31 9:39
 */
public abstract class BaseWrapper implements ObjectWrapper {
    protected static final Object[] NO_ARGUMENTS = new Object[0];
    protected final MetaObject metaObject;

    public BaseWrapper(MetaObject metaObject) {
        this.metaObject = metaObject;
    }
    //暂时不考虑集合对象
}
