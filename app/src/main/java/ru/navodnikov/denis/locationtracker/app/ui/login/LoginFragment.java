package ru.navodnikov.denis.locationtracker.app.ui.login;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ru.navodnikov.denis.locationtracker.R;
import ru.navodnikov.denis.locationtracker.app.TrackerApp;
import ru.navodnikov.denis.locationtracker.app.ui.register.RegisterViewModel;
import ru.navodnikov.denis.locationtracker.app.ui.register.RegisterViewModelFactory;
import ru.navodnikov.denis.locationtracker.databinding.FragmentLoginBinding;
import ru.navodnikov.denis.locationtracker.app.ui.Constants;
import ru.navodnikov.denis.locationtracker.mvi.HostedFragment;


public class LoginFragment extends HostedFragment<LoginScreenState, LoginContract.ViewModel, LoginContract.Host> implements LoginContract.View, LoginContract.Router {
    private FragmentLoginBinding fragmentLoginBinding;
    FirebaseAuth mAuth;

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
                mAuth = FirebaseAuth.getInstance();
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


        fragmentLoginBinding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();

//         Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
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
            Toast.makeText(
                    getContext().getApplicationContext(),
                    errorString,
                    Toast.LENGTH_LONG).show();
        }
    }


    public void login() {
        String username = fragmentLoginBinding.emailOrPhone.getText().toString().trim();
        String password = fragmentLoginBinding.password.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
//            TODO: написать проверку
            return;
        }
        if (TextUtils.isEmpty(password)) {
//            TODO: написать проверку
            return;
        }
        if (Constants.ZERO == username) {
//            TODO: написать проверку
            return;
        }

        if (Constants.ZERO == password) {
//            TODO: написать проверку
            return;
        }
        mAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("log", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("log", "signInWithEmail:failure", task.getException());
                            Toast.makeText(getActivity(), getActivity().getString(R.string.login_failed),
                                    Toast.LENGTH_SHORT).show();

                            updateUI(null);
                        }

                        // ...
                    }
                });
    }



    private void updateUI(FirebaseUser user) {
//        TODO: обновление интерфейса
        if(user== null){

        }else {
//            Navigation.findNavController(getActivity(), R.id.nav_host).navigate(R.id.action_loginFragment_to_trackingFragment);
        }
    }



    @Override
    public void proceedToNextScreen() {
        if (hasHost()) {
            getFragmentHost().proceedFromLoginToTrackingScreen();
        }
    }

    @Override
    public void launchWorker() {
        if (hasHost()) {
//            TODO здесь происходит старт работника, который логинится в фоне
//            final WorkRequest uploadWorkRequest = new OneTimeWorkRequest.Builder(UploadWorker.class).build();
//            WorkManager.getInstance(getContext()).enqueue(uploadWorkRequest);
        }
    }

    @Override
    public void showError(Throwable error) {
//        TODO показ сообщения об ошибке
        if (hasHost()) {
//            getFragmentHost().showError(error);
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
    public void onDestroyView() {
        super.onDestroyView();
        fragmentLoginBinding = null;
    }
}