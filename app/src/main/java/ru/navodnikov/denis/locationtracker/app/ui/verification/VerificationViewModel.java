package ru.navodnikov.denis.locationtracker.app.ui.verification;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ru.navodnikov.denis.locationtracker.app.ui.register.infra.RegisterScreenState;
import ru.navodnikov.denis.locationtracker.app.ui.verification.infra.VerificationScreenState;
import ru.navodnikov.denis.locationtracker.models.repo.network.Network;
import ru.navodnikov.denis.locationtracker.mvi.MviViewModel;

public class VerificationViewModel extends MviViewModel<VerificationScreenState> implements VerificationContract.ViewModel {

    private final VerificationContract.Router router;
    private final Network network;

    public VerificationViewModel(VerificationContract.Router router, Network network) {
        this.router = router;
        this.network = network;
    }


    @Override
    public void verification(String smsCode) {
        Single<String> single = Single.just(smsCode);
        Disposable disposable = single
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> router.proceedToNextScreen())
                .doOnError(throwable -> postState(VerificationScreenState.createErrorVerificationState()))
                .subscribe(code -> network.verificationWithSMS(code));
    }

}
