package vyvital.fitz.data;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;

import vyvital.fitz.R;

public class TitleChildViewHolder extends ChildViewHolder {
    public EditText reps, weight;

    public TitleChildViewHolder(View itemView) {
        super(itemView);
        reps = itemView.findViewById(R.id.exReps);
        weight = itemView.findViewById(R.id.exWeight);
    }
}
