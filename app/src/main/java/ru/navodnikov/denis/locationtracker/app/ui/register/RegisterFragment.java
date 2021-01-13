package ru.navodnikov.denis.locationtracker.app.ui.register;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ru.navodnikov.denis.locationtracker.R;
import ru.navodnikov.denis.locationtracker.app.ui.Constants;
import ru.navodnikov.denis.locationtracker.databinding.FragmentLoginBinding;
import ru.navodnikov.denis.locationtracker.databinding.FragmentRegisterBinding;


public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding fragmentRegisterBinding;
    FirebaseAuth mAuth;


    public RegisterFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentRegisterBinding = FragmentRegisterBinding.inflate(inflater, container, false);
        View view = fragmentRegisterBinding.getRoot();
        return view;
    }

    public void register() {
        String username = fragmentRegisterBinding.nameForRegister.getText().toString().trim();
        String userEmail = fragmentRegisterBinding.emailForRegister.getText().toString().trim();
        String userPhone = fragmentRegisterBinding.phoneForRegister.getText().toString().trim();
        String password = fragmentRegisterBinding.passwordForRegister.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
//            TODO: написать проверку
//            return;
        }
        if (TextUtils.isEmpty(password)) {
//            TODO: написать проверку
            return;
        }
        if (Constants.ZERO == username) {
//            TODO: написать проверку
//            return;
        }

        if (Constants.ZERO == password) {
//            TODO: написать проверку
            return;
        }
        mAuth.createUserWithEmailAndPassword(userEmail, password)
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
            Navigation.findNavController(getActivity(), R.id.nav_host).navigate(R.id.action_registerFragment_to_trackingFragment);
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentRegisterBinding = null;
    }
}