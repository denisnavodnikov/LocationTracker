package ru.navodnikov.denis.locationtracker.app.ui.start;

import ru.navodnikov.denis.locationtracker.abstractions.FragmentContract;
import ru.navodnikov.denis.locationtracker.app.ui.start.infra.StartScreenState;

public class StartContract {
    public interface ViewModel extends FragmentContract.ViewModel<StartScreenState> {

        void onItemClicked(int button);

        void checkUserAuthorisation();
    }

    public interface View extends FragmentContract.View {
        void proceedToRegisterScreen();

        void proceedToTrackingScreen();

        void proceedToLoginScreen();
    }

}
