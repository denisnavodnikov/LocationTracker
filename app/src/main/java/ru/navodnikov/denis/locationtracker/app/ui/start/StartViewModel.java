package ru.navodnikov.denis.locationtracker.app.ui.start;


import ru.navodnikov.denis.locationtracker.models.cache.Cache;
import ru.navodnikov.denis.locationtracker.mvi.MviViewModel;

public class StartViewModel extends MviViewModel<StartScreenState> implements StartContract.ViewModel {
    private final StartContract.Router router;
    private final Cache cache;

    public StartViewModel(StartContract.Router router, Cache cache) {
        this.router = router;
        this.cache = cache;
    }

//    TODO добавить мтоды с логикой работы

}
