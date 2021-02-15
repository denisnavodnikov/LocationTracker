package ru.navodnikov.denis.locationtracker.models_impl.repo.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.google.firebase.firestore.auth.User;

import ru.navodnikov.denis.locationtracker.models_impl.repo.dao.schemas.UserLocation;

@Database(entities = {UserLocation.class}, version = 1, exportSchema = false)
public abstract class TrackerDatabase extends RoomDatabase {

    public abstract TrackerRoomDao trackerDao();
}
