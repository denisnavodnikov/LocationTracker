package ru.navodnikov.denis.locationtracker.models;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class User {
    String name;
    String password;
    List<LatLng> listOfCoordinates;
}