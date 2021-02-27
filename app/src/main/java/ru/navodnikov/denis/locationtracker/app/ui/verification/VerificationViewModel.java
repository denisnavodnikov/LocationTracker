package ru.navodnikov.denis.locationtracker.app.ui.verification;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ru.navodnikov.denis.locationtracker.app.ui.verification.infra.VerificationScreenState;
import ru.navodnikov.denis.locationtracker.models.repo.TrackerRepository;
import ru.navodnikov.denis.locationtracker.models.repo.network.TrackerNetwork;
import ru.navodnikov.denis.locationtracker.models.storage.UserStorage;
import ru.navodnikov.denis.locationtracker.viewmodel.FragmentContract;

public class VerificationViewModel extends ViewModel implements FragmentContract.ViewModel<VerificationScreenState> {

    private final TrackerRepository repo;
    private final TrackerNetwork trackerNetwork;
    private final UserStorage userStorage;
    private final MutableLiveData<VerificationScreenState> stateHolder = new MutableLiveData<>();
    private final CompositeDisposable onDestroyDisposables = new CompositeDisposable();

    @Inject
    public VerificationViewModel(TrackerRepository repo, TrackerNetwork trackerNetwork, UserStorage userStorage) {
        this.repo = repo;
        this.trackerNetwork = trackerNetwork;
        this.userStorage = userStorage;
    }



    public void verification(String smsCode) {

        observeTillDestroy(trackerNetwork.verificationWithSMS(smsCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(item -> {
                    postState(VerificationScreenState.createVerificationState());
                })
                .subscribe(result -> {
                            userStorage.putUserId(result);
                            postState(VerificationScreenState.createMoveToTrackingState());
                        },
                        throwable -> postState(VerificationScreenState.createErrorVerificationState())
                ));
    }


    @Override
    public MutableLiveData<VerificationScreenState> getStateObservable() {
        return stateHolder;
    }

    @Override
    public void postState(VerificationScreenState state) {
        stateHolder.postValue(state);
    }
    protected void observeTillDestroy(Disposable... subscriptions) {
        onDestroyDisposables.addAll(subscriptions);
    }
}
