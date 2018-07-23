package org.d.xpathx.parse;

import org.d.xpathx.data.XNode;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

/**
 *
 * 该类封装了XPath,用于解析Document
 * @author xh.d
 * @since 2018/7/20 10:30
 */
public class XPathParser {
    private XPath xpath;
    private Document document;
    private Boolean validation;
    private Properties variables;
    public XPathParser() {
        commonConstructor(false);
    }

    public XPathParser(XPath xpath) {
        this.xpath = xpath;
    }

    public XPathParser(InputStream inputStream){
        commonConstructor(false);
        this.document = createDocument(new InputSource(inputStream));
    }

    public XPathParser(InputStream inputStream, Properties variables) {
        this(inputStream);
        this.variables = variables;
    }

    public XPathParser(Reader reader){
        commonConstructor(false);
        this.document = createDocument(new InputSource(reader));
    }

    private Document createDocument(InputSource source) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(validation);
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(source);
            //暂未设置实体解析器 builder.setEntityResolver(null);
            builder.setErrorHandler(new ErrorHandler() {
                @Override
                public void error(SAXParseException exception) throws SAXException {
                    throw exception;
                }

                @Override
                public void fatalError(SAXParseException exception) throws SAXException {
                    throw exception;
                }

                @Override
                public void warning(SAXParseException exception) throws SAXException {
                }
            });
            return document;
        } catch (ParserConfigurationException e) {

            e.printStackTrace();
        } catch (SAXException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return null;
    }

    private void commonConstructor(Boolean validation) {
        this.validation = validation;
        XPathFactory factory = XPathFactory.newInstance();
        xpath = factory.newXPath();
    }

    public XNode evalNode(String expression) {
        try {
            Node node = (Node) xpath.evaluate(expression,document, XPathConstants.NODE);
            if (node == null){
                return null;
            }
            return new XNode(this,node,variables);
        }
        catch (XPathExpressionException e){
            e.printStackTrace();
        }
        return null;
    }

    public XNode evalNode(Node nde, String expression) throws Exception {
        try {
            if (nde == null){
                throw new Exception();
            }
            Node node= (Node) xpath.evaluate(expression,nde, XPathConstants.NODE);
            if (node == null){
                return null;
            }
            return new XNode(this, node,variables);
        }
        catch (XPathExpressionException e){
            e.printStackTrace();
        }
        return null;
    }
    /*
    * 应当使用XPath进行的解析都使用ZPathParser进行解析
    * */

}
