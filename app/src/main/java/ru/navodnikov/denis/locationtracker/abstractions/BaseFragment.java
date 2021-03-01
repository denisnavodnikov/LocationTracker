package ru.navodnikov.denis.locationtracker.abstractions;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewbinding.ViewBinding;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.DaggerFragment;
import ru.navodnikov.denis.locationtracker.R;
import ru.navodnikov.denis.locationtracker.app.ui.MainActivity;
import ru.navodnikov.denis.locationtracker.app.ui.ViewModelProviderFactory;


public abstract class BaseFragment<STATE extends ScreenState, VIEW_MODEL extends ViewModel, BINDING extends ViewBinding>
        extends DaggerFragment
        implements FragmentContract.View, Observer<STATE> {

    @Inject
    ViewModelProviderFactory viewModelFactory;

    protected BINDING fragmentBinding;

    protected VIEW_MODEL viewModel;

    protected NavController navController;

    public void showError(int error) {
        ((MainActivity)getActivity()).showError(error);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModelClass());

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(getActivity(), R.id.nav_host);
    }

    public abstract Class<VIEW_MODEL> getViewModelClass();

    @Override
    public void onChanged(STATE state) {
        state.visit(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentBinding = null;
    }

}
