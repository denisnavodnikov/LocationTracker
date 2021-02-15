package ru.navodnikov.denis.locationtracker.models_impl.location;


import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;


import io.reactivex.rxjava3.core.Single;
import ru.navodnikov.denis.locationtracker.models.location.AppLocation;
import ru.navodnikov.denis.locationtracker.models.location.infra.Result;

public class TrackerLocation implements AppLocation {
    private final FusedLocationProviderClient fusedLocationClient;
    private final Context context;

    public TrackerLocation(Context context) {
        this.context = context;
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
    }

    @SuppressLint("MissingPermission")
    @Override
    public Single<Result<Location>> getLocation() {
        return Single.create(emitter -> fusedLocationClient.getLastLocation()
                .addOnSuccessListener( new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                    emitter.onSuccess(new Result<>(location));

                    }
                }));
    }

}
