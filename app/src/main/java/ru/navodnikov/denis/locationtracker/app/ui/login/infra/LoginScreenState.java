package ru.navodnikov.denis.locationtracker.app.ui.login.infra;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import ru.navodnikov.denis.locationtracker.R;
import ru.navodnikov.denis.locationtracker.app.ui.login.LoginContract;
import ru.navodnikov.denis.locationtracker.abstractions.ScreenState;

public class LoginScreenState extends ScreenState<LoginContract.View> {
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({USERNAME, PASSWORD, LOGIN, ERROR_LOGIN, TO_VERIFICATION, TO_TRACKING})
    private  @interface ItemTypeDef {}
    private static final int USERNAME = 0;
    private static final int PASSWORD = 1;
    private static final int LOGIN = 2;
    private static final int ERROR_LOGIN = 3;
    private static final int TO_VERIFICATION = 4;
    private static final int TO_TRACKING = 5;


    private final int action;
    private Throwable error;

    public LoginScreenState(@ItemTypeDef int action) {
        this.action = action;
    }

    public LoginScreenState(int action, Throwable error) {
        this.action = action;
        this.error = error;
    }

    public static LoginScreenState createErrorInputUsernameState() {
        return new LoginScreenState(USERNAME);
    }

    public static LoginScreenState createErrorInputPasswordState() {
        return new LoginScreenState(PASSWORD);
    }

    public static LoginScreenState createLoginState() {
        return new LoginScreenState(LOGIN);
    }

    public static LoginScreenState createErrorLoginState(Throwable error) {
        return new LoginScreenState(ERROR_LOGIN, error);
    }

    public static LoginScreenState createMoveToVerificationState() {
        return new LoginScreenState(TO_VERIFICATION);
    }

    public static LoginScreenState createMoveToTrackingState() {
        return new LoginScreenState(TO_TRACKING);
    }


    @Override
    public void visit(LoginContract.View loginScreen) {

        if (USERNAME == action) {
            loginScreen.showErrorEmptyUserName();
        } else if (PASSWORD == action) {
            loginScreen.showErrorEmptyPassword();
        } else if (LOGIN == action) {
            loginScreen.showProgress();
        } else if (ERROR_LOGIN == action) {
            if(error!=null){
                error.printStackTrace();
            }
            loginScreen.hideProgress();
            loginScreen.showLoginFailed(R.string.login_failed);
        } else if (TO_VERIFICATION == action) {
            loginScreen.proceedFromLoginToVerificationScreen();
        } else if (TO_TRACKING == action) {
            loginScreen.proceedFromLoginToTrackingScreen();
        }
    }
}
