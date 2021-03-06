package ru.navodnikov.denis.locationtracker.app.di.module.tracking;

import android.app.Application;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import dagger.Module;
import dagger.Provides;
import ru.navodnikov.denis.locationtracker.app.bg.SendTrackerContract;
import ru.navodnikov.denis.locationtracker.app.bg.TrackerLocationSender;
import ru.navodnikov.denis.locationtracker.app.di.scope.PerFragment;
import ru.navodnikov.denis.locationtracker.models.location.TrackerLocation;
import ru.navodnikov.denis.locationtracker.models.repo.TrackerRepository;
import ru.navodnikov.denis.locationtracker.models.repo.network.TrackerNetwork;
import ru.navodnikov.denis.locationtracker.models.repo.storage.UserStorage;
import ru.navodnikov.denis.locationtracker.models_impl.location.TrackerLocationImpl;

@Module
public class TrackingModule {
    @Provides
    static TrackerLocation getTrackerLocation(Application application, FusedLocationProviderClient fusedLocationClient) {
        return new TrackerLocationImpl(application, fusedLocationClient);
    }

    @Provides
    static FusedLocationProviderClient getFusedLocationProviderClient(Application application){
        return LocationServices.getFusedLocationProviderClient(application);
    }

    @Provides
    static SendTrackerContract.LocationSender getTrackerLocationSender(TrackerRepository repo, TrackerNetwork trackerNetwork, TrackerLocation trackerLocation, UserStorage userStorage) {
        return new TrackerLocationSender(repo, trackerLocation);
    }

}
