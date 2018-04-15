package vyvital.fitz.data.models;

import java.util.List;

public class Days {

    private List<Exercise2> exercises;

    public Days() {
    }

    public Days(List<Exercise2> ex){
        this.exercises = ex;
    }

    public List<Exercise2> getExercises() {
        return exercises;
    }
}
