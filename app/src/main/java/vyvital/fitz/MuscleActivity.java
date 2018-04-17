package vyvital.fitz;

import android.os.Bundle;

import vyvital.fitz.fragments.FragAMuscle;

public class MuscleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muscle);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content, FragAMuscle.newInstance())
                .commit();
    }



}
