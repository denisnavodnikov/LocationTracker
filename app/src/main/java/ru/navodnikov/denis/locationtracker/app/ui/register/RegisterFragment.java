package ru.navodnikov.denis.locationtracker.app.ui.register;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import ru.navodnikov.denis.locationtracker.R;
import ru.navodnikov.denis.locationtracker.app.utils.Constants;
import ru.navodnikov.denis.locationtracker.app.ui.register.infra.RegisterScreenState;
import ru.navodnikov.denis.locationtracker.databinding.FragmentRegisterBinding;
import ru.navodnikov.denis.locationtracker.viewmodel.BaseFragment;

import static ru.navodnikov.denis.locationtracker.app.utils.Utils.getTextFromView;


public class RegisterFragment extends BaseFragment<RegisterScreenState, RegisterViewModel> implements RegisterContract.View {


    private FragmentRegisterBinding fragmentRegisterBinding;
    private NavController navController;

    public RegisterFragment() {
    }

    @Override
    public Class<RegisterViewModel> getViewModel() {
        return RegisterViewModel.class;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model.getStateObservable().observe(this,this);
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
        fragmentRegisterBinding.spinnerRegister.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == Constants.LOGIN_EMAIL) {
                    fragmentRegisterBinding.emailOrPhoneForRegister.setHint(view.getContext().getResources().getString(R.string.prompt_email));
                    fragmentRegisterBinding.emailOrPhoneForRegister.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                    fragmentRegisterBinding.passwordForRegister.setVisibility(View.VISIBLE);
                } else if (position == Constants.LOGIN_PHONE) {
                    fragmentRegisterBinding.emailOrPhoneForRegister.setHint(view.getContext().getResources().getString(R.string.prompt_phone));
                    fragmentRegisterBinding.emailOrPhoneForRegister.setInputType(InputType.TYPE_CLASS_PHONE);
                    fragmentRegisterBinding.passwordForRegister.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        fragmentRegisterBinding.registerButton.setOnClickListener(v -> {
            if (fragmentRegisterBinding.spinnerRegister.getSelectedItemPosition() == Constants.LOGIN_PHONE) {
                model.registerWithPhone(getTextFromView(fragmentRegisterBinding.emailOrPhoneForRegister));
            } else {
                model.registerWithEmail(getTextFromView(fragmentRegisterBinding.emailOrPhoneForRegister),
                        getTextFromView(fragmentRegisterBinding.passwordForRegister));
            }
        });
        navController = Navigation.findNavController(getActivity(), R.id.nav_host);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentRegisterBinding = null;
    }

    @Override
    public void proceedFromRegisterToVerificationScreen() {
        navController.navigate(R.id.action_registerFragment_to_verificationFragment);
    }

    @Override
    public void proceedFromRegisterToTrackingScreen() {
        navController.navigate(R.id.action_registerFragment_to_trackingFragment);
    }

    @Override
    public void showLoginFailed(@StringRes Integer errorString) {
        showError(errorString);
    }


    @Override
    public void showErrorEmptyUserEmailOrPhone() {
        fragmentRegisterBinding.emailOrPhoneForRegister.setError(getContext().getString(R.string.empty_fild_error));
    }


    @Override
    public void showErrorEmptyUserPassword() {
        fragmentRegisterBinding.passwordForRegister.setError(getContext().getString(R.string.empty_fild_error));
    }

    @Override
    public void showErrorShortPassword() {
        fragmentRegisterBinding.passwordForRegister.setError(getContext().getString(R.string.invalid_password));
    }

    @Override
    public void showErrorNotUserEmail() {
        fragmentRegisterBinding.emailOrPhoneForRegister.setError(getContext().getString(R.string.invalid_user_email));
    }

    @Override
    public void showProgress() {
        fragmentRegisterBinding.loading.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        fragmentRegisterBinding.loading.setVisibility(View.GONE);
    }

}