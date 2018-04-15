package vyvital.fitz.data;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import vyvital.fitz.R;
import vyvital.fitz.data.models.Exercise;
import vyvital.fitz.data.models.Muscle;


public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.MyViewHolder>{
    private List<Exercise> exercises;
    private Context mContext;
    private int photo;
    private Spinner spin;





    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, mechanics;
        private ImageView img;
        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.exercise_name);
            mechanics = view.findViewById(R.id.mechanic_name);
            img = view.findViewById(R.id.muscle_photo);
            spin = view.findViewById(R.id.spinner);
        }
    }

    public ExerciseAdapter(Context context, List<Exercise> exercises,int photo) {
        this.mContext = context;
        this.exercises = exercises;
        this.photo = photo;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.musclecard, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Exercise exercise = exercises.get(position);
        holder.name.setText(exercise.getName());
        holder.mechanics.setText(exercise.getMechanics());
        Glide.with(mContext).load(photo).into(holder.img);
        List<String> equip = new ArrayList<>();
        for (int i = 0;i<exercise.getEquip().size();i++){
            equip.add(exercise.getEquip().get(i).getName());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(mContext,
                android.R.layout.simple_spinner_item, equip);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(dataAdapter);

    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }
}
