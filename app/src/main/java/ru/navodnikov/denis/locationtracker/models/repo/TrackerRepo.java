package ru.navodnikov.denis.locationtracker.models.repo;

import ru.navodnikov.denis.locationtracker.models.repo.dao.TrackerDao;

public interface TrackerRepo {

    TrackerDao getDao();
}
