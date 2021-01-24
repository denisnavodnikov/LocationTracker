package ru.navodnikov.denis.locationtracker.app.ui.tracking;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

import ru.navodnikov.denis.locationtracker.app.ui.tracking.infra.TrackingScreenState;
import ru.navodnikov.denis.locationtracker.models.cache.Cache;
import ru.navodnikov.denis.locationtracker.models.repo.TrackerRepo;
import ru.navodnikov.denis.locationtracker.models.repo.network.Network;
import ru.navodnikov.denis.locationtracker.mvi.MviViewModel;

import static ru.navodnikov.denis.locationtracker.app.ui.Constants.NAME_OF_FDB;
import static ru.navodnikov.denis.locationtracker.app.ui.Constants.REQUEST_LOCATION;

public class TrackingViewModel extends MviViewModel<TrackingScreenState> implements TrackingContract.ViewModel{
    private final TrackingContract.Router router;
    private final TrackerRepo repo;
    private final Cache cache;
    private final Network network;

    public TrackingViewModel(TrackingContract.Router router, TrackerRepo repo, Cache cache, Network network) {
        this.router = router;
        this.repo = repo;
        this.cache = cache;
        this.network = network;
    }
//    TODO добавить методы с логикой работы

    @Override
    public void logOut() {
        network.getmAuth().signOut();
        router.proceedToNextScreen();
    }

    @Override
    public void startTracking() {


    }
    @Override
    public void stopTracking() {

    }


}
