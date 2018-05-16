package vyvital.fitz.data.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Equipment implements Parcelable {

    private String name;

    public Equipment() {
    }

    public Equipment(String name) {
        this.name = name;
    }

    protected Equipment(Parcel in) {
        name = in.readString();
    }

    public static final Creator<Equipment> CREATOR = new Creator<Equipment>() {
        @Override
        public Equipment createFromParcel(Parcel in) {
            return new Equipment(in);
        }

        @Override
        public Equipment[] newArray(int size) {
            return new Equipment[size];
        }
    };

    public String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }
}