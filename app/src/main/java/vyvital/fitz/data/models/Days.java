package vyvital.fitz.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Days implements Parcelable {

    private List<Exercises> exercises;
    private String name;

    public Days() {
    }

    public Days(List<Exercises> ex, String name) {
        this.exercises = ex;
        this.name = name;
    }

    protected Days(Parcel in) {

        name = in.readString();
        exercises = in.createTypedArrayList(Exercises.CREATOR);
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



    public void setExercises(List<Exercises> exercises) {
        this.exercises = exercises;
    }

    public List<Exercises> getExercises() {
        return exercises;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(name);
        dest.writeTypedList(exercises);

    }
}
