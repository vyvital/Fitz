package vyvital.fitz;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import vyvital.fitz.data.models.Days;
import vyvital.fitz.data.models.Workout;
import vyvital.fitz.fragments.ExerciseFragment;


public class ExerciseActivity extends BaseActivity {



    private static Workout ex;
    FloatingActionButton fab;
    FragmentPagerItemAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
       // w = getIntent().getParcelableExtra("workout");
//        Log.d("name", w.getDays().get(0).getExercises().get(0).getName());
       // Log.d("name", w.getName());
//        Log.d("name", c.getType());
        //Log.d("name", w.getDays().get(0).getExercises().get(0).getSets().get(0).getReps()+"");
        //Log.d("name", w.getDays().get(0).getName());

        Bundle b = new Bundle();
        b = getIntent().getExtras();
        ex = b.getParcelable("workout");
       // Workout w = b.getParcelable("workout");
//        Log.d("name", ex.getName());
//        Log.d("name", ex.getType());
//        Log.d("name", ex.getLevel());
//        Log.d("name", ex.getDays()+"");
//        Log.d("name", ex.getId()+"");
//        Log.d("name", ex.getSize()+"");
//        Log.d("name", c.getType());
         fab = findViewById(R.id.fab);
        final ViewPager viewPager = findViewById(R.id.pager);
        SmartTabLayout viewPagerTab = findViewById(R.id.viewpagertab);
        FragmentPagerItems pages = new FragmentPagerItems(this);
        String[] exList = tab10();
        for (int i = 0 ; i<exList.length;i++) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("w",ex.getDays().get(i));
            pages.add(FragmentPagerItem.of(exList[i], ExerciseFragment.class, bundle));
        }

         adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), pages);


        viewPager.setAdapter(adapter);
        viewPagerTab.setViewPager(viewPager);
        for (int i = 0; i < pages.size(); i++)
            viewPagerTab.getTabAt(i).setBackgroundDrawable(null);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_SETTLING:
                        displayFloatingActionButtonIfNeeded(viewPager.getCurrentItem());
                        break;

                    case ViewPager.SCROLL_STATE_IDLE:
                        displayFloatingActionButtonIfNeeded(viewPager.getCurrentItem());
                        break;

                    default:
                        fab.hide();
                }
            }
        });
    }
    private void displayFloatingActionButtonIfNeeded(final int position) {
        if (adapter.getItem(position) instanceof ExerciseFragment) {

            final ExerciseFragment floatingActionButtonFragment = (ExerciseFragment) adapter.getItem(position);

            fab.show();
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ExerciseActivity.this, "ok", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public static String[] tab10() {
        String[] dayNames = new String[ex.getSize()];
        for (int i = 0; i<ex.getSize();i++)
            dayNames[i]=ex.getDays().get(i).getName();

       return dayNames;
    }


}
