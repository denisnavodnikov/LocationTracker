package ru.navodnikov.denis.locationtracker.viewmodel;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.DaggerFragment;
import ru.navodnikov.denis.locationtracker.app.ui.MainActivity;
import ru.navodnikov.denis.locationtracker.app.ui.ViewModelProviderFactory;
import ru.navodnikov.denis.locationtracker.app.ui.register.infra.RegisterScreenState;
import ru.navodnikov.denis.locationtracker.app.ui.start.infra.StartScreenState;


public abstract class BaseFragment<STATE extends ScreenState, VIEW_MODEL extends ViewModel>
        extends DaggerFragment
        implements FragmentContract.View, Observer<STATE> {

    @Inject
    ViewModelProviderFactory viewModelFactory;

    protected VIEW_MODEL model;

    public void showError(int error) {
        ((MainActivity)getActivity()).showError(error);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
        model = ViewModelProviders.of(this, viewModelFactory).get(getViewModel());

    }


    protected VIEW_MODEL getModel() {
        return model;
    }

    public abstract Class<VIEW_MODEL> getViewModel();

    @Override
    public void onChanged(STATE state) {
        state.visit(this);
    }
}
