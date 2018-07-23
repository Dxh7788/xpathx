package org.diro.mybatis.session;

import org.diro.mybatis.data.Configuration;
import org.diro.mybatis.session.defaults.DefaultSqlSessionFactory;
import org.diro.mybatis.xml.XMLConfigBuilder;

import java.io.InputStream;

/**
 * Copyright (C) 2017-2018 https://www.htouhui.com - A project by xpathx
 *
 * @author xh.d
 * @since 2018/7/23 16:47
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream inputStream) {
        XMLConfigBuilder parser = new XMLConfigBuilder(inputStream);
        return doBuild(parser.parse());
    }

    private SqlSessionFactory doBuild(Configuration config) {
        return new DefaultSqlSessionFactory(config);
    }
}
