package ru.navodnikov.denis.locationtracker.app.ui.tracking;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.navodnikov.denis.locationtracker.models.AppModule;

public class TrackingViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final AppModule appComponent;
    private final TrackingContract.Router router;

    public TrackingViewModelFactory(AppModule appComponent, TrackingContract.Router router) {
        this.appComponent = appComponent;
        this.router = router;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == TrackingViewModel.class) {
            return (T) new TrackingViewModel(router, appComponent.getTrackerRepo(), appComponent.getCache(), appComponent.getTrackerNetwork());
        }
        return null;
    }
}
