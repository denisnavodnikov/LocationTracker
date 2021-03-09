package ru.navodnikov.denis.locationtracker.app.ui.login;

import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;
import ru.navodnikov.denis.locationtracker.abstractions.FragmentContract;
import ru.navodnikov.denis.locationtracker.app.ui.login.infra.LoginScreenState;
import ru.navodnikov.denis.locationtracker.models.repo.TrackerRepository;

public class LoginViewModel extends ViewModel implements LoginContract.ViewModel {

    private final TrackerRepository trackerRepository;
    private final MutableLiveData<LoginScreenState> stateHolder = new MutableLiveData<>();
    private final CompositeDisposable onDestroyDisposables = new CompositeDisposable();

    @Inject
    public LoginViewModel(TrackerRepository trackerRepository) {
        this.trackerRepository = trackerRepository;
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
                trackerRepository.loginWithEmailRepo(userEmail, password)
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(item -> {
                            postState(LoginScreenState.createLoginState());
                        })
                        .subscribe(result -> postState(LoginScreenState.createMoveToTrackingState()),
                                throwable -> postState(LoginScreenState.createErrorLoginState(throwable))
                        ));
    }

    @Override
    public void loginWithPhone(String userPhone) {
        if (TextUtils.isEmpty(userPhone)) {
            postState(LoginScreenState.createErrorInputUsernameState());
            return;
        }

        observeTillDestroy(trackerRepository.verifyWithPhoneNumberRepo(userPhone)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(item -> {
                    postState(LoginScreenState.createLoginState());
                })
                .subscribeWith(new DisposableCompletableObserver() {

                    @Override
                    public void onComplete() {
                        postState(LoginScreenState.createMoveToVerificationState());
                    }

                    @Override
                    public void onError(@NonNull Throwable throwable) {
                        postState(LoginScreenState.createErrorLoginState(throwable));
                    }
                }));

    }

    @Override
    public MutableLiveData<LoginScreenState> getStateObservable() {
        return stateHolder;
    }

    @Override
    public void postState(LoginScreenState state) {
        stateHolder.postValue(state);
    }

    @Override
    public void observeTillDestroy(Disposable... subscriptions) {
        onDestroyDisposables.addAll(subscriptions);
    }
}
