package ru.navodnikov.denis.locationtracker.app.ui.register;

import ru.navodnikov.denis.locationtracker.mvi.FragmentContract;

public class RegisterContract {
    public interface ViewModel extends FragmentContract.ViewModel<RegisterScreenState> {
    }

    public interface View extends FragmentContract.View {
        void showProgress();

        void hideProgress();
    }

    public interface Host extends FragmentContract.Host {
        void proceedFromRegisterToTrackingScreen();

        void showError(Throwable error);
    }

    public interface Router {
        void proceedToNextScreen();

        void launchWorker();

        void showError(Throwable error);
    }
}
