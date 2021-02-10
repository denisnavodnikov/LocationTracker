package ru.navodnikov.denis.locationtracker.app.ui.start;


import ru.navodnikov.denis.locationtracker.R;
import ru.navodnikov.denis.locationtracker.app.ui.start.infra.StartScreenState;
import ru.navodnikov.denis.locationtracker.models.repo.network.Network;
import ru.navodnikov.denis.locationtracker.mvi.MviViewModel;

public class StartViewModel extends MviViewModel<StartScreenState> implements StartContract.ViewModel {
    private final Network network;

    public StartViewModel(Network network) {
        this.network = network;
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
        if (network.getmAuth().getCurrentUser() != null) {
            postState(StartScreenState.createMoveToTrackingState());
        }
    }

}
