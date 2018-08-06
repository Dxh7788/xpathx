package org.diro.mybatis.binding;

import org.diro.mybatis.reflection.ParamNameResolver;
import org.diro.mybatis.reflection.TypeParameterResolver;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * @author xh.d
 * @since 2018/8/6 9:48
 */
public class MapperMethod {

    //方法签名
    public static class MethodSignature {
        private Method method;
        private boolean returnVoid;
        private boolean returnMany;
        private ParamNameResolver paramNameResolver;
        private Class<?> mapperMethodInterface;

        public MethodSignature(Class<?> mapperMethodInterface, Method method) {
            Type resolvedReturnType = TypeParameterResolver.resolveReturnType(method, mapperMethodInterface);
            this.mapperMethodInterface = mapperMethodInterface;
            this.method = method;
        }
    }

}
