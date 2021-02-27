package ru.navodnikov.denis.locationtracker.models.repo.network;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.database.FirebaseDatabase;

import io.reactivex.rxjava3.core.Single;
import ru.navodnikov.denis.locationtracker.models_impl.repo.dao.schemas.UserLocation;

public interface TrackerNetwork {


    FirebaseAuth getmAuth();

    FirebaseDatabase getDb();

    Single<String> loginWithEmail(String username, String password);

    Single<String> verifyWithPhoneNumber(String userEmail);

    Single<String> verificationWithSMS(String smsCode);

    Single<String> signInWithPhoneAuthCredential(PhoneAuthCredential credential);

    void sendLocation(UserLocation location);

    Single<String> registerWithEmail(String email, String password);
}
