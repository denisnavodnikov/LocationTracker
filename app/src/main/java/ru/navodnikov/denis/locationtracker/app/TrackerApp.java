package ru.navodnikov.denis.locationtracker.app;

import android.app.Application;

import ru.navodnikov.denis.locationtracker.app.di.components.AppComponent;
import ru.navodnikov.denis.locationtracker.app.di.components.DaggerAppComponent;
import ru.navodnikov.denis.locationtracker.app.di.module.AppModule;

public class TrackerApp extends Application {
    private static TrackerApp instance;
    private static AppComponent component;

    public static AppComponent getComponent() {
        return component;
    }
    public static TrackerApp getContext() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        component = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }
}
