package com.diro.mybatis;

import org.diro.mybatis.reflection.DefaultReflectorFactory;
import org.diro.mybatis.reflection.Reflector;
import org.diro.mybatis.reflection.ReflectorFactory;

/**
 * @author xh.d
 * @since 2018/7/27 14:47
 */
public class RefactoryTest {

    public static void main(String[] args) {
        ReflectorFactory refactory = new DefaultReflectorFactory();
        User u = new User();
        Reflector forClass = refactory.findForClass(u.getClass());
        System.out.println();
    }
}
