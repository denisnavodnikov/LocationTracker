package ru.navodnikov.denis.locationtracker.app.ui.start;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.navodnikov.denis.locationtracker.R;
import ru.navodnikov.denis.locationtracker.app.ui.start.infra.StartScreenState;
import ru.navodnikov.denis.locationtracker.databinding.FragmentStartBinding;
import ru.navodnikov.denis.locationtracker.mvi.HostedFragment;


public class StartFragment extends HostedFragment<StartScreenState, StartContract.ViewModel, StartContract.Host> implements StartContract.View, View.OnClickListener {

    private FragmentStartBinding fragmentStartBinding;


    public StartFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();
        getModel().checkUserAuthorisation();
    }


    @Override
    protected StartContract.ViewModel createModel() {
        return new ViewModelProvider(this, new StartViewModelFactory()).get(StartViewModel.class);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentStartBinding = FragmentStartBinding.inflate(inflater, container, false);
        View view = fragmentStartBinding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentStartBinding.startLogin.setOnClickListener(this);
        fragmentStartBinding.startRegister.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentStartBinding = null;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.start_login) {
            getModel().onItemClicked(R.id.start_login);
        }
        if (v.getId() == R.id.start_register) {
            getModel().onItemClicked(R.id.start_register);
        }
    }

    @Override
    public void proceedToRegisterScreen() {
        NavHostFragment.findNavController(this).navigate(R.id.action_startFragment_to_registerFragment);
    }

    @Override
    public void proceedToTrackingScreen() {
        NavHostFragment.findNavController(this).navigate(R.id.action_startFragment_to_trackingFragment);
    }

    @Override
    public void proceedToLoginScreen() {
        NavHostFragment.findNavController(this).navigate(R.id.action_startFragment_to_loginFragment);
    }
}