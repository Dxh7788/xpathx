package org.d.xpathx;

import org.d.xpathx.data.XNode;
import org.d.xpathx.parse.XPathParser;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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

    private static void checkFactoryAttributes(DocumentBuilderFactory factory) {
        factory.setValidating(false);
        factory.setNamespaceAware(false);
        factory.setIgnoringComments(true);
        factory.setIgnoringElementContentWhitespace(false);
        factory.setCoalescing(false);
        factory.setExpandEntityReferences(true);
    }
}
