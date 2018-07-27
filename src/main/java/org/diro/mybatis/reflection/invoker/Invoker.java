package org.diro.mybatis.reflection.invoker;

import java.lang.reflect.InvocationTargetException;

/**
 * 调用接口
 *
 * @author xh.d
 * @since 2018/7/27 13:25
 */
public interface Invoker {

    Object invoke(Object target, Object[] args) throws InvocationTargetException, IllegalAccessException;//执行调用

    Class<?> getType();//获取类型
}
