package ru.navodnikov.denis.locationtracker.models_impl.repo.network;


import android.util.Log;

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


import java.util.Objects;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import ru.navodnikov.denis.locationtracker.app.utils.Constants;
import ru.navodnikov.denis.locationtracker.models.ActivityHolder;
import ru.navodnikov.denis.locationtracker.models.repo.network.TrackerNetwork;
import ru.navodnikov.denis.locationtracker.models_impl.repo.dao.schemas.UserLocation;


public class TrackerNetworkImpl implements TrackerNetwork {

    private final ActivityHolder activityHolder;

    private final FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private final PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthCredential credential;

    @Inject
    public TrackerNetworkImpl(ActivityHolder activityHolder) {
        this.activityHolder = activityHolder;
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Log.w("TAG", "onVerificationFailed", e);
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
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
    public Single<String> loginWithEmail(String username, String password) {
        return Single.create(emitter -> mAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("log", "signInWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        emitter.onSuccess(Objects.requireNonNull(user).getUid());
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("log", "signInWithEmail:failure", task.getException());
                        emitter.onError(Objects.requireNonNull(task.getException()));
                    }

                }).addOnFailureListener(emitter::onError)
        );

    }

    @Override
    public Completable verifyWithPhoneNumber(String userPhone) {
        return Completable.create(emitter -> {
            try {
                PhoneAuthOptions options =
                        PhoneAuthOptions.newBuilder(mAuth)
                                .setPhoneNumber(userPhone)       // Phone number to verify
                                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                                .setActivity(activityHolder.getActivity())                 // Activity (for callback binding)
                                .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                                .build();
                PhoneAuthProvider.verifyPhoneNumber(options);
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        });

    }

    @Override
    public Single<String> verificationWithSMS(String smsCode) {
        credential = PhoneAuthProvider.getCredential(mVerificationId, smsCode);
        return signInWithPhoneAuthCredential(credential);
    }

    @Override
    public Single<String> signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        return Single.create(emitter -> {
            mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("TAG", "signInWithCredential:success");
                        FirebaseUser user = task.getResult().getUser();
                        emitter.onSuccess(Objects.requireNonNull(user).getUid());
                    } else {
                        // Sign in failed, display a message and update the UI
                        Log.w("TAG", "signInWithCredential:failure", task.getException());
                        emitter.onError(Objects.requireNonNull(task.getException()));

                    }
                }
            }).addOnFailureListener(emitter::onError);
        });
    }


    @Override
    public Completable sendLocation(UserLocation location) {
        return Completable.create(emitter -> {
                    firebaseDatabase.getReference(Constants.DATABASE_PATH)
                            .child(mAuth.getCurrentUser().getUid())
                            .setValue(location).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d("TAG", "upload location: Success");
                            emitter.onComplete();
                        }
                    }).addOnFailureListener(e -> {
                        emitter.onError(e);
                    });
                }
        );
    }

    @Override
    public Single<String> registerWithEmail(String email, String password) {
        return Single.create(emitter -> mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            emitter.onSuccess(Objects.requireNonNull(user).getUid());
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "createUserWithEmail:failure", task.getException());
                            emitter.onError(Objects.requireNonNull(task.getException()));
                        }

                    }
                }).addOnFailureListener(emitter::onError));
    }

    @Override
    public void signOut() {
        mAuth.signOut();
    }

    @Override
    public boolean userIsNotNull() {
        if (mAuth.getCurrentUser() == null) {
            return false;
        } else return true;
    }


}
