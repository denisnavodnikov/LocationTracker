package ru.navodnikov.denis.locationtracker.app.ui.start;

import ru.navodnikov.denis.locationtracker.mvi.FragmentContract;

public class StartContract {

//    TODO возможно этот интерфейс здесь не нужен
    public interface ViewModel extends FragmentContract.ViewModel<StartScreenState> {
    }

    public interface Host extends FragmentContract.Host {
        void proceedToLoginScreen();
        void proceedToRegisterScreen();
    }
    public interface View extends FragmentContract.View {
    }
    public interface Router {
        void proceedToNextScreen();

    }
}
