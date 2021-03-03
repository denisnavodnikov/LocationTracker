package ru.navodnikov.denis.locationtracker.models_impl.repo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ru.navodnikov.denis.locationtracker.models.repo.TrackerRepository;
import ru.navodnikov.denis.locationtracker.models.repo.network.TrackerNetwork;
import ru.navodnikov.denis.locationtracker.models.storage.UserStorage;
import ru.navodnikov.denis.locationtracker.models_impl.repo.dao.TrackerDatabase;
import ru.navodnikov.denis.locationtracker.models.repo.dao.TrackerRoomDao;
import ru.navodnikov.denis.locationtracker.models_impl.repo.dao.schemas.UserLocation;

public class TrackerRepositoryImpl implements TrackerRepository {
    private final TrackerRoomDao dao;
    private final TrackerNetwork trackerNetwork;
    private final UserStorage userStorage;

    @Inject
    public TrackerRepositoryImpl(TrackerDatabase db, TrackerNetwork trackerNetwork, UserStorage userStorage) {
        dao = db.trackerDao();
        this.trackerNetwork = trackerNetwork;
        this.userStorage = userStorage;
    }

    @Override
    public Single<String> loginWithEmailRepo(String userEmail, String password) {
        return trackerNetwork.loginWithEmail(userEmail, password)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Completable verifyWithPhoneNumberRepo(String userPhone) {
        return trackerNetwork.verifyWithPhoneNumber(userPhone)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Single<String> registerWithEmailRepo(String userEmail, String password) {
        return trackerNetwork.registerWithEmail(userEmail, password)
                .doOnSuccess(result -> userStorage.putUserId(result))
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Single<String> verificationWithSMSRepo(String smsCode) {
        return trackerNetwork.verificationWithSMS(smsCode)
                .doOnSuccess(result -> userStorage.putUserId(result))
                .subscribeOn(Schedulers.io());
    }

    @Override
    public void signOutRepo() {
        trackerNetwork.signOut();
    }


    @Override
    public void insert(UserLocation location) {
        dao.insertLocation(location);
    }

    @Override
    public void deleteAll() {
        dao.deleteAll();
    }

    @Override
    public List<UserLocation> getAllLocations() {
        return dao.getAllLocations();
    }


}
