import Converter.JsonToXmlConverter;

public class Main {
    public static void main(String[] args) {
        String in = "files/keyboards.xml";
        String out = "files/brands.json";
        String inin = "files/newkeyboards.xml";
        String outout = "files/newbrands.json";
        //Converter.XmlToJsonConverter xmlToJsonConverter = new Converter.XmlToJsonConverter();
        //xmlToJsonConverter.convert(in,out);
        JsonToXmlConverter jsonToXmlConverter = new JsonToXmlConverter();
        jsonToXmlConverter.convert(out, inin);
    }
}
