package ru.navodnikov.denis.locationtracker.app.ui.register;

import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import ru.navodnikov.denis.locationtracker.R;
import ru.navodnikov.denis.locationtracker.abstractions.BaseFragment;
import ru.navodnikov.denis.locationtracker.app.ui.register.infra.RegisterScreenState;
import ru.navodnikov.denis.locationtracker.app.utils.Constants;
import ru.navodnikov.denis.locationtracker.databinding.FragmentRegisterBinding;

import static ru.navodnikov.denis.locationtracker.app.utils.Utils.getTextFromView;


public class RegisterFragment extends BaseFragment<RegisterScreenState, RegisterViewModel, FragmentRegisterBinding> implements RegisterContract.View {


    public RegisterFragment() {
    }

    @Override
    public Class<RegisterViewModel> getViewModelClass() {
        return RegisterViewModel.class;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.getStateObservable().observe(this,this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentBinding = FragmentRegisterBinding.inflate(inflater, container, false);
        View view = fragmentBinding.getRoot();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentBinding.spinnerRegister.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == Constants.LOGIN_EMAIL) {
                    fragmentBinding.emailOrPhoneForRegister.setHint(view.getContext().getResources().getString(R.string.prompt_email));
                    fragmentBinding.emailOrPhoneForRegister.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                    fragmentBinding.passwordForRegister.setVisibility(View.VISIBLE);
                } else if (position == Constants.LOGIN_PHONE) {
                    fragmentBinding.emailOrPhoneForRegister.setHint(view.getContext().getResources().getString(R.string.prompt_phone));
                    fragmentBinding.emailOrPhoneForRegister.setInputType(InputType.TYPE_CLASS_PHONE);
                    fragmentBinding.passwordForRegister.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        fragmentBinding.registerButton.setOnClickListener(v -> {
            if (fragmentBinding.spinnerRegister.getSelectedItemPosition() == Constants.LOGIN_PHONE) {
                viewModel.registerWithPhone(getTextFromView(fragmentBinding.emailOrPhoneForRegister));
            } else {
                viewModel.registerWithEmail(getTextFromView(fragmentBinding.emailOrPhoneForRegister),
                        getTextFromView(fragmentBinding.passwordForRegister));
            }
        });
    }


    @Override
    public void proceedFromRegisterToVerificationScreen() {
        navController.navigate(RegisterFragmentDirections.actionRegisterFragmentToVerificationFragment());
    }

    @Override
    public void proceedFromRegisterToTrackingScreen() {
        navController.navigate(RegisterFragmentDirections.actionRegisterFragmentToTrackingFragment());
    }

    @Override
    public void showLoginFailed(@StringRes Integer errorString) {
        showError(errorString);
    }


    @Override
    public void showErrorEmptyUserEmailOrPhone() {
        fragmentBinding.emailOrPhoneForRegister.setError(getContext().getString(R.string.empty_fild_error));
    }


    @Override
    public void showErrorEmptyUserPassword() {
        fragmentBinding.passwordForRegister.setError(getContext().getString(R.string.empty_fild_error));
    }

    @Override
    public void showErrorShortPassword() {
        fragmentBinding.passwordForRegister.setError(getContext().getString(R.string.invalid_password));
    }

    @Override
    public void showErrorNotUserEmail() {
        fragmentBinding.emailOrPhoneForRegister.setError(getContext().getString(R.string.invalid_user_email));
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