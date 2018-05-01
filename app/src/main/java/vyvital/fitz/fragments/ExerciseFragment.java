package vyvital.fitz.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;


import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;

import java.util.ArrayList;
import java.util.List;

import vyvital.fitz.R;
import vyvital.fitz.data.ExerciseAdapter;
import vyvital.fitz.data.UserExerciseAdapter;
import vyvital.fitz.data.models.Days;
import vyvital.fitz.data.models.Exercise;
import vyvital.fitz.data.models.Exercises;
import vyvital.fitz.data.models.Sets;

public class ExerciseFragment extends Fragment{
    private RecyclerView exRV;
    Days d;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_exercise, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        d = getArguments().getParcelable("w");
        exRV = view.findViewById(R.id.exerciseRVuser);
        exRV.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        exRV.setLayoutManager(mLayoutManager);
        UserExerciseAdapter adapter = new UserExerciseAdapter(getActivity(), init());
        adapter.setParentClickableViewAnimationDefaultDuration();
        adapter.setParentAndIconExpandOnClick(true);
        exRV.setAdapter(adapter);
        Animation bottomUp = AnimationUtils.loadAnimation(getContext(), R.anim.bottom_up);
        exRV.startAnimation(bottomUp);

    }

    private List<ParentObject> init() {
        List<ParentObject> parentObjects = new ArrayList<>();
        List<Object> child = null;
        if (d.getExercises()!=null){
        for (int p = 0; p < d.getExercises().size(); p++) {
            Exercises ex = d.getExercises().get(p);
            child = new ArrayList<>();
            for (int c = 0; c < ex.getSets().size(); c++) {

                child.add(new Sets(ex.getSets().get(c).getReps(), ex.getSets().get(c).getWeight()));
            }
            ex.setChildObjectList(child);
            parentObjects.add(ex);
        }}
        return parentObjects;
    }

}
