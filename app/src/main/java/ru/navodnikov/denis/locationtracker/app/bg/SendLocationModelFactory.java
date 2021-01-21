package ru.navodnikov.denis.locationtracker.app.bg;

import ru.navodnikov.denis.locationtracker.app.TrackerApp;
import ru.navodnikov.denis.locationtracker.models.AppModule;


public class SendLocationModelFactory {

    public AppModule appComponent;

    public SendLocationModelFactory() {
        this.appComponent = TrackerApp.getInstance().getAppComponent();
    }

    public SendTrackerContract.ServiceModel create() {
        return new SendLocationModel(appComponent.getTrackerRepo(), appComponent.getCache());
    }
}
