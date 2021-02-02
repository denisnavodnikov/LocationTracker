package ru.navodnikov.denis.locationtracker.app.ui.verification;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.navodnikov.denis.locationtracker.models.AppModule;

public class VerificationViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final VerificationContract.Router router;
    private final AppModule appComponent;

    public VerificationViewModelFactory(AppModule appComponent, VerificationContract.Router router) {
        super();
        this.appComponent = appComponent;
        this.router = router;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == VerificationViewModel.class) {
            return (T) new VerificationViewModel(router, appComponent.getTrackerNetwork());
        }
        return null;
    }

}
