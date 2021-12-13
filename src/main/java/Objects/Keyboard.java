package Objects;

import java.util.ArrayList;

public class Keyboard {
    private String model, color, backlighting;
    private ArrayList<String> specifications;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBacklighting() {
        return backlighting;
    }

    public void setBacklighting(String backlighting) {
        this.backlighting = backlighting;
    }

    public ArrayList<String> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(ArrayList<String> specifications) {
        this.specifications = specifications;
    }
}

