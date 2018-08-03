package com.diro.mybatis.binding;

import org.diro.mybatis.binding.MapperProxyFactory;
import org.junit.Test;

/**
 * Copyright (C) 2017-2018 https://www.htouhui.com - A project by dmybatis
 *
 * @author xh.d
 * @since 2018/8/3 16:40
 */
public class MapperProxyFactoryTest {

    @Test
    public void newInstanceTest() {
        MapperProxyFactory factory = new MapperProxyFactory(TestMapper.class);
        TestMapper testMapper = (TestMapper) factory.newInstance("第一个session");
        testMapper.print("kkk");
    }
}
