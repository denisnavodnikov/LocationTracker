package ru.navodnikov.denis.locationtracker.models_impl.repo;

import com.google.android.gms.location.FusedLocationProviderClient;

import ru.navodnikov.denis.locationtracker.models.repo.TrackerRepo;
import ru.navodnikov.denis.locationtracker.models.repo.dao.TrackerDao;

public class TrackerRepository implements TrackerRepo {
        private final TrackerDao dao;
        private final FusedLocationProviderClient client;

    public TrackerRepository(TrackerDao dao, FusedLocationProviderClient client) {
        this.dao = dao;
        this.client = client;
    }
}
