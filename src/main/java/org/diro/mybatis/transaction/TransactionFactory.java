package org.diro.mybatis.transaction;

import java.sql.Connection;
import java.util.Properties;

/**
 * TransactionFactory
 *
 * @author xh.d
 * @since 2018/7/24 16:17
 */
public interface TransactionFactory {
    void setProperties(Properties props);

    Transaction newTransaction(Connection connection);
}
