package ru.navodnikov.denis.locationtracker.app.ui.tracking.infra;

import ru.navodnikov.denis.locationtracker.app.ui.tracking.TrackingContract;
import ru.navodnikov.denis.locationtracker.mvi.ScreenState;

public class TrackingScreenState extends ScreenState<TrackingContract.View> {

    private static final int ERROR = 1;
    private static final int MESSAGE = 2;
    private static final int TRACKING = 3;
    private static final int LOGOUT = 4;

    private final int action;

    public TrackingScreenState(int action) {
        this.action = action;
    }

    public static TrackingScreenState createLogoutState() {
        return new TrackingScreenState(LOGOUT);
    }


    @Override
    public void visit(TrackingContract.View trackingScreen) {
        if(LOGOUT == action){
            trackingScreen.proceedToStartScreen();
        }
    }
}
