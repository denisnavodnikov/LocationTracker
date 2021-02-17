package ru.navodnikov.denis.locationtracker.models_impl;

import android.app.Activity;

import java.lang.ref.WeakReference;

import ru.navodnikov.denis.locationtracker.models.ActivityHolder;

public class ActivityHolderImp implements ActivityHolder {
    private WeakReference<Activity> activityWeakReference;

    @Override
    public void setActivity(Activity activity) {
        this.activityWeakReference = new WeakReference<>(activity);
    }

    @Override
    public Activity getActivity() {
        return activityWeakReference.get();
    }
    @Override
    public void clear(){
        activityWeakReference.clear();
    }
}
