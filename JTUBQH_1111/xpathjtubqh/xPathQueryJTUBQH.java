package xpathjtubqh;

import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import org.xml.sax.SAXException;

public class xPathQueryJTUBQH {

    public static void main(String[] args) {
        try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document document = documentBuilder.parse("studentJTUBQH.xml");
            document.getDocumentElement().normalize();

            XPath xPath = XPathFactory.newInstance().newXPath();

            String jtubqh = "/class/hallgato";

            System.out.println("\n Futás eredménye:");

            NodeList jtubqhList = (NodeList) xPath.compile(jtubqh)
                .evaluate(document, XPathConstants.NODESET);


            BufferedWriter writer = new BufferedWriter(new FileWriter("student1JTUBQH.xml"));
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<hallgato>\n");

            for(int i = 0; i < jtubqhList.getLength(); i++){

                Node node = jtubqhList.item(i);

                System.out.println("\nAktuális elem: " + node.getNodeName());

                if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("hallgato")) {
                    Element element = (Element) node;

                    String id = element.getAttribute("id");
                    String keresztnev = element.getElementsByTagName("keresztnev").item(0).getTextContent();
                    String vezeteknev = element.getElementsByTagName("vezeteknev").item(0).getTextContent();
                    String becenev = element.getElementsByTagName("becenev").item(0).getTextContent();
                    String kor = element.getElementsByTagName("kor").item(0).getTextContent();

                    System.out.println("Hallgató ID: " + id);
                    System.out.println("Keresztnév: " + keresztnev);
                    System.out.println("Vezetéknév: " + vezeteknev);
                    System.out.println("Becenév: " + becenev);
                    System.out.println("Kor: " + kor);
                    
                }
            }

            writer.write("<hallagto>");
            writer.close();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();    
         
        } catch (SAXException e) {
            e.printStackTrace(); 

        } catch (IOException e) {
            e.printStackTrace(); 

        } catch (XPathExpressionException e) {
            e.printStackTrace(); 

        }
    }
}