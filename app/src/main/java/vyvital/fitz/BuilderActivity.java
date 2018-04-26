package vyvital.fitz;


import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import vyvital.fitz.data.EmptyRecyclerView;
import vyvital.fitz.data.RVAdapter;
import vyvital.fitz.data.RecyclerTouchHelper;
import vyvital.fitz.data.models.Workout;


public class BuilderActivity extends BaseActivity implements RecyclerTouchHelper.RecyclerItemTouchHelperListener {


    String[] type_data = {"Strength", "Hypertrophy", "Maintenance", "Endurance"};
    String[] lvl_data = {"Novice", "Intermediate", "Advanced", "Elite"};
    String[] day_data = {"1 Days", "2 Days", "3 Days", "4 Days", "5 Days", "6 days", "7 Days"};
    Dialog workoutDialog;
    public List<Workout> workouts;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef = null;
    private ConstraintLayout constraintLayout;
    private List<Workout> workoutList;
    public RVAdapter adapter;
    EmptyRecyclerView rv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_builder);
        rv = findViewById(R.id.rv);
        View emptyView = findViewById(R.id.empty_view);
        FloatingActionButton fab = findViewById(R.id.fab);
        constraintLayout = findViewById(R.id.constraintLayout);
        mDatabase = FirebaseDatabase.getInstance();
        workouts = new LinkedList<>();
        mRef = mDatabase.getReference("Users").child(mAuth.getCurrentUser().getUid()).child("workouts");
        workoutList = new ArrayList<>();
        // mRef = FirebaseDatabase.getInstance().getReference().child("Users");

        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle("Program Manager");

        RecyclerView.LayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setLayoutManager(llm);
        rv.setEmptyView(emptyView);

        ItemTouchHelper.SimpleCallback touchHelperCallBack = new RecyclerTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(touchHelperCallBack).attachToRecyclerView(rv);
        initializeData();

        workoutDialog = new Dialog(this);

    }

    private void initializeData() {
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    Workout workout = childDataSnapshot.getValue(Workout.class);
                    workoutList.add(workout);
//                    List<Exercise> exercises = muscle.getExercises();
//                    Log.d("BOOM", exercises.get(0).getName());
//                    Log.d("BOOM",exercises.get(0).getMechanics());
//                    Log.d("BOOM", (String.valueOf(exercises.get(0).getID())));
//                    List<Equipment> equipment = exercises.get(0).getEquip();
//
//                    for (int i = 0 ; i < equipment.size() ; i++)
//                        Log.d("xalue is" , equipment.get(i).getName());

                    //Log.v("BOOM",""+ childDataSnapshot.getKey()); //displays the key for the node
                    //Log.v("BOOM2",""+ childDataSnapshot.child("0").child("name").getValue());   //gives the value for given keyname
                }
                adapter = new RVAdapter(workoutList);
                rv.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.d("NOPE", "Failed to read value.", error.toException());
            }
        });
    }



    public void popUp(View view) {
        Button btnClose;
        Button btnOK;
        workoutDialog.setContentView(R.layout.addworkout);
        final TextInputLayout name = workoutDialog.findViewById(R.id.input_workout_name);
        btnClose = workoutDialog.findViewById(R.id.cancel_adding);
        btnOK = workoutDialog.findViewById(R.id.done_adding);
        final MaterialBetterSpinner workoutTypeSpinner = workoutDialog.findViewById(R.id.type_spinner);
        final MaterialBetterSpinner workoutDateSpinner = workoutDialog.findViewById(R.id.day_spinner);
        final MaterialBetterSpinner workoutLevelSpinner = workoutDialog.findViewById(R.id.lvl_spinner);
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
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Workout w = new Workout();
                w.setName(name.getEditText().getText().toString());
                w.setType(workoutTypeSpinner.getText().toString());
                w.setId(randomFind());
                w.setSize(Integer.parseInt(workoutDateSpinner.getText().toString().substring(0, 1)));
                w.setLevel(workoutLevelSpinner.getText().toString());
                workoutList.add(w);
                workoutDialog.dismiss();
                mRef.child(String.valueOf(w.getId())).setValue(w);
                adapter.notifyDataSetChanged();
            }
        });
        workoutDialog.show();
    }

    public int randomFind() {
        Random r = new Random();
        int rr = r.nextInt(100);
        for (int i = 0; i < workoutList.size(); i++)
            if (workoutList.get(i).getId() == rr)
                randomFind();
        return rr;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, final int position) {
        if (viewHolder instanceof RVAdapter.WorkoutViewHolder) {
            // get the removed item name to display it in snack bar
            String name = workoutList.get(viewHolder.getAdapterPosition()).getName();

            // backup of removed item for undo purpose
            final Workout deletedItem = workoutList.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            // remove the item from recycler view
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("workouts").child(String.valueOf(workoutList.get(deletedIndex).getId()));
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snap : dataSnapshot.getChildren()) {
                        snap.getRef().removeValue();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e(TAG, "onCancelled", databaseError.toException());
                }
            });

            adapter.removeItem(viewHolder.getAdapterPosition());


            // showing snack bar with Undo option
            Snackbar snackbar = Snackbar
                    .make(constraintLayout, name + " removed from workouts!", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // undo is selected, restore the deleted item
                    adapter.restoreItem(deletedItem, deletedIndex);
                    mRef.child(String.valueOf(workoutList.get(deletedIndex).getId())).setValue(workoutList.get(position));
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }
}
