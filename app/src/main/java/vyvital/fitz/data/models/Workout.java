package vyvital.fitz.data.models;


import java.util.ArrayList;

// Done
public class Workout {
    public String name;
    public String type;
    public String level;
    public int days;
    public int photoId;
    public ArrayList<Exercise> exercises;

    // This is required for serialization
    public Workout() {
    }

    public Workout(String name, String type, String level, int days, int photoId) {
        exercises = new ArrayList<>();
        this.name = name;
        this.type = type;
        this.level = level;
        this.days = days;
        this.photoId = photoId;
    }
}