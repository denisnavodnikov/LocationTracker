package ru.navodnikov.denis.locationtracker.models_impl.repo.network;

import android.content.Context;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import ru.navodnikov.denis.locationtracker.models.repo.network.Network;

public class TrackerNetwork implements Network {
    private final FirebaseAuth mAuth;
    private final FirebaseFirestore db;

    public TrackerNetwork() {
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }
}
