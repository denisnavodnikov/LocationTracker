package ru.navodnikov.denis.locationtracker.app.ui.register;

import android.text.TextUtils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ru.navodnikov.denis.locationtracker.app.ui.register.infra.RegisterScreenState;
import ru.navodnikov.denis.locationtracker.app.utils.Constants;
import ru.navodnikov.denis.locationtracker.models.repo.TrackerRepo;
import ru.navodnikov.denis.locationtracker.models.repo.network.Network;
import ru.navodnikov.denis.locationtracker.models.sharedpref.SharedPref;
import ru.navodnikov.denis.locationtracker.mvi.MviViewModel;

public class RegisterViewModel extends MviViewModel<RegisterScreenState> implements RegisterContract.ViewModel {
    private final Network network;
    private final SharedPref sharedPref;

    public RegisterViewModel(Network network, SharedPref sharedPref) {
        this.network = network;
        this.sharedPref = sharedPref;

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

        observeTillDestroy(network.registerWithEmail(userEmail, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(item -> {
                    postState(RegisterScreenState.createRegisterState());
                })
                .subscribe(result -> {
                    if (result.isError()) {
                        postState(RegisterScreenState.createErrorRegisterState(result.getError()));
                    } else if (result.getValue() != Constants.ID_DEF_VALUE) {
                        sharedPref.putUserId(result.getValue());
                        postState(RegisterScreenState.createMoveToTrackingState());
                    }
                }));

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
                .doOnSubscribe(result -> {
                    postState(RegisterScreenState.createRegisterState());
                })
                .subscribe(result -> postState(RegisterScreenState.createMoveToVerificationState()),
                        throwable -> postState(RegisterScreenState.createErrorRegisterState(throwable))));

    }


}
