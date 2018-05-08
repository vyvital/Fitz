package vyvital.fitz.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.List;

import belka.us.androidtoggleswitch.widgets.ToggleSwitch;
import vyvital.fitz.R;


public class FragBNutri extends Fragment{
    public static final String TAG = FragBNutri.class.getSimpleName();
    int tdee = 0;
    int goal = 0;
    int cals = 0;
    double wt = 0;
    int protein = 0;
    int carbs = 0;
    int fats = 0;
    public FragBNutri() {
        // Required empty public constructor
    }

    public static FragBNutri newInstance() {
        return new FragBNutri();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!=null){
            tdee = getArguments().getInt("TDEE");
            wt = getArguments().getDouble("WEIGHT");
            goal = getArguments().getInt("GOAL");
            update(tdee,wt,goal);
        }

    }

    private void update(int tdee, double wt,int goal) {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("Tdee", Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = sharedPreferences.edit();
        mEditor.putInt("TDEE",tdee);
        mEditor.putLong("WEIGHT",Double.doubleToLongBits(wt));
        mEditor.putInt("GOAL",goal);
        mEditor.apply();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nutri_b, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView td = getView().findViewById(R.id.tdee2);
        final TextView tdd = getView().findViewById(R.id.tdee);
        final TextView p = getView().findViewById(R.id.cal_protein);
        final TextView c = getView().findViewById(R.id.cal_carb);
        final TextView f = getView().findViewById(R.id.cal_fat);
        final TextView gp = getView().findViewById(R.id.gram_protein);
        final TextView gc = getView().findViewById(R.id.gram_carb);
        final TextView gf = getView().findViewById(R.id.gram_fat);
        final PieChart pie = getView().findViewById(R.id.chart);
        ToggleSwitch ts = getView().findViewById(R.id.goal_switch);
        td.setText(String.valueOf(tdee));
        ts.setCheckedTogglePosition(1);
        tdd.setText(String.valueOf(tdee));
        cals = tdee;
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry((int) (wt * 1.8 ), "Protein"));
        entries.add(new PieEntry((int) (cals - cals * 0.3 - wt*1.8*4)/4, "Carb"));
        entries.add(new PieEntry((int) (cals * 0.3/9), "Fat"));
        PieDataSet set = new PieDataSet(entries, null);
        set.setColors(getResources().getColor(R.color.bluez), getResources().getColor(R.color.greenz),getResources().getColor(R.color.red));
        set.setSliceSpace(3f);
        set.setSelectionShift(9f);
        set.setValueFormatter(new PercentFormatter());
        PieData data = new PieData(set);
        pie.getDescription().setEnabled(false);
        pie.getLegend().setEnabled(false);
        pie.setUsePercentValues(true);
        pie.setHoleColor(getResources().getColor(R.color.colorGrey));
        pie.setHoleRadius(35f);
        pie.setData(data);
        pie.spin(500,0,-360f, Easing.EasingOption.EaseInOutQuad);
        p.setText(String.valueOf(((int) (wt * 1.8 * 4))));
        f.setText(String.valueOf(((int) (cals * 0.3))));
        c.setText(String.valueOf(cals-((int) (wt * 1.8 * 4)) - ((int) (cals * 0.3))));
        gp.setText(String.valueOf(((int) (wt * 1.8 ))));
        gf.setText(String.valueOf(((int) (cals * 0.3 / 9))));
        gc.setText(String.valueOf((int)(cals-(wt * 1.8 * 4) -  (cals * 0.3))/4));
        update(tdee,wt,tdee);
        ts.setOnToggleSwitchChangeListener(new ToggleSwitch.OnToggleSwitchChangeListener(){
            @Override
            public void onToggleSwitchChangeListener(int position, boolean isChecked) {
                if (position==0) {
                    cals = tdee-250;
                    protein = ((int) (wt * 2.1 * 4));
                    fats = ((int)( wt * 9));

                }
                else if (position==2) {
                    cals = tdee+200;
                    protein = ((int) (wt * 1.8 * 4));
                    fats = ((int) (cals * 0.25));
                }
                else  {
                    cals=tdee;
                    protein = ((int) (wt * 1.8 * 4));
                    fats = ((int) (cals * 0.3));
                }
                goal = cals;
                carbs = cals - protein - fats;
                tdd.setText(String.valueOf(cals));
                p.setText(String.valueOf(protein));
                f.setText(String.valueOf(fats));
                c.setText(String.valueOf(carbs));
                gp.setText(String.valueOf(protein/4));
                gf.setText(String.valueOf(fats/9));
                gc.setText(String.valueOf(carbs/4));
                List<PieEntry> entries = new ArrayList<>();
                entries.add(new PieEntry(protein/4, "Protein"));
                entries.add(new PieEntry(carbs/4, "Carb"));
                entries.add(new PieEntry(fats/9, "Fat"));
                PieDataSet set = new PieDataSet(entries, null);
                set.setColors(getResources().getColor(R.color.bluez), getResources().getColor(R.color.greenz),getResources().getColor(R.color.red));
                set.setSliceSpace(3f);
                set.setSelectionShift(9f);
                set.setValueFormatter(new PercentFormatter());
                PieData data = new PieData(set);
                pie.getDescription().setEnabled(false);
                pie.getLegend().setEnabled(false);
                pie.setUsePercentValues(true);
                pie.setHoleColor(getResources().getColor(R.color.colorGrey));
                pie.setHoleRadius(35f);
                pie.setData(data);
                pie.notifyDataSetChanged();
                pie.invalidate();
                update(tdee,wt,goal);
            }

        });

    }


}
