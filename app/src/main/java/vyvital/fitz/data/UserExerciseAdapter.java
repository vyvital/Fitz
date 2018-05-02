package vyvital.fitz.data;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;

import java.util.ArrayList;
import java.util.List;

import vyvital.fitz.R;
import vyvital.fitz.data.models.Exercises;
import vyvital.fitz.data.models.Sets;

public class UserExerciseAdapter extends ExpandableRecyclerAdapter<UserExerciseAdapter.TitleParentViewHolder, UserExerciseAdapter.TitleChildViewHolder> {

    LayoutInflater inflater;
    List<Exercises> ex;
    private Context mContext;
    private int photo;
    private List<Sets> s;
    private Spinner sets;


    public UserExerciseAdapter(Context context, List<ParentObject> exercises, List<Exercises> ex) {
        super(context, exercises);
        inflater = LayoutInflater.from(context);
        this.mContext = context;
        this.ex = ex;

    }


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
    public void onBindParentViewHolder(TitleParentViewHolder titleParentViewHolder, final int i, Object o) {
        final Exercises exercise = (Exercises) o;

        titleParentViewHolder.name.setText(exercise.getName());
        titleParentViewHolder.equipment.setText(exercise.getEquip());

        //Glide.with(mContext).load(photo).into(holder.img);
        List<String> equip = new ArrayList<>();
        for (int z = 0; z < 10; z++) {
            equip.add(z + " Sets");
        }
        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(mContext,
                android.R.layout.simple_spinner_item, equip);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        titleParentViewHolder.sets.setAdapter(dataAdapter);
        titleParentViewHolder.sets.setSelection(exercise.getSets().size());
        titleParentViewHolder.sets.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s = exercise.getSets();
                if (position > exercise.getSets().size()) {
                    Log.d("book", position + "");
                    Log.d("book2", exercise.getSets().size() + "");
                    for (int t = position - exercise.getSets().size(); t > 0; t--) {
                        s.add(new Sets());
                    }
                    exercise.setSets(s);
                    ex.get(i).setSets(s);
                    //Testing that the set is actually added and updated in the exercise object
                    for (int vv = 0; vv < exercise.getSets().size(); vv++) {
                        Log.d("book", exercise.getSets().get(vv).getWeight() + "");
                        Log.d("book", exercise.getSets().get(vv).getReps() + "");
                    }
                    dataAdapter.notifyDataSetChanged();
                    notifyDataSetChanged();



                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }

    @Override
    public void onBindChildViewHolder(TitleChildViewHolder titleChildViewHolder, int i, Object o) {
        final Sets sets = (Sets) o;
        titleChildViewHolder.weight.setText(String.valueOf(sets.getWeight()));
        titleChildViewHolder.reps.setText(String.valueOf(sets.getReps()));
    }

//    @Override
//    public int getItemCount() {
//        return exercises.size();
//    }
public class TitleChildViewHolder extends ChildViewHolder {
    public EditText reps, weight;

    public TitleChildViewHolder(View itemView) {
        super(itemView);
        reps = itemView.findViewById(R.id.exReps);
        weight = itemView.findViewById(R.id.exWeight);
    }
}

    public class TitleParentViewHolder  extends ParentViewHolder{
        public TextView name, equipment;
        public ImageView img;
        public Spinner sets;

        public TitleParentViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.exName);
            equipment = itemView.findViewById(R.id.exEquip);
            img = itemView.findViewById(R.id.exPhoto);
            sets = itemView.findViewById(R.id.setSpinner);


        }

    }

}
