package ru.navodnikov.denis.locationtracker.app.ui.tracking;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import ru.navodnikov.denis.locationtracker.app.ui.tracking.infra.TrackingScreenState;
import ru.navodnikov.denis.locationtracker.models.repo.TrackerRepository;
import ru.navodnikov.denis.locationtracker.abstractions.FragmentContract;


public class TrackingViewModel extends ViewModel implements TrackingContract.ViewModel {

    private final TrackerRepository trackerRepository;
    private final MutableLiveData<TrackingScreenState> stateHolder = new MutableLiveData<>();

    @Inject
    public TrackingViewModel(TrackerRepository trackerRepository) {
        this.trackerRepository = trackerRepository;
    }

    @Override
    public void logOut() {
        trackerRepository.signOutRepo();
        postState(TrackingScreenState.createLogoutState());
    }

    @Override
    public void startTracking() {
        postState(TrackingScreenState.createCheckPermissionState());
        postState(TrackingScreenState.createStartTrackingState());

    }

    @Override
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
