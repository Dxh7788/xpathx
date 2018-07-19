package org.d.xpathx;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.InputStream;

/**
 * Hello world!
 *
 */
public class XPathXBootApplication
{
    public static void main( String[] args ) throws Exception {
        XPathXBootApplication application = new XPathXBootApplication();
        //首先加载xml文件
        ClassLoader loader = application.getClass().getClassLoader();
        InputStream inputStream = loader.getResourceAsStream("rules/definer.xml");
        InputSource reader = new InputSource(inputStream);
        //准备,仅仅是标准的 JAXP 和 DOM，没有什么新鲜的
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        checkFactoryAttributes(builderFactory);
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document document = builder.parse(reader);
        //开始xpath
        XPathFactory factory = XPathFactory.newInstance();
        XPath xPath = factory.newXPath();
        Node node = (Node) xPath.evaluate("/bookstore/book",document, XPathConstants.NODE);
        NodeList nodeList = node.getChildNodes();
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
