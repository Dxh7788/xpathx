package org.diro.mybatis.reflection;

import org.diro.mybatis.reflection.invoker.Invoker;

/**
 * @author xh.d
 * @since 2018/7/27 15:44
 */
public class MetaClass {
    private ReflectorFactory reflectorFactory;
    private Reflector reflector;
    private Class<?> type;

    private MetaClass(Class<?> type, ReflectorFactory reflectorFactory) {
        this.type = type;
        this.reflectorFactory = reflectorFactory;
        this.reflector = reflectorFactory.findForClass(type);
    }

    public static MetaClass forClass(Class<?> type, ReflectorFactory reflectorFactory) {
        return new MetaClass(type, reflectorFactory);
    }

    public Invoker getSetInvoker(String name) {
        return reflector.getSetInvoker(name);
    }
}
