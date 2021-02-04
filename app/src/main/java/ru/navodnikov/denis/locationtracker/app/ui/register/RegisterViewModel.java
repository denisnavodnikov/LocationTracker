package ru.navodnikov.denis.locationtracker.app.ui.register;

import android.text.TextUtils;
import android.util.Pair;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ru.navodnikov.denis.locationtracker.app.utils.Constants;
import ru.navodnikov.denis.locationtracker.app.ui.register.infra.RegisterScreenState;
import ru.navodnikov.denis.locationtracker.models.cache.Cache;
import ru.navodnikov.denis.locationtracker.models.repo.network.Network;
import ru.navodnikov.denis.locationtracker.mvi.MviViewModel;

public class RegisterViewModel extends MviViewModel<RegisterScreenState> implements RegisterContract.ViewModel {
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
    public void registerWithEmail(String userEmail, String password) {

        if (TextUtils.isEmpty(userEmail)) {
            postState(RegisterScreenState.createErrorEmptyUserEmailState());
        }

        if (TextUtils.isEmpty(password)) {
            postState(RegisterScreenState.createErrorEmptyPasswordState());
        }
        if (TextUtils.isEmpty(userEmail) || TextUtils.isEmpty(password)) {
            return;
        }
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            postState(RegisterScreenState.createErrorUserEmailState());
            return;
        }

        if (password.length() < 5) {
            postState(RegisterScreenState.createErrorPasswordState());
            return;
        }

        Pair<String, String> loginUser = new Pair<>(userEmail, password);

        Single<Pair<String, String>> single = Single.just(loginUser);
        Disposable disposable = single
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(item -> {
                    postState(RegisterScreenState.createRegisterState());
                })
                .doOnSuccess(pair -> {
                    cache.setUserEmail(userEmail);
                    cache.setPassword(password);
                    cache.setAction(Constants.REGISTRATION);
                    router.proceedToNextScreen();
                })
                .doOnError(throwable -> postState(RegisterScreenState.createErrorRegisterState()))
                .subscribe(pair -> network.registerWithEmailNumber(pair.first, pair.second));
    }

    @Override
    public void registerWithPhone(String userPhone) {
        if (TextUtils.isEmpty(userPhone)) {
            postState(RegisterScreenState.createErrorEmptyUserPhoneState());
            return;
        }

        Single<String> single = Single.just(userPhone);
        Disposable disposable = single
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(s -> {
                    postState(RegisterScreenState.createRegisterState());
                })
                .doOnSuccess(s -> {
                    cache.setUserPhone(userPhone);
                    cache.setAction(Constants.REGISTRATION);
                    router.proceedToNextScreen();
                })
                .doOnError(throwable -> postState(RegisterScreenState.createErrorRegisterState()))
                .subscribe(s -> network.verifyWithPhoneNumber(s));
    }


}