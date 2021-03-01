package ru.navodnikov.denis.locationtracker.app.ui.login;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

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
import ru.navodnikov.denis.locationtracker.abstractions.BaseFragment;

import static ru.navodnikov.denis.locationtracker.app.utils.Utils.getTextFromView;


public class LoginFragment extends BaseFragment<LoginScreenState, LoginViewModel, FragmentLoginBinding> implements LoginContract.View {

    public LoginFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.getStateObservable().observe(this,this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        fragmentBinding = FragmentLoginBinding.inflate(inflater, container, false);
        View view = fragmentBinding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentBinding.spinnerLogin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == Constants.LOGIN_EMAIL) {
                    fragmentBinding.emailOrPhoneForLogin.setHint(view.getContext().getResources().getString(R.string.prompt_email));
                    fragmentBinding.emailOrPhoneForLogin.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                    fragmentBinding.passwordForLogin.setVisibility(View.VISIBLE);
                } else if (position == Constants.LOGIN_PHONE) {
                    fragmentBinding.emailOrPhoneForLogin.setHint(view.getContext().getResources().getString(R.string.prompt_phone));
                    fragmentBinding.emailOrPhoneForLogin.setInputType(InputType.TYPE_CLASS_PHONE);
                    fragmentBinding.passwordForLogin.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        fragmentBinding.loginButton.setOnClickListener(v -> {
            if (fragmentBinding.spinnerLogin.getSelectedItemPosition() == Constants.LOGIN_PHONE) {
                viewModel.loginWithPhone(getTextFromView(fragmentBinding.emailOrPhoneForLogin));
            } else {
                viewModel.loginWithEmail(getTextFromView(fragmentBinding.emailOrPhoneForLogin),
                        getTextFromView(fragmentBinding.passwordForLogin));
            }
        });

    }
    @Override
    public Class<LoginViewModel> getViewModelClass() {
        return LoginViewModel.class;
    }

    @Override
    public void showLoginFailed(@StringRes Integer errorString) {
        showError(errorString);
    }


    @Override
    public void showErrorEmptyUserName() {
        fragmentBinding.emailOrPhoneForLogin.setError(getContext().getString(R.string.empty_fild_error));
    }

    @Override
    public void proceedFromLoginToVerificationScreen() {
        navController.navigate(R.id.action_loginFragment_to_verificationFragment);
    }

    @Override
    public void proceedFromLoginToTrackingScreen() {
        navController.navigate(R.id.action_loginFragment_to_trackingFragment);
    }


    @Override
    public void showErrorEmptyPassword() {
        fragmentBinding.passwordForLogin.setError(getContext().getString(R.string.empty_fild_error));
    }

    @Override
    public void showProgress() {
        fragmentBinding.loading.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        fragmentBinding.loading.setVisibility(View.GONE);
    }



}