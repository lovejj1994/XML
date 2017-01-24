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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 生成不带命名空间的XML文档
 * reated by panqian on 2017/1/24.
 */
public class BuildXML_1 {

    public static void main(String[] args) {
        BuildXML_1.build();
    }

    public static void build(){
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
}

