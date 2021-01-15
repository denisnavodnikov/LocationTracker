package ru.navodnikov.denis.locationtracker.app.ui.tracking;

import ru.navodnikov.denis.locationtracker.models.cache.Cache;
import ru.navodnikov.denis.locationtracker.models.repo.TrackerRepo;
import ru.navodnikov.denis.locationtracker.mvi.MviViewModel;

public class TrackingViewModel extends MviViewModel<TrackingScreenState> implements TrackingContract.ViewModel{
    private final TrackingContract.Router router;
    private final TrackerRepo repo;
    private final Cache cache;

    public TrackingViewModel(TrackingContract.Router router, TrackerRepo repo, Cache cache) {
        this.router = router;
        this.repo = repo;
        this.cache = cache;
    }

    //    TODO добавить методы с логикой работы
}
