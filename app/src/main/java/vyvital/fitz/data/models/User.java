package vyvital.fitz.data.models;

import java.util.List;

public class User {
    public String email;
    public String id;
    public List<Workout> workoutList;
    public String time;
    public String key;

    public User() {
    }

    public User(String userEmail, String userId, String time, String key, List<Workout> workouts) {
        this.email = userEmail;
        this.id = userId;
        if (time != null)
            this.time = time;
        this.key = key;
        if (workouts != null)
            this.workoutList = workouts;
    }

    public String getEmail() {
        return email;
    }

    public String getModelName() {
        return "users";
    }

    public String getKey() {
        return key;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setWorkoutList(List<Workout> workoutList) {
        this.workoutList = workoutList;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getId() {
        return id;
    }

    public List<Workout> getWorkoutList() {
        return workoutList;
    }

    public String getTime() {
        return time;
    }
}