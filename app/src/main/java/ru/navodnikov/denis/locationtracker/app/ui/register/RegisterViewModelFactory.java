package ru.navodnikov.denis.locationtracker.app.ui.register;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import ru.navodnikov.denis.locationtracker.app.TrackerApp;
import ru.navodnikov.denis.locationtracker.models.repo.TrackerRepo;
import ru.navodnikov.denis.locationtracker.models.repo.network.Network;

public class RegisterViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    @Inject
    TrackerRepo repo;
    @Inject
    Network network;

    public RegisterViewModelFactory() {
        super();
        TrackerApp.getComponent().inject(this);
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == RegisterViewModel.class) {
            return (T) new RegisterViewModel(repo, network);
        }
        return null;
    }
}
