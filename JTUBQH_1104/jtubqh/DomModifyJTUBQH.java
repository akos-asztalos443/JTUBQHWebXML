package jtubqh;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class DomModifyJTUBQH {

    public static void main(String[] args) {
        try {
            File xmlFile = new File("JTUBQHXML.xml");
            if (!xmlFile.exists()) {
                xmlFile = new File("JTUBQH_1021/JTUBQHXML.xml");
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = factory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("Vendeg");
            if (nList.getLength() == 0) {
                nList = doc.getElementsByTagName("vendeg");
            }

            for (int i = 0; i < nList.getLength(); i++) 
            {
                Node vendeg = nList.item(i);
                NodeList list = vendeg.getChildNodes();

                for(int temp = 0; temp < list.getLength(); temp++){
                    Node node = list.item(temp);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) node;
                        if ("Eletkor".equals(eElement.getNodeName()) || "eletkor".equals(eElement.getNodeName())) {
                            if ("26".equals(eElement.getTextContent())) {
                                eElement.setTextContent("22");
                            }
                        }
                        if ("Nev".equals(eElement.getNodeName()) || "nev".equals(eElement.getNodeName())) {
                            if ("Kiss Hajnalka".equals(eElement.getTextContent())) {
                                eElement.setTextContent("Új Név");
                            }
                        }
                    }
                }
            }

            NodeList gyList = doc.getElementsByTagName("Gyakornok");
            if (gyList.getLength() == 0) {
                gyList = doc.getElementsByTagName("gyakornok");
            }
            if (gyList.getLength() > 0) {
                Node gyakornok = gyList.item(0);
                NamedNodeMap attr = gyakornok.getAttributes();
                Node nodeAttr = attr.getNamedItem("E_GY");
                if (nodeAttr == null) nodeAttr = attr.getNamedItem("e_gy");
                if (nodeAttr != null) {
                    nodeAttr.setTextContent("e3");
                }
            }

            Node vendeglatas = doc.getFirstChild();
            NodeList childNode = vendeglatas.getChildNodes();

            for(int count = childNode.getLength() - 1; count >= 0; count--){
                Node node = childNode.item(count);

                if ("rendeles".equalsIgnoreCase(node.getNodeName())) {
                    vendeglatas.removeChild(node);
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            System.out.println("-----Módosított XML Fájl-----");
            StreamResult consoResult = new StreamResult(System.out);
            transformer.transform(source, consoResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}