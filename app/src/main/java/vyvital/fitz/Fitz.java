package vyvital.fitz;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class Fitz extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
