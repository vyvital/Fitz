package vyvital.fitz.data.models;


import java.util.ArrayList;

public class Exercise {
    public String equip;
    public String name;
    public String mechanics;
    public ArrayList<Round> rounds;

    public Exercise() {
    }

    public Exercise(String equip, String name, String mechanics) {
        this.equip = equip;
        this.name = name;
        this.mechanics = mechanics;
        this.rounds = new ArrayList<>();
    }
}
