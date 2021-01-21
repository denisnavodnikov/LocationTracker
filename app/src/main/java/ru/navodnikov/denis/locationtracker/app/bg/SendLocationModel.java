package ru.navodnikov.denis.locationtracker.app.bg;


import android.Manifest;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import ru.navodnikov.denis.locationtracker.models.cache.Cache;
import ru.navodnikov.denis.locationtracker.models.repo.TrackerRepo;

public class SendLocationModel implements SendTrackerContract.ServiceModel {

    private final CompositeDisposable disposables = new CompositeDisposable();
    private final TrackerRepo repo;
    private final Cache cache;


    public SendLocationModel(TrackerRepo repo, Cache cache) {
        this.repo = repo;
        this.cache = cache;
    }
// TODO поменяять логику работы метода
    @Override
    public void sendLocationStart() {
//        if (ActivityCompat.checkSelfPermission(getActivity(),
//                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(),
//                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(getActivity(),
//                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
//                    REQUEST_LOCATION);
//        } else {
//            fusedLocationClient.getLastLocation()
//                    .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
//                        @Override
//                        public void onSuccess(Location location) {
//                            // Got last known location. In some rare situations this can be null.
//                            db.collection(NAME_OF_FDB).add(location).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                                @Override
//                                public void onSuccess(DocumentReference documentReference) {
//                                    Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
//                                }
//                            })
//                                    .addOnFailureListener(new OnFailureListener() {
//                                        @Override
//                                        public void onFailure(@NonNull Exception e) {
//                                            Log.w("TAG", "Error adding document", e);
//                                        }
//                                    });
//                        }
//                    });
//        }
//
    }

}
