package ru.navodnikov.denis.locationtracker.app.bg;

import javax.inject.Inject;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ru.navodnikov.denis.locationtracker.models.location.TrackerLocation;
import ru.navodnikov.denis.locationtracker.models.repo.TrackerRepository;

public class TrackerLocationSender implements SendTrackerContract.LocationSender {

    private final TrackerRepository trackerRepository;
    private final TrackerLocation trackerLocation;
    private final CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    public TrackerLocationSender(TrackerRepository trackerRepository, TrackerLocation trackerLocation) {
        this.trackerRepository = trackerRepository;
        this.trackerLocation = trackerLocation;
    }

    @Override
    public void sendLocationStart() {
        disposables.addAll(trackerLocation.getLocation()
                .subscribeOn(Schedulers.io())
                .flatMapCompletable(location -> trackerRepository.saveLocation(location))
                .subscribe());

    }



    @Override
    public void sendLocationsToServer() {
        disposables.addAll(trackerRepository.sendLocationsToServerRepo()
                .subscribe()
        );
    }

    @Override
    public void sendLocationStop() {
        disposables.clear();
    }
}
