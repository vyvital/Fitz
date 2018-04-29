package vyvital.fitz.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.ArrayList;
import java.util.List;

import vyvital.fitz.R;
import vyvital.fitz.data.models.Exercises;
import vyvital.fitz.data.models.Sets;

public class UserExerciseAdapter extends ExpandableRecyclerAdapter<TitleParentViewHolder,TitleChildViewHolder> {

    LayoutInflater inflater;
    private List<Exercises> exercises;
    private Context mContext;
    private int photo;
    private Spinner sets;

    public UserExerciseAdapter(Context context, List<ParentObject> exercises) {
        super(context, exercises);
        inflater = LayoutInflater.from(context);
        this.mContext = context;
    }


//        @Override
//        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View itemView = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.exercisecard, parent, false);
//
//            return new MyViewHolder(itemView);
//        }
//
//        @Override
//        public void onBindViewHolder(MyViewHolder holder, int position) {
//            final Exercises exercise = exercises.get(position);
//            holder.name.setText(exercise.getName());
//            holder.equipment.setText(exercise.getEquip());
//            //Glide.with(mContext).load(photo).into(holder.img);
//            List<String> equip = new ArrayList<>();
//            for (int i = 0;i<10;i++){
//                equip.add(i+" Sets");
//            }
//            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(mContext,
//                    android.R.layout.simple_spinner_item, equip);
//            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            sets.setAdapter(dataAdapter);
//            sets.setSelection(exercise.getSets().size());
//        }

    @Override
    public TitleParentViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        View itemView = inflater.inflate(R.layout.exercisecard, viewGroup, false);
        return new TitleParentViewHolder(itemView);
    }

    @Override
    public TitleChildViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        View itemView = inflater.inflate(R.layout.setcard, viewGroup, false);
        return new TitleChildViewHolder(itemView);
    }

    @Override
    public void onBindParentViewHolder(TitleParentViewHolder titleParentViewHolder, int i, Object o) {
         final Exercises exercise = (Exercises) o;
        titleParentViewHolder.name.setText(exercise.getName());
        titleParentViewHolder.equipment.setText(exercise.getEquip());

        //Glide.with(mContext).load(photo).into(holder.img);
        List<String> equip = new ArrayList<>();
        for (int z = 0; z < 10; z++) {
            equip.add(z + " Sets");
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(mContext,
                android.R.layout.simple_spinner_item, equip);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        titleParentViewHolder.sets.setAdapter(dataAdapter);
        titleParentViewHolder.sets.setSelection(exercise.getSets().size());
    }

    @Override
    public void onBindChildViewHolder(TitleChildViewHolder titleChildViewHolder, int i, Object o) {
        final Sets sets = (Sets)o;
        titleChildViewHolder.weight.setText(String.valueOf(sets.getWeight()));
        titleChildViewHolder.reps.setText(String.valueOf(sets.getReps()));
    }

//    @Override
//    public int getItemCount() {
//        return exercises.size();
//    }


}
