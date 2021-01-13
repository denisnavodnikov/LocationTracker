package ru.navodnikov.denis.locationtracker.models_impl.repo;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ru.navodnikov.denis.locationtracker.models.repo.TrackerRoomDao;
import ru.navodnikov.denis.locationtracker.models_impl.repo.dao.schemas.LocationSchema;
import ru.navodnikov.denis.locationtracker.models_impl.repo.dao.schemas.UserSchema;

@Database(entities = {UserSchema.class, LocationSchema.class}, version = 1, exportSchema = false)
public abstract class TrackerDatabase extends RoomDatabase {
//
//    public static TrackerDatabase trackerDatabase;
    public abstract TrackerRoomDao trackerDao();

//    public static TrackerDatabase getInstance(Context ctx){
//        if(trackerDatabase == null){
//            trackerDatabase = Room.databaseBuilder(ctx, TrackerDatabase.class, DB_NAME).build();
//        }
//        return trackerDatabase;
//    }
}
