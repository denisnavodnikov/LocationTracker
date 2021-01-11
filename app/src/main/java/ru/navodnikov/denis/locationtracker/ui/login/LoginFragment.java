package ru.navodnikov.denis.locationtracker.ui.login;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
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
import ru.navodnikov.denis.locationtracker.databinding.FragmentLoginBinding;
import ru.navodnikov.denis.locationtracker.ui.Constants;


public class LoginFragment extends Fragment {
    private FragmentLoginBinding fragmentLoginBinding;
    FirebaseAuth mAuth;

    public LoginFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        fragmentLoginBinding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
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

    public void register() {
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
        mAuth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("log", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {

                            Log.w("log", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getActivity(), getActivity().getString(R.string.register_failed),
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
            Navigation.findNavController(getActivity(), R.id.nav_host).navigate(R.id.action_loginFragment_to_trackingFragment);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentLoginBinding = null;
    }
}