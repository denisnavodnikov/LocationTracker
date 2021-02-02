package ru.navodnikov.denis.locationtracker.app.ui.login;

import androidx.annotation.StringRes;

import ru.navodnikov.denis.locationtracker.app.ui.login.infra.LoginScreenState;
import ru.navodnikov.denis.locationtracker.mvi.FragmentContract;

public class LoginContract {
    public interface ViewModel extends FragmentContract.ViewModel<LoginScreenState> {
        void login(String username, String password, int inputType);

    }
    public interface View extends FragmentContract.View {
        void showErrorEmptyPassword();

        void showProgress();

        void hideProgress();

        void showLoginFailed(@StringRes Integer errorString);

        void showErrorEmptyUserName();
    }
    public interface Host extends FragmentContract.Host {

        void proceedFromLoginToVerificationScreen();
        void showError(int error);

    }

    public interface Router {
        void proceedToNextScreen();
    }
}
