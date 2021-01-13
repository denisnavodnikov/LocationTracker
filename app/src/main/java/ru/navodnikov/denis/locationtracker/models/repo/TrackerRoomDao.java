package ru.navodnikov.denis.locationtracker.models.repo;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;

import ru.navodnikov.denis.locationtracker.models_impl.repo.dao.schemas.LocationSchema;

@Dao
public abstract class TrackerRoomDao {
    @Query("SELECT * FROM locations ORDER BY time")
    abstract ArrayList<LocationSchema> getAllLocations();

    @Insert
    abstract void insertLocation(LocationSchema locationSchema);
}
