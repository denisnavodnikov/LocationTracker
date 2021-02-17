package ru.navodnikov.denis.locationtracker.app.ui.verification;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.navodnikov.denis.locationtracker.R;
import ru.navodnikov.denis.locationtracker.app.ui.verification.infra.VerificationScreenState;
import ru.navodnikov.denis.locationtracker.databinding.FragmentVerificationBinding;
import ru.navodnikov.denis.locationtracker.mvi.HostedFragment;


public class VerificationFragment extends HostedFragment<VerificationScreenState, VerificationContract.ViewModel, VerificationContract.Host> implements VerificationContract.View {

    private FragmentVerificationBinding fragmentVerificationBinding;
    private NavController navController;

    public VerificationFragment() {

    }

    @Override
    protected VerificationContract.ViewModel createModel() {
        return new ViewModelProvider(this, new VerificationViewModelFactory()).get(VerificationViewModel.class);
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
                if(count==6){
                    Log.d("TAG", "sms code");
                    getModel().verification(s.toString());
                    fragmentVerificationBinding.loadingSms.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        navController = Navigation.findNavController(getActivity(), R.id.nav_host);
    }


    @Override
    public void hideProgress() {
        fragmentVerificationBinding.loadingSms.setVisibility(View.GONE);
    }
    @Override
    public void showProgress() {
        fragmentVerificationBinding.loadingSms.setVisibility(View.VISIBLE);
    }

    @Override
    public void showVerificationFailed(int errorString) {
        if (hasHost()) {
            getFragmentHost().showError(errorString);
        }
    }

    @Override
    public void proceedFromVerificationToTrackingScreen() {
        navController.navigate(R.id.action_verificationFragment_to_trackingFragment);
    }
}