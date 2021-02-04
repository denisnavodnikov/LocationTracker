package ru.navodnikov.denis.locationtracker.app.ui.verification;

import ru.navodnikov.denis.locationtracker.app.ui.verification.infra.VerificationScreenState;
import ru.navodnikov.denis.locationtracker.mvi.FragmentContract;

public class VerificationContract {
    public interface ViewModel extends FragmentContract.ViewModel<VerificationScreenState> {

        void verification(String s);
    }

    public interface View extends FragmentContract.View {

        void hideProgress();

        void showVerificationFailed(int verification_failed);
    }

    public interface Host extends FragmentContract.Host {

        void proceedFromVerificationToTrackingScreen();
        void showError(int error);
    }


    public interface Router {
        void proceedToNextScreen();

    }
}