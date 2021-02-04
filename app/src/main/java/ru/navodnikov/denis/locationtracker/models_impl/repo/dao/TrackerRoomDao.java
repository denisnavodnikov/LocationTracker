package ru.navodnikov.denis.locationtracker.models_impl.repo.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import ru.navodnikov.denis.locationtracker.models_impl.repo.dao.schemas.Locations;
import ru.navodnikov.denis.locationtracker.models_impl.repo.dao.schemas.User;

@Dao
public abstract class TrackerRoomDao {
    @Query("SELECT * FROM locations ORDER BY time")
    abstract List<Locations> getAllLocations();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void insertLocation(Locations locations);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void insertUser(User user);
    @Query("SELECT*FROM users")
    abstract User getUser();
}
