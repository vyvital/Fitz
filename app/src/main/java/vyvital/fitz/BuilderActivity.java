package vyvital.fitz;


import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import vyvital.fitz.data.EmptyRecyclerView;
import vyvital.fitz.data.RVAdapter;
import vyvital.fitz.data.models.Exercise;
import vyvital.fitz.data.models.Sets;
import vyvital.fitz.data.models.Workout;


public class BuilderActivity extends BaseActivity implements View.OnClickListener{

    String namez;
    private List<Workout> workouts;
    MaterialBetterSpinner workoutTypeSpinner ;
    MaterialBetterSpinner workoutDateSpinner ;
    MaterialBetterSpinner workoutLevelSpinner ;
    String[] type_data = {"Strength","Hypertrophy","Maintenance","Endurance"};
    String[] lvl_data = {"Novice","Intermediate","Advanced","Elite"};
    String[] day_data = {"1 Day", "2 Days", "3 Days", "4 Days", "5 Days", "6 days", "7 Days"};
    Dialog workoutDialog;

    private DatabaseReference mRef;
    private DatabaseReference mWorkoutsDatabaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_builder);
        EmptyRecyclerView rv = findViewById(R.id.rv);
        View emptyView = findViewById(R.id.empty_view);
        FloatingActionButton fab = findViewById(R.id.fab);

        mRef =  FirebaseDatabase.getInstance().getReference().child("Users");
        mWorkoutsDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Workouts");
        LinearLayoutManager llm = new LinearLayoutManager(this);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle("Program Manager");

        rv.setHasFixedSize(true);
        workouts = new ArrayList<>();
        rv.setLayoutManager(llm);
        rv.setEmptyView(emptyView);
        //addTheComplexOneAsWell();
        initializeData();
        RVAdapter adapter = new RVAdapter(workouts);
        rv.setAdapter(adapter);
        workoutDialog = new Dialog(this);

    }

    private void initializeData() {
        workouts = new ArrayList<>();
        List<Exercise> days = new ArrayList<>();
        workouts.add(new Workout("Summer Cut", "Maintenance", "Beginner", days));
        workouts.add(new Workout("Strengh Gaining", "Strengh", "Medium", days));
        workouts.add(new Workout("Gainz are Coming", "Hyperthrophy", "Hardcore", days));
    }


    private void addTheComplexOneAsWell(){
        String userEMail = "meme@gmail.com";
        addWorkoutsToSpecificUserGivenEMail(userEMail);
    }
    private void addWorkoutsToSpecificUserGivenEMail(String userEMail){
        String name = "Summer Cut"; // this is done by getting edit text from the app or any way ... here is just hardcoded example
        String type = "Heavy";
        String level = "Beginner";
        List<Exercise> days = new ArrayList<>();
        // add 2 fake exercises
        Exercise exercises = getExercisesInstanceInitialized();
        for (int i = 1; i <= 2; i++)
            days.add(exercises);
        Workout workouts = new Workout(name, type, level, days);
        // --- adding more than 1 workout for ex. 4
        List<Workout> workoutsList = new ArrayList<>();
        for (int i = 1; i <= 2; i++)
            workoutsList.add(workouts);
        String nodeName = "workouts"; // CONSTANT you Specify
        // userEMail is given
    /*List<Integer> list = new ArrayList<>();
    list.add(11);
    list.add(16);*/
        mWorkoutsDatabaseReference.push().setValue(workoutsList); // ========== here when u put in database
    }
    private Exercise getExercisesInstanceInitialized(){
        String name = "Bench Press";
        String mechanics = "Compound";
        List<Sets> setsList = new ArrayList<>();
        // add 3 fake sets
        Sets sets = new Sets(6,80);
        for (int i = 1; i <= 3; i++)
            setsList.add(sets);
        return new Exercise(name, mechanics, setsList);
    }

    @Override
    public void onClick(View v) {

    }

    public void popUp(View view) {
        Button btnClose;
        workoutDialog.setContentView(R.layout.addworkout);
        btnClose = workoutDialog.findViewById(R.id.cancel_adding);
        workoutTypeSpinner = workoutDialog.findViewById(R.id.type_spinner);
        workoutDateSpinner = workoutDialog.findViewById(R.id.day_spinner);
        workoutLevelSpinner = workoutDialog.findViewById(R.id.lvl_spinner);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, type_data);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, day_data);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, lvl_data);
        workoutTypeSpinner.setAdapter(adapter1);
        workoutDateSpinner.setAdapter(adapter2);
        workoutLevelSpinner.setAdapter(adapter3);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workoutDialog.dismiss();
            }
        });
        workoutDialog.show();
    }

}
