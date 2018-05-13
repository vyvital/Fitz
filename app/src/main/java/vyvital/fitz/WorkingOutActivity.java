package vyvital.fitz;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import vyvital.fitz.data.ParentAdapter;
import vyvital.fitz.data.models.Days;
import vyvital.fitz.data.models.Workout;

public class WorkingOutActivity extends AppCompatActivity {

    private Days day;
    Button start,pause;
    TextView stopwatch;
    boolean running;
    Handler handler;
    private long stopped = 0;
    int seconds;
    boolean started = false;
    boolean paused = false;
    Chronometer chrono;
    RecyclerView workoutRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working_out);
        Bundle b = new Bundle();
        b = getIntent().getExtras();
        day = b.getParcelable("day");
        //stopwatch = findViewById(R.id.stopwatch);
        start = findViewById(R.id.btnStart);
        chrono = findViewById(R.id.chrono);
        pause = findViewById(R.id.btnPause);
        handler = new Handler();
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (started == false){
                chrono.setBase(SystemClock.elapsedRealtime()+stopped);
                chrono.start();
                started = true;
                paused = false;
                }
                //running = true;

            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //running = false;
                if (paused==false && started==true){
                chrono.stop();
                stopped = chrono.getBase() - SystemClock.elapsedRealtime();
                started  = false;
                paused = true;}
            }
        });

        workoutRV = findViewById(R.id.workoutRV);
        workoutRV.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        workoutRV.setLayoutManager(mLayoutManager);
        ParentAdapter adapter = new ParentAdapter(this,day.getExercises(),true);
        workoutRV.setAdapter(adapter);
        //runTimer();
      //  test.setText(day.getExercises().get(0).getName());
    }


//    public void runTimer(){
//        final TextView textView = (TextView) findViewById(R.id.stopwatch);
//        final Handler handler = new Handler();
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                int hours = seconds / 3600;
//                int minutes = (seconds % 3600) / 60;
//                int sec = seconds % 60;
//                String time = String.format("%d:%02d:%02d", hours, minutes, sec);
//                textView.setText(time);
//                if(running) {
//                    seconds++;
//                }
//                handler.postDelayed(this, 1000);
//            }
//        });
//
//    }
}
