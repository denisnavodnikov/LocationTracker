package ru.navodnikov.denis.locationtracker.app.ui.register;

import androidx.annotation.StringRes;

import ru.navodnikov.denis.locationtracker.app.ui.register.infra.RegisterScreenState;
import ru.navodnikov.denis.locationtracker.mvi.FragmentContract;

public class RegisterContract {
    public interface ViewModel extends FragmentContract.ViewModel<RegisterScreenState> {
        void registerWithEmail(String userEmail, String password);
        void registerWithPhone(String userPhone);
    }

    public interface View extends FragmentContract.View {
        void showErrorEmptyUserEmailOrPhone();

        void showErrorEmptyUserPassword();

        void showErrorShortPassword();

        void showErrorNotUserEmail();

        void showProgress();

        void hideProgress();

        void showLoginFailed(@StringRes Integer errorString);

        void proceedFromRegisterToVerificationScreen();
        void proceedFromRegisterToTrackingScreen();

    }

    public interface Host extends FragmentContract.Host {

        void showError(int error);
    }

}
