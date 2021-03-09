package ru.navodnikov.denis.locationtracker.models.repo.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import java.util.List;

import ru.navodnikov.denis.locationtracker.app.utils.Constants;
import ru.navodnikov.denis.locationtracker.models_impl.repo.dao.schemas.UserLocation;

@Dao
public interface TrackerRoomDao {


    @Query(" SELECT * FROM "+ Constants.TABLE_NAME +" ORDER BY id LIMIT 100 ")
    List<UserLocation> getScopeLocations();

    @Query(" DELETE FROM " + Constants.TABLE_NAME +" WHERE id IN ( SELECT id FROM "+ Constants.TABLE_NAME +" ORDER BY id LIMIT 100 ) ")
    void deleteScope();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLocation(UserLocation userLocation);

    @Query(" SELECT COUNT(*) FROM " + Constants.TABLE_NAME)
    int countRows();

}
