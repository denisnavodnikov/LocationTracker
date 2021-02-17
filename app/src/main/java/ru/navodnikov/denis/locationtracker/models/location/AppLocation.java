package ru.navodnikov.denis.locationtracker.models.location;

import android.location.Location;

import io.reactivex.rxjava3.core.Single;

public interface AppLocation {
    Single<Location> getLocation();
}
