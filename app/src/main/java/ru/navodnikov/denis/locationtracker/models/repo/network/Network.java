package ru.navodnikov.denis.locationtracker.models.repo.network;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public interface Network {

    String getIdToken();

    FirebaseAuth getmAuth();

    FirebaseFirestore getDb();

    Context getContext();

    void loginWithEmail(String username, String password);

    void verifyWithPhoneNumber(String userEmail, String password);

    void verificationWithSMS(String smsCode);

    void getUserToken(FirebaseUser user);

    void startTracking();
}
