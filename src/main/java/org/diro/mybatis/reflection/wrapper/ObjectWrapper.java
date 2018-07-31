package org.diro.mybatis.reflection.wrapper;

import org.diro.mybatis.reflection.MetaObject;
import org.diro.mybatis.reflection.factory.ObjectFactory;
import org.diro.mybatis.reflection.property.PropertyTokenizer;

import java.util.List;

/**
 * @author xh.d
 * @since 2018/7/31 9:33
 */
public interface ObjectWrapper {

    Object get(PropertyTokenizer prop);

    void set(PropertyTokenizer prop, Object value);

    boolean hasGetter(String name);

    boolean hasSetter(String name);

    String[] getGetterNames();

    String[] getSetterNames();

    Class<?> getGetterType(String name);

    Class<?> getSetterType(String name);

    MetaObject instantiatePropertyValue(String name, PropertyTokenizer prop, ObjectFactory objectFactory);

    //Collection的方法
    boolean isCollection();

    void add(Object element);

    <E> void addAll(List<E> element);
}
