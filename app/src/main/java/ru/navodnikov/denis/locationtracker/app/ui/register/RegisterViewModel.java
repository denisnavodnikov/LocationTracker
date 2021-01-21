package ru.navodnikov.denis.locationtracker.app.ui.register;

import android.text.TextUtils;

import ru.navodnikov.denis.locationtracker.app.ui.Constants;
import ru.navodnikov.denis.locationtracker.app.ui.register.infra.RegisterScreenState;
import ru.navodnikov.denis.locationtracker.models.cache.Cache;
import ru.navodnikov.denis.locationtracker.mvi.MviViewModel;

public class RegisterViewModel extends MviViewModel<RegisterScreenState> implements RegisterContract.ViewModel{
    private final RegisterContract.Router router;
    private final Cache cache;

    public RegisterViewModel(RegisterContract.Router router, Cache cache) {
        this.router = router;
        this.cache = cache;
    }
//    TODO добавить методы с логикой работы

    public void register(String username, String userEmail, String userPhone, String password) {
        if (TextUtils.isEmpty(username)) {
            RegisterScreenState.createErrorInputUsernameState(true);
        }
        if (TextUtils.isEmpty(userEmail)) {
            RegisterScreenState.createErrorInputUserEmailState(true);
        }
        if (TextUtils.isEmpty(userPhone)) {
            RegisterScreenState.createErrorInputUserPhoneState(true);
        }
        if (TextUtils.isEmpty(password)) {
            RegisterScreenState.createErrorInputPasswordState(true);
        }
        if(TextUtils.isEmpty(username)||TextUtils.isEmpty(userEmail)||TextUtils.isEmpty(userPhone)||TextUtils.isEmpty(password)){
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
//        mAuth.createUserWithEmailAndPassword(userEmail, password)
//                .addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            Log.d("log", "createUserWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);
//                        } else {
//
//                            Log.w("log", "createUserWithEmail:failure", task.getException());
//                            Toast.makeText(getActivity(), getActivity().getString(R.string.register_failed),
//                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
//                        }
//
//                        // ...
//                    }
//                });
    }
}
