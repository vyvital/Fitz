package vyvital.fitz;


import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

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
import vyvital.fitz.data.models.Days;
import vyvital.fitz.data.models.Workout;


public class BuilderActivity extends BaseActivity implements RecyclerTouchHelper.RecyclerItemTouchHelperListener {


    String[] type_data = {"Strength", "Hypertrophy", "Maintenance", "Endurance"};
    String[] lvl_data = {"Novice", "Intermediate", "Advanced", "Elite"};
    String[] day_data = {"1 Day", "2 Days", "3 Days", "4 Days", "5 Days", "6 days", "7 Days"};
    Dialog workoutDialog;
    Dialog workoutDialogDays;
    Context context;
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
        context = BuilderActivity.this;
        mDatabase = FirebaseDatabase.getInstance();

        mRef = mDatabase.getReference("Users").child(mAuth.getCurrentUser().getUid()).child("workouts");
        workoutList = new ArrayList<>();


        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle("Program Manager");

        RecyclerView.LayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setLayoutManager(llm);
        rv.setEmptyView(emptyView);
        Animation bottomUp = AnimationUtils.loadAnimation(this, R.anim.item_animation_from_right);
        rv.startAnimation(bottomUp);
        ItemTouchHelper.SimpleCallback touchHelperCallBack = new RecyclerTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(touchHelperCallBack).attachToRecyclerView(rv);
        initializeData();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUp(null);
            }
        });

        workoutDialog = new Dialog(this);
        workoutDialogDays = new Dialog(this);
        saveDefault();
    }

    private void saveDefault() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("Tdee", Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = sharedPreferences.edit();
        for (Workout p : workoutList){
            if (p.isDef())
                mEditor.putInt("DEF",p.getId());
        }
        mEditor.apply();
    }

    private void initializeData() {
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    Workout workout = childDataSnapshot.getValue(Workout.class);
                    workoutList.add(workout);
                }
                adapter = new RVAdapter(workoutList,context);
                rv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.d("NOPE", "Failed to read value.", error.toException());
            }
        });
    }

    public void popDown(final Workout workout) {
        Button btnClosePop;
        Button btnOKPop;
        workoutDialogDays.setContentView(R.layout.addworkoutdays);
        btnOKPop = workoutDialogDays.findViewById(R.id.done_adding2);
        btnClosePop = workoutDialogDays.findViewById(R.id.cancel_adding2);
        final EditText day1 = workoutDialogDays.findViewById(R.id.day1);
        final EditText day2 = workoutDialogDays.findViewById(R.id.day2);
        final EditText day3 = workoutDialogDays.findViewById(R.id.day3);
        final EditText day4 = workoutDialogDays.findViewById(R.id.day4);
        final EditText day5 = workoutDialogDays.findViewById(R.id.day5);
        final EditText day6 = workoutDialogDays.findViewById(R.id.day6);
        final EditText day7 = workoutDialogDays.findViewById(R.id.day7);
        if (workout.getDays() != null) {
            day1.setText(workout.getDays().get(0).getName());
            switch (workout.getDays().size()) {
                case 2:
                    day2.setText(workout.getDays().get(1).getName());
                    break;
                case 3:
                    day2.setText(workout.getDays().get(1).getName());
                    day3.setText(workout.getDays().get(2).getName());
                    break;
                case 4:
                    day2.setText(workout.getDays().get(1).getName());
                    day3.setText(workout.getDays().get(2).getName());
                    day4.setText(workout.getDays().get(3).getName());
                    break;
                case 5:
                    day2.setText(workout.getDays().get(1).getName());
                    day3.setText(workout.getDays().get(2).getName());
                    day4.setText(workout.getDays().get(3).getName());
                    day5.setText(workout.getDays().get(4).getName());
                    break;
                case 6:
                    day2.setText(workout.getDays().get(1).getName());
                    day3.setText(workout.getDays().get(2).getName());
                    day4.setText(workout.getDays().get(3).getName());
                    day5.setText(workout.getDays().get(4).getName());
                    day6.setText(workout.getDays().get(5).getName());
                    break;
                default:
                    day2.setText(workout.getDays().get(1).getName());
                    day3.setText(workout.getDays().get(2).getName());
                    day4.setText(workout.getDays().get(3).getName());
                    day5.setText(workout.getDays().get(4).getName());
                    day6.setText(workout.getDays().get(5).getName());
                    day7.setText(workout.getDays().get(6).getName());
                    break;

            }

        }
        final List<Days> daysList = new ArrayList<>();
        final Days d1, d2, d3, d4, d5, d6, d7;
        d1 = new Days();
        d2 = new Days();
        d3 = new Days();
        d4 = new Days();
        d5 = new Days();
        d6 = new Days();
        d7 = new Days();
        switch (workout.getSize()) {
            case 1:
                day2.setVisibility(View.INVISIBLE);
                day3.setVisibility(View.INVISIBLE);
                day4.setVisibility(View.INVISIBLE);
                day5.setVisibility(View.INVISIBLE);
                day6.setVisibility(View.INVISIBLE);
                day7.setVisibility(View.INVISIBLE);
                break;
            case 2:
                day3.setVisibility(View.INVISIBLE);
                day4.setVisibility(View.INVISIBLE);
                day5.setVisibility(View.INVISIBLE);
                day6.setVisibility(View.INVISIBLE);
                day7.setVisibility(View.INVISIBLE);
                break;
            case 3:
                day4.setVisibility(View.INVISIBLE);
                day5.setVisibility(View.INVISIBLE);
                day6.setVisibility(View.INVISIBLE);
                day7.setVisibility(View.INVISIBLE);
                break;
            case 4:
                day5.setVisibility(View.INVISIBLE);
                day6.setVisibility(View.INVISIBLE);
                day7.setVisibility(View.INVISIBLE);
                break;
            case 5:
                day6.setVisibility(View.INVISIBLE);
                day7.setVisibility(View.INVISIBLE);
                break;
            case 6:
                day7.setVisibility(View.INVISIBLE);
                break;
            default:
                break;
        }

        btnClosePop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workoutDialogDays.dismiss();
            }
        });
        btnOKPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (workout.getDays() == null) {
                    switch (workout.getSize()) {
                        case 1:
                            d1.setName(day1.getText().toString());
                            daysList.add(d1);
                            break;
                        case 2:
                            d1.setName(day1.getText().toString());
                            d2.setName(day2.getText().toString());
                            daysList.add(d1);
                            daysList.add(d2);
                            break;
                        case 3:
                            d1.setName(day1.getText().toString());
                            d2.setName(day2.getText().toString());
                            d3.setName(day3.getText().toString());
                            daysList.add(d1);
                            daysList.add(d2);
                            daysList.add(d3);
                            break;
                        case 4:
                            d1.setName(day1.getText().toString());
                            d2.setName(day2.getText().toString());
                            d3.setName(day3.getText().toString());
                            d4.setName(day4.getText().toString());
                            daysList.add(d1);
                            daysList.add(d2);
                            daysList.add(d3);
                            daysList.add(d4);
                            break;
                        case 5:
                            d1.setName(day1.getText().toString());
                            d2.setName(day2.getText().toString());
                            d3.setName(day3.getText().toString());
                            d4.setName(day4.getText().toString());
                            d5.setName(day5.getText().toString());
                            daysList.add(d1);
                            daysList.add(d2);
                            daysList.add(d3);
                            daysList.add(d4);
                            daysList.add(d5);
                            break;
                        case 6:
                            d1.setName(day1.getText().toString());
                            d2.setName(day2.getText().toString());
                            d3.setName(day3.getText().toString());
                            d4.setName(day4.getText().toString());
                            d5.setName(day5.getText().toString());
                            d6.setName(day6.getText().toString());
                            daysList.add(d1);
                            daysList.add(d2);
                            daysList.add(d3);
                            daysList.add(d4);
                            daysList.add(d5);
                            daysList.add(d6);
                            break;
                        default:
                            d1.setName(day1.getText().toString());
                            d2.setName(day2.getText().toString());
                            d3.setName(day3.getText().toString());
                            d4.setName(day4.getText().toString());
                            d5.setName(day5.getText().toString());
                            d6.setName(day6.getText().toString());
                            d7.setName(day6.getText().toString());
                            daysList.add(d1);
                            daysList.add(d2);
                            daysList.add(d3);
                            daysList.add(d4);
                            daysList.add(d5);
                            daysList.add(d6);
                            daysList.add(d7);
                            break;

                    }
                } else {
                    switch (workout.getSize()) {
                        case 1:
                            if (workout.getDays().get(0).getExercises() != null)
                                d1.setExercises(workout.getDays().get(0).getExercises());
                            d1.setName(day1.getText().toString());
                            daysList.add(d1);
                            break;
                        case 2:
                            if (workout.getDays().get(0).getExercises() != null)
                                d1.setExercises(workout.getDays().get(0).getExercises());
                            d1.setName(day1.getText().toString());
                            daysList.add(d1);
                            if (workout.getDays().size() > 1)
                                if (workout.getDays().get(1).getExercises() != null)
                                    d2.setExercises(workout.getDays().get(1).getExercises());
                            d2.setName(day2.getText().toString());
                            daysList.add(d2);
                            break;

                        case 3:
                            if (workout.getDays().get(0).getExercises() != null)
                                d1.setExercises(workout.getDays().get(0).getExercises());
                            d1.setName(day1.getText().toString());
                            daysList.add(d1);
                            if (workout.getDays().size() > 1)
                                if (workout.getDays().get(1).getExercises() != null)
                                    d2.setExercises(workout.getDays().get(1).getExercises());
                            d2.setName(day2.getText().toString());
                            daysList.add(d2);
                            if (workout.getDays().size() > 2)
                                if (workout.getDays().get(2).getExercises() != null)
                                    d3.setExercises(workout.getDays().get(2).getExercises());
                            d3.setName(day3.getText().toString());
                            daysList.add(d3);
                            break;
                        case 4:
                            if (workout.getDays().get(0).getExercises() != null)
                                d1.setExercises(workout.getDays().get(0).getExercises());
                            d1.setName(day1.getText().toString());
                            daysList.add(d1);
                            if (workout.getDays().size() > 1)
                                if (workout.getDays().get(1).getExercises() != null)
                                    d2.setExercises(workout.getDays().get(1).getExercises());
                            d2.setName(day2.getText().toString());
                            daysList.add(d2);
                            if (workout.getDays().size() > 2)
                                if (workout.getDays().get(2).getExercises() != null)
                                    d3.setExercises(workout.getDays().get(2).getExercises());
                            d3.setName(day3.getText().toString());
                            daysList.add(d3);
                            if (workout.getDays().size() > 3)
                                if (workout.getDays().get(3).getExercises() != null)
                                    d4.setExercises(workout.getDays().get(3).getExercises());
                            d4.setName(day4.getText().toString());
                            daysList.add(d4);
                            break;
                        case 5:
                            if (workout.getDays().get(0).getExercises() != null)
                                d1.setExercises(workout.getDays().get(0).getExercises());
                            d1.setName(day1.getText().toString());
                            daysList.add(d1);
                            if (workout.getDays().size() > 1)
                                if (workout.getDays().get(1).getExercises() != null)
                                    d2.setExercises(workout.getDays().get(1).getExercises());
                            d2.setName(day2.getText().toString());
                            daysList.add(d2);
                            if (workout.getDays().size() > 2)
                                if (workout.getDays().get(2).getExercises() != null)
                                    d3.setExercises(workout.getDays().get(2).getExercises());
                            d3.setName(day3.getText().toString());
                            daysList.add(d3);
                            if (workout.getDays().size() > 3)
                                if (workout.getDays().get(3).getExercises() != null)
                                    d4.setExercises(workout.getDays().get(3).getExercises());
                            d4.setName(day4.getText().toString());
                            daysList.add(d4);
                            if (workout.getDays().size() > 4)
                                if (workout.getDays().get(4).getExercises() != null)
                                    d5.setExercises(workout.getDays().get(4).getExercises());
                            d5.setName(day5.getText().toString());
                            daysList.add(d5);
                            break;
                        case 6:
                            if (workout.getDays().get(0).getExercises() != null)
                                d1.setExercises(workout.getDays().get(0).getExercises());
                            d1.setName(day1.getText().toString());
                            daysList.add(d1);
                            if (workout.getDays().size() > 1)
                                if (workout.getDays().get(1).getExercises() != null)
                                    d2.setExercises(workout.getDays().get(1).getExercises());
                            d2.setName(day2.getText().toString());
                            daysList.add(d2);
                            if (workout.getDays().size() > 2)
                                if (workout.getDays().get(2).getExercises() != null)
                                    d3.setExercises(workout.getDays().get(2).getExercises());
                            d3.setName(day3.getText().toString());
                            daysList.add(d3);
                            if (workout.getDays().size() > 3)
                                if (workout.getDays().get(3).getExercises() != null)
                                    d4.setExercises(workout.getDays().get(3).getExercises());
                            d4.setName(day4.getText().toString());
                            daysList.add(d4);
                            if (workout.getDays().size() > 4)
                                if (workout.getDays().get(4).getExercises() != null)
                                    d5.setExercises(workout.getDays().get(4).getExercises());
                            d5.setName(day5.getText().toString());
                            daysList.add(d5);
                            if (workout.getDays().size() > 5)
                                if (workout.getDays().get(5).getExercises() != null)
                                    d6.setExercises(workout.getDays().get(5).getExercises());
                            d6.setName(day6.getText().toString());
                            daysList.add(d6);
                            break;
                        default:
                            if (workout.getDays().get(0).getExercises() != null)
                                d1.setExercises(workout.getDays().get(0).getExercises());
                            d1.setName(day1.getText().toString());
                            daysList.add(d1);
                            if (workout.getDays().size() > 1)
                                if (workout.getDays().get(1).getExercises() != null)
                                    d2.setExercises(workout.getDays().get(1).getExercises());
                            d2.setName(day2.getText().toString());
                            daysList.add(d2);
                            if (workout.getDays().size() > 2)
                                if (workout.getDays().get(2).getExercises() != null)
                                    d3.setExercises(workout.getDays().get(2).getExercises());
                            d3.setName(day3.getText().toString());
                            daysList.add(d3);
                            if (workout.getDays().size() > 3)
                                if (workout.getDays().get(3).getExercises() != null)
                                    d4.setExercises(workout.getDays().get(3).getExercises());
                            d4.setName(day4.getText().toString());
                            daysList.add(d4);
                            if (workout.getDays().size() > 4)
                                if (workout.getDays().get(4).getExercises() != null)
                                    d5.setExercises(workout.getDays().get(4).getExercises());
                            d5.setName(day5.getText().toString());
                            daysList.add(d5);
                            if (workout.getDays().size() > 5)
                                if (workout.getDays().get(5).getExercises() != null)
                                    d6.setExercises(workout.getDays().get(5).getExercises());
                            d6.setName(day6.getText().toString());
                            daysList.add(d6);
                            if (workout.getDays().size() > 6)
                                if (workout.getDays().get(6).getExercises() != null)
                                    d7.setExercises(workout.getDays().get(6).getExercises());
                            d7.setName(day7.getText().toString());
                            daysList.add(d7);
                            break;
                    }
                }
                workout.setDays(daysList);
                if (!workoutList.contains(workout))
                    workoutList.add(workout);
                workoutDialogDays.dismiss();
                mRef.child(String.valueOf(workout.getId())).setValue(workout);
                adapter.notifyDataSetChanged();
            }
        });
        workoutDialogDays.show();
    }

    public void popUp(final Workout zzz) {
        Button btnClose;
        Button btnOK;
        Button setDefault;
        workoutDialog.setContentView(R.layout.addworkout);

        final TextInputLayout name = workoutDialog.findViewById(R.id.input_workout_name);
        btnClose = workoutDialog.findViewById(R.id.cancel_adding);
        btnOK = workoutDialog.findViewById(R.id.done_adding);
        setDefault = workoutDialog.findViewById(R.id.set_default);
        setDefault.setVisibility(View.GONE);
        final MaterialBetterSpinner workoutTypeSpinner = workoutDialog.findViewById(R.id.type_spinner);
        final MaterialBetterSpinner workoutDateSpinner = workoutDialog.findViewById(R.id.day_spinner);
        final MaterialBetterSpinner workoutLevelSpinner = workoutDialog.findViewById(R.id.lvl_spinner);
        final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, type_data);
        final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, day_data);
        final ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, lvl_data);
        workoutTypeSpinner.setAdapter(adapter1);
        workoutDateSpinner.setAdapter(adapter2);
        workoutLevelSpinner.setAdapter(adapter3);

        if (zzz != null) {
            name.getEditText().setText(zzz.getName());
            workoutDateSpinner.setText(adapter2.getItem(zzz.getSize() - 1));
            workoutLevelSpinner.setText(zzz.getLevel());
            workoutTypeSpinner.setText(zzz.getType());
            if (zzz.isDef())
            setDefault.setVisibility(View.GONE);
            else setDefault.setVisibility(View.VISIBLE);
        }
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workoutDialog.dismiss();
            }
        });
        setDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Workout w : workoutList){
                    w.setDef(false);
                    if (w.getId()==zzz.getId())
                        w.setDef(true);
                }
                saveDefault();
                mRef.setValue(workoutList);
                adapter.notifyDataSetChanged();
                workoutDialog.dismiss();
            }
        });
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Workout w = new Workout();
                if (zzz == null) {
                    w.setName(name.getEditText().getText().toString());
                    if (name.getEditText().getText().toString().equals("")) {
                        name.getEditText().setText("Temp name");
                        w.setName("Temp name");
                    } else if (workoutTypeSpinner.getText().toString().equals(""))
                        workoutTypeSpinner.setError("Don't forget me!");
                    else if (workoutLevelSpinner.getText().toString().equals(""))
                        workoutLevelSpinner.setError("Don't forget me!");
                    else if (workoutDateSpinner.getText().toString().equals(""))
                        workoutDateSpinner.setError("Don't forget me!");
                    else {
                        w.setType(workoutTypeSpinner.getText().toString());
                        w.setId(randomFind());
                        w.setSize(Integer.parseInt(workoutDateSpinner.getText().toString().substring(0, 1)));
                        w.setLevel(workoutLevelSpinner.getText().toString());
                        popDown(w);
                        workoutDialog.dismiss();
                    }
                } else {
                    zzz.setName(name.getEditText().getText().toString());
                    if (name.getEditText().getText().toString().equals("")) {
                        name.getEditText().setText("Temp name");
                        zzz.setName("Temp name");
                    } else if (workoutTypeSpinner.getText().toString().equals(""))
                        workoutTypeSpinner.setError("Don't forget me!");
                    else if (workoutLevelSpinner.getText().toString().equals(""))
                        workoutLevelSpinner.setError("Don't forget me!");
                    else if (workoutDateSpinner.getText().toString().equals(""))
                        workoutDateSpinner.setError("Don't forget me!");
                    else {
                        zzz.setType(workoutTypeSpinner.getText().toString());
                        zzz.setSize(Integer.parseInt(workoutDateSpinner.getText().toString().substring(0, 1)));
                        zzz.setLevel(workoutLevelSpinner.getText().toString());
                        popDown(zzz);
                        workoutDialog.dismiss();
                    }
                }
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
