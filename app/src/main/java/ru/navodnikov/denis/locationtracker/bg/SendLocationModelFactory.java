package ru.navodnikov.denis.locationtracker.bg;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


public class SendLocationModelFactory extends ViewModelProvider.NewInstanceFactory {


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SendLocationViewModel();
    }
}
