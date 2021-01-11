package ru.navodnikov.denis.locationtracker.bg;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import ru.navodnikov.denis.locationtracker.TrackerApp;

public class SendWorker extends Worker {

//    private final SendLocationModel model;

    public SendWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
//        model = new SendLocationModelFactory(TrackerApp.getInstance().getAppComponent()).create();
    }

    @NonNull
    @Override
    public Result doWork() {
        // отправка местоположения в фаирбэйс
//        model.sendLocationStart();

        return Result.success();
    }
}
