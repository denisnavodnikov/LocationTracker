package ru.navodnikov.denis.locationtracker.models.repo;

import java.util.List;

import ru.navodnikov.denis.locationtracker.models_impl.repo.dao.schemas.UserLocation;

public interface TrackerRepository {

    void insert(UserLocation location);
    void deleteAll();
    List<UserLocation> getAllLocations();
}
