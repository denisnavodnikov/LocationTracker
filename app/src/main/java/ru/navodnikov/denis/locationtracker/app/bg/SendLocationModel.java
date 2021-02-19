package ru.navodnikov.denis.locationtracker.app.bg;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ru.navodnikov.denis.locationtracker.models.location.AppLocation;
import ru.navodnikov.denis.locationtracker.models.repo.TrackerRepo;
import ru.navodnikov.denis.locationtracker.models.repo.network.Network;
import ru.navodnikov.denis.locationtracker.models.sharedpref.SharedPref;

public class SendLocationModel implements SendTrackerContract.LocationModel {

    private final CompositeDisposable disposables = new CompositeDisposable();
    private final TrackerRepo repo;
    private final Network network;
    private final AppLocation appLocation;
    private final SharedPref sharedPref;

    public SendLocationModel(TrackerRepo repo, Network network, AppLocation appLocation, SharedPref sharedPref) {
        this.repo = repo;
        this.network = network;
        this.appLocation = appLocation;
        this.sharedPref = sharedPref;
    }

    @Override
    public void sendLocationStart() {
        disposables.addAll(appLocation.getLocation()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userLocation -> {
                    repo.getDao().saveLocation(userLocation);
                    network.sendLocation(userLocation);
                }



        ));


        }
    @Override
    public void sendLocationStop() {

    }


}
