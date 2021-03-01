package ru.navodnikov.denis.locationtracker.app.ui.verification;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.navodnikov.denis.locationtracker.R;
import ru.navodnikov.denis.locationtracker.app.ui.verification.infra.VerificationScreenState;
import ru.navodnikov.denis.locationtracker.databinding.FragmentVerificationBinding;
import ru.navodnikov.denis.locationtracker.abstractions.BaseFragment;


public class VerificationFragment extends BaseFragment<VerificationScreenState, VerificationViewModel, FragmentVerificationBinding> implements VerificationContract.View {


    public VerificationFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.getStateObservable().observe(this,this);
    }

    @Override
    public Class<VerificationViewModel> getViewModelClass() {
        return VerificationViewModel.class;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentBinding = FragmentVerificationBinding.inflate(inflater, container, false);
        View view = fragmentBinding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentBinding.editTextSmsCod.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count == 6) {
                    Log.d("TAG", "sms code");
                    viewModel.verification(s.toString());
                    fragmentBinding.loadingSms.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }


    @Override
    public void hideProgress() {
        fragmentBinding.loadingSms.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        fragmentBinding.loadingSms.setVisibility(View.VISIBLE);
    }

    @Override
    public void showVerificationFailed(int errorString) {
        showError(errorString);
    }

    @Override
    public void proceedFromVerificationToTrackingScreen() {
        navController.navigate(R.id.action_verificationFragment_to_trackingFragment);
    }
}