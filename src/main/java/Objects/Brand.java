package Objects;

import java.util.ArrayList;

public class Brand {
    private String name;
    private ArrayList<Keyboard> keyboards;

    public String getName() {
        return name;
    }

    public void setName(String brand) {
        this.name= brand;
    }

    public ArrayList<Keyboard> getKeyboards() {
        return keyboards;
    }

    public void setKeyboards(ArrayList<Keyboard> keyboards) {
        this.keyboards = keyboards;
    }

    public void addKeyboard(Keyboard keyboard) {
        keyboards.add(keyboard);
    }

    public Brand() {
        name = null;
        keyboards = new ArrayList<>();
    }
}
