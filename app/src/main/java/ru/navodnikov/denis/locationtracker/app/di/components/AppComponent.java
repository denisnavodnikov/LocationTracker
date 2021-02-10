package ru.navodnikov.denis.locationtracker.app.di.components;

import javax.inject.Singleton;

import dagger.Component;
import ru.navodnikov.denis.locationtracker.app.bg.SendLocationModelFactory;
import ru.navodnikov.denis.locationtracker.app.di.module.AppModule;
import ru.navodnikov.denis.locationtracker.app.ui.login.LoginViewModelFactory;
import ru.navodnikov.denis.locationtracker.app.ui.register.RegisterViewModelFactory;
import ru.navodnikov.denis.locationtracker.app.ui.start.StartViewModelFactory;
import ru.navodnikov.denis.locationtracker.app.ui.tracking.TrackingViewModelFactory;
import ru.navodnikov.denis.locationtracker.app.ui.verification.VerificationViewModelFactory;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(StartViewModelFactory startViewModelFactory);
    void inject(VerificationViewModelFactory verificationViewModelFactory);
    void inject(LoginViewModelFactory loginViewModelFactory);
    void inject(RegisterViewModelFactory registerViewModelFactory);
    void inject(TrackingViewModelFactory trackingViewModelFactory);
    void inject(SendLocationModelFactory sendLocationModelFactory);

}
