package ru.navodnikov.denis.locationtracker.app.di.builders;

import androidx.lifecycle.ViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import ru.navodnikov.denis.locationtracker.app.di.qualifiers.ViewModelKey;
import ru.navodnikov.denis.locationtracker.app.ui.login.LoginViewModel;
import ru.navodnikov.denis.locationtracker.app.ui.register.RegisterViewModel;
import ru.navodnikov.denis.locationtracker.app.ui.start.StartViewModel;
import ru.navodnikov.denis.locationtracker.app.ui.tracking.TrackingViewModel;
import ru.navodnikov.denis.locationtracker.app.ui.verification.VerificationViewModel;

@Module
public abstract class ViewModelBuilderModule {
    @Binds
    @IntoMap
    @ViewModelKey(StartViewModel.class)
    public abstract ViewModel bindStartViewModel(StartViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    public abstract ViewModel bindLoginViewModel(LoginViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel.class)
    public abstract ViewModel bindRegisterViewModel(RegisterViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(VerificationViewModel.class)
    public abstract ViewModel bindVerificationViewModel(VerificationViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(TrackingViewModel.class)
    public abstract ViewModel bindTrackingViewModel(TrackingViewModel viewModel);
}
