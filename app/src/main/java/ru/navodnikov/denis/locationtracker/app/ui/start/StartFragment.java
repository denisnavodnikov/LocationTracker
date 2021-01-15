package ru.navodnikov.denis.locationtracker.app.ui.start;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.navodnikov.denis.locationtracker.R;
import ru.navodnikov.denis.locationtracker.app.TrackerApp;
import ru.navodnikov.denis.locationtracker.app.ui.tracking.TrackingContract;
import ru.navodnikov.denis.locationtracker.databinding.FragmentLoginBinding;
import ru.navodnikov.denis.locationtracker.databinding.FragmentStartBinding;
import ru.navodnikov.denis.locationtracker.mvi.HostedFragment;


public class StartFragment  extends HostedFragment<StartScreenState, StartContract.ViewModel, StartContract.Host> implements StartContract.View, StartContract.Router{
    private FragmentStartBinding fragmentStartBinding;


    public StartFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected StartContract.ViewModel createModel() {
        return new ViewModelProvider(this, new StartViewModelFactory(TrackerApp.getInstance().getAppComponent(), this)).get(StartViewModel.class);
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
        fragmentStartBinding.startLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Navigation.findNavController(v).navigate(R.id.action_startFragment_to_loginFragment);
            }
        });
        fragmentStartBinding.startRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Navigation.findNavController(v).navigate(R.id.action_startFragment_to_registerFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentStartBinding = null;
    }


    @Override
    public void proceedToNextScreen() {
        // TODO написать условие при котором происходит переход на определенный экран

        if (hasHost()) {
            getFragmentHost().proceedToLoginScreen();
        }

        // или

//        if (hasHost()) {
//            getFragmentHost().proceedToRegisterScreen();
//        }

    }



}