package ru.navodnikov.denis.locationtracker.models.repo.network;

import com.google.firebase.auth.PhoneAuthCredential;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import ru.navodnikov.denis.locationtracker.models_impl.repo.dao.schemas.UserLocation;

public interface TrackerNetwork {

    Single<String> loginWithEmail(String username, String password);

    Completable verifyWithPhoneNumber(String userEmail);

    Single<String> verificationWithSMS(String smsCode);

    Single<String> signInWithPhoneAuthCredential(PhoneAuthCredential credential);

    void sendLocation(UserLocation location);

    Single<String> registerWithEmail(String email, String password);

    void signOut();

    boolean userIsNotNull();
}
