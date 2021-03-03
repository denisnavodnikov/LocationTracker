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
import ru.navodnikov.denis.locationtracker.abstractions.FragmentContract;

public class VerificationViewModel extends ViewModel implements FragmentContract.ViewModel<VerificationScreenState> {

    private final TrackerRepository trackerRepository;
    private final MutableLiveData<VerificationScreenState> stateHolder = new MutableLiveData<>();
    private final CompositeDisposable onDestroyDisposables = new CompositeDisposable();

    @Inject
    public VerificationViewModel(TrackerRepository trackerRepository) {
        this.trackerRepository = trackerRepository;
    }

    public void verification(String smsCode) {

        observeTillDestroy(trackerRepository.verificationWithSMSRepo(smsCode)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(item -> {
                    postState(VerificationScreenState.createVerificationState());
                })
                .subscribe(result -> postState(VerificationScreenState.createMoveToTrackingState()),
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
