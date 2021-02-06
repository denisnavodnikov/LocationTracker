package ru.navodnikov.denis.locationtracker.app.ui.login;

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

    @Override
    public void loginWithEmail(String userEmail, String password) {

        if (TextUtils.isEmpty(userEmail)) {
            postState(LoginScreenState.createErrorInputUsernameState());
        }

        if (TextUtils.isEmpty(password)) {
            postState(LoginScreenState.createErrorInputPasswordState());
        }

        if (TextUtils.isEmpty(userEmail) || TextUtils.isEmpty(password)) {
            return;
        }

        Pair<String, String> loginUser = new Pair<>(userEmail, password);

        Single<Pair<String, String>> single = Single.just(loginUser);
        if (!hasOnDestroyDisposables()) {
            observeTillDestroy(single
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(item -> {
                        postState(LoginScreenState.createLoginState());
                    })
                    .doOnSuccess(pair -> {
                        router.proceedToTrackingScreen();
                    })
                    .doOnError(throwable -> postState(LoginScreenState.createErrorLoginState()))
                    .subscribe(pair -> network.loginWithEmail(userEmail, password)));

        }
    }

    @Override
    public void loginWithPhone(String userPhone) {
        if (TextUtils.isEmpty(userPhone)) {
            postState(LoginScreenState.createErrorInputUsernameState());
            return;
        }

        Single<String> single = Single.just(userPhone);
        if (!hasOnDestroyDisposables()) {
            observeTillDestroy(single
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(item -> {
                        postState(LoginScreenState.createLoginState());
                    })
                    .doOnSuccess(s -> {
                        router.proceedToVerificationScreen();
                    })
                    .doOnError(throwable -> postState(LoginScreenState.createErrorLoginState()))
                    .subscribe(s -> network.verifyWithPhoneNumber(s)));
        }
    }


}
