package ru.navodnikov.denis.locationtracker.models.repo.dao;

import ru.navodnikov.denis.locationtracker.models_impl.repo.dao.schemas.User;

public interface TrackerDao {
    void saveUser(User user);
}
