package ru.navodnikov.denis.locationtracker.models_impl.repo.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import ru.navodnikov.denis.locationtracker.models_impl.repo.dao.schemas.LocationSchema;
import ru.navodnikov.denis.locationtracker.models_impl.repo.dao.schemas.UserSchema;

@Dao
public abstract class TrackerRoomDao {
    @Query("SELECT * FROM locations ORDER BY time")
    abstract List<LocationSchema> getAllLocations();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void insertLocation(LocationSchema locationSchema);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void insertUser(UserSchema userSchema);
}
