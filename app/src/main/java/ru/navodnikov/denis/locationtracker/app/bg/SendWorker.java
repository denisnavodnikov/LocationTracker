package ru.navodnikov.denis.locationtracker.app.bg;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class SendWorker extends Worker {

    private final SendTrackerContract.LocationModel model;

    public SendWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        model = new SendLocationModelFactory().create();
    }

    @NonNull
    @Override
    public Result doWork() {
        model.sendLocationStart();
        return Result.success();
    }
}
