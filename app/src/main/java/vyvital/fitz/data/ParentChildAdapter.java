package vyvital.fitz.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;

import java.util.List;

import vyvital.fitz.R;
import vyvital.fitz.data.models.Exercises;
import vyvital.fitz.data.models.Sets;

public class ParentChildAdapter extends ExpandableRecyclerAdapter<Exercises, Sets, ExercisesViewHolder, SetsViewHolder> {
    private LayoutInflater mInflater;
   private Context context;
    private List<Sets> s;

    public ParentChildAdapter(Context context, @NonNull List<Exercises> parentList) {
        super(parentList);
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public ExercisesViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View exerciseView = mInflater.inflate(R.layout.exercisecard, parentViewGroup, false);
        return new ExercisesViewHolder(exerciseView);
    }

    @NonNull
    @Override
    public SetsViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        View setsView = mInflater.inflate(R.layout.setcard, childViewGroup, false);
        return new SetsViewHolder(setsView);
    }

    @Override
    public void onBindParentViewHolder(@NonNull ExercisesViewHolder parentViewHolder, final int parentPosition, @NonNull final Exercises parents) {
        parentViewHolder.bind(parents,context);
        parentViewHolder.sets.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s = parents.getSets();
                if (position > parents.getSets().size()) {
                    Log.d("book", position + "");
                    Log.d("book2", parents.getSets().size() + "");
                    for (int t = position - parents.getSets().size(); t > 0; t--) {
                        s.add(new Sets());
                    }
                    parents.setSets(s);
                    //Testing that the set is actually added and updated in the exercise object
                    for (int vv = 0; vv < parents.getSets().size(); vv++) {
                        Log.d("book", parents.getSets().get(vv).getWeight() + "");
                        Log.d("book", parents.getSets().get(vv).getReps() + "");
                    }
                    notifyChildInserted(parentPosition,position);
                    s.clear();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onBindChildViewHolder(@NonNull SetsViewHolder childViewHolder, int parentPosition, int childPosition, @NonNull Sets child) {
        childViewHolder.bind(child);

    }
}
