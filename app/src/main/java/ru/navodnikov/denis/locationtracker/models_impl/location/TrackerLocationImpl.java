package ru.navodnikov.denis.locationtracker.models_impl.location;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Looper;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;


import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import ru.navodnikov.denis.locationtracker.models.location.TrackerLocation;
import ru.navodnikov.denis.locationtracker.models_impl.repo.dao.schemas.UserLocation;

public class TrackerLocationImpl implements TrackerLocation {
    private final FusedLocationProviderClient fusedLocationClient;
    private LocationRequest locationRequest;

    private final Context context;
    private long UPDATE_INTERVAL_IN_MILLISECONDS = 5000;

    @Inject
    public TrackerLocationImpl(Context context) {
        this.context = context;
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
    }

    @SuppressLint("MissingPermission")
    @Override
    public Observable<UserLocation> getLocation() {
        createLocationRequest();
        return Observable.create(emitter -> fusedLocationClient.requestLocationUpdates(locationRequest, new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        UserLocation location = new UserLocation(
                                locationResult.getLastLocation().getLatitude(),
                                locationResult.getLastLocation().getLongitude(),
                                System.currentTimeMillis());
                        Log.d("TAG", "onLocationResult: Lat is: " + locationResult.getLastLocation().getLatitude() + ", Lng is: " +
                                locationResult.getLastLocation().getLongitude());
                        emitter.onNext(location);
                    }

                    @Override
                    public void onLocationAvailability(LocationAvailability locationAvailability) {
                        if (!locationAvailability.isLocationAvailable()) {
                            fusedLocationClient.removeLocationUpdates(this);
                            emitter.onError(new IllegalStateException("Location not available"));
                        }
                    }
                }, Looper.myLooper())

        );
    }

    @Override
    public void createLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }



}
