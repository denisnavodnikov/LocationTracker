package ru.navodnikov.denis.locationtracker.app.ui.register;

import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ru.navodnikov.denis.locationtracker.app.ui.register.infra.RegisterScreenState;
import ru.navodnikov.denis.locationtracker.models.repo.TrackerRepository;
import ru.navodnikov.denis.locationtracker.abstractions.FragmentContract;

public class RegisterViewModel extends ViewModel implements RegisterContract.ViewModel {
    private final TrackerRepository trackerRepository;
    private final MutableLiveData<RegisterScreenState> stateHolder = new MutableLiveData<>();
    private final CompositeDisposable onDestroyDisposables = new CompositeDisposable();

    @Inject
    public RegisterViewModel(TrackerRepository trackerRepository) {
        this.trackerRepository = trackerRepository;

    }

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

        observeTillDestroy(trackerRepository.registerWithEmailRepo(userEmail, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(item -> {
                    postState(RegisterScreenState.createRegisterState());
                })
                .subscribe(result -> postState(RegisterScreenState.createMoveToTrackingState()),
                        throwable -> postState(RegisterScreenState.createErrorRegisterState(throwable))
                ));

    }

    @Override
    public void registerWithPhone(String userPhone) {
        if (TextUtils.isEmpty(userPhone)) {
            postState(RegisterScreenState.createErrorEmptyUserEmailOrPhoneState());
            return;
        }
        observeTillDestroy(trackerRepository.verifyWithPhoneNumberRepo(userPhone)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(result -> {
                    postState(RegisterScreenState.createRegisterState());
                })
                .subscribeWith(new DisposableCompletableObserver(){

                    @Override
                    public void onComplete() {
                        postState(RegisterScreenState.createMoveToVerificationState());
                    }

                    @Override
                    public void onError(@NonNull Throwable throwable) {
                        postState(RegisterScreenState.createErrorRegisterState(throwable));
                    }
                }));

    }


    @Override
    public MutableLiveData<RegisterScreenState> getStateObservable() {
        return stateHolder;
    }

    @Override
    public void postState(RegisterScreenState state) {
        stateHolder.postValue(state);
    }

    @Override
    public void observeTillDestroy(Disposable... subscriptions) {
        onDestroyDisposables.addAll(subscriptions);
    }
}
