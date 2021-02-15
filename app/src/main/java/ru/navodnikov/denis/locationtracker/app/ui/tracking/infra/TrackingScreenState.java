package ru.navodnikov.denis.locationtracker.app.ui.tracking.infra;

import ru.navodnikov.denis.locationtracker.R;
import ru.navodnikov.denis.locationtracker.app.ui.tracking.TrackingContract;
import ru.navodnikov.denis.locationtracker.mvi.ScreenState;

public class TrackingScreenState extends ScreenState<TrackingContract.View> {

    private static final int ERROR = 1;
    private static final int START_TRACKING = 2;
    private static final int STOP_TRACKING = 3;
    private static final int LOGOUT = 4;

    private final int action;

    public TrackingScreenState(int action) {
        this.action = action;
    }

    public static TrackingScreenState createLogoutState() {
        return new TrackingScreenState(LOGOUT);
    }

    public static TrackingScreenState createStopTrackingState() {
        return new TrackingScreenState(STOP_TRACKING);
    }
    public static TrackingScreenState createStartTrackingState() {
        return new TrackingScreenState(START_TRACKING);
    }


    @Override
    public void visit(TrackingContract.View trackingScreen) {
        if(LOGOUT == action){
            trackingScreen.proceedToStartScreen();
        }else if(STOP_TRACKING==action){
            trackingScreen.stopService();
            trackingScreen.showMassage(R.string.text_tracking_stop);
        }
        else if(STOP_TRACKING==action){
            trackingScreen.startService();
            trackingScreen.showMassage(R.string.text_tracking_start);
        }
    }
}
