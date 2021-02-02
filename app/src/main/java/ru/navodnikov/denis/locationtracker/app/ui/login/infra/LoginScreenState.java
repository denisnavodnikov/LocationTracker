package ru.navodnikov.denis.locationtracker.app.ui.login.infra;

import ru.navodnikov.denis.locationtracker.R;
import ru.navodnikov.denis.locationtracker.app.ui.login.LoginContract;
import ru.navodnikov.denis.locationtracker.mvi.ScreenState;

public class LoginScreenState extends ScreenState<LoginContract.View> {

    private static final int USERNAME = 1;
    private static final int PASSWORD = 2;
    private static final int LOGIN = 3;
    private static final int ERROR_LOGIN = 4;

    private final int action;
    private final int error;

    public LoginScreenState(int action) {
        this.action = action;
        this.error = -1;
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

    public static LoginScreenState createErrorLoginState() {
        return new LoginScreenState(ERROR_LOGIN);
    }

    @Override
    public void visit(LoginContract.View loginScreen) {
        if (USERNAME == action) {
            loginScreen.showErrorEmptyUserName();
        } else if (PASSWORD == action) {
            loginScreen.showErrorEmptyPassword();

        }
        else if(LOGIN == action){
            loginScreen.showProgress();
        }
        else if(ERROR_LOGIN == action){
            loginScreen.hideProgress();
            loginScreen.showLoginFailed(R.string.login_failed);
        }
    }
}
