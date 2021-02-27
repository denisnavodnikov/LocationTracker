package ru.navodnikov.denis.locationtracker.app.ui.tracking;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


import ru.navodnikov.denis.locationtracker.R;
import ru.navodnikov.denis.locationtracker.app.bg.ForegroundService;
import ru.navodnikov.denis.locationtracker.app.ui.tracking.infra.TrackingScreenState;
import ru.navodnikov.denis.locationtracker.databinding.FragmentTrackingBinding;
import ru.navodnikov.denis.locationtracker.viewmodel.BaseFragment;

import static ru.navodnikov.denis.locationtracker.app.utils.Constants.REQUEST_LOCATION;


public class TrackingFragment extends BaseFragment<TrackingScreenState, TrackingViewModel> implements TrackingContract.View {


    private FragmentTrackingBinding fragmentTrackingBinding;
    private NavController navController;

    public TrackingFragment() {
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model.getStateObservable().observe(this,this);
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
    public Class<TrackingViewModel> getViewModel() {
        return TrackingViewModel.class;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentTrackingBinding.buttonLogOut.setOnClickListener(v -> getModel().logOut());
        fragmentTrackingBinding.buttonStartTracking.setOnClickListener(v -> getModel().startTracking());
        fragmentTrackingBinding.buttonStopTracking.setOnClickListener(v -> getModel().stopTracking());
        navController = Navigation.findNavController(getActivity(), R.id.nav_host);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentTrackingBinding = null;
    }


    @Override
    public void showError(int error) {
        showError(error);
    }

    @Override
    public void showMassage(int massage) {
        fragmentTrackingBinding.textViewInfo.setText(massage);
    }

    @Override
    public void proceedToStartScreen() {
        navController.navigate(R.id.action_trackingFragment_to_startFragment);
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
                showError(R.string.permissions_denied);
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