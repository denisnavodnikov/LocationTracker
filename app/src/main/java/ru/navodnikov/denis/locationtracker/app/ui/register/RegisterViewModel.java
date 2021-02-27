package ru.navodnikov.denis.locationtracker.app.ui.register;

import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ru.navodnikov.denis.locationtracker.app.ui.register.infra.RegisterScreenState;
import ru.navodnikov.denis.locationtracker.models.repo.network.TrackerNetwork;
import ru.navodnikov.denis.locationtracker.models.storage.UserStorage;
import ru.navodnikov.denis.locationtracker.viewmodel.FragmentContract;

public class RegisterViewModel extends ViewModel implements FragmentContract.ViewModel<RegisterScreenState> {
    private final TrackerNetwork trackerNetwork;
    private final UserStorage userStorage;
    private final MutableLiveData<RegisterScreenState> stateHolder = new MutableLiveData<>();
    private final CompositeDisposable onDestroyDisposables = new CompositeDisposable();

    @Inject
    public RegisterViewModel(TrackerNetwork trackerNetwork, UserStorage userStorage) {
        this.trackerNetwork = trackerNetwork;
        this.userStorage = userStorage;

    }


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

        observeTillDestroy(trackerNetwork.registerWithEmail(userEmail, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(item -> {
                    postState(RegisterScreenState.createRegisterState());
                })
                .subscribe(result -> {
                            userStorage.putUserId(result);
                            postState(RegisterScreenState.createMoveToTrackingState());
                        },
                        throwable -> postState(RegisterScreenState.createErrorRegisterState(throwable))
                ));

    }

    public void registerWithPhone(String userPhone) {
        if (TextUtils.isEmpty(userPhone)) {
            postState(RegisterScreenState.createErrorEmptyUserEmailOrPhoneState());
            return;
        }
        observeTillDestroy(trackerNetwork.verifyWithPhoneNumber(userPhone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(result -> {
                    postState(RegisterScreenState.createRegisterState());
                })
                .subscribe(result -> postState(RegisterScreenState.createMoveToVerificationState()),
                        throwable -> postState(RegisterScreenState.createErrorRegisterState(throwable))));

    }


    @Override
    public MutableLiveData<RegisterScreenState> getStateObservable() {
        return stateHolder;
    }

    @Override
    public void postState(RegisterScreenState state) {
        stateHolder.postValue(state);
    }

    protected void observeTillDestroy(Disposable... subscriptions) {
        onDestroyDisposables.addAll(subscriptions);
    }
}
