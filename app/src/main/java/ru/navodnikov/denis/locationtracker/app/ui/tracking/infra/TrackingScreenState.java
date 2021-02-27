package ru.navodnikov.denis.locationtracker.app.ui.tracking.infra;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import ru.navodnikov.denis.locationtracker.R;
import ru.navodnikov.denis.locationtracker.app.ui.tracking.TrackingContract;
import ru.navodnikov.denis.locationtracker.viewmodel.ScreenState;

public class TrackingScreenState extends ScreenState<TrackingContract.View> {
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ERROR, START_TRACKING, STOP_TRACKING,LOGOUT, CHECK_PERMISSION})
    private  @interface ItemTypeDef {}
    private static final int ERROR = 0;
    private static final int START_TRACKING = 1;
    private static final int STOP_TRACKING = 2;
    private static final int LOGOUT = 3;
    private static final int CHECK_PERMISSION = 4;

    private final int action;

    public TrackingScreenState(@ItemTypeDef int action) {
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

    public static TrackingScreenState createCheckPermissionState() {
        return new TrackingScreenState(CHECK_PERMISSION);
    }


    @Override
    public void visit(TrackingContract.View trackingScreen) {
        if(LOGOUT == action){
            trackingScreen.proceedToStartScreen();
        }else if(STOP_TRACKING==action){
            trackingScreen.stopService();
            trackingScreen.showMassage(R.string.text_tracking_stop);
        }
        else if(START_TRACKING==action){
            trackingScreen.startService();
            trackingScreen.showMassage(R.string.text_tracking_start);
        }
        else if(CHECK_PERMISSION==action){
            trackingScreen.permissionRequest();
        }
    }
}
