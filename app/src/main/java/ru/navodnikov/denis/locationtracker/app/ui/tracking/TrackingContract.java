package ru.navodnikov.denis.locationtracker.app.ui.tracking;

import ru.navodnikov.denis.locationtracker.app.ui.tracking.infra.TrackingScreenState;
import ru.navodnikov.denis.locationtracker.mvi.FragmentContract;

public class TrackingContract {
    public interface ViewModel extends FragmentContract.ViewModel<TrackingScreenState> {
        void logOut();

        void startTracking();

        void stopTracking();
    }

    public interface View extends FragmentContract.View {
        void showError(int error);
        void showMassage(int massage);
    }

    public interface Host extends FragmentContract.Host {

        void proceedToStartScreen();
    }


    public interface Router {
        void proceedToNextScreen();

        void launchWorker();


    }
}
