package com.diro.mybatis;

import org.diro.mybatis.reflection.DefaultReflectorFactory;
import org.diro.mybatis.reflection.MetaClass;
import org.diro.mybatis.reflection.ReflectorFactory;
import org.diro.mybatis.reflection.invoker.Invoker;

import java.lang.reflect.InvocationTargetException;

/**
 * @author xh.d
 * @since 2018/7/27 14:47
 */
public class RefactoryTest {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        ReflectorFactory factory = new DefaultReflectorFactory();
        User u = new User();//这一步的替换要使用ObjectFactory
        MetaClass metaClass = MetaClass.forClass(u.getClass(), factory);
        Invoker invoker = metaClass.getSetInvoker("name");
        Object[] os = {"000"};
        invoker.invoke(u, os);
        System.out.println(u.getName());
    }
}
