package ru.navodnikov.denis.locationtracker.app.ui.login;

import ru.navodnikov.denis.locationtracker.models.cache.Cache;
import ru.navodnikov.denis.locationtracker.mvi.MviViewModel;

public class LoginViewModel extends MviViewModel<LoginScreenState> implements LoginContract.ViewModel {
    private final LoginContract.Router router;
    private final Cache cache;

    public LoginViewModel(LoginContract.Router router, Cache cache) {
        this.router = router;
        this.cache = cache;
    }
    //    TODO добавить методы с логикой работы
}
