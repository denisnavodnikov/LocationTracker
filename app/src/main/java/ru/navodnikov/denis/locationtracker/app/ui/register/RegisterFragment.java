package ru.navodnikov.denis.locationtracker.app.ui.register;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseUser;

import ru.navodnikov.denis.locationtracker.R;
import ru.navodnikov.denis.locationtracker.app.TrackerApp;
import ru.navodnikov.denis.locationtracker.app.ui.register.infra.RegisterScreenState;
import ru.navodnikov.denis.locationtracker.databinding.FragmentRegisterBinding;
import ru.navodnikov.denis.locationtracker.mvi.HostedFragment;


public class RegisterFragment extends HostedFragment<RegisterScreenState, RegisterContract.ViewModel, RegisterContract.Host> implements RegisterContract.View, RegisterContract.Router,  View.OnClickListener {

    private FragmentRegisterBinding fragmentRegisterBinding;


    public RegisterFragment() {

    }


    @Override
    protected RegisterContract.ViewModel createModel() {
        return new ViewModelProvider(this, new RegisterViewModelFactory(TrackerApp.getInstance().getAppComponent(), this)).get(RegisterViewModel.class);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentRegisterBinding = FragmentRegisterBinding.inflate(inflater, container, false);
        View view = fragmentRegisterBinding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentRegisterBinding.registerButton.setOnClickListener(this);
    }

    private void updateUI(FirebaseUser user) {
//        TODO: обновление интерфейса
        if(user== null){

        }else {
//            Navigation.findNavController(getActivity(), R.id.nav_host).navigate(R.id.action_registerFragment_to_trackingFragment);
        }
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
    public void showError(int error) {
        if(error==RegisterScreenState.USERNAME){
            fragmentRegisterBinding.nameForRegister.setError(getContext().getString(R.string.empty_fild_error));
        }
        if(error==RegisterScreenState.USER_EMAIL){
            fragmentRegisterBinding.emailForRegister.setError(getContext().getString(R.string.empty_fild_error));
        }
        if(error==RegisterScreenState.USER_PHONE){
            fragmentRegisterBinding.phoneForRegister.setError(getContext().getString(R.string.empty_fild_error));
        }
        if(error==RegisterScreenState.PASSWORD){
            fragmentRegisterBinding.passwordForRegister.setError(getContext().getString(R.string.empty_fild_error));
        }

    }

    @Override
//    TODO показать прогресс
    public void showProgress() {
//        if (refreshSwipe != null && !refreshSwipe.isRefreshing()) {
//            refreshSwipe.setRefreshing(true);
//        }
    }

    @Override
    //    TODO спрятать прогресс
    public void hideProgress() {
//        if (refreshSwipe != null && refreshSwipe.isRefreshing()) {
//            refreshSwipe.setRefreshing(false);
//        }
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