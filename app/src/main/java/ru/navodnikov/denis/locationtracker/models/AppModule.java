package ru.navodnikov.denis.locationtracker.models;

import android.content.Context;

import com.google.android.gms.location.FusedLocationProviderClient;

import ru.navodnikov.denis.locationtracker.models.cache.Cache;
import ru.navodnikov.denis.locationtracker.models.repo.dao.TrackerDao;
import ru.navodnikov.denis.locationtracker.models.repo.TrackerRepo;

public interface AppModule {
    Context getApp();

    TrackerDao getTrackerDao();

    TrackerRepo getTrackerRepo();

    Cache getCache();

    FusedLocationProviderClient getLocationClient();
}
