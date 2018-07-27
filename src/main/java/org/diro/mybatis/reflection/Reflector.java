package org.diro.mybatis.reflection;

import org.diro.mybatis.reflection.invoker.Invoker;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xh.d
 * @since 2018/7/27 13:21
 */
public class Reflector {
    private Class<?> type;//针对的类型
    private String[] readablePropertyNames;//可读的属性,一般是有setter方法的属性
    private String[] writeablePropertyNames;//可写的属性,一般是有getter方法的属性
    private Map<String, Invoker> setMethods = new HashMap<String, Invoker>();//set方法
    private Map<String, Invoker> getMethods = new HashMap<String, Invoker>();//get方法


}
