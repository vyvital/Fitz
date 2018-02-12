package vyvital.fitz.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import vyvital.fitz.R;


public class FragBMuscle extends Fragment {

    int pic;
    String trans;

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
        if (getArguments()!=null){
            pic = getArguments().getInt("PIC");
            trans = getArguments().getString("TRANS");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_muscle_b, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView image = getView().findViewById(R.id.fragment_b_image);
        image.setImageResource(pic);
        image.setTransitionName(trans);

    }
}