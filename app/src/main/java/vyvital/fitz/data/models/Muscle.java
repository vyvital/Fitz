package vyvital.fitz.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;


public class Muscle implements Parcelable {

    private String name;
    private List<Exercise> exercises;
    private int id;

    public Muscle() {
    }

    public Muscle(int id, String name, List<Exercise> exercises) {
        this.id = id;
        this.name = name;
        this.exercises = exercises;
    }


    protected Muscle(Parcel in) {
        name = in.readString();
        exercises = in.createTypedArrayList(Exercise.CREATOR);
        id = in.readInt();
        //exercises = new ArrayList<Exercise>();
        //in.readList(exercises,Exercise.class.getClassLoader());
    }

    public static final Creator<Muscle> CREATOR = new Creator<Muscle>() {
        @Override
        public Muscle createFromParcel(Parcel in) {
            return new Muscle(in);
        }

        @Override
        public Muscle[] newArray(int size) {
            return new Muscle[size];
        }
    };

    public String getName() {
        return name;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public int getId() {
        return id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(id);
        dest.writeList(exercises);
    }
}