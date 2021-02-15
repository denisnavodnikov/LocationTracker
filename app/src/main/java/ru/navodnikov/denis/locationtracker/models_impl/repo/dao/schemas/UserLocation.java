package ru.navodnikov.denis.locationtracker.models_impl.repo.dao.schemas;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import ru.navodnikov.denis.locationtracker.models_impl.repo.dao.TrackerDaoImpl;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = TrackerDaoImpl.TABLE_NAME)
public class UserLocation {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private double latitude;
    private double longitude;
    private long time;

    public UserLocation(int id, double latitude, double longitude, long time) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = time;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

}
