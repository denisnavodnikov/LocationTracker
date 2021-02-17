package ru.navodnikov.denis.locationtracker.models;

import android.app.Activity;

public interface ActivityHolder {
    void setActivity(Activity activity);

    Activity getActivity();

    void clear();
}
