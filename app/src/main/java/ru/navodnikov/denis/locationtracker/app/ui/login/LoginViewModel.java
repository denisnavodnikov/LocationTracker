package ru.navodnikov.denis.locationtracker.app.ui.login;

import android.text.TextUtils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ru.navodnikov.denis.locationtracker.app.ui.login.infra.LoginScreenState;
import ru.navodnikov.denis.locationtracker.app.utils.Constants;
import ru.navodnikov.denis.locationtracker.models.repo.network.Network;
import ru.navodnikov.denis.locationtracker.mvi.MviViewModel;

public class LoginViewModel extends MviViewModel<LoginScreenState> implements LoginContract.ViewModel {

    private final Network network;

    public LoginViewModel(Network network) {
        this.network = network;
    }

    @Override
    public void loginWithEmail(String userEmail, String password) {

        if (TextUtils.isEmpty(userEmail) || TextUtils.isEmpty(password)) {
            if (TextUtils.isEmpty(userEmail)) {
                postState(LoginScreenState.createErrorInputUsernameState());
            }

            if (TextUtils.isEmpty(password)) {
                postState(LoginScreenState.createErrorInputPasswordState());
            }
            return;
        }
        observeTillDestroy(
                network.loginWithEmail(userEmail, password)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(item -> {
                            postState(LoginScreenState.createLoginState());
                        })
                        .subscribe(result -> {
                                    if (result.isError()) {
                                        postState(LoginScreenState.createErrorLoginState(result.getError()));
                                    } else if (result.getValue() != Constants.ID_DEF_VALUE) {
                                        postState(LoginScreenState.createMoveToTrackingState());
                                    }
                                }
                        ));
    }

    @Override
    public void loginWithPhone(String userPhone) {
        if (TextUtils.isEmpty(userPhone)) {
            postState(LoginScreenState.createErrorInputUsernameState());
            return;
        }

        observeTillDestroy(network.verifyWithPhoneNumber(userPhone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(item -> {
                    postState(LoginScreenState.createLoginState());
                })
                .subscribe(result -> postState(LoginScreenState.createMoveToVerificationState()),
                        throwable -> postState(LoginScreenState.createErrorLoginState(throwable))));

    }


}
