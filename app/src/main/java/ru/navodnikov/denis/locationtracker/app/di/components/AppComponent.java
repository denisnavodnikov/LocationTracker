package ru.navodnikov.denis.locationtracker.app.di.components;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import ru.navodnikov.denis.locationtracker.app.TrackerApp;
import ru.navodnikov.denis.locationtracker.app.di.builders.ActivityBuildersModule;
import ru.navodnikov.denis.locationtracker.app.di.builders.ViewModelBuilderModule;
import ru.navodnikov.denis.locationtracker.app.di.module.AppModule;
import ru.navodnikov.denis.locationtracker.app.di.module.ViewModelFactoryModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        ActivityBuildersModule.class,
        AppModule.class,
        ViewModelFactoryModule.class,
        ViewModelBuilderModule.class
})
public interface AppComponent extends AndroidInjector<TrackerApp> {

    @Component.Builder
    interface Builder {
        AppComponent build();

        @BindsInstance
        Builder application(Application application);
    }




}
