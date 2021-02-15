package ru.navodnikov.denis.locationtracker.models_impl.sharedpref;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Inject;

import ru.navodnikov.denis.locationtracker.app.TrackerApp;
import ru.navodnikov.denis.locationtracker.app.utils.Constants;
import ru.navodnikov.denis.locationtracker.models.sharedpref.SharedPref;

public class TrackerSharedPref implements SharedPref {

    private final SharedPreferences sharedPreferences;

    public TrackerSharedPref(Context context) {
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public void putUserId(String id) {
        sharedPreferences.edit().putString(Constants.KEY_ID, id).apply();
    }

    @Override
    public String getUserId() {
        return sharedPreferences.getString(Constants.KEY_ID, Constants.ID_DEF_VALUE);
    }
}
