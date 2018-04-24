package vyvital.fitz.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import vyvital.fitz.R;
import vyvital.fitz.data.models.Muscle;


public class FragAMuscle extends Fragment {
    public static final String TAG = FragAMuscle.class.getSimpleName();
    private FirebaseDatabase mDatabase;
    private DatabaseReference myRef = null;
    private List<Muscle> muscleList;

    public FragAMuscle() {
        // Required empty public constructor
    }

    public static FragAMuscle newInstance() {
        return new FragAMuscle();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_muscle_a, container, false);
        mDatabase = FirebaseDatabase.getInstance();
        myRef = mDatabase.getReference("muscles");
        muscleList = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    Muscle muscle = childDataSnapshot.getValue(Muscle.class);
                    muscleList.add(muscle);
                    //    Log.d("BOOM", "" + muscle.getName());
//                    List<Exercise> exercises = muscle.getExercises();
//                    Log.d("BOOM", exercises.get(0).getName());
//                    Log.d("BOOM",exercises.get(0).getMechanics());
//                    Log.d("BOOM", (String.valueOf(exercises.get(0).getID())));
//                    List<Equipment> equipment = exercises.get(0).getEquip();
//
//                    for (int i = 0 ; i < equipment.size() ; i++)
//                        Log.d("value is" , equipment.get(i).getName());

                    //Log.v("BOOM",""+ childDataSnapshot.getKey()); //displays the key for the node
                    //Log.v("BOOM2",""+ childDataSnapshot.child("0").child("name").getValue());   //gives the value for given keyname
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.d("NOPE", "Failed to read value.", error.toException());
            }
        });

        final ImageButton abs_view = view.findViewById(R.id.abs);
        final ImageButton chest_view = view.findViewById(R.id.chest);
        final ImageButton back_view = view.findViewById(R.id.back);
        final ImageButton tricep_view = view.findViewById(R.id.tricep);
        final ImageButton bicep_view = view.findViewById(R.id.bicep);
        final ImageButton legs_view = view.findViewById(R.id.legs);
        final ImageButton cardio_view = view.findViewById(R.id.cardio);
        final ImageButton crossfit_view = view.findViewById(R.id.crossfit);
        final ImageButton shoulder_view = view.findViewById(R.id.shoulder);
        abs_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("PIC", R.drawable.abs_t);
                bundle.putString("TRANS", abs_view.getTransitionName());
                FragBMuscle simpleFragmentB = FragBMuscle.newInstance();
                simpleFragmentB.setArguments(bundle);
                assert getFragmentManager() != null;
                getFragmentManager()
                        .beginTransaction()
                        .addSharedElement(abs_view, ViewCompat.getTransitionName(abs_view))
                        .addToBackStack(TAG)
                        .replace(R.id.content, simpleFragmentB)
                        .commit();
            }
        });

        chest_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("PIC", R.drawable.chest_t);
                bundle.putString("TRANS", chest_view.getTransitionName());
                bundle.putParcelable("MUSCLE", muscleList.get(0));
                bundle.putInt("PHOTO", R.drawable.mchest);
                FragBMuscle simpleFragmentB = FragBMuscle.newInstance();
                simpleFragmentB.setArguments(bundle);
                assert getFragmentManager() != null;
                getFragmentManager()
                        .beginTransaction()
                        .addSharedElement(chest_view, ViewCompat.getTransitionName(chest_view))
                        .addToBackStack(TAG)
                        .replace(R.id.content, simpleFragmentB)
                        .commit();
            }
        });
        back_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("PIC", R.drawable.back_t);
                bundle.putParcelable("MUSCLE", muscleList.get(1));
                bundle.putString("TRANS", back_view.getTransitionName());
                FragBMuscle simpleFragmentB = FragBMuscle.newInstance();
                simpleFragmentB.setArguments(bundle);
                assert getFragmentManager() != null;
                getFragmentManager()
                        .beginTransaction()
                        .addSharedElement(back_view, ViewCompat.getTransitionName(back_view))
                        .addToBackStack(TAG)
                        .replace(R.id.content, simpleFragmentB)
                        .commit();
            }
        });
        tricep_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("PIC", R.drawable.tricep_t);
                bundle.putString("TRANS", tricep_view.getTransitionName());
                FragBMuscle simpleFragmentB = FragBMuscle.newInstance();
                simpleFragmentB.setArguments(bundle);
                assert getFragmentManager() != null;
                getFragmentManager()
                        .beginTransaction()
                        .addSharedElement(tricep_view, ViewCompat.getTransitionName(tricep_view))
                        .addToBackStack(TAG)
                        .replace(R.id.content, simpleFragmentB)
                        .commit();
            }
        });
        bicep_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("PIC", R.drawable.bicep_t);
                bundle.putString("TRANS", bicep_view.getTransitionName());
                FragBMuscle simpleFragmentB = FragBMuscle.newInstance();
                simpleFragmentB.setArguments(bundle);
                assert getFragmentManager() != null;
                getFragmentManager()
                        .beginTransaction()
                        .addSharedElement(bicep_view, ViewCompat.getTransitionName(bicep_view))
                        .addToBackStack(TAG)
                        .replace(R.id.content, simpleFragmentB)
                        .commit();
            }
        });
        legs_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("PIC", R.drawable.legs_t);
                bundle.putString("TRANS", legs_view.getTransitionName());
                FragBMuscle simpleFragmentB = FragBMuscle.newInstance();
                simpleFragmentB.setArguments(bundle);
                assert getFragmentManager() != null;
                getFragmentManager()
                        .beginTransaction()
                        .addSharedElement(legs_view, ViewCompat.getTransitionName(legs_view))
                        .addToBackStack(TAG)
                        .replace(R.id.content, simpleFragmentB)
                        .commit();
            }
        });
        cardio_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("PIC", R.drawable.cardio_t);
                bundle.putString("TRANS", cardio_view.getTransitionName());
                FragBMuscle simpleFragmentB = FragBMuscle.newInstance();
                simpleFragmentB.setArguments(bundle);
                assert getFragmentManager() != null;
                getFragmentManager()
                        .beginTransaction()
                        .addSharedElement(cardio_view, ViewCompat.getTransitionName(cardio_view))
                        .addToBackStack(TAG)
                        .replace(R.id.content, simpleFragmentB)
                        .commit();
            }
        });
        crossfit_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("PIC", R.drawable.crossfit_t);
                bundle.putString("TRANS", crossfit_view.getTransitionName());
                FragBMuscle simpleFragmentB = FragBMuscle.newInstance();
                simpleFragmentB.setArguments(bundle);
                assert getFragmentManager() != null;
                getFragmentManager()
                        .beginTransaction()
                        .addSharedElement(crossfit_view, ViewCompat.getTransitionName(crossfit_view))
                        .addToBackStack(TAG)
                        .replace(R.id.content, simpleFragmentB)
                        .commit();
            }
        });
        shoulder_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("PIC", R.drawable.shoulder_t);
                bundle.putString("TRANS", shoulder_view.getTransitionName());
                FragBMuscle simpleFragmentB = FragBMuscle.newInstance();
                simpleFragmentB.setArguments(bundle);
                assert getFragmentManager() != null;
                getFragmentManager()
                        .beginTransaction()
                        .addSharedElement(shoulder_view, ViewCompat.getTransitionName(shoulder_view))
                        .addToBackStack(TAG)
                        .replace(R.id.content, simpleFragmentB)
                        .commit();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //initialize(view);


    }


}