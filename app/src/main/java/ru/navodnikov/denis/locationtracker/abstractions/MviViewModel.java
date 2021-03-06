package ru.navodnikov.denis.locationtracker.abstractions;

import androidx.annotation.CallSuper;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public abstract class MviViewModel <T> extends ViewModel implements FragmentContract.ViewModel<T>{

    private final CompositeDisposable onDestroyDisposables = new CompositeDisposable();
    private final MutableLiveData<T> stateHolder = new MutableLiveData<>();

    @Override
    public MutableLiveData<T> getStateObservable() {
        return stateHolder;
    }

    @CallSuper
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    protected void onAny(LifecycleOwner owner, Lifecycle.Event event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            onDestroyDisposables.clear();
        }
    }


    protected void observeTillDestroy(Disposable... subscriptions) {
        onDestroyDisposables.addAll(subscriptions);
    }

    protected void setState(T state) {
        stateHolder.setValue(state);
    }

    public void postState(T state) {
        stateHolder.postValue(state);
    }

    protected boolean hasOnDestroyDisposables() {
        return onDestroyDisposables.size() != 0;
    }

}
