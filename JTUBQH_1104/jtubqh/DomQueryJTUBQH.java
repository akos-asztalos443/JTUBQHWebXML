package jtubqh;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomQueryJTUBQH {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        File xmlFile = new File("JTUBQHXML.xml");
        if (!xmlFile.exists()) {
            xmlFile = new File("JTUBQH_1021/JTUBQHXML.xml");
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        System.out.println("Gyökér elem: " + doc.getDocumentElement().getNodeName() + "\n");

        System.out.println("1. Azok a szakacsok, akiknek a végzetségeik között van szakközépiskola:\n");

        NodeList nodeList = doc.getElementsByTagName("Szakacs");
        if (nodeList.getLength() == 0) {
            nodeList = doc.getElementsByTagName("szakacs");
        }

        for(int i = 0; i < nodeList.getLength(); i++){
            Node nNode = nodeList.item(i);

            if(nNode.getNodeType() == Node.ELEMENT_NODE){
                Element elem = (Element) nNode;

                Node node3;

                NodeList vegList = elem.getElementsByTagName("Vegzettseg");
                if (vegList.getLength() == 0) {
                    vegList = elem.getElementsByTagName("vegzettseg");
                }

                for(int j = 0; j < vegList.getLength(); j++){
                    node3 = vegList.item(j);
                    String edu1 = node3.getTextContent();

                    if("Szakközépiskola".equalsIgnoreCase(edu1)){
                        String id = elem.getAttribute("SzKod");
                        if (id.isEmpty()) id = elem.getAttribute("szkod");

                        String eid = elem.getAttribute("E_SZ");
                        if (eid.isEmpty()) eid = elem.getAttribute("e_sz");

                        String work = "Ez a szakasz a(z) "+ eid + " azonosítójú étteremnek dolgozik";
                        
                        Node node1 = elem.getElementsByTagName("Nev").item(0);
                        if (node1 == null) node1 = elem.getElementsByTagName("nev").item(0);
                        String name = node1.getTextContent();

                        Node node2 = elem.getElementsByTagName("Reszleg").item(0);
                        if (node2 == null) node2 = elem.getElementsByTagName("reszleg").item(0);
                        String department = node2.getTextContent();

                        String edu2 = "";

                        for(int k = 0; k < vegList.getLength(); k++){
                            node3 = vegList.item(k);
                            if(k == vegList.getLength() - 1){
                                edu2 += node3.getTextContent();
                            } else {
                                edu2 += node3.getTextContent() + ", ";
                            }
                        }

                        System.out.println("Szakacs ID: " + id);
                        System.out.println("Név: " + name);
                        System.out.println("Részleg: " + department);
                        System.out.println("Végzettség: " + edu2);
                        System.out.println(work + "\n");
                        break;
                    }
                }
            }
        }

        System.out.println("2. Azok az éttermek amik öt csillagosak:\n");

        nodeList = doc.getElementsByTagName("etterem");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);

            if(nNode.getNodeType()==Node.ELEMENT_NODE){
                Element elem = (Element) nNode;

                Node node5 = elem.getElementsByTagName("csillag").item(0);
                String stars = node5.getTextContent();

                if("5".equals(stars)){
                    String id = elem.getAttribute("EKod");

                    Node node1 = elem.getElementsByTagName("nev").item(0);
                    String name = node1.getTextContent();

                    Node node2 = elem.getElementsByTagName("varos").item(0);
                    String city = node2.getTextContent();

                    Node node3 = elem.getElementsByTagName("utca").item(0);
                    String street = node3.getTextContent();

                    Node node4 = elem.getElementsByTagName("hazszam").item(0);
                    String number = node4.getTextContent();

                    String adr = city + ", " + street + " utca" + number + ",";

                    System.out.println("Étterem ID: " + id);
                    System.out.println("Név: " + name);
                    System.out.println("Cím: " + street);
                    System.out.println("Csillag: " + stars);
                }
            }
        }

    }
}