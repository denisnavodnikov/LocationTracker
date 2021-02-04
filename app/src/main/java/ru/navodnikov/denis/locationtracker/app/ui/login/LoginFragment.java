package ru.navodnikov.denis.locationtracker.app.ui.login;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import ru.navodnikov.denis.locationtracker.R;
import ru.navodnikov.denis.locationtracker.app.TrackerApp;
import ru.navodnikov.denis.locationtracker.app.ui.login.infra.LoginScreenState;
import ru.navodnikov.denis.locationtracker.databinding.FragmentLoginBinding;
import ru.navodnikov.denis.locationtracker.app.ui.Constants;
import ru.navodnikov.denis.locationtracker.mvi.HostedFragment;


public class LoginFragment extends HostedFragment<LoginScreenState, LoginContract.ViewModel, LoginContract.Host> implements LoginContract.View, LoginContract.Router, View.OnClickListener {
    private FragmentLoginBinding fragmentLoginBinding;

    public LoginFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected LoginContract.ViewModel createModel() {
        return new ViewModelProvider(this, new LoginViewModelFactory(TrackerApp.getInstance().getAppComponent(), this)).get(LoginViewModel.class);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        fragmentLoginBinding = FragmentLoginBinding.inflate(inflater, container, false);
        View view = fragmentLoginBinding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentLoginBinding.spinnerLogin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == Constants.LOGIN_EMAIL) {
                    fragmentLoginBinding.emailOrPhoneForLogin.setHint(view.getContext().getResources().getString(R.string.prompt_email));
                    fragmentLoginBinding.emailOrPhoneForLogin.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                    fragmentLoginBinding.passwordForLogin.setVisibility(View.VISIBLE);
                } else if (position == Constants.LOGIN_PHONE) {
                    fragmentLoginBinding.emailOrPhoneForLogin.setHint(view.getContext().getResources().getString(R.string.prompt_phone));
                    fragmentLoginBinding.emailOrPhoneForLogin.setInputType(InputType.TYPE_CLASS_PHONE);
                    fragmentLoginBinding.passwordForLogin.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        fragmentLoginBinding.loginButton.setOnClickListener(this);
    }


    @Override
    public void showLoginFailed(@StringRes Integer errorString) {
        if (hasHost()) {
            getFragmentHost().showError(errorString);
        }
    }

    @Override
    public void proceedToVerificationScreen() {
        if (hasHost()) {
            getFragmentHost().proceedFromLoginToVerificationScreen();
        }
    }

    @Override
    public void proceedToTrackingScreen() {
        if (hasHost()) {
            getFragmentHost().proceedFromLoginToTrackingScreen();
        }
    }


    @Override
    public void showErrorEmptyUserName() {
        fragmentLoginBinding.emailOrPhoneForLogin.setError(getContext().getString(R.string.empty_fild_error));
    }


    @Override
    public void showErrorEmptyPassword() {
        fragmentLoginBinding.passwordForLogin.setError(getContext().getString(R.string.empty_fild_error));
    }

    @Override
    public void showProgress() {
        fragmentLoginBinding.loading.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        fragmentLoginBinding.loading.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentLoginBinding = null;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login_button && fragmentLoginBinding.spinnerLogin.getSelectedItemPosition() == Constants.LOGIN_EMAIL) {
            String emailOrPhone = fragmentLoginBinding.emailOrPhoneForLogin.getText().toString().trim();
            String password = fragmentLoginBinding.passwordForLogin.getText().toString().trim();
            getModel().loginWithEmail(emailOrPhone, password);
        } else if(v.getId() == R.id.login_button && fragmentLoginBinding.spinnerLogin.getSelectedItemPosition() == Constants.LOGIN_PHONE){
            String emailOrPhone = fragmentLoginBinding.emailOrPhoneForLogin.getText().toString().trim();
            getModel().loginWithPhone(emailOrPhone);
        }
    }
}