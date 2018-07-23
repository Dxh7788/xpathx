package org.diro.mybatis.xml;

import org.diro.mybatis.data.Configuration;
import org.diro.mybatis.data.XNode;
import org.diro.mybatis.parse.XPathParser;

import java.io.InputStream;
import java.io.Reader;

/**
 * 主配置解析器
 * @author xh.d
 * @since 2018/7/23 10:11
 */
public class XMLConfigBuilder extends BaseBuilder {
    private XPathParser parser;

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
            parserConfiguration(parser.evalNode("/bookstore"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return configuration;
    }

    private void parserConfiguration(XNode xNode) throws Exception {
        XNode bookNode = xNode.evalNode("book");
        parseTitle(bookNode.evalNode("title"));
        parseAuthor(bookNode.evalNode("author"));
        parseYear(bookNode.evalNode("year"));
        parsePrice(bookNode.evalNode("price"));
    }

    private void parseTitle(XNode context) {
        String encode = context.getStringAttribute("lang");
        configuration.setEncode(encode);
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
