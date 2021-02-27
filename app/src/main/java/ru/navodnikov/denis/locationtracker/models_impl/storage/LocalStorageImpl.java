package ru.navodnikov.denis.locationtracker.models_impl.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import ru.navodnikov.denis.locationtracker.app.utils.Constants;
import ru.navodnikov.denis.locationtracker.models.storage.UserStorage;

public class LocalStorageImpl implements UserStorage {

    private final SharedPreferences sharedPreferences;

    public LocalStorageImpl(Context context) {
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
