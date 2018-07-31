package org.diro.mybatis.reflection;

import org.diro.mybatis.reflection.factory.ObjectFactory;
import org.diro.mybatis.reflection.property.PropertyTokenizer;
import org.diro.mybatis.reflection.wrapper.ObjectWrapper;
import org.diro.mybatis.reflection.wrapper.ObjectWrapperFactory;

/**
 * @author xh.d
 * @since 2018/7/31 9:38
 */
public class MetaObject {
    private Object originObject;
    private ObjectWrapperFactory objectWrapperFactory;
    private ObjectFactory objectFactory;
    private ReflectorFactory reflectFactory;
    private ObjectWrapper objectWrapper;

    private MetaObject(Object object, ObjectFactory objectFactory, ObjectWrapperFactory objectWrapperFactory, ReflectorFactory reflectFactory) {
        this.originObject = object;
        this.objectWrapperFactory = objectWrapperFactory;
        this.objectFactory = objectFactory;
        this.reflectFactory = reflectFactory;
    }

    public ReflectorFactory getReflectFactory() {
        return reflectFactory;
    }

    public static MetaObject forObject(Object object, ObjectFactory objectFactory, ObjectWrapperFactory objectWrapperFactory, ReflectorFactory reflectFactory) {
        return new MetaObject(object, objectFactory, objectWrapperFactory, reflectFactory);
    }

    public MetaObject metaObjectForProperty(String name) {
        Object value = getValue(name);
        return MetaObject.forObject(value, objectFactory, objectWrapperFactory, reflectFactory);
    }

    private Object getValue(String name) {
        PropertyTokenizer prop = new PropertyTokenizer(name);
        if (prop.hasNext()) {
            MetaObject metaValue = metaObjectForProperty(prop.getIndexName());
            if (metaValue == SystemMetaObject.NULL_META_OBJECT) {
                return null;
            } else {
                return metaValue.getValue(prop.getChildren());
            }
        } else {
            return objectWrapper.get(prop);
        }
    }

    public boolean hasSetter(String name) {
        return objectWrapper.hasSetter(name);
    }

    public boolean hasGetter(String name) {
        return objectWrapper.hasGetter(name);
    }
}
