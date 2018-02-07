package vyvital.fitz;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class FragmentA extends Fragment {
    public static final String TAG = FragmentA.class.getSimpleName();

    public FragmentA() {
        // Required empty public constructor
    }

    public static FragmentA newInstance() {
        return new FragmentA();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_a, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ImageButton abs_view = view.findViewById(R.id.abs);
        final ImageButton chest_view = view.findViewById(R.id.chest);
        final ImageButton back_view = view.findViewById(R.id.back);
        final ImageButton tricep_view = view.findViewById(R.id.tricep);
        final ImageButton bicep_view = view.findViewById(R.id.bicep);
        final ImageButton legs_view = view.findViewById(R.id.legs);
        final ImageButton cardio_view = view.findViewById(R.id.cardio);
        final ImageButton crossfit_view = view.findViewById(R.id.crossfit);
        final ImageButton shoulder_view = view.findViewById(R.id.shoulder);

        abs_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("PIC",R.drawable.abs_t);
                bundle.putString("TRANS",abs_view.getTransitionName());
                FragmentB simpleFragmentB = FragmentB.newInstance();
                simpleFragmentB.setArguments(bundle);
                getFragmentManager()
                        .beginTransaction()
                        .addSharedElement(abs_view, ViewCompat.getTransitionName(abs_view))
                        .addToBackStack(TAG)
                        .replace(R.id.content, simpleFragmentB)
                        .commit();
            }
        });

        chest_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("PIC",R.drawable.chest_t);
                bundle.putString("TRANS",chest_view.getTransitionName());
                FragmentB simpleFragmentB = FragmentB.newInstance();
                simpleFragmentB.setArguments(bundle);
                getFragmentManager()
                        .beginTransaction()
                        .addSharedElement(chest_view, ViewCompat.getTransitionName(chest_view))
                        .addToBackStack(TAG)
                        .replace(R.id.content, simpleFragmentB)
                        .commit();
            }
        });
        back_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("PIC",R.drawable.back_t);
                bundle.putString("TRANS",back_view.getTransitionName());
                FragmentB simpleFragmentB = FragmentB.newInstance();
                simpleFragmentB.setArguments(bundle);
                getFragmentManager()
                        .beginTransaction()
                        .addSharedElement(back_view, ViewCompat.getTransitionName(back_view))
                        .addToBackStack(TAG)
                        .replace(R.id.content, simpleFragmentB)
                        .commit();
            }
        });
        tricep_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("PIC",R.drawable.tricep_t);
                bundle.putString("TRANS",tricep_view.getTransitionName());
                FragmentB simpleFragmentB = FragmentB.newInstance();
                simpleFragmentB.setArguments(bundle);
                getFragmentManager()
                        .beginTransaction()
                        .addSharedElement(tricep_view, ViewCompat.getTransitionName(tricep_view))
                        .addToBackStack(TAG)
                        .replace(R.id.content, simpleFragmentB)
                        .commit();
            }
        });
        bicep_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("PIC",R.drawable.bicep_t);
                bundle.putString("TRANS",bicep_view.getTransitionName());
                FragmentB simpleFragmentB = FragmentB.newInstance();
                simpleFragmentB.setArguments(bundle);
                getFragmentManager()
                        .beginTransaction()
                        .addSharedElement(bicep_view, ViewCompat.getTransitionName(bicep_view))
                        .addToBackStack(TAG)
                        .replace(R.id.content, simpleFragmentB)
                        .commit();
            }
        });
        legs_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("PIC",R.drawable.legs_t);
                bundle.putString("TRANS",legs_view.getTransitionName());
                FragmentB simpleFragmentB = FragmentB.newInstance();
                simpleFragmentB.setArguments(bundle);
                getFragmentManager()
                        .beginTransaction()
                        .addSharedElement(legs_view, ViewCompat.getTransitionName(legs_view))
                        .addToBackStack(TAG)
                        .replace(R.id.content, simpleFragmentB)
                        .commit();
            }
        });
        cardio_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("PIC",R.drawable.cardio_t);
                bundle.putString("TRANS",cardio_view.getTransitionName());
                FragmentB simpleFragmentB = FragmentB.newInstance();
                simpleFragmentB.setArguments(bundle);
                getFragmentManager()
                        .beginTransaction()
                        .addSharedElement(cardio_view, ViewCompat.getTransitionName(cardio_view))
                        .addToBackStack(TAG)
                        .replace(R.id.content, simpleFragmentB)
                        .commit();
            }
        });
        crossfit_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("PIC",R.drawable.crossfit_t);
                bundle.putString("TRANS",crossfit_view.getTransitionName());
                FragmentB simpleFragmentB = FragmentB.newInstance();
                simpleFragmentB.setArguments(bundle);
                getFragmentManager()
                        .beginTransaction()
                        .addSharedElement(crossfit_view, ViewCompat.getTransitionName(crossfit_view))
                        .addToBackStack(TAG)
                        .replace(R.id.content, simpleFragmentB)
                        .commit();
            }
        });
        shoulder_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("PIC",R.drawable.shoulder_t);
                bundle.putString("TRANS",shoulder_view.getTransitionName());
                FragmentB simpleFragmentB = FragmentB.newInstance();
                simpleFragmentB.setArguments(bundle);
                getFragmentManager()
                        .beginTransaction()
                        .addSharedElement(shoulder_view, ViewCompat.getTransitionName(shoulder_view))
                        .addToBackStack(TAG)
                        .replace(R.id.content, simpleFragmentB)
                        .commit();
            }
        });



    }
}