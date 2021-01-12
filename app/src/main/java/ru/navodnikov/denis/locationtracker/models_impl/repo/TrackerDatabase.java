package ru.navodnikov.denis.locationtracker.models_impl.repo;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ru.navodnikov.denis.locationtracker.models_impl.repo.dao.TrackerDao;
import ru.navodnikov.denis.locationtracker.models_impl.repo.dao.schemas.LocationSchema;
import ru.navodnikov.denis.locationtracker.models_impl.repo.dao.schemas.UserSchema;

@Database(entities = {UserSchema.class, LocationSchema.class}, version = 1, exportSchema = false)
public abstract class TrackerDatabase extends RoomDatabase {
    private static final String DB_NAME = "tracker.db";
    public static TrackerDatabase trackerDatabase;
    public abstract TrackerDao trackerDao();

    public static TrackerDatabase getInstance(Context ctx){
        if(trackerDatabase == null){
            trackerDatabase = Room.databaseBuilder(ctx, TrackerDatabase.class, DB_NAME).build();
        }
        return trackerDatabase;
    }
}
