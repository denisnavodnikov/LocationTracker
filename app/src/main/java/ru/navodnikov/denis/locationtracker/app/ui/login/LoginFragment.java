package ru.navodnikov.denis.locationtracker.app.ui.login;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import ru.navodnikov.denis.locationtracker.R;
import ru.navodnikov.denis.locationtracker.app.ui.login.infra.LoginScreenState;
import ru.navodnikov.denis.locationtracker.databinding.FragmentLoginBinding;
import ru.navodnikov.denis.locationtracker.app.utils.Constants;
import ru.navodnikov.denis.locationtracker.mvi.HostedFragment;

import static ru.navodnikov.denis.locationtracker.app.utils.Utils.getTextFromView;


public class LoginFragment extends HostedFragment<LoginScreenState, LoginContract.ViewModel, LoginContract.Host> implements LoginContract.View, View.OnClickListener {
    private FragmentLoginBinding fragmentLoginBinding;

    public LoginFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected LoginContract.ViewModel createModel() {
        return new ViewModelProvider(this, new LoginViewModelFactory()).get(LoginViewModel.class);
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
    public void showErrorEmptyUserName() {
        fragmentLoginBinding.emailOrPhoneForLogin.setError(getContext().getString(R.string.empty_fild_error));
    }

    @Override
    public void proceedFromLoginToVerificationScreen() {
        NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_verificationFragment);
    }

    @Override
    public void proceedFromLoginToTrackingScreen() {
        NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_trackingFragment);
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
        if (v.getId() == R.id.login_button && fragmentLoginBinding.spinnerLogin.getSelectedItemPosition() == Constants.LOGIN_PHONE) {
            getModel().loginWithPhone(getTextFromView(fragmentLoginBinding.emailOrPhoneForLogin));
        } else {
            getModel().loginWithEmail(getTextFromView(fragmentLoginBinding.emailOrPhoneForLogin),
                    getTextFromView(fragmentLoginBinding.passwordForLogin));
        }
    }
}