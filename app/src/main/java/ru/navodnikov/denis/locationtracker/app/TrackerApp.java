package ru.navodnikov.denis.locationtracker.app;

import android.app.Application;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import ru.navodnikov.denis.locationtracker.app.di.components.DaggerAppComponent;

public class TrackerApp extends Application implements HasAndroidInjector {

    @Inject
    DispatchingAndroidInjector<Object> androidInjector;

    @Override
    public AndroidInjector<Object> androidInjector() {
        return androidInjector;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent.builder().application(this).build().inject(this);
    }

//    @Override
//    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
//        return DaggerAppComponent.builder().application(this).build();
//    }



}
