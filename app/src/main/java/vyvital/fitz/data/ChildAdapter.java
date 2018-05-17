package vyvital.fitz.data;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.List;

import vyvital.fitz.R;
import vyvital.fitz.WorkingOutActivity;
import vyvital.fitz.data.models.Sets;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ChildViewHolder> {

    private List<Sets> setsList;
    private Context context;
    boolean today = false;
    boolean color;
    private WorkingOutActivity workingOutActivity;

    public ChildAdapter(Context context, List<Sets> sets) {
        this.context = context;
        this.setsList = sets;
    }

    public ChildAdapter(Context context, List<Sets> sets, boolean today) {
        this.context = context;
        this.setsList = sets;
        this.today = today;
        this.workingOutActivity = (WorkingOutActivity) context;
    }

    @Override
    public ChildViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.setcard, viewGroup, false);
        return new ChildViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ChildViewHolder viewHolder, final int position) {
        Sets set = setsList.get(position);
        viewHolder.weight.setTag(set);
        viewHolder.reps.setTag(set);
        viewHolder.weight.setText(String.valueOf(set.getWeight()));
        viewHolder.reps.setText(String.valueOf(set.getReps()));
        if (today) {
            viewHolder.reps.setEnabled(false);
            viewHolder.weight.setEnabled(false);
            viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ColorDrawable viewColor = (ColorDrawable) viewHolder.linearLayout.getBackground();
                    if (viewColor.getColor() == ContextCompat.getColor(context, R.color.lightGrey)) {
                        workingOutActivity.count++;
                        viewHolder.linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.lightGreen));
                    } else {
                        if (workingOutActivity.count > 0)
                            workingOutActivity.count--;
                        viewHolder.linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.lightGrey));
                    }
                    if (workingOutActivity.count == workingOutActivity.total)
                        workingOutActivity.Celebrate();
                }
            });
        } else {

            viewHolder.reps.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s.length() > 0)
                        ((Sets) viewHolder.reps.getTag()).setReps(Integer.parseInt(s.toString()));
                    else if (s.toString().equals(""))
                        viewHolder.reps.setText("0");
                }
            });

            viewHolder.weight.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s.length() > 0)
                        ((Sets) viewHolder.weight.getTag()).setWeight(Integer.parseInt(s.toString()));
                    if (s.toString().equals(""))
                        viewHolder.weight.setText("0");
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return setsList.size();
    }

    public final static class ChildViewHolder extends RecyclerView.ViewHolder {
        public EditText reps, weight;
        public LinearLayout linearLayout;

        public ChildViewHolder(View itemView) {
            super(itemView);
            reps = itemView.findViewById(R.id.exReps);
            weight = itemView.findViewById(R.id.exWeight);
            linearLayout = itemView.findViewById(R.id.RLayoutSet);
        }
    }
}