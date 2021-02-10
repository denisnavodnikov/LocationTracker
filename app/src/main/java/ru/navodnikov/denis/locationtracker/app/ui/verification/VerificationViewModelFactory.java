package ru.navodnikov.denis.locationtracker.app.ui.verification;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import ru.navodnikov.denis.locationtracker.app.TrackerApp;
import ru.navodnikov.denis.locationtracker.models.repo.TrackerRepo;
import ru.navodnikov.denis.locationtracker.models.repo.network.Network;

public class VerificationViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    @Inject
    TrackerRepo repo;
    @Inject
    Network network;

    public VerificationViewModelFactory() {
        super();
        TrackerApp.getComponent().inject(this);
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == VerificationViewModel.class) {
            return (T) new VerificationViewModel(repo, network);
        }
        return null;
    }

}
