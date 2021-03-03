package ru.navodnikov.denis.locationtracker.app;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import ru.navodnikov.denis.locationtracker.app.di.components.DaggerAppComponent;

public class TrackerApp extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}
