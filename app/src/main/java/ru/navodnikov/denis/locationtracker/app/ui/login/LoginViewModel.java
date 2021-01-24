package ru.navodnikov.denis.locationtracker.app.ui.login;

import android.text.TextUtils;

import ru.navodnikov.denis.locationtracker.app.ui.login.infra.LoginScreenState;
import ru.navodnikov.denis.locationtracker.models.cache.Cache;
import ru.navodnikov.denis.locationtracker.models.repo.network.Network;
import ru.navodnikov.denis.locationtracker.mvi.MviViewModel;

public class LoginViewModel extends MviViewModel<LoginScreenState> implements LoginContract.ViewModel {
    private final LoginContract.Router router;
    private final Cache cache;
    private final Network network;

    public LoginViewModel(LoginContract.Router router, Cache cache, Network network) {
        this.router = router;
        this.cache = cache;
        this.network = network;
    }

    //    TODO добавить методы с логикой работы

    @Override
    public void login(String username, String password) {

        if (TextUtils.isEmpty(username)) {
            postState(LoginScreenState.createErrorInputUsernameState());
        }

        if (TextUtils.isEmpty(password)) {
            postState(LoginScreenState.createErrorInputPasswordState());
        }


        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            return;
        }

        postState(LoginScreenState.createLoginState());

        network.login(username,password);
        router.proceedToNextScreen();

//        postState(LoginScreenState.createErrorLoginState());  В случае ошибки

    }
    @Override
    public void checkUserAuthorisation(){
        if(network.getmAuth().getCurrentUser()!=null){
            router.proceedToNextScreen();
        }
    }
}
