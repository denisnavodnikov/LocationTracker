package ru.navodnikov.denis.locationtracker.app.ui.register;

import androidx.annotation.StringRes;

import ru.navodnikov.denis.locationtracker.app.ui.register.infra.RegisterScreenState;
import ru.navodnikov.denis.locationtracker.mvi.FragmentContract;

public class RegisterContract {
    public interface ViewModel extends FragmentContract.ViewModel<RegisterScreenState> {
        void register(String username, String userEmail, String userPhone, String password);
    }

    public interface View extends FragmentContract.View {
        void showErrorEmptyUserEmail();

        void showErrorEmptyUserPhone();

        void showErrorEmptyUserPassword();

        void showErrorShortPassword();

        void showErrorNotUserEmail();

        void showProgress();

        void hideProgress();

        void showLoginFailed(@StringRes Integer errorString);

        void showErrorEmptyUserName();

    }

    public interface Host extends FragmentContract.Host {
        void proceedFromRegisterToVerificationScreen();
        void showError(int error);
    }



    public interface Router {
        void proceedToNextScreen();
    }
}
