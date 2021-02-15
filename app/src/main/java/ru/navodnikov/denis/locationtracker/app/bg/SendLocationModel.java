package ru.navodnikov.denis.locationtracker.app.bg;


import io.reactivex.rxjava3.disposables.CompositeDisposable;
import ru.navodnikov.denis.locationtracker.models.location.AppLocation;
import ru.navodnikov.denis.locationtracker.models.repo.TrackerRepo;
import ru.navodnikov.denis.locationtracker.models.repo.network.Network;
import ru.navodnikov.denis.locationtracker.models.sharedpref.SharedPref;

public class SendLocationModel implements SendTrackerContract.LocationModel {

    private final CompositeDisposable disposables = new CompositeDisposable();
    private final TrackerRepo repo;
    private final Network network;
    private final AppLocation appLocation;
    private final SharedPref sharedPref;

    public SendLocationModel(TrackerRepo repo, Network network, AppLocation appLocation, SharedPref sharedPref) {
        this.repo = repo;
        this.network = network;
        this.appLocation = appLocation;
        this.sharedPref = sharedPref;
    }
// TODO поменяять логику работы метода
    @Override
    public void sendLocationStart() {
//        appLocation.getLocation();
//        repo.getDao().saveLocation();
//        network.sendLocation();

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
