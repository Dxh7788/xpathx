package org.diro.mybatis.reflection.invoker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author xh.d
 * @since 2018/7/27 13:28
 */
public class MethodInvoker implements Invoker {
    private Method method;

    public void setMethod(Method method) {
        this.method = method;
    }

    @Override
    public Object invoke(Object target, Object[] args) throws InvocationTargetException, IllegalAccessException {
        return method.invoke(target, args);
    }

    @Override
    public Class<?> getType() {
        return method.getReturnType();
    }
}
