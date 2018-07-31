package org.diro.mybatis.reflection;

import org.diro.mybatis.reflection.invoker.Invoker;
import org.diro.mybatis.reflection.property.PropertyTokenizer;

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

    public Invoker getGetInvoker(String name) {
        return reflector.getGetInvoker(name);
    }

    public String[] getGetterNames() {
        return reflector.getGetterNames();
    }

    public String[] getSetterNames() {
        return reflector.getSetterNames();
    }

    public Class<?> getGetterType(String name) {
        return reflector.getGetterType(name);
    }

    public Class<?> getSetterType(String name) {
        return reflector.getSetterType(name);
    }

    //首先得校验上层是否有getter属性.xxx.xxn.name,首先校验xxx是否有getter,然后校验xxn是否有getter,最后校验name.
    public boolean hasGetter(String name) {
        PropertyTokenizer prop = new PropertyTokenizer(name);
        if (prop.hasNext()) {
            if (reflector.hasGetter(prop.getIndexName())) {
                MetaClass metaProp = metaClassForProperty(prop.getIndexName());
                return metaProp.hasGetter(prop.getChildren());
            } else {
                return false;
            }
        } else {
            return reflector.hasGetter(name);
        }
    }

    public MetaClass metaClassForProperty(String name) {
        Class<?> type = reflector.getGetterType(name);
        return MetaClass.forClass(type, reflectorFactory);
    }

    public boolean hasSetter(String name) {
        PropertyTokenizer prop = new PropertyTokenizer(name);
        if (prop.hasNext()) {
            if (reflector.hasSetter(prop.getIndexName())) {
                MetaClass metaProp = metaClassForProperty(prop.getIndexName());
                return metaProp.hasSetter(prop.getChildren());
            } else {
                return false;
            }
        } else {
            return reflector.hasGetter(name);
        }
    }
}
