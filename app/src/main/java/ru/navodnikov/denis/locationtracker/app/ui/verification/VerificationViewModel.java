package ru.navodnikov.denis.locationtracker.app.ui.verification;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ru.navodnikov.denis.locationtracker.app.ui.login.infra.LoginScreenState;
import ru.navodnikov.denis.locationtracker.app.ui.verification.infra.VerificationScreenState;
import ru.navodnikov.denis.locationtracker.app.utils.Constants;
import ru.navodnikov.denis.locationtracker.models.repo.TrackerRepo;
import ru.navodnikov.denis.locationtracker.models.repo.network.Network;
import ru.navodnikov.denis.locationtracker.models.sharedpref.SharedPref;
import ru.navodnikov.denis.locationtracker.mvi.MviViewModel;

public class VerificationViewModel extends MviViewModel<VerificationScreenState> implements VerificationContract.ViewModel {

    private final TrackerRepo repo;
    private final Network network;
    private final SharedPref sharedPref;

    public VerificationViewModel(TrackerRepo repo, Network network, SharedPref sharedPref) {
        this.repo = repo;
        this.network = network;
        this.sharedPref = sharedPref;
    }


    @Override
    public void verification(String smsCode) {

        observeTillDestroy(network.verificationWithSMS(smsCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(item -> {
                    postState(VerificationScreenState.createVerificationState());
                })
                .subscribe(result -> {
                    if (result.isError()) {
                        postState(VerificationScreenState.createErrorVerificationState());
                    } else if (result.getValue() != Constants.ID_DEF_VALUE) {
                        sharedPref.putUserId(result.getValue());
                        postState(VerificationScreenState.createMoveToTrackingState());
                    }

                }));
    }


}
