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
    private boolean isPermissionChecked;

    public TrackingViewModel(TrackerRepo repo, Network network, AppLocation appLocation, SharedPref sharedPref) {
        this.repo = repo;
        this.network = network;
        this.appLocation = appLocation;
        this.sharedPref = sharedPref;
    }

    @Override
    public void setPermissionChecked(boolean permissionChecked) {
        isPermissionChecked = permissionChecked;
    }

    @Override
    public void logOut() {
        network.getmAuth().signOut();
        postState(TrackingScreenState.createLogoutState());
    }

    @Override
    public void startTracking() {
        if(isPermissionChecked){
            postState(TrackingScreenState.createStartTrackingState());
        }else{
            postState(TrackingScreenState.createCheckPermissionState());
        }


//        appLocation.getLocation();
//        repo.getDao().saveLocation();
//        network.sendLocation();

    }
    @Override
    public void stopTracking() {
        postState(TrackingScreenState.createStopTrackingState());
    }


}
