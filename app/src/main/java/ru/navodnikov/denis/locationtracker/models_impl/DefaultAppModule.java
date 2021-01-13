package ru.navodnikov.denis.locationtracker.models_impl;

import android.content.Context;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import ru.navodnikov.denis.locationtracker.models.AppModule;
import ru.navodnikov.denis.locationtracker.models.cache.Cache;
import ru.navodnikov.denis.locationtracker.models.repo.dao.TrackerDao;
import ru.navodnikov.denis.locationtracker.models_impl.repo.TrackerRepo;
import ru.navodnikov.denis.locationtracker.models_impl.cache.CacheImpl;
import ru.navodnikov.denis.locationtracker.models_impl.repo.TrackerRepository;
import ru.navodnikov.denis.locationtracker.models.repo.TrackerRoomDao;
import ru.navodnikov.denis.locationtracker.models_impl.repo.dao.TrackerDaoImpl;

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


    public TrackerDao getTrackerDao() {
        return new TrackerDaoImpl(app);
    }

    public TrackerRepo getTrackerRepo() {
        return new TrackerRepository(getTrackerDao(), getLocationClient());
    }

    public FusedLocationProviderClient getLocationClient(){
        return LocationServices.getFusedLocationProviderClient(app);
    }

    public Cache getCache() {
        return cache;
    }
}
