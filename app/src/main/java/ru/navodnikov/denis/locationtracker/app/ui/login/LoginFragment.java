package ru.navodnikov.denis.locationtracker.app.ui.login;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseUser;

import ru.navodnikov.denis.locationtracker.R;
import ru.navodnikov.denis.locationtracker.app.TrackerApp;
import ru.navodnikov.denis.locationtracker.app.ui.login.infra.LoginScreenState;
import ru.navodnikov.denis.locationtracker.databinding.FragmentLoginBinding;
import ru.navodnikov.denis.locationtracker.app.ui.Constants;
import ru.navodnikov.denis.locationtracker.mvi.HostedFragment;


public class LoginFragment extends HostedFragment<LoginScreenState, LoginContract.ViewModel, LoginContract.Host> implements LoginContract.View, LoginContract.Router, View.OnClickListener {
    private FragmentLoginBinding fragmentLoginBinding;
    private CoordinatorLayout coordinator;

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
        coordinator = getActivity().findViewById(R.id.coordinator);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentLoginBinding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == Constants.POSITION_EMAIL) {
                    fragmentLoginBinding.emailOrPhone.setHint(view.getContext().getResources().getString(R.string.prompt_email));
                } else if (position == Constants.POSITION_PHONE) {
                    fragmentLoginBinding.emailOrPhone.setHint(view.getContext().getResources().getString(R.string.prompt_phone));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        fragmentLoginBinding.loginButton.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();

//         Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
    }

//    private void updateUiWithUser(LoggedInUserView model) {
//        String welcome = getString(R.string.welcome) + model.getDisplayName();
//        // TODO : initiate successful logged in experience
//        if (getContext() != null && getContext().getApplicationContext() != null) {
//            Toast.makeText(getContext().getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
//        }
//    }

    private void showLoginFailed(@StringRes Integer errorString) {
        if (getContext() != null && getContext().getApplicationContext() != null) {

            Snackbar snackbar = Snackbar.make(
                    coordinator,
                    errorString,
                    Snackbar.LENGTH_LONG
            );
            snackbar.show();
        }
    }


    private void updateUI(FirebaseUser user) {
//        TODO: обновление интерфейса
        if (user == null) {

        } else {
//            Navigation.findNavController(getActivity(), R.id.nav_host).navigate(R.id.action_loginFragment_to_trackingFragment);
        }
    }


    @Override
    public void proceedToNextScreen() {
        if (hasHost()) {
            getFragmentHost().showTrackingScreen();
        }
    }


    @Override
    public void showErrorEmptyUserName(int error) {
        fragmentLoginBinding.emailOrPhone.setError(getContext().getString(error));
    }

    @Override
    public void showErrorEmptyPassword(int error) {
        fragmentLoginBinding.password.setError(getContext().getString(error));
    }

    @Override
//    TODO показать прогресс
    public void showProgress() {
        fragmentLoginBinding.loading.setVisibility(View.INVISIBLE);
    }

    @Override
    //    TODO спрятать прогресс
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
        if (v.getId() == R.id.login_button) {
            String username = fragmentLoginBinding.emailOrPhone.getText().toString().trim();
            String password = fragmentLoginBinding.password.getText().toString().trim();
            getModel().login(username, password);
        }
    }
}