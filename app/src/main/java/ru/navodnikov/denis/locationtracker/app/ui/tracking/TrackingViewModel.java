package ru.navodnikov.denis.locationtracker.app.ui.tracking;

import ru.navodnikov.denis.locationtracker.app.ui.tracking.infra.TrackingScreenState;
import ru.navodnikov.denis.locationtracker.models.location.Location;
import ru.navodnikov.denis.locationtracker.models.repo.TrackerRepo;
import ru.navodnikov.denis.locationtracker.models.repo.network.Network;
import ru.navodnikov.denis.locationtracker.mvi.MviViewModel;

public class TrackingViewModel extends MviViewModel<TrackingScreenState> implements TrackingContract.ViewModel{
    private final TrackerRepo repo;
    private final Network network;
    private final Location location;

    public TrackingViewModel(TrackerRepo repo, Network network, Location location) {
        this.repo = repo;
        this.network = network;
        this.location = location;
    }
//    TODO добавить методы с логикой работы

    @Override
    public void logOut() {
        network.getmAuth().signOut();
        postState(TrackingScreenState.createLogoutState());
    }

    @Override
    public void startTracking() {


    }
    @Override
    public void stopTracking() {

    }


}
