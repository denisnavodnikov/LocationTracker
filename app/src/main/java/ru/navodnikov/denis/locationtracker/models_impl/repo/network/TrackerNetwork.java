package ru.navodnikov.denis.locationtracker.models_impl.repo.network;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;


import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Single;
import ru.navodnikov.denis.locationtracker.app.utils.Constants;
import ru.navodnikov.denis.locationtracker.models.location.infra.Result;
import ru.navodnikov.denis.locationtracker.models.repo.network.Network;
import ru.navodnikov.denis.locationtracker.models_impl.repo.dao.schemas.UserLocation;


public class TrackerNetwork implements Network {
    private final FirebaseAuth mAuth;
    private FirebaseDatabase db;
    private final Context context;
    private final PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthCredential credential;


    public TrackerNetwork(Context ctx) {
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        this.context = ctx;
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                mAuth.signInWithCredential(credential).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d("TAG", "onVerificationCompleted:" + credential);

                    } else {
                        Log.w("log", "createUserWithPhone:failure", task.getException());
                    }
                });
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Log.w("TAG", "onVerificationFailed", e);
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    // ...
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    // ...
                }
                // Show a message and update the UI
                // ...
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                Log.d("TAG", "onCodeSent:" + verificationId);
                mVerificationId = verificationId;
                mResendToken = token;
            }
        };
    }


    @Override
    public FirebaseAuth getmAuth() {
        return mAuth;
    }

    @Override
    public FirebaseDatabase getDb() {
        return db;
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public Single<Result<String>> loginWithEmail(String username, String password) {
        return Single.create(emitter -> mAuth.signInWithEmailAndPassword(username, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("log", "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                emitter.onSuccess(new Result<>(user.getUid()));
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("log", "signInWithEmail:failure", task.getException());
                                emitter.onSuccess(new Result<>(task.getException()));
                            }

                        })
//                .addOnFailureListener()
        );
        //TODO: addOnFailureListener

    }

    @Override
    public Single<Object> verifyWithPhoneNumber(String userPhone) {
        return Single.create(emitter -> {
            PhoneAuthOptions options =
                    PhoneAuthOptions.newBuilder(mAuth)
                            .setPhoneNumber(userPhone)       // Phone number to verify
                            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                            .setActivity((Activity) context)                 // Activity (for callback binding)
                            .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                            .build();
            PhoneAuthProvider.verifyPhoneNumber(options);

            // TODO: do return result
        });


    }

    @Override
    public Single<Result<String>> verificationWithSMS(String smsCode) {
        return Single.create(emitter -> {
            credential = PhoneAuthProvider.getCredential(mVerificationId, smsCode);
            mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("TAG", "signInWithCredential:success");
                        FirebaseUser user = task.getResult().getUser();
                        emitter.onSuccess(new Result<>(user.getUid()));
                    } else {
                        // Sign in failed, display a message and update the UI
                        Log.w("TAG", "signInWithCredential:failure", task.getException());
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            // The verification code entered was invalid
                            emitter.onSuccess(new Result<>(task.getException()));
                        }
                    }
                }
            });
        });

    }


    @Override
    public void sendLocation(UserLocation location) {
        db.getReference(Constants.DATABASE_PATH)
                .child(mAuth.getCurrentUser().getUid())
                .setValue(location).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override public void onSuccess(Void aVoid) {
                Log.d("TAG", "upload location: Success");
            }
        });


    }

    @Override
    public Single<Result<String>> registerWithEmail(String email, String password) {
        return Single.create(emitter -> mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            emitter.onSuccess(new Result<>(user.getUid()));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(context, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            emitter.onSuccess(new Result<>(task.getException()));
                        }

                        // ...
                    }
                }));

    }


}
