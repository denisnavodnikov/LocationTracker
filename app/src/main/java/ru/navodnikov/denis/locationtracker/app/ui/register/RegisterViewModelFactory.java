package ru.navodnikov.denis.locationtracker.app.ui.register;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.navodnikov.denis.locationtracker.models.AppModule;
import ru.navodnikov.denis.locationtracker.models.cache.Cache;
import ru.navodnikov.denis.locationtracker.mvi.MviViewModel;

public class RegisterViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final RegisterContract.Router router;
    private final AppModule appComponent;

    public RegisterViewModelFactory(AppModule appComponent, RegisterContract.Router router) {
        this.appComponent = appComponent;
        this.router = router;

    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == RegisterViewModel.class) {
            return (T) new RegisterViewModel(router, appComponent.getCache());
        }
        return null;
    }
}
