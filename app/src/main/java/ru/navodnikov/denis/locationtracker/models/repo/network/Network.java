package ru.navodnikov.denis.locationtracker.models.repo.network;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public interface Network {

    FirebaseAuth getmAuth();

    FirebaseFirestore getDb();

    Context getContext();

    void login(String username, String password);

    void register(String userEmail, String password);

    void startTracking();
}
