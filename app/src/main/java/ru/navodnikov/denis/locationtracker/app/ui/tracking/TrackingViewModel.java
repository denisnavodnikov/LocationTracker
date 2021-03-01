package ru.navodnikov.denis.locationtracker.app.ui.tracking;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import ru.navodnikov.denis.locationtracker.app.ui.tracking.infra.TrackingScreenState;
import ru.navodnikov.denis.locationtracker.models.location.TrackerLocation;
import ru.navodnikov.denis.locationtracker.models.repo.TrackerRepository;
import ru.navodnikov.denis.locationtracker.models.repo.network.TrackerNetwork;
import ru.navodnikov.denis.locationtracker.models.storage.UserStorage;
import ru.navodnikov.denis.locationtracker.abstractions.FragmentContract;


public class TrackingViewModel extends ViewModel implements FragmentContract.ViewModel<TrackingScreenState> {
    private final TrackerRepository repo;
    private final TrackerNetwork trackerNetwork;
    private final TrackerLocation trackerLocation;
    private final UserStorage userStorage;
    private boolean isPermissionChecked;
    private final MutableLiveData<TrackingScreenState> stateHolder = new MutableLiveData<>();

    @Inject
    public TrackingViewModel(TrackerRepository repo, TrackerNetwork trackerNetwork, TrackerLocation trackerLocation, UserStorage userStorage) {
        this.repo = repo;
        this.trackerNetwork = trackerNetwork;
        this.trackerLocation = trackerLocation;
        this.userStorage = userStorage;
    }


    public void setPermissionChecked(boolean permissionChecked) {
        isPermissionChecked = permissionChecked;
    }


    public void logOut() {
        trackerNetwork.signOut();
        postState(TrackingScreenState.createLogoutState());
    }


    public void startTracking() {
        if(isPermissionChecked){
            postState(TrackingScreenState.createStartTrackingState());
        }else{
            postState(TrackingScreenState.createCheckPermissionState());
        }


    }

    public void stopTracking() {
        postState(TrackingScreenState.createStopTrackingState());
    }


    @Override
    public MutableLiveData<TrackingScreenState> getStateObservable() {
        return stateHolder;
    }

    @Override
    public void postState(TrackingScreenState state) {
        stateHolder.postValue(state);
    }
}
