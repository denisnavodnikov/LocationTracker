package ru.navodnikov.denis.locationtracker.models.storage;

public interface UserStorage {
    void putUserId(String id);
    String getUserId();
}
