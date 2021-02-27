package ru.navodnikov.denis.locationtracker.app.bg;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ru.navodnikov.denis.locationtracker.app.TrackerApp;
import ru.navodnikov.denis.locationtracker.models.location.TrackerLocation;
import ru.navodnikov.denis.locationtracker.models.repo.TrackerRepository;
import ru.navodnikov.denis.locationtracker.models.repo.network.TrackerNetwork;
import ru.navodnikov.denis.locationtracker.models.storage.UserStorage;

public class TrackerLocationSender implements SendTrackerContract.LocationSender {


    private final TrackerRepository repo;

    private final TrackerNetwork trackerNetwork;

    private final TrackerLocation trackerLocation;

    private final UserStorage userStorage;

    private final CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    public TrackerLocationSender(TrackerRepository repo, TrackerNetwork trackerNetwork, TrackerLocation trackerLocation, UserStorage userStorage) {
        this.repo = repo;
        this.trackerNetwork = trackerNetwork;
        this.trackerLocation = trackerLocation;
        this.userStorage = userStorage;
    }

    @Override
    public void sendLocationStart() {
        disposables.addAll(trackerLocation.getLocation()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userLocation -> {
                    repo.insert(userLocation);
                    trackerNetwork.sendLocation(userLocation);
                }
        ));


        }
    @Override
    public void sendLocationStop() {
        disposables.clear();
    }


}
