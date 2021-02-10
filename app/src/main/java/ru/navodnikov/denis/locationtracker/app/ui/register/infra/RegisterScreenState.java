package ru.navodnikov.denis.locationtracker.app.ui.register.infra;

import ru.navodnikov.denis.locationtracker.R;
import ru.navodnikov.denis.locationtracker.app.ui.register.RegisterContract;
import ru.navodnikov.denis.locationtracker.mvi.ScreenState;

public class RegisterScreenState extends ScreenState<RegisterContract.View> {

    public static final int EMPTY_USER_EMAIL_OR_PHONE = 0;
    public static final int EMPTY_PASSWORD = 1;
    public static final int SHORT_PASSWORD = 2;
    public static final int NOT_USER_EMAIL = 3;
    private static final int REGISTER = 4;
    private static final int ERROR_REGISTER = 5;
    private static final int TO_VERIFICATION = 6;
    private static final int TO_TRACKING = 7;


    private final int action;

    public RegisterScreenState(int action) {
        this.action = action;
    }



    public static RegisterScreenState createErrorEmptyUserEmailOrPhoneState() {
        return new RegisterScreenState(EMPTY_USER_EMAIL_OR_PHONE);
    }

    public static RegisterScreenState createErrorEmptyPasswordState() {
        return new RegisterScreenState(EMPTY_PASSWORD);
    }

    public static RegisterScreenState createErrorPasswordState() {
        return new RegisterScreenState(SHORT_PASSWORD);
    }

    public static RegisterScreenState createErrorUserEmailState() {
        return new RegisterScreenState(NOT_USER_EMAIL);
    }

    public static RegisterScreenState createRegisterState() {
        return new RegisterScreenState(REGISTER);
    }

    public static RegisterScreenState createErrorRegisterState() {
        return new RegisterScreenState(ERROR_REGISTER);
    }
    public static RegisterScreenState createMoveToVerificationState() {
        return new RegisterScreenState(TO_VERIFICATION);
    }
    public static RegisterScreenState createMoveToTrackingState() {
        return new RegisterScreenState(TO_TRACKING);
    }

    @Override
    public void visit(RegisterContract.View registerScreen) {

        if (EMPTY_USER_EMAIL_OR_PHONE ==action){
            registerScreen.showErrorEmptyUserEmailOrPhone();
        }
        else if (EMPTY_PASSWORD ==action){
            registerScreen.showErrorEmptyUserPassword();
        }
        else if (SHORT_PASSWORD == action){
            registerScreen.showErrorShortPassword();
        }
        else if (NOT_USER_EMAIL == action){
            registerScreen.showErrorNotUserEmail();
        }
        else if (REGISTER == action){
            registerScreen.showProgress();
        }
        else if (ERROR_REGISTER == action){
            registerScreen.hideProgress();
            registerScreen.showLoginFailed(R.string.register_failed);
        }
        else if (TO_VERIFICATION ==action){
            registerScreen.proceedFromRegisterToVerificationScreen();
        }
        else if (TO_TRACKING ==action){
            registerScreen.proceedFromRegisterToTrackingScreen();
        }
    }
}
