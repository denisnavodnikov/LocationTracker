package ru.navodnikov.denis.locationtracker.models.location;

import io.reactivex.rxjava3.core.Observable;
import ru.navodnikov.denis.locationtracker.models_impl.repo.dao.schemas.UserLocation;

public interface AppLocation {
    Observable<UserLocation> getLocation();

    void createLocationRequest();

}
