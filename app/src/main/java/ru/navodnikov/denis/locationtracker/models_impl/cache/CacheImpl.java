package ru.navodnikov.denis.locationtracker.models_impl.cache;

import ru.navodnikov.denis.locationtracker.models.cache.Cache;

public class CacheImpl implements Cache {
    private String userEmail;
    private String userPhone;
    private String password;
    private int action;

    @Override
    public int getAction() {
        return action;
    }

    @Override
    public void setAction(int action) {
        this.action = action;
    }

    @Override
    public String getUserEmail() {
        return userEmail;
    }

    @Override
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String getUserPhone() {
        return userPhone;
    }

    @Override
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }
}
