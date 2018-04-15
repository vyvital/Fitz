package vyvital.fitz.data;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import vyvital.fitz.R;
import vyvital.fitz.data.models.Workout;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.WorkoutViewHolder> {

    List<Workout> workouts;

    public RVAdapter(List<Workout> workouts){
        this.workouts = workouts;
    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }

    @Override
    public WorkoutViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.programcard, parent, false);
        WorkoutViewHolder wvh = new WorkoutViewHolder(v);
        return wvh;
    }

    @Override
    public void onBindViewHolder(WorkoutViewHolder holder, int position) {
        holder.nameOfWorkout.setText(workouts.get(position).getName());
        Log.d("BOOM",workouts.get(position).getName());
        holder.typeOfWorkout.setText(workouts.get(position).getType());
        holder.days.setText(String.valueOf(workouts.get(position).getSize()));
        holder.level.setText(workouts.get(position).getLevel());

    }

    public static class WorkoutViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView nameOfWorkout;
        TextView typeOfWorkout;
        TextView level;
        TextView days;
        ImageView personPhoto;

        WorkoutViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            nameOfWorkout = itemView.findViewById(R.id.workout_name);
            typeOfWorkout = itemView.findViewById(R.id.workout_type);
            level = itemView.findViewById(R.id.workout_level);
            days = itemView.findViewById(R.id.workout_days);
            personPhoto = itemView.findViewById(R.id.workout_photo);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
