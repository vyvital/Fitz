package vyvital.fitz.data;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import vyvital.fitz.BuilderActivity;
import vyvital.fitz.ExerciseActivity;
import vyvital.fitz.R;
import vyvital.fitz.data.models.Workout;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.WorkoutViewHolder> {

    List<Workout> workouts;

    public RVAdapter(List<Workout> workouts) {
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
    public void onBindViewHolder(WorkoutViewHolder holder, final int position) {
        holder.nameOfWorkout.setText(workouts.get(position).getName());
        holder.typeOfWorkout.setText(workouts.get(position).getType());
        holder.days.setText(String.valueOf(workouts.get(position).getSize()));
        holder.level.setText(workouts.get(position).getLevel());
        holder.background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Log.d("testing",workouts.get(position).getDays().get(0).getName());
                Context context = v.getContext();
                Intent i = new Intent(context,ExerciseActivity.class);
                i.putExtra("workout",workouts.get(position));
                context.startActivity(i);
            }
        });

    }

    public static class WorkoutViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout cv, background;
        TextView nameOfWorkout;
        TextView typeOfWorkout;
        TextView level;
        TextView days;
        ImageView personPhoto;

        WorkoutViewHolder(View itemView) {
            super(itemView);
            background = itemView.findViewById(R.id.backgroundView);
            cv = itemView.findViewById(R.id.foregroundView);
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

    public void removeItem(int position) {
        workouts.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(Workout item, int position) {
        workouts.add(position, item);
        notifyItemInserted(position);
    }
}
