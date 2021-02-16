package ru.navodnikov.denis.locationtracker.models.repo.network;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.database.FirebaseDatabase;

import io.reactivex.rxjava3.core.Single;
import ru.navodnikov.denis.locationtracker.models.location.infra.Result;
import ru.navodnikov.denis.locationtracker.models_impl.repo.dao.schemas.UserLocation;

public interface Network {


    void getResultVerification(FirebaseUser user);

    void getResultVerification(Throwable error);

    FirebaseAuth getmAuth();

    FirebaseDatabase getDb();

    Context getContext();

    Single<Result<String>> loginWithEmail(String username, String password);

    Single<Result<String>> verifyWithPhoneNumber(String userEmail);

    Single<Result<String>> verificationWithSMS(String smsCode);

    Single<Result<String>> signInWithPhoneAuthCredential(PhoneAuthCredential credential);

    void sendLocation(UserLocation location);

    Single<Result<String>> registerWithEmail(String email, String password);
}
