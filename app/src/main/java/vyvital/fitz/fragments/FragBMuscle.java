package vyvital.fitz.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import vyvital.fitz.MuscleActivity;
import vyvital.fitz.R;
import vyvital.fitz.data.ExerciseAdapter;
import vyvital.fitz.data.models.Days;
import vyvital.fitz.data.models.Muscle;

public class FragBMuscle extends Fragment {

    private int pic;
    private String trans;
    private int img;
    private Muscle muscle;
    Days day;
    public MuscleActivity muscleActivity;

    public FragBMuscle() {
        // Required empty public constructor
    }

    public static FragBMuscle newInstance() {
        return new FragBMuscle();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move));
        }
        if (getArguments() != null) {
            pic = getArguments().getInt("PIC");
            trans = getArguments().getString("TRANS");
            muscle = getArguments().getParcelable("MUSCLE");
            img = getArguments().getInt("PHOTO");
        }
        muscleActivity = (MuscleActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_muscle_b, container, false);
        RecyclerView exerciseRV = view.findViewById(R.id.exerciseRV);
        exerciseRV.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        exerciseRV.setLayoutManager(mLayoutManager);
        exerciseRV.setAdapter(new ExerciseAdapter(getActivity(), muscle.getExercises(), img, muscleActivity));
        Animation bottomUp = AnimationUtils.loadAnimation(getContext(), R.anim.bottom_up);
        exerciseRV.startAnimation(bottomUp);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView image = getView().findViewById(R.id.fragment_b_image);
        image.setImageResource(pic);
        image.setTransitionName(trans);
    }
}