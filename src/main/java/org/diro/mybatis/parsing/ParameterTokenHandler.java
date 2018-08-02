package org.diro.mybatis.parsing;

/**
 * @author xh.d
 * @since 2018/8/2 17:31
 */
public class ParameterTokenHandler implements TokenHandler {

    @Override
    public String handleToken(String expression) {
        //此处加入ParameterMapping的设置
        return " ? ";
    }
}
