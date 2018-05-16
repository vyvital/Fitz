package vyvital.fitz.data;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import vyvital.fitz.MuscleActivity;
import vyvital.fitz.R;
import vyvital.fitz.data.models.Exercise;
import vyvital.fitz.data.models.Exercises;
import vyvital.fitz.data.models.Sets;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.MyViewHolder> {
    private List<Exercise> exercises;
    private Context mContext;
    private int photo;
    private MuscleActivity muscleActivity;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, mechanics;
        private ImageView img, add;
        public Spinner spiner;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.exercise_name);
            mechanics = view.findViewById(R.id.mechanic_name);
            img = view.findViewById(R.id.muscle_photo);
            spiner = view.findViewById(R.id.spinnerz);
            add = view.findViewById(R.id.add_exercise);
        }
    }

    public ExerciseAdapter(Context context, List<Exercise> exercises, int photo, MuscleActivity activity) {
        this.mContext = context;
        this.exercises = exercises;
        this.photo = photo;
        this.muscleActivity = activity;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.musclecard, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Exercise exercise = exercises.get(position);
        holder.name.setText(exercise.getName());
        holder.mechanics.setText(exercise.getMechanics());

        switch (exercise.getId()) {
            case 305:
            case 306:
            case 307:
            case 308:
            case 309:
            case 310:
            case 313:
                this.photo = R.drawable.mham;
                break;
            case 711:
            case 712:
            case 713:
                this.photo = R.drawable.mobliq;
                break;
            case 701:
            case 702:
            case 703:
                this.photo = R.drawable.mabs;
                break;
            case 301:
            case 302:
            case 303:
            case 304:
            case 311:
            case 312:
            case 314:
            case 315:
                this.photo = R.drawable.mquad;
                break;
            case 316:
            case 317:
                this.photo = R.drawable.mcalf;
                break;
        }
        Glide.with(mContext).load(photo).into(holder.img);
        List<String> equip = new ArrayList<>();
        for (int i = 0; i < exercise.getEquip().size(); i++) {
            equip.add(exercise.getEquip().get(i).getName());
        }
        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(mContext,
                android.R.layout.simple_spinner_item, equip);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.spiner.setAdapter(dataAdapter);
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Exercises ex1 = new Exercises();
                ex1.setName(exercise.getName());
                ex1.setEquip(holder.spiner.getSelectedItem().toString());
                ex1.setId(exercise.getId() + "");
                ex1.setMechanics(exercise.getMechanics());
                List<Sets> setsTemp = new ArrayList<>();
                setsTemp.add(new Sets(0, 0));
                ex1.setSets(setsTemp);
                muscleActivity.workout.getDays().get(0).getExercises().add(ex1);
                Snackbar snackbar = Snackbar
                        .make(v, exercise.getName() + " " + String.valueOf(holder.spiner.getSelectedItem()) + " was added!", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }
}