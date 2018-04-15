package vyvital.fitz.data.models;

import java.sql.Timestamp;
import java.util.Date;

public class Weight extends Model {

    public Timestamp time;
    public int weight;


    public Weight() {
    }

    public Weight(Timestamp time, int weight) {

        this.time = time;
        this.weight = weight;
    }
    public Timestamp getTimestamp() {
        return time;
    }
    public void setWeight(int weight) {
        Date date = new Date();
        this.time = new Timestamp(date.getTime());
        this.weight = weight;
    }
    public int getWeight() {
        return weight;
    }
}
