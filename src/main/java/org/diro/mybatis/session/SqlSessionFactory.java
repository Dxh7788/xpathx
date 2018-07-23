package org.diro.mybatis.session;

import org.diro.mybatis.data.Configuration;

/**
 * Copyright (C) 2017-2018 https://www.htouhui.com - A project by xpathx
 *
 * @author xh.d
 * @since 2018/7/23 16:53
 */
public interface SqlSessionFactory {

    Configuration getConfiguration();

    SqlSession openSession();
}
