package Writer;

import Objects.Brand;
import Objects.BrandCover;
import Objects.BrandsCover;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class JsonWriter implements IWriter<Brand> {

    @Override
    public void write(ArrayList<Brand> brands, String filename) {
        try {
            ArrayList<BrandCover> brandCover = new ArrayList<>();
            for (Brand brand : brands) {
                brandCover.add(new BrandCover(brand));
            }
            BrandsCover brandsCover = new BrandsCover(brandCover);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Writer writer = Files.newBufferedWriter(Paths.get(filename));
            gson.toJson(brandsCover, writer);
            writer.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
