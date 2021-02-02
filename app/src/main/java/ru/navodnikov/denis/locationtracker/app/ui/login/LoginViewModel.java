package ru.navodnikov.denis.locationtracker.app.ui.login;

import android.text.InputType;
import android.text.TextUtils;
import android.util.Pair;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
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
    public void login(String username, String password, int inputType) {

        if (TextUtils.isEmpty(username)) {
            postState(LoginScreenState.createErrorInputUsernameState());
        }

        if (TextUtils.isEmpty(password)) {
            postState(LoginScreenState.createErrorInputPasswordState());
        }


        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            return;
        }
        if(inputType== InputType.TYPE_CLASS_PHONE){
            Pair<String, String> loginUser = new Pair<>(username, password);

            Single<Pair<String, String>> single = Single.just(loginUser);
            Disposable disposable = single
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(item -> {
                        postState(LoginScreenState.createLoginState());
                    })
                    .doOnSuccess(pair -> router.proceedToNextScreen())
                    .doOnError(throwable -> postState(LoginScreenState.createErrorLoginState()))
                    .subscribe(pair -> network.verifyWithPhoneNumber(pair.first, pair.second));
        }else {
            Pair<String, String> loginUser = new Pair<>(username, password);

            Single<Pair<String, String>> single = Single.just(loginUser);
            Disposable disposable = single
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(item -> {
                        postState(LoginScreenState.createLoginState());
                    })
                    .doOnSuccess(pair -> router.proceedToNextScreen())
                    .doOnError(throwable -> postState(LoginScreenState.createErrorLoginState()))
                    .subscribe(pair -> network.loginWithEmail(username,password));
        }

    }

}
