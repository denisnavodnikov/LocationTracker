package ru.navodnikov.denis.locationtracker.app.di.builders;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ru.navodnikov.denis.locationtracker.app.di.module.tracking.TrackingModule;
import ru.navodnikov.denis.locationtracker.app.ui.login.LoginFragment;
import ru.navodnikov.denis.locationtracker.app.ui.register.RegisterFragment;
import ru.navodnikov.denis.locationtracker.app.ui.start.StartFragment;
import ru.navodnikov.denis.locationtracker.app.ui.tracking.TrackingFragment;
import ru.navodnikov.denis.locationtracker.app.ui.verification.VerificationFragment;

@Module
public abstract class FragmentBuildersModule {

    @ContributesAndroidInjector()
    abstract StartFragment provideStartFragment();

    @ContributesAndroidInjector()
    abstract LoginFragment provideLoginFragment();

    @ContributesAndroidInjector()
    abstract RegisterFragment provideRegisterFragment();

    @ContributesAndroidInjector()
    abstract VerificationFragment provideVerificationFragment();

    @ContributesAndroidInjector(modules = {TrackingModule.class})
    abstract TrackingFragment provideTrackingFragment();
}
