package ru.navodnikov.denis.locationtracker.models.repo.network;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import io.reactivex.rxjava3.core.Single;
import ru.navodnikov.denis.locationtracker.mvi.ScreenState;

public interface Network {

    String getIdToken();

    FirebaseAuth getmAuth();

    FirebaseFirestore getDb();

    Context getContext();

    Single<Object> loginWithEmail(String username, String password);

    Single<Object> verifyWithPhoneNumber(String userEmail);

    Single<Object> verificationWithSMS(String smsCode);

    void getUserToken(FirebaseUser user);

    void startTracking();

    Single<Object> registerWithEmailNumber(String email, String password);
}
