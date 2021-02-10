package ru.navodnikov.denis.locationtracker.app.ui.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import ru.navodnikov.denis.locationtracker.app.TrackerApp;
import ru.navodnikov.denis.locationtracker.models.repo.network.Network;

public class LoginViewModelFactory extends ViewModelProvider.NewInstanceFactory  {
    @Inject
    Network network;


    public LoginViewModelFactory() {
        super();
        TrackerApp.getComponent().inject(this);
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == LoginViewModel.class) {
            return (T) new LoginViewModel(network);
        }
        return null;
    }
}
