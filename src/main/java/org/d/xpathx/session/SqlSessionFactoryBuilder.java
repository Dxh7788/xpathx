package org.d.xpathx.session;

import org.d.xpathx.data.Configuration;
import org.d.xpathx.session.defaults.DefaultSqlSessionFactory;
import org.d.xpathx.xml.XMLConfigBuilder;

import java.io.InputStream;

/**
 * Copyright (C) 2017-2018 https://www.htouhui.com - A project by xpathx
 *
 * @author xh.d
 * @since 2018/7/23 16:47
 */
public class SqlSessionFactoryBuilder {

    public static SqlSessionFactory build(InputStream inputStream) {
        XMLConfigBuilder parser = new XMLConfigBuilder(inputStream);
        return doBuild(parser.parse());
    }

    private static SqlSessionFactory doBuild(Configuration config) {
        return new DefaultSqlSessionFactory(config);
    }
}
