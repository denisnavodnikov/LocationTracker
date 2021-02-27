package ru.navodnikov.denis.locationtracker.app.ui.login;

import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ru.navodnikov.denis.locationtracker.app.ui.login.infra.LoginScreenState;
import ru.navodnikov.denis.locationtracker.models.repo.network.TrackerNetwork;
import ru.navodnikov.denis.locationtracker.viewmodel.FragmentContract;

public class LoginViewModel extends ViewModel implements FragmentContract.ViewModel<LoginScreenState>{

    private final TrackerNetwork trackerNetwork;
    private final MutableLiveData<LoginScreenState> stateHolder = new MutableLiveData<>();
    private final CompositeDisposable onDestroyDisposables = new CompositeDisposable();

    @Inject
    public LoginViewModel(TrackerNetwork trackerNetwork) {
        this.trackerNetwork = trackerNetwork;
    }

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
                trackerNetwork.loginWithEmail(userEmail, password)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(item -> {
                            postState(LoginScreenState.createLoginState());
                        })
                        .subscribe(result -> postState(LoginScreenState.createMoveToTrackingState()),
                                throwable -> postState(LoginScreenState.createErrorLoginState(throwable))
                        ));
    }

    public void loginWithPhone(String userPhone) {
        if (TextUtils.isEmpty(userPhone)) {
            postState(LoginScreenState.createErrorInputUsernameState());
            return;
        }

        observeTillDestroy(trackerNetwork.verifyWithPhoneNumber(userPhone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(item -> {
                    postState(LoginScreenState.createLoginState());
                })
                .subscribe(result -> postState(LoginScreenState.createMoveToVerificationState()),
                        throwable -> postState(LoginScreenState.createErrorLoginState(throwable))));

    }


    @Override
    public MutableLiveData<LoginScreenState> getStateObservable() {
        return stateHolder;
    }

    @Override
    public void postState(LoginScreenState state) {
        stateHolder.postValue(state);
    }
    protected void observeTillDestroy(Disposable... subscriptions) {
        onDestroyDisposables.addAll(subscriptions);
    }
}
