package ru.navodnikov.denis.locationtracker.models.cache;

public interface Cache {

    int getAction();

    void setAction(int action);

    String getUserEmail();

    void setUserEmail(String userEmail);

    String getUserPhone();

    void setUserPhone(String userPhone);

    String getPassword();

    void setPassword(String password);
}
