package ma.ensa;

import java.time.LocalDateTime;
import java.util.Date;

public class Position {
    private int id;
    private double latitude;
    private double longitude;
    private String imei;
    private LocalDateTime date;

    public Position(){

    }

    public Position(int id, double latitude, double longitude, String imei, LocalDateTime date) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.imei = imei;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}


