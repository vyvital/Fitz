package vyvital.fitz.data;


public class Workout {

    String nameOfWorkout;
    String typeOfWorkout;
    String level;
    int numDays;
    int photoId;

    public Workout(String nameOfWorkout, String typeOfWorkout, String level, int numDays, int photoId) {
        this.nameOfWorkout = nameOfWorkout;
        this.typeOfWorkout = typeOfWorkout;
        this.level = level;
        this.numDays = numDays;
        this.photoId = photoId;
    }


}