package com.ares.rappiinterview;

import android.app.Application;

import com.ares.rappiinterview.injection.application.ApplicationComponent;
import com.ares.rappiinterview.injection.application.ApplicationModule;
import com.ares.rappiinterview.injection.application.DaggerApplicationComponent;
import com.facebook.stetho.Stetho;

/**
 * Created by ares on 2019-05-27
 */
public class RappiApp extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        if(BuildConfig.DEBUG)
            Stetho.initializeWithDefaults(this);

        this.setUpGraph();
    }

    private void setUpGraph() {
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        component.inject(this);
    }

    public ApplicationComponent component(){
        return component;
    }
}
