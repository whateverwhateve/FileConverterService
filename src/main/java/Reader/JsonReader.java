package Reader;

import Objects.Brand;
import Objects.BrandCover;
import Objects.BrandsCover;
import Reader.IReader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class JsonReader implements IReader<Brand> {
    @Override
    public ArrayList<Brand> read(String filename) {
        try {
            BufferedReader reader = Files.newBufferedReader(Path.of(filename), StandardCharsets.UTF_8);
            Gson gson = new Gson();
            Type type = new TypeToken<BrandsCover>(){}.getType();
            BrandsCover brandsCover = gson.fromJson(reader, type);
            ArrayList<BrandCover> brandCover = brandsCover.brands;
            ArrayList<Brand> brands = new ArrayList<>();
            for (BrandCover bc : brandCover) {
                brands.add(bc.brand);
            }
            return brands;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
