package ru.navodnikov.denis.locationtracker.app.ui.login;

import ru.navodnikov.denis.locationtracker.app.ui.login.infra.LoginScreenState;
import ru.navodnikov.denis.locationtracker.mvi.FragmentContract;

public class LoginContract {
    public interface ViewModel extends FragmentContract.ViewModel<LoginScreenState> {
        void login(String username, String password);
    }
    public interface View extends FragmentContract.View {
        void showErrorEmptyPassword(int error);

        void showProgress();

        void hideProgress();

        void showErrorEmptyUserName(int error);
    }
    public interface Host extends FragmentContract.Host {

        void showTrackingScreen();

    }

    public interface Router {
        void proceedToNextScreen();
    }
}
