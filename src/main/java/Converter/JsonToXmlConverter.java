package Converter;

import Converter.IConverter;
import Objects.Brand;
import Objects.BrandKeyboard;
import Objects.Keyboard;
import Reader.JsonReader;
import Writer.XmlWriter;

import java.util.ArrayList;

public class JsonToXmlConverter implements IConverter {
    @Override
    public void convert(String inFilename, String outFilename) {
        JsonReader jsonReader = new JsonReader();
        ArrayList<Brand> brands = jsonReader.read(inFilename);
        if (brands != null) {
            ArrayList<BrandKeyboard> keyboards = new ArrayList<>();
            for (Brand brand : brands) {
                for (Keyboard keyboard : brand.getKeyboards()) {
                    BrandKeyboard brandKeyboard = new BrandKeyboard();
                    brandKeyboard.setBrand(brand.getName());
                    brandKeyboard.setModel(keyboard.getModel());
                    brandKeyboard.setColor(keyboard.getColor());
                    brandKeyboard.setBacklighting(keyboard.getBacklighting());
                    brandKeyboard.setSpecifications(keyboard.getSpecifications());
                    keyboards.add(brandKeyboard);
                }
            }
            XmlWriter xmlWriter = new XmlWriter();
            xmlWriter.write(keyboards, outFilename);
        }
    }
}
