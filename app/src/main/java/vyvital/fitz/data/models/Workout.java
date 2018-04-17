package vyvital.fitz.data.models;


import java.util.List;

public class Workout extends Model{

    private String name;
    private String type;
    private String level;
    private int size;
    private List<Days> days;
    private int id;

    // This is required for serialization
    public Workout() {
    }

    public Workout(String name, String type, String level, int size ,int id ) {
        this.name = name;
        this.type = type;
        this.level = level;
        this.size = size;
        this.days = null;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    public void setSize(int days) {
        this.size = days;
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
    public int getSize() {
        return size;
    }
    public void setDays(List<Days> days) {
        this.days = days;
    }

    public List<Days> getDays() {
        return days;
    }
}