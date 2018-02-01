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
import java.util.List;
import vyvital.fitz.data.EmptyRecyclerView;
import vyvital.fitz.data.RVAdapter;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_builder);
        EmptyRecyclerView rv = findViewById(R.id.rv);
        View emptyView = findViewById(R.id.empty_view);
        FloatingActionButton fab = findViewById(R.id.fab);

        mRef =  FirebaseDatabase.getInstance().getReference().child("Users");

        LinearLayoutManager llm = new LinearLayoutManager(this);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle("Program Manager");



        rv.setHasFixedSize(true);
        workouts = new ArrayList<>();
        rv.setLayoutManager(llm);
        rv.setEmptyView(emptyView);
//        loadWorkout();
        initializeData();
        RVAdapter adapter = new RVAdapter(workouts);
        rv.setAdapter(adapter);
        workoutDialog = new Dialog(this);

    }
    private void loadWorkout(){

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        workouts = new ArrayList<>();
        final String[] namez = {""};
        final String[] typez = {""};
        final String[] lvlz = {""};
        final int[] dz = {0};
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                 for (DataSnapshot snaps : dataSnapshot.getChildren()) {
                    String snap = snaps.getKey();
                    if (dataSnapshot.child(snap).child("userID").getValue().equals(user.getUid())) {
                        namez[0] = String.valueOf(dataSnapshot.child(snap).child("workouts").child("0").child("name").getValue());
                        typez[0] = String.valueOf(dataSnapshot.child(snap).child("workouts").child("0").child("type").getValue());
                        lvlz[0] = String.valueOf(dataSnapshot.child(snap).child("workouts").child("0").child("level").getValue());
                        dz[0] = Integer.parseInt(String.valueOf(dataSnapshot.child(snap).child("workouts").child("0").child("days").getValue())) ;
                        Log.e(TAG, namez[0]);
                        Log.e(TAG, typez[0]);
                        Log.e(TAG, lvlz[0]);
                        Log.e(TAG, String.valueOf(dz[0]));
                    }


            }
                workouts.add(new Workout("Summer Cut", "Maintanance", "Beginner", 5, R.drawable.profile));
                workouts.add(new Workout(namez[0],typez[0],lvlz[0],dz[0],R.drawable.profile));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
       // Toast.makeText(BuilderActivity.this,"txt", Toast.LENGTH_SHORT).show();

    }
    private void initializeData() {
        workouts = new ArrayList<>();
        workouts.add(new Workout("Summer Cut", "Maintanance", "Beginner", 5, R.drawable.profile));
        workouts.add(new Workout("Strengh Gaining", "Strengh", "Medium", 3, R.drawable.profile));
        workouts.add(new Workout("Gainz are Coming", "Hyperthrophy", "Hardcore", 4, R.drawable.profile));
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
