package com.diro.mybatis.parsing;

import org.diro.mybatis.parsing.GenericTokenParser;
import org.diro.mybatis.parsing.ParameterTokenHandler;
import org.junit.Test;

/**
 * Copyright (C) 2017-2018 https://www.htouhui.com - A project by dmybatis
 *
 * @author xh.d
 * @since 2018/8/2 17:22
 */
public class GenericTokenParserTest {

    @Test
    public void test() {
        String text = "select * from user where id = #{} and name = #{name}";
        //暂时不设置TokenHandler
        GenericTokenParser parser = new GenericTokenParser("#{", "}", new ParameterTokenHandler());
        String parserStr = parser.parse(text);
        System.out.println(parserStr);
    }
}
