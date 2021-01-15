package ru.navodnikov.denis.locationtracker.mvi;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;

public class FragmentContract {

    public interface ViewModel<T> extends LifecycleObserver {
        MutableLiveData<T> getStateObservable();
    }
    public interface View {
    }

    public interface Host {
    }
}
