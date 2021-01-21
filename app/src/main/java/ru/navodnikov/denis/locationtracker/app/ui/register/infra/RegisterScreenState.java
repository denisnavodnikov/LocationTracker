package ru.navodnikov.denis.locationtracker.app.ui.register.infra;

import ru.navodnikov.denis.locationtracker.R;
import ru.navodnikov.denis.locationtracker.app.ui.register.RegisterContract;
import ru.navodnikov.denis.locationtracker.mvi.ScreenState;

public class RegisterScreenState extends ScreenState<RegisterContract.View> {

    public static final int USERNAME = 1;
    public static final int USER_EMAIL = 2;
    public static final int USER_PHONE = 3;
    public static final int PASSWORD = 4;
    private static final int LOADING = 5;
    private static final int REGISTER = 6;

    private final int action;
    private final int error;
    private final boolean isProgress;

    public RegisterScreenState(int action, int error) {
        this.action = action;
        this.error = error;
        this.isProgress = false;
    }

    public RegisterScreenState(int action, boolean isProgress) {
        this.action = action;
        this.error = -1;
        this.isProgress = isProgress;
    }

    public static RegisterScreenState createErrorInputUsernameState(boolean empty) {
        return new RegisterScreenState(USERNAME, empty);
    }
    public static RegisterScreenState createErrorInputUserEmailState(boolean empty) {
        return new RegisterScreenState(USER_EMAIL, empty);
    }
    public static RegisterScreenState createErrorInputUserPhoneState(boolean empty) {
        return new RegisterScreenState(USER_PHONE, empty);
    }
    public static RegisterScreenState createErrorInputPasswordState(boolean empty) {
        return new RegisterScreenState(PASSWORD, empty);
    }

    @Override
    public void visit(RegisterContract.View registerScreen) {
        if(USERNAME==action){
            registerScreen.showError(action);
        }
        else if (USER_EMAIL==action){
            registerScreen.showError(action);
        }
        else if (USER_PHONE==action){
            registerScreen.showError(action);
        }
        else if (PASSWORD==action){
            registerScreen.showError(action);
        }
    }
}
