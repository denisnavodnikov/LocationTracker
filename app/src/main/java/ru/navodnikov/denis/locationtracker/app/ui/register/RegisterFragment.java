package ru.navodnikov.denis.locationtracker.app.ui.register;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseUser;

import ru.navodnikov.denis.locationtracker.R;
import ru.navodnikov.denis.locationtracker.app.TrackerApp;
import ru.navodnikov.denis.locationtracker.app.ui.register.infra.RegisterScreenState;
import ru.navodnikov.denis.locationtracker.databinding.FragmentRegisterBinding;
import ru.navodnikov.denis.locationtracker.mvi.HostedFragment;


public class RegisterFragment extends HostedFragment<RegisterScreenState, RegisterContract.ViewModel, RegisterContract.Host> implements RegisterContract.View, RegisterContract.Router,  View.OnClickListener {

    private FragmentRegisterBinding fragmentRegisterBinding;
    private CoordinatorLayout coordinator;

    public RegisterFragment() {

    }


    @Override
    protected RegisterContract.ViewModel createModel() {
        return new ViewModelProvider(this, new RegisterViewModelFactory(TrackerApp.getInstance().getAppComponent(), this)).get(RegisterViewModel.class);
    }

    @Override
    public void onStart() {
        super.onStart();
        getModel().checkUserAuthorisation();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentRegisterBinding = FragmentRegisterBinding.inflate(inflater, container, false);
        View view = fragmentRegisterBinding.getRoot();
        coordinator = getActivity().findViewById(R.id.coordinator);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentRegisterBinding.registerButton.setOnClickListener(this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentRegisterBinding = null;
    }

    @Override
    public void proceedToNextScreen() {
        if (hasHost()) {
            getFragmentHost().proceedFromRegisterToTrackingScreen();
        }

    }
    @Override
    public void showLoginFailed(@StringRes Integer errorString){
        if (getContext() != null && getContext().getApplicationContext() != null) {

            Snackbar snackbar = Snackbar.make(
                    coordinator,
                    errorString,
                    Snackbar.LENGTH_LONG
            );
            snackbar.show();
        }
    }

    @Override
    public void showError(int action) {
        if(action==RegisterScreenState.EMPTY_USERNAME){
            fragmentRegisterBinding.nameForRegister.setError(getContext().getString(R.string.empty_fild_error));
        }
        if(action==RegisterScreenState.EMPTY_USER_EMAIL){
            fragmentRegisterBinding.emailForRegister.setError(getContext().getString(R.string.empty_fild_error));
        }
        if(action==RegisterScreenState.EMPTY_USER_PHONE){
            fragmentRegisterBinding.phoneForRegister.setError(getContext().getString(R.string.empty_fild_error));
        }
        if(action==RegisterScreenState.EMPTY_PASSWORD){
            fragmentRegisterBinding.passwordForRegister.setError(getContext().getString(R.string.empty_fild_error));
        }
        if(action==RegisterScreenState.SHORT_PASSWORD){
            fragmentRegisterBinding.passwordForRegister.setError(getContext().getString(R.string.invalid_password));
        }
        if(action==RegisterScreenState.NOT_USER_EMAIL){
            fragmentRegisterBinding.passwordForRegister.setError(getContext().getString(R.string.invalid_user_email));
        }

    }

    @Override
    public void showProgress() {
        fragmentRegisterBinding.loading.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        fragmentRegisterBinding.loading.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.register_button){
            String username = fragmentRegisterBinding.nameForRegister.getText().toString().trim();
            String userEmail = fragmentRegisterBinding.emailForRegister.getText().toString().trim();
            String userPhone = fragmentRegisterBinding.phoneForRegister.getText().toString().trim();
            String password = fragmentRegisterBinding.passwordForRegister.getText().toString().trim();
            getModel().register(username, userEmail, userPhone, password);
        }
    }
}