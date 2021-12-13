package Writer;

import java.util.ArrayList;

public interface IWriter<T> {
    void write(ArrayList<T> arrayList, String filename);
}
