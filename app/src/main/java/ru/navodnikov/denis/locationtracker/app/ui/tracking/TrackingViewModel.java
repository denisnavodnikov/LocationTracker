package ru.navodnikov.denis.locationtracker.app.ui.tracking;


import ru.navodnikov.denis.locationtracker.app.ui.tracking.infra.TrackingScreenState;
import ru.navodnikov.denis.locationtracker.models.location.AppLocation;
import ru.navodnikov.denis.locationtracker.models.repo.TrackerRepo;
import ru.navodnikov.denis.locationtracker.models.repo.network.Network;
import ru.navodnikov.denis.locationtracker.models.sharedpref.SharedPref;
import ru.navodnikov.denis.locationtracker.mvi.MviViewModel;


public class TrackingViewModel extends MviViewModel<TrackingScreenState> implements TrackingContract.ViewModel{
    private final TrackerRepo repo;
    private final Network network;
    private final AppLocation appLocation;
    private final SharedPref sharedPref;

    public TrackingViewModel(TrackerRepo repo, Network network, AppLocation appLocation, SharedPref sharedPref) {
        this.repo = repo;
        this.network = network;
        this.appLocation = appLocation;
        this.sharedPref = sharedPref;
    }
//    TODO добавить методы с логикой работы

    @Override
    public void logOut() {
        network.getmAuth().signOut();
        postState(TrackingScreenState.createLogoutState());
    }

    @Override
    public void startTracking() {
        postState(TrackingScreenState.createStartTrackingState());
//        appLocation.getLocation();
//        repo.getDao().saveLocation();
//        network.sendLocation();

    }
    @Override
    public void stopTracking() {
        postState(TrackingScreenState.createStopTrackingState());
    }


}
