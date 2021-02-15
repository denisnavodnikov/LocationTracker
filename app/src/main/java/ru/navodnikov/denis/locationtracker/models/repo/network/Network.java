package ru.navodnikov.denis.locationtracker.models.repo.network;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import io.reactivex.rxjava3.core.Single;
import ru.navodnikov.denis.locationtracker.models.location.infra.Result;
import ru.navodnikov.denis.locationtracker.models_impl.repo.dao.schemas.UserLocation;

public interface Network {


    FirebaseAuth getmAuth();

    FirebaseDatabase getDb();

    Context getContext();

    Single<Result<String>> loginWithEmail(String username, String password);

    Single<Object> verifyWithPhoneNumber(String userEmail);

    Single<Result<String>> verificationWithSMS(String smsCode);

    void sendLocation(UserLocation location);

    Single<Result<String>> registerWithEmail(String email, String password);
}
