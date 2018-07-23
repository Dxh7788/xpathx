package org.diro.mybatis.session.defaults;

import org.diro.mybatis.data.Configuration;
import org.diro.mybatis.session.DefaultSqlSession;
import org.diro.mybatis.session.SqlSession;
import org.diro.mybatis.session.SqlSessionFactory;

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
