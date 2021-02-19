package ru.navodnikov.denis.locationtracker.app.bg;

public class SendTrackerContract {
    public interface LocationModel {

        void sendLocationStart();

        void sendLocationStop();
    }

}
