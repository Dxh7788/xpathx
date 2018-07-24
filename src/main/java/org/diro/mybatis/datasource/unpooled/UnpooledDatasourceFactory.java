package org.diro.mybatis.datasource.unpooled;

import org.diro.mybatis.datasource.DatasourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author xh.d
 * @since 2018/7/24 16:59
 */
public class UnpooledDatasourceFactory implements DatasourceFactory {

    protected DataSource dataSource;

    public UnpooledDatasourceFactory() {
        dataSource = new UnpooledDatasource();
    }

    @Override
    public void setProperties(Properties properties) {
        //这里会使用反射来setter属性
    }

    @Override
    public DataSource getDatasource() {
        return null;
    }
}
