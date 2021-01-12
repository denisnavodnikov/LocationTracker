package ru.navodnikov.denis.locationtracker.models_impl.repo.dao.schemas;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "locations")
public class LocationSchema {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ForeignKey(entity = UserSchema.class, parentColumns = "id", childColumns = "userId", onDelete = CASCADE)
    private int userId;
    private double latitude;
    private double longitude;
    private long time;

    public LocationSchema(int id, int userId, double latitude, double longitude, long time) {
        this.id = id;
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
