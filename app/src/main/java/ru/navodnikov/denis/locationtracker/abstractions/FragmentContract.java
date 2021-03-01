package ru.navodnikov.denis.locationtracker.abstractions;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;

public class FragmentContract {

    public interface ViewModel<T> extends LifecycleObserver {
        MutableLiveData<T> getStateObservable();
        void postState(T t);
    }

    public interface View {
    }

}
