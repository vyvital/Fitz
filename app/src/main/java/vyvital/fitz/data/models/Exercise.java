package vyvital.fitz.data.models;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Exercise implements Parcelable {


    private int id;
    private String name;
    private String mechanics;
    private List<Equipment> equip;

    public Exercise() {
    }

    public Exercise(int id, String name, String mechanics, List<Equipment> equip) {
        this.id = id;
        this.name = name;
        this.mechanics = mechanics;
        this.equip = equip;
    }


    protected Exercise(Parcel in) {
        id = in.readInt();
        name = in.readString();
        mechanics = in.readString();
        equip = in.createTypedArrayList(Equipment.CREATOR);
    }

    public static final Creator<Exercise> CREATOR = new Creator<Exercise>() {
        @Override
        public Exercise createFromParcel(Parcel in) {
            return new Exercise(in);
        }

        @Override
        public Exercise[] newArray(int size) {
            return new Exercise[size];
        }
    };

    public int getId() {
        return id;
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMechanics(String mechanics) {
        this.mechanics = mechanics;
    }

    public void setEquip(List<Equipment> equip) {
        this.equip = equip;
    }

    public String getName() {
        return name;
    }

    public String getMechanics() {
        return mechanics;
    }

    public List<Equipment> getEquip() {
        return equip;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(mechanics);
        dest.writeList(equip);
    }

}
