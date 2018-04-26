package vyvital.fitz.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Weight implements Parcelable {
    private String time;
    private float weight;
    private URL imageUrl;

    public Weight() {
    }
    public Weight(String time, float weight, URL imageUrl) {

        this.time = time;
        this.weight = weight;
        if (imageUrl != null)
            this.imageUrl = imageUrl;
    }

    protected Weight(Parcel in) {
        time = in.readString();
        weight = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(time);
        dest.writeFloat(weight);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Weight> CREATOR = new Creator<Weight>() {
        @Override
        public Weight createFromParcel(Parcel in) {
            return new Weight(in);
        }

        @Override
        public Weight[] newArray(int size) {
            return new Weight[size];
        }
    };

    public void setTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy (HH:mm)");
        this.time = sdf.format(new Date());
    }

    public String getTime() {
        return time;
    }
    public void setWeight(float weight) {
        this.weight = weight;
    }
    public URL getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(URL imageUrl) {
        this.imageUrl = imageUrl;
    }
    public float getWeight() {
        return weight;
    }
}
