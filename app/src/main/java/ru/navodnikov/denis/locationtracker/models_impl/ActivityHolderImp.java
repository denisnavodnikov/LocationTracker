package ru.navodnikov.denis.locationtracker.models_impl;

import android.app.Activity;

import androidx.annotation.Nullable;

import java.lang.ref.WeakReference;
import java.util.Objects;

import ru.navodnikov.denis.locationtracker.models.ActivityHolder;

public class ActivityHolderImp implements ActivityHolder {
    private WeakReference<Activity> activityWeakReference;

    @Override
    public void setActivity(Activity activity) {
        this.activityWeakReference = new WeakReference<>(activity);
    }

    @Nullable
    @Override
    public Activity getActivity() {
        if (activityWeakReference.get() != null) {
            return activityWeakReference.get();
        }else return Objects.requireNonNull(activityWeakReference.get(),"Activity is null");
    }

    @Override
    public void clear() {
        activityWeakReference.clear();
    }
}
