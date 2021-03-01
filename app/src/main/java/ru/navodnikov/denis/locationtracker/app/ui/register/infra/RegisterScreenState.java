package ru.navodnikov.denis.locationtracker.app.ui.register.infra;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import ru.navodnikov.denis.locationtracker.R;
import ru.navodnikov.denis.locationtracker.app.ui.register.RegisterContract;
import ru.navodnikov.denis.locationtracker.abstractions.ScreenState;

public class RegisterScreenState extends ScreenState<RegisterContract.View> {
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({EMPTY_USER_EMAIL_OR_PHONE, EMPTY_PASSWORD, SHORT_PASSWORD,NOT_USER_EMAIL, REGISTER, ERROR_REGISTER, TO_VERIFICATION, TO_TRACKING})
    private  @interface ItemTypeDef {}
    private static final int EMPTY_USER_EMAIL_OR_PHONE = 0;
    private static final int EMPTY_PASSWORD = 1;
    private static final int SHORT_PASSWORD = 2;
    private static final int NOT_USER_EMAIL = 3;
    private static final int REGISTER = 4;
    private static final int ERROR_REGISTER = 5;
    private static final int TO_VERIFICATION = 6;
    private static final int TO_TRACKING = 7;


    private final int action;
    private Throwable error;

    public RegisterScreenState(@ItemTypeDef int action) {
        this.action = action;
    }

    public RegisterScreenState(int action, Throwable error) {
        this.action = action;
        this.error = error;
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

    public static RegisterScreenState createErrorRegisterState(Throwable error) {
        return new RegisterScreenState(ERROR_REGISTER, error);
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
            if(error!=null){
                error.printStackTrace();
            }
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
