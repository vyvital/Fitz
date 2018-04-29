package vyvital.fitz.data;

import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;

import vyvital.fitz.R;

public class TitleParentViewHolder  extends ParentViewHolder{
    public TextView name, equipment;
    public ImageView img;
    public Spinner sets;

    public TitleParentViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.exName);
        equipment = itemView.findViewById(R.id.exEquip);
        img = itemView.findViewById(R.id.exPhoto);
        sets = itemView.findViewById(R.id.setSpinner);
    }
}
