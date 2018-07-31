package org.diro.mybatis.reflection.wrapper;

import org.diro.mybatis.reflection.MetaClass;
import org.diro.mybatis.reflection.MetaObject;
import org.diro.mybatis.reflection.factory.ObjectFactory;
import org.diro.mybatis.reflection.invoker.Invoker;
import org.diro.mybatis.reflection.property.PropertyTokenizer;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Copyright (C) 2017-2018 https://www.htouhui.com - A project by dmybatis
 *
 * @author xh.d
 * @since 2018/7/31 9:42
 */
public class BeanWrapper extends BaseWrapper {
    private Object object;
    private MetaClass metaClass;

    public BeanWrapper(MetaObject metaObject, Object object) {
        super(metaObject);
        this.object = object;
        metaClass = MetaClass.forClass(object.getClass(), metaObject.getReflectFactory());
    }

    @Override
    public Object get(PropertyTokenizer prop) {
        return getBeanProperty(prop, object);
    }

    private Object getBeanProperty(PropertyTokenizer prop, Object object) {
        Invoker method = metaClass.getGetInvoker(prop.getName());
        try {
            return method.invoke(object, NO_ARGUMENTS);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void set(PropertyTokenizer prop, Object value) {
        Invoker method = metaClass.getSetInvoker(prop.getName());
        Object[] params = {value};
        try {
            method.invoke(object, params);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    //是否有getter和setter查的是最后的名称是否有getter和setter,如xxx.xxx.name查看的是name是否有getter,也要校验xxx是否有属性或者setter方法
    @Override
    public boolean hasGetter(String name) {
        PropertyTokenizer prop = new PropertyTokenizer(name);
        if (prop.hasNext()) {
            //首先看下当前类是否有setter属性
            if (metaClass.hasGetter(prop.getIndexName())) {
                MetaObject metaValue = metaObject.metaObjectForProperty(prop.getName());
                return metaValue.hasGetter(prop.getChildren());
            } else {
                return false;
            }
        } else {
            return metaClass.hasGetter(prop.getName());
        }
    }

    @Override
    public boolean hasSetter(String name) {
        PropertyTokenizer prop = new PropertyTokenizer(name);
        if (prop.hasNext()) {
            //首先看下当前类是否有setter属性
            if (metaClass.hasSetter(prop.getIndexName())) {
                MetaObject metaProp = metaObject.metaObjectForProperty(prop.getName());
                return metaProp.hasSetter(prop.getChildren());
            } else {
                return false;
            }
        } else {
            return metaClass.hasGetter(prop.getName());
        }
    }

    @Override
    public String[] getGetterNames() {
        return metaClass.getGetterNames();
    }

    @Override
    public String[] getSetterNames() {
        return metaClass.getSetterNames();
    }

    @Override
    public Class<?> getGetterType(String name) {
        return metaClass.getGetterType(name);
    }

    @Override
    public Class<?> getSetterType(String name) {
        return metaClass.getSetterType(name);
    }

    @Override
    public MetaObject instantiatePropertyValue(String name, PropertyTokenizer prop, ObjectFactory objectFactory) {
        return null;
    }

    @Override
    public boolean isCollection() {
        return false;
    }

    @Override
    public void add(Object element) {

    }

    @Override
    public <E> void addAll(List<E> element) {

    }
}
