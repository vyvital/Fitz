package vyvital.fitz.data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.List;

import vyvital.fitz.R;
import vyvital.fitz.data.models.Sets;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ChildViewHolder> {

    private List<Sets> setsList;
    private Context context;


    public ChildAdapter(Context context, List<Sets> sets) {
        this.context = context;
        this.setsList = sets;

    }

    @Override
    public ChildViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.setcard, viewGroup, false);
        return new ChildViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ChildViewHolder viewHolder, final int position) {
        Sets s = setsList.get(position);
        viewHolder.weight.setText(String.valueOf(s.getWeight()));
        viewHolder.reps.setText(String.valueOf(s.getReps()));
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
                    setsList.get(position).setReps(Integer.parseInt(s.toString()));
                if (s.toString().equals(""))
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
                    setsList.get(position).setWeight(Integer.parseInt(s.toString()));
                if (s.toString().equals(""))
                    viewHolder.weight.setText("0");
            }
        });

    }

    @Override
    public int getItemCount() {
        return setsList.size();
    }

    public final static class ChildViewHolder extends RecyclerView.ViewHolder {
        public EditText reps, weight;

        public ChildViewHolder(View itemView) {
            super(itemView);
            reps = itemView.findViewById(R.id.exReps);
            weight = itemView.findViewById(R.id.exWeight);
        }
    }


}
