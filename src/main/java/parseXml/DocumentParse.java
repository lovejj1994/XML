package parseXml;

import org.w3c.dom.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by panqian on 2017/1/25.
 */
public class DocumentParse {


    public static void main(String[] args) {
        Path path = Paths.get("BuildXML_3.xml");
        parse(path);
    }

    public static void parse(Path path){
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
            Document doc = builder.parse(path.toFile());

            Element root = doc.getDocumentElement();

            System.out.println("根元素  ： "+root.getTagName());

            DocumentParse.findAttr(root);

            DocumentParse.findNodes(root);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void findAttr(Element e){
        NamedNodeMap attributes = e.getAttributes();
        int length = attributes.getLength();
        if(length==0){
            System.out.println("当前标签无属性");
        }else{
            for(int j=0;j<length;j++){
                System.out.println("第"+j+"个属性  key:"+attributes.item(j).getNodeName()+" value:"+attributes.item(j).getNodeValue());
            }
        }
    }

    public static void findNodes(Element root){

        NodeList childNodes = root.getChildNodes();

        for(int i=0;i<childNodes.getLength();i++){
            Node item = childNodes.item(i);
            if(item instanceof Element){
                Element e = (Element) item;
                System.out.println("子目录："+ e.getTagName());

                DocumentParse.findAttr(e);

                DocumentParse.findNodes(e);
            }else{
                Text text = (Text) item;
                String s = text.getData().trim();
                if(!"".equals(s))
                    System.out.println("文本 ： "+ s);
            }
        }
    }

}
