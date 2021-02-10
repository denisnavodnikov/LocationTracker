package ru.navodnikov.denis.locationtracker.app.ui.tracking;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import ru.navodnikov.denis.locationtracker.app.TrackerApp;
import ru.navodnikov.denis.locationtracker.models.location.Location;
import ru.navodnikov.denis.locationtracker.models.repo.TrackerRepo;
import ru.navodnikov.denis.locationtracker.models.repo.network.Network;

public class TrackingViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    @Inject
    TrackerRepo repo;
    @Inject
    Network network;
    @Inject
    Location  location;

    public TrackingViewModelFactory() {
        super();
        TrackerApp.getComponent().inject(this);
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == TrackingViewModel.class) {
            return (T) new TrackingViewModel( repo, network, location);
        }
        return null;
    }
}
