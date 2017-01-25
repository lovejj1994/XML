package parseXml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Created by panqian on 2017/1/25.
 */
public class SAXParse {


    public static void main(String[] args) {
        Path path = Paths.get("BuildXML_2.xml");
        parse(path);
    }

    public static void parse(Path path){
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(path.toFile(),new DefaultHandler(){
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    System.out.println("当前标签: "+qName );
                    int length = attributes.getLength();
                    if(length==0){
                        System.out.println("当前标签无属性");
                    }else{
                        for(int i=0;i<length;i++){
                            System.out.println("第"+i+"个属性  key:"+attributes.getLocalName(i)+" value:"+attributes.getValue(i));
                        }
                    }
                }
                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    String s = String.valueOf(ch).substring(start, start + length).trim();
                    if(!"".equals(s)){
                        System.out.print("字符值 : ");
                        System.out.println(String.valueOf(ch).substring(start,start+length));
                    }
                }
            });
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
