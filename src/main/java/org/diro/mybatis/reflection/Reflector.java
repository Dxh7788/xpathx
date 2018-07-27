package org.diro.mybatis.reflection;

import org.diro.mybatis.reflection.invoker.GetFieldInvoker;
import org.diro.mybatis.reflection.invoker.Invoker;
import org.diro.mybatis.reflection.invoker.MethodInvoker;
import org.diro.mybatis.reflection.invoker.SetFieldInvoker;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Locale;
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
    private final Map<String, Class<?>> setTypes = new HashMap<String, Class<?>>();//set的type,type指的是参数的type
    private final Map<String, Class<?>> getTypes = new HashMap<String, Class<?>>();//get的type,type指的是返回值type
    private Constructor<?> defaultConstructor;//默认构造器,可能默认构造器是带参的

    public Reflector(Class<?> type) {
        this.type = type;
        //根据type解析出来可读属性,可写属性,set方法集和get方法集
        addDefaultConstructor(type);
        addSetMethods(type);
        addGetMethods(type);
        addFields(type);
        readablePropertyNames = getMethods.keySet().toArray(new String[getMethods.keySet().size()]);
        writeablePropertyNames = setMethods.keySet().toArray(new String[setMethods.keySet().size()]);
    }

    //指的是参数为0的构造函数
    private void addDefaultConstructor(Class<?> clazz) {
        Constructor<?>[] consts = clazz.getDeclaredConstructors();
        for (Constructor<?> constructor : consts) {
            if (constructor.getParameterTypes().length == 0) {
                try {
                    constructor.setAccessible(true);
                } catch (Exception e) {
                    // Ignored. This is only a final precaution, nothing we can do.
                }
            }
            if (constructor.isAccessible()) {
                this.defaultConstructor = constructor;
            }
        }
    }

    /*
    * 暂不支持泛型参数和泛型数组
    * */
    private void addSetMethods(Class<?> type) {
        Method[] methods = type.getClass().getDeclaredMethods();//找到所有的set方法
        for (Method method : methods) {
            //如果参数超过1个,不添加
            if (method.getParameterTypes().length > 1) {
                continue;
            }
            String name = method.getName();
            if (null != name && name.startsWith("set")) {
                name = methodToProperty(name);
                if (method.isAccessible()) {
                    setMethods.put(name, new MethodInvoker(method));//加入Map缓存
                    Class<?> returnType = method.getParameterTypes()[0];
                    setTypes.put(name, returnType);
                }
            }
        }
    }

    /*
    * 暂不支持泛型和泛型数组
    * */
    private void addGetMethods(Class<?> type) {
        Method[] methods = type.getClass().getMethods();//找到所有的set方法
        for (Method method : methods) {
            if (method.getParameterTypes().length > 0) {//不能有参数
                continue;
            }
            String name = method.getName();
            if ((name.startsWith("get") && name.length() > 3) ||
                    (name.startsWith("is") && name.length() > 2)) {
                name = methodToProperty(name);
                if (method.isAccessible()) {
                    getMethods.put(name, new MethodInvoker(method));//加入Map缓存
                    Class<?> returnType = method.getReturnType();
                    getTypes.put(name, returnType);
                }
            }
        }
    }

    //添加属性,暂时不考虑泛型参数和泛型数据
    private void addFields(Class<?> type) {
        Field[] fields = type.getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            if (!setMethods.containsKey(name)) {
                setMethods.put(name, new SetFieldInvoker(field));
                setTypes.put(name, field.getType());
            }
            if (!getMethods.containsKey(name)) {
                getMethods.put(name, new GetFieldInvoker(field));
                getTypes.put(name, field.getType());
            }
        }
    }

    //从get和set方法中找到属性名
    public String methodToProperty(String name) {
        if (name.startsWith("is")) {
            name = name.substring(2);
        } else if (name.startsWith("get") || name.startsWith("set")) {
            name = name.substring(3);
        } else {
            //throw new Exception("Error parsing property name '" + name + "'.  Didn't start with 'is', 'get' or 'set'.");
        }

        if (name.length() == 1 || (name.length() > 1 && !Character.isUpperCase(name.charAt(1)))) {
            name = name.substring(0, 1).toLowerCase(Locale.ENGLISH) + name.substring(1);
        }
        return name;
    }
}
