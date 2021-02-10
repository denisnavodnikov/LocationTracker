package ru.navodnikov.denis.locationtracker.app.bg;

import javax.inject.Inject;

import ru.navodnikov.denis.locationtracker.app.TrackerApp;
import ru.navodnikov.denis.locationtracker.models.repo.TrackerRepo;
import ru.navodnikov.denis.locationtracker.models.repo.network.Network;


public class SendLocationModelFactory {
    @Inject
    TrackerRepo repo;
    @Inject
    Network network;


    public SendLocationModelFactory() {
        //TODO: change other module
        TrackerApp.getComponent().inject(this);
    }

    public SendTrackerContract.LocationModel create() {
        return new SendLocationModel(repo, network);
    }
}
