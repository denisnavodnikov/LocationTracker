package ru.navodnikov.denis.locationtracker.app.ui.start;

import ru.navodnikov.denis.locationtracker.app.ui.start.infra.StartScreenState;
import ru.navodnikov.denis.locationtracker.mvi.FragmentContract;

public class StartContract {


    public interface ViewModel extends FragmentContract.ViewModel<StartScreenState> {

        void onItemClicked(int button);

        void checkUserAuthorisation();


    }

    public interface Host extends FragmentContract.Host {

    }

    public interface View extends FragmentContract.View {
        void proceedToRegisterScreen();

        void proceedToTrackingScreen();

        void proceedToLoginScreen();
    }

}
