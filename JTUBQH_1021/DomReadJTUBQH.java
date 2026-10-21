
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class DomReadJTUBQH {
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException
    {
        File xmlFile = new File("JTUBQHXML.xml");
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder dBuilder = factory.newDocumentBuilder();

        Document doc = dBuilder.parse(xmlFile);

        doc.getDocumentElement().normalize();

        System.out.println("Gyökér elem: " + doc.getDocumentElement().getNodeName());

        NodeList nList = doc.getElementsByTagName("Etterem");

        for(int i = 0; i <nList.getLength(); i++)
        {
            Node nNode = nList.item(i);

            System.out.println("\nAktuális elem: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) 
            {
                Element elem = (Element) nNode;
                String id = elem.getAttribute("EKod");

                Node node1 = elem.getElementsByTagName("Nev").item(0);
                String name = node1.getTextContent();

                Node node2 = elem.getElementsByTagName("Varos").item(0);
                String city = node2.getTextContent();

                Node node3 = elem.getElementsByTagName("Utca").item(0);
                String street = node3.getTextContent();

                Node node4 = elem.getElementsByTagName("Hazszam").item(0);
                String number = node4.getTextContent();


                String adr =  city + ", " + street + " utca " + number + ", ";

                System.out.println("Etterem ID: " + id);
                System.out.println("Név: " + name);
                System.out.println("Cím: " + adr);
                  
            }
        }

        NodeList fList = doc.getElementsByTagName("Foszakacs");

        for(int i = 0; i < fList.getLength(); i++)
        {
            Node fNode = fList.item(i);

            System.out.println("\nAktuális elem: " + fNode.getNodeName());

            if (fNode.getNodeType() == Node.ELEMENT_NODE) 
            {
                Element elem = (Element) fNode;

                String fkod = elem.getAttribute("FKod");
                String e_f = elem.getAttribute("E_F");

                Node node1 = elem.getElementsByTagName("Nev").item(0);
                String name = node1.getTextContent();

                Node node2 = elem.getElementsByTagName("Eletkor").item(0);
                String age = node2.getTextContent();

                Node node3 = elem.getElementsByTagName("Vegzettseg").item(0);
                String qual = node3.getTextContent();

                String edu = "";
                Node node3;

                for(int j = 0; j < elem.getElementsByTagName("Vegzettseg").getLength(); j++)
                {
                    node3 = elem.getElementsByTagName("Vegzettseg").item(j);
                    if(j == elem.getElementsByTagName("Vegzettseg").getLength() - 1)
                {
                    edu += node3.getTextContent();
                }
                else
                {
                    edu += node3.getTextContent() + ", ";
                }
                }

                System.out.println("Főszakács ID: " + fkod);
                System.out.println("Étterem FK ID: " + e_f);
                System.out.println("Név: " + name);
                System.out.println("Életkor: " + age);
                System.out.println("Végzettség: " + qual);
            }
        }

        
    }
    
}