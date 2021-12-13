package Converter;

import Converter.IConverter;
import Objects.Brand;
import Objects.BrandKeyboard;
import Objects.Keyboard;
import Reader.XmlReader;
import Writer.JsonWriter;

import java.util.ArrayList;

public class XmlToJsonConverter implements IConverter {
    @Override
    public void convert(String inFilename, String outFilename) {
        XmlReader xmlReader = new XmlReader();
        ArrayList<BrandKeyboard> keyboards = xmlReader.read(inFilename);
        if (keyboards != null) {
            ArrayList<Brand> brands = new ArrayList<>();
            for (BrandKeyboard keyboard : keyboards) {
                String brandName = keyboard.getBrand();
                if (!containsBrand(brands, brandName)) {
                    Brand brand = new Brand();
                    brand.setName(brandName);
                    Keyboard kb = new Keyboard();
                    kb.setModel(keyboard.getModel());
                    kb.setColor(keyboard.getColor());
                    kb.setBacklighting(keyboard.getBacklighting());
                    kb.setSpecifications(keyboard.getSpecifications());
                    brand.addKeyboard(kb);
                    brands.add(brand);
                }
                else {
                    for (Brand brand : brands) {
                        if (brand.getName().equals(keyboard.getBrand())){
                            Keyboard kb = new Keyboard();
                            kb.setModel(keyboard.getModel());
                            kb.setColor(keyboard.getColor());
                            kb.setBacklighting(keyboard.getBacklighting());
                            kb.setSpecifications(keyboard.getSpecifications());
                            brand.addKeyboard(kb);
                        }
                    }
                }
            }
            JsonWriter jsonWriter = new JsonWriter();
            jsonWriter.write(brands, outFilename);
        }
    }

    private boolean containsBrand(ArrayList<Brand> brands, String brandName) {
        for (Brand brand : brands) {
            if (brand.getName().equals(brandName)) {
                return true;
            }
        }
        return false;
    }
}
