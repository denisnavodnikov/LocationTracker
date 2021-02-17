package ru.navodnikov.denis.locationtracker.app.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import ru.navodnikov.denis.locationtracker.R;
import ru.navodnikov.denis.locationtracker.app.TrackerApp;
import ru.navodnikov.denis.locationtracker.app.ui.login.LoginContract;
import ru.navodnikov.denis.locationtracker.app.ui.register.RegisterContract;
import ru.navodnikov.denis.locationtracker.app.ui.start.StartContract;
import ru.navodnikov.denis.locationtracker.app.ui.tracking.TrackingContract;
import ru.navodnikov.denis.locationtracker.app.ui.verification.VerificationContract;
import ru.navodnikov.denis.locationtracker.models.ActivityHolder;

public class MainActivity extends AppCompatActivity implements LoginContract.Host, RegisterContract.Host, StartContract.Host, TrackingContract.Host, VerificationContract.Host {

    @Inject
    ActivityHolder activityHolder;

    private CoordinatorLayout coordinator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coordinator = findViewById(R.id.coordinator);
        TrackerApp.getComponent().inject(this);
        activityHolder.setActivity(this);

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityHolder.clear();
    }
}