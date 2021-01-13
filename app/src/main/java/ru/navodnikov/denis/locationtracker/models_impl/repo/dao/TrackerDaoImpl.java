package ru.navodnikov.denis.locationtracker.models_impl.repo.dao;

import android.content.Context;

import androidx.room.Room;

import ru.navodnikov.denis.locationtracker.models.repo.TrackerRoomDao;
import ru.navodnikov.denis.locationtracker.models.repo.dao.TrackerDao;
import ru.navodnikov.denis.locationtracker.models_impl.repo.TrackerDatabase;

public class TrackerDaoImpl implements TrackerDao {
        private final TrackerRoomDao trackerRoomDao;
    private static final String DB_NAME = "tracker.db";

    public TrackerDaoImpl(Context ctx) {
        trackerRoomDao = Room.databaseBuilder(ctx, TrackerDatabase.class, DB_NAME).build().trackerDao();
    }
}
