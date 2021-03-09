package ru.navodnikov.denis.locationtracker.app.bg;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import javax.inject.Inject;

public class SendWorker extends Worker {

    @Inject
    SendTrackerContract.LocationSender model;

    public SendWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            model.sendLocationsToServer();
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure();
        }
    }
}
