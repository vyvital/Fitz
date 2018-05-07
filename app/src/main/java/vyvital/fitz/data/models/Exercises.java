package vyvital.fitz.data.models;

import android.os.Parcel;

import android.os.Parcelable;


import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.List;

public class Exercises implements Parcelable,Parent<Sets> {

    private List<Sets> sets;
    private List<Object> s;
    private String id;
    private String name;
    private String mechanics;
    private String equip;


    public Exercises() {
    }

    public Exercises(String id, String name, String mechanics, String equip, List<Sets> sets) {
        this.name = name;
        this.id = id;
        this.mechanics = mechanics;
        this.equip = equip;
        this.sets = sets;
    }


    protected Exercises(Parcel in) {
        name = in.readString();
        id = in.readString();
        equip = in.readString();
        mechanics = in.readString();
        sets = in.createTypedArrayList(Sets.CREATOR);
    }

    public static final Creator<Exercises> CREATOR = new Creator<Exercises>() {
        @Override
        public Exercises createFromParcel(Parcel in) {
            return new Exercises(in);
        }

        @Override
        public Exercises[] newArray(int size) {
            return new Exercises[size];
        }
    };


    public String getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMechanics() {
        return mechanics;
    }

    public String getEquip() {
        return equip;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public List<Sets> getSets() {
        return sets;
    }

    public void setSets(List<Sets> sets) {
        this.sets = sets;
    }

    @Override

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(id);
        dest.writeString(equip);
        dest.writeString(mechanics);
        dest.writeTypedList(sets);
    }

    @Override
    public List<Sets> getChildList() {
        return sets;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
