package vyvital.fitz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MuscleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muscle);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content, FragmentA.newInstance())
                .commit();
    }
}
