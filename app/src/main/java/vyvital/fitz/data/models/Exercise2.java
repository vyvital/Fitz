package vyvital.fitz.data.models;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Exercise2 implements Parcelable {


    private String id;
    private String name;
    private String mechanics;
    private String equip;
    private List<Sets> sets;

    public Exercise2() {
    }

    public Exercise2(String id, String name, String mechanics, String equip, List<Sets> sets) {
        this.id = id;
        this.name = name;
        this.mechanics = mechanics;
        this.equip = equip;
        this.sets = sets;
    }


    protected Exercise2(Parcel in) {
        id = in.readString();
        name = in.readString();
        mechanics = in.readString();
        equip = in.readString();
        sets = in.createTypedArrayList(Sets.CREATOR);
    }

    public static final Creator<Exercise2> CREATOR = new Creator<Exercise2>() {
        @Override
        public Exercise2 createFromParcel(Parcel in) {
            return new Exercise2(in);
        }

        @Override
        public Exercise2[] newArray(int size) {
            return new Exercise2[size];
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
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(mechanics);
        dest.writeString(equip);
        dest.writeList(sets);
    }

}
