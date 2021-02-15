package ru.navodnikov.denis.locationtracker.models_impl.repo.dao;

import android.content.Context;

import androidx.room.Room;

import ru.navodnikov.denis.locationtracker.models.repo.dao.TrackerDao;
import ru.navodnikov.denis.locationtracker.models_impl.repo.dao.schemas.UserLocation;

public class TrackerDaoImpl implements TrackerDao {
        private final TrackerRoomDao trackerRoomDao;
    public static final String DB_NAME = "tracker.db";
    public static final String TABLE_NAME = "locations";

    public TrackerDaoImpl(Context ctx) {
        trackerRoomDao = Room.databaseBuilder(ctx, TrackerDatabase.class, DB_NAME).build().trackerDao();
    }


    @Override
    public void saveLocation(UserLocation location) {
        trackerRoomDao.insertLocation(location);
    }
}
