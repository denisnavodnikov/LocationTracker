package ru.navodnikov.denis.locationtracker.app.ui.start;


import ru.navodnikov.denis.locationtracker.app.ui.start.infra.StartScreenState;
import ru.navodnikov.denis.locationtracker.mvi.MviViewModel;

public class StartViewModel extends MviViewModel<StartScreenState> implements StartContract.ViewModel {
    private final StartContract.Router router;

    public StartViewModel(StartContract.Router router) {
        this.router = router;
    }


    @Override
    public void onItemClicked(int button) {
        router.proceedToNextScreen(button);
    }

}
