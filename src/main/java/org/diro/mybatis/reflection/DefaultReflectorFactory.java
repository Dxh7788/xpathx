package org.diro.mybatis.reflection;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Copyright (C) 2017-2018 https://www.htouhui.com - A project by dmybatis
 *
 * @author xh.d
 * @since 2018/7/27 14:33
 */
public class DefaultReflectorFactory implements ReflectorFactory {

    private boolean classCacheEnabled = true;
    private final ConcurrentMap<Class<?>, Reflector> reflectorMap = new ConcurrentHashMap<Class<?>, Reflector>();

    @Override
    public boolean isClassCacheEnabled() {
        return false;
    }

    @Override
    public void setClassCacheEnabled(boolean classCacheEnabled) {
        this.classCacheEnabled = classCacheEnabled;
    }

    @Override
    public Reflector findForClass(Class<?> type) {
        /*if (classCacheEnabled) {
            // synchronized (type) removed see issue #461
            Reflector cached = reflectorMap.get(type);
            if (cached == null) {
                cached = new Reflector(type);
                reflectorMap.put(type, cached);
            }
            return cached;
        } else {*/
            return new Reflector(type);
//        }
    }
}
