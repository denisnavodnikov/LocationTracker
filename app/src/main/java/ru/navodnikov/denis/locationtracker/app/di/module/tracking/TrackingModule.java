package ru.navodnikov.denis.locationtracker.app.di.module.tracking;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.navodnikov.denis.locationtracker.app.bg.SendTrackerContract;
import ru.navodnikov.denis.locationtracker.app.bg.TrackerLocationSender;
import ru.navodnikov.denis.locationtracker.app.di.scope.PerFragment;
import ru.navodnikov.denis.locationtracker.models.location.TrackerLocation;
import ru.navodnikov.denis.locationtracker.models.repo.TrackerRepository;
import ru.navodnikov.denis.locationtracker.models.repo.network.TrackerNetwork;
import ru.navodnikov.denis.locationtracker.models.storage.UserStorage;
import ru.navodnikov.denis.locationtracker.models_impl.location.TrackerLocationImpl;
import ru.navodnikov.denis.locationtracker.models_impl.repo.TrackerRepositoryImpl;
import ru.navodnikov.denis.locationtracker.models_impl.repo.dao.TrackerDatabase;

@Module
public class TrackingModule {
    @Provides
    @PerFragment
    static TrackerLocation getTrackerLocation(Application application) {
        return new TrackerLocationImpl(application);
    }

    @Provides
    @PerFragment
    static SendTrackerContract.LocationSender getTrackerLocationSender(TrackerRepository repo, TrackerNetwork trackerNetwork, TrackerLocation trackerLocation, UserStorage userStorage) {
        return new TrackerLocationSender(repo,  trackerNetwork, trackerLocation, userStorage);
    }


}
