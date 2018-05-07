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

import vyvital.fitz.R;
import vyvital.fitz.data.ParentChildAdapter;
import vyvital.fitz.data.models.Days;

public class ExerciseFragment extends Fragment {
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
        ParentChildAdapter adapter = new ParentChildAdapter(getActivity(), d.getExercises());
        exRV.setAdapter(adapter);
        Animation bottomUp = AnimationUtils.loadAnimation(getContext(), R.anim.bottom_up);
        exRV.startAnimation(bottomUp);

    }


}
