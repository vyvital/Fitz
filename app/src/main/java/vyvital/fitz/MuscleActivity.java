package vyvital.fitz;


import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import vyvital.fitz.data.models.Days;
import vyvital.fitz.fragments.FragAMuscle;
import vyvital.fitz.fragments.FragBMuscle;

public class MuscleActivity extends BaseActivity {

    Days day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        day = getIntent().getExtras().getParcelable("currentDay");
        Bundle bundle = new Bundle();
        bundle.putParcelable("currentDay",day);
        FragAMuscle simpleA = FragAMuscle.newInstance();
        simpleA.setArguments(bundle);
        setContentView(R.layout.activity_muscle);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content, simpleA)
                .commit();

    }


}
