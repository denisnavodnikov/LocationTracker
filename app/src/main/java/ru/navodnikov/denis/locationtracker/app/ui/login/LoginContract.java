package ru.navodnikov.denis.locationtracker.app.ui.login;

import ru.navodnikov.denis.locationtracker.mvi.FragmentContract;

public class LoginContract {
    public interface ViewModel extends FragmentContract.ViewModel<LoginScreenState> {
    }
    public interface View extends FragmentContract.View {
        void showProgress();

        void hideProgress();
    }
    public interface Host extends FragmentContract.Host {

        void proceedFromLoginToTrackingScreen();

        void showError(Throwable error);


    }
    public interface Router {
        void proceedToNextScreen();

        void launchWorker();

        void showError(Throwable error);
    }
}
