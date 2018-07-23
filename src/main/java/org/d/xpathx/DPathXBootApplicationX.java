package org.d.xpathx;

import org.d.xpathx.data.Configuration;
import org.d.xpathx.data.XNode;
import org.d.xpathx.parse.XPathParser;
import org.d.xpathx.xml.XMLConfigBuilder;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class DPathXBootApplicationX
{
    public static void main( String[] args ) throws Exception {
//        day20180720();
        day20180723();
    }

    private static void day20180723() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream input = loader.getResourceAsStream("rules/definer.xml");
        XMLConfigBuilder builder =new XMLConfigBuilder(input);
        Configuration configuration = builder.parse();
        System.out.println(configuration.toString());
    }

    private static void day20180720() throws Exception {
        DPathXBootApplicationX application = new DPathXBootApplicationX();
        //首先加载xml文件
        ClassLoader loader = application.getClass().getClassLoader();
        InputStream inputStream = loader.getResourceAsStream("rules/definer.xml");
        InputStream input = loader.getResourceAsStream("rules/application.properties");
        Properties props = new Properties();
        props.load(input);
        XPathParser parser = new XPathParser(inputStream,props);
        //开始xpath
        XNode node = parser.evalNode("/bookstore");
        XNode node2 = node.evalNode("book");
        XNode attrNode = node2.evalNode("title");
        String encode = attrNode.getStringAttribute("lang");
        System.out.println(encode);
    }
}
