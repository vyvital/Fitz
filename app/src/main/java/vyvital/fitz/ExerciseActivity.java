package vyvital.fitz;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Toast;
import android.widget.Toolbar;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import vyvital.fitz.data.models.Days;
import vyvital.fitz.data.models.Exercise;
import vyvital.fitz.data.models.Exercise2;
import vyvital.fitz.data.models.Workout;
import vyvital.fitz.fragments.ExerciseFragment;


public class ExerciseActivity extends BaseActivity {


    private static Exercise2 w;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        Intent intent = getIntent();
         w = intent.getParcelableExtra("workout");
        Log.d("REPS", w.getSets().get(0).getReps()+"");
        Log.d("WE", w.getSets().get(0).getWeight()+"");
        Log.d("REPS", w.getSets().get(1).getReps()+"");
        Log.d("WE", w.getSets().get(1).getWeight()+"");

        ViewPager viewPager = findViewById(R.id.pager);
        SmartTabLayout viewPagerTab = findViewById(R.id.viewpagertab);
        FragmentPagerItems pages = new FragmentPagerItems(this);

        for (String titleResId : tab10()) {
            pages.add(FragmentPagerItem.of(titleResId, ExerciseFragment.class));
        }

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), pages);


        viewPager.setAdapter(adapter);
        viewPagerTab.setViewPager(viewPager);
        for (int i = 0; i < pages.size(); i++)
            viewPagerTab.getTabAt(i).setBackgroundDrawable(null);

    }


    public static String[] tab10() {


        return new String[]{
                "one", "two"
        };
    }


}
