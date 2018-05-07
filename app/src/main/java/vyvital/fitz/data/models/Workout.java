package vyvital.fitz.data.models;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Workout implements Parcelable {

    private String name;
    private String type;
    private String level;
    private int size;
    private List<Days> days;
    private int id;
    private boolean def;

    // This is required for serialization
    public Workout() {
    }

    public Workout(List<Days> d,String name, String type, String level, int size, int id, boolean def) {
        this.days = d;
        this.name = name;
        this.type = type;
        this.level = level;
        this.size = size;
        this.id = id;
        this.def = def;
    }

    protected Workout(Parcel in) {
        id = in.readInt();
        level = in.readString();
        name = in.readString();
        size = in.readInt();
        type = in.readString();
        def = in.readByte() != 0;
        days = in.createTypedArrayList(Days.CREATOR);
    }

    public static final Creator<Workout> CREATOR = new Creator<Workout>() {
        @Override
        public Workout createFromParcel(Parcel in) {
            return new Workout(in);
        }

        @Override
        public Workout[] newArray(int size) {
            return new Workout[size];
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

    public void setType(String type) {
        this.type = type;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setSize(int days) {
        this.size = days;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean isDef() {
        return def;
    }

    public void setDef(boolean def) {
        this.def = def;
    }

    public String getLevel() {
        return level;
    }

    public int getSize() {
        return size;
    }

    public void setDays(List<Days> days) {
        this.days = days;
    }

    public List<Days> getDays() {
        return days;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(level);
        dest.writeString(name);
        dest.writeInt(size);
        dest.writeString(type);
        dest.writeByte((byte)(def ? 1 : 0));
        dest.writeTypedList(days);
    }
}