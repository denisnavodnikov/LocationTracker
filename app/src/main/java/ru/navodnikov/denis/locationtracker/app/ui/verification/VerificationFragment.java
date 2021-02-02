package ru.navodnikov.denis.locationtracker.app.ui.verification;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.navodnikov.denis.locationtracker.R;
import ru.navodnikov.denis.locationtracker.app.TrackerApp;
import ru.navodnikov.denis.locationtracker.app.ui.verification.infra.VerificationScreenState;
import ru.navodnikov.denis.locationtracker.databinding.FragmentVerificationBinding;
import ru.navodnikov.denis.locationtracker.mvi.HostedFragment;


public class VerificationFragment extends HostedFragment<VerificationScreenState, VerificationContract.ViewModel, VerificationContract.Host> implements VerificationContract.View, VerificationContract.Router {

    private FragmentVerificationBinding fragmentVerificationBinding;


    public VerificationFragment() {

    }

    @Override
    protected VerificationContract.ViewModel createModel() {
        return new ViewModelProvider(this, new VerificationViewModelFactory(TrackerApp.getInstance().getAppComponent(), this)).get(VerificationViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentVerificationBinding = FragmentVerificationBinding.inflate(inflater, container, false);
        View view = fragmentVerificationBinding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentVerificationBinding.editTextSmsCod.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(start==6){
                    getModel().verification(s.toString());
                    fragmentVerificationBinding.loadingSms.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public void proceedToNextScreen() {
        if (hasHost()) {
            getFragmentHost().proceedFromVerificationToTrackingScreen();
        }
    }

    @Override
    public void hideProgress() {
        fragmentVerificationBinding.loadingSms.setVisibility(View.GONE);
    }

    @Override
    public void showVerificationFailed(int errorString) {
        if (hasHost()) {
            getFragmentHost().showError(errorString);
        }
    }
}