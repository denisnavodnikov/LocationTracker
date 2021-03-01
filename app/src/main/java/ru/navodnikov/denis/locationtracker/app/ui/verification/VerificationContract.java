package ru.navodnikov.denis.locationtracker.app.ui.verification;

import ru.navodnikov.denis.locationtracker.app.ui.verification.infra.VerificationScreenState;
import ru.navodnikov.denis.locationtracker.abstractions.FragmentContract;

public class VerificationContract {
    public interface ViewModel extends FragmentContract.ViewModel<VerificationScreenState> {

        void verification(String s);
    }

    public interface View extends FragmentContract.View {

        void hideProgress();

        void showVerificationFailed(int verification_failed);

        void proceedFromVerificationToTrackingScreen();

        void showProgress();
    }


}
