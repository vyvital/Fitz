package vyvital.fitz.data.models;


import java.util.List;

public class Exercise {
    private String name;
    private String mechanics;
    private List<Sets> setsList;

    public Exercise() {
    }

    public Exercise(String name,String mechanics,  List<Sets> setsList) {

        this.name = name;
        this.mechanics = mechanics;
        this.setsList = setsList;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setMechanics(String mechanics) {
        this.mechanics = mechanics;
    }
    public void setSetsList(List<Sets> setsList) {
        this.setsList = setsList;
    }
    public String getName() {
        return name;
    }
    public String getMechanics() {
        return mechanics;
    }
    public List<Sets> getSetsList() {
        return setsList;
    }
}
