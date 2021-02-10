package ru.navodnikov.denis.locationtracker.app.di.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.navodnikov.denis.locationtracker.models.location.Location;
import ru.navodnikov.denis.locationtracker.models.repo.dao.TrackerDao;
import ru.navodnikov.denis.locationtracker.models.repo.TrackerRepo;
import ru.navodnikov.denis.locationtracker.models.repo.network.Network;
import ru.navodnikov.denis.locationtracker.models_impl.location.TrackerLocation;
import ru.navodnikov.denis.locationtracker.models_impl.repo.TrackerRepository;
import ru.navodnikov.denis.locationtracker.models_impl.repo.dao.TrackerDaoImpl;
import ru.navodnikov.denis.locationtracker.models_impl.repo.network.TrackerNetwork;

@Module
public class AppModule {
    private final Context app;

    public AppModule(Context app) {
        this.app = app;
    }


    @Provides
    @Singleton
    public Context getApp() {
        return app;
    }

    @Provides
    @Singleton
    public TrackerDao getTrackerDao() {
        return new TrackerDaoImpl(app);
    }

    @Provides
    @Singleton
    public TrackerRepo getTrackerRepo() {
        return new TrackerRepository(getTrackerDao());
    }


    @Provides
    @Singleton
    public Network getTrackerNetwork() {
        return new TrackerNetwork(app);
    }

    @Provides
    @Singleton
    public Location getTrackerLocation() {
        return new TrackerLocation(app);
    }
}
