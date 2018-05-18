package vyvital.fitz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import vyvital.fitz.data.models.Days;
import vyvital.fitz.data.models.Workout;
import vyvital.fitz.fragments.FragAMuscle;

public class MuscleActivity extends BaseActivity {

    Days day;
    public Workout workout;
    public int dayNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle(getResources().getString(R.string.Fitz));
        workout = getIntent().getExtras().getParcelable("currentWorkout");
        dayNum = getIntent().getExtras().getInt("currentDay");
        FragAMuscle simpleA = FragAMuscle.newInstance();
        setContentView(R.layout.activity_muscle);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content, simpleA)
                .commit();
    }

    @Override
    public void onBackPressed() {
        Fragment f = MuscleActivity.this.getSupportFragmentManager().findFragmentById(R.id.content);
        if (f instanceof FragAMuscle) {
            Intent intent = new Intent();
            intent.putExtra("currentWorkout", workout);
            setResult(RESULT_OK, intent);
            finish();
        }
        super.onBackPressed();
    }
}