package vyvital.fitz.data;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vyvital.fitz.R;
import vyvital.fitz.data.models.Weight;

public class BodyWeightAdapter extends RecyclerView.Adapter<BodyWeightAdapter.WeightViewHolder> {

    List<Weight> weightList;

    public BodyWeightAdapter(List<Weight> weights) {
        this.weightList = weights;
    }

    @Override
    public WeightViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.weightcard, parent, false);
        return new WeightViewHolder(v);
    }

    @Override
    public void onBindViewHolder(WeightViewHolder holder, int position) {
        holder.weight.setText(String.valueOf(weightList.get(position).getWeight()));
        holder.date.setText(weightList.get(position).getTime());
        holder.kg_lb.setText(R.string.kg);
    }

    @Override
    public int getItemCount() {
        return weightList.size();
    }

    public static class WeightViewHolder extends RecyclerView.ViewHolder {
        TextView weight, kg_lb, date, sign2;
        ImageView imageUrl;

        WeightViewHolder(View itemView) {
            super(itemView);
            weight = itemView.findViewById(R.id.weight);
            kg_lb = itemView.findViewById(R.id.kg_lb);
            date = itemView.findViewById(R.id.date);
//            sign2 = itemView.findViewById(R.id.sign2);
//            imageUrl = itemView.findViewById(R.id.img);
        }
    }
}