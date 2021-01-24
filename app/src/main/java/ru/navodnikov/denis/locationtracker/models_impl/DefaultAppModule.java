package ru.navodnikov.denis.locationtracker.models_impl;

import android.content.Context;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import ru.navodnikov.denis.locationtracker.models.AppModule;
import ru.navodnikov.denis.locationtracker.models.cache.Cache;
import ru.navodnikov.denis.locationtracker.models.location.Location;
import ru.navodnikov.denis.locationtracker.models.repo.dao.TrackerDao;
import ru.navodnikov.denis.locationtracker.models.repo.TrackerRepo;
import ru.navodnikov.denis.locationtracker.models.repo.network.Network;
import ru.navodnikov.denis.locationtracker.models_impl.cache.CacheImpl;
import ru.navodnikov.denis.locationtracker.models_impl.location.TrackerLocation;
import ru.navodnikov.denis.locationtracker.models_impl.repo.TrackerRepository;
import ru.navodnikov.denis.locationtracker.models_impl.repo.dao.TrackerDaoImpl;
import ru.navodnikov.denis.locationtracker.models_impl.repo.network.TrackerNetwork;

public class DefaultAppModule implements AppModule {
    private final Context app;
    private final Cache cache;

    public DefaultAppModule(Context app) {
        this.app = app;
        cache = new CacheImpl();
    }

    @Override
    public Context getApp() {
        return app;
    }

    @Override
    public TrackerDao getTrackerDao() {
        return new TrackerDaoImpl(app);
    }

    @Override
    public TrackerRepo getTrackerRepo() {
        return new TrackerRepository(getTrackerDao());
    }

    @Override
    public Cache getCache() {
        return cache;
    }

    @Override
    public Network getTrackerNetwork() {
        return new TrackerNetwork(app);
    }

    @Override
    public Location getTrackerLocation() {
        return new TrackerLocation(app);
    }
}
