package vyvital.fitz;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import vyvital.fitz.fragments.FragANutri;
import vyvital.fitz.fragments.FragBNutri;

public class NutriActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutri);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle(getResources().getString(R.string.nutrition));
        if (getData() == 0) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.content, FragANutri.newInstance())
                    .commit();
        } else {
            Bundle bundle = new Bundle();
            bundle.putDouble("WEIGHT", getData2());
            bundle.putInt("TDEE", getData());
            bundle.putInt("GOAL", getData3());
            FragBNutri simpleFragmentB = FragBNutri.newInstance();
            simpleFragmentB.setArguments(bundle);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, simpleFragmentB)
                    .commit();
        }
    }

    private int getData() {
        SharedPreferences sharedPreferences = getSharedPreferences("Tdee", MODE_PRIVATE);
        return sharedPreferences.getInt("TDEE", 0);
    }

    private double getData2() {
        SharedPreferences sharedPreferences = getSharedPreferences("Tdee", MODE_PRIVATE);
        return Double.valueOf(sharedPreferences.getString("WEIGHT", "0"));
    }

    private int getData3() {
        SharedPreferences sharedPreferences = getSharedPreferences("Tdee", MODE_PRIVATE);
        return sharedPreferences.getInt("GOAL", 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.reset, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reset:
                erase();
        }
        return super.onOptionsItemSelected(item);
    }

    private void erase() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("Tdee", Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = sharedPreferences.edit();
        mEditor.putInt("TDEE", 0);
        mEditor.putString("WEIGHT", "0");
        mEditor.putInt("GOAL", 0);
        mEditor.apply();
        finish();
        startActivity(getIntent());
    }
}