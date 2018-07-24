package org.diro.mybatis.transaction.jdbc;

import org.diro.mybatis.transaction.Transaction;
import org.diro.mybatis.transaction.TransactionFactory;

import java.sql.Connection;
import java.util.Properties;

/**
 * Copyright (C) 2017-2018 https://www.htouhui.com - A project by dmybatis
 *
 * @author xh.d
 * @since 2018/7/24 16:21
 */
public class JdbcTransactionFactory implements TransactionFactory {
    private Properties props;

    @Override
    public void setProperties(Properties props) {
        this.props = props;
    }

    @Override
    public Transaction newTransaction(Connection connection) {
        return null;
    }
}
