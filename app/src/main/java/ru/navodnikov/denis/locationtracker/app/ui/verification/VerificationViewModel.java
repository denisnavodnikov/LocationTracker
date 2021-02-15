package ru.navodnikov.denis.locationtracker.app.ui.verification;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ru.navodnikov.denis.locationtracker.app.ui.verification.infra.VerificationScreenState;
import ru.navodnikov.denis.locationtracker.models.repo.TrackerRepo;
import ru.navodnikov.denis.locationtracker.models.repo.network.Network;
import ru.navodnikov.denis.locationtracker.mvi.MviViewModel;

public class VerificationViewModel extends MviViewModel<VerificationScreenState> implements VerificationContract.ViewModel {

    private final TrackerRepo repo;
    private final Network network;

    public VerificationViewModel(TrackerRepo repo, Network network) {
        this.repo = repo;
        this.network = network;
    }


    @Override
    public void verification(String smsCode) {

        observeTillDestroy(network.verificationWithSMS(smsCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(s -> {
                    saveToRepository();
                    //TODO: change chane
                })
                .subscribe(code -> postState(VerificationScreenState.createMoveToTrackingState()),
                        throwable -> postState(VerificationScreenState.createErrorVerificationState())));
    }

    private void saveToRepository() {
        //TODO: change cache
//        if(cache.getAction()== Constants.REGISTRATION){
//            repo.getDao().saveUser(new User(0, cache.getUserPhone(), network.getIdToken()));
//        }
    }

}
