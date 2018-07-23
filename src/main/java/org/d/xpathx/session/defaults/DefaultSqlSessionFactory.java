package org.d.xpathx.session.defaults;

import org.d.xpathx.data.Configuration;
import org.d.xpathx.session.DefaultSqlSession;
import org.d.xpathx.session.SqlSession;
import org.d.xpathx.session.SqlSessionFactory;

/**
 * Copyright (C) 2017-2018 https://www.htouhui.com - A project by xpathx
 *
 * @author xh.d
 * @since 2018/7/23 16:51
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }
}
