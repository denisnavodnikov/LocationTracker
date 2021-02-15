package ru.navodnikov.denis.locationtracker.app.ui.login.infra;

import ru.navodnikov.denis.locationtracker.R;
import ru.navodnikov.denis.locationtracker.app.ui.login.LoginContract;
import ru.navodnikov.denis.locationtracker.mvi.ScreenState;

public class LoginScreenState extends ScreenState<LoginContract.View> {

    private static final int USERNAME = 1;
    private static final int PASSWORD = 2;
    private static final int LOGIN = 3;
    private static final int ERROR_LOGIN = 4;
    private static final int TO_VERIFICATION = 5;
    private static final int TO_TRACKING = 6;


    private final int action;
    private Throwable error;

    public LoginScreenState(int action) {
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
