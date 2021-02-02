package ru.navodnikov.denis.locationtracker.models;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class User {
    String userEmail;
    String password;
    List<LatLng> listOfCoordinates;

    public User(String userEmail, String password) {
        this.userEmail = userEmail;
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getPassword() {
        return password;
    }

    public List<LatLng> getListOfCoordinates() {
        return listOfCoordinates;
    }
}
