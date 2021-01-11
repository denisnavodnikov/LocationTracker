package ru.navodnikov.denis.locationtracker.models;

import android.content.Context;

import ru.navodnikov.denis.locationtracker.models.cache.Cache;

public interface AppModule {
    Context getApp();

//    Network getTrackerNetwork();

    TrackerDao getTrackerDao();

    TrackerRepo getTrackerRepo();

    Cache getCache();
}
