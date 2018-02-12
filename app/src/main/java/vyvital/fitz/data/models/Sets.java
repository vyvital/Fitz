package vyvital.fitz.data.models;


public class Sets {
    public int reps;
    public int weight;


    public Sets() {
    }

    public Sets(int reps, int weight) {
        this.reps = reps;
        this.weight = weight;
    }
    public void setReps(int reps) {
        this.reps = reps;
    }
    public int getReps() {
        return reps;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public int getWeight() {
        return weight;
    }
}
