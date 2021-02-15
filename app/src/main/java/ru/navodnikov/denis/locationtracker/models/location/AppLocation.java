package ru.navodnikov.denis.locationtracker.models.location;

import android.location.Location;

import io.reactivex.rxjava3.core.Single;
import ru.navodnikov.denis.locationtracker.models.location.infra.Result;

public interface AppLocation {
    Single<Result<Location>> getLocation();
}
