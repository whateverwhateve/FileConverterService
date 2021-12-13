package Writer;

import Objects.BrandKeyboard;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

public class XmlWriter implements IWriter<BrandKeyboard> {

    @Override
    public void write(ArrayList<BrandKeyboard> arrayList, String filename) {
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element keyboards = document.createElement("keyboards");
            document.appendChild(keyboards);
            for (int i = 0; i < arrayList.size(); i++) {
                Element keyboard = document.createElement("keyboard");
                keyboard.setAttribute("id", String.valueOf(i));
                keyboards.appendChild(keyboard);
                fillKeyboard(document, keyboard, arrayList.get(i));
            }
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(filename));
            transformer.setOutputProperty("indent", "yes");
            transformer.transform(domSource, streamResult);
        }
        catch (ParserConfigurationException | TransformerException ex) {
            ex.printStackTrace();
        }
    }

    private void fillKeyboard(Document document, Element keyboard, BrandKeyboard kb) {
        Element brand = document.createElement("brand");
        brand.appendChild(document.createTextNode(kb.getBrand()));
        keyboard.appendChild(brand);

        Element model = document.createElement("model");
        model.appendChild(document.createTextNode(kb.getModel()));
        keyboard.appendChild(model);

        Element color = document.createElement("color");
        color.appendChild(document.createTextNode(kb.getColor()));
        keyboard.appendChild(color);

        Element backlighting = document.createElement("backlighting");
        backlighting.appendChild(document.createTextNode(kb.getBacklighting()));
        keyboard.appendChild(backlighting);

        Element specifications = document.createElement("specifications");
        for (String specString : kb.getSpecifications()) {
            Element specification = document.createElement("genre");
            specification.appendChild(document.createTextNode(specString));
            specifications.appendChild(specification);
        }
        keyboard.appendChild(specifications);
    }
}
