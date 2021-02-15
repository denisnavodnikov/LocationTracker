package ru.navodnikov.denis.locationtracker.app.ui.tracking;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import ru.navodnikov.denis.locationtracker.app.TrackerApp;
import ru.navodnikov.denis.locationtracker.models.location.AppLocation;
import ru.navodnikov.denis.locationtracker.models.repo.TrackerRepo;
import ru.navodnikov.denis.locationtracker.models.repo.network.Network;
import ru.navodnikov.denis.locationtracker.models.sharedpref.SharedPref;

public class TrackingViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    @Inject
    TrackerRepo repo;
    @Inject
    Network network;
    @Inject
    AppLocation appLocation;
    @Inject
    SharedPref sharedPref;

    public TrackingViewModelFactory() {
        super();
        TrackerApp.getComponent().inject(this);
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == TrackingViewModel.class) {
            return (T) new TrackingViewModel( repo, network, appLocation, sharedPref);
        }
        return null;
    }
}
