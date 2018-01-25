package vyvital.fitz.data;


public class Exercise {

    int id;
    String nameOfExercise;
    String muscle;
    String mechanics;
    String equip[];


    public Exercise(int id, String nameOfExercise, String mechanics, String equip[], String muscle) {
        this.nameOfExercise = nameOfExercise;
        this.muscle = muscle;
        this.mechanics = mechanics;
        this.equip = equip;
        this.id = id;
    }
}
