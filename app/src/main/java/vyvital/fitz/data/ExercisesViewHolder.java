package vyvital.fitz.data;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ParentViewHolder;

import java.util.ArrayList;
import java.util.List;

import vyvital.fitz.R;
import vyvital.fitz.data.models.Exercises;
import vyvital.fitz.data.models.Sets;

public class ExercisesViewHolder extends ParentViewHolder {
    public TextView name, equipment;
    public ImageView img;
    public Spinner sets;


    public ExercisesViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.exName);
        equipment = itemView.findViewById(R.id.exEquip);
        img = itemView.findViewById(R.id.exPhoto);
        sets = itemView.findViewById(R.id.setSpinner);
    }

    public void bind(final Exercises exercises, Context context) {
        name.setText(exercises.getName());
        equipment.setText(exercises.getEquip());
        List<String> equip = new ArrayList<>();
        for (int z = 0; z < 10; z++) {
            equip.add(z + " Sets");
        }
        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(context,
                android.R.layout.simple_spinner_item, equip);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sets.setAdapter(dataAdapter);
        sets.setSelection(exercises.getSets().size());

    }


}
