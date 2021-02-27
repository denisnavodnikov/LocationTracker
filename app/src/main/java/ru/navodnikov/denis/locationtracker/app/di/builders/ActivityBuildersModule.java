package ru.navodnikov.denis.locationtracker.app.di.builders;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ru.navodnikov.denis.locationtracker.app.di.module.tracking.TrackingModule;
import ru.navodnikov.denis.locationtracker.app.di.scope.PerActivity;
import ru.navodnikov.denis.locationtracker.app.ui.MainActivity;

@Module
public abstract class ActivityBuildersModule {

    @PerActivity
    @ContributesAndroidInjector(modules = {FragmentBuildersModule.class, ViewModelBuilderModule.class, TrackingModule.class})
    abstract MainActivity contributeMainActivity();


}
