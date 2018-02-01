package vyvital.fitz.data.models;

import java.util.ArrayList;

// Done.
public class User {
    public String userEmail;
    public String userId;
    public ArrayList<Workout> workouts;

    public User() {
    }

    public User(String userEmail, String userId) {
        this.workouts = new ArrayList<>();
        this.userEmail = userEmail;
        this.userId = userId;
    }
}
