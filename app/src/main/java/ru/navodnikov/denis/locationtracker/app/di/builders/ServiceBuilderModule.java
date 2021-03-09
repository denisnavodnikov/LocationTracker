package ru.navodnikov.denis.locationtracker.app.di.builders;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ru.navodnikov.denis.locationtracker.app.bg.ForegroundService;

@Module
public abstract class ServiceBuilderModule {

    @ContributesAndroidInjector
    abstract ForegroundService contributeMyService();

}
