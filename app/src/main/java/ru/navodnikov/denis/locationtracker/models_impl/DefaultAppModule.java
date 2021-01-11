package ru.navodnikov.denis.locationtracker.models_impl;

import android.content.Context;

import ru.navodnikov.denis.locationtracker.models.AppModule;
import ru.navodnikov.denis.locationtracker.models.cache.Cache;
import ru.navodnikov.denis.locationtracker.models_impl.cache.CacheImpl;

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

//    public Network getTrackerNetwork() {
//        return new TrackerNetwork();
//    }

    public TrackerDao getTrackerDao() {
        return new TrackerDaoImpl(app);
    }

    public TrackerRepo getTrackerRepo() {
        return new TrackerRepository(getTrackerDao(), getTrackerNetwork());
    }

    public Cache getCache() {
        return cache;
    }
}
