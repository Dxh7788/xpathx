package org.diro.mybatis.binding;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Copyright (C) 2017-2018 https://www.htouhui.com - A project by dmybatis
 *
 * @author xh.d
 * @since 2018/8/3 16:27
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {
    private static final long serialVersionUID = -6420140198037351200L;

    private String session;
    private Class<T> mapperInterface;
    private Map<Method, String> methodCache = new ConcurrentHashMap<Method, String>();

    public MapperProxy(String session, Class<T> mapperInterface, Map<Method, String> methodCache) {
        this.session = session;
        this.mapperInterface = mapperInterface;
        this.methodCache = methodCache;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Proxy 调用成功");
        return null;
    }
}
