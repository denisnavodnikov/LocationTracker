package ru.navodnikov.denis.locationtracker.app.ui.register;

import android.text.TextUtils;
import android.util.Pair;

import androidx.lifecycle.Lifecycle;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ru.navodnikov.denis.locationtracker.app.utils.Constants;
import ru.navodnikov.denis.locationtracker.app.ui.register.infra.RegisterScreenState;
import ru.navodnikov.denis.locationtracker.models.cache.Cache;
import ru.navodnikov.denis.locationtracker.models.repo.TrackerRepo;
import ru.navodnikov.denis.locationtracker.models.repo.network.Network;
import ru.navodnikov.denis.locationtracker.models_impl.repo.dao.schemas.User;
import ru.navodnikov.denis.locationtracker.mvi.MviViewModel;

public class RegisterViewModel extends MviViewModel<RegisterScreenState> implements RegisterContract.ViewModel {
    private final RegisterContract.Router router;
    private final TrackerRepo repo;
    private final Cache cache;
    private final Network network;

    public RegisterViewModel(RegisterContract.Router router, TrackerRepo repo, Cache cache, Network network) {
        this.router = router;
        this.repo = repo;
        this.cache = cache;
        this.network = network;
    }
//    TODO добавить методы с логикой работы

    @Override
    public void registerWithEmail(String userEmail, String password) {

        if (TextUtils.isEmpty(userEmail)) {
            postState(RegisterScreenState.createErrorEmptyUserEmailOrPhoneState());
        }

        if (TextUtils.isEmpty(password)) {
            postState(RegisterScreenState.createErrorEmptyPasswordState());
        }
        if (TextUtils.isEmpty(userEmail) || TextUtils.isEmpty(password)) {
            return;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            postState(RegisterScreenState.createErrorUserEmailState());
            return;
        }

        if (password.length() < 5) {
            postState(RegisterScreenState.createErrorPasswordState());
            return;
        }

        Pair<String, String> loginUser = new Pair<>(userEmail, password);

        Single<Pair<String, String>> single = Single.just(loginUser);
        if (!hasOnDestroyDisposables()) {
            observeTillDestroy(single
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(item -> {
                        postState(RegisterScreenState.createRegisterState());
                    })
                    .doOnSuccess(pair -> {
                        cache.setUserEmail(userEmail);
                        cache.setPassword(password);
                        cache.setAction(Constants.REGISTRATION);
                        repo.getDao().saveUser(new User(0, userEmail, network.getIdToken()));
                        router.proceedFromRegisterToTrackingScreen();
                    })
                    .doOnError(throwable -> postState(RegisterScreenState.createErrorRegisterState()))
                    .subscribe(pair -> network.registerWithEmailNumber(pair.first, pair.second)));
        }
    }


    @Override
    public void registerWithPhone(String userPhone) {
        if (TextUtils.isEmpty(userPhone)) {
            postState(RegisterScreenState.createErrorEmptyUserEmailOrPhoneState());
            return;
        }

        Single<String> single = Single.just(userPhone);
        if (!hasOnDestroyDisposables()) {
            observeTillDestroy(single
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(s -> {
                        postState(RegisterScreenState.createRegisterState());
                    })
                    .doOnSuccess(s -> {
                        cache.setUserPhone(userPhone);
                        cache.setAction(Constants.REGISTRATION);
                        router.proceedFromRegisterToVerificationScreen();
                    })
                    .doOnError(throwable -> postState(RegisterScreenState.createErrorRegisterState()))
                    .subscribe(s -> network.verifyWithPhoneNumber(s)));
        }
    }


}
