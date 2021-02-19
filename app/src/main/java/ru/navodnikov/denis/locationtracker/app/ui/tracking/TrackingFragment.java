package ru.navodnikov.denis.locationtracker.app.ui.tracking;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import ru.navodnikov.denis.locationtracker.R;
import ru.navodnikov.denis.locationtracker.app.bg.ForegroundService;
import ru.navodnikov.denis.locationtracker.app.ui.MainActivity;
import ru.navodnikov.denis.locationtracker.app.ui.tracking.infra.TrackingScreenState;
import ru.navodnikov.denis.locationtracker.databinding.FragmentTrackingBinding;
import ru.navodnikov.denis.locationtracker.mvi.HostedFragment;

import static ru.navodnikov.denis.locationtracker.app.utils.Constants.REQUEST_LOCATION;


public class TrackingFragment extends HostedFragment<TrackingScreenState, TrackingContract.ViewModel, TrackingContract.Host> implements TrackingContract.View, View.OnClickListener {

    private FragmentTrackingBinding fragmentTrackingBinding;
    private NavController navController;

    public TrackingFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        fragmentTrackingBinding = FragmentTrackingBinding.inflate(inflater, container, false);
        View view = fragmentTrackingBinding.getRoot();
        return view;

    }

    @Override
    protected TrackingContract.ViewModel createModel() {
        return new ViewModelProvider(this, new TrackingViewModelFactory()).get(TrackingViewModel.class);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentTrackingBinding.buttonLogOut.setOnClickListener(this);
        fragmentTrackingBinding.buttonStartTracking.setOnClickListener(this);
        fragmentTrackingBinding.buttonStopTracking.setOnClickListener(this);
        navController = Navigation.findNavController(getActivity(), R.id.nav_host);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentTrackingBinding = null;
    }


    @Override
    public void showError(int error) {
        if (hasHost()) {
            getFragmentHost().showError(error);
        }
    }

    @Override
    public void showMassage(int massage) {
        fragmentTrackingBinding.textViewInfo.setText(massage);
    }

    @Override
    public void proceedToStartScreen() {
        FirebaseAuth.getInstance().signOut();
        navController.navigate(R.id.action_trackingFragment_to_startFragment);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_log_out) {
            getModel().logOut();
        } else if (v.getId() == R.id.button_start_tracking) {
            getModel().startTracking();
        } else if (v.getId() == R.id.button_stop_tracking) {
            getModel().stopTracking();
        }
    }

    @Override
    public void permissionRequest() {
        if (ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_LOCATION);

        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        Log.i("TAG", "onRequestPermissionResult");
        if (requestCode == REQUEST_LOCATION) {
            if (grantResults.length <= 0) {
                // If user interaction was interrupted, the permission request is cancelled and you
                // receive empty arrays.
                Log.i("TAG", "User interaction was cancelled.");
            } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission was granted.
                getModel().setPermissionChecked(true);
            } else {
                // Permission denied.
                getFragmentHost().showError(R.string.permissions_denied);
            }
        }
    }

    @Override
    public void startService() {
        Intent startServiceIntent = new Intent(getActivity(), ForegroundService.class);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            requireContext().startForegroundService(startServiceIntent);
        } else {
            requireContext().startService(startServiceIntent);
        }

    }

    @Override
    public void stopService() {
        Intent serviceIntent = new Intent(getActivity(), ForegroundService.class);
        requireContext().stopService(serviceIntent);
    }
}