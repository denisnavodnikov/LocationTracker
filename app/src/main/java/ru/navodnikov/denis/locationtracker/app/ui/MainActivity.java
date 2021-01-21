package ru.navodnikov.denis.locationtracker.app.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;

import ru.navodnikov.denis.locationtracker.R;
import ru.navodnikov.denis.locationtracker.app.ui.login.LoginContract;
import ru.navodnikov.denis.locationtracker.app.ui.register.RegisterContract;
import ru.navodnikov.denis.locationtracker.app.ui.start.StartContract;
import ru.navodnikov.denis.locationtracker.app.ui.tracking.TrackingContract;
import ru.navodnikov.denis.locationtracker.mvi.HostActivity;

public class MainActivity extends HostActivity implements LoginContract.Host, RegisterContract.Host, StartContract.Host, TrackingContract.Host {

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        navController = Navigation.findNavController(this, R.id.nav_host);
    }


    @Override
    protected int getFragmentContainerId() {
        return 0;
    }

    @Override
    public void proceedToLoginScreen() {
        navController.navigate(R.id.action_startFragment_to_loginFragment);
    }

    @Override
    public void proceedToRegisterScreen() {
        navController.navigate(R.id.action_startFragment_to_registerFragment);
    }

    @Override
    public void showTrackingScreen() {
        navController.navigate(R.id.action_loginFragment_to_trackingFragment);
    }

    @Override
    public void proceedFromRegisterToTrackingScreen() {
        navController.navigate(R.id.action_registerFragment_to_trackingFragment);
    }

    @Override
    public void proceedToStartScreen() {
        navController.navigate(R.id.action_trackingFragment_to_startFragment);
    }

}