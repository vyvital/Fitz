package vyvital.fitz.data.models;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Weight extends Model {
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
