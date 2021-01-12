package ru.navodnikov.denis.locationtracker.models_impl.repo.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;

import ru.navodnikov.denis.locationtracker.models_impl.repo.dao.schemas.LocationSchema;

@Dao
public interface TrackerDao {
    @Query("SELECT * FROM locations ORDER BY time")
    ArrayList<LocationSchema> getAllLocations();

    @Insert
    void insertLocation(LocationSchema locationSchema);
}
