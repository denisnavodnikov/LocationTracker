package ru.navodnikov.denis.locationtracker.models.repo.dao;


import ru.navodnikov.denis.locationtracker.models_impl.repo.dao.schemas.UserLocation;

public interface TrackerDao {
    void saveLocation(UserLocation location);
}
