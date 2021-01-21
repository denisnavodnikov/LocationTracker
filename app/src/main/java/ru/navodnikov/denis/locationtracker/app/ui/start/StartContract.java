package ru.navodnikov.denis.locationtracker.app.ui.start;

import ru.navodnikov.denis.locationtracker.app.ui.start.infra.StartScreenState;
import ru.navodnikov.denis.locationtracker.mvi.FragmentContract;

public class StartContract {


    public interface ViewModel extends FragmentContract.ViewModel<StartScreenState> {

    void onItemClicked(int button);
    }

    public interface Host extends FragmentContract.Host {
        void proceedToLoginScreen();
        void proceedToRegisterScreen();
    }
    public interface View extends FragmentContract.View {
    }

    public interface Router {
        void proceedToNextScreen(int button);

    }
}
