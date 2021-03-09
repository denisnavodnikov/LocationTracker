package ru.navodnikov.denis.locationtracker.models.repo.storage;

public interface UserStorage {
    void putUserId(String id);
    String getUserId();
}
