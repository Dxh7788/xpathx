package org.diro.mybatis.reflection;

import java.lang.reflect.*;

/**
 * @author xh.d
 * @since 2018/8/6 9:54
 */
public class TypeParameterResolver {

    /*
    * 解析返回结果
    * */
    public static Type resolveReturnType(Method method, Type srcType) {
        Type returnType = method.getGenericReturnType();
        Class<?> declareClass = method.getDeclaringClass();
        return resolveType(returnType, srcType, declareClass);
    }

    private static Type resolveType(Type type, Type srcType, Class<?> declareClass) {
        if (type instanceof TypeVariable) {
            return resolveTypeVar((TypeVariable<?>) type, srcType, declareClass);
        } else if (type instanceof ParameterizedType) {//比如Collection<String>,这种泛型返回值
            return resolveParameterizeType((ParameterizedType) type, srcType, declareClass);
        } else if (type instanceof GenericArrayType) {//获得这个数组元素类型，即获得：A<T>（A<T>[]）或T（T[]）
            return resolveGenericArrayType((GenericArrayType) type, srcType, declareClass);
        }
        return type;
    }

    //TypeVariable，类型变量，描述类型，表示泛指任意或相关一类类型，也可以说狭义上的泛型（泛指某一类类型），一般用大写字母作为变量，比如K、V、E等
    private static Type resolveTypeVar(TypeVariable<?> type, Type srcType, Class<?> declareClass) {
        return null;
    }

    private static Type resolveParameterizeType(ParameterizedType type, Type srcType, Class<?> declareClass) {
        return null;
    }

    private static Type resolveGenericArrayType(GenericArrayType type, Type srcType, Class<?> declareClass) {
        return null;
    }
}
