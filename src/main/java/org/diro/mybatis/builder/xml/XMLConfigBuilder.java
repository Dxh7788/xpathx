package org.diro.mybatis.builder.xml;

import org.diro.mybatis.builder.BaseBuilder;
import org.diro.mybatis.data.Configuration;
import org.diro.mybatis.data.XNode;
import org.diro.mybatis.datasource.DatasourceFactory;
import org.diro.mybatis.datasource.unpooled.UnpooledDatasourceFactory;
import org.diro.mybatis.mapping.Environment;
import org.diro.mybatis.parse.XPathParser;
import org.diro.mybatis.transaction.TransactionFactory;
import org.diro.mybatis.transaction.jdbc.JdbcTransactionFactory;

import java.io.InputStream;
import java.io.Reader;
import java.util.Locale;
import java.util.Properties;

/**
 * 主配置解析器
 * @author xh.d
 * @since 2018/7/23 10:11
 */
public class XMLConfigBuilder extends BaseBuilder {
    private XPathParser parser;
    private String environment;

    public XMLConfigBuilder(Reader reader) {
        this(new XPathParser(reader));
    }
    public XMLConfigBuilder(InputStream inputStream){
        this(new XPathParser(inputStream));
    }

    public XMLConfigBuilder(XPathParser parser) {
        super(new Configuration());
        this.parser = parser;
    }

    public Configuration parse(){
        try {
            parserConfiguration(parser.evalNode("/configuration"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return configuration;
    }

    private void parserConfiguration(XNode context) {
        //解析数据库环境变量配置,环境变量配置包括,事务/连接池/以及数据库连接
        try {
            parseEnvironments(context.evalNode("environments"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseEnvironments(XNode context) throws Exception {
        if (environment == null) {
            environment = context.getStringAttribute("default");
        }
        for (XNode xNode : context.getChildren()) {
            String id = xNode.getStringAttribute("id");
            if (isSpecifiedEnvironment(id)) {
                //解析事务配置
                TransactionFactory txFactory = transactionManagerElement(xNode.evalNode("transactionManager"));
                DatasourceFactory dsFactory = datasourceManagerElement(xNode.evalNode("datasource"));
                //解析数据库配置
                Environment.Builder builder = new Environment.Builder().datasourceFactory(dsFactory).transactionFactory(txFactory).id(id);
                configuration.setEnvironment(builder.build());
            }
        }
    }

    private DatasourceFactory datasourceManagerElement(XNode context) {
        DatasourceFactory dsFactory = null;
        if (null != context) {
            String type = context.getStringAttribute("type");
            if (type.toLowerCase(Locale.ENGLISH).equals("UNPOOLED")) {
                dsFactory = new UnpooledDatasourceFactory();
                Properties properties = context.getChildrenAsProperties();
                dsFactory.setProperties(properties);
                return dsFactory;
            }
        }
        return null;
    }

    private TransactionFactory transactionManagerElement(XNode context) {
        TransactionFactory txFactory = null;
        if (null != context) {
            String type = context.getStringAttribute("type");
            if (type.toLowerCase(Locale.ENGLISH) == "jdbc") {
                txFactory = new JdbcTransactionFactory();
                Properties props = context.getChildrenAsProperties();
                txFactory.setProperties(props);
                return txFactory;
            }
        }
        return null;
    }

    private boolean isSpecifiedEnvironment(String id) {
        if (environment == null) {
            return false;
        }
        if (id == null) {
            return false;
        }
        if (environment == id) {
            return true;
        }
        return false;
    }

    private void parseAuthor(XNode context) {
        String author = context.getStringBody("44");
        configuration.setAuthor(author);
    }

    private void parseYear(XNode context) {
        Integer year = Integer.valueOf(context.getStringBody("44"));
        configuration.setYear(year);
    }

    private void parsePrice(XNode context) {
        Double price = Double.valueOf(context.getStringBody("44"));
        configuration.setPrice(price);
    }
}
