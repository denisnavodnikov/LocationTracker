package ru.navodnikov.denis.locationtracker.models_impl.repo.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import java.util.List;

import ru.navodnikov.denis.locationtracker.models_impl.repo.dao.schemas.UserLocation;

@Dao
public abstract class TrackerRoomDao {


    @Query("SELECT * FROM "+ TrackerDaoImpl.TABLE_NAME +"  ORDER BY time ")
    abstract List<UserLocation> getAllLocations();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void insertLocation(UserLocation userLocation);

}
