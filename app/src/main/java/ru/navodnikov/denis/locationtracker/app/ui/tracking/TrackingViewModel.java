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

    private final TrackerRepository trackerRepository;
    private boolean isPermissionChecked;
    private final MutableLiveData<TrackingScreenState> stateHolder = new MutableLiveData<>();

    @Inject
    public TrackingViewModel(TrackerRepository trackerRepository) {
        this.trackerRepository = trackerRepository;
    }


    public void setPermissionChecked(boolean permissionChecked) {
        isPermissionChecked = permissionChecked;
    }


    public void logOut() {
        trackerRepository.signOutRepo();
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
