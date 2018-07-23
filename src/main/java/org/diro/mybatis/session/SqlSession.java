package org.diro.mybatis.session;

import java.util.List;

/**
 * Copyright (C) 2017-2018 https://www.htouhui.com - A project by xpathx
 *
 * @author xh.d
 * @since 2018/7/23 16:52
 */
public interface SqlSession<T> {

    List<T> selectOne();

    List<T> selectList();
}
