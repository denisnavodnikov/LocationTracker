package ru.navodnikov.denis.locationtracker.models_impl.repo.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ru.navodnikov.denis.locationtracker.models_impl.repo.dao.schemas.Locations;
import ru.navodnikov.denis.locationtracker.models_impl.repo.dao.schemas.User;

@Database(entities = {User.class, Locations.class}, version = 1, exportSchema = false)
public abstract class TrackerDatabase extends RoomDatabase {

    public abstract TrackerRoomDao trackerDao();
}
