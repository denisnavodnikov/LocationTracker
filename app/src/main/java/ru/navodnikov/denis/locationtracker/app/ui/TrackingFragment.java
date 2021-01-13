package ru.navodnikov.denis.locationtracker.app.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;


import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import ru.navodnikov.denis.locationtracker.R;
import ru.navodnikov.denis.locationtracker.databinding.FragmentTrackingBinding;

import static ru.navodnikov.denis.locationtracker.app.ui.Constants.NAME_OF_FDB;
import static ru.navodnikov.denis.locationtracker.app.ui.Constants.REQUEST_LOCATION;

public class TrackingFragment extends Fragment {

    private FragmentTrackingBinding fragmentTrackingBinding;
    private FusedLocationProviderClient fusedLocationClient;
    FirebaseFirestore db;

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

    public TrackingFragment() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = FirebaseFirestore.getInstance();
        fragmentTrackingBinding.buttonLogOut.setOnClickListener(v -> signOut());
        fragmentTrackingBinding.buttonStartTracking.setOnClickListener(v -> sendLocationStart());
        fragmentTrackingBinding.buttonStopTracking.setOnClickListener(v -> sendLocationStop());
    }

    private void sendLocationStart() {
        if (ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_LOCATION);
        } else {
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            db.collection(NAME_OF_FDB).add(location).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                                }
                            })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.w("TAG", "Error adding document", e);
                                        }
                                    });
                        }
                    });
        }

    }

    private void sendLocationStop() {
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentTrackingBinding = null;
    }

    public void signOut(){
        FirebaseAuth.getInstance().signOut();
        Navigation.findNavController(getActivity(), R.id.nav_host).navigate(R.id.action_trackingFragment_to_loginFragment);
    }
}