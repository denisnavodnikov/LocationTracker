package ru.navodnikov.denis.locationtracker.app.ui.verification.infra;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import ru.navodnikov.denis.locationtracker.R;
import ru.navodnikov.denis.locationtracker.app.ui.verification.VerificationContract;
import ru.navodnikov.denis.locationtracker.viewmodel.ScreenState;

public class VerificationScreenState extends ScreenState<VerificationContract.View> {
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ERROR_VERIFICATION, VERIFICATION, TO_TRACKING})
    private  @interface ItemTypeDef {}
    private static final int ERROR_VERIFICATION = 0;
    private static final int VERIFICATION = 1;
    private static final int TO_TRACKING = 2;


    private final int action;

    public VerificationScreenState(@ItemTypeDef int action) {
        this.action = action;
    }

    public static VerificationScreenState createErrorVerificationState() {
        return new VerificationScreenState(ERROR_VERIFICATION);
    }

    public static VerificationScreenState createMoveToTrackingState() {
        return new VerificationScreenState(TO_TRACKING);
    }

    public static VerificationScreenState createVerificationState() {
        return new VerificationScreenState(VERIFICATION);
    }

    @Override
    public void visit(VerificationContract.View verificationScreen) {
        if (ERROR_VERIFICATION == action) {
            verificationScreen.hideProgress();
            verificationScreen.showVerificationFailed(R.string.verification_failed);
        }else if(TO_TRACKING == action){
            verificationScreen.hideProgress();
            verificationScreen.proceedFromVerificationToTrackingScreen();
        }
        else if(VERIFICATION == action){
            verificationScreen.showProgress();
        }
    }
}
