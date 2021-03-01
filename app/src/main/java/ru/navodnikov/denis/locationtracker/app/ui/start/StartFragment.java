package ru.navodnikov.denis.locationtracker.app.ui.start;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.navodnikov.denis.locationtracker.R;
import ru.navodnikov.denis.locationtracker.app.ui.start.infra.StartScreenState;
import ru.navodnikov.denis.locationtracker.databinding.FragmentStartBinding;
import ru.navodnikov.denis.locationtracker.viewmodel.BaseFragment;


public class StartFragment extends BaseFragment<StartScreenState, StartViewModel> implements StartContract.View {


    private FragmentStartBinding fragmentStartBinding;
    private NavController navController;


    public StartFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model.getStateObservable().observe(this,this);
    }

    @Override
    public void onStart() {
        super.onStart();
        model.checkUserAuthorisation();
    }


    @Override
    public Class<StartViewModel> getViewModel() {
        return StartViewModel.class;
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
        fragmentStartBinding.startLogin.setOnClickListener(v -> model.onItemClicked(R.id.start_login));
        fragmentStartBinding.startRegister.setOnClickListener(v -> model.onItemClicked(R.id.start_register));
        navController = Navigation.findNavController(getActivity(), R.id.nav_host);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentStartBinding = null;
    }


    @Override
    public void proceedToRegisterScreen() {
        navController.navigate(R.id.action_startFragment_to_registerFragment);
    }

    @Override
    public void proceedToTrackingScreen() {
        navController.navigate(R.id.action_startFragment_to_trackingFragment);
    }

    @Override
    public void proceedToLoginScreen() {
        navController.navigate(R.id.action_startFragment_to_loginFragment);
    }
}