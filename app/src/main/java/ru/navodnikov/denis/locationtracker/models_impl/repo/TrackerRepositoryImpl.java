package ru.navodnikov.denis.locationtracker.models_impl.repo;

import java.util.List;

import javax.inject.Inject;

import ru.navodnikov.denis.locationtracker.models.repo.TrackerRepository;
import ru.navodnikov.denis.locationtracker.models_impl.repo.dao.TrackerDatabase;
import ru.navodnikov.denis.locationtracker.models.repo.dao.TrackerRoomDao;
import ru.navodnikov.denis.locationtracker.models_impl.repo.dao.schemas.UserLocation;

public class TrackerRepositoryImpl implements TrackerRepository {
        private final TrackerRoomDao dao;

    @Inject
    public TrackerRepositoryImpl(TrackerDatabase db) {
        dao = db.trackerDao();
    }


    @Override
    public void insert(UserLocation location) {
        dao.insertLocation(location);
    }

    @Override
    public void deleteAll() {
        dao.deleteAll();
    }

    @Override
    public List<UserLocation> getAllLocations() {
        return dao.getAllLocations();
    }
}
