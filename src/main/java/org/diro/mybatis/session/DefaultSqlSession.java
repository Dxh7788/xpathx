package org.diro.mybatis.session;

import org.diro.mybatis.data.Configuration;

import java.util.Collections;
import java.util.List;

/**
 * Copyright (C) 2017-2018 https://www.htouhui.com - A project by xpathx
 *
 * @author xh.d
 * @since 2018/7/23 16:55
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession() {
    }

    public DefaultSqlSession(Configuration configuration) {
        this();
        this.configuration = configuration;
    }

    @Override
    public List selectOne() {
        return Collections.emptyList();
    }

    @Override
    public List selectList() {
        return null;
    }
}
