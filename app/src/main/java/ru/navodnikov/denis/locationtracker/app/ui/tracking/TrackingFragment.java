package ru.navodnikov.denis.locationtracker.app.ui.tracking;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;


import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import ru.navodnikov.denis.locationtracker.R;
import ru.navodnikov.denis.locationtracker.app.TrackerApp;
import ru.navodnikov.denis.locationtracker.app.ui.tracking.infra.TrackingScreenState;
import ru.navodnikov.denis.locationtracker.databinding.FragmentTrackingBinding;
import ru.navodnikov.denis.locationtracker.mvi.HostedFragment;


public class TrackingFragment extends HostedFragment<TrackingScreenState, TrackingContract.ViewModel, TrackingContract.Host> implements TrackingContract.View, TrackingContract.Router,  View.OnClickListener {

    private FragmentTrackingBinding fragmentTrackingBinding;
    private FusedLocationProviderClient fusedLocationClient;
    FirebaseFirestore db;

    public TrackingFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        fragmentTrackingBinding = FragmentTrackingBinding.inflate(inflater, container, false);
        View view = fragmentTrackingBinding.getRoot();
        return view;

    }

    @Override
    protected TrackingContract.ViewModel createModel() {
        return new ViewModelProvider(this, new TrackingViewModelFactory(TrackerApp.getInstance().getAppComponent(), this)).get(TrackingViewModel.class);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = FirebaseFirestore.getInstance();
        fragmentTrackingBinding.buttonLogOut.setOnClickListener(this);
        fragmentTrackingBinding.buttonStartTracking.setOnClickListener(this);
        fragmentTrackingBinding.buttonStopTracking.setOnClickListener(this);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentTrackingBinding = null;
    }

    public void signOut(){
//        TODO перенести proceedToNextScreen()
    }

    @Override
    public void proceedToNextScreen() {
        FirebaseAuth.getInstance().signOut();
        if (hasHost()) {
            getFragmentHost().proceedToStartScreen();
        }
    }

    @Override
    public void launchWorker() {
        if (hasHost()) {
//            TODO здесь происходит старт работника, который отправляет местоположение в фоне
//            final WorkRequest uploadWorkRequest = new OneTimeWorkRequest.Builder(UploadWorker.class).build();
//            WorkManager.getInstance(getContext()).enqueue(uploadWorkRequest);
        }
    }

    @Override
    public void showError(int error) {
//        TODO показ сообщения об ошибке
        if (hasHost()) {
//            getFragmentHost().showError(error);
        }
    }

    @Override
    public void showMassage(int massage) {
        fragmentTrackingBinding.textViewInfo.setText(massage);
    }


    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.button_log_out){
            getModel().logOut();
        }
        else if(v.getId()==R.id.button_start_tracking){
            getModel().startTracking();
        }
        else if(v.getId()==R.id.button_stop_tracking){
            getModel().stopTracking();
        }
    }
}