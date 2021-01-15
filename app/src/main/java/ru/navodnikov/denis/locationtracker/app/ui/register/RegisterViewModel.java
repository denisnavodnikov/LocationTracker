package ru.navodnikov.denis.locationtracker.app.ui.register;

import ru.navodnikov.denis.locationtracker.models.cache.Cache;
import ru.navodnikov.denis.locationtracker.mvi.MviViewModel;

public class RegisterViewModel extends MviViewModel<RegisterScreenState> implements RegisterContract.ViewModel{
    private final RegisterContract.Router router;
    private final Cache cache;

    public RegisterViewModel(RegisterContract.Router router, Cache cache) {
        this.router = router;
        this.cache = cache;
    }
//    TODO добавить методы с логикой работы

}
