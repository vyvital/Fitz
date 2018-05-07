package vyvital.fitz.fragments;

import android.app.AlertDialog;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wang.avi.AVLoadingIndicatorView;
import com.xw.repo.BubbleSeekBar;
import belka.us.androidtoggleswitch.widgets.ToggleSwitch;
import io.ghyeok.stickyswitch.widget.StickySwitch;
import vyvital.fitz.R;


public class FragANutri extends Fragment{
    public static final String TAG = FragANutri.class.getSimpleName();
    int tdee = 15;
    int bmr = 0;
    double weightz;
    public FragANutri() {
        // Required empty public constructor
    }

    public static FragANutri newInstance() {
        return new FragANutri();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nutri_a, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final TextView ageT = view.findViewById(R.id.edit_age);
        final TextView heightT = view.findViewById(R.id.edit_height);
        final TextView weightT = view.findViewById(R.id.edit_weight);
        final StickySwitch gender = view.findViewById(R.id.sticky_switch_gender);
        final StickySwitch measure = view.findViewById(R.id.sticky_switch_measure);
        final ToggleSwitch act = view.findViewById(R.id.activity_switch);
        final ToggleSwitch act2 = view.findViewById(R.id.activity_switch2);
        final BubbleSeekBar seek = view.findViewById(R.id.seekDays);
        final Button calc = view.findViewById(R.id.calculate);
        final Button reset = view.findViewById(R.id.reset);
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                View mView = getLayoutInflater().inflate(R.layout.load,null);
                AVLoadingIndicatorView avi = mView.findViewById(R.id.avi);
                mBuilder.setView(mView);
                avi.show();
                final AlertDialog dialog = mBuilder.create();
                if ( ageT.getText().toString().trim().length()==0 || ageT.getText().toString().equals("0") ||
                        weightT.getText().toString().trim().length()==0 || weightT.getText().toString().equals("0")  ||
                        heightT.getText().toString().trim().length()==0 || heightT.getText().toString().equals("0") || (weightT.getText().toString().length()>0 && weightT.getText().toString().substring(0,1).equals(".") ) )
                    Toast.makeText(getActivity(), "Please Make Sure Your Input is Correct", Toast.LENGTH_SHORT).show();
                else {
                    dialog.show();
                if (gender.getDirection().name().equals("LEFT")) {
                    if (measure.getDirection().name().equals("LEFT")){
                        bmr = BMRcalcKG(Integer.parseInt(ageT.getText().toString()),Integer.parseInt(heightT.getText().toString()),Double.parseDouble(weightT.getText().toString()),false);
                        weightz = Double.parseDouble(weightT.getText().toString());
                    } else if (measure.getDirection().name().equals("RIGHT")){
                        bmr = BMRcalcLB(Integer.parseInt(ageT.getText().toString()),Integer.parseInt(heightT.getText().toString()),Integer.parseInt(weightT.getText().toString()),false);
                        weightz = Integer.parseInt(weightT.getText().toString())/2.2;
                    }
                } else if (gender.getDirection().name().equals("RIGHT")) {
                    if (measure.getDirection().name().equals("LEFT")) {
                        bmr = BMRcalcKG(Integer.parseInt(ageT.getText().toString()), Integer.parseInt(heightT.getText().toString()), Double.parseDouble(weightT.getText().toString()), true);
                        weightz = Double.parseDouble(weightT.getText().toString());
                    } else if (measure.getDirection().name().equals("RIGHT")) {
                        bmr = BMRcalcLB(Integer.parseInt(ageT.getText().toString()), Integer.parseInt(heightT.getText().toString()), Integer.parseInt(weightT.getText().toString()), true);
                        weightz = Integer.parseInt(weightT.getText().toString())/2.2;
                    }
                }
                int activ = act.getCheckedTogglePosition();
                int intense = act2.getCheckedTogglePosition();
                int seeki = seek.getProgress();
                tdee = tdeeTest(bmr,activ,intense,seeki);

                new CountDownTimer(3000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                    }
                    @Override
                    public void onFinish() {
                        dialog.dismiss();
                        Bundle bundle = new Bundle();
                        bundle.putDouble("WEIGHT",weightz);
                        bundle.putInt("TDEE",tdee);
                        FragBNutri simpleFragmentB = FragBNutri.newInstance();
                        simpleFragmentB.setArguments(bundle);
                        getFragmentManager()
                                .beginTransaction()
                                .addToBackStack(TAG)
                                .replace(R.id.content, simpleFragmentB)
                                .commit();
                    }
                }.start();
            }}
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ageT.setText("");
                heightT.setText("");
                weightT.setText("");
                gender.setDirection(StickySwitch.Direction.LEFT);
                measure.setDirection(StickySwitch.Direction.LEFT);
                act.setCheckedTogglePosition(0);
                act2.setCheckedTogglePosition(0);
                seek.setProgress(0);
            }
        });
    }

    private int tdeeTest(int bmr, int activ, int intense, int seeki) {
        double active2,intense2,seeki2;
        if (activ==0) active2 = 1.2;
        else if (activ==1) active2 = 1.3;
        else active2 = 1.75;
        if (intense==0) intense2 = active2+0.05;
        else if (intense==1) intense2 = active2+0.1;
        else intense2 = active2+0.15;
        seeki2 = seeki*0.01 + intense2;

        return ((int) (bmr * seeki2));
    }

    public int BMRcalcKG(int age, int height, double weight, boolean gender){
        if (gender){
            return (int)((10*weight)+(6.25*height)-(5*age)+5);
        } else {
            return (int)((10*weight)+(6.25*height)-(5*age)-161);
        }
    }
    public int BMRcalcLB(int age, int height, int weight, boolean gender){
        if (gender){
            return (int)((10*weight/2.2)+(6.25*height*2.54)-(5*age)+5);
        } else {
            return (int)((10*weight/2.2)+(6.25*height*2.54)-(5*age)-161);
        }
    }





}
