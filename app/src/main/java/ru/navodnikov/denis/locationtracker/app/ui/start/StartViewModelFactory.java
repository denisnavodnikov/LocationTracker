package ru.navodnikov.denis.locationtracker.app.ui.start;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.navodnikov.denis.locationtracker.models.AppModule;

public class StartViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final StartContract.Router router;
    private final AppModule appComponent;

    public StartViewModelFactory(AppModule appComponent, StartContract.Router router) {
        this.appComponent = appComponent;
        this.router = router;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == StartViewModel.class) {
            return (T) new StartViewModel(router, appComponent.getCache());
        }
        return null;
    }
}
