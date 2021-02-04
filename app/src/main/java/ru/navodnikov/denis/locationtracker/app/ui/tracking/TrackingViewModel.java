package ru.navodnikov.denis.locationtracker.app.ui.tracking;

import ru.navodnikov.denis.locationtracker.app.ui.tracking.infra.TrackingScreenState;
import ru.navodnikov.denis.locationtracker.models.cache.Cache;
import ru.navodnikov.denis.locationtracker.models.repo.TrackerRepo;
import ru.navodnikov.denis.locationtracker.models.repo.network.Network;
import ru.navodnikov.denis.locationtracker.mvi.MviViewModel;

public class TrackingViewModel extends MviViewModel<TrackingScreenState> implements TrackingContract.ViewModel{
    private final TrackingContract.Router router;
    private final TrackerRepo repo;
    private final Cache cache;
    private final Network network;

    public TrackingViewModel(TrackingContract.Router router, TrackerRepo repo, Cache cache, Network network) {
        this.router = router;
        this.repo = repo;
        this.cache = cache;
        this.network = network;
    }
//    TODO добавить методы с логикой работы

    @Override
    public void logOut() {
        network.getmAuth().signOut();
        router.proceedToNextScreen();
    }

    @Override
    public void startTracking() {


    }
    @Override
    public void stopTracking() {

    }


}
