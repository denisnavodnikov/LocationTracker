package ru.navodnikov.denis.locationtracker.app.ui.start.infra;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import ru.navodnikov.denis.locationtracker.app.ui.start.StartContract;
import ru.navodnikov.denis.locationtracker.abstractions.ScreenState;

public class StartScreenState extends ScreenState<StartContract.View> {
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TO_LOGIN, TO_REGISTRATION, TO_TRACKING})
    private  @interface ItemTypeDef {}
    private static final int TO_LOGIN = 0;
    private static final int TO_REGISTRATION = 1;
    private static final int TO_TRACKING = 2;

    private final int action;

    public StartScreenState(@ItemTypeDef int action) {
        this.action = action;
    }

    public static StartScreenState createMoveToLoginState() {
        return new StartScreenState(TO_LOGIN);
    }
    public static StartScreenState createMoveToRegistrState() {
        return new StartScreenState(TO_REGISTRATION);
    }
    public static StartScreenState createMoveToTrackingState() {
        return new StartScreenState(TO_TRACKING);
    }

    @Override
    public void visit(StartContract.View startScreen) {
        if (TO_LOGIN ==action){
            startScreen.proceedToLoginScreen();
        }
        else if (TO_REGISTRATION ==action){
            startScreen.proceedToRegisterScreen();
        }
        else if (TO_TRACKING ==action){
            startScreen.proceedToTrackingScreen();
        }
    }
}
