package vyvital.fitz.data.models;



import java.util.List;

public class Workout {

    private int size;
    private String name;
    private String type;
    private String level;
    private List<Exercise> days;

    // This is required for serialization
    public Workout() {
    }

    public Workout(String name, String type, String level, List<Exercise> days) {
        this.name = name;
        this.type = type;
        this.level = level;
        this.days = days;
        this.size = days.size();

    }
    public void setName(String name) {
        this.name = name;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    public void setDays(List<Exercise> days) {
        this.days = days;
    }
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public String getLevel() {
        return level;
    }
    public List<Exercise> getDays() {
        return days;
    }
    public int getSize(){
        return size;
    }
}