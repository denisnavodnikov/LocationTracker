package ru.navodnikov.denis.locationtracker.app.ui.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.navodnikov.denis.locationtracker.models.AppModule;

public class LoginViewModelFactory extends ViewModelProvider.NewInstanceFactory  {
    private final AppModule appComponent;
    private final LoginContract.Router router;


    public LoginViewModelFactory(AppModule appComponent, LoginContract.Router router) {
        this.appComponent = appComponent;
        this.router = router;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == LoginViewModel.class) {
            return (T) new LoginViewModel(router, appComponent.getCache(), appComponent.getTrackerNetwork());
        }
        return null;
    }
}
