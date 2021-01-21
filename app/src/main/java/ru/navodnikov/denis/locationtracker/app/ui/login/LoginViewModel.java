package ru.navodnikov.denis.locationtracker.app.ui.login;

import android.text.TextUtils;

import ru.navodnikov.denis.locationtracker.app.ui.login.infra.LoginScreenState;
import ru.navodnikov.denis.locationtracker.models.cache.Cache;
import ru.navodnikov.denis.locationtracker.mvi.MviViewModel;

public class LoginViewModel extends MviViewModel<LoginScreenState> implements LoginContract.ViewModel {
    private final LoginContract.Router router;
    private final Cache cache;

    public LoginViewModel(LoginContract.Router router, Cache cache) {
        this.router = router;
        this.cache = cache;
    }
    //    TODO добавить методы с логикой работы

    public void login(String username, String password) {

        if (TextUtils.isEmpty(username)) {
            postState(LoginScreenState.createErrorInputUsernameState(true));
        }

        if (TextUtils.isEmpty(username)) {
            postState(LoginScreenState.createErrorInputPasswordState(true));
        }


        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            return;
        }

//        mAuth.signInWithEmailAndPassword(username, password)
//                .addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d("log", "signInWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w("log", "signInWithEmail:failure", task.getException());
//                            Toast.makeText(getActivity(), getActivity().getString(R.string.login_failed),
//                                    Toast.LENGTH_SHORT).show();
//
//                            updateUI(null);
//                        }
//
//                        // ...
//                    }
//                });
    }
}
