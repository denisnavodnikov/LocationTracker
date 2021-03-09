package ru.navodnikov.denis.locationtracker.app.ui.start;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import ru.navodnikov.denis.locationtracker.R;
import ru.navodnikov.denis.locationtracker.app.ui.start.infra.StartScreenState;
import ru.navodnikov.denis.locationtracker.models.repo.network.TrackerNetwork;
import ru.navodnikov.denis.locationtracker.abstractions.FragmentContract;

public class StartViewModel extends ViewModel implements StartContract.ViewModel {

    private final TrackerNetwork trackerNetwork;
    private final MutableLiveData<StartScreenState> stateHolder = new MutableLiveData<>();


    @Inject
    public StartViewModel(TrackerNetwork trackerNetwork) {
        this.trackerNetwork = trackerNetwork;
    }


    @Override
    public void onItemClicked(int button) {
        if (button == R.id.start_login) {
            postState(StartScreenState.createMoveToLoginState());
        }
        if (button == R.id.start_register) {
            postState(StartScreenState.createMoveToRegistrState());
        }
    }

    @Override
    public void checkUserAuthorisation() {
        if (trackerNetwork.userIsNotNull()) {
            postState(StartScreenState.createMoveToTrackingState());
        }
    }

    @Override
    public MutableLiveData<StartScreenState> getStateObservable() {
        return stateHolder;
    }

    @Override
    public void postState(StartScreenState state) {
        stateHolder.postValue(state);
    }
}
