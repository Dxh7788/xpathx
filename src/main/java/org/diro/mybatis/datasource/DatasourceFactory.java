package org.diro.mybatis.datasource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Copyright (C) 2017-2018 https://www.htouhui.com - A project by dmybatis
 *
 * @author xh.d
 * @since 2018/7/24 16:58
 */
public interface DatasourceFactory {

    void setProperties(Properties properties);

    DataSource getDatasource();
}
