package org.d.xpathx;

import org.d.xpathx.data.DNode;
import org.d.xpathx.parse.ZPathParser;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.InputStream;

/**
 * Hello world!
 *
 */
public class DPathXBootApplication
{
    public static void main( String[] args ) throws Exception {
        DPathXBootApplication application = new DPathXBootApplication();
        //首先加载xml文件
        ClassLoader loader = application.getClass().getClassLoader();
        InputStream inputStream = loader.getResourceAsStream("rules/definer.xml");
        ZPathParser parser = new ZPathParser(inputStream);
        //开始xpath
        DNode node = parser.evalNode("/bookstore");
        DNode node2 = node.evalNode("book");
        NodeList nodeList = node2.getNode().getChildNodes();
        if (nodeList != null){
            for (int i=0;i<nodeList.getLength();i++){
                Node iNode = nodeList.item(i);
                if (iNode.getNodeType() == Node.ELEMENT_NODE){
                    System.out.println("[key:"+iNode.getNodeName()+",value:"+iNode.getTextContent()+"]");
                    NamedNodeMap map = iNode.getAttributes();
                    for (int j=0;j<map.getLength();j++){
                        Node attrNode = map.item(j);
                        String name = attrNode.getNodeName();
                        String value = attrNode.getTextContent();
                        System.out.println("  [key:"+name+",value:"+value+"]");
                    }
                }
            }
        }
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
