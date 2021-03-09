package ru.navodnikov.denis.locationtracker.app.ui.start;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.navodnikov.denis.locationtracker.R;
import ru.navodnikov.denis.locationtracker.app.ui.start.infra.StartScreenState;
import ru.navodnikov.denis.locationtracker.databinding.FragmentStartBinding;
import ru.navodnikov.denis.locationtracker.abstractions.BaseFragment;


public class StartFragment extends BaseFragment<StartScreenState, StartViewModel, FragmentStartBinding> implements StartContract.View {

    public StartFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.getStateObservable().observe(this,this);
    }

    @Override
    public void onStart() {
        super.onStart();
        viewModel.checkUserAuthorisation();
    }


    @Override
    public Class<StartViewModel> getViewModelClass() {
        return StartViewModel.class;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentBinding = FragmentStartBinding.inflate(inflater, container, false);
        View view = fragmentBinding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentBinding.startLogin.setOnClickListener(v -> viewModel.onItemClicked(R.id.start_login));
        fragmentBinding.startRegister.setOnClickListener(v -> viewModel.onItemClicked(R.id.start_register));
    }


    @Override
    public void proceedToRegisterScreen() {
        navController.navigate(StartFragmentDirections.actionStartFragmentToRegisterFragment());
    }

    @Override
    public void proceedToTrackingScreen() {
        navController.navigate(StartFragmentDirections.actionStartFragmentToTrackingFragment());
    }

    @Override
    public void proceedToLoginScreen() {
        navController.navigate(StartFragmentDirections.actionStartFragmentToLoginFragment());
    }
}