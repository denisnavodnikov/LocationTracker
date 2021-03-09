package ru.navodnikov.denis.locationtracker.app.bg;

public class SendTrackerContract {
    public interface LocationSender {

        void sendLocationStart();

        void sendLocationStop();

        void sendLocationsToServer();
    }

}
