package Reader;

import java.util.ArrayList;

public interface IReader<T> {
    ArrayList<T> read(String filename);
}
