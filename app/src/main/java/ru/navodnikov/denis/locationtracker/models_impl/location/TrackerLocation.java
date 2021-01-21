package ru.navodnikov.denis.locationtracker.models_impl.location;

import android.content.Context;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import ru.navodnikov.denis.locationtracker.models.location.Location;

public class TrackerLocation implements Location {
    private final FusedLocationProviderClient fusedLocationClient;

    public TrackerLocation(Context app) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(app);
    }
}
