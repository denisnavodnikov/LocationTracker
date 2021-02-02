package ru.navodnikov.denis.locationtracker.app.ui.verification.infra;

import ru.navodnikov.denis.locationtracker.R;
import ru.navodnikov.denis.locationtracker.app.ui.verification.VerificationContract;
import ru.navodnikov.denis.locationtracker.mvi.ScreenState;

public class VerificationScreenState extends ScreenState<VerificationContract.View> {

    public static final int ERROR_VERIFICATION =1;

    private final int action;

    public VerificationScreenState(int action) {
        this.action = action;
    }

    public static VerificationScreenState createErrorVerificationState() {
        return new VerificationScreenState(ERROR_VERIFICATION);
    }

    @Override
    public void visit(VerificationContract.View verificationScreen) {
        if (ERROR_VERIFICATION == action){
            verificationScreen.hideProgress();
            verificationScreen.showVerificationFailed(R.string.verification_failed);
        }
    }
}
