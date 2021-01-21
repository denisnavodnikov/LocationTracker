package ru.navodnikov.denis.locationtracker.app.ui.login.infra;

import ru.navodnikov.denis.locationtracker.R;
import ru.navodnikov.denis.locationtracker.app.ui.login.LoginContract;
import ru.navodnikov.denis.locationtracker.mvi.ScreenState;

public class LoginScreenState extends ScreenState<LoginContract.View> {

    private static final int USERNAME = 1;
    private static final int PASSWORD = 2;
    private static final int LOADING = 3;
    private static final int LOGIN = 4;

    private final int action;
    private final int error;
    private final boolean isProgress;
    private final boolean empty;

    public LoginScreenState(int action, boolean empty) {
        this.action = action;
        this.error = -1;
        this.empty = empty;
        this.isProgress = false;
    }

    public static LoginScreenState createErrorInputUsernameState(boolean empty) {
        return new LoginScreenState(USERNAME, empty);
    }
    public static LoginScreenState createErrorInputPasswordState(boolean empty) {
        return new LoginScreenState(PASSWORD, empty);
    }

    @Override
    public void visit(LoginContract.View loginScreen) {
        if(USERNAME==action){
            loginScreen.showErrorEmptyUserName(R.string.empty_fild_error);
        }
        else if (PASSWORD==action){
            loginScreen.showErrorEmptyPassword(R.string.empty_password);
        }
    }
}
