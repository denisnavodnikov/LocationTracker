package ru.navodnikov.denis.locationtracker.app.ui.start;

import ru.navodnikov.denis.locationtracker.abstractions.FragmentContract;

public class StartContract {

    public interface View extends FragmentContract.View {
        void proceedToRegisterScreen();

        void proceedToTrackingScreen();

        void proceedToLoginScreen();
    }

}
