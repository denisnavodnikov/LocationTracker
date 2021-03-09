package ru.navodnikov.denis.locationtracker.models_impl.repo;

import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ru.navodnikov.denis.locationtracker.models.repo.TrackerRepository;
import ru.navodnikov.denis.locationtracker.models.repo.dao.TrackerRoomDao;
import ru.navodnikov.denis.locationtracker.models.repo.network.TrackerNetwork;
import ru.navodnikov.denis.locationtracker.models.repo.storage.UserStorage;
import ru.navodnikov.denis.locationtracker.models_impl.repo.dao.TrackerDatabase;
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
    public Completable saveLocation(UserLocation location) {
        return trackerNetwork.sendLocation(location)
                .onErrorComplete()
                .andThen(insert(location));
    }

    @Override
    public Completable sendLocationsToServerRepo() {
        return Completable.create(emitter -> {
            AtomicInteger rows = new AtomicInteger(dao.countRows());
                    try {
                        while (rows.get() != 0){
                            Observable.fromIterable(dao.getScopeLocations())
                                    .observeOn(Schedulers.io())
                                    .map(location -> trackerNetwork.sendLocation(location))
                                    .doOnComplete(() ->  dao.deleteScope())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe();
                            rows.set(dao.countRows());
                        }
                        emitter.onComplete();
                    } catch (Exception e) {
                        emitter.onError(e);
                    }
                }
        );



    }


    @Override
    public Completable insert(UserLocation location) {
        return Completable.fromAction(() ->dao.insertLocation(location));
    }



}
