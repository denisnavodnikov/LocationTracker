package ru.navodnikov.denis.locationtracker.app.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import ru.navodnikov.denis.locationtracker.R;
import ru.navodnikov.denis.locationtracker.app.ui.login.LoginContract;
import ru.navodnikov.denis.locationtracker.app.ui.register.RegisterContract;
import ru.navodnikov.denis.locationtracker.app.ui.start.StartContract;
import ru.navodnikov.denis.locationtracker.app.ui.tracking.TrackingContract;
import ru.navodnikov.denis.locationtracker.app.ui.verification.VerificationContract;

public class MainActivity extends AppCompatActivity implements LoginContract.Host, RegisterContract.Host, StartContract.Host, TrackingContract.Host, VerificationContract.Host {

    private CoordinatorLayout coordinator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coordinator = findViewById(R.id.coordinator);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }

    @Override
    public void showError(int error) {
        Snackbar snackbar = Snackbar.make(
                coordinator,
                getString(error),
                Snackbar.LENGTH_LONG
        );
        snackbar.show();
    }


}