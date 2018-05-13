package vyvital.fitz;





import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;


import android.util.Log;
import android.widget.Toast;

import vyvital.fitz.data.models.Days;
import vyvital.fitz.data.models.Workout;
import vyvital.fitz.fragments.FragAMuscle;
import vyvital.fitz.fragments.FragBMuscle;

public class MuscleActivity extends BaseActivity {

    Days day;
    public Workout workout;
    int dayNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        workout = getIntent().getExtras().getParcelable("currentWorkout");
        dayNum = getIntent().getExtras().getInt("currentDay");
//        Bundle bundle = new Bundle();
//        bundle.putParcelable("currentDay",day);
        FragAMuscle simpleA = FragAMuscle.newInstance();
//        simpleA.setArguments(bundle);
        setContentView(R.layout.activity_muscle);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content, simpleA)
                .commit();

    }

    @Override
    public void onBackPressed() {
        Fragment f = MuscleActivity.this.getSupportFragmentManager().findFragmentById(R.id.content);
        if(f instanceof FragAMuscle) {
            Intent intent = new Intent();
            intent.putExtra("currentWorkout",workout);
            setResult(RESULT_OK,intent);
            finish();
       }
        super.onBackPressed();
    }
}
