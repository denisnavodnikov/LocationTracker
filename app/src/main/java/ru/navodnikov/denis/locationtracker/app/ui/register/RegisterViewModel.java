package ru.navodnikov.denis.locationtracker.app.ui.register;

import android.text.TextUtils;

import ru.navodnikov.denis.locationtracker.app.ui.register.infra.RegisterScreenState;
import ru.navodnikov.denis.locationtracker.models.cache.Cache;
import ru.navodnikov.denis.locationtracker.models.repo.network.Network;
import ru.navodnikov.denis.locationtracker.mvi.MviViewModel;

public class RegisterViewModel extends MviViewModel<RegisterScreenState> implements RegisterContract.ViewModel{
    private final RegisterContract.Router router;
    private final Cache cache;
    private final Network network;

    public RegisterViewModel(RegisterContract.Router router, Cache cache, Network network) {
        this.router = router;
        this.cache = cache;
        this.network = network;
    }
//    TODO добавить методы с логикой работы

    @Override
    public void register(String username, String userEmail, String userPhone, String password) {
        if (TextUtils.isEmpty(username)) {
            postState(RegisterScreenState.createErrorEmptyUsernameState());
        }
        if (TextUtils.isEmpty(userEmail)) {
            postState(RegisterScreenState.createErrorEmptyUserEmailState());
        }
        if (TextUtils.isEmpty(userPhone)) {
            postState(RegisterScreenState.createErrorEmptyUserPhoneState());
        }
        if (TextUtils.isEmpty(password)) {
            postState(RegisterScreenState.createErrorEmptyPasswordState());
        }
        if(TextUtils.isEmpty(username)||TextUtils.isEmpty(userEmail)||TextUtils.isEmpty(userPhone)||TextUtils.isEmpty(password)){
            return;
        }
        if (!username.contains("@")&&!userEmail.contains(".")) {
            postState(RegisterScreenState.createErrorUserEmailState());
            return;
        }

        if (password.length()<5) {
            postState(RegisterScreenState.createErrorPasswordState());
            return;
        }
            postState(RegisterScreenState.createRegisterState());
            network.register(userEmail, password);
            router.proceedToNextScreen();

//            postState(RegisterScreenState.createErrorRegisterState()); В случае ошибки
    }

    @Override
    public void checkUserAuthorisation(){
        if(network.getmAuth().getCurrentUser()!=null){
            router.proceedToNextScreen();
        }
    }
}
