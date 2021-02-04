package ru.navodnikov.denis.locationtracker.app.ui.verification;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ru.navodnikov.denis.locationtracker.app.utils.Constants;
import ru.navodnikov.denis.locationtracker.app.ui.verification.infra.VerificationScreenState;
import ru.navodnikov.denis.locationtracker.models.cache.Cache;
import ru.navodnikov.denis.locationtracker.models.repo.TrackerRepo;
import ru.navodnikov.denis.locationtracker.models.repo.network.Network;
import ru.navodnikov.denis.locationtracker.models_impl.repo.dao.schemas.User;
import ru.navodnikov.denis.locationtracker.mvi.MviViewModel;

public class VerificationViewModel extends MviViewModel<VerificationScreenState> implements VerificationContract.ViewModel {

    private final VerificationContract.Router router;
    private final TrackerRepo repo;
    private final Cache cache;
    private final Network network;

    public VerificationViewModel(VerificationContract.Router router, TrackerRepo repo, Cache cache, Network network) {
        this.router = router;
        this.repo = repo;
        this.cache = cache;
        this.network = network;
    }


    @Override
    public void verification(String smsCode) {
        Single<String> single = Single.just(smsCode);
        Disposable disposable = single
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(s -> {
                    saveToRepository();
                    router.proceedToNextScreen();
                })
                .doOnError(throwable -> postState(VerificationScreenState.createErrorVerificationState()))
                .subscribe(code -> network.verificationWithSMS(code));
    }

    private void saveToRepository() {
        if(cache.getAction()== Constants.REGISTRATION){
            repo.getDao().saveUser(new User(0, cache.getUserEmail(), cache.getUserPhone(), network.getIdToken()));
        }
    }

}
