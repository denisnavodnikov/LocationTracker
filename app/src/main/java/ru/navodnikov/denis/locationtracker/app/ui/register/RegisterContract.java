package ru.navodnikov.denis.locationtracker.app.ui.register;

import androidx.annotation.StringRes;

import ru.navodnikov.denis.locationtracker.app.ui.register.infra.RegisterScreenState;
import ru.navodnikov.denis.locationtracker.mvi.FragmentContract;

public class RegisterContract {
    public interface ViewModel extends FragmentContract.ViewModel<RegisterScreenState> {
        void register(String username, String userEmail, String userPhone, String password);

        void checkUserAuthorisation();
    }

    public interface View extends FragmentContract.View {
        void showProgress();

        void hideProgress();

        void showLoginFailed(@StringRes Integer errorString);

        void showError(int error);

    }

    public interface Host extends FragmentContract.Host {
        void proceedFromRegisterToTrackingScreen();
    }



    public interface Router {
        void proceedToNextScreen();
    }
}
