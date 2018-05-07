package vyvital.fitz.data;

import android.view.View;
import android.widget.EditText;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import vyvital.fitz.R;
import vyvital.fitz.data.models.Sets;

public class SetsViewHolder extends com.bignerdranch.expandablerecyclerview.ChildViewHolder{
    public EditText reps, weight;
    public SetsViewHolder(View itemView) {
        super(itemView);
        reps = itemView.findViewById(R.id.exReps);
        weight = itemView.findViewById(R.id.exWeight);
    }

    public void bind(Sets sets){
        weight.setText(String.valueOf(sets.getWeight()));
        reps.setText(String.valueOf(sets.getReps()));
    }
}
