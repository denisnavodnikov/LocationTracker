package ru.navodnikov.denis.locationtracker.models.repo;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import ru.navodnikov.denis.locationtracker.models_impl.repo.dao.schemas.UserLocation;

public interface TrackerRepository {

    void insert(UserLocation location);
    void deleteAll();
    List<UserLocation> getAllLocations();

    Single<String> loginWithEmailRepo(String userEmail, String password);

    Completable verifyWithPhoneNumberRepo(String userPhone);

    Single<String> registerWithEmailRepo(String userEmail, String password);

    Single<String> verificationWithSMSRepo(String smsCode);

    void signOutRepo();
}
