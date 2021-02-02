package ru.navodnikov.denis.locationtracker.app.ui.start;


import ru.navodnikov.denis.locationtracker.app.ui.start.infra.StartScreenState;
import ru.navodnikov.denis.locationtracker.models.repo.network.Network;
import ru.navodnikov.denis.locationtracker.mvi.MviViewModel;

public class StartViewModel extends MviViewModel<StartScreenState> implements StartContract.ViewModel {
    private final StartContract.Router router;
    private final Network network;

    public StartViewModel(StartContract.Router router, Network network) {
        this.router = router;
        this.network = network;
    }


    @Override
    public void onItemClicked(int button) {
        router.proceedToNextScreen(button);
    }

    @Override
    public void checkUserAuthorisation() {
        if(network.getmAuth().getCurrentUser()!=null){
            router.proceedToTrackingScreenWithOutLogin();
        }
    }

}
