package org.diro.mybatis.reflection;

/**
 * Copyright (C) 2017-2018 https://www.htouhui.com - A project by dmybatis
 *
 * @author xh.d
 * @since 2018/7/27 14:31
 */
public interface ReflectorFactory {
    boolean isClassCacheEnabled();

    void setClassCacheEnabled(boolean classCacheEnabled);

    Reflector findForClass(Class<?> type);
}
