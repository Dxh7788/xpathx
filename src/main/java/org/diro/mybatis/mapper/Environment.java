package org.diro.mybatis.mapper;

import org.diro.mybatis.datasource.DatasourceFactory;
import org.diro.mybatis.transaction.TransactionFactory;

/**
 * Copyright (C) 2017-2018 https://www.htouhui.com - A project by dmybatis
 *
 * @author xh.d
 * @since 2018/7/24 17:25
 */
public class Environment {

    private TransactionFactory txFactory;
    private DatasourceFactory dsFactor;
    private String id;

    public Environment(TransactionFactory txFactory, DatasourceFactory dsFactor, String id) {
        this.txFactory = txFactory;
        this.dsFactor = dsFactor;
        this.id = id;
    }

    public static class Builder {
        private TransactionFactory txFactory;
        private DatasourceFactory dsFactory;
        private String id;

        public Builder transactionFactory(TransactionFactory transactionFactory) {
            this.txFactory = transactionFactory;
            return this;
        }

        public Builder datasourceFactory(DatasourceFactory datasourceFactory) {
            this.dsFactory = datasourceFactory;
            return this;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Environment build() {
            return new Environment(this.txFactory, this.dsFactory, this.id);
        }
    }

    public TransactionFactory getTransactionFactory() {
        return txFactory;
    }

    public DatasourceFactory getDatasourceFactor() {
        return dsFactor;
    }

    public String getId() {
        return id;
    }
}
