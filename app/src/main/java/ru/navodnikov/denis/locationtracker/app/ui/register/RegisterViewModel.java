package ru.navodnikov.denis.locationtracker.app.ui.register;

import android.text.TextUtils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ru.navodnikov.denis.locationtracker.app.utils.Constants;
import ru.navodnikov.denis.locationtracker.app.ui.register.infra.RegisterScreenState;
import ru.navodnikov.denis.locationtracker.models.repo.TrackerRepo;
import ru.navodnikov.denis.locationtracker.models.repo.network.Network;
import ru.navodnikov.denis.locationtracker.models_impl.repo.dao.schemas.User;
import ru.navodnikov.denis.locationtracker.mvi.MviViewModel;

public class RegisterViewModel extends MviViewModel<RegisterScreenState> implements RegisterContract.ViewModel {
    private final TrackerRepo repo;
    private final Network network;

    public RegisterViewModel(TrackerRepo repo, Network network) {
        this.repo = repo;
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

        observeTillDestroy(network.registerWithEmailNumber(userEmail, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(item -> {
                    postState(RegisterScreenState.createRegisterState());
                })
                .doOnSuccess(pair -> {
                    repo.getDao().saveUser(new User(0, userEmail, network.getIdToken()));
                    // TODO: move repo to other place
                })
                .subscribe(s -> postState(RegisterScreenState.createMoveToTrackingState()),
                        throwable -> postState(RegisterScreenState.createErrorRegisterState())));
    }


    @Override
    public void registerWithPhone(String userPhone) {
        if (TextUtils.isEmpty(userPhone)) {
            postState(RegisterScreenState.createErrorEmptyUserEmailOrPhoneState());
            return;
        }
        observeTillDestroy(network.verifyWithPhoneNumber(userPhone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(s -> {
                    postState(RegisterScreenState.createRegisterState());
                })
                .subscribe(s -> postState(RegisterScreenState.createMoveToVerificationState()),
                        throwable -> postState(RegisterScreenState.createErrorRegisterState())));

    }


}
