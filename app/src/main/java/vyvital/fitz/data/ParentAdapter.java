package vyvital.fitz.data;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import vyvital.fitz.R;
import vyvital.fitz.data.models.Exercises;
import vyvital.fitz.data.models.Sets;

public class ParentAdapter extends RecyclerView.Adapter<ParentAdapter.ParentViewHolder> implements SimpleItemTouchHelperCallback.ItemTouchHelperAdapter {

    List<Exercises> exercisesList;
    private Context context;
    ChildAdapter adapter;
    List<Sets> s;

    public ParentAdapter(Context context, List<Exercises> exercises) {
        this.context = context;
        this.exercisesList = exercises;
    }

    @Override
    public ParentViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.exercisecard, viewGroup, false);
        return new ParentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ParentViewHolder viewHolder, int position) {
        final Exercises ex = exercisesList.get(position);

        viewHolder.name.setText(ex.getName());
        viewHolder.equipment.setText(ex.getEquip());
        List<String> equip = new ArrayList<>();
        for (int z = 0; z < 10; z++) {
            equip.add(z + " Sets");
        }

        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(context,
                android.R.layout.simple_spinner_item, equip);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        viewHolder.sets.setAdapter(dataAdapter);
        viewHolder.sets.setSelection(ex.getSets().size());
        adapter = new ChildAdapter(context, ex.getSets());

        viewHolder.rv.setAdapter(adapter);
        viewHolder.sets.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s = ex.getSets();
                if (position > s.size()) {
                    for (int t = position - s.size(); t > 0; t--) {
                        s.add(new Sets());
                    }
                } else if (position < s.size()) {
                    for (int t = 0; t < s.size() - position; t++) {
                        s.remove(s.size() - 1);
                    }
                } else if (position ==0)
                    s.clear();
                ex.setSets(s);
                viewHolder.rv.getAdapter().notifyDataSetChanged();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return exercisesList.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(exercisesList,fromPosition,toPosition);
        notifyItemMoved(fromPosition,toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        exercisesList.remove(position);
        notifyItemRemoved(position);
    }

    public final static class ParentViewHolder extends RecyclerView.ViewHolder  implements
            SimpleItemTouchHelperCallback.ItemTouchHelperViewHolder {
        RelativeLayout relativeLayout;
        public TextView name, equipment;
        public ImageView img;
        public Spinner sets;
        RecyclerView rv;

        public ParentViewHolder(final View itemView) {
            super(itemView);
            relativeLayout = itemView.findViewById(R.id.RLayoutEx);
            rv = itemView.findViewById(R.id.child);
            name = itemView.findViewById(R.id.exName);
            equipment = itemView.findViewById(R.id.exEquip);
            img = itemView.findViewById(R.id.exPhoto);
            sets = itemView.findViewById(R.id.setSpinner);
            LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.VERTICAL, false);
            rv.setHasFixedSize(false);
            rv.setLayoutManager(layoutManager);
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (rv.getVisibility() == View.GONE) {
                        Animation bottomUp = AnimationUtils.loadAnimation(itemView.getContext(), R.anim.item_animation_from_right);
                        rv.setVisibility(View.VISIBLE);
                        rv.startAnimation(bottomUp);
                    } else {
                        rv.setVisibility(View.GONE);
                    }
                }
            });
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundResource(R.color.colorPrimaryLight);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }

}
