package vyvital.fitz.data.models;


import java.util.List;


public class Muscle {
    private String name;
    private List<Exercise> exercises;

    public Muscle() {
    }

    public Muscle(String name, List<Exercise> exercises) {
        this.name = name;
        this.exercises = exercises;
    }
}
