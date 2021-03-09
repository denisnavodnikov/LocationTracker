package ru.navodnikov.denis.locationtracker.app.ui.login;

import androidx.annotation.StringRes;

import io.reactivex.rxjava3.disposables.Disposable;
import ru.navodnikov.denis.locationtracker.app.ui.login.infra.LoginScreenState;
import ru.navodnikov.denis.locationtracker.abstractions.FragmentContract;

public class LoginContract {
    public interface ViewModel extends FragmentContract.ViewModel<LoginScreenState> {
        void loginWithEmail(String username, String password);

        void loginWithPhone(String emailOrPhone);

        void observeTillDestroy(Disposable... subscriptions);
    }

    public interface View extends FragmentContract.View {
        void showErrorEmptyPassword();

        void showProgress();

        void hideProgress();

        void showLoginFailed(@StringRes Integer errorString);

        void showErrorEmptyUserName();

        void proceedFromLoginToVerificationScreen();

        void proceedFromLoginToTrackingScreen();
    }


}
