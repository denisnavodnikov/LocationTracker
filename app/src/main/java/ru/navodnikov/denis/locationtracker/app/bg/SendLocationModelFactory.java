package ru.navodnikov.denis.locationtracker.app.bg;

import ru.navodnikov.denis.locationtracker.models.AppModule;


public class SendLocationModelFactory {

    public AppModule appComponent;

    public SendLocationModelFactory(AppModule appComponent) {
        this.appComponent = appComponent;
    }

    public SendTrackerContract.ServiceModel create() {
        return new SendLocationModel(appComponent.getTrackerRepo(), appComponent.getCache(), appComponent.getLocationClient());
    }
}
