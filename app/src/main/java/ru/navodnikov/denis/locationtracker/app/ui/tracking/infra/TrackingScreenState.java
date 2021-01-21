package ru.navodnikov.denis.locationtracker.app.ui.tracking.infra;

import ru.navodnikov.denis.locationtracker.app.ui.tracking.TrackingContract;
import ru.navodnikov.denis.locationtracker.mvi.ScreenState;

public class TrackingScreenState extends ScreenState<TrackingContract.View> {

    private static final int ERROR = 1;
    private static final int MESSAGE = 2;
    private static final int TRACKING = 3;

    private final int action;
    private final int error;
    private final boolean isTracking;

    public TrackingScreenState(int action, int error) {
        this.action = action;
        this.error = error;
        this.isTracking = false;
    }

    public TrackingScreenState(int action, boolean isTracking) {
        this.action = action;
        this.error = -1;
        this.isTracking = isTracking;
    }

    @Override
    public void visit(TrackingContract.View screen) {

    }
}
