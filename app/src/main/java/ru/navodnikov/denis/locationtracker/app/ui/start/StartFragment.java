package ru.navodnikov.denis.locationtracker.app.ui.start;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.navodnikov.denis.locationtracker.R;
import ru.navodnikov.denis.locationtracker.app.ui.Constants;
import ru.navodnikov.denis.locationtracker.app.ui.start.infra.StartScreenState;
import ru.navodnikov.denis.locationtracker.databinding.FragmentStartBinding;
import ru.navodnikov.denis.locationtracker.mvi.HostedFragment;


public class StartFragment  extends HostedFragment<StartScreenState, StartContract.ViewModel, StartContract.Host> implements StartContract.View, StartContract.Router, View.OnClickListener{

    private FragmentStartBinding fragmentStartBinding;


    public StartFragment() {
    }


    @Override
    protected StartContract.ViewModel createModel() {
        return new ViewModelProvider(this, new StartViewModelFactory(this)).get(StartViewModel.class);
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
    public void proceedToNextScreen(int button) {

        if (hasHost()&& button == Constants.BUTTON_LOGIN) {
            getFragmentHost().proceedToLoginScreen();
        }

        if (hasHost()&& button == Constants.BUTTON_REGISTER) {
            getFragmentHost().proceedToRegisterScreen();
        }

    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.start_login){
            proceedToNextScreen(Constants.BUTTON_LOGIN);
        }
        if (v.getId()==R.id.start_register){
            proceedToNextScreen(Constants.BUTTON_REGISTER);
        }
    }
}