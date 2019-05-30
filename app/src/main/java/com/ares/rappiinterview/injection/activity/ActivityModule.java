package com.ares.rappiinterview.injection.activity;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ares on 2019-05-27
 */
@Module
public class ActivityModule {

    private Context context;

    public ActivityModule(Context context) {
        this.context = context;
    }

    @Provides
    @ActivityScope
    Context provideContext() {
        return context;
    }

}
