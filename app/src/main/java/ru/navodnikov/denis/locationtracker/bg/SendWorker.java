package ru.navodnikov.denis.locationtracker.bg;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class SendWorker extends Worker {

    public SendWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        // отправка местоположения в фаирбэйс

        return Result.success();
    }
}
