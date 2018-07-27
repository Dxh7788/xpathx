package org.diro.mybatis.reflection.invoker;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * 如果属性没有get方法,直接使用属性来获取
 *
 * @author xh.d
 * @since 2018/7/27 13:28
 */
public class GetFieldInvoker implements Invoker {
    private Field field;

    public void setField(Field field) {
        this.field = field;
    }

    @Override
    public Object invoke(Object target, Object[] args) throws InvocationTargetException, IllegalAccessException {
        return field.get(target);
    }

    @Override
    public Class<?> getType() {
        return field.getType();
    }
}
