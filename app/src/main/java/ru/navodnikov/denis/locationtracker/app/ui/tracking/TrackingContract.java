package ru.navodnikov.denis.locationtracker.app.ui.tracking;

import ru.navodnikov.denis.locationtracker.mvi.FragmentContract;

public class TrackingContract {
    public interface ViewModel extends FragmentContract.ViewModel<TrackingScreenState> {
    }

    public interface View extends FragmentContract.View {
    }

    public interface Host extends FragmentContract.Host {

        void proceedToStartScreen();

        void showError(Throwable error);
    }
    public interface Router {
        void proceedToNextScreen();

        void launchWorker();

        void showError(Throwable error);
    }
}
