package vyvital.fitz.data.models;


import android.os.Parcel;
import android.os.Parcelable;

public class Sets implements Parcelable {
    public int reps;
    public int weight;

    public Sets() {
    }

    public Sets(int reps, int weight) {
        this.reps = reps;
        this.weight = weight;
    }

    protected Sets(Parcel in) {
        reps = in.readInt();
        weight = in.readInt();
    }

    public static final Creator<Sets> CREATOR = new Creator<Sets>() {
        @Override
        public Sets createFromParcel(Parcel in) {
            return new Sets(in);
        }

        @Override
        public Sets[] newArray(int size) {
            return new Sets[size];
        }
    };

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getReps() {
        return reps;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(reps);
        dest.writeInt(weight);
    }
}