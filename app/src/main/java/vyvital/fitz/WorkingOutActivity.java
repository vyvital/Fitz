package vyvital.fitz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import vyvital.fitz.data.models.Days;
import vyvital.fitz.data.models.Workout;

public class WorkingOutActivity extends AppCompatActivity {

    private Days day;
    TextView test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working_out);
        Bundle b = new Bundle();
        b = getIntent().getExtras();
        day = b.getParcelable("day");
        test = findViewById(R.id.test);
        test.setText(day.getExercises().get(0).getName());
    }
}
