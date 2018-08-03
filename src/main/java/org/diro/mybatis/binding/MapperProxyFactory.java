package org.diro.mybatis.binding;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Mapper接口代理类实现
 *
 * @author xh.d
 * @since 2018/8/3 16:25
 */
public class MapperProxyFactory<T> {

    private Class<T> mapperInterface;
    private final Map<Method, String> methodCache = new ConcurrentHashMap<>();

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public Class<T> getMapperInterface() {
        return mapperInterface;
    }

    public Map<Method, String> getMethodCache() {
        return methodCache;
    }

    protected T newInstance(MapperProxy<T> mapperProxy) {
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, mapperProxy);
    }

    public T newInstance(String session) {
        MapperProxy<T> mapperProxy = new MapperProxy<>(session, mapperInterface, methodCache);
        return newInstance(mapperProxy);
    }
}
