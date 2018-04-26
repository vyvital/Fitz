package vyvital.fitz.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Days implements Parcelable {

    private List<Exercise2> exercises;
    private String name;

    public Days() {
    }


    public Days(List<Exercise2> ex, String name) {
        this.exercises = ex;
        this.name = name;
    }

    protected Days(Parcel in) {
        exercises = in.createTypedArrayList(Exercise2.CREATOR);
        name = in.readString();
    }

    public static final Creator<Days> CREATOR = new Creator<Days>() {
        @Override
        public Days createFromParcel(Parcel in) {
            return new Days(in);
        }

        @Override
        public Days[] newArray(int size) {
            return new Days[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExercises(List<Exercise2> exercises) {
        this.exercises = exercises;
    }

    public List<Exercise2> getExercises() {
        return exercises;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(exercises);
        dest.writeString(name);
    }
}
