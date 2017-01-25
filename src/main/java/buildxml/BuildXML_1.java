package buildxml;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 生成不带命名空间的XML文档
 * reated by panqian on 2017/1/24.
 */
public class BuildXML_1 {

    public static void main(String[] args) {
        BuildXML_1.buildNoNameSpace();
        BuildXML_1.buildNameSpace();

    }

    public static void buildNoNameSpace(){
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = documentBuilderFactory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element root = doc.createElement("root");
            Element childElement = doc.createElement("name");
            Text textNode = doc.createTextNode(new String("潘谦".getBytes("UTF-8"), Charset.forName("UTF-8")));

            doc.appendChild(root);
            root.appendChild(childElement);
            childElement.appendChild(textNode);

            root.setAttribute("attr","man");

            DOMImplementation implementation = doc.getImplementation();
            DOMImplementationLS ls = (DOMImplementationLS) implementation.getFeature("LS", "3.0");

            LSSerializer lsSerializer = ls.createLSSerializer();
            lsSerializer.getDomConfig().setParameter("format-pretty-print",true);

            //输出字符串
            String string = lsSerializer.writeToString(doc);
            System.out.println(string);

            //输出到文件
            LSOutput lsOutput = ls.createLSOutput();
            lsOutput.setEncoding("UTF-8");
            lsOutput.setByteStream(Files.newOutputStream(Paths.get("BuildXML_1.xml")));
            lsSerializer.write(doc,lsOutput);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void buildNameSpace(){
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);
        String namespace = "http://maven.apache.org/POM/4.0.0";
        DocumentBuilder builder = null;
        try {
            builder = documentBuilderFactory.newDocumentBuilder();
            Document doc = builder.newDocument();
            doc.setXmlVersion("1.1");

            Element root = doc.createElementNS(namespace,"project");
            root.setAttribute("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
            root.setAttribute("xsi:schemaLocation","http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd");


            Element childElement1 = doc.createElement("modelVersion");
            Text textNode1 = doc.createTextNode("4.0.0");

            Element childElement2 = doc.createElement("groupId");
            Text textNode2 = doc.createTextNode("groupId");

            Element childElement3 = doc.createElement("artifactId");
            Text textNode3 = doc.createTextNode("XML");

            Element childElement4 = doc.createElement("version");
            Text textNode4 = doc.createTextNode("1.0-SNAPSHOT");

            doc.appendChild(root);
            root.appendChild(childElement1);
            childElement1.appendChild(textNode1);
            root.appendChild(childElement2);
            childElement2.appendChild(textNode2);
            root.appendChild(childElement3);
            childElement3.appendChild(textNode3);
            root.appendChild(childElement4);
            childElement4.appendChild(textNode4);

            DOMImplementation implementation = doc.getImplementation();
            DOMImplementationLS ls = (DOMImplementationLS) implementation.getFeature("LS", "3.0");

            LSSerializer lsSerializer = ls.createLSSerializer();
            lsSerializer.getDomConfig().setParameter("format-pretty-print",true);

            //输出字符串
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            LSOutput lsOutput = ls.createLSOutput();
            lsOutput.setEncoding("UTF-8");
            lsOutput.setByteStream(stream);
            lsSerializer.write(doc,lsOutput);

            String string = stream.toString("UTF-8");
            System.out.println(string);

            //输出到文件
            LSOutput lsOutput1 = ls.createLSOutput();
            lsOutput1.setEncoding("UTF-8");
            lsOutput1.setByteStream(Files.newOutputStream(Paths.get("BuildXML_2.xml")));
            lsSerializer.write(doc,lsOutput1);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

