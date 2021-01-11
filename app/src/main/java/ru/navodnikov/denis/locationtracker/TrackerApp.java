package ru.navodnikov.denis.locationtracker;

import android.app.Application;

import ru.navodnikov.denis.locationtracker.models.AppModule;
import ru.navodnikov.denis.locationtracker.models_impl.DefaultAppModule;

public class TrackerApp extends Application {
    private static TrackerApp instance;
    private AppModule appComponent;

    public static TrackerApp getInstance() {
        return instance;
    }

    public AppModule getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appComponent = new DefaultAppModule(this);
    }
}
