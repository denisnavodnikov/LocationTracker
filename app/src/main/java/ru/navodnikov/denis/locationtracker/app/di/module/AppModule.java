package ru.navodnikov.denis.locationtracker.app.di.module;

import android.app.Application;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.navodnikov.denis.locationtracker.app.utils.Constants;
import ru.navodnikov.denis.locationtracker.models.ActivityHolder;
import ru.navodnikov.denis.locationtracker.models.repo.TrackerRepository;
import ru.navodnikov.denis.locationtracker.models.repo.network.TrackerNetwork;
import ru.navodnikov.denis.locationtracker.models.repo.storage.UserStorage;
import ru.navodnikov.denis.locationtracker.models_impl.ActivityHolderImp;
import ru.navodnikov.denis.locationtracker.models_impl.repo.TrackerRepositoryImpl;
import ru.navodnikov.denis.locationtracker.models_impl.repo.dao.TrackerDatabase;
import ru.navodnikov.denis.locationtracker.models_impl.repo.network.TrackerNetworkImpl;
import ru.navodnikov.denis.locationtracker.models_impl.repo.storage.LocalStorageImpl;

@Module
public class AppModule {


    @Provides
    @Singleton
    static TrackerDatabase getDatabase(Application application) {
        return Room.databaseBuilder(application, TrackerDatabase.class, Constants.DB_NAME).build();
    }

    @Provides
    @Singleton
    static ActivityHolder getActivityHolderImp() {
        return new ActivityHolderImp();
    }

    @Provides
    @Singleton
    static TrackerNetwork getTrackerNetwork(ActivityHolder activityHolder) {
        return new TrackerNetworkImpl(activityHolder);
    }

    @Provides
    @Singleton
    static UserStorage getTrackerSharedPref(Application application) {
        return new LocalStorageImpl(application);
    }

    @Provides
    @Singleton
    public TrackerRepository getTrackerRepo(TrackerDatabase db, TrackerNetwork trackerNetwork, UserStorage userStorage) {
        return new TrackerRepositoryImpl(db, trackerNetwork, userStorage);
    }



}
