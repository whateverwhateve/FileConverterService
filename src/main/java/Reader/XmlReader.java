package Reader;

import Objects.BrandKeyboard;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class XmlReader implements IReader<BrandKeyboard> {

    @Override
    public ArrayList<BrandKeyboard> read(String filename) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(new File(filename));
            ArrayList<BrandKeyboard> keyboards = new ArrayList<>();
            NodeList keyboardsNodeList = document.getDocumentElement().getElementsByTagName("keyboard");
            for (int i = 0; i < keyboardsNodeList.getLength(); i++) {
                keyboards.add(getKeyboard(keyboardsNodeList.item(i)));
            }
            return keyboards;
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static BrandKeyboard getKeyboard(Node keyboardNode) {
        BrandKeyboard keyboard = new BrandKeyboard();
        NodeList keyboardChildNodes = keyboardNode.getChildNodes();
        for (int i = 0; i < keyboardChildNodes.getLength(); i++) {
            Node keyboardChildNode = keyboardChildNodes.item(i);
            if (keyboardChildNode.getNodeType() != Node.TEXT_NODE) {
                switch (keyboardChildNode.getNodeName()) {
                    case "brand" -> keyboard.setBrand(keyboardChildNode.getTextContent());
                    case "model" -> keyboard.setModel(keyboardChildNode.getTextContent());
                    case "color" -> keyboard.setColor(keyboardChildNode.getTextContent());
                    case "backlighting" -> keyboard.setBacklighting(keyboardChildNode.getTextContent());
                    case "specifications" -> {
                        ArrayList<String> specifications = new ArrayList<>();
                        NodeList specificationsNodeList = keyboardChildNode.getChildNodes();
                        for (int j = 0; j < specificationsNodeList.getLength(); j++) {
                            Node specification = specificationsNodeList.item(j);
                            if (specification.getNodeType() != Node.TEXT_NODE)
                                specifications.add(specification.getTextContent());
                        }
                        keyboard.setSpecifications(specifications);
                    }
                }
            }
        }
        return keyboard;
    }
}
