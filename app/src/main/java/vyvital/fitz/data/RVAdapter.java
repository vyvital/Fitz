package vyvital.fitz.data;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import vyvital.fitz.BuilderActivity;
import vyvital.fitz.ExerciseActivity;
import vyvital.fitz.R;
import vyvital.fitz.data.models.Workout;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.WorkoutViewHolder> {

    List<Workout> workouts;
    Context mContext;
    private int photo;

    public RVAdapter(List<Workout> workouts, Context context) {
        this.workouts = workouts;
        this.mContext = context;
    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }

    @Override
    public WorkoutViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.programcard, parent, false);
        return new WorkoutViewHolder(v);
    }

    @Override
    public void onBindViewHolder(WorkoutViewHolder holder, final int position) {
        if (workouts.get(position).isDef())
            holder.rib.setVisibility(View.VISIBLE);
        else holder.rib.setVisibility(View.INVISIBLE);
        switch (workouts.get(position).getType()) {
            case "Strength":
                this.photo = R.drawable.str;
                break;
            case "Hypertrophy":
                this.photo = R.drawable.hyper;
                break;
            case "Endurance":
                this.photo = R.drawable.endurance;
                break;
            case "Maintenance":
                this.photo = R.drawable.maint;
                break;
        }
        Glide.with(mContext).load(photo).into(holder.personPhoto);
        holder.nameOfWorkout.setText(workouts.get(position).getName());
        holder.typeOfWorkout.setText(workouts.get(position).getType());
        holder.days.setText(String.valueOf(workouts.get(position).getSize()));
        holder.level.setText(workouts.get(position).getLevel());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ((BuilderActivity) v.getContext()).popUp(workouts.get(position));
                } catch (Exception e) {
                    // ignore
                }
            }
        });
        holder.background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Workout w = workouts.get(position);
                Context context = v.getContext();
                Intent i = new Intent(context, ExerciseActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("workout", w);
                i.putExtras(bundle);
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
        ImageButton edit;
        ImageView rib;

        WorkoutViewHolder(View itemView) {
            super(itemView);
            background = itemView.findViewById(R.id.backgroundView);
            cv = itemView.findViewById(R.id.foregroundView);
            nameOfWorkout = itemView.findViewById(R.id.workout_name);
            typeOfWorkout = itemView.findViewById(R.id.workout_type);
            level = itemView.findViewById(R.id.workout_level);
            days = itemView.findViewById(R.id.workout_days);
            personPhoto = itemView.findViewById(R.id.workout_photo);
            edit = itemView.findViewById(R.id.edit);
            rib = itemView.findViewById(R.id.rib);
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