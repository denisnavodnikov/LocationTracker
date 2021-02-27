package ru.navodnikov.denis.locationtracker.app.di.module;

import androidx.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import ru.navodnikov.denis.locationtracker.app.ui.ViewModelProviderFactory;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory projectViewModelProviderFactory);
}
